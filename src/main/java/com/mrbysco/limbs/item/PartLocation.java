package com.mrbysco.limbs.item;

import net.minecraft.world.entity.HumanoidArm;

import javax.annotation.Nullable;

public enum PartLocation {
	HEAD(false, null),
	TORSO(false, null),
	LEFT_ARM(true, HumanoidArm.LEFT),
	RIGHT_ARM(true, HumanoidArm.RIGHT),
	LEFT_LEG(false, null),
	RIGHT_LEG(false, null);

	private final boolean isArm;
	@Nullable
	private final HumanoidArm humanoidArm;

	PartLocation(boolean isArm, @Nullable HumanoidArm humanoidArm) {
		this.isArm = isArm;
		this.humanoidArm = humanoidArm;
	}

	public boolean isArm() {
		return isArm;
	}

	@Nullable
	public HumanoidArm getHumanoidArm() {
		return humanoidArm;
	}
}
