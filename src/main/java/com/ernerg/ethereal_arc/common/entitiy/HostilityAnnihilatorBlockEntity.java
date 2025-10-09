package com.ernerg.ethereal_arc.common.entitiy;

import com.ernerg.ethereal_arc.common.registration.AllBlockEntities;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.Containers;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.items.ItemStackHandler;

public class HostilityAnnihilatorBlockEntity extends BlockEntity {

	public final ItemStackHandler inventory = new ItemStackHandler(1) {
		@Override
		protected int getStackLimit(int slot, ItemStack stack) {
			return 1;
		}

		@Override
		protected void onContentsChanged(int slot) {
			setChanged();
			if (!level.isClientSide()) {
				level.sendBlockUpdated(getBlockPos(), getBlockState(), getBlockState(), 3);
			}
		}
	};

	public HostilityAnnihilatorBlockEntity(BlockPos pos, BlockState blockState) {
		super(AllBlockEntities.HOSTILITY_ANNIHILATOR_BE.get(), pos, blockState);
	}

	public void clearContents() {
		inventory.setStackInSlot(0, ItemStack.EMPTY);
	}

	public void drops() {
		SimpleContainer inv = new SimpleContainer(inventory.getSlots());
		for (int i = 0; i < inventory.getSlots(); i++) {
			inv.setItem(i, inventory.getStackInSlot(i));
		}

		Containers.dropContents(this.level, this.worldPosition, inv);
	}

	@Override
	protected void saveAdditional(CompoundTag tag, Provider registries) {
		super.saveAdditional(tag, registries);
		tag.put("inventory", inventory.serializeNBT(registries));
	}

	@Override
	protected void loadAdditional(CompoundTag tag, Provider registries) {
		super.loadAdditional(tag, registries);
		inventory.deserializeNBT(registries, tag.getCompound("inventory"));
	}
}
