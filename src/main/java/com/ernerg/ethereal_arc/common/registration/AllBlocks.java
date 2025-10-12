package com.ernerg.ethereal_arc.common.registration;

import java.util.function.Supplier;

import com.ernerg.ethereal_arc.common.EtherealArc;
import com.ernerg.ethereal_arc.common.block.BuddingArcyniteBlock;
import com.ernerg.ethereal_arc.common.block.HostilityAnnihilatorBlock;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
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
			.sound(SoundType.DEEPSLATE)
			.requiresCorrectToolForDrops())
	);

	public static final DeferredBlock<Block> IRIDIUM_BLOCK = registerBlock(
		"iridium_block",
		() -> new Block(BlockBehaviour.Properties.of()
			.strength(3.0f)
			.requiresCorrectToolForDrops())
	);

	public static final DeferredBlock<Block> RAW_IRIDIUM_BLOCK = registerBlock(
		"raw_iridium_block",
		() -> new Block(BlockBehaviour.Properties.of()
			.strength(3.0f)
			.requiresCorrectToolForDrops())
	);

	public static final DeferredBlock<Block> HOSTILITY_ANNIHILATOR = registerBlock(
		"hostility_annihilator",
		() -> new HostilityAnnihilatorBlock(BlockBehaviour.Properties.of()
			.noLootTable())

	);

	public static final DeferredBlock<Block> ARCYNITE_BLOCK = registerBlock(
		"arcynite_block",
		() -> new Block(BlockBehaviour.Properties.of()
			.strength(1.5f)
			.sound(SoundType.AMETHYST)
			.requiresCorrectToolForDrops())
	);

	public static final DeferredBlock<Block> BUDDING_ARCYNITE = registerBlock(
		"budding_arcynite",
		() -> new BuddingArcyniteBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BUDDING_AMETHYST))
	);

	public static final DeferredBlock<Block> ARCYNITE_CLUSTER = registerBlock(
		"arcynite_cluster",
		() -> new AmethystClusterBlock(7, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.AMETHYST_CLUSTER))
	);

	public static final DeferredBlock<Block> LARGE_ARCYNITE_BUD = registerBlock(
		"large_arcynite_bud",
		() -> new AmethystClusterBlock(5, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.LARGE_AMETHYST_BUD))
	);

	public static final DeferredBlock<Block> MEDIUM_ARCYNITE_BUD = registerBlock(
		"medium_arcynite_bud",
		() -> new AmethystClusterBlock(4, 3, BlockBehaviour.Properties.ofFullCopy(Blocks.MEDIUM_AMETHYST_BUD))
	);

	public static final DeferredBlock<Block> SMALL_ARCYNITE_BUD = registerBlock(
		"small_arcynite_bud",
		() -> new AmethystClusterBlock(3, 4, BlockBehaviour.Properties.ofFullCopy(Blocks.SMALL_AMETHYST_BUD))
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
