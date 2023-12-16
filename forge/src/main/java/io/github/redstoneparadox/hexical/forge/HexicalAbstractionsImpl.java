package io.github.redstoneparadox.hexical.forge;

import io.github.redstoneparadox.hexical.HexicalAbstractions;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public class HexicalAbstractionsImpl {
    /**
     * This is the actual implementation of {@link HexicalAbstractions#getConfigDirectory()}.
     */
    public static Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }
}
