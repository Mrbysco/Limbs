package com.mrbysco.limbs.datagen.data;

import com.mrbysco.limbs.Limbs;
import com.mrbysco.limbs.registry.LimbRegistry;
import com.mrbysco.limbs.registry.helper.LimbRegHelper;
import com.mrbysco.limbs.util.LimbTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.ItemTags;
import net.neoforged.neoforge.common.data.ItemTagsProvider;

import java.util.concurrent.CompletableFuture;

public class LimbItemTags extends ItemTagsProvider {

	public LimbItemTags(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(packOutput, lookupProvider, Limbs.MOD_ID);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		this.tag(LimbTags.HEAD).addOptionalTag(ItemTags.SKULLS);

		makeLimbTags(LimbRegistry.SKELETON_LIMBS);
		makeLimbTags(LimbRegistry.STRAY_LIMBS);
		makeLimbTags(LimbRegistry.WITHER_SKELETON_LIMBS);
		makeLimbTags(LimbRegistry.ZOMBIE_LIMBS);
		makeLimbTags(LimbRegistry.HUSK_LIMBS);
		makeLimbTags(LimbRegistry.DROWNED_LIMBS);
		makeLimbTags(LimbRegistry.ENDERMAN_LIMBS);
		makeLimbTags(LimbRegistry.PIGLIN_LIMBS);
		makeLimbTags(LimbRegistry.ZOMBIFIED_PIGLIN_LIMBS);
		makeLimbTags(LimbRegistry.PIGLIN_BRUTE_LIMBS);
	}


	private void makeLimbTags(LimbRegHelper limbs) {
		this.tag(limbs.getTag()).add(limbs.getHead(), limbs.getTorso(), limbs.getLeftArm(), limbs.getRightArm(), limbs.getLeftLeg(), limbs.getRightLeg());
		this.tag(LimbTags.HEAD).add(limbs.getHead());
		this.tag(LimbTags.TORSO).add(limbs.getTorso());
		this.tag(LimbTags.LEFT_ARM).add(limbs.getLeftArm());
		this.tag(LimbTags.RIGHT_ARM).add(limbs.getRightArm());
		this.tag(LimbTags.LEFT_LEG).add(limbs.getLeftLeg());
		this.tag(LimbTags.RIGHT_LEG).add(limbs.getRightLeg());
	}
}