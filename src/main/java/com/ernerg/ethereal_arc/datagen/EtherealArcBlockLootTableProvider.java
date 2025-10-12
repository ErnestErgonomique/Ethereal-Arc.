package com.ernerg.ethereal_arc.datagen;

import java.util.Set;

import com.ernerg.ethereal_arc.common.registration.AllBlocks;
import com.ernerg.ethereal_arc.common.registration.AllItems;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

public class EtherealArcBlockLootTableProvider extends BlockLootSubProvider {

	protected EtherealArcBlockLootTableProvider(HolderLookup.Provider registries) {
		super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
		
	}

	@Override
	protected void generate() {
		HolderLookup.RegistryLookup<Enchantment> registryLookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

		add(AllBlocks.IRIDIUM_ORE.get(),
			block -> createOreDrop(AllBlocks.IRIDIUM_ORE.get(), AllItems.RAW_IRIDIUM.get())
		);
		add(AllBlocks.DEEPSLATE_IRIDIUM_ORE.get(),
			block -> createOreDrop(AllBlocks.DEEPSLATE_IRIDIUM_ORE.get(), AllItems.RAW_IRIDIUM.get())
		);
		dropSelf(AllBlocks.IRIDIUM_BLOCK.get());
		dropSelf(AllBlocks.RAW_IRIDIUM_BLOCK.get());
		dropSelf(AllBlocks.ARCYNITE_BLOCK.get());
		add(AllBlocks.BUDDING_ARCYNITE.get(), noDrop());
		add(AllBlocks.ARCYNITE_CLUSTER.get(),
			block -> createSilkTouchDispatchTable(
				block, LootItem.lootTableItem(AllItems.ETHEREAL_CORE.get())
					.apply(SetItemCountFunction.setCount(ConstantValue.exactly(4.0f)))
					.apply(ApplyBonusCount.addOreBonusCount(registryLookup.getOrThrow(Enchantments.FORTUNE)))
					.when(MatchTool.toolMatches(ItemPredicate.Builder.item().of(ItemTags.CLUSTER_MAX_HARVESTABLES)))
					.otherwise(
						(LootPoolEntryContainer.Builder<?>)this.applyExplosionDecay(
                             block, LootItem.lootTableItem(AllItems.ETHEREAL_CORE.get())
							 	.apply(SetItemCountFunction.setCount(ConstantValue.exactly(2.0F)))
                        )
					)
			)
		);
		dropWhenSilkTouch(AllBlocks.LARGE_ARCYNITE_BUD.get());
		dropWhenSilkTouch(AllBlocks.MEDIUM_ARCYNITE_BUD.get());
		dropWhenSilkTouch(AllBlocks.SMALL_ARCYNITE_BUD.get());
	}

	@Override
	protected Iterable<Block> getKnownBlocks() {
		return AllBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
	}
}
