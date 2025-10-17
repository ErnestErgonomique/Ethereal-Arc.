package com.ernerg.ethereal_arc.common.blockentity;

import com.ernerg.ethereal_arc.common.menu.EthericCatalystMenu;
import com.ernerg.ethereal_arc.common.registration.AllBlockEntities;
import com.ernerg.ethereal_arc.common.registration.AllItems;

import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.ContainerHelper;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BaseContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class EthericCatalystBlockEntity extends BaseContainerBlockEntity {

	private static final int INPUT_SLOTS = 5;
	private static final int OUTPUT_SLOT = 5;
	private static final int TOTAL_SLOTS = 6;

	private final NonNullList<ItemStack> items = NonNullList.withSize(TOTAL_SLOTS, ItemStack.EMPTY);

	private int progress = 0;
	private static final int MAX_PROGRESS = 200;

	public EthericCatalystBlockEntity(BlockPos pos, BlockState blockState) {
		super(AllBlockEntities.ETHERIC_CATALYST_BLOCK_ENTITY.get(), pos, blockState);
	}

	@Override
	public int getContainerSize() {
		return TOTAL_SLOTS;
	}

	@Override
	protected Component getDefaultName() {
		return Component.literal("Etheric Catalyst");
	}

	@Override
	protected NonNullList<ItemStack> getItems() {
		return this.items;
	}

	public int getProgress() {
		return this.progress;
	}

	public int getMaxProgress() {
		return MAX_PROGRESS;
	}

	@Override
	protected void setItems(NonNullList<ItemStack> items) {
		for (int i = 0; i < TOTAL_SLOTS; i++) {
			this.items.set(i, items.get(i));
		}
	}

	public static void tick(Level level, BlockPos pos, BlockState state, EthericCatalystBlockEntity be) {
		if (level.isClientSide) return;

		if (be.canProcess()) {
			be.progress++;
			if (be.progress >= MAX_PROGRESS) {
				be.processRecipe();
				be.progress = 0;
			}
		} else {
			be.progress = 0;
		}
	}

	private boolean canProcess() {
		return true;
	}

	private void processRecipe() {
		for (int i = 0; i < INPUT_SLOTS; i++) {
			items.get(i).shrink(1);
		}
		items.set(OUTPUT_SLOT, new ItemStack(AllItems.ETHEREAL_CORE.get()));
		setChanged();
	}

	@Override
	protected AbstractContainerMenu createMenu(int containerId, Inventory inventory) {
		return new EthericCatalystMenu(containerId, inventory, this);
	}

	@Override
	protected void loadAdditional(CompoundTag tag, Provider registries) {
		super.loadAdditional(tag, registries);
		ContainerHelper.loadAllItems(tag, items, registries);
		this.progress = tag.getInt("Progress");
	}

	@Override
	protected void saveAdditional(CompoundTag tag, Provider registries) {
		super.saveAdditional(tag, registries);
		ContainerHelper.saveAllItems(tag, items, registries);
		tag.putInt("Progress", progress);
	}
}
