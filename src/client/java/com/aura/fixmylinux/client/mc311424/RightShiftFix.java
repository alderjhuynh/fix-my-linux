package com.aura.fixmylinux.client.mc311424;

// falls back to sdl keycode when the scancode is unknown
public final class RightShiftFix {
    public static final int SCANCODE_UNKNOWN = 0;

    public static final int SCANCODE_RSHIFT = 229;

    public static final int SDLK_RSHIFT = 1073742053;

    private RightShiftFix() {
    }

    // true when the scancode is unknown but the keycode is right shift
    public static boolean isUnknownRightShift(int scancode, int keycode) {
        return scancode == SCANCODE_UNKNOWN && keycode == SDLK_RSHIFT;
    }

    public static int resolveScancode(int scancode, int keycode) {
        return isUnknownRightShift(scancode, keycode) ? SCANCODE_RSHIFT : scancode;
    }
}
