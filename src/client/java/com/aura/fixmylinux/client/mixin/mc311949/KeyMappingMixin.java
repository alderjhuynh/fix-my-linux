package com.aura.fixmylinux.client.mixin.mc311949;

import com.aura.fixmylinux.client.mc311949.UnboundKeys;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// stops unbound keys from being bound to key.keyboard.unknown
@Mixin(KeyMapping.class)
public abstract class KeyMappingMixin {
    @Shadow
    protected InputConstants.Key key;

    @ModifyVariable(method = "setKey", at = @At("HEAD"), argsOnly = true)
    private InputConstants.Key fixmylinux$unboundUsesNone(InputConstants.Key key) {
        if (key != null && InputConstants.UNKNOWN.equals(key)) {
            return UnboundKeys.none();
        }
        return key;
    }

    @Inject(method = "isUnbound", at = @At("HEAD"), cancellable = true)
    private void fixmylinux$treatNoneAsUnbound(CallbackInfoReturnable<Boolean> cir) {
        if (UnboundKeys.isNone(this.key)) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "shouldSetOnIngameFocus", at = @At("HEAD"), cancellable = true)
    private void fixmylinux$skipNoneOnIngameFocus(CallbackInfoReturnable<Boolean> cir) {
        if (UnboundKeys.isNone(this.key)) {
            cir.setReturnValue(false);
        }
    }
}
