# Fix My Linux!

I recently switched to running Linux, and have since experienced a couple of bugs that I didn't before on MacOS. Fix My Linux! aims to, well, fix your Linux experience on Minecraft. 

## Current bugs fixed:

[MC-311949: The "Not Bound" scancode can activate key binds](https://bugs.mojang.com/browse/MC/issues/MC-311949)

I ended up fixing this by setting unbound keybinds to the scancode -1 (which isn't emitted by anything), and then custom rendering it as "Not Bound" in the keybinding menu. 

[MC-311424: The Right Shift key is recognized as `key.keyboard.unknown` with certain IMEs](https://bugs.mojang.com/browse/MC/issues/MC-311424)

With certain input methods a Right Shift press gives the scancode 0 even though the SDL keycode still identifies it as Right Shift. In that case, it now falls back to Right Shift.

[MC-311451: Mouse input is not recognized on monitors that are in negative positions on Wayland](https://bugs.mojang.com/browse/MC/issues/MC-311451)

in Vanilla, MouseHandler recomputed the cursor from the global mouse position minus SDL's GetWindowPosition, but Wayland never reports a window position. On the Wayland driver the global-position path is now skipped in favor of the existing window-relative fallback.

[MC-266908: Set Wayland APP_ID for showing icon (Linux)](https://bugs.mojang.com/browse/MC/issues/MC-266908)

Vanilla never sets an app_id, so Wayland compositors show a generic icon and can't group the window with its icon. The SDL_APP_ID hint is now set to minecraft before the window is created.