package net.lostluma.luma_mode.mixin;

import net.minecraft.block.BedBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BedBlock.class)
public class BedBlockMixin {
	/**
	 * Disallow sleeping.
	 */
	@Inject(method = "use", at = @At("HEAD"), cancellable = true)
	private void use(CallbackInfoReturnable<Boolean> callbackInfo) {
		callbackInfo.setReturnValue(false);
	}
}
