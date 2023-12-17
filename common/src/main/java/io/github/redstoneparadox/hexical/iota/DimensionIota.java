package io.github.redstoneparadox.hexical.iota;

import at.petrak.hexcasting.api.spell.iota.Iota;
import at.petrak.hexcasting.api.spell.iota.IotaType;
import at.petrak.hexcasting.api.spell.mishaps.MishapInvalidIota;
import at.petrak.hexcasting.api.utils.HexUtils;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.dimension.DimensionType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class DimensionIota extends Iota {
	public static IotaType<DimensionIota> TYPE = new IotaType<>() {
		@Override
		public @NotNull DimensionIota deserialize(NbtElement tag, ServerWorld world) throws IllegalArgumentException {
			var compound = HexUtils.downcast(tag, NbtCompound.TYPE);
			var id = new Identifier(compound.getString("dimension"));
			var dimensionType = BuiltinRegistries.DIMENSION_TYPE.get(id);

			if (dimensionType == null) throw new IllegalArgumentException("Dimension type was null");

			return new DimensionIota(dimensionType);
		}

		@Override
		public Text display(NbtElement tag) {
			var compound = HexUtils.downcast(tag, NbtCompound.TYPE);
			var id = new Identifier(compound.getString("dimension"));

			if (!BuiltinRegistries.DIMENSION_TYPE.containsId(id)) return Text.translatable("hexcasting.spelldata.unknown");

			var out = Text.empty();
			out.append(id.toString());

			return out;
		}

		@Override
		public int color() {
			return 0xff_53daf6;
		}
	};
	public DimensionIota(@NotNull Object payload) {
		super(TYPE, payload);
	}

	@Override
	public boolean isTruthy() {
		return false;
	}

	@Override
	protected boolean toleratesOther(Iota that) {
		if (!this.type.equals(that.getType()) || !(that instanceof DimensionIota)) {
			return false;
		}

		return this.getDimensionType() == ((DimensionIota) that).getDimensionType();
	}

	@Override
	public @NotNull NbtElement serialize() {
		var tag = new NbtCompound();
		var dimensionType = getDimensionType();
		var id = BuiltinRegistries.DIMENSION_TYPE.getId(dimensionType);

		assert id != null;
		tag.putString("dimension", id.toString());

		return tag;
	}

	public DimensionType getDimensionType() {
		return (DimensionType) payload;
	}
}
