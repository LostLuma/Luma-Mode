package net.lostluma.luma_mode.mixin;

import net.minecraft.entity.ai.goal.UntamedActiveTargetGoal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(UntamedActiveTargetGoal.class)
public class UntamedActiveTargetGoalMixin {
	/**
	 * Stop untamed wolves and ocelots from attacking other mobs.
	 * This will need to be reevaluated once wolves attack skeletons on their own (Minecraft 1.8+).
	 */
	@Inject(method = "canStart", at = @At("HEAD"), cancellable = true)
	private void canStart(CallbackInfoReturnable<Boolean> callbackInfo) {
		callbackInfo.setReturnValue(false);
	}
}
