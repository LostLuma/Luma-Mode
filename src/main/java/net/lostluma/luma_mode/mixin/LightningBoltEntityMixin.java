package net.lostluma.luma_mode.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.living.mob.passive.animal.AnimalEntity;
import net.minecraft.entity.weather.LightningBoltEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LightningBoltEntity.class)
public class LightningBoltEntityMixin {
	/**
	 * Pretend the chunk this lightning bolt is in is unloaded to disable fire spread.
	 */
	@Redirect(
		method = { "<init>", "tick" },
		at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;isAreaLoaded(IIII)Z" )
	)
	private boolean never(World world, int x, int y, int z, int range) {
		return false;
	}

	/**
	 * Prevents animals from being damaged by lightning, to preserve the world's fauna.
	 */
	@Redirect(
		method = "tick",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/entity/Entity;onLightningStrike(Lnet/minecraft/entity/weather/LightningBoltEntity;)V")
	)
	private void onLightningStrike(Entity entity, LightningBoltEntity lightning) {
		if (!(entity instanceof AnimalEntity)) {
			entity.onLightningStrike(lightning);
		}
	}
}
