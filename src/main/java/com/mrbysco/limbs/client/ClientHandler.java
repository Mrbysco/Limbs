package com.mrbysco.limbs.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mrbysco.limbs.Limbs;
import com.mrbysco.limbs.client.bodypart.BodyPartRegistry;
import com.mrbysco.limbs.client.bodypart.BodyPartType;
import com.mrbysco.limbs.item.PartItem;
import com.mrbysco.limbs.item.PartLocation;
import net.minecraft.client.entity.ClientAvatarEntity;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.player.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;
import net.minecraft.client.renderer.entity.state.AvatarRenderState;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.util.context.ContextKey;
import net.minecraft.world.entity.Avatar;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.StandingAndWallBlockItem;
import net.minecraft.world.level.block.AbstractSkullBlock;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterSpecialModelRendererEvent;
import net.neoforged.neoforge.client.event.RenderArmEvent;
import net.neoforged.neoforge.client.event.RenderPlayerEvent;
import net.neoforged.neoforge.client.renderstate.AvatarRenderStateModifier;
import net.neoforged.neoforge.client.renderstate.RegisterRenderStateModifiersEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotResult;
import top.theillusivec4.curios.api.client.ICurioRenderer;
import top.theillusivec4.curios.api.type.capability.ICuriosItemHandler;

import java.util.List;
import java.util.Optional;

@EventBusSubscriber(Dist.CLIENT)
public class ClientHandler {

	public static final ContextKey<Optional<ICuriosItemHandler>> CURIOS_ITEM_HANDLER = new ContextKey<>(Identifier.fromNamespaceAndPath(Limbs.MOD_ID, "curios_inventory"));

	@SubscribeEvent
	public static void registerSpecialModelRenderers(RegisterSpecialModelRendererEvent event) {
		event.register(Identifier.fromNamespaceAndPath(Limbs.MOD_ID, "part"), PartSpecialRenderer.Unbaked.MAP_CODEC);
	}

	@SubscribeEvent
	public static void registerCustomRenderData(RegisterRenderStateModifiersEvent event) {
		event.registerAvatarEntityModifier(new AvatarRenderStateModifier() {
			@Override
			public <T extends Avatar & ClientAvatarEntity> void accept(T avatar, AvatarRenderState renderState) {
				renderState.setRenderData(CURIOS_ITEM_HANDLER, CuriosApi.getCuriosInventory(avatar));
			}
		});
	}

	@SubscribeEvent
	public static void onClientSetup(FMLClientSetupEvent event) {
		for (Item item : BuiltInRegistries.ITEM.stream().toList()) {
			if (item instanceof StandingAndWallBlockItem blockItem && blockItem.getBlock() instanceof AbstractSkullBlock) {
				ICurioRenderer.register(item, CurioSkullRenderer::new);
			}
		}
	}

