# MAVEN

For developers that want to add Bumblezone to their mod's workspace:

<blockquote>
repositories {

&nbsp;&nbsp;&nbsp;maven {

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;url "https://nexus.resourcefulbees.com/repository/maven-public/"

&nbsp;&nbsp;&nbsp;}

}
</blockquote>

&nbsp;

Don't forget to change \<modversion> with the actual latest version of this mod like `3.1.0` for example.

<blockquote>
dependencies {

...

&nbsp;&nbsp;&nbsp;&nbsp;modImplementation "com.telepathicgrunt:Bumblezone-Fabric:\<modversion>+1.17.1"

}</blockquote>

**____________________________________________________________________________**

&nbsp;

![The banner logo for The Bumblezone with a friendly and hostile bee shown below the mod name.](https://i.imgur.com/ovPqrFL.png)

## **CURRENTLY FOR 1.17.0 MC**

**(Play this mod with _[Lithium](https://www.curseforge.com/minecraft/mc-mods/lithium)_ for best performance!)**
###### [[Click here for the 1.16.5 Forge version of this mod!]](https://www.curseforge.com/minecraft/mc-mods/the-bumblezone-forge)

**Recent 1.17.0 News:** Ported to 1.17 MC now!

&nbsp;

**Check out the Changelog for more info!**

**(Also, please report any glitches BUGS you find)**

&nbsp;

**Mojang bugs to beware of with this mod:**

<details>

**Make a copy of your world before putting on The Bumblezone! If you remove any dimension mod, the Nether and End dimensions will be missing from the game due to a Mojang bug. Also, putting the mod back on will not restore the dimension. [https://bugs.mojang.com/browse/MC-197860](https://bugs.mojang.com/browse/MC-197860)**

&nbsp;

**SERVER OWNERS NOTE:**

**If you make a fresh new world with this mod on a server, restart the server after the world is made due to this mojang bug causing json dimensions to not be made upon world creation only on servers. [https://bugs.mojang.com/browse/MC-195468](https://bugs.mojang.com/browse/MC-195468)**

</details>

&nbsp;

**____________________________________________________________________________**

## **WHAT IS THE BUMBLEZONE?**

&nbsp;

The Bumblezone is a dimension filled with Bee mobs, Honey Slime mobs, Honeycomb Blocks, Honey Blocks, and a new Porous Honeycomb Block that can be filled with Honey! The dimension itself looks like it is the inside of a bee nest! You will find giant walls in rows with canals of water between them along with giant columns providing support to the ceiling all over. The walls and columns are riddled with honeycomb holes. By default, traveling a block in this dimension is the same as traveling 10 block in the Overworld. (A movement factor of 10. For reference, the Nether has a movement factor of 8 so traveling a block in the Nether is the same as traveling 8 blocks in Overworld)

&nbsp;

But that isn't all! Within the dimension, bees are extremely protective of their honey. If you mine a Honey Block or take honey from a Filled Porous Honeycomb block with a Glass Bottle, all bees around you will get a speed, absorption, and strength potion effect and will become VERY angry at you! You will know you are in DEEP trouble when you get the Wrath of the Hive icon showing you got that effect and the fog in the dimension become more red! The bee will swarm you like crazy and will kill you unless you come very well prepared. A full set of unenchanted Diamond gear will not be enough. (but you can nerf the bees in the config if you find them too strong or buff them if you want to die!) Also, with the Filled Porous Honeycomb block, once you take honey from it, it turns into a Porous Honeycomb block but you can use a Honey Bottle on it to refill it back to being a Filled Porous Honeycomb block! And you may find passive Honey Slime mobs hopping around that can be used as a farmable source of Honey Bottles as well!

&nbsp;

For more info on all the blocks and what mods Bumblezone has direct support for, just click each spoiler in the section below! **And yes, you can use this mod in a modpack!**

&nbsp;

**____________________________________________________________________________**

## WHAT DOES THIS MOD ADD?

![An image that organizes and shows off all of the items and blocks that The Bumblezone has added.](https://media.forgecdn.net/attachments/302/227/2020-07-12_14.png)

### BLOCKS - 10 new blocks and 1 new liquid

<details>

**Filled Porous Honeycomb Block**

&nbsp;

-Use Glass Bottle on this block to obtain a Honey Bottle and turn this block into a Porous Honeycomb Block. However, you will get a Wrath of the Hive effect if you do this. A Dispenser with a Glass Bottle can also pull honey out of this block and turn it into a regular Porous Honeycomb Block.

&nbsp;

-You walk 10% slower on this block but barely noticable.

&nbsp;

-Use a Silk Touch pickaxe to drop this block. Otherwise, mining it will drop a Honeycomb and a Porous Honeycomb Block.

&nbsp;

-Attaching a Comparator to this block will always output a redstone power of 1.

&nbsp;



**Porous Honeycomb Block**



-Use a Honey Bottle on this block to turn this block into a Filled Porous Honeycomb Block.

&nbsp;

-When mined, this block drops itself.

&nbsp;



**Honeycomb Brood Block**



-Watch the larva inside grow through 4 stages over time! When at the 4th stage and this block gets ticked, it will spawn a Bee only if the hole in front isn't blocked off and there is less than 10 bees within 50 blocks of the block. After the bee is spawned, the block then resets back to having a stage 1 of the larva. Sometime, a Honey Slime will spawn too with the Bee!

&nbsp;

-The Larva grows much faster in The Bumblezone dimension but will still grow outside of it. You can speed up the larva stages by feeding it Honey Bottles which always results in growth or feed it Sugar Water Bottle which sometimes results in growth. You can use a Dispenser with Honey Bottles or Sugar Water Bottles right next to the block to automate the growing and spawning of bees with this block!

&nbsp;

-If you use a Glass Bottle on this block, you will get a Honey Bottle, turn this block into an Empty Honeycomb Brood Block, and you will get Wrath of the Hive effect. A Dispenser with a Glass Bottle can also pull honey out of this block and turn it into an Empty Honeycomb Brood Block.

&nbsp;

-If you are near this block and you have Wrath of the Hive, this block will grow the larva and spawn bees much, much faster! Stay away from these blocks when bees are unleashing their wrath!

&nbsp;

-When mined, this block drops Empty Honeycomb Brood Block and a Honeycomb and since you killed the larva, you get Wrath of the Hive effect as well. But if you use a Silk Touch pickaxe, you can get Honeycomb Brood Block instead without angering the bee or getting the effect.

&nbsp;

-When feeding the Honeycomb Brood Block and the larva grows a stage, you will get a short duration of Protection of the Hive effect where nearby Bees will attack anyone who attacked you!

&nbsp;

-Attaching a Comparator to this block will output Redstone power based on what stage the larva is in the block (1 to 4 power).



**Empty Honeycomb Brood Block**



-Does nothing by itself. Merely a decorative block unless certain other mods are on. See the Mod Compatibility section below.

&nbsp;



**Honey Crystal**



-A pretty translucent crystallized honey block that can face any direction and can only be placed on solid sides of blocks.

&nbsp;

-This block can be waterlogged but will transform any water liquid placed into it into Sugar Water instead.

&nbsp;

-Can be found underwater or in caves in The Bumblezone dimension. This is a non-renewable resource so treasure them! They are most common in Hive Wall biome and lest common in Sugar Water biome.

&nbsp;

-When mined, this block drops Honey Crystal Shards (works with Fortune enchantment). If you mine with Silk Touch, this block drops itself instead.

&nbsp;



**Sugar Water**



-Looks like a lighter/cloudier version of regular water and spawns at sealevel for The Bumblezone dimension.

&nbsp;

-Entities swim slower in this liquid and the liquid will slowly heal any Bee touching it when the bee isn't taking any damage at the time.

&nbsp;

-Sugar Cane placed on any block adjacent to Sugar Water will grow much faster and taller! The effect can be stacked up to 4 times (surround the block the Sugar Cane is on with 4 Sugar Water).

&nbsp;

-When the flowing form of this liquid touches Lava, Sugar Infused Cobblestone will be made. If Lava touches the source block of Sugar Water, Sugar Infused Stone will be made instead.

&nbsp;

-Source blocks can be picked up and placed with a Bucket. Using Glass Bottles on this liquid will give you Sugar Water Bottle.

&nbsp;

-Uses the water tag so that anything that checks for the water tag will work with Sugar Water. For example; keeping Coral alive or hydrating Farmland blocks. See this page for more details on what the water tagged blocks can do: [https://minecraft.gamepedia.com/Tag#Fluids](https://minecraft.gamepedia.com/Tag#Fluids)

&nbsp;



**Sticky Honey Residue**



-A very sticky block that can be placed on multiple solid surfaces at once and is created by smelting the following in a Furnace: Honey Crystal blocks, Honey Crystal Shards items, Honey Crystal Shields, or vanilla's Honey blocks. Breaking this block will drop nothing.

&nbsp;

-When any entity touches this block, they move extremely lowly. This block can also be climbed if it is on a wall.

&nbsp;

-You can quickly delete this block by right clicking it with water bucket, sugar water bottle, bottle of water, or a wet sponge in your hand as if you are washing away the stickiness.

&nbsp;

-Flowing fluids and pushing pistons will break this block as well.

&nbsp;



**Sticky Honey Redstone**



-This block does everything Sticky Honey Residue can do and can be created by crafting 1 Sticky Honey Residue with 1 Redstone.

&nbsp;

-When an entity gets stuck on this block, this block outputs a redstone signal of 1 which is pretty useful for Redstone contraptions!

&nbsp;

-When activated, this block will power whatever block it is attached to and also power through that block as well like turning off a Redstone Torch on the other side. If Sticky Honey Residue is attached to the floor, it will power any adjacent blocks attached to it as well such as Redstone dust on the floor.

&nbsp;



**Sugar Infused Stone**



-If placed in a Furnace, the sugar can be quickly burned off for some quick cheap XP.

&nbsp;

-When mined with a Silk Touch pickaxe, this block drops itself. Otherwise, this block drops Sugar and Cobblestone.

&nbsp;



**Sugar Infused Cobblestone**



-If placed in a Furnace, the sugar can be quickly burned off for some quick cheap XP.

&nbsp;

-When mined with a Silk Touch pickaxe, this block drops itself. Otherwise, this block drops Sugar and Cobblestone.

&nbsp;



**Beeswax Planks Block**



-Merely a decorative block that can be found forming the ceiling and floor boundaries of The Bumblezone Dimension.

&nbsp;

-Can be used to craft Bee Nests Blocks by placing 8 Beeswax Planks blocks in a circle with a Honeycomb in the center at a Crafting Table! The crafting shape is the following with B being Beeswax Planks and H being Honeycomb item.  
B B B

B H B

B B B

</details>

&nbsp;


### ITEMS - 4 new items

<details>

**Sugar Water Bottle**



-Obtainable by using a Glass Bottle on a Sugar Water source block.

&nbsp;

-Drinking this will give you half a bar of hunger and the Haste 1 effect for a short period of time. Sugar rush!

&nbsp;

-Can be fed to Honeycomb Brood Block by hand or Dispenser for a 30% chance of growing the larva.

&nbsp;

-Can be fed to any mob that extends BeeEntity to heal them slightly and spawn a heart particle. If fed to bees angry at you, there is a 7% chance of removing Wrath of the Hive effect off of yourself and escape a death of stingers!

&nbsp;

-When fed to a Bee, you have a chance of getting Protection of the Hive effect where nearby Bees will attack anyone who attacked you!

&nbsp;



**Sugar Water Bucket**



-Obtained by using a Bucket on Sugar Water source block or crafting Water Bucket with Sugar. Can be used to move the water around.

&nbsp;

-Works with Dispensers too! The Dispenser can place the Sugar Water like any other buck of fluid.

&nbsp;



**Honey Crystal Shards**



-Obtained by mining or breaking Honey Crystal blocks.

&nbsp;

-When eaten, this item restores 2 hunger.

&nbsp;

-Can be smelted to create Sticky Honey Residue block.

&nbsp;

-Can be used to craft and repair Honey Crystal Shields! The recipe to craft the shield is the following with # being Honey Crystal Shards:  
`# # #`

`# # #`

`  #  `

&nbsp;



**Honey Crystal Shield**



-A shield that starts off super weak but becomes much stronger the more you repair it to the point it has about double the durability of vanilla's Shield! But beware, trying to block fire damage and explosive damage with the shield will destroy the shield very quickly! Yes, Fireballs and Fireworks will also greatly damage the shield so be careful!

&nbsp;

-The shield can be repaired with Honey Crystal Shards and the recipe to craft the shield is the following with # being Honey Crystal Shards:  
`# # #`

`# # #`

`  #  `

&nbsp;

-The valid items for repairing the shield can be changed with a datapack overriding the "the_bumblezone:honey_crystal_shield_repair_items" item tag.

&nbsp;

-Any mob that physically hits your shield when you're blocking will apply a 4 second slowness effect on the mob due to the honey's stickiness!

&nbsp;

-This shield can be enchanted with Unbreaking or Curse of the Vanishing. (Mending cannot be applied as that would make the shield way too overpowered at maximum durability)

&nbsp;

-Can be smelted in a Furnace to make 5 Sticky Honey Residue.

</details>

&nbsp;


### MOBS - 1 new mob

<details>

**Honey Slime**



-Donated to The Bumblezone from the folks who made Buzzier Bees! Their texture and code has been ported to this mod. Thank you Bagel!

&nbsp;

-Honey Slimes will spawn throughout The Bumblezone and sometimes from Honeycome Brood Blocks.

&nbsp;

-They are passive mobs until they are hit. Then they will chase you for a bit!

&nbsp;

-Using a Glass Bottle on Honey Slime will give you a Honey Bottle but make the Honey Slime angry! They will regain their honey state over time but can be sped up by having them move over Honey, Filled Porous Honeycomb, Sticky Honey Residue, Sticky Honey Redstone, or Honeycomb Brood blocks.

&nbsp;

-When covered in honey, they can fall from higher heights and take no or reduced damage!

&nbsp;

-They can be lured with Sugar and fed it to breed with other Honey Slime mobs.

&nbsp;

-Using a Honey Block on vanilla's Slime mob that are small or medium sized will turn them into passive Honey Slimes! Making the Honey Slime available outside the Bumblezone dimension!

</details>

&nbsp;


### Enchantments - 1 new enchantment

<details>


**Comb Cutter**



-Can be applied to Swords and Shears

&nbsp;

-Only 1 level but will make the tool mine all comb based blocks super fast! It will also mine wax, hive, and nest blocks slightly faster.

&nbsp;

-Can be used to double the Honeycomb output from Beehives and Bee Nests.

</details>

&nbsp;





### BEE'S BEHAVIOR - NOT THE BEEEEESSSS!!!

<details>

-Any arthropod mob without "bee" in the name, any pandas, and any bears will get a weak version of Wrath of the Hive and nearby Bees will attack them in The Bumblezone dimension.

&nbsp;

-Any mob or player damaging a bee in The Bumblezone dimension will get Wrath of the Hive effect and a bunch of angry bees coming after them.

&nbsp;

-Mining Honey Blocks, taking honey from a Filled Porous Honeycomb Block, or taking honey from a Honeycomb Brood Block will give you Wrath of the Hive effect when in the dimension.

&nbsp;

-Having Wrath of the Hive will turn the dimension's fog red, play Flight of the Bumblebee by Rimsky Korsakov, make nearby Honeycomb Brood Blocks grow and spawn bees much faster, and the dimension will spawn even more Bees a bit away from you to hunt you down! Grab your Bane of Arthropod sword!

&nbsp;

-Feeding angry bees a Sugar Water Bottle has a 7% of removing the Wrath of the Hive effect, heals the bee a bit, and calms nearby bees. Feeding the bee a Honey Bottle instead has a 30% chance of removing the effect and greatly heals the fed bee.

&nbsp;

-When feeding a Bee, you have a chance of getting Protection of the Hive effect where nearby Bees will attack anyone who attacked you!

</details>

&nbsp;



### DIMENSION - It's like the insides of a Bee Hive!

<details>

-Contains only 3 biomes: Hive Wall Biome, Hive Pillar biome, and Sugar Water Floor Biome. Hive Wall Biome creates the flat walls of the dimension. Hive Pillar biome creates the large support columns that upholds the space. Sugar Water Floor takes up the rest of the space with low land and filled with Sugar Water.

&nbsp;

-Spawns Bees at a high rate and Slime mobs too. Will also spawn Spiders, Cave Spiders, Endermen, and Phantoms at a very low rate (with Phantoms be rarest).

&nbsp;

-Walls in the dimension has Honeycomb Holes filled with Honey Blocks and has a few Honeycomb Brood Blocks (some will be empty). There is also a cave system inside the walls/pillars that are only exposed through the space that Honeycomb Holes creates.

&nbsp;

-Bee Dungeons can be found in the caves where they are filled with Honey Crystal Blocks and lit Candles! It also has lots of Honeycomb brood Blocks.

&nbsp;

-Spider Infested Bee Dungeons can be rarely found in the caves where they are filled with Honey Crystal Blocks, unlit Candles, Empty Honeycomb Brood Blocks, Cobwebs, Spider Spawner, and Cave Spider Spawners. These dungeons can make a great XP, Spider Eyes, and String farm!

&nbsp;

-Honey Crystal Blocks will be scattered throughout the dimension either underwater or inside the caves. They are most common in Hive Wall biome and lest common in Sugar Water biome.

</details>

&nbsp;



### MUSIC - 2 new songs

<details>

**Honey Bee by Rat Faced Boy (also goes by AcidBurp and Gerald Tenderfly)**

&nbsp;

-The music will play occasionally in the dimension. It's pretty cool! You can get a Music Disc of the song from Wandering Traders to play in Jukeboxes! But if you want to download the song, be sure to buy it from BandCamp here to support the musician! [https://acidburp.bandcamp.com/track/honey-bee](https://acidburp.bandcamp.com/track/honey-bee)

&nbsp;

&nbsp;

**Flight of the Bumblebee by Rimsky Korsakov**

&nbsp;

-When you get Wrath of the Hive, a midi version of this song will play! I gotten the public domain sheet music for it, ran it through Anvil Studio, and produced the song that you can hear when you are in DEEP trouble! A Music Disc for this song can also be obtained from Wandering Traders too.

</details>

&nbsp;



### MOD COMPATIBILITY - Interacts directly with 3 other mods!

<details>

#### 1.17.0 MC

<details>

**Charm** - [https://www.curseforge.com/minecraft/mc-mods/charm](https://www.curseforge.com/minecraft/mc-mods/charm)


-Many Bumblezone items are added to Charm's BeeKeeper villager too!

&nbsp;

</details>

#### 1.16.5 MC

<details>

**Potion of Bees** - [https://www.curseforge.com/minecraft/mc-mods/potion-of-bees-fabric](https://www.curseforge.com/minecraft/mc-mods/potion-of-bees-fabric)



-Their Potion of Bees item can be used to revive a Empty Honeycomb Brood Block into a stage 1, 2, or 3 Honeycomb Brood Block by right clicking or using a Dispenser!

&nbsp;

-Their Splash Potion of Bees item can be thrown onto a Empty Honeycomb Brood Block to revive the block into a stage 1, 2, or 3 Honeycomb Brood Block! If you have the splash potion in a Dispenser facing the block, it'll revive the block without bees going everywhere.

&nbsp;



**Charm** - [https://www.curseforge.com/minecraft/mc-mods/charm](https://www.curseforge.com/minecraft/mc-mods/charm)



-Bee Dungeons and Spider Infested Bee Dungeons will have Charm candles!

&nbsp;

-Many Bumblezone items are added to Charm's BeeKeeper villager too!

&nbsp;



**Bee Better** - [https://www.curseforge.com/minecraft/mc-mods/bee-better](https://www.curseforge.com/minecraft/mc-mods/bee-better)



-Their Beeswax Block will spawn in patches around sealevel and below in The Bumblezone!

&nbsp;

-Both Bee Dungeons and Spider Infested Bee Dungeons may have different colored candles. A great way to collect all the colors!

&nbsp;

-Bee Dungeons and Spider Infested Bee Dungeons will now spawn certain building blocks from Bee Better to help make them look less monotone.

</details>

</details>

&nbsp;



**Also, be sure to check out the images page for several screenshots of what the world looks like!**

Small video I made about some main parts of my mod! https://www.youtube.com/watch?v=440rpJavHwY

&nbsp;

**____________________________________________________________________________**

## HOW DO I ENTER THIS WORLD?

Entering the dimension is super easy! Just throw an Enderpearl at a Bee Nest to enter inside it and spawn into the dimension. To teleport out of the dimension, you can either dig down and fall below Y = 0 (don't worry, you won't die as I made sure of that) or climb your way to above Y = 256\. The mod will see you are trying to leave and it'll take your coordinates in The Bumblezone, convert it to the correct coordinate in the original dimension you came from and will try to place you in that dimension near a Bee Nest (or it'll create a new Bee Nest to place you next to if you does not find a Bee Nest already). Through normal use, you cannot use the mod to jump between dimensions but you can use it to travel long distances in any dimension you want. Just be sure to enter The Bumblezone from the dimension you want to fast travel in and then exiting The Bumblezone will put you back into that dimension.

![Picture showing how to enter The Bumblezone by a player throwing an Enderpearl at a Bee Nest](https://i.imgur.com/52ThBt7.jpg)

&nbsp;

**____________________________________________________________________________**

## HOW CAN I CONFIGURE THIS MOD?

To reach this mod's configs you'll find most of the configs in the config screen in-game! Just put on Mod Menu and find my mod in there and edit away! When done, restart the game to make the config changes take effect! The other configs for this mod are in JSON so you just need to make a datapack to override those settings. In fact, some block tags used in this mod can be used to blacklist what hive block can teleport you to the dimension or if hive blocks need a certain block under them to allow teleportation! Here's the files the datapack can override: https://github.com/TelepathicGrunt/Bumblezone-Fabric/tree/master/src/main/resources/data/the_bumblezone

&nbsp;

There are several config options currently for the mod. Some affects the dimension and lets you control how bright the fog is (in-game config), what the movement factor of the dimension is (JSON config), and whether exiting the dimension will always place you back at Overworld instead of previous dimension you came from (in-game config). Many other configs lets you customize the Wrath of the Hive effect so you can change how long it lasts, how far bees has to be to be angered and boosted, how strong the effects given to bees are, whether Wrath of the Hive can be triggered outside The Bumblezone, show particles of the effect, or just turn off the effect completely. Lots of options! Take a look at the in-game config and JSON files and let me know if there's any config option that would be super helpful for you to have with this mod. :)

&nbsp;

**______________________________________________________________________________**

For the list of changes in this mod, click the Source tab and check out README.txt file. This helps me keep track of what I had done so far.

&nbsp;

Special thanks to Bagel (dev of Buzzier Bees) for allowing us to use their Honey Slime mob code and textures!

&nbsp;

**If you find an issue, glitch, or have a suggestion about my mod, let me know! But if you don't have a GitHub account to report in the Issue tab above, just comment what the problem is below and I'll try and get back to you ASAP! :)**

&nbsp;

**Discord Link to #telepathicgrunt-mods channel for my mods! :**

**[https://discord.gg/SM7WBT6FGu](https://discord.gg/SM7WBT6FGu "https://discord.gg/SM7WBT6FGu")**

<a class="anchor-3Z-8Bb anchorUnderlineOnHover-2ESHQB" style="font-size: 24px;" tabindex="0" title="https://discord.gg/SM7WBT6FGu" role="button" href="https://discord.gg/SM7WBT6FGu" target="_blank" rel="noopener noreferrer"><img src="https://www.freepnglogos.com/uploads/discord-logo-png/concours-discord-cartes-voeux-fortnite-france-6.png" alt="discord-logo-png-free-transparent-png-logos-discord-png-logo-300_300 (PNG)  | BeeIMG" width="112" height="112" /></a>


