package com.ernerg.ethereal_arc.datagen;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import com.ernerg.ethereal_arc.common.EtherealArc;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = EtherealArc.MOD_ID)
public class EtherealArcDataGenerators {

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator generator = event.getGenerator();
		PackOutput packOutput = generator.getPackOutput();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
		CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

		generator.addProvider(event.includeServer(), new LootTableProvider(packOutput,
			Collections.emptySet(),
			List.of(new LootTableProvider
				.SubProviderEntry(EtherealArcBlockLootTableProvider::new, LootContextParamSets.BLOCK)),
			lookupProvider
		));

		BlockTagsProvider blockTagsProvider = new
			EtherealArcBlockTagProvider(packOutput, lookupProvider, existingFileHelper);
		
		generator.addProvider(event.includeServer(), blockTagsProvider);
		generator.addProvider(event.includeServer(), new EtherealArcItemTagProvider(packOutput, lookupProvider,
			blockTagsProvider.contentsGetter(), existingFileHelper));

		generator.addProvider(event.includeClient(), new EtherealArcItemModelProvider(packOutput, existingFileHelper));
		generator.addProvider(event.includeClient(), new EtherealArcBlockStateProvider(packOutput, existingFileHelper));
		
		generator.addProvider(event.includeServer(), new EtherealArcRecipeProvider(packOutput, lookupProvider));

		generator.addProvider(event.includeServer(), new EtherealArcDatapackProvider(packOutput, lookupProvider));
	}
}
