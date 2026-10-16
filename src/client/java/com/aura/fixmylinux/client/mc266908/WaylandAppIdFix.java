package com.aura.fixmylinux.client.mc266908;

import org.lwjgl.sdl.SDLHints;
import org.lwjgl.sdl.SDLInit;

// sets the appid so wayland can actually display the icon
public final class WaylandAppIdFix {
    public static final String APP_ID = "minecraft";

    // SDL3 app metadata key vanilla sets to "com.mojang.minecraft" in RenderSystem.initBackendSystem
    public static final String APP_METADATA_IDENTIFIER = "SDL.app.metadata.identifier";

    private WaylandAppIdFix() {
    }

    public static String hintName() {
        return SDLHints.SDL_HINT_APP_ID;
    }

    public static void apply() {
        // SDL2 compat hint; harmless on SDL3 but not sufficient alone.
        SDLHints.SDL_SetHint(SDLHints.SDL_HINT_APP_ID, APP_ID);
        // SDL3 Wayland app_id comes from app metadata, which vanilla sets to
        // "com.mojang.minecraft". Override before the native window is created.
        SDLInit.SDL_SetAppMetadataProperty(APP_METADATA_IDENTIFIER, APP_ID);
    }
}
