package com.mrbysco.limbs.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import com.mrbysco.limbs.client.bodypart.BodyPartRegistry;
import com.mrbysco.limbs.client.bodypart.BodyPartType;
import com.mrbysco.limbs.item.PartItem;
import com.mrbysco.limbs.item.PartLocation;
import com.mrbysco.limbs.mixin.LivingEntityRendererAccessor;
import com.mrbysco.limbs.mixin.PlayerRendererAccessor;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.event.RenderArmEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;

import java.util.List;
import java.util.Optional;

public class ClientHandler {

	public static void onRenderArm(RenderArmEvent event) {
		final AbstractClientPlayer player = event.getPlayer();

		Optional<SlotResult> firstCurio = CuriosApi.getCuriosHelper().findFirstCurio(player, (stack) -> stack.getItem() instanceof PartItem part &&
				part.getPartLocation().isArm() && part.getPartLocation().getHumanoidArm() == event.getArm());
		if (!player.isSpectator() && firstCurio.isPresent()) {
			SlotResult result = firstCurio.get();
			ItemStack stack = result.stack();
			if (stack.getItem() instanceof PartItem partItem) {
				final ResourceLocation partRegistry = partItem.getPartRegistry();
				BodyPartType partType = BodyPartRegistry.BODY_PARTS.get().getValue(partRegistry);
				if (partType != null) {
					PoseStack poseStack = event.getPoseStack();
					poseStack.pushPose();
					final ModelPart bodyPart = partType.getBodyPart();
					final PartLocation partLocation = partItem.getPartLocation();
					final int i = OverlayTexture.pack(OverlayTexture.u(0.0F), OverlayTexture.v(false));

					if (event.getArm() == HumanoidArm.RIGHT && partLocation == PartLocation.RIGHT_ARM) {
						bodyPart.render(poseStack, event.getMultiBufferSource().getBuffer(RenderType.entityTranslucent(partType.getTexture())), event.getPackedLight(), i);
						if (partType.getSecondTexture() != null) {
							poseStack.scale(1.001F, 1.001F, 1.001F);
							bodyPart.render(poseStack, event.getMultiBufferSource().getBuffer(RenderType.entityTranslucent(partType.getSecondTexture())), event.getPackedLight(), i);
						}
						event.setCanceled(true);
					} else if (event.getArm() == HumanoidArm.LEFT && partLocation == PartLocation.LEFT_ARM) {
						bodyPart.render(poseStack, event.getMultiBufferSource().getBuffer(RenderType.entityTranslucent(partType.getTexture())), event.getPackedLight(), i);
						if (partType.getSecondTexture() != null) {
							poseStack.scale(1.01F, 1.01F, 1.01F);
							poseStack.translate(0.001F, -0.005F, 0.001F);
							bodyPart.render(poseStack, event.getMultiBufferSource().getBuffer(RenderType.entityTranslucent(partType.getSecondTexture())), event.getPackedLight(), i);
						}
						event.setCanceled(true);
					}
					poseStack.popPose();
				}
			}
		}
	}

	public static void onPlayerRenderPre(RenderPlayerEvent.Pre event) {
		final Player player = event.getPlayer();
		final PlayerRenderer playerRenderer = event.getRenderer();
		PlayerModel<?> playerModel = playerRenderer.getModel();

		List<SlotResult> slotResults = CuriosApi.getCuriosHelper().findCurios(player, stack -> stack.getItem() instanceof PartItem);
		for (SlotResult result : slotResults) {
			ItemStack stack = result.stack();
			if (stack.getItem() instanceof PartItem partItem) {
				final ResourceLocation partRegistry = partItem.getPartRegistry();
				BodyPartType partType = BodyPartRegistry.BODY_PARTS.get().getValue(partRegistry);
				if (partType != null) {
					final PartLocation partLocation = partItem.getPartLocation();
					switch (partLocation) {
						case HEAD -> {
							playerModel.head.visible = false;
							playerModel.hat.visible = false;
						}
						case TORSO -> {
							playerModel.body.visible = false;
							playerModel.jacket.visible = false;
						}
						case LEFT_ARM -> {
							playerModel.leftArm.visible = false;
							playerModel.leftSleeve.visible = false;
						}
						case RIGHT_ARM -> {
							playerModel.rightArm.visible = false;
							playerModel.rightSleeve.visible = false;
						}
						case LEFT_LEG -> {
							playerModel.leftLeg.visible = false;
							playerModel.leftPants.visible = false;
						}
						case RIGHT_LEG -> {
							playerModel.rightLeg.visible = false;
							playerModel.rightPants.visible = false;
						}
					}
				}
			}
		}
	}

