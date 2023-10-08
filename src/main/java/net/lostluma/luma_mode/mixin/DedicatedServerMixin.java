package net.lostluma.luma_mode.mixin;

import net.minecraft.server.dedicated.DedicatedServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DedicatedServer.class)
public class DedicatedServerMixin {
	/**
	 * Force difficulty to hard.
	 */
	@Inject(method = "getDefaultDifficulty", at = @At("HEAD"), cancellable = true)
	private void getDefaultDifficulty(CallbackInfoReturnable<Integer> callbackInfo) {
		callbackInfo.setReturnValue(3);
	}
}
