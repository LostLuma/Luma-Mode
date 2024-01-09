package net.lostluma.luma_mode.mixin.debug;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.living.LivingEntity;
import net.minecraft.entity.living.mob.passive.PassiveEntity;
import net.minecraft.entity.living.player.PlayerEntity;
import net.minecraft.server.MinecraftServer;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
	/**
	 * Logs whenever a passive entity dies from a non-player source.
	 *
	 * Animals have sometimes been disappearing in my world and I'd like to find out why!
	 */
	@Inject(method = "onKilled", at = @At("HEAD"))
	private void onKilled(DamageSource damageSource, CallbackInfo callbackInfo) {
		var instance = (LivingEntity)(Object)this;

		if (!(instance instanceof PassiveEntity)) {
			return;
		}

		if (damageSource.getAttacker() instanceof PlayerEntity) {
			return;
		}

		var x = Integer.toString((int)instance.x);
		var y = Integer.toString((int)instance.y);
		var z = Integer.toString((int)instance.z);

		MinecraftServer.getInstance().getLogger().info(
			instance.getName() + " died due to " + damageSource.name + " at " + String.join(" ", x, y, z)
		);
	}
}
