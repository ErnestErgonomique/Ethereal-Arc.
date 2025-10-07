package com.ernerg.ethereal_arc.datagen;

import com.ernerg.ethereal_arc.common.EtherealArc;
import com.ernerg.ethereal_arc.common.registration.AllBlocks;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class EtherealArcBlockStateProvider extends BlockStateProvider {

	public EtherealArcBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
		super(output, EtherealArc.MOD_ID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		blockWithItem(AllBlocks.IRIDIUM_ORE);
		blockWithItem(AllBlocks.DEEPSLATE_IRIDIUM_ORE);
		blockWithItem(AllBlocks.IRIDIUM_BLOCK);
	}

	private void blockWithItem(DeferredBlock<?> deferredBlock) {
		simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
	}
	
}
