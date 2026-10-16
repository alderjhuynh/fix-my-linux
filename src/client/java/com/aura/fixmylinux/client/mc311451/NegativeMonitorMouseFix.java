package com.aura.fixmylinux.client.mc311451;

import java.nio.IntBuffer;

// on wayland video driver, skip global position math and use window relative position
public final class NegativeMonitorMouseFix {
    // video driver name reported by GetCurrentVideoDriver
    public static final String WAYLAND_DRIVER = "wayland";

    private NegativeMonitorMouseFix() {
    }

    public static boolean shouldUseWindowRelativeCoords(String videoDriver) {
        return WAYLAND_DRIVER.equalsIgnoreCase(videoDriver);
    }

    public static boolean redirectGetWindowPosition(long windowHandle, IntBuffer x, IntBuffer y,
            GetWindowPosition original, String videoDriver) {
        if (shouldUseWindowRelativeCoords(videoDriver)) {
            return false;
        }
        return original.get(windowHandle, x, y);
    }

    public interface GetWindowPosition {
        boolean get(long windowHandle, IntBuffer x, IntBuffer y);
    }
}
