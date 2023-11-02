package net.lostluma.luma_mode.mixin;

import net.minecraft.entity.living.mob.passive.animal.SheepEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(SheepEntity.class)
public class SheepEntityMixin {
	/**
	 * Stop sheep from eating and thus destroying tall grass.
	 * They will instead automatically choose to eat the regular grass block below, which can regrow.
	 */
	@Redirect(
		method = { "tickDespawn" },
		at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;getBlock(III)I", ordinal = 1)
	)
	private int getBlock(World world, int x, int y, int z) {
		return 0;
	}
}
