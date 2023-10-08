package net.lostluma.luma_mode.mixin;

import net.minecraft.entity.living.mob.hostile.EndermanEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EndermanEntity.class)
public class EndermanEntityMixin {
	@Shadow
	private static boolean[] blocksToPickup;

	/**
	 * Clear the list of blocks Endermen can pick up.
	 */
	@Inject(method = "<clinit>", at = @At("TAIL"))
	private static void clinit(CallbackInfo callbackInfo) {
		blocksToPickup = new boolean[256];
	}
}
