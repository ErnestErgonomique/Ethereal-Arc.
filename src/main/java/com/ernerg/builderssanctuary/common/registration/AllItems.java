package com.ernerg.builderssanctuary.common.registration;

import java.util.function.Supplier;

import com.ernerg.builderssanctuary.common.EtherealArc;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AllItems {
	// Item list given to the game at the right time
	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(EtherealArc.MOD_ID);

	public static final Supplier<Item> ETHEREAL_CORE = ITEMS.registerSimpleItem(
		"ethereal_core",
		new Item.Properties()
	);

	public static final Supplier<Item> RAW_IRIDIUM = ITEMS.registerSimpleItem(
		"raw_iridium",
		new Item.Properties()
	);

	// Method called in the main class constructor to register the list
	public static void register(IEventBus eventBus) {
		ITEMS.register(eventBus);
	}
}
