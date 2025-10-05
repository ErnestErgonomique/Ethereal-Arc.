package com.ernerg.builderssanctuary.common.registration;

import java.util.function.Supplier;

import com.ernerg.builderssanctuary.common.EtherealArc;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AllCreativeModeTabs {
	public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(
		Registries.CREATIVE_MODE_TAB, EtherealArc.MOD_ID
	);

	public static final Supplier<CreativeModeTab> ETHEREAL_ARC = CREATIVE_MODE_TABS.register(
		"ethereal_arc", () -> CreativeModeTab.builder()
		.title(Component.translatable("creativetabs." + EtherealArc.MOD_ID + ".main"))
		.icon(() -> new ItemStack(AllItems.ETHEREAL_CORE.get()))
		.displayItems((itemDisplayParameters, output) -> {
			output.accept(AllItems.ETHEREAL_CORE.get());
			output.accept(AllBlocks.IRIDIUM_ORE);
			output.accept(AllBlocks.DEEPSLATE_IRIDIUM_ORE);
			output.accept(AllItems.RAW_IRIDIUM.get());
			output.accept(AllItems.IRIDIUM_INGOT.get());
		}).build());

	public static void register(IEventBus eventBus) {
		CREATIVE_MODE_TABS.register(eventBus);
	}
}
