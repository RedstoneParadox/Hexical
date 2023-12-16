package io.github.redstoneparadox.hexical.fabric;

import net.fabricmc.loader.api.FabricLoader;
import io.github.redstoneparadox.hexical.HexicalAbstractions;

import java.nio.file.Path;

public class HexicalAbstractionsImpl {
    /**
     * This is the actual implementation of {@link HexicalAbstractions#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FabricLoader.getInstance().getConfigDir();
    }
}
