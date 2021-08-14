package com.rcx.psionicolor.datagen;

import java.util.function.Consumer;

import com.rcx.psionicolor.Psionicolor;
import com.rcx.psionicolor.PsionicolorResources;
import com.rcx.psionicolor.misc.HybridColorizerRecipe;

import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.DyeColor;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import slimeknights.mantle.recipe.data.ConsumerWrapperBuilder;
import vazkii.psi.common.Psi;
import vazkii.psi.common.item.base.ModItems;
import vazkii.psi.common.lib.LibItemNames;
import vazkii.psi.common.lib.ModTags;

public class PsionicolorRecipes extends RecipeProvider implements IConditionBuilder {

	public PsionicolorRecipes(DataGenerator gen) {
		super(gen);
	}

	public void registerRecipes(Consumer<IFinishedRecipe> consumer) {
		ICriterionInstance hasPsidust = hasItem(ModTags.PSIDUST);
		for (DyeColor color : DyeColor.values()) {
			ShapelessRecipeBuilder.shapelessRecipe(Registry.ITEM.getOrDefault(new ResourceLocation(Psionicolor.modID, LibItemNames.CAD_COLORIZER + color.getString() + "_neon")))
			.setGroup("psi:colorizer")
			.addIngredient(Registry.ITEM.getOrDefault(Psi.location(LibItemNames.CAD_COLORIZER + color.getString())))
			.addIngredient(Tags.Items.DUSTS_GLOWSTONE)
			.addCriterion("has_psidust", hasPsidust)
			.build(consumer, new ResourceLocation(Psionicolor.modID, LibItemNames.CAD_COLORIZER + color.getString() + "_neon"));
		}

		ShapedRecipeBuilder.shapedRecipe(PsionicolorResources.HYBRID_COLORIZER.get())
		.setGroup("psi:colorizer")
		.key('D', ModTags.PSIDUST)
		.key('G', Tags.Items.GLASS)
		.key('C', PsionicolorItemTags.COLORIZERS_COMPONENTS)
		.patternLine(" D ")
		.patternLine("GCG")
		.patternLine(" C ")
		.addCriterion("has_psidust", hasItem(ModTags.PSIDUST))
		.build(ConsumerWrapperBuilder.wrap(HybridColorizerRecipe.SERIALIZER).build(consumer), new ResourceLocation(Psionicolor.modID, "cad_colorizer_hybrid"));

		ShapedRecipeBuilder.shapedRecipe(PsionicolorResources.INDICATOR_COLORIZER.get())
		.setGroup("psi:colorizer")
		.key('D', ModTags.PSIDUST)
		.key('B', ModItems.cadBatteryBasic)
		.key('G', Tags.Items.GLASS)
		.key('C', PsionicolorItemTags.COLORIZERS_COMPONENTS)
		.patternLine(" D ")
		.patternLine("BCG")
		.patternLine(" C ")
		.addCriterion("has_psidust", hasItem(ModTags.PSIDUST))
		.build(ConsumerWrapperBuilder.wrap(HybridColorizerRecipe.SERIALIZER).build(consumer), new ResourceLocation(Psionicolor.modID, "cad_colorizer_indicator"));

		ShapedRecipeBuilder.shapedRecipe(PsionicolorResources.TRIGGERED_COLORIZER.get())
		.setGroup("psi:colorizer")
		.key('D', ModTags.PSIDUST)
		.key('B', Items.STONE_BUTTON)
		.key('G', Tags.Items.GLASS)
		.key('C', PsionicolorItemTags.COLORIZERS_COMPONENTS)
		.patternLine(" DB")
		.patternLine("GCG")
		.patternLine(" C ")
		.addCriterion("has_psidust", hasItem(ModTags.PSIDUST))
		.build(ConsumerWrapperBuilder.wrap(HybridColorizerRecipe.SERIALIZER).build(consumer), new ResourceLocation(Psionicolor.modID, "cad_colorizer_triggered"));


		ShapelessRecipeBuilder.shapelessRecipe(PsionicolorResources.CONFIGURABLE_COLORIZER.get())
		.setGroup("psi:colorizer")
		.addIngredient(ModItems.cadColorizerEmpty)
		.addIngredient(Tags.Items.DYES_CYAN)
		.addIngredient(Tags.Items.DYES_YELLOW)
		.addIngredient(Tags.Items.DYES_MAGENTA)
		.addIngredient(ModTags.EBONY_SUBSTANCE)
		.addCriterion("has_ebony", hasItem(ModTags.EBONY_SUBSTANCE))
		.build(consumer, new ResourceLocation(Psionicolor.modID, "cad_colorizer_configurable"));

		standardColorizerRecipe(consumer, PsionicolorResources.CLOCK_COLORIZER.get(), Ingredient.fromItems(Items.CLOCK));

		standardColorizerRecipe(consumer, PsionicolorResources.ROCKIN_COLORIZER.get(), Ingredient.fromItems(Items.COOKED_BEEF));

		standardColorizerRecipe(consumer, PsionicolorResources.RANDOM_COLORIZER.get(), Ingredient.fromItems(Items.WHEAT_SEEDS));

		standardColorizerRecipe(consumer, PsionicolorResources.GRAPE_COLORIZER.get(), Ingredient.fromItems(Items.CHORUS_FRUIT));
		standardColorizerRecipe(consumer, PsionicolorResources.LEMON_LIME_COLORIZER.get(), Ingredient.fromItems(Items.POISONOUS_POTATO));

		standardColorizerRecipe(consumer, PsionicolorResources.FIRE_COLORIZER.get(), Ingredient.fromItems(Items.BLAZE_POWDER));
	}

	public void standardColorizerRecipe(Consumer<IFinishedRecipe> consumer, IItemProvider result, Ingredient center) {
		ShapedRecipeBuilder.shapedRecipe(result)
		.setGroup("psi:colorizer")
		.key('D', ModTags.PSIDUST)
		.key('I', Tags.Items.INGOTS_IRON)
		.key('G', Tags.Items.GLASS)
		.key('C', center)
		.patternLine(" D ")
		.patternLine("GCG")
		.patternLine(" I ")
		.addCriterion("has_psidust", hasItem(ModTags.PSIDUST))
		.build(consumer, result.asItem().getRegistryName());
	}
}
