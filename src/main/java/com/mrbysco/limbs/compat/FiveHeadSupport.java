package com.mrbysco.limbs.compat;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mrbysco.fivehead.registry.SmartRegistry;
import net.minecraft.world.item.ItemStack;

public class FiveHeadSupport {
	public static void scaleHead(ItemStack stack, PoseStack poseStack) {
		float scale = stack.getOrDefault(SmartRegistry.SIZE_TYPE.get(), 0.0F);
		if (scale > 0.0F) {
			float newScale = scale + 1.0F;
			poseStack.scale(newScale, newScale, newScale);
			poseStack.translate(0.0F, 0.25F * scale, 0.0F);
		}
	}
}
