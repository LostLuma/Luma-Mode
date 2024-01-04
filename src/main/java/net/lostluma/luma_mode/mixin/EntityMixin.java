package net.lostluma.luma_mode.mixin;

import net.lostluma.luma_mode.util.Nbt;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.Box;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Fixes entities suffocating in blocks when loaded (MC-2025).

 * This fix was originally proposed by @WolfieMario on Mojira:
 * <a href="https://bugs.mojang.com/browse/MC-2025?focusedCommentId=74619#comment-74619">MC-2025</a>
 */
@Mixin(Entity.class)
public class EntityMixin {
	@Shadow
	@Final
	public Box shape;

	@Inject(
		method = "writeEntityNbt",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/entity/Entity;writeCustomNbt(Lnet/minecraft/nbt/NbtCompound;)V"
		)
	)
	private void writeEntityNbt(NbtCompound nbt, CallbackInfo callbackInfo) {
		var shape = this.shape;
		nbt.put("AABB", Nbt.asNbtList(shape.minX, shape.minY, shape.minZ, shape.maxX, shape.maxY, shape.maxZ));
	}

	@Inject(
		method = "readEntityNbt",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/entity/Entity;readCustomNbt(Lnet/minecraft/nbt/NbtCompound;)V"
		)
	)
	private void readEntityNbt(NbtCompound nbt, CallbackInfo callbackInfo) {
		if (!nbt.contains("AABB")) {
			return;
		}

		var bounds = Nbt.fromNbtList(nbt.getList("AABB"));
		this.shape.set(bounds.get(0), bounds.get(1), bounds.get(2), bounds.get(3), bounds.get(4), bounds.get(5));
	}
}
