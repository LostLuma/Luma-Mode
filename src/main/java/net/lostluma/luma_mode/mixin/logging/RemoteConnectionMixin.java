package net.lostluma.luma_mode.mixin.logging;

import net.minecraft.network.RemoteConnection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Arrays;

@Mixin(RemoteConnection.class)
public class RemoteConnectionMixin {
	private static final String[] IGNORED = {
		"Bad packet id",
		"Received string length longer than maximum"
	};

	private static boolean isSocketError(Exception exception) {
		return exception instanceof SocketException || exception instanceof SocketTimeoutException;
	}

	/**
	 * Stop logging exceptions when failing to establish connections to modern protocol clients.
	 */
	@Redirect(method = "onConnectionCrashed", at = @At(value = "INVOKE", target = "Ljava/lang/Exception;printStackTrace()V"))
	private void onConnectionCrashed(Exception exception) {
		var message = exception.getMessage();

		if (!(isSocketError(exception) || Arrays.stream(IGNORED).anyMatch(message::startsWith))) {
			exception.printStackTrace();
		}
	}
}
