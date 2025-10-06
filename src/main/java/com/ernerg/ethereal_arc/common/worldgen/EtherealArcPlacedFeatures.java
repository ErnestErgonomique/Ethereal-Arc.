package com.ernerg.ethereal_arc.common.worldgen;

import java.util.List;

import com.ernerg.ethereal_arc.common.EtherealArc;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

public class EtherealArcPlacedFeatures {

	public static void bootstrap(BootstrapContext<PlacedFeature> context) {
		var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);
	}
	
	private static ResourceKey<PlacedFeature> registerKey(String name) {
		return ResourceKey.create(Registries.PLACED_FEATURE,
			ResourceLocation.fromNamespaceAndPath(EtherealArc.MOD_ID, name));
	}

	public static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
		Holder<ConfiguredFeature<?, ?>> configuation, List<PlacementModifier> modifiers
	) {
		context.register(key, new PlacedFeature(configuation, List.copyOf(modifiers)));
	}
}
