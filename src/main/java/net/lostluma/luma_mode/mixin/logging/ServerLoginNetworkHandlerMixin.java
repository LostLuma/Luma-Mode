package net.lostluma.luma_mode.mixin.logging;

import net.minecraft.server.network.handler.ServerLoginNetworkHandler;
import net.minecraft.util.ILogger;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Arrays;

@Mixin(ServerLoginNetworkHandler.class)
public class ServerLoginNetworkHandlerMixin {
	private static final String[] IGNORED = {
		"Protocol error",
		"Took too long to log in"
	};

	/**
	 * Don't log disconnects of modern protocol clients.
	 */
	@Redirect(
		method = "disconnect",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/util/ILogger;info(Ljava/lang/String;)V")
	)
	private void disconnect(ILogger logger, String message) {
		if (Arrays.stream(IGNORED).noneMatch(message::endsWith)) {
			logger.info(message);
		}
	}

	/**
	 * Don't log disconnects when login sequence was never initiated.
	 */
	@Redirect(
		method = "onDisconnect",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/util/ILogger;info(Ljava/lang/String;)V")
	)
	private void onDisconnect(ILogger logger, String message) {
		// Message can be either of these:
		// /1.1.1.1 lost connection
		// LostLuma [/1.1.1.1] lost connection
		if (!message.startsWith("/")) {
			logger.info(message);
		}
	}
}
