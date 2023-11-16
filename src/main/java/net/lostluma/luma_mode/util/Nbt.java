package net.lostluma.luma_mode.util;

import net.minecraft.nbt.NbtDouble;
import net.minecraft.nbt.NbtList;

import java.util.ArrayList;
import java.util.List;

public class Nbt {
	public static NbtList asNbtList(double... values) {
		NbtList result = new NbtList();

		for(double var6 : values) {
			result.add(new NbtDouble(null, var6));
		}

		return result;
	}

	public static List<Double> fromNbtList(NbtList value) {
		List<Double> result = new ArrayList<>();

		for (var x = 0; x < value.size(); x++) {
			result.add(((NbtDouble) value.get(x)).value);
		}

		return result;
	}
}
