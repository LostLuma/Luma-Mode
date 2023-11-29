package net.lostluma.luma_mode.mixin;

import net.lostluma.luma_mode.util.bot.ShadowCommand;
import net.minecraft.server.command.handler.CommandManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CommandManager.class)
public class CommandManagerMixin {
	@Inject(method = "<init>", at = @At("TAIL"))
	private void onInit(CallbackInfo callbackInfo) {
		((CommandManager) (Object) this).register(new ShadowCommand());
	}
}
