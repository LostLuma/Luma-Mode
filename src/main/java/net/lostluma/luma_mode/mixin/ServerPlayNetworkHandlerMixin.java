package net.lostluma.luma_mode.mixin;

import net.minecraft.server.network.handler.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {
	/**
	 * Set the effective spawn protection distance to 0, removing the OP requirement.
	 */
	@ModifyConstant(method = { "handlePlayerHandAction", "handlePlayerUse" }, constant = @Constant(intValue = 16))
	private int spawnProtectionRange(int value) {
		return 0;
	}
}
