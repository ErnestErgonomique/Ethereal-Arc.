package com.ernerg.ethereal_arc.common.block;

import com.ernerg.ethereal_arc.common.blockentity.HostilityAnnihilatorBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class HostilityAnnihilatorBlock extends Block implements EntityBlock {

	public HostilityAnnihilatorBlock(Properties properties) {
		super(properties);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new HostilityAnnihilatorBlockEntity(pos, state);
	}

	@Override
	protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
		if(!level.isClientSide) {
			HostilityAnnihilatorBlockEntity.addBlock(pos);
		}
		super.onPlace(state, level, pos, oldState, movedByPiston);
	}

	@Override
	protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
		if(!level.isClientSide) {
			HostilityAnnihilatorBlockEntity.removeBlock(pos);
		}
		super.onPlace(state, level, pos, oldState, movedByPiston);
	}
}
