package com.ernerg.ethereal_arc.util;

import com.ernerg.ethereal_arc.common.EtherealArc;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class AllTags {

	public static class Blocks {

		private static TagKey<Block> createTag(String name) {
			return BlockTags.create(ResourceLocation.fromNamespaceAndPath(EtherealArc.MOD_ID, name));
		}
	}

	public static class Items {

		public static final TagKey<Item> TRANSFORMABLE_ITEMS = createTag("transformable items");

		private static TagKey<Item> createTag(String name) {
			return ItemTags.create(ResourceLocation.fromNamespaceAndPath(EtherealArc.MOD_ID, name));
		}
	}
}
