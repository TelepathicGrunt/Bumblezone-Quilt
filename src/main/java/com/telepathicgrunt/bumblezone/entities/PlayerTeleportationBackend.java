package com.telepathicgrunt.bumblezone.entities;

import com.google.common.primitives.Doubles;
import com.telepathicgrunt.bumblezone.Bumblezone;
import com.telepathicgrunt.bumblezone.tags.BZBlockTags;
import com.telepathicgrunt.bumblezone.utils.BzPlacingUtils;
import com.telepathicgrunt.bumblezone.world.dimension.BzDimension;
import net.minecraft.block.BeehiveBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.BlockTags;
import net.minecraft.tag.FluidTags;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.WorldChunk;
import org.apache.logging.log4j.Level;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PlayerTeleportationBackend {
    //use this to teleport to any dimension
    //FabricDimensions.teleport(playerEntity, <destination dimension type>, <placement>);

    public static void enteringBumblezone(Entity entity){
        //Note, the player does not hold the previous dimension oddly enough.
        Vec3d destinationPosition;

        if (entity instanceof ServerPlayerEntity) {
            Bumblezone.PLAYER_COMPONENT.get(entity).setNonBZPos(entity.getPos());
            Bumblezone.PLAYER_COMPONENT.get(entity).setNonBZDimension(((ServerPlayerEntity) entity).getServerWorld().getRegistryKey().getValue());
            MinecraftServer minecraftServer = entity.getServer(); // the server itself
            ServerWorld bumblezoneWorld = minecraftServer.getWorld(BzDimension.BZ_WORLD_KEY);
            RegistryKey<World> world_key = entity.world.getRegistryKey();

            // Prevent crash due to mojang bug that makes mod's json dimensions not exist upload first creation of world on server. A restart fixes this.
            if(bumblezoneWorld == null){
                Bumblezone.LOGGER.log(Level.INFO, "Bumblezone: Please restart the server. The Bumblezone dimension hasn't been made yet due to this bug: https://bugs.mojang.com/browse/MC-195468. A restart will fix this.");
                Text message = new LiteralText("Please restart the server. The Bumblezone dimension hasn't been made yet due to this bug: §6https://bugs.mojang.com/browse/MC-195468§f. A restart will fix this.");
                ((ServerPlayerEntity)entity).sendMessage(message, true);
                return;
            }

            ServerWorld serverWorld = minecraftServer.getWorld(world_key);
            if(serverWorld == null){
                serverWorld = minecraftServer.getWorld(World.OVERWORLD);
            }
            destinationPosition = teleportByPearl((PlayerEntity) entity, serverWorld, bumblezoneWorld);
            ((ServerPlayerEntity)entity).teleport(
                    bumblezoneWorld,
                    destinationPosition.x,
                    destinationPosition.y,
                    destinationPosition.z,
                    entity.yaw,
                    entity.pitch
            );
        }
    }


    public static void exitingBumblezone(Entity entity, ServerWorld destination){
        boolean upwardChecking = entity.getY() > 0;
        Vec3d destinationPosition;

        if (entity instanceof ServerPlayerEntity) {
            destinationPosition = teleportByOutOfBounds((PlayerEntity) entity, destination, upwardChecking);

            ((ServerPlayerEntity)entity).teleport(
                    destination,
                    destinationPosition.x,
                    destinationPosition.y,
                    destinationPosition.z,
                    entity.yaw,
                    entity.pitch
            );
        }
    }


    private static Vec3d teleportByOutOfBounds(PlayerEntity playerEntity, ServerWorld destination, boolean checkingUpward) {
        //converts the position to get the corresponding position in non-bumblezone dimension
        double coordinateScale = playerEntity.getEntityWorld().getDimension().getCoordinateScale() / destination.getDimension().getCoordinateScale();
        BlockPos finalSpawnPos;
        BlockPos validBlockPos = null;

        if(Bumblezone.BZ_CONFIG.BZDimensionConfig.teleportationMode == 1){
            finalSpawnPos = new BlockPos(
                    Doubles.constrainToRange(playerEntity.getPos().getX() * coordinateScale, -29999936D, 29999936D),
                    playerEntity.getPos().getY(),
                    Doubles.constrainToRange(playerEntity.getPos().getZ() * coordinateScale, -29999936D, 29999936D));

            //Gets valid space in other world
            validBlockPos = validPlayerSpawnLocationByBeehive(destination, finalSpawnPos, 100, checkingUpward);
        }

        else if(Bumblezone.BZ_CONFIG.BZDimensionConfig.teleportationMode == 2){
            Vec3d playerPos = Bumblezone.PLAYER_COMPONENT.get(playerEntity).getNonBZPos();
            if(playerPos != null){
                validBlockPos = new BlockPos(playerPos);
            }
        }

        // Teleportaion mode 3
        else{
            finalSpawnPos = new BlockPos(
                    Doubles.constrainToRange(playerEntity.getPos().getX() * coordinateScale, -29999936D, 29999936D),
                    playerEntity.getPos().getY(),
                    Doubles.constrainToRange(playerEntity.getPos().getZ() * coordinateScale, -29999936D, 29999936D));

            //Gets valid space in other world
            validBlockPos = validPlayerSpawnLocationByBeehive(destination, finalSpawnPos, 100, checkingUpward);

            Vec3d playerPos = Bumblezone.PLAYER_COMPONENT.get(playerEntity).getNonBZPos();
            if(validBlockPos == null && playerPos != null) {
                validBlockPos = new BlockPos(playerPos);
            }
        }

        // If all else fails, fallback to player pos
        finalSpawnPos = validBlockPos;
        if(finalSpawnPos == null) {
            finalSpawnPos = new BlockPos(playerEntity.getPos());
        }

        // Make sure spacing is safe if in mode 2 or mode 3 when doing forced teleportation when valid land isn't found.
        if (Bumblezone.BZ_CONFIG.BZDimensionConfig.teleportationMode == 2 ||
            (Bumblezone.BZ_CONFIG.BZDimensionConfig.teleportationMode == 3 && validBlockPos == null))
        {

            if (destination.getBlockState(finalSpawnPos.up()).isOpaque()) {
                destination.setBlockState(finalSpawnPos, Blocks.AIR.getDefaultState(), 3);
                destination.setBlockState(finalSpawnPos.up(), Blocks.AIR.getDefaultState(), 3);
            }
        }

        //use found location
        //teleportation spot finding complete. return spot
        return new Vec3d(
                finalSpawnPos.getX() + 0.5D,
                finalSpawnPos.getY() + 1,
                finalSpawnPos.getZ() + 0.5D
        );
    }

    private static Vec3d teleportByPearl(PlayerEntity playerEntity, ServerWorld originalWorld, ServerWorld bumblezoneWorld) {


        //converts the position to get the corresponding position in bumblezone dimension
        double coordinateScale = 1;
        if (Bumblezone.BZ_CONFIG.BZDimensionConfig.teleportationMode != 2) {
            coordinateScale = originalWorld.getDimension().getCoordinateScale() / bumblezoneWorld.getDimension().getCoordinateScale();
        }
        BlockPos blockpos = new BlockPos(
                Doubles.constrainToRange(playerEntity.getPos().getX() * coordinateScale, -29999936D, 29999936D),
                playerEntity.getPos().getY(),
                Doubles.constrainToRange(playerEntity.getPos().getZ() * coordinateScale, -29999936D, 29999936D));


        //gets valid space in other world
        BlockPos validBlockPos = validPlayerSpawnLocation(bumblezoneWorld, blockpos, 10);


        //No valid space found around destination. Begin secondary valid spot algorithms
        if (validBlockPos == null) {
            //go down to first solid land with air above.
            validBlockPos = new BlockPos(
                    blockpos.getX(),
                    BzPlacingUtils.topOfSurfaceBelowHeightThroughWater(bumblezoneWorld, blockpos.getY(), 0, blockpos) + 1,
                    blockpos.getZ());

            //No solid land was found. Who digs out an entire chunk?!
            if (validBlockPos.getY() == 0) {
                validBlockPos = null;
            }
            //checks if spot is not two water blocks with air block able to be reached above
            else if (bumblezoneWorld.getBlockState(validBlockPos).getMaterial() == Material.WATER &&
                    bumblezoneWorld.getBlockState(validBlockPos.up()).getMaterial() == Material.WATER) {
                BlockPos.Mutable mutable = new BlockPos.Mutable(validBlockPos.getX(), validBlockPos.getY(), validBlockPos.getZ());

                //moves upward looking for air block while not interrupted by a solid block
                while (mutable.getY() < 255 && !bumblezoneWorld.isAir(mutable) || bumblezoneWorld.getBlockState(mutable).getMaterial() == Material.WATER) {
                    mutable.move(Direction.UP);
                }
                if (bumblezoneWorld.getBlockState(mutable).getMaterial() != Material.AIR) {
                    validBlockPos = null; // No air found. Let's not place player here where they could drown
                } else {
                    validBlockPos = mutable; // Set player to top of water level
                }
            }
            //checks if spot is not a non-solid block with air block above
            else if ((!bumblezoneWorld.isAir(validBlockPos) && bumblezoneWorld.getBlockState(validBlockPos).getMaterial() != Material.WATER) &&
                    bumblezoneWorld.getBlockState(validBlockPos.up()).getMaterial() != Material.AIR) {
                validBlockPos = null;
            }


            //still no valid position, time to force a valid location ourselves
            if (validBlockPos == null) {
                //We are going to spawn player at exact spot of scaled coordinates by placing air at the spot with honeycomb bottom
                //and honeycomb walls to prevent drowning
                //This is the last resort
                bumblezoneWorld.setBlockState(blockpos, Blocks.AIR.getDefaultState());
                bumblezoneWorld.setBlockState(blockpos.up(), Blocks.AIR.getDefaultState());

                bumblezoneWorld.setBlockState(blockpos.down(), Blocks.HONEYCOMB_BLOCK.getDefaultState());
                bumblezoneWorld.setBlockState(blockpos.up().up(), Blocks.HONEYCOMB_BLOCK.getDefaultState());

                bumblezoneWorld.setBlockState(blockpos.north(), Blocks.HONEYCOMB_BLOCK.getDefaultState());
                bumblezoneWorld.setBlockState(blockpos.west(), Blocks.HONEYCOMB_BLOCK.getDefaultState());
                bumblezoneWorld.setBlockState(blockpos.east(), Blocks.HONEYCOMB_BLOCK.getDefaultState());
                bumblezoneWorld.setBlockState(blockpos.south(), Blocks.HONEYCOMB_BLOCK.getDefaultState());
                bumblezoneWorld.setBlockState(blockpos.north().up(), Blocks.HONEYCOMB_BLOCK.getDefaultState());
                bumblezoneWorld.setBlockState(blockpos.west().up(), Blocks.HONEYCOMB_BLOCK.getDefaultState());
                bumblezoneWorld.setBlockState(blockpos.east().up(), Blocks.HONEYCOMB_BLOCK.getDefaultState());
                bumblezoneWorld.setBlockState(blockpos.south().up(), Blocks.HONEYCOMB_BLOCK.getDefaultState());
                validBlockPos = blockpos;
            }

        }

        // if player throws pearl at hive and then goes to sleep, they wake up
        if (playerEntity.isSleeping()) {
            playerEntity.wakeUp();
        }

        // place hive block below player if they would've fallen out of dimension
        // because there's air all the way down to y = 0 below player
        int heightCheck = 0;
        while(heightCheck <= validBlockPos.getY() && bumblezoneWorld.getBlockState(validBlockPos.down(heightCheck)).isAir()){
            heightCheck++;
        }
        if(heightCheck >= validBlockPos.getY()){
            bumblezoneWorld.setBlockState(validBlockPos.getY() == 0 ? validBlockPos : validBlockPos.down(), Blocks.HONEYCOMB_BLOCK.getDefaultState());
        }

        // teleportation spot finding complete. return spot
        return new Vec3d(
                validBlockPos.getX() + 0.5D,
                validBlockPos.getY(),
                validBlockPos.getZ() + 0.5D
        );
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //Util


    private static BlockPos validPlayerSpawnLocationByBeehive(World world, BlockPos position, int maximumRange, boolean checkingUpward) {

        // Gets the height of highest block over the area so we aren't checking an
        // excessive amount of area above that doesn't need checking.
        int maxHeight = 0;
        int halfRange = maximumRange / 2;
        BlockPos.Mutable mutableBlockPos = new BlockPos.Mutable();
        Set<WorldChunk> chunksInRange = new HashSet<>();
        for (int x = -halfRange; x < halfRange; x++) {
            for (int z = -halfRange; z < halfRange; z++) {
                mutableBlockPos.set(position.getX() + x, 0, position.getZ() + z);
                Chunk chunk = world.getChunk(mutableBlockPos);
                if(chunk instanceof WorldChunk) chunksInRange.add((WorldChunk)chunk);
                maxHeight = Math.max(maxHeight, world.getTopY(Heightmap.Type.MOTION_BLOCKING, mutableBlockPos.getX(), mutableBlockPos.getZ()));
            }
        }
        maxHeight = Math.min(maxHeight, world.getHeight() - 1); //cannot place user at roof of other dimension

        // two mutable blockpos we can reuse for calculations
        BlockPos.Mutable mutableTemp1 = new BlockPos.Mutable();
        BlockPos.Mutable mutableTemp2 = new BlockPos.Mutable();

        // Get all block entities from the chunks
        Set<BlockEntity> tempSet = new HashSet<>();
        chunksInRange.stream().map(WorldChunk::getBlockEntities).forEach(map -> tempSet.addAll(map.values()));
        Stream<BlockEntity> allBlockEntitiesInRange = tempSet.stream().filter(be -> {

            // filter out all block entities that are not valid bee blocks we want
            if(!isValidBeeHive(be.getCachedState())){
                return false;
            }

            // Filter out all positions that are below sealevel if we do not want underground spots.
            if (Bumblezone.BZ_CONFIG.BZDimensionConfig.seaLevelOrHigherExitTeleporting && be.getPos().getY() < world.getSeaLevel()) {
                return false;
            }

            // Return all block entities that are within the radius we want
            mutableTemp1.set(be.getPos()).move(-position.getX(), 0, -position.getZ());
            return mutableTemp1.getX() * mutableTemp1.getX() + mutableTemp1.getZ() + mutableTemp1.getZ() < maximumRange;
        });

        // Sort the block entities in the order we want to check if we should spawn next to them
        List<BlockEntity> sortedBlockEntities = allBlockEntitiesInRange.sorted((be1, be2) -> {
            mutableTemp1.set(be1.getPos()).move(-position.getX(), 0, -position.getZ());
            mutableTemp2.set(be2.getPos()).move(-position.getX(), 0, -position.getZ());
            int heightDiff = mutableTemp1.getY() - mutableTemp2.getY();
            int xzDiff = Math.abs(mutableTemp1.getX() - mutableTemp2.getX()) + Math.abs(mutableTemp1.getZ() - mutableTemp2.getZ());

            // Reverse direction if checking upward
            if(checkingUpward){
                heightDiff *= -1;
                xzDiff *= -1;
            }

            // Creates a cone of block entities to check where we start from the tip and work our way to the base of the cone.
            return heightDiff - xzDiff;
        }).collect(Collectors.toList());

        for(BlockEntity blockEntity : sortedBlockEntities){
            //try to find a valid spot next to it
            BlockPos validSpot = validPlayerSpawnLocation(world, blockEntity.getPos(), 4);
            if (validSpot != null) {
                return validSpot;
            }
        }

        //this mode will not generate a beenest automatically.
        if(Bumblezone.BZ_CONFIG.BZDimensionConfig.teleportationMode == 3) return null;

        //no valid spot was found, generate a hive and spawn us on the highest land
        //This if statement is so we dont get placed on roof of other roofed dimension
        if (maxHeight + 1 < world.getHeight()) {
            maxHeight += 1;
        }
        mutableBlockPos.set(
                position.getX(),
                BzPlacingUtils.topOfSurfaceBelowHeight(world, maxHeight, 0, position),
                position.getZ());

        if (mutableBlockPos.getY() <= 0) {
            //No valid spot was found. Just place character on a generate hive at center of height of coordinate
            //Basically just f*** it at this point lol
            mutableBlockPos.set(
                    position.getX(),
                    world.getHeight() / 2,
                    position.getZ());

        }
        createSpaceForPlayer(world, mutableBlockPos);
        return mutableBlockPos;
    }

    private static void createSpaceForPlayer(World world, BlockPos.Mutable mutableBlockPos) {
        if(Bumblezone.BZ_CONFIG.BZDimensionConfig.generateBeenest)
            world.setBlockState(mutableBlockPos, Blocks.BEE_NEST.getDefaultState());
        else if(world.getBlockState(mutableBlockPos).getMaterial() == Material.AIR ||
                (!world.getBlockState(mutableBlockPos).getFluidState().isEmpty() &&
                    !world.getBlockState(mutableBlockPos).getFluidState().isIn(FluidTags.WATER)))
            world.setBlockState(mutableBlockPos, Blocks.HONEYCOMB_BLOCK.getDefaultState());

        world.setBlockState(mutableBlockPos.up(), Blocks.AIR.getDefaultState());
    }

    private static BlockPos validPlayerSpawnLocation(World world, BlockPos position, int maximumRange) {
        //Try to find 2 non-solid spaces around it that the player can spawn at
        int radius;
        int outerRadius;
        int distanceSq;
        BlockPos.Mutable currentPos = new BlockPos.Mutable(position.getX(), position.getY(), position.getZ());

        //checks for 2 non-solid blocks with solid block below feet
        //checks outward from center position in both x, y, and z.
        //The x2, y2, and z2 is so it checks at center of the range box instead of the corner.
        for (int range = 0; range < maximumRange; range++) {
            radius = range * range;
            outerRadius = (range + 1) * (range + 1);

            for (int y = 0; y <= range * 2; y++) {
                int y2 = y > range ? -(y - range) : y;


                for (int x = 0; x <= range * 2; x++) {
                    int x2 = x > range ? -(x - range) : x;


                    for (int z = 0; z <= range * 2; z++) {
                        int z2 = z > range ? -(z - range) : z;

                        distanceSq = x2 * x2 + z2 * z2 + y2 * y2;
                        if (distanceSq >= radius && distanceSq < outerRadius) {
                            currentPos.set(position.add(x2, y2, z2));
                            if (world.getBlockState(currentPos.down()).isOpaque() &&
                                    world.getBlockState(currentPos).getMaterial() == Material.AIR &&
                                    world.getBlockState(currentPos.up()).getMaterial() == Material.AIR) {
                                //valid space for player is found
                                return currentPos;
                            }
                        }
                    }
                }
            }
        }

        return null;
    }

    public static boolean isValidBeeHive(BlockState block) {
        if(BZBlockTags.BLACKLISTED_TELEPORTATION_HIVES_TAG.contains(block.getBlock())) return false;

        if(BlockTags.BEEHIVES.contains(block.getBlock()) || block.getBlock() instanceof BeehiveBlock) {
            return true;
        }

        return false;
    }

    // Player exiting Bumblezone dimension
    public static void playerLeavingBz(Identifier dimensionLeaving, ServerPlayerEntity serverPlayerEntity){
        //Updates the non-BZ dimension that the player is leaving
        if (!dimensionLeaving.equals(Bumblezone.MOD_DIMENSION_ID)) {
            Bumblezone.PLAYER_COMPONENT.get(serverPlayerEntity).setNonBZDimension(dimensionLeaving);
        }
    }
}