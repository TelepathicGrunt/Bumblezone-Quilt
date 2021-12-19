package com.telepathicgrunt.the_bumblezone.mixin.items;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class HoneyShieldDisableMixin {

    @Inject(method = "disableShield",
            at = @At(value = "HEAD"))
    private void thebumblezone_isHoneyCrystalShield(boolean sprinting, CallbackInfo ci) {
        ((Player)(Object)this).getCooldowns().addCooldown(Items.SHIELD, 100);
    }
}