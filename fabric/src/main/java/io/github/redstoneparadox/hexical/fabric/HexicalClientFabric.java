package io.github.redstoneparadox.hexical.fabric;

import net.fabricmc.api.ClientModInitializer;
import io.github.redstoneparadox.hexical.HexicalClient;

/**
 * Fabric client loading entrypoint.
 */
public class HexicalClientFabric implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HexicalClient.init();
    }
}
