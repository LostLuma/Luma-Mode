package net.lostluma.luma_mode.mixin;

import net.minecraft.block.NetherWartBlock;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import java.util.Random;

@Mixin(NetherWartBlock.class)
public class NetherWartBlockMixin {
	/**
	 * Makes Nether Wart grow at a third of its regular speed in non-Nether dimensions.
	 * This allows it to be used as decoration, but farms are encouraged to be in the Nether.
	 */
	@ModifyConstant(method = "tick", constant = @Constant(intValue = 10))
	private int growthChance(int previous, World world, int x, int y, int z, Random random) {
		return world.dimension.id == -1 ? previous : previous * 3;
	}
}