	public static void onPlayerRenderPost(RenderPlayerEvent.Post event) {
		final Player player = event.getPlayer();
		final PlayerRenderer playerRenderer = event.getRenderer();
		PlayerModel<?> playerModel = playerRenderer.getModel();

		List<SlotResult> slotResults = CuriosApi.getCuriosHelper().findCurios(player, stack -> stack.getItem() instanceof PartItem);
		for (SlotResult result : slotResults) {
			ItemStack stack = result.stack();
			if (stack.getItem() instanceof PartItem partItem) {
				final int i = OverlayTexture.pack(OverlayTexture.u(0.0F), OverlayTexture.v(false));
				final ResourceLocation partRegistry = partItem.getPartRegistry();
				BodyPartType partType = BodyPartRegistry.BODY_PARTS.get().getValue(partRegistry);
				if (partType != null) {
					PoseStack poseStack = event.getPoseStack();
					poseStack.pushPose();

					if (player instanceof AbstractClientPlayer clientPlayer) {
						setupRotation(poseStack, clientPlayer, playerRenderer, event.getPartialTick());
					}

					final ModelPart bodyPart = partType.getBodyPart();
					final PartLocation partLocation = partItem.getPartLocation();
					poseStack.scale(0.9375F, 0.9375F, 0.9375F);
					poseStack.translate(0, (1f / 16) * 24, 0);
					poseStack.scale(1, -1, -1);
					switch (partLocation) {
						case HEAD -> {
							bodyPart.loadPose(playerModel.head.storePose());
						}
						case TORSO -> bodyPart.loadPose(playerModel.body.storePose());
						case LEFT_ARM -> bodyPart.loadPose(playerModel.leftArm.storePose());
						case RIGHT_ARM -> bodyPart.loadPose(playerModel.rightArm.storePose());
						case LEFT_LEG -> bodyPart.loadPose(playerModel.leftLeg.storePose());
						case RIGHT_LEG -> bodyPart.loadPose(playerModel.rightLeg.storePose());
					}
					poseStack.mulPose(Vector3f.YN.rotationDegrees(180F));

					bodyPart.render(poseStack, event.getMultiBufferSource().getBuffer(RenderType.entityTranslucent(partType.getTexture())), event.getPackedLight(), i);
					if (partType.getSecondTexture() != null) {
						poseStack.scale(1.01F, 1.01F, 1.01F);
						poseStack.translate(0.001F, -0.005F, 0.001F);
						bodyPart.render(poseStack, event.getMultiBufferSource().getBuffer(RenderType.entityTranslucent(partType.getSecondTexture())), event.getPackedLight(), i);
					}
					poseStack.popPose();
				}
			}
		}
	}

	protected static void setupRotation(PoseStack poseStack, AbstractClientPlayer player, PlayerRenderer playerRenderer, float partialTicks) {
		boolean shouldSit = player.isPassenger() && (player.getVehicle() != null && player.getVehicle().shouldRiderSit());
		float f = Mth.rotLerp(partialTicks, player.yBodyRotO, player.yBodyRot);
		float f1 = Mth.rotLerp(partialTicks, player.yHeadRotO, player.yHeadRot);
		if (shouldSit && player.getVehicle() instanceof LivingEntity) {
			LivingEntity livingentity = (LivingEntity) player.getVehicle();
			f = Mth.rotLerp(partialTicks, livingentity.yBodyRotO, livingentity.yBodyRot);
			float f2 = f1 - f;
			float f3 = Mth.wrapDegrees(f2);
			if (f3 < -85.0F) {
				f3 = -85.0F;
			}

			if (f3 >= 85.0F) {
				f3 = 85.0F;
			}

			f = f1 - f3;
			if (f3 * f3 > 2500.0F) {
				f += f3 * 0.2F;
			}
		}


		float f7 = ((LivingEntityRendererAccessor) playerRenderer).limbsGetBob(player, partialTicks);
		((PlayerRendererAccessor) playerRenderer).limbsSetupRotations(player, poseStack, f7, f, partialTicks);
	}
}
