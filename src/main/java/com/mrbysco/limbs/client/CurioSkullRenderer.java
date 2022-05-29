package com.mrbysco.limbs.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.fml.ModList;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class CurioSkullRenderer implements ICurioRenderer {
	@Override
	public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext,
																		  PoseStack poseStack, RenderLayerParent<T, M> renderLayerParent,
																		  MultiBufferSource multiBufferSource, int light, float limbSwing,
																		  float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if (slotContext.identifier().equals("head")) {
			if (!(renderLayerParent.getModel() instanceof HeadedModel headedModel)) {
				return;
			}
			poseStack.pushPose();

			ICurioRenderer.followHeadRotations(slotContext.entity(), headedModel.getHead());
			headedModel.getHead().translateAndRotate(poseStack);

			poseStack.scale(1.25F, -1.25F, -1.25F);
			poseStack.translate(0, 0.5F, 0);

			if (ModList.get().isLoaded("fivehead") && stack.hasTag() && stack.getTag().contains("FiveHeadScale")) {
				float scale = stack.getTag().getFloat("FiveHeadScale");
				if (scale > 0.0F) {
					float newScale = scale + 1.0F;
					poseStack.scale(newScale, newScale, newScale);
					poseStack.translate(0, 0.25F * scale, 0);
				}
			}

			Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemTransforms.TransformType.HEAD, light, OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, 0);
			poseStack.popPose();
		}
	}
}
