package com.ernerg.ethereal_arc.common.block;

import com.ernerg.ethereal_arc.common.registration.AllBlocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BuddingAmethystBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;

public class BuddingArcyniteBlock extends BuddingAmethystBlock {

	private static final Direction[] DIRECTIONS = Direction.values();

	public BuddingArcyniteBlock(Properties properties) {
		super(properties);
	}
	
	@Override
	protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
		if (random.nextInt(5) == 0) {
            Direction direction = DIRECTIONS[random.nextInt(DIRECTIONS.length)];
            BlockPos blockpos = pos.relative(direction);
            BlockState blockstate = level.getBlockState(blockpos);
            Block block = null;
            if (canClusterGrowAtState(blockstate)) {
                block = AllBlocks.SMALL_ARCYNITE_BUD.get();
            } else if (blockstate.is(AllBlocks.SMALL_ARCYNITE_BUD.get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = AllBlocks.MEDIUM_ARCYNITE_BUD.get();
            } else if (blockstate.is(AllBlocks.MEDIUM_ARCYNITE_BUD.get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = AllBlocks.LARGE_ARCYNITE_BUD.get();
            } else if (blockstate.is(AllBlocks.LARGE_ARCYNITE_BUD.get()) && blockstate.getValue(AmethystClusterBlock.FACING) == direction) {
                block = AllBlocks.ARCYNITE_CLUSTER.get();
            }

            if (block != null) {
                BlockState blockstate1 = block.defaultBlockState()
                    .setValue(AmethystClusterBlock.FACING, direction)
                    .setValue(AmethystClusterBlock.WATERLOGGED, Boolean.valueOf(blockstate.getFluidState().getType() == Fluids.WATER));
                level.setBlockAndUpdate(blockpos, blockstate1);
            }
        }
	}
}
