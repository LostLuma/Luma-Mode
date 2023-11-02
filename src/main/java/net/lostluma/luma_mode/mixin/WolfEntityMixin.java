package net.lostluma.luma_mode.mixin;

import net.minecraft.entity.living.mob.passive.animal.tamable.WolfEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.List;

@Mixin(WolfEntity.class)
public class WolfEntityMixin {
	@Redirect(
		method = { "tickDespawn" },
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/World;getEntities(Ljava/lang/Class;Lnet/minecraft/util/math/Box;)Ljava/util/List;"
		)
	)
	private List<?> getBlock(World world, Class type, Box bounds) {
		return List.of();
	}
}
