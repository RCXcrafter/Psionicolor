package com.rcx.psionicolor.datagen;

import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import com.rcx.psionicolor.Psionicolor;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.loot.LootParameterSet;
import net.minecraft.loot.LootParameterSets;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTableManager;
import net.minecraft.loot.ValidationTracker;
import net.minecraft.util.ResourceLocation;

public class PsionicolorLootTables extends LootTableProvider {

	private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> lootTables = ImmutableList.of(Pair.of(PsionicolorBlockLootTables::new, LootParameterSets.BLOCK));

	public PsionicolorLootTables(DataGenerator gen) {
		super(gen);
	}

	@Override
	protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
		return lootTables;
	}

	@Override
	protected void validate(Map<ResourceLocation,LootTable> map, ValidationTracker validationtracker) {
		map.forEach((loc, table) -> LootTableManager.validateLootTable(validationtracker, loc, table));
		// Remove vanilla's tables, which we also loaded so we can redirect stuff to them.
		// This ensures the remaining generator logic doesn't write those to files.
		map.keySet().removeIf((loc) -> !loc.getNamespace().equals(Psionicolor.modID));
	}

	/**
	 * Gets a name for this provider, to use in logging.
	 */
	@Override
	public String getName() {
		return Psionicolor.modID + " LootTables";
	}
}