package com.ernerg.ethereal_arc.common.blockentity;

import java.util.HashSet;
import java.util.Set;

import com.ernerg.ethereal_arc.common.registration.AllBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class HostilityAnnihilatorBlockEntity extends BlockEntity {
	private static final Set<BlockPos> ACTIVE_BLOCKS = new HashSet<>();

	public HostilityAnnihilatorBlockEntity(BlockPos pos, BlockState blockState) {
		super(AllBlockEntities.HOSTILITY_ANNIHILATOR_BLOCK_ENTITY.get(), pos, blockState);
	}

	public static void addBlock(BlockPos pos) {
		ACTIVE_BLOCKS.add(pos);
	}

	public static void removeBlock(BlockPos pos) {
		ACTIVE_BLOCKS.remove(pos);
	}

	public static boolean isInSpawnZone(BlockPos pos) {
		for (BlockPos blockPos : ACTIVE_BLOCKS) {
			int dx = (pos.getX() / 16) - (blockPos.getX() / 16);
			int dz = (pos.getZ() / 16) - (blockPos.getZ() / 16);

			if (Math.abs(dx) <= 2 && Math.abs(dz) <= 2 ) {
				return true;
			}
		}
		return false;
	}
}
