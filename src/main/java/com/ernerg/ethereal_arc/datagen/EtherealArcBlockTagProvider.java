package com.ernerg.ethereal_arc.datagen;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import com.ernerg.ethereal_arc.common.EtherealArc;
import com.ernerg.ethereal_arc.common.registration.AllBlocks;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class EtherealArcBlockTagProvider extends BlockTagsProvider {

	public EtherealArcBlockTagProvider(PackOutput output, CompletableFuture<Provider> lookupProvider, 
			@Nullable ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, EtherealArc.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		tag(BlockTags.MINEABLE_WITH_PICKAXE)
			.add(AllBlocks.IRIDIUM_ORE.get())
			.add(AllBlocks.DEEPSLATE_IRIDIUM_ORE.get())
			.add(AllBlocks.IRIDIUM_BLOCK.get())
			.add(AllBlocks.RAW_IRIDIUM_BLOCK.get());

		tag(BlockTags.NEEDS_IRON_TOOL)
			.add(AllBlocks.IRIDIUM_ORE.get())
			.add(AllBlocks.DEEPSLATE_IRIDIUM_ORE.get())
			.add(AllBlocks.IRIDIUM_BLOCK.get())
			.add(AllBlocks.RAW_IRIDIUM_BLOCK.get());
	}
	
}
