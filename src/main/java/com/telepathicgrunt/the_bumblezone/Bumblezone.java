package com.telepathicgrunt.the_bumblezone;

import com.telepathicgrunt.the_bumblezone.components.EntityComponent;
import com.telepathicgrunt.the_bumblezone.components.FlyingSpeedComponent;
import com.telepathicgrunt.the_bumblezone.components.NeurotoxinsMissedCounterComponent;
import com.telepathicgrunt.the_bumblezone.configs.BzConfig;
import com.telepathicgrunt.the_bumblezone.entities.BeeAggression;
import com.telepathicgrunt.the_bumblezone.entities.WanderingTrades;
import com.telepathicgrunt.the_bumblezone.items.dispenserbehavior.DispenserItemSetup;
import com.telepathicgrunt.the_bumblezone.modcompat.ModChecker;
import com.telepathicgrunt.the_bumblezone.modinit.*;
import com.telepathicgrunt.the_bumblezone.packets.BeehemothControlsPacket;
import com.telepathicgrunt.the_bumblezone.packets.BumbleBeeChestplateFlyingPacket;
import com.telepathicgrunt.the_bumblezone.packets.StinglessBeeHelmetSightPacket;
import com.telepathicgrunt.the_bumblezone.world.dimension.BzDimension;
import com.telepathicgrunt.the_bumblezone.world.dimension.BzWorldSavedData;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.lifecycle.api.event.ServerWorldTickEvents;

public class Bumblezone implements ModInitializer, EntityComponentInitializer {

    public static final String MODID = "the_bumblezone";
    public static final ResourceLocation MOD_DIMENSION_ID = new ResourceLocation(Bumblezone.MODID, Bumblezone.MODID);

    public static BzConfig BZ_CONFIG;
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public static final ComponentKey<EntityComponent> ENTITY_COMPONENT = ComponentRegistry.getOrCreate(new ResourceLocation(MODID, "entity_component"), EntityComponent.class);
    public static final ComponentKey<FlyingSpeedComponent> FLYING_SPEED_COMPONENT = ComponentRegistry.getOrCreate(new ResourceLocation(MODID, "original_flying_speed"), FlyingSpeedComponent.class);
    public static final ComponentKey<NeurotoxinsMissedCounterComponent> NEUROTOXINS_MISSED_COUNTER_COMPONENT = ComponentRegistry.getOrCreate(new ResourceLocation(MODID, "neurotoxins_missed_counter"), NeurotoxinsMissedCounterComponent.class);

    @Override
    public void onInitialize(ModContainer mod) {
        //Set up config
        AutoConfig.register(BzConfig.class, JanksonConfigSerializer::new);
        BZ_CONFIG = AutoConfig.getConfigHolder(BzConfig.class).getConfig();

        BzTags.initTags();
        BzBiomeHeightRegistry.initBiomeHeightRegistry();

        // Must be before items so that items like music disc can get sounds
        BzSounds.registerSounds();

        BzBlocks.registerBlocks();
        BzBlockEntities.registerBlockEntities();
        BzFluids.registerFluids();
        BzEntities.registerEntities();
        BzItems.registerItems();
        BzRecipes.registerRecipes();
        BzEffects.registerEffects();
        BzEnchantments.registerEnchantment();
        BzCriterias.registerCriteriaTriggers();
        BzPOI.registerPOIs();
        BzMenuTypes.registerMenu();
        BzParticles.registerParticles();
        BzStats.registerStats();

        BzPredicates.registerPredicates();
        BzLootFunctionTypes.registerContainerLootFunctions();
        BzProcessors.registerProcessors();
        BzPlacements.registerPlacements();
        BzFeatures.registerFeatures();
        BzSurfaceRules.registerSurfaceRules();
        BzStructures.registerStructures();
        BzDimension.setupDimension();

        WanderingTrades.addWanderingTrades();
        DispenserItemSetup.setupDispenserBehaviors();

        BeeAggression.setupEvents();
        ModChecker.setupModCompat();
        ServerWorldTickEvents.END.register(BzWorldSavedData::tick);

        BeehemothControlsPacket.registerPacket();
        BumbleBeeChestplateFlyingPacket.registerPacket();
        StinglessBeeHelmetSightPacket.registerPacket();
    }

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        //attach component to living entities
        registry.registerFor(LivingEntity.class, ENTITY_COMPONENT, p -> new EntityComponent());
        registry.registerFor(LivingEntity.class, FLYING_SPEED_COMPONENT, p -> new FlyingSpeedComponent());
        registry.registerFor(LivingEntity.class, NEUROTOXINS_MISSED_COUNTER_COMPONENT, p -> new NeurotoxinsMissedCounterComponent());
    }
}
