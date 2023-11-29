package net.lostluma.luma_mode.util.bot;

import net.minecraft.network.Connection;
import net.minecraft.network.PacketHandler;
import net.minecraft.network.packet.Packet;

import java.net.SocketAddress;

public class BotConnection implements Connection {
	@Override
	public void setListener(PacketHandler listener) {}

	@Override
	public void send(Packet packet) {}

	@Override
	public void interrupt() {}

	@Override
	public void tick() {}

	@Override
	public SocketAddress getAddress() {
		return null;
	}

	@Override
	public void close() {}

	@Override
	public int getBlockDataSendQueueSize() {
		return 0;
	}

	@Override
	public void disconnect(String reason, Object... args) {}

	@Override
	public void stop() {}
}
