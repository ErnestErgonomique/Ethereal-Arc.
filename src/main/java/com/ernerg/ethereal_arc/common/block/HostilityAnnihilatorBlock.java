package com.ernerg.ethereal_arc.common.block;

import com.ernerg.ethereal_arc.common.blockentity.HostilityAnnihilatorBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

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

	@Override
	protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
			Player player, InteractionHand hand, BlockHitResult hitResult) {
		if (level.getBlockEntity(pos) instanceof HostilityAnnihilatorBlockEntity hostilityAnnihilatorBlockEntity) {
			if (!level.isClientSide()) {
				((ServerPlayer) player).openMenu(
					new SimpleMenuProvider(hostilityAnnihilatorBlockEntity, Component.literal("Hostility Annihilator")),
					pos
				);
			}
		}

		return ItemInteractionResult.SUCCESS;
	}
}
