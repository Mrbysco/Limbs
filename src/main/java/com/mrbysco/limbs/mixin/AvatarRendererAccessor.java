package com.mrbysco.limbs.mixin;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(AvatarRenderer.class)
public interface AvatarRendererAccessor {
	@Invoker("setupRotations")
	void limbs_setupRotations(AvatarRenderState state, PoseStack poseStack, float bodyRot, float entityScale);
}
