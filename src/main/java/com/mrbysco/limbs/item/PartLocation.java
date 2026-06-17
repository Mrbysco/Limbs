package com.mrbysco.limbs.item;

import net.minecraft.util.StringRepresentable;
import net.minecraft.world.entity.HumanoidArm;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public enum PartLocation implements StringRepresentable {
	HEAD(false, null),
	TORSO(false, null),
	LEFT_ARM(true, HumanoidArm.LEFT),
	RIGHT_ARM(true, HumanoidArm.RIGHT),
	LEFT_LEG(false, null),
	RIGHT_LEG(false, null);

	public static final StringRepresentable.EnumCodec<PartLocation> CODEC =
			StringRepresentable.fromEnum(PartLocation::values);

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

	@Nonnull
	@Override
	public String getSerializedName() {
		return this.name().toLowerCase();
	}
}
