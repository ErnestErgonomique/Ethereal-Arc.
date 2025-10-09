package com.ernerg.ethereal_arc.common.block;

import javax.annotation.Nullable;

import com.ernerg.ethereal_arc.common.entitiy.HostilityAnnihilatorBlockEntity;
import com.mojang.serialization.MapCodec;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class HostilityAnnihilatorBlock extends BaseEntityBlock {

	public static final VoxelShape SHAPE = Block.box(2, 2, 2, 14, 14, 14);
	public static final MapCodec<HostilityAnnihilatorBlock> CODEC = simpleCodec(HostilityAnnihilatorBlock::new);

	public HostilityAnnihilatorBlock(Properties properties) {
		super(properties);
	}

	@Override
	protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return SHAPE;
	}

	@Override
	protected MapCodec<? extends BaseEntityBlock> codec() {
		return CODEC;
	}

	@Override
	protected RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new HostilityAnnihilatorBlockEntity(pos, state);
	}

	@Override
	protected void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
		if (state.getBlock() != newState.getBlock()) {
			if (level.getBlockEntity(pos) instanceof HostilityAnnihilatorBlockEntity hostilityAnnihilatorBlockEntity) {
				hostilityAnnihilatorBlockEntity.drops();
				level.updateNeighbourForOutputSignal(pos, this);
			}
		}
		super.onRemove(state, level, pos, newState, movedByPiston);
	}

	@Override
	protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
			Player player, InteractionHand hand, BlockHitResult hitResult) {
		if (level.getBlockEntity(pos) instanceof HostilityAnnihilatorBlockEntity hostilityAnnihilatorBlockEntity) {
			if (hostilityAnnihilatorBlockEntity.inventory.getStackInSlot(0).isEmpty() && !stack.isEmpty()) {
				hostilityAnnihilatorBlockEntity.inventory.insertItem(0, stack.copy(), false);
				stack.shrink(1);
				level.playSound(player, pos, SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1f, 2f);
			} else if (stack.isEmpty()) {
				ItemStack stackInDevice = hostilityAnnihilatorBlockEntity.inventory.extractItem(0, 1, false);
				player.setItemInHand(InteractionHand.MAIN_HAND, stackInDevice);
				hostilityAnnihilatorBlockEntity.clearContents();
				level.playSound(player, pos, SoundEvents.BELL_RESONATE, SoundSource.BLOCKS, 1f, 2f);
			}
			
		}

		return ItemInteractionResult.SUCCESS;
	}
}
