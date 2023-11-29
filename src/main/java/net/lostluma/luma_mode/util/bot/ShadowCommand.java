package net.lostluma.luma_mode.util.bot;

import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.AbstractCommand;
import net.minecraft.server.command.source.CommandSource;
import org.jetbrains.annotations.NotNull;

public class ShadowCommand extends AbstractCommand {
	@Override
	public String getName() {
		return "shadow";
	}

	@Override
	public boolean canUse(CommandSource source) {
		return true;
	}

	@Override
	public int getRequiredPermissionLevel() {
		return 0;
	}

	@Override
	public void run(CommandSource source, String[] args) {
		var server = MinecraftServer.getInstance();
		var playerManager = server.getPlayerManager();

		var player = playerManager.createForLogin(source.getSourceName());
		playerManager.onLogin(new BotConnection(), player); // Replace author :)
	}

	@Override
	public int compareTo(@NotNull Object o) {
		return 0;
	}
}
