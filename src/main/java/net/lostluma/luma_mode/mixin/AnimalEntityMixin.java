package net.lostluma.luma_mode.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;

import net.minecraft.entity.living.mob.passive.animal.AnimalEntity;

@Mixin(AnimalEntity.class)
public class AnimalEntityMixin {
	/**
	 * Disable the player from interacting with food on animals that have already received some.
	 *
	 * Prevents repeatedly wasting food when feeding multiple animals in a pen. This gets fixed in later Minecraft versions.
	 */
	@WrapOperation(method = "interact", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/living/mob/passive/animal/AnimalEntity;getBreedingAge()I"))
	private int interact(AnimalEntity instance, Operation<Integer> original) {
		if (instance.loveTicks != 0) {
			return -1;
		} else {
			return original.call(instance);
		}
	}
}
