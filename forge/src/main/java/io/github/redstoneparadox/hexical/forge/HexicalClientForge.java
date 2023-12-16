package io.github.redstoneparadox.hexical.forge;

import io.github.redstoneparadox.hexical.HexicalClient;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * Forge client loading entrypoint.
 */
public class HexicalClientForge {
    public static void init(FMLClientSetupEvent event) {
        HexicalClient.init();
    }
}
