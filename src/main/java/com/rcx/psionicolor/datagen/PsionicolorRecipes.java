package com.rcx.psionicolor.datagen;

import java.util.function.Consumer;

import com.rcx.psionicolor.Psionicolor;
import com.rcx.psionicolor.PsionicolorResources;
import com.rcx.psionicolor.misc.HybridColorizerRecipe;

import net.minecraft.advancements.critereon.InventoryChangeTrigger.TriggerInstance;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import slimeknights.mantle.recipe.data.ConsumerWrapperBuilder;
import vazkii.psi.common.Psi;
import vazkii.psi.common.item.base.ModItems;
import vazkii.psi.common.lib.LibItemNames;

public class PsionicolorRecipes extends RecipeProvider implements IConditionBuilder {

	public PsionicolorRecipes(DataGenerator gen) {
		super(gen);
	}

	@Override
	public void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
		TriggerInstance hasPsidust = has(ModItems.psidust);
		for (DyeColor color : DyeColor.values()) {
			ShapelessRecipeBuilder.shapeless(Registry.ITEM.get(new ResourceLocation(Psionicolor.modID, LibItemNames.CAD_COLORIZER + color.getName() + "_neon")))
			.group("psi:colorizer")
			.requires(Registry.ITEM.get(Psi.location(LibItemNames.CAD_COLORIZER + color.getName())))
			.requires(Tags.Items.DUSTS_GLOWSTONE)
			.unlockedBy("has_psidust", hasPsidust)
			.save(consumer, new ResourceLocation(Psionicolor.modID, LibItemNames.CAD_COLORIZER + color.getName() + "_neon"));
		}

		ShapedRecipeBuilder.shaped(PsionicolorResources.HYBRID_COLORIZER.get())
		.group("psi:colorizer")
		.define('D', ModItems.psidust)
		.define('G', Tags.Items.GLASS)
		.define('C', PsionicolorItemTags.COLORIZERS_COMPONENTS)
		.pattern(" D ")
		.pattern("GCG")
		.pattern(" C ")
		.unlockedBy("has_psidust", has(ModItems.psidust))
		.save(ConsumerWrapperBuilder.wrap(HybridColorizerRecipe.SERIALIZER).build(consumer), new ResourceLocation(Psionicolor.modID, "cad_colorizer_hybrid"));

		ShapedRecipeBuilder.shaped(PsionicolorResources.INDICATOR_COLORIZER.get())
		.group("psi:colorizer")
		.define('D', ModItems.psidust)
		.define('B', ModItems.cadBatteryBasic)
		.define('G', Tags.Items.GLASS)
		.define('C', PsionicolorItemTags.COLORIZERS_COMPONENTS)
		.pattern(" D ")
		.pattern("BCG")
		.pattern(" C ")
		.unlockedBy("has_psidust", has(ModItems.psidust))
		.save(ConsumerWrapperBuilder.wrap(HybridColorizerRecipe.SERIALIZER).build(consumer), new ResourceLocation(Psionicolor.modID, "cad_colorizer_indicator"));

		ShapedRecipeBuilder.shaped(PsionicolorResources.TRIGGERED_COLORIZER.get())
		.group("psi:colorizer")
		.define('D', ModItems.psidust)
		.define('B', Items.STONE_BUTTON)
		.define('G', Tags.Items.GLASS)
		.define('C', PsionicolorItemTags.COLORIZERS_COMPONENTS)
		.pattern(" DB")
		.pattern("GCG")
		.pattern(" C ")
		.unlockedBy("has_psidust", has(ModItems.psidust))
		.save(ConsumerWrapperBuilder.wrap(HybridColorizerRecipe.SERIALIZER).build(consumer), new ResourceLocation(Psionicolor.modID, "cad_colorizer_triggered"));


		ShapelessRecipeBuilder.shapeless(PsionicolorResources.CONFIGURABLE_COLORIZER.get())
		.group("psi:colorizer")
		.requires(ModItems.cadColorizerEmpty)
		.requires(Tags.Items.DYES_CYAN)
		.requires(Tags.Items.DYES_YELLOW)
		.requires(Tags.Items.DYES_MAGENTA)
		.requires(ModItems.ebonySubstance)
		.unlockedBy("has_ebony", has(ModItems.ebonySubstance))
		.save(consumer, new ResourceLocation(Psionicolor.modID, "cad_colorizer_configurable"));

		standardColorizerRecipe(consumer, PsionicolorResources.CLOCK_COLORIZER.get(), Ingredient.of(Items.CLOCK));

		standardColorizerRecipe(consumer, PsionicolorResources.ROCKIN_COLORIZER.get(), Ingredient.of(Items.COOKED_BEEF));

		standardColorizerRecipe(consumer, PsionicolorResources.RANDOM_COLORIZER.get(), Ingredient.of(Items.WHEAT_SEEDS));

		standardColorizerRecipe(consumer, PsionicolorResources.GRAPE_COLORIZER.get(), Ingredient.of(Items.CHORUS_FRUIT));
		standardColorizerRecipe(consumer, PsionicolorResources.LEMON_LIME_COLORIZER.get(), Ingredient.of(Items.POISONOUS_POTATO));

		standardColorizerRecipe(consumer, PsionicolorResources.FIRE_COLORIZER.get(), Ingredient.of(Items.BLAZE_POWDER));
	}

	public void standardColorizerRecipe(Consumer<FinishedRecipe> consumer, ItemLike result, Ingredient center) {
		ShapedRecipeBuilder.shaped(result)
		.group("psi:colorizer")
		.define('D', ModItems.psidust)
		.define('I', Tags.Items.INGOTS_IRON)
		.define('G', Tags.Items.GLASS)
		.define('C', center)
		.pattern(" D ")
		.pattern("GCG")
		.pattern(" I ")
		.unlockedBy("has_psidust", has(ModItems.psidust))
		.save(consumer, result.asItem().getRegistryName());
	}
}
