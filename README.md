# Fix My Linux!

I recently switched to running Linux, and have since experienced a couple of bugs that I didn't before on MacOS. Fix My Linux! aims to, well, fix your Linux experience on Minecraft. 

Current bugs fixed:

[MC-311949: The "Not Bound" scancode can activate key binds](https://bugs.mojang.com/browse/MC/issues/MC-311949)

I ended up fixing this by setting unbound keybinds to the scancode -1 (which isn't emitted by anything), and then custom rendering it as "Not Bound" in the keybinding menu. 