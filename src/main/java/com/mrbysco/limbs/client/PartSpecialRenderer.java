package com.mrbysco.limbs.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mrbysco.limbs.client.bodypart.BodyPartRegistry;
import com.mrbysco.limbs.client.bodypart.BodyPartType;
import com.mrbysco.limbs.item.PartItem;
import com.mrbysco.limbs.item.PartLocation;
import com.mrbysco.limbs.registry.PartRegistry;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import org.joml.Vector3fc;
import org.jspecify.annotations.Nullable;

import java.util.function.Consumer;

public class PartSpecialRenderer implements SpecialModelRenderer<PartSpecialRenderer.PartInfo> {

	private ModelPart bodyPart;
	private Identifier texture;
	@Nullable
	private Identifier secondTexture;

	@Override
	public void submit(@Nullable PartInfo argument, PoseStack poseStack, SubmitNodeCollector nodeCollector,
	                   int lightCoords, int overlayCoords, boolean hasFoil, int outlineColor) {
		if (argument == null) return;
		BodyPartType type = argument.bodyPartType();
		if (type != null) {
			this.bodyPart = type.getInventoryPart();
			this.texture = type.getTexture();
			this.secondTexture = type.getSecondTexture();
		}

		PartLocation partLocation = argument.partLocation();
		if (this.bodyPart != null) {
			poseStack.pushPose();
			poseStack.translate(0.5, 0.75, 0.5);
			poseStack.scale(0.5F, 0.5F, 0.5F);
			this.bodyPart.x = 0;
			this.bodyPart.y = 6;
			this.bodyPart.z = 0;
			this.bodyPart.xRot = 0;
			this.bodyPart.yRot = 0;
			this.bodyPart.zRot = 0;
			switch (partLocation) {
				case HEAD -> {
				}
				case TORSO -> {
					poseStack.translate(0, 0.625D, 0);
				}
				case LEFT_ARM, RIGHT_ARM, LEFT_LEG, RIGHT_LEG -> {

					assert type != null;
					Identifier partId = BodyPartRegistry.BODY_PARTS.getKey(type);
					if (partId != null && isEndermanLimb(partId)) {
						poseStack.scale(0.75F, 0.75F, 0.75F);
					}
					poseStack.translate(0, 0.5D, 0);
				}
			}
			poseStack.mulPose(Axis.ZN.rotationDegrees(180F));
			poseStack.mulPose(Axis.YN.rotationDegrees(180F));
			nodeCollector.submitModelPart(this.bodyPart, poseStack, RenderTypes.entityTranslucent(this.texture), lightCoords, overlayCoords, null);
			if (this.secondTexture != null) {
				poseStack.scale(1.01F, 1.01F, 1.01F);
				poseStack.translate(0.001F, -0.005F, 0.001F);
				nodeCollector.submitModelPart(this.bodyPart, poseStack, RenderTypes.entityTranslucent(this.secondTexture), lightCoords, overlayCoords, null);
			}
			poseStack.popPose();
		}
	}

	private boolean isEndermanLimb(Identifier bodyPartIdentifier) {
		return bodyPartIdentifier.equals(PartRegistry.ENDERMAN_LEFT_ARM.getId()) ||
				bodyPartIdentifier.equals(PartRegistry.ENDERMAN_RIGHT_ARM.getId()) ||
				bodyPartIdentifier.equals(PartRegistry.ENDERMAN_LEFT_LEG.getId()) ||
				bodyPartIdentifier.equals(PartRegistry.ENDERMAN_RIGHT_LEG.getId());
	}

	@Override
	public void getExtents(Consumer<Vector3fc> output) {
		if (this.bodyPart == null) return;
		PoseStack posestack = new PoseStack();
		posestack.translate(0.5F, 0.0F, 0.5F);
		posestack.scale(-1.0F, -1.0F, 1.0F);
		this.bodyPart.getExtentsForGui(posestack, output);
	}

	@Override
	public @Nullable PartInfo extractArgument(ItemStack stack) {
		if (stack.getItem() instanceof PartItem partItem) {
			PartLocation partLocation = partItem.getPartLocation();
			final Identifier partRegistry = partItem.getPartRegistry();
			BodyPartType bodyPartType = BodyPartRegistry.BODY_PARTS.getValue(partRegistry);
			if (bodyPartType != null) {
				return new PartInfo(bodyPartType, partLocation);
			}
		}
		return null;
	}

	public record Unbaked(Identifier bodyPartIdentifier,
	                      PartLocation partLocation) implements SpecialModelRenderer.Unbaked<PartInfo> {
		public static final MapCodec<Unbaked> MAP_CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
						Identifier.CODEC.fieldOf("base_model").forGetter(Unbaked::bodyPartIdentifier),
						PartLocation.CODEC.fieldOf("base_trim_texture").forGetter(Unbaked::partLocation))
				.apply(instance, Unbaked::new));

		@Override
		public MapCodec<? extends SpecialModelRenderer.Unbaked<PartInfo>> type() {
			return MAP_CODEC;
		}

		@Override
		public SpecialModelRenderer<PartInfo> bake(BakingContext context) {
			return new PartSpecialRenderer();
		}
	}

	public record PartInfo(BodyPartType bodyPartType, PartLocation partLocation) {

	}
}
