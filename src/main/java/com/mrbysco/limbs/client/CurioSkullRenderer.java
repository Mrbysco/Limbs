package com.mrbysco.limbs.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HeadedModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.neoforged.fml.ModList;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class CurioSkullRenderer implements ICurioRenderer {
	@Override
	public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext,
	                                                                      PoseStack poseStack, RenderLayerParent<T, M> renderLayerParent,
	                                                                      MultiBufferSource multiBufferSource, int light, float limbSwing,
	                                                                      float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		if (slotContext.identifier().equals("head") && slotContext.visible()) {
			if (!(renderLayerParent.getModel() instanceof HeadedModel headedModel)) {
				return;
			}
			poseStack.pushPose();

			ICurioRenderer.followHeadRotations(slotContext.entity(), headedModel.getHead());
			headedModel.getHead().translateAndRotate(poseStack);

			poseStack.scale(1.25F, -1.25F, -1.25F);
			poseStack.translate(0, 0.5F, 0);

			if (ModList.get().isLoaded("fivehead")) {
				com.mrbysco.limbs.compat.FiveHeadSupport.scaleHead(stack, poseStack);
			}

			var itemRenderer = Minecraft.getInstance().getItemRenderer();
			itemRenderer.renderStatic(stack, ItemDisplayContext.HEAD, light, OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, null, 0);
			poseStack.popPose();
		}
	}
}
