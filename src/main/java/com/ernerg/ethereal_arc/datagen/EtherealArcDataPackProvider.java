package com.ernerg.ethereal_arc.datagen;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

import com.ernerg.ethereal_arc.common.EtherealArc;
import com.ernerg.ethereal_arc.common.worldgen.EtherealArcBiomeModifier;
import com.ernerg.ethereal_arc.common.worldgen.EtherealArcConfiguredFeatures;
import com.ernerg.ethereal_arc.common.worldgen.EtherealArcPlacedFeatures;

import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.RegistrySetBuilder.PatchedRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class EtherealArcDataPackProvider extends DatapackBuiltinEntriesProvider {

	public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
		.add(Registries.CONFIGURED_FEATURE, EtherealArcConfiguredFeatures::bootstrap)
		.add(Registries.PLACED_FEATURE, EtherealArcPlacedFeatures::bootstrap)
		.add(NeoForgeRegistries.Keys.BIOME_MODIFIERS, EtherealArcBiomeModifier::bootstrap);

	public EtherealArcDataPackProvider(PackOutput output, CompletableFuture<PatchedRegistries> registries) {
		super(output, registries, Set.of(EtherealArc.MOD_ID));
	}
	
}
