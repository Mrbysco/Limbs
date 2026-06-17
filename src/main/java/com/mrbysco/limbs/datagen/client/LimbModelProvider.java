package com.mrbysco.limbs.datagen.client;

import com.mrbysco.limbs.Limbs;
import com.mrbysco.limbs.client.PartSpecialRenderer;
import com.mrbysco.limbs.item.PartItem;
import com.mrbysco.limbs.item.PartLocation;
import com.mrbysco.limbs.registry.LimbRegistry;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;

public class LimbModelProvider extends ModelProvider {
	public LimbModelProvider(PackOutput output) {
		super(output, Limbs.MOD_ID);
	}

	@Override
	protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
		for (DeferredHolder<Item, ? extends Item> limbObject : LimbRegistry.ITEMS.getEntries()) {
			if (limbObject.get() instanceof PartItem partItem)
				makeLimb(itemModels, partItem);
		}
	}

	private void makeLimb(ItemModelGenerators itemModels, PartItem partItem) {
		Identifier template = Identifier.fromNamespaceAndPath("limbs", "template_limb");
		final Identifier partRegistry = partItem.getPartRegistry();
		PartLocation partLocation = partItem.getPartLocation();
		itemModels.itemModelOutput.accept(partItem, ItemModelUtils.specialModel(template, new PartSpecialRenderer.Unbaked(partRegistry, partLocation)));
	}
}