	@SubscribeEvent
	public static void onRenderArm(RenderArmEvent event) {
		final AbstractClientPlayer player = event.getPlayer();
		final SubmitNodeCollector nodeCollector = event.getSubmitNodeCollector();

		Optional<ICuriosItemHandler> curioInv = CuriosApi.getCuriosInventory(player);
		curioInv.ifPresent(inv -> {
			Optional<SlotResult> firstCurio = inv.findFirstCurio((stack) -> stack.getItem() instanceof PartItem part &&
					part.getPartLocation().isArm() && part.getPartLocation().getHumanoidArm() == event.getArm());

			if (!player.isSpectator() && firstCurio.isPresent()) {
				SlotResult result = firstCurio.get();
				ItemStack stack = result.stack();
				if (stack.getItem() instanceof PartItem partItem) {
					final Identifier partRegistry = partItem.getPartRegistry();
					BodyPartType partType = BodyPartRegistry.BODY_PARTS.getValue(partRegistry);
					if (partType != null) {
						PoseStack poseStack = event.getPoseStack();
						poseStack.pushPose();
						final ModelPart bodyPart = partType.getBodyPart();
						final PartLocation partLocation = partItem.getPartLocation();
						final int packedOverlay = OverlayTexture.pack(OverlayTexture.u(0.0F), OverlayTexture.v(false));

						if (event.getArm() == HumanoidArm.RIGHT && partLocation == PartLocation.RIGHT_ARM) {
							nodeCollector.submitModelPart(bodyPart, poseStack, RenderTypes.entityTranslucent(partType.getTexture()), event.getPackedLight(), packedOverlay, null);
							if (partType.getSecondTexture() != null) {
								poseStack.scale(1.001F, 1.001F, 1.001F);
								nodeCollector.submitModelPart(bodyPart, poseStack, RenderTypes.entityTranslucent(partType.getSecondTexture()), event.getPackedLight(), packedOverlay, null);
							}
							event.setCanceled(true);
						} else if (event.getArm() == HumanoidArm.LEFT && partLocation == PartLocation.LEFT_ARM) {
							nodeCollector.submitModelPart(bodyPart, poseStack, RenderTypes.entityTranslucent(partType.getTexture()), event.getPackedLight(), packedOverlay, null);
							if (partType.getSecondTexture() != null) {
								poseStack.scale(1.01F, 1.01F, 1.01F);
								poseStack.translate(0.001F, -0.005F, 0.001F);
								nodeCollector.submitModelPart(bodyPart, poseStack, RenderTypes.entityTranslucent(partType.getSecondTexture()), event.getPackedLight(), packedOverlay, null);
							}
							event.setCanceled(true);
						}
						poseStack.popPose();
					}
				}
			}
		});
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void onPlayerRenderPre(RenderPlayerEvent.Pre<?> event) {
		if (event.getRenderState() instanceof AvatarRenderState renderState) {
			final AvatarRenderer<?> playerRenderer = event.getRenderer();
			PlayerModel playerModel = playerRenderer.getModel();

			Optional<ICuriosItemHandler> curioInv = renderState.getRenderDataOrDefault(CURIOS_ITEM_HANDLER, Optional.empty());
			curioInv.ifPresent(inv -> {
				List<SlotResult> slotResults = inv.findCurios(stack -> stack.getItem() instanceof PartItem ||
						(stack.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof AbstractSkullBlock));
				for (SlotResult result : slotResults) {
					ItemStack stack = result.stack();
					if (stack.getItem() instanceof PartItem partItem && result.slotContext().visible()) {
						final Identifier partRegistry = partItem.getPartRegistry();
						BodyPartType partType = BodyPartRegistry.BODY_PARTS.getValue(partRegistry);
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
					} else if (stack.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof AbstractSkullBlock skullBlock) {
						playerModel.head.visible = false;
						playerModel.hat.visible = false;
					}
				}
			});
		}
	}

	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void onPlayerRenderPost(RenderPlayerEvent.Post<?> event) {
		if (event.getRenderState() instanceof AvatarRenderState renderState) {
			final AvatarRenderer<?> playerRenderer = event.getRenderer();
			final SubmitNodeCollector nodeCollector = event.getSubmitNodeCollector();
			PlayerModel playerModel = playerRenderer.getModel();

			Optional<ICuriosItemHandler> curioInv = renderState.getRenderDataOrDefault(CURIOS_ITEM_HANDLER, Optional.empty());
			curioInv.ifPresent(inv -> {
				final List<SlotResult> slotResults = inv.findCurios(stack -> stack.getItem() instanceof PartItem ||
						(stack.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof AbstractSkullBlock));
				for (SlotResult result : slotResults) {
					if (result.slotContext().visible()) {
						final ItemStack stack = result.stack();
						final PoseStack poseStack = event.getPoseStack();
						final int packedLight = renderState.lightCoords;
						if (stack.getItem() instanceof PartItem partItem) {
							final int i = OverlayTexture.pack(OverlayTexture.u(0.0F), OverlayTexture.v(false));
							final Identifier partRegistry = partItem.getPartRegistry();
							BodyPartType partType = BodyPartRegistry.BODY_PARTS.getValue(partRegistry);
							if (partType != null) {
								poseStack.pushPose();

//								if (player instanceof AbstractClientPlayer clientPlayer) {
//									setupRotation(poseStack, clientPlayer, playerRenderer, event.getPartialTick(), event.getRenderState().scale);
//								}

								final ModelPart bodyPart = partType.getBodyPart();
								final PartLocation partLocation = partItem.getPartLocation();
								poseStack.scale(0.9375F, 0.9375F, 0.9375F);
								poseStack.scale(renderState.scale, renderState.scale, renderState.scale);
								poseStack.translate(0, (1f / 16) * 24, 0);
								poseStack.scale(1, -1, -1);
								switch (partLocation) {
									case HEAD -> bodyPart.loadPose(playerModel.head.storePose());
									case TORSO -> bodyPart.loadPose(playerModel.body.storePose());
									case LEFT_ARM -> bodyPart.loadPose(playerModel.leftArm.storePose());
									case RIGHT_ARM -> bodyPart.loadPose(playerModel.rightArm.storePose());
									case LEFT_LEG -> bodyPart.loadPose(playerModel.leftLeg.storePose());
									case RIGHT_LEG -> bodyPart.loadPose(playerModel.rightLeg.storePose());
								}
								poseStack.mulPose(Axis.YN.rotationDegrees(180F));

								nodeCollector.submitModelPart(bodyPart, poseStack, RenderTypes.entityTranslucent(partType.getTexture()), packedLight, i, null);
								if (partType.getSecondTexture() != null) {
									poseStack.scale(1.01F, 1.01F, 1.01F);
									poseStack.translate(0.001F, -0.005F, 0.001F);
									nodeCollector.submitModelPart(bodyPart, poseStack, RenderTypes.entityTranslucent(partType.getSecondTexture()), packedLight, i, null);
								}
								poseStack.popPose();
							}
						}
					}
				}
			});
		}
	}
}
