package com.ernerg.ethereal_arc.common.menu;

import com.ernerg.ethereal_arc.common.blockentity.EthericCatalystBlockEntity;
import com.ernerg.ethereal_arc.common.registration.AllMenuTypes;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.SimpleContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

public class EthericCatalystMenu extends AbstractContainerMenu {

	private final EthericCatalystBlockEntity blockEntity;
	private final ContainerData data;

	public EthericCatalystMenu(int containerId, Inventory inv, FriendlyByteBuf extraData) {
		this(containerId, inv, getBlockEntityFromBuf(inv, extraData));
	}

	public EthericCatalystMenu(int containerId, Inventory inv, EthericCatalystBlockEntity blockEntity) {
		super(AllMenuTypes.ETHERIC_CATALYST_MENU.get(), containerId);
		this.blockEntity = blockEntity;
		this.data = new SimpleContainerData(2);

		for (int i = 0; i < 5; i++) {
			this.addSlot(new Slot(blockEntity, i, i*18, 0));
		}

		this.addSlot(new Slot(blockEntity, 5, 0, 18) {
			@Override
			public boolean mayPlace(ItemStack stack) {
				return false;
			}
		});

		addPlayerInventory(inv);
		addPlayerHotbar(inv);
	}

	public int getProgress() {
		return data.get(0);
	}

	public int getMaxProgress() {
		return data.get(1);
	}

	private static EthericCatalystBlockEntity getBlockEntityFromBuf(Inventory inv, FriendlyByteBuf buf) {
    BlockEntity be = inv.player.level().getBlockEntity(buf.readBlockPos());
    if (!(be instanceof EthericCatalystBlockEntity)) {
        throw new IllegalStateException("Invalid Block Entity");
    }
    return (EthericCatalystBlockEntity) be;
}

	private void addPlayerInventory(Inventory inventory) {
		for (int i = 0; i < 3; ++i) {
			for (int l = 0; l < 9; ++l) {
				this.addSlot(new Slot(inventory, l + i * 9 + 9, 8 + l * 18, 84 + i * 18));
			}
		}
	}

	private void addPlayerHotbar(Inventory inventory) {
		for (int i = 0; i < 9; ++i) {
			this.addSlot(new Slot(inventory, i, 8 + i * 18, 142));
		}
	}

	@Override
	public ItemStack quickMoveStack(Player player, int index) {
		return null;
	}

	@Override
	public boolean stillValid(Player player) {
		return blockEntity.stillValid(player);
	}
	
}
