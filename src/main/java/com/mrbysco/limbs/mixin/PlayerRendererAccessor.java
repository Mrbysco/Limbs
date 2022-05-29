package com.mrbysco.limbs.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(PlayerRenderer.class)
public interface PlayerRendererAccessor {
	@Invoker("setupRotations")
	void limbsSetupRotations(AbstractClientPlayer clientPlayer, PoseStack poseStack, float ageInTicks, float rotationYaw, float partialTicks);
}
