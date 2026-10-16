package com.aura.fixmylinux.client.mixin.mc311949;

import com.aura.fixmylinux.client.mc311949.UnboundKeys;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// renders the -1 keycode as "Not Bound"
@Mixin(InputConstants.Key.class)
public abstract class KeyDisplayMixin {
    // forces minecraft to not use the sdl lookup
    @Inject(method = "getDisplayName", at = @At("HEAD"), cancellable = true)
    private void fixmylinux$displayNoneAsNotBound(CallbackInfoReturnable<Component> cir) {
        InputConstants.Key self = (InputConstants.Key) (Object) this;
        if (UnboundKeys.isNone(self)) {
            cir.setReturnValue(Component.translatable(UnboundKeys.NONE_NAME));
        }
    }
}
