package com.ernerg.ethereal_arc.common.recipe;

import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

public record EthericCatalystRecipe(Ingredient inputItem, ItemStack output)
	implements Recipe<EthericCatalystRecipeInput> {

	@Override
	public boolean matches(EthericCatalystRecipeInput input, Level level) {
		return false;
	}

	@Override
	public ItemStack assemble(EthericCatalystRecipeInput input, Provider registries) {
		return null;
	}

	@Override
	public boolean canCraftInDimensions(int width, int height) {
		return false;
	}

	@Override
	public ItemStack getResultItem(Provider registries) {
		return null;
	}

	@Override
	public RecipeSerializer<?> getSerializer() {
		return null;
	}

	@Override
	public RecipeType<?> getType() {
		return null;
	}
}
