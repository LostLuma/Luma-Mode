package net.lostluma.luma_mode.mixin;

import net.minecraft.block.Block;
import net.minecraft.item.SwordItem;
import net.minecraft.network.packet.BlockUpdatePacket;
import net.minecraft.server.ServerPlayerInteractionManager;
import net.minecraft.server.entity.living.player.ServerPlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ServerPlayerInteractionManager.class)
public class ServerPlayerInteractionManagerMixin {
	@Shadow
	public World world;

	@Shadow
	public ServerPlayerEntity player;

	/**
	 * Stop the player from mining instant mine-able blocks during combat.
	 */
	@Inject(method = "tryMineBlock", at = @At("HEAD"), cancellable = true)
	private void tryMineBlock(int x, int y, int z, CallbackInfoReturnable<Boolean> callbackInfo) {
		var stack = this.player.inventory.getStack(this.player.inventory.selectedSlot);
		var speed = Block.BY_ID[this.world.getBlock(x, y, z)].getMiningSpeed(this.world, x, y, z);

		if (stack != null && speed == 0.0f && stack.getItem().getClass().equals(SwordItem.class)) {
			// Replace the block on the client, otherwise we have a ghost block on our hands!
			this.player.networkHandler.sendPacket(new BlockUpdatePacket(x, y, z, this.world));
			callbackInfo.setReturnValue(false); // Disallow mining entirely, same as adventure mode
		}
	}
}
