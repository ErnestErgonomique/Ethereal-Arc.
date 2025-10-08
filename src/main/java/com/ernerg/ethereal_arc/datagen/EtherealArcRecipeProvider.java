package com.ernerg.ethereal_arc.datagen;

import java.util.concurrent.CompletableFuture;

import com.ernerg.ethereal_arc.common.registration.AllBlocks;
import com.ernerg.ethereal_arc.common.registration.AllItems;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.world.item.crafting.Ingredient;

public class EtherealArcRecipeProvider extends RecipeProvider {

	

	public EtherealArcRecipeProvider(PackOutput output, CompletableFuture<Provider> registries) {
		super(output, registries);
	}

	@Override
	protected void buildRecipes(RecipeOutput output) {
		/*===== CRAFTING =====*/
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllBlocks.IRIDIUM_BLOCK.get())
			.define('#', AllItems.IRIDIUM_INGOT.get())
			.pattern("###")
			.pattern("###")
			.pattern("###")
			.unlockedBy("has_iridium_ingot", has(AllItems.IRIDIUM_INGOT.get()))
			.save(output, "iridium_block");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.IRIDIUM_INGOT.get(), 9)
			.requires(AllBlocks.IRIDIUM_BLOCK.get())
			.unlockedBy("has_iridium_block", has(AllBlocks.IRIDIUM_BLOCK.get()))
			.save(output, "iridium_ingot_from_block");

		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, AllItems.IRIDIUM_INGOT.get())
			.define('#', AllItems.IRIDIUM_NUGGET.get())
			.pattern("###")
			.pattern("###")
			.pattern("###")
			.unlockedBy("has_iridium_nugget", has(AllItems.IRIDIUM_NUGGET.get()))
			.save(output, "iridium_ingot_from_nuggets");

		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, AllItems.IRIDIUM_NUGGET.get(), 9)
			.requires(AllItems.IRIDIUM_INGOT.get())
			.unlockedBy("has_iridium_ingot", has(AllItems.IRIDIUM_INGOT.get()))
			.save(output, "iridium_nugget");

		
		/*===== SMELTING =====*/
		SimpleCookingRecipeBuilder.smelting(
			Ingredient.of(AllItems.RAW_IRIDIUM.get()),
			RecipeCategory.MISC,
			AllItems.IRIDIUM_INGOT.get(),
			2.0f, 200
		).unlockedBy("has_raw_iridium", has(AllItems.RAW_IRIDIUM.get()))
		.save(output, "iridium_ingot_from_smelting_raw_iridium");

		SimpleCookingRecipeBuilder.smelting(
			Ingredient.of(AllBlocks.IRIDIUM_ORE.get()),
			RecipeCategory.MISC,
			AllItems.IRIDIUM_INGOT.get(),
			2.0f, 200
		).unlockedBy("has_raw_iridium", has(AllBlocks.IRIDIUM_ORE.get()))
		.save(output, "iridium_ingot_from_smelting_iridium_ore");

		SimpleCookingRecipeBuilder.smelting(
			Ingredient.of(AllBlocks.DEEPSLATE_IRIDIUM_ORE.get()),
			RecipeCategory.MISC,
			AllItems.IRIDIUM_INGOT.get(),
			2.0f, 200
		).unlockedBy("has_raw_iridium", has(AllBlocks.DEEPSLATE_IRIDIUM_ORE.get()))
		.save(output, "iridium_ingot_from_smelting_deepslate_iridium_ore");

		/*===== BLASTING =====*/
		SimpleCookingRecipeBuilder.blasting(
			Ingredient.of(AllItems.RAW_IRIDIUM.get()),
			RecipeCategory.MISC,
			AllItems.IRIDIUM_INGOT.get(),
			2.0f, 100
		).unlockedBy("has_raw_iridium", has(AllItems.RAW_IRIDIUM.get()))
		.save(output, "iridium_ingot_from_blasting_raw_iridium");

		SimpleCookingRecipeBuilder.blasting(
			Ingredient.of(AllBlocks.IRIDIUM_ORE.get()),
			RecipeCategory.MISC,
			AllItems.IRIDIUM_INGOT.get(),
			2.0f, 100
		).unlockedBy("has_raw_iridium", has(AllBlocks.IRIDIUM_ORE.get()))
		.save(output, "iridium_ingot_from_blasting_iridium_ore");

		SimpleCookingRecipeBuilder.blasting(
			Ingredient.of(AllBlocks.DEEPSLATE_IRIDIUM_ORE.get()),
			RecipeCategory.MISC,
			AllItems.IRIDIUM_INGOT.get(),
			2.0f, 100
		).unlockedBy("has_raw_iridium", has(AllBlocks.DEEPSLATE_IRIDIUM_ORE.get()))
		.save(output, "iridium_ingot_from_blasting_deepslate_iridium_ore");
	}
	
}
