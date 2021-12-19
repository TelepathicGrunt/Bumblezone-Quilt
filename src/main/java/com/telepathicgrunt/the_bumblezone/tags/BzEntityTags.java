package com.telepathicgrunt.the_bumblezone.tags;

import com.telepathicgrunt.the_bumblezone.Bumblezone;
import net.fabricmc.fabric.api.tag.TagFactory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.Tag;
import net.minecraft.world.entity.EntityType;

public class BzEntityTags {
    // All tag wrappers need to be made at mod init.
    public static void tagInit() {}

    public static final Tag<EntityType<?>> POLLEN_PUFF_CAN_POLLINATE = TagFactory.ENTITY_TYPE.create(new ResourceLocation(Bumblezone.MODID+":pollen_puff_can_pollinate"));
}