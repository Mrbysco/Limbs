package com.mrbysco.limbs.datagen;

import com.mrbysco.limbs.Reference;
import com.mrbysco.limbs.lootmodifiers.LimbDropsModifier;
import com.mrbysco.limbs.registry.LimbRegistry;
import com.mrbysco.limbs.registry.helper.LimbRegHelper;
import com.mrbysco.limbs.util.LimbTags;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemKilledByPlayerCondition;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile.UncheckedModelFile;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class LimbDataGen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		ExistingFileHelper helper = event.getExistingFileHelper();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

		if (event.includeServer()) {
			LimbBlockTags limbBlockTagProvider;
			generator.addProvider(event.includeServer(), limbBlockTagProvider = new LimbBlockTags(packOutput, lookupProvider, helper));
			generator.addProvider(event.includeServer(), new LimbItemTags(packOutput, lookupProvider, limbBlockTagProvider.contentsGetter(), helper));
			generator.addProvider(event.includeServer(), new LimbEntityTags(packOutput, lookupProvider, helper));
			generator.addProvider(event.includeServer(), new LimbLootProvider(packOutput));
		}
		if (event.includeClient()) {
			generator.addProvider(event.includeClient(), new Language(packOutput));
			generator.addProvider(event.includeClient(), new ItemModels(packOutput, helper));
		}
	}

	private static class Language extends LanguageProvider {
		public Language(PackOutput packOutput) {
			super(packOutput, Reference.MOD_ID, "en_us");
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
		}
	}

	private static class ItemModels extends ItemModelProvider {
		public ItemModels(PackOutput packOutput, ExistingFileHelper helper) {
			super(packOutput, Reference.MOD_ID, helper);
		}

		@Override
		protected void registerModels() {
			for (RegistryObject<Item> limbObject : LimbRegistry.ITEMS.getEntries()) {
				makeLimb(limbObject.getId());
			}
		}

		private void makeLimb(ResourceLocation location) {
			getBuilder(location.getPath())
					.parent(new UncheckedModelFile(mcLoc("item/template_skull")))
					.transforms().transform(ItemDisplayContext.THIRD_PERSON_RIGHT_HAND)
					.rotation(45, 45, 0)
					.translation(0, 0, 5)
					.scale(0.5F, 0.5F, 0.5F).end()
					.transform(ItemDisplayContext.THIRD_PERSON_LEFT_HAND)
					.rotation(45, 45, 0)
					.translation(5, 5, 0)
					.scale(0.5F, 0.5F, 0.5F).end()
					.transform(ItemDisplayContext.FIRST_PERSON_LEFT_HAND)
					.rotation(0, 0, 0)
					.translation(5, 5, 5)
					.scale(0.5F, 0.5F, 0.5F).end();
		}
	}

	public static class LimbBlockTags extends BlockTagsProvider {

		public LimbBlockTags(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper helper) {
			super(output, lookupProvider, Reference.MOD_ID, helper);
		}

		@Override
		protected void addTags(HolderLookup.Provider provider) {

		}
	}

	public static class LimbItemTags extends ItemTagsProvider {

		public LimbItemTags(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTagProvider, ExistingFileHelper helper) {
			super(packOutput, lookupProvider, blockTagProvider, Reference.MOD_ID, helper);
		}

		@Override
		protected void addTags(HolderLookup.Provider provider) {
			this.tag(LimbTags.HEAD).addTag(LimbTags.HEADS);

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

	public static class LimbEntityTags extends EntityTypeTagsProvider {
		public static final TagKey<EntityType<?>> LIMB_ABLE = create(new ResourceLocation(Reference.MOD_ID, "limb_able"));

		public LimbEntityTags(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, ExistingFileHelper existingFileHelper) {
			super(packOutput, lookupProvider, Reference.MOD_ID, existingFileHelper);
		}

		@Override
		protected void addTags(HolderLookup.Provider provider) {
			this.tag(LIMB_ABLE).add(EntityType.SKELETON, EntityType.STRAY, EntityType.WITHER_SKELETON, EntityType.ZOMBIE,
					EntityType.HUSK, EntityType.DROWNED, EntityType.ENDERMAN, EntityType.PIGLIN, EntityType.PIGLIN_BRUTE, EntityType.ZOMBIFIED_PIGLIN);
		}

		private static TagKey<EntityType<?>> create(ResourceLocation location) {
			return TagKey.create(Registries.ENTITY_TYPE, location);
		}
	}

	public static class LimbLootProvider extends GlobalLootModifierProvider {
		public LimbLootProvider(PackOutput packOutput) {
			super(packOutput, Reference.MOD_ID);
		}

		@Override
		protected void start() {
			this.add("limb_drops", new LimbDropsModifier(
					new LootItemCondition[]{
							LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, EntityPredicate.Builder.entity().of(LimbEntityTags.LIMB_ABLE)).build(),
							LootItemKilledByPlayerCondition.killedByPlayer().build()
					}));
		}
	}
}