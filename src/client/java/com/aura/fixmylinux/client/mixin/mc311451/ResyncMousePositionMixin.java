package com.aura.fixmylinux.client.mixin.mc311451;

import com.aura.fixmylinux.client.mc311451.NegativeMonitorMouseFix;
import java.nio.IntBuffer;
import net.minecraft.client.MouseHandler;
import org.lwjgl.sdl.SDLVideo;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

// force the existing window relative fallback on wayland driver instead.
@Mixin(MouseHandler.class)
public class ResyncMousePositionMixin {
    @Redirect(
        method = "resyncMousePosition",
        at = @At(
            value = "INVOKE",
            target = "Lorg/lwjgl/sdl/SDLVideo;SDL_GetWindowPosition(JLjava/nio/IntBuffer;Ljava/nio/IntBuffer;)Z"
        )
    )
    private boolean fixmylinux$preferWindowRelativeOnWayland(long windowHandle, IntBuffer x, IntBuffer y) {
        return NegativeMonitorMouseFix.redirectGetWindowPosition(
            windowHandle, x, y,
            SDLVideo::SDL_GetWindowPosition,
            SDLVideo.SDL_GetCurrentVideoDriver()
        );
    }
}
