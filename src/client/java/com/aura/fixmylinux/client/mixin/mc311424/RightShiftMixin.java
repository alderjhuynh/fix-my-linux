package com.aura.fixmylinux.client.mixin.mc311424;

import com.aura.fixmylinux.client.mc311424.RightShiftFix;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.input.KeyEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// fall back to keycode (yes i said this before yes im gonna say it again)
@Mixin(InputConstants.class)
public class RightShiftMixin {
    @Inject(
        method = "getKey(Lnet/minecraft/client/input/KeyEvent;)Lcom/mojang/blaze3d/platform/InputConstants$Key;",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void fixmylinux$resolveUnknownRightShift(KeyEvent event, CallbackInfoReturnable<InputConstants.Key> cir) {
        int resolved = RightShiftFix.resolveScancode(event.key(), event.keycode());
        if (resolved != event.key()) {
            cir.setReturnValue(InputConstants.Type.KEYBOARD.getOrCreate(resolved));
        }
    }
}
