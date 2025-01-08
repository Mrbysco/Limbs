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
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public class PartItemInventoryRenderer extends BlockEntityWithoutLevelRenderer {
	private ModelPart bodyPart;
	private ResourceLocation texture;
	@Nullable
	private ResourceLocation secondTexture;

	public PartItemInventoryRenderer(BlockEntityRendererProvider.Context context) {
		super(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
	}

	@Override
	public void renderByItem(ItemStack stack, ItemDisplayContext transformType, PoseStack poseStack, MultiBufferSource bufferSource, int combinedLight, int combinedOverlay) {
		if (stack.getItem() instanceof PartItem partItem) {
			final ResourceLocation partRegistry = partItem.getPartRegistry();
			if (this.bodyPart == null || this.texture == null) {
				BodyPartType type = BodyPartRegistry.BODY_PARTS.get(partRegistry);
				if (type != null) {
					this.bodyPart = type.getInventoryPart();
					this.texture = type.getTexture();
					this.secondTexture = type.getSecondTexture();
				}
			}
			if (this.bodyPart != null) {
				poseStack.pushPose();
				final PartLocation partLocation = partItem.getPartLocation();
				this.bodyPart.x = 0;
				this.bodyPart.y = 6;
				this.bodyPart.z = 0;
				this.bodyPart.xRot = 0;
				this.bodyPart.yRot = 0;
				this.bodyPart.zRot = 0;
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
				this.bodyPart.render(poseStack, bufferSource.getBuffer(RenderType.entityTranslucent(this.texture)), combinedLight, combinedOverlay);
				if (this.secondTexture != null) {
					poseStack.scale(1.01F, 1.01F, 1.01F);
					poseStack.translate(0.001F, -0.005F, 0.001F);
					this.bodyPart.render(poseStack, bufferSource.getBuffer(RenderType.entityTranslucent(this.secondTexture)), combinedLight, combinedOverlay);
				}
				poseStack.popPose();
			}
		}
	}
}
