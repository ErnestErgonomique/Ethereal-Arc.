package com.ernerg.ethereal_arc.datagen;

import com.ernerg.ethereal_arc.common.EtherealArc;
import com.ernerg.ethereal_arc.common.registration.AllItems;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
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
	}
	
}
