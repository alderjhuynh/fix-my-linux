package com.aura.fixmylinux.client.mixin.mc311949;

import com.aura.fixmylinux.client.mc311949.UnboundKeys;
import com.mojang.blaze3d.platform.InputConstants;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


// renders the -1 keycode on the keyboard type
@Mixin(InputConstants.Type.class)
public abstract class InputTypeMixin {
    @Shadow
    private static void addKey(InputConstants.Type type, String name, int value) {
    }

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void fixmylinux$registerNoneKey(CallbackInfo ci) {
        addKey(InputConstants.Type.KEYBOARD, UnboundKeys.NONE_NAME, UnboundKeys.NONE_VALUE);
    }
}
