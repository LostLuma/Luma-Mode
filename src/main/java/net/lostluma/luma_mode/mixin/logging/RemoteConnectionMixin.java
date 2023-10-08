package net.lostluma.luma_mode.mixin.logging;

import net.minecraft.network.RemoteConnection;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Arrays;

@Mixin(RemoteConnection.class)
public class RemoteConnectionMixin {
	private static final String[] IGNORED = {"Bad packet id", "Received string length longer than maximum"};

	/**
	 * Stop logging exceptions when failing to establish connections to modern protocol clients.
	 */
	@Redirect(method = "onConnectionCrashed", at = @At(value = "INVOKE", target = "Ljava/lang/Exception;printStackTrace()V"))
	private void onConnectionCrashed(Exception exception) {
		if (Arrays.stream(IGNORED).noneMatch(x -> exception.getMessage().startsWith(x))) {
			exception.printStackTrace();
		}
	}
}
