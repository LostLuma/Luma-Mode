package net.lostluma.luma_mode.mixin;

import net.minecraft.block.Block;
import net.minecraft.entity.living.player.PlayerEntity;
import net.minecraft.item.SwordItem;
import net.minecraft.server.ServerPlayerInteractionManager;
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
	public PlayerEntity player;

	/**
	 * Stop the player from mining instant mine-able blocks during combat.
	 */
	@Inject(method = "tryMineBlock", at = @At("HEAD"), cancellable = true)
	private void tryMineBlock(int x, int y, int z, CallbackInfoReturnable<Boolean> callbackInfo) {
		var stack = this.player.inventory.getMainHandStack();
		var speed = Block.BY_ID[this.world.getBlock(x, y, z)].getMiningSpeed();

		if (stack != null && speed == 0.0f && stack.getItem().getClass().equals(SwordItem.class)) {
			callbackInfo.setReturnValue(false); // Disallow mining entirely, same as adventure mode
		}
	}
}
