package com.mrbysco.limbs.datagen;

import com.mrbysco.limbs.datagen.client.LimbLanguageProvider;
import com.mrbysco.limbs.datagen.client.LimbModelProvider;
import com.mrbysco.limbs.datagen.data.LimbEntityTags;
import com.mrbysco.limbs.datagen.data.LimbItemTags;
import com.mrbysco.limbs.datagen.data.LimbLootProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.concurrent.CompletableFuture;

@EventBusSubscriber
public class LimbDataGen {
	@SubscribeEvent
	public static void gatherData(GatherDataEvent.Client event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

		generator.addProvider(true, new LimbItemTags(packOutput, lookupProvider));
		generator.addProvider(true, new LimbEntityTags(packOutput, lookupProvider));
		generator.addProvider(true, new LimbLootProvider(packOutput, lookupProvider));

		generator.addProvider(true, new LimbLanguageProvider(packOutput));
		generator.addProvider(true, new LimbModelProvider(packOutput));

	}
}