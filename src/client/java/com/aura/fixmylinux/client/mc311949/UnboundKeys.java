package com.aura.fixmylinux.client.mc311949;

import com.mojang.blaze3d.platform.InputConstants;

public final class UnboundKeys {
    public static final String NONE_NAME = "key.keyboard.none";
    public static final int NONE_VALUE = -1;

    private UnboundKeys() {
    }

    public static InputConstants.Key none() {
        return InputConstants.Type.KEYBOARD.getOrCreate(NONE_VALUE);
    }

    public static boolean isNone(InputConstants.Key key) {
        return key != null
                && key.getType() == InputConstants.Type.KEYBOARD
                && key.getValue() == NONE_VALUE;
    }
}
