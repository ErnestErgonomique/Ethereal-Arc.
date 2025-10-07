package com.ernerg.ethereal_arc.common.worldgen;

import java.util.List;

import com.ernerg.ethereal_arc.common.EtherealArc;

import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

public class EtherealArcPlacedFeatures {

	public static final ResourceKey<PlacedFeature> IRIDIUM_ORE_PLACED_KEY = registerKey("iridium_ore_placed");

	public static void bootstrap(BootstrapContext<PlacedFeature> context) {
		var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

		register(context, IRIDIUM_ORE_PLACED_KEY,
			configuredFeatures.getOrThrow(EtherealArcConfiguredFeatures.OVERWORLD_IRIDIUM_ORE_KEY),
			EtherealArcOrePlacement.commonOrePlacement(7,
				HeightRangePlacement.triangle(
					VerticalAnchor.aboveBottom(48),
					VerticalAnchor.aboveBottom(-48)
				))
		);
	}
	
	private static ResourceKey<PlacedFeature> registerKey(String name) {
		return ResourceKey.create(Registries.PLACED_FEATURE,
			ResourceLocation.fromNamespaceAndPath(EtherealArc.MOD_ID, name));
	}

	private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key,
		Holder<ConfiguredFeature<?, ?>> configuation, List<PlacementModifier> modifiers
	) {
		context.register(key, new PlacedFeature(configuation, List.copyOf(modifiers)));
	}
}
