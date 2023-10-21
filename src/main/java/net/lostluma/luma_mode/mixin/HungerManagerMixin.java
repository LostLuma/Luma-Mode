package net.lostluma.luma_mode.mixin;

import net.minecraft.entity.living.player.PlayerEntity;
import net.minecraft.entity.player.HungerManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(HungerManager.class)
public class HungerManagerMixin {
	@Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/living/player/PlayerEntity;needsHealing()Z"))
	private boolean needsHealing(PlayerEntity player) {
		return false;
	}
}
