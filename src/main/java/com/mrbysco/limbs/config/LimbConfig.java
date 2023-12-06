package com.mrbysco.limbs.config;

import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.DoubleValue;
import org.apache.commons.lang3.tuple.Pair;

public class LimbConfig {

	public static class Common {
		public final DoubleValue limbDropChance;
		public final ModConfigSpec.BooleanValue dropHeads;

		Common(ModConfigSpec.Builder builder) {
			//General settings
			builder.comment("General settings")
					.push("general");

			limbDropChance = builder
					.comment("The drop chance of limbs when a compatible mob is killed (Default: 0.01)")
					.defineInRange("limbDropChance", 0.01, 0, 1.0);

			dropHeads = builder
					.comment("If true, mobs have a chance of dropping their head [Should be disabled when using the Heads mod] (Default: true)")
					.define("dropHeads", true);

			builder.pop();
		}
	}

	public static final ModConfigSpec commonSpec;
	public static final Common COMMON;

	static {
		final Pair<Common, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(Common::new);
		commonSpec = specPair.getRight();
		COMMON = specPair.getLeft();
	}
}
