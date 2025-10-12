package com.ernerg.ethereal_arc.datagen;

import java.util.Objects;

import com.ernerg.ethereal_arc.common.EtherealArc;
import com.ernerg.ethereal_arc.common.registration.AllBlocks;
import com.ernerg.ethereal_arc.common.registration.AllItems;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class EtherealArcItemModelProvider extends ItemModelProvider {

	public EtherealArcItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, EtherealArc.MOD_ID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		basicItem(AllItems.ETHEREAL_CORE.get());
		basicItem(AllItems.RAW_IRIDIUM.get());
		basicItem(AllItems.IRIDIUM_INGOT.get());
		basicItem(AllItems.IRIDIUM_NUGGET.get());
		basicItemFromBlock(AllBlocks.ARCYNITE_CLUSTER.get());
		basicItemFromBlock(AllBlocks.LARGE_ARCYNITE_BUD.get());
		basicItemFromBlock(AllBlocks.MEDIUM_ARCYNITE_BUD.get());
		basicItemFromBlock(AllBlocks.SMALL_ARCYNITE_BUD.get());
		basicItem(AllItems.ARCYNITE_SHARD.get());
	}

	public ItemModelBuilder basicItemFromBlock(Block block) {
		ResourceLocation item = Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(block.asItem()));
        return getBuilder(item.toString())
                .parent(new ModelFile.UncheckedModelFile("item/generated"))
                .texture("layer0", ResourceLocation.fromNamespaceAndPath(item.getNamespace(), "block/" + item.getPath()));
    }
}
