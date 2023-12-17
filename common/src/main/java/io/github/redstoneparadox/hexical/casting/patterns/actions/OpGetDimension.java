package io.github.redstoneparadox.hexical.casting.patterns.actions;

import at.petrak.hexcasting.api.spell.Action;
import at.petrak.hexcasting.api.spell.ConstMediaAction;
import at.petrak.hexcasting.api.spell.OperationResult;
import at.petrak.hexcasting.api.spell.casting.CastingContext;
import at.petrak.hexcasting.api.spell.casting.eval.SpellContinuation;
import at.petrak.hexcasting.api.spell.iota.Iota;
import io.github.redstoneparadox.hexical.iota.DimensionIota;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class OpGetDimension implements ConstMediaAction {
	@Override
	public int getArgc() {
		return 0;
	}

	@Override
	public int getMediaCost() {
		return 1;
	}

	@NotNull
	@Override
	public List<Iota> execute(@NotNull List<? extends Iota> list, @NotNull CastingContext castingContext) {
		var caster = castingContext.getCaster();
		var world = caster.world;
		var dimension = world.getDimension();
		var iota = new DimensionIota(dimension);

		return Collections.singletonList(iota);
	}

	@Override
	public boolean getAlwaysProcessGreatSpell() {
		return false;
	}

	@Override
	public boolean getCausesBlindDiversion() {
		return false;
	}

	@NotNull
	@Override
	public Text getDisplayName() {
		return Action.DefaultImpls.getDisplayName(this);
	}

	@Override
	public boolean isGreat() {
		return false;
	}

	@NotNull
	@Override
	public OperationResult operate(@NotNull SpellContinuation spellContinuation, @NotNull List<Iota> list, @Nullable Iota iota, @NotNull CastingContext castingContext) {
		return ConstMediaAction.DefaultImpls.operate(this, spellContinuation, list, iota, castingContext);
	}
}
