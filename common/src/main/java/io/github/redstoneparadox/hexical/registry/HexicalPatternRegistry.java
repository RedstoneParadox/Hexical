package io.github.redstoneparadox.hexical.registry;

import at.petrak.hexcasting.api.PatternRegistry;
import at.petrak.hexcasting.api.spell.Action;
import at.petrak.hexcasting.api.spell.math.HexDir;
import at.petrak.hexcasting.api.spell.math.HexPattern;
import io.github.redstoneparadox.hexical.casting.patterns.actions.OpGetDimension;
import kotlin.Triple;
import io.github.redstoneparadox.hexical.casting.patterns.math.OpSignum;
import io.github.redstoneparadox.hexical.casting.patterns.spells.OpCongrats;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

import static io.github.redstoneparadox.hexical.Hexical.id;

public class HexicalPatternRegistry {
    public static List<Triple<HexPattern, Identifier, Action>> PATTERNS = new ArrayList<>();
    public static List<Triple<HexPattern, Identifier, Action>> PER_WORLD_PATTERNS = new ArrayList<>();
    // IMPORTANT: be careful to keep the registration calls looking like this, or you'll have to edit the hexdoc pattern regex.
    public static HexPattern CONGRATS = registerPerWorld(HexPattern.fromAngles("eed", HexDir.WEST), "congrats", new OpCongrats());
    public static HexPattern SIGNUM = register(HexPattern.fromAngles("edd", HexDir.NORTH_WEST), "signum", new OpSignum());
    public static HexPattern PLANESWALKERS_REFLECTION = register(HexPattern.fromAngles("wwaawdwa", HexDir.EAST), "planeswalkers_reflection", new OpGetDimension());

    public static void init() {
        try {
            for (Triple<HexPattern, Identifier, Action> patternTriple : PATTERNS) {
                PatternRegistry.mapPattern(patternTriple.getFirst(), patternTriple.getSecond(), patternTriple.getThird());
            }
            for (Triple<HexPattern, Identifier, Action> patternTriple : PER_WORLD_PATTERNS) {
                PatternRegistry.mapPattern(patternTriple.getFirst(), patternTriple.getSecond(), patternTriple.getThird(), true);
            }
        } catch (PatternRegistry.RegisterPatternException e) {
            e.printStackTrace();
        }
    }

    private static HexPattern register(HexPattern pattern, String name, Action action) {
        Triple<HexPattern, Identifier, Action> triple = new Triple<>(pattern, id(name), action);
        PATTERNS.add(triple);
        return pattern;
    }

    private static HexPattern registerPerWorld(HexPattern pattern, String name, Action action) {
        Triple<HexPattern, Identifier, Action> triple = new Triple<>(pattern, id(name), action);
        PER_WORLD_PATTERNS.add(triple);
        return pattern;
    }
}
