package com.mrbysco.limbs.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mrbysco.limbs.client.bodypart.BodyPartRegistry;
import com.mrbysco.limbs.client.bodypart.BodyPartType;
import com.mrbysco.limbs.item.PartItem;
import com.mrbysco.limbs.item.PartLocation;
import com.mrbysco.limbs.registry.LimbRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemTransforms.TransformType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

public class PartItemInventoryRenderer extends BlockEntityWithoutLevelRenderer {
	public PartItemInventoryRenderer(BlockEntityRendererProvider.Context context) {
		super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
	}

	@Override
	public void renderByItem(ItemStack stack, TransformType transformType, PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {
		if (stack.getItem() instanceof PartItem partItem) {
			final ResourceLocation partRegistry = partItem.getPartRegistry();
			BodyPartType partType = BodyPartRegistry.BODY_PARTS.get().getValue(partRegistry);
			if (partType != null) {
				poseStack.pushPose();
				final ModelPart bodyPart = partType.getInventoryPart();
				final PartLocation partLocation = partItem.getPartLocation();
				bodyPart.x = 0;
				bodyPart.y = 6;
				bodyPart.z = 0;
				bodyPart.xRot = 0;
				bodyPart.yRot = 0;
				bodyPart.zRot = 0;
				poseStack.translate(1, 0, 0);
				switch (partLocation) {
					case HEAD -> {
					}
					case TORSO -> {
						poseStack.translate(0, 0.625D, 0);
					}
					case LEFT_ARM, RIGHT_ARM, LEFT_LEG, RIGHT_LEG -> {
						if (stack.is(LimbRegistry.ENDERMAN_LIMBS.getLeftArm()) && stack.is(LimbRegistry.ENDERMAN_LIMBS.getRightArm()) &&
								stack.is(LimbRegistry.ENDERMAN_LIMBS.getLeftLeg()) && stack.is(LimbRegistry.ENDERMAN_LIMBS.getRightLeg())) {
							poseStack.scale(0.75F, 0.75F, 0.75F);
						}
						poseStack.translate(0, 0.5D, 0);
					}
				}
				poseStack.mulPose(Axis.ZN.rotationDegrees(180F));
				poseStack.mulPose(Axis.YN.rotationDegrees(180F));
				bodyPart.render(poseStack, bufferSource.getBuffer(RenderType.entityTranslucent(partType.getTexture())), combinedLight, combinedOverlay);
				if (partType.getSecondTexture() != null) {
					poseStack.scale(1.01F, 1.01F, 1.01F);
					poseStack.translate(0.001F, -0.005F, 0.001F);
					bodyPart.render(poseStack, bufferSource.getBuffer(RenderType.entityTranslucent(partType.getSecondTexture())), combinedLight, combinedOverlay);
				}
				poseStack.popPose();
			}
		}
	}
}
