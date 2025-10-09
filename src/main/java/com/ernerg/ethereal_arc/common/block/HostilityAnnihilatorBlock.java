package com.ernerg.ethereal_arc.common.block;

import com.ernerg.ethereal_arc.common.system.HostilityAnnihilatorManager;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;

public class HostilityAnnihilatorBlock extends Block {

	public HostilityAnnihilatorBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
		if(!level.isClientSide) {
			HostilityAnnihilatorManager.addBlock(pos);
		}
		super.onPlace(state, level, pos, oldState, movedByPiston);
	}

	@Override
	protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
		if(!level.isClientSide) {
			HostilityAnnihilatorManager.removeBlock(pos);
		}
		super.onPlace(state, level, pos, oldState, movedByPiston);
	}
}
