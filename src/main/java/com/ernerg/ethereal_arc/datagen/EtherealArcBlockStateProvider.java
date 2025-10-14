package com.ernerg.ethereal_arc.datagen;

import com.ernerg.ethereal_arc.common.EtherealArc;
import com.ernerg.ethereal_arc.common.registration.AllBlocks;

import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
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
		blockWithItem(AllBlocks.RAW_IRIDIUM_BLOCK);
		blockWithItem(AllBlocks.ARCYNITE_BLOCK);
		blockWithItem(AllBlocks.BUDDING_ARCYNITE);
		// Only blockstates for those 4
		crystalLike(AllBlocks.ARCYNITE_CLUSTER, "arcynite_cluster");
		crystalLike(AllBlocks.LARGE_ARCYNITE_BUD, "large_arcynite_bud");
		crystalLike(AllBlocks.MEDIUM_ARCYNITE_BUD, "medium_arcynite_bud");
		crystalLike(AllBlocks.SMALL_ARCYNITE_BUD, "small_arcynite_bud");
		
		blockWithItem(AllBlocks.HOSTILITY_ANNIHILATOR);

		simpleBlock(
			AllBlocks.ETHERIC_CATALYST.get(),
			models().getExistingFile(modLoc("block/etheric_catalyst"))
		); 
	}

	private void blockWithItem(DeferredBlock<?> deferredBlock) {
		simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
	}
	
	private void crystalLike(DeferredBlock<?> deferredBlock, String name) {
		getVariantBuilder(deferredBlock.get()).forAllStates(state -> {
			Direction dir = state.getValue(BlockStateProperties.FACING);
			int xRot = switch (dir) {
				case DOWN -> 180;
				case UP -> 0;
				case NORTH, SOUTH, EAST, WEST -> 90;
			};
			int yRot = switch (dir) {
				case SOUTH -> 180;
				case WEST -> 270;
				case EAST -> 90;
				default -> 0;
			};

			ModelFile model = new ModelFile.UncheckedModelFile(modLoc("block/" + name));

			return ConfiguredModel.builder()
				.modelFile(model)
				.rotationX(xRot)
				.rotationY(yRot)
				.build();
		});
	}
}
