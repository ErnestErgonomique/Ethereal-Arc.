package com.ernerg.ethereal_arc.common.system;

import java.util.HashSet;
import java.util.Set;

import net.minecraft.core.BlockPos;

public class HostilityAnnihilatorManager {
	
	private static final Set<BlockPos> ACTIVE_BLOCKS = new HashSet<>();

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
