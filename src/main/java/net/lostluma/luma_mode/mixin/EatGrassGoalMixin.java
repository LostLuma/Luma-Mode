package net.lostluma.luma_mode.mixin;

import net.minecraft.entity.ai.goal.EatGrassGoal;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(EatGrassGoal.class)
public class EatGrassGoalMixin {
	/**
	 * Stop sheep from eating and thus destroying tall grass.
	 * They will instead automatically choose to eat the regular grass block below, which can regrow.
	 */
	@Redirect(
		method = { "canStart", "tick" },
		at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getBlock(III)I", ordinal = 0)
	)
	private int getBlock(World world, int x, int y, int z) {
		return 0;
	}
}
