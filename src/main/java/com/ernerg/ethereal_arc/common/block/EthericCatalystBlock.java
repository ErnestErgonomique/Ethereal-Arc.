package com.ernerg.ethereal_arc.common.block;

import com.ernerg.ethereal_arc.common.blockentity.EthericCatalystBlockEntity;
import com.ernerg.ethereal_arc.common.blockentity.HostilityAnnihilatorBlockEntity;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class EthericCatalystBlock extends Block implements EntityBlock {

	private static final VoxelShape SHAPE = Shapes.or(
		// Center part
		// Low base
		Block.box(0, 0, 0, 16, 2, 16),
		// High base
		Block.box(2, 2, 2, 14, 4, 14),
		// Core
		Block.box(4, 4, 4, 12, 7, 12),
		// Cables
		Block.box(3, 7, 3, 13, 13, 13),
		// Top casing
		Block.box(5, 13, 5, 11, 16, 11),
		// Side rooms
		// Purple covering 1
		Block.box(0, 5, 0, 6, 15, 6),
		// Purple detail 1
		Block.box(0, 4, 0, 3, 5, 3),
		// Casing bottom 1
		Block.box(1, 3, 1, 5, 5, 5),
		// Purple covering 2
		Block.box(10, 5, 0, 16, 15, 6),
		// Purple detail 2
		Block.box(13, 4, 0, 16, 5, 3),
		// Casing bottom 2
		Block.box(11, 3, 1, 15, 5, 5),
		// Purple covering 3
		Block.box(0, 5, 10, 6, 15, 16),
		// Purple detail 3
		Block.box(0, 4, 13, 3, 5, 16),
		// Casing bottom 3
		Block.box(1, 3, 11, 5, 5, 15),
		// Purple covering 4
		Block.box(10, 5, 10, 16, 15, 16),
		// Purple detail 4
		Block.box(13, 4, 13, 16, 5, 16),
		// Casing bottom 4
		Block.box(11, 3, 11, 15, 5, 15)
	);

	public EthericCatalystBlock(Properties properties) {
		super(properties);
	}

	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new EthericCatalystBlockEntity(pos, state);
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
			Player player, InteractionHand hand, BlockHitResult hitResult) {
		if (!level.isClientSide) {
			BlockEntity be = level.getBlockEntity(pos);
			if (be instanceof EthericCatalystBlockEntity blockEntity) {
				if (player instanceof ServerPlayer serverPlayer) {
					serverPlayer.openMenu(blockEntity, buf -> buf.writeBlockPos(pos));
				}
			}
		}

		return ItemInteractionResult.sidedSuccess(level.isClientSide());
	}
}
