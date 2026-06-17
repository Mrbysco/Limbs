package com.mrbysco.limbs.datagen.client;

import com.mrbysco.limbs.Limbs;
import com.mrbysco.limbs.registry.LimbRegistry;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class LimbLanguageProvider extends LanguageProvider {
	public LimbLanguageProvider(PackOutput packOutput) {
		super(packOutput, Limbs.MOD_ID, "en_us");
	}

	@Override
	protected void addTranslations() {
		this.add("itemGroup.limbs.tab", "Limbs");

		this.add("curios.identifier.left_arm", "Left Arm");
		this.add("curios.identifier.right_arm", "Right Arm");
		this.add("curios.identifier.torso", "Torso");
		this.add("curios.identifier.left_leg", "Left Leg");
		this.add("curios.identifier.right_leg", "Right Leg");

		this.add(LimbRegistry.SKELETON_LIMBS.getHead(), "Skeleton Head");
		this.add(LimbRegistry.SKELETON_LIMBS.getTorso(), "Skeleton Torso");
		this.add(LimbRegistry.SKELETON_LIMBS.getLeftArm(), "Skeleton Left Arm");
		this.add(LimbRegistry.SKELETON_LIMBS.getRightArm(), "Skeleton Right Arm");
		this.add(LimbRegistry.SKELETON_LIMBS.getLeftLeg(), "Skeleton Left Leg");
		this.add(LimbRegistry.SKELETON_LIMBS.getRightLeg(), "Skeleton Right Leg");

		this.add(LimbRegistry.STRAY_LIMBS.getHead(), "Stray Head");
		this.add(LimbRegistry.STRAY_LIMBS.getTorso(), "Stray Torso");
		this.add(LimbRegistry.STRAY_LIMBS.getLeftArm(), "Stray Left Arm");
		this.add(LimbRegistry.STRAY_LIMBS.getRightArm(), "Stray Right Arm");
		this.add(LimbRegistry.STRAY_LIMBS.getLeftLeg(), "Stray Left Leg");
		this.add(LimbRegistry.STRAY_LIMBS.getRightLeg(), "Stray Right Leg");

		this.add(LimbRegistry.WITHER_SKELETON_LIMBS.getHead(), "Wither Skeleton Head");
		this.add(LimbRegistry.WITHER_SKELETON_LIMBS.getTorso(), "Wither Skeleton Torso");
		this.add(LimbRegistry.WITHER_SKELETON_LIMBS.getLeftArm(), "Wither Skeleton Left Arm");
		this.add(LimbRegistry.WITHER_SKELETON_LIMBS.getRightArm(), "Wither Skeleton Right Arm");
		this.add(LimbRegistry.WITHER_SKELETON_LIMBS.getLeftLeg(), "Wither Skeleton Left Leg");
		this.add(LimbRegistry.WITHER_SKELETON_LIMBS.getRightLeg(), "Wither Skeleton Right Leg");

		this.add(LimbRegistry.ZOMBIE_LIMBS.getHead(), "Zombie Head");
		this.add(LimbRegistry.ZOMBIE_LIMBS.getTorso(), "Zombie Torso");
		this.add(LimbRegistry.ZOMBIE_LIMBS.getLeftArm(), "Zombie Left Arm");
		this.add(LimbRegistry.ZOMBIE_LIMBS.getRightArm(), "Zombie Right Arm");
		this.add(LimbRegistry.ZOMBIE_LIMBS.getLeftLeg(), "Zombie Left Leg");
		this.add(LimbRegistry.ZOMBIE_LIMBS.getRightLeg(), "Zombie Right Leg");

		this.add(LimbRegistry.HUSK_LIMBS.getHead(), "Husk Head");
		this.add(LimbRegistry.HUSK_LIMBS.getTorso(), "Husk Torso");
		this.add(LimbRegistry.HUSK_LIMBS.getLeftArm(), "Husk Left Arm");
		this.add(LimbRegistry.HUSK_LIMBS.getRightArm(), "Husk Right Arm");
		this.add(LimbRegistry.HUSK_LIMBS.getLeftLeg(), "Husk Left Leg");
		this.add(LimbRegistry.HUSK_LIMBS.getRightLeg(), "Husk Right Leg");

		this.add(LimbRegistry.DROWNED_LIMBS.getHead(), "Drowned Head");
		this.add(LimbRegistry.DROWNED_LIMBS.getTorso(), "Drowned Torso");
		this.add(LimbRegistry.DROWNED_LIMBS.getLeftArm(), "Drowned Left Arm");
		this.add(LimbRegistry.DROWNED_LIMBS.getRightArm(), "Drowned Right Arm");
		this.add(LimbRegistry.DROWNED_LIMBS.getLeftLeg(), "Drowned Left Leg");
		this.add(LimbRegistry.DROWNED_LIMBS.getRightLeg(), "Drowned Right Leg");

		this.add(LimbRegistry.ENDERMAN_LIMBS.getHead(), "Enderman Head");
		this.add(LimbRegistry.ENDERMAN_LIMBS.getTorso(), "Enderman Torso");
		this.add(LimbRegistry.ENDERMAN_LIMBS.getLeftArm(), "Enderman Left Arm");
		this.add(LimbRegistry.ENDERMAN_LIMBS.getRightArm(), "Enderman Right Arm");
		this.add(LimbRegistry.ENDERMAN_LIMBS.getLeftLeg(), "Enderman Left Leg");
		this.add(LimbRegistry.ENDERMAN_LIMBS.getRightLeg(), "Enderman Right Leg");

		this.add(LimbRegistry.PIGLIN_LIMBS.getHead(), "Piglin Head");
		this.add(LimbRegistry.PIGLIN_LIMBS.getTorso(), "Piglin Torso");
		this.add(LimbRegistry.PIGLIN_LIMBS.getLeftArm(), "Piglin Left Arm");
		this.add(LimbRegistry.PIGLIN_LIMBS.getRightArm(), "Piglin Right Arm");
		this.add(LimbRegistry.PIGLIN_LIMBS.getLeftLeg(), "Piglin Left Leg");
		this.add(LimbRegistry.PIGLIN_LIMBS.getRightLeg(), "Piglin Right Leg");

		this.add(LimbRegistry.ZOMBIFIED_PIGLIN_LIMBS.getHead(), "Zombified Piglin Head");
		this.add(LimbRegistry.ZOMBIFIED_PIGLIN_LIMBS.getTorso(), "Zombified Piglin Torso");
		this.add(LimbRegistry.ZOMBIFIED_PIGLIN_LIMBS.getLeftArm(), "Zombified Piglin Left Arm");
		this.add(LimbRegistry.ZOMBIFIED_PIGLIN_LIMBS.getRightArm(), "Zombified Piglin Right Arm");
		this.add(LimbRegistry.ZOMBIFIED_PIGLIN_LIMBS.getLeftLeg(), "Zombified Piglin Left Leg");
		this.add(LimbRegistry.ZOMBIFIED_PIGLIN_LIMBS.getRightLeg(), "Zombified Piglin Right Leg");

		this.add(LimbRegistry.PIGLIN_BRUTE_LIMBS.getHead(), "Piglin Brute Head");
		this.add(LimbRegistry.PIGLIN_BRUTE_LIMBS.getTorso(), "Piglin Brute Torso");
		this.add(LimbRegistry.PIGLIN_BRUTE_LIMBS.getLeftArm(), "Piglin Brute Left Arm");
		this.add(LimbRegistry.PIGLIN_BRUTE_LIMBS.getRightArm(), "Piglin Brute Right Arm");
		this.add(LimbRegistry.PIGLIN_BRUTE_LIMBS.getLeftLeg(), "Piglin Brute Left Leg");
		this.add(LimbRegistry.PIGLIN_BRUTE_LIMBS.getRightLeg(), "Piglin Brute Right Leg");

		addConfig("title", "Limbs Config", null);
		addConfig("general", "General", "General settings");
		addConfig("limbDropChance", "Limb Drop Chance", "The drop chance of limbs when a compatible mob is killed (Default: 0.01)");
		addConfig("dropHeads", "Drop Heads", "If true, mobs have a chance of dropping their head [Should be disabled when using the Heads mod] (Default: true)");

	}

	/**
	 * Add the translation for a config entry
	 *
	 * @param path        The path of the config entry
	 * @param name        The name of the config entry
	 * @param description The description of the config entry (optional in case of targeting "title" or similar entries that have no tooltip)
	 */
	private void addConfig(String path, String name, @org.jetbrains.annotations.Nullable String description) {
		this.add("limbs.configuration." + path, name);
		if (description != null && !description.isEmpty())
			this.add("limbs.configuration." + path + ".tooltip", description);
	}
}
