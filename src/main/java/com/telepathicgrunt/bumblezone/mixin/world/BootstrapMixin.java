package com.telepathicgrunt.bumblezone.mixin.world;

import com.telepathicgrunt.bumblezone.Bumblezone;
import net.minecraft.Bootstrap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(Bootstrap.class)
public class BootstrapMixin {
    //TODO: Remove this mixin when fabric fixes biome shuffling bug
    @Inject(
            method = "initialize",
            at = @At(value = "TAIL")
    )
    private static void earlyRegister(CallbackInfo ci) {
        Bumblezone.reserveBiomeIDs();
    }
}