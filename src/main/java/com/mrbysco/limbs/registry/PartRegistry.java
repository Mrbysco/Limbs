package com.mrbysco.limbs.registry;

import com.mrbysco.limbs.Limbs;
import com.mrbysco.limbs.client.bodypart.BodyPartRegistry;
import com.mrbysco.limbs.client.bodypart.BodyPartType;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EndermanModel;
import net.minecraft.client.model.PiglinModel;
import net.minecraft.client.model.SkeletonModel;
import net.minecraft.client.model.ZombieModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class PartRegistry {
	public static final DeferredRegister<BodyPartType> BODY_PARTS = DeferredRegister.create(BodyPartRegistry.BODY_PARTS_KEY, Limbs.MOD_ID);

	public static final Supplier<BodyPartType> SKELETON_HEAD = BODY_PARTS.register("skeleton_head", () -> new BodyPartType(() -> new SkeletonModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.SKELETON)).getHead(), new ResourceLocation("textures/entity/skeleton/skeleton.png")));
	public static final Supplier<BodyPartType> SKELETON_TORSO = BODY_PARTS.register("skeleton_torso", () -> new BodyPartType(() -> new SkeletonModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.SKELETON)).body, new ResourceLocation("textures/entity/skeleton/skeleton.png")));
	public static final Supplier<BodyPartType> SKELETON_LEFT_ARM = BODY_PARTS.register("skeleton_left_arm", () -> new BodyPartType(() -> new SkeletonModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.SKELETON)).leftArm, new ResourceLocation("textures/entity/skeleton/skeleton.png")));
	public static final Supplier<BodyPartType> SKELETON_RIGHT_ARM = BODY_PARTS.register("skeleton_right_arm", () -> new BodyPartType(() -> new SkeletonModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.SKELETON)).rightArm, new ResourceLocation("textures/entity/skeleton/skeleton.png")));
	public static final Supplier<BodyPartType> SKELETON_LEFT_LEG = BODY_PARTS.register("skeleton_left_leg", () -> new BodyPartType(() -> new SkeletonModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.SKELETON)).leftLeg, new ResourceLocation("textures/entity/skeleton/skeleton.png")));
	public static final Supplier<BodyPartType> SKELETON_RIGHT_LEG = BODY_PARTS.register("skeleton_right_leg", () -> new BodyPartType(() -> new SkeletonModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.SKELETON)).rightLeg, new ResourceLocation("textures/entity/skeleton/skeleton.png")));

	public static final Supplier<BodyPartType> STRAY_HEAD = BODY_PARTS.register("stray_head", () -> new BodyPartType(() -> new SkeletonModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.STRAY)).getHead(), new ResourceLocation("textures/entity/skeleton/stray.png"), new ResourceLocation("textures/entity/skeleton/stray_overlay.png")));
	public static final Supplier<BodyPartType> STRAY_TORSO = BODY_PARTS.register("stray_torso", () -> new BodyPartType(() -> new SkeletonModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.STRAY)).body, new ResourceLocation("textures/entity/skeleton/stray.png"), new ResourceLocation("textures/entity/skeleton/stray_overlay.png")));
	public static final Supplier<BodyPartType> STRAY_LEFT_ARM = BODY_PARTS.register("stray_left_arm", () -> new BodyPartType(() -> new SkeletonModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.STRAY)).leftArm, new ResourceLocation("textures/entity/skeleton/stray.png"), new ResourceLocation("textures/entity/skeleton/stray_overlay.png")));
	public static final Supplier<BodyPartType> STRAY_RIGHT_ARM = BODY_PARTS.register("stray_right_arm", () -> new BodyPartType(() -> new SkeletonModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.STRAY)).rightArm, new ResourceLocation("textures/entity/skeleton/stray.png"), new ResourceLocation("textures/entity/skeleton/stray_overlay.png")));
	public static final Supplier<BodyPartType> STRAY_LEFT_LEG = BODY_PARTS.register("stray_left_leg", () -> new BodyPartType(() -> new SkeletonModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.STRAY)).leftLeg, new ResourceLocation("textures/entity/skeleton/stray.png"), new ResourceLocation("textures/entity/skeleton/stray_overlay.png")));
	public static final Supplier<BodyPartType> STRAY_RIGHT_LEG = BODY_PARTS.register("stray_right_leg", () -> new BodyPartType(() -> new SkeletonModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.STRAY)).rightLeg, new ResourceLocation("textures/entity/skeleton/stray.png"), new ResourceLocation("textures/entity/skeleton/stray_overlay.png")));

	public static final Supplier<BodyPartType> WITHER_SKELETON_HEAD = BODY_PARTS.register("wither_skeleton_head", () -> new BodyPartType(() -> new SkeletonModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.WITHER_SKELETON)).getHead(), new ResourceLocation("textures/entity/skeleton/wither_skeleton.png")));
	public static final Supplier<BodyPartType> WITHER_SKELETON_TORSO = BODY_PARTS.register("wither_skeleton_torso", () -> new BodyPartType(() -> new SkeletonModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.WITHER_SKELETON)).body, new ResourceLocation("textures/entity/skeleton/wither_skeleton.png")));
	public static final Supplier<BodyPartType> WITHER_SKELETON_LEFT_ARM = BODY_PARTS.register("wither_skeleton_left_arm", () -> new BodyPartType(() -> new SkeletonModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.WITHER_SKELETON)).leftArm, new ResourceLocation("textures/entity/skeleton/wither_skeleton.png")));
	public static final Supplier<BodyPartType> WITHER_SKELETON_RIGHT_ARM = BODY_PARTS.register("wither_skeleton_right_arm", () -> new BodyPartType(() -> new SkeletonModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.WITHER_SKELETON)).rightArm, new ResourceLocation("textures/entity/skeleton/wither_skeleton.png")));
	public static final Supplier<BodyPartType> WITHER_SKELETON_LEFT_LEG = BODY_PARTS.register("wither_skeleton_left_leg", () -> new BodyPartType(() -> new SkeletonModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.WITHER_SKELETON)).leftLeg, new ResourceLocation("textures/entity/skeleton/wither_skeleton.png")));
	public static final Supplier<BodyPartType> WITHER_SKELETON_RIGHT_LEG = BODY_PARTS.register("wither_skeleton_right_leg", () -> new BodyPartType(() -> new SkeletonModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.WITHER_SKELETON)).rightLeg, new ResourceLocation("textures/entity/skeleton/wither_skeleton.png")));

	public static final Supplier<BodyPartType> ZOMBIE_HEAD = BODY_PARTS.register("zombie_head", () -> new BodyPartType(() -> new ZombieModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.ZOMBIE)).getHead(), new ResourceLocation("textures/entity/zombie/zombie.png")));
	public static final Supplier<BodyPartType> ZOMBIE_TORSO = BODY_PARTS.register("zombie_torso", () -> new BodyPartType(() -> new ZombieModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.ZOMBIE)).body, new ResourceLocation("textures/entity/zombie/zombie.png")));
	public static final Supplier<BodyPartType> ZOMBIE_LEFT_ARM = BODY_PARTS.register("zombie_left_arm", () -> new BodyPartType(() -> new ZombieModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.ZOMBIE)).leftArm, new ResourceLocation("textures/entity/zombie/zombie.png")));
	public static final Supplier<BodyPartType> ZOMBIE_RIGHT_ARM = BODY_PARTS.register("zombie_right_arm", () -> new BodyPartType(() -> new ZombieModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.ZOMBIE)).rightArm, new ResourceLocation("textures/entity/zombie/zombie.png")));
	public static final Supplier<BodyPartType> ZOMBIE_LEFT_LEG = BODY_PARTS.register("zombie_left_leg", () -> new BodyPartType(() -> new ZombieModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.ZOMBIE)).leftLeg, new ResourceLocation("textures/entity/zombie/zombie.png")));
	public static final Supplier<BodyPartType> ZOMBIE_RIGHT_LEG = BODY_PARTS.register("zombie_right_leg", () -> new BodyPartType(() -> new ZombieModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.ZOMBIE)).rightLeg, new ResourceLocation("textures/entity/zombie/zombie.png")));

	public static final Supplier<BodyPartType> HUSK_HEAD = BODY_PARTS.register("husk_head", () -> new BodyPartType(() -> new ZombieModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.HUSK)).getHead(), new ResourceLocation("textures/entity/zombie/husk.png")));
	public static final Supplier<BodyPartType> HUSK_TORSO = BODY_PARTS.register("husk_torso", () -> new BodyPartType(() -> new ZombieModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.HUSK)).body, new ResourceLocation("textures/entity/zombie/husk.png")));
	public static final Supplier<BodyPartType> HUSK_LEFT_ARM = BODY_PARTS.register("husk_left_arm", () -> new BodyPartType(() -> new ZombieModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.HUSK)).leftArm, new ResourceLocation("textures/entity/zombie/husk.png")));
	public static final Supplier<BodyPartType> HUSK_RIGHT_ARM = BODY_PARTS.register("husk_right_arm", () -> new BodyPartType(() -> new ZombieModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.HUSK)).rightArm, new ResourceLocation("textures/entity/zombie/husk.png")));
	public static final Supplier<BodyPartType> HUSK_LEFT_LEG = BODY_PARTS.register("husk_left_leg", () -> new BodyPartType(() -> new ZombieModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.HUSK)).leftLeg, new ResourceLocation("textures/entity/zombie/husk.png")));
	public static final Supplier<BodyPartType> HUSK_RIGHT_LEG = BODY_PARTS.register("husk_right_leg", () -> new BodyPartType(() -> new ZombieModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.HUSK)).rightLeg, new ResourceLocation("textures/entity/zombie/husk.png")));

	public static final Supplier<BodyPartType> DROWNED_HEAD = BODY_PARTS.register("drowned_head", () -> new BodyPartType(() -> new ZombieModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.DROWNED)).getHead(), new ResourceLocation("textures/entity/zombie/drowned.png"), new ResourceLocation("textures/entity/zombie/drowned_outer_layer.png")));
	public static final Supplier<BodyPartType> DROWNED_TORSO = BODY_PARTS.register("drowned_torso", () -> new BodyPartType(() -> new ZombieModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.DROWNED)).body, new ResourceLocation("textures/entity/zombie/drowned.png"), new ResourceLocation("textures/entity/zombie/drowned_outer_layer.png")));
	public static final Supplier<BodyPartType> DROWNED_LEFT_ARM = BODY_PARTS.register("drowned_left_arm", () -> new BodyPartType(() -> new ZombieModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.DROWNED)).leftArm, new ResourceLocation("textures/entity/zombie/drowned.png"), new ResourceLocation("textures/entity/zombie/drowned_outer_layer.png")));
	public static final Supplier<BodyPartType> DROWNED_RIGHT_ARM = BODY_PARTS.register("drowned_right_arm", () -> new BodyPartType(() -> new ZombieModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.DROWNED)).rightArm, new ResourceLocation("textures/entity/zombie/drowned.png"), new ResourceLocation("textures/entity/zombie/drowned_outer_layer.png")));
	public static final Supplier<BodyPartType> DROWNED_LEFT_LEG = BODY_PARTS.register("drowned_left_leg", () -> new BodyPartType(() -> new ZombieModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.DROWNED)).leftLeg, new ResourceLocation("textures/entity/zombie/drowned.png"), new ResourceLocation("textures/entity/zombie/drowned_outer_layer.png")));
	public static final Supplier<BodyPartType> DROWNED_RIGHT_LEG = BODY_PARTS.register("drowned_right_leg", () -> new BodyPartType(() -> new ZombieModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.DROWNED)).rightLeg, new ResourceLocation("textures/entity/zombie/drowned.png"), new ResourceLocation("textures/entity/zombie/drowned_outer_layer.png")));

	public static final Supplier<BodyPartType> ENDERMAN_HEAD = BODY_PARTS.register("enderman_head", () -> new BodyPartType(() -> new EndermanModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.ENDERMAN)).getHead(), new ResourceLocation("textures/entity/enderman/enderman.png")));
	public static final Supplier<BodyPartType> ENDERMAN_TORSO = BODY_PARTS.register("enderman_torso", () -> new BodyPartType(() -> new EndermanModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.ENDERMAN)).body, new ResourceLocation("textures/entity/enderman/enderman.png")));
	public static final Supplier<BodyPartType> ENDERMAN_LEFT_ARM = BODY_PARTS.register("enderman_left_arm", () -> new BodyPartType(() -> new EndermanModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.ENDERMAN)).leftArm, new ResourceLocation("textures/entity/enderman/enderman.png")));
	public static final Supplier<BodyPartType> ENDERMAN_RIGHT_ARM = BODY_PARTS.register("enderman_right_arm", () -> new BodyPartType(() -> new EndermanModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.ENDERMAN)).rightArm, new ResourceLocation("textures/entity/enderman/enderman.png")));
	public static final Supplier<BodyPartType> ENDERMAN_LEFT_LEG = BODY_PARTS.register("enderman_left_leg", () -> new BodyPartType(() -> new EndermanModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.ENDERMAN)).leftLeg, new ResourceLocation("textures/entity/enderman/enderman.png")));
	public static final Supplier<BodyPartType> ENDERMAN_RIGHT_LEG = BODY_PARTS.register("enderman_right_leg", () -> new BodyPartType(() -> new EndermanModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.ENDERMAN)).rightLeg, new ResourceLocation("textures/entity/enderman/enderman.png")));

	public static final Supplier<BodyPartType> PIGLIN_HEAD = BODY_PARTS.register("piglin_head", () -> new BodyPartType(() -> new PiglinModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.PIGLIN)).getHead(), new ResourceLocation("textures/entity/piglin/piglin.png")));
	public static final Supplier<BodyPartType> PIGLIN_TORSO = BODY_PARTS.register("piglin_torso", () -> new BodyPartType(() -> new PiglinModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.PIGLIN)).body, new ResourceLocation("textures/entity/piglin/piglin.png")));
	public static final Supplier<BodyPartType> PIGLIN_LEFT_ARM = BODY_PARTS.register("piglin_left_arm", () -> new BodyPartType(() -> new PiglinModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.PIGLIN)).leftArm, new ResourceLocation("textures/entity/piglin/piglin.png")));
	public static final Supplier<BodyPartType> PIGLIN_RIGHT_ARM = BODY_PARTS.register("piglin_right_arm", () -> new BodyPartType(() -> new PiglinModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.PIGLIN)).rightArm, new ResourceLocation("textures/entity/piglin/piglin.png")));
	public static final Supplier<BodyPartType> PIGLIN_LEFT_LEG = BODY_PARTS.register("piglin_left_leg", () -> new BodyPartType(() -> new PiglinModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.PIGLIN)).leftLeg, new ResourceLocation("textures/entity/piglin/piglin.png")));
	public static final Supplier<BodyPartType> PIGLIN_RIGHT_LEG = BODY_PARTS.register("piglin_right_leg", () -> new BodyPartType(() -> new PiglinModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.PIGLIN)).rightLeg, new ResourceLocation("textures/entity/piglin/piglin.png")));

	public static final Supplier<BodyPartType> ZOMBIFIED_PIGLIN_HEAD = BODY_PARTS.register("zombified_piglin_head", () -> new BodyPartType(() -> new PiglinModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.ZOMBIFIED_PIGLIN)).getHead(), new ResourceLocation("textures/entity/piglin/zombified_piglin.png")));
	public static final Supplier<BodyPartType> ZOMBIFIED_PIGLIN_TORSO = BODY_PARTS.register("zombified_piglin_torso", () -> new BodyPartType(() -> new PiglinModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.ZOMBIFIED_PIGLIN)).body, new ResourceLocation("textures/entity/piglin/zombified_piglin.png")));
	public static final Supplier<BodyPartType> ZOMBIFIED_PIGLIN_LEFT_ARM = BODY_PARTS.register("zombified_piglin_left_arm", () -> new BodyPartType(() -> new PiglinModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.ZOMBIFIED_PIGLIN)).leftArm, new ResourceLocation("textures/entity/piglin/zombified_piglin.png")));
	public static final Supplier<BodyPartType> ZOMBIFIED_PIGLIN_RIGHT_ARM = BODY_PARTS.register("zombified_piglin_right_arm", () -> new BodyPartType(() -> new PiglinModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.ZOMBIFIED_PIGLIN)).rightArm, new ResourceLocation("textures/entity/piglin/zombified_piglin.png")));
	public static final Supplier<BodyPartType> ZOMBIFIED_PIGLIN_LEFT_LEG = BODY_PARTS.register("zombified_piglin_left_leg", () -> new BodyPartType(() -> new PiglinModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.ZOMBIFIED_PIGLIN)).leftLeg, new ResourceLocation("textures/entity/piglin/zombified_piglin.png")));
	public static final Supplier<BodyPartType> ZOMBIFIED_PIGLIN_RIGHT_LEG = BODY_PARTS.register("zombified_piglin_right_leg", () -> new BodyPartType(() -> new PiglinModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.ZOMBIFIED_PIGLIN)).rightLeg, new ResourceLocation("textures/entity/piglin/zombified_piglin.png")));

	public static final Supplier<BodyPartType> PIGLIN_BRUTE_HEAD = BODY_PARTS.register("piglin_brute_head", () -> new BodyPartType(() -> new PiglinModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.PIGLIN_BRUTE)).getHead(), new ResourceLocation("textures/entity/piglin/piglin_brute.png")));
	public static final Supplier<BodyPartType> PIGLIN_BRUTE_TORSO = BODY_PARTS.register("piglin_brute_torso", () -> new BodyPartType(() -> new PiglinModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.PIGLIN_BRUTE)).body, new ResourceLocation("textures/entity/piglin/piglin_brute.png")));
	public static final Supplier<BodyPartType> PIGLIN_BRUTE_LEFT_ARM = BODY_PARTS.register("piglin_brute_left_arm", () -> new BodyPartType(() -> new PiglinModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.PIGLIN_BRUTE)).leftArm, new ResourceLocation("textures/entity/piglin/piglin_brute.png")));
	public static final Supplier<BodyPartType> PIGLIN_BRUTE_RIGHT_ARM = BODY_PARTS.register("piglin_brute_right_arm", () -> new BodyPartType(() -> new PiglinModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.PIGLIN_BRUTE)).rightArm, new ResourceLocation("textures/entity/piglin/piglin_brute.png")));
	public static final Supplier<BodyPartType> PIGLIN_BRUTE_LEFT_LEG = BODY_PARTS.register("piglin_brute_left_leg", () -> new BodyPartType(() -> new PiglinModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.PIGLIN_BRUTE)).leftLeg, new ResourceLocation("textures/entity/piglin/piglin_brute.png")));
	public static final Supplier<BodyPartType> PIGLIN_BRUTE_RIGHT_LEG = BODY_PARTS.register("piglin_brute_right_leg", () -> new BodyPartType(() -> new PiglinModel<>(Minecraft.getInstance().getEntityModels().bakeLayer(ModelLayers.PIGLIN_BRUTE)).rightLeg, new ResourceLocation("textures/entity/piglin/piglin_brute.png")));
}
