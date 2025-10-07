package com.ernerg.ethereal_arc.datagen;

import java.util.Set;

import com.ernerg.ethereal_arc.common.registration.AllBlocks;
import com.ernerg.ethereal_arc.common.registration.AllItems;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

public class EtherealArcBlockLootTableProvider extends BlockLootSubProvider {

	protected EtherealArcBlockLootTableProvider(HolderLookup.Provider registries) {
		super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
		
	}

	@Override
	protected void generate() {
		add(AllBlocks.IRIDIUM_ORE.get(),
			block -> createOreDrop(AllBlocks.IRIDIUM_ORE.get(), AllItems.RAW_IRIDIUM.get())
		);
		add(AllBlocks.DEEPSLATE_IRIDIUM_ORE.get(),
			block -> createOreDrop(AllBlocks.DEEPSLATE_IRIDIUM_ORE.get(), AllItems.RAW_IRIDIUM.get())
		);
		dropSelf(AllBlocks.IRIDIUM_BLOCK.get());
	}

	@Override
	protected Iterable<Block> getKnownBlocks() {
		return AllBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
	}
}
