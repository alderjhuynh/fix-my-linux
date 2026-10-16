package com.aura.fixmylinux.client.mixin.mc266908;

import com.aura.fixmylinux.client.mc266908.WaylandAppIdFix;
import com.mojang.blaze3d.platform.Window;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// set appid hint before window is created so icon is resolved
@Mixin(Window.class)
public class WindowAppIdMixin {
    @Inject(method = "createWindow", at = @At("HEAD"))
    private void fixmylinux$setWaylandAppId(CallbackInfoReturnable<Long> cir) {
        WaylandAppIdFix.apply();
    }
}
