package com.ernerg.ethereal_arc.common.registration;

import java.util.function.Supplier;

import com.ernerg.ethereal_arc.common.EtherealArc;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AllBlocks {
	public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(EtherealArc.MOD_ID);

	public static final DeferredBlock<Block> IRIDIUM_ORE = registerBlock(
		"iridium_ore",
		() -> new Block(BlockBehaviour.Properties.of()
			.strength(3f)
			.requiresCorrectToolForDrops())
	);

	public static final DeferredBlock<Block> DEEPSLATE_IRIDIUM_ORE = registerBlock(
		"deepslate_iridium_ore",
		() -> new Block(BlockBehaviour.Properties.of()
			.strength(4.5f)
			.requiresCorrectToolForDrops())
	);

	public static final DeferredBlock<Block> IRIDIUM_BLOCK = registerBlock(
		"iridium_block",
		() -> new Block(BlockBehaviour.Properties.of()
			.strength(3.0f)
			.requiresCorrectToolForDrops()
		)
	);

	private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
		DeferredBlock<T> toReturn = BLOCKS.register(name, block);
		registerBlockItem(name, toReturn);
		return toReturn;
	}

	private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
		AllItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
	}

	public static void register(IEventBus eventBus) {
		BLOCKS.register(eventBus);
	}
}
