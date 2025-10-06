package com.ernerg.ethereal_arc.datagen;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import com.ernerg.ethereal_arc.common.EtherealArc;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class EtherealArcItemTagProvider extends ItemTagsProvider {

	
	public EtherealArcItemTagProvider(PackOutput output, CompletableFuture<Provider> lookupProvider,
		CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
		super(output, lookupProvider, blockTags, EtherealArc.MOD_ID, existingFileHelper);
	}

	@Override
	protected void addTags(Provider provider) {
		
	}
	
}
