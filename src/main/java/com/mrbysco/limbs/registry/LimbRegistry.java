package com.mrbysco.limbs.registry;

import com.mrbysco.limbs.Reference;
import com.mrbysco.limbs.registry.helper.LimbRegHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.ArrayList;
import java.util.List;

public class LimbRegistry {
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Reference.MOD_ID);
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Reference.MOD_ID);

	public static final List<LimbRegHelper> REGISTERED_LIMBS = new ArrayList<>();

	public static final LimbRegHelper SKELETON_LIMBS = register(new LimbRegHelper("skeleton", () -> EntityType.SKELETON));
	public static final LimbRegHelper STRAY_LIMBS = register(new LimbRegHelper("stray", () -> EntityType.STRAY));
	public static final LimbRegHelper WITHER_SKELETON_LIMBS = register(new LimbRegHelper("wither_skeleton", () -> EntityType.WITHER_SKELETON));
	public static final LimbRegHelper ZOMBIE_LIMBS = register(new LimbRegHelper("zombie", () -> EntityType.ZOMBIE));
	public static final LimbRegHelper HUSK_LIMBS = register(new LimbRegHelper("husk", () -> EntityType.HUSK));
	public static final LimbRegHelper DROWNED_LIMBS = register(new LimbRegHelper("drowned", () -> EntityType.DROWNED));
	public static final LimbRegHelper ENDERMAN_LIMBS = register(new LimbRegHelper("enderman", () -> EntityType.ENDERMAN));
	public static final LimbRegHelper PIGLIN_LIMBS = register(new LimbRegHelper("piglin", () -> EntityType.PIGLIN));
	public static final LimbRegHelper PIGLIN_BRUTE_LIMBS = register(new LimbRegHelper("piglin_brute", () -> EntityType.PIGLIN_BRUTE));
	public static final LimbRegHelper ZOMBIFIED_PIGLIN_LIMBS = register(new LimbRegHelper("zombified_piglin", () -> EntityType.ZOMBIFIED_PIGLIN));

	public static LimbRegHelper register(LimbRegHelper limb) {
		REGISTERED_LIMBS.add(limb);
		return limb;
	}

	public static final RegistryObject<CreativeModeTab> LIMB_TAB = CREATIVE_MODE_TABS.register("tab", () -> CreativeModeTab.builder()
			.icon(() -> new ItemStack(LimbRegistry.DROWNED_LIMBS.getHead()))
			.withTabsBefore(CreativeModeTabs.SPAWN_EGGS)
			.title(Component.translatable("itemGroup.limbs.tab"))
			.displayItems((displayParameters, output) -> {
				List<ItemStack> stacks = LimbRegistry.ITEMS.getEntries().stream().map(reg -> new ItemStack(reg.get())).toList();
				output.acceptAll(stacks);
			}).build());
}