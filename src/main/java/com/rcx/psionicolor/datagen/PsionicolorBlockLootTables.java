package com.rcx.psionicolor.datagen;

import java.util.Objects;
import java.util.stream.Collectors;

import javax.annotation.Nonnull;

import com.rcx.psionicolor.Psionicolor;

import net.minecraft.data.loot.BlockLoot;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;

public class PsionicolorBlockLootTables extends BlockLoot {

	@Nonnull
	@Override
	protected Iterable<Block> getKnownBlocks() {
		return ForgeRegistries.BLOCKS.getValues().stream()
				.filter((block) -> Psionicolor.modID.equals(Objects.requireNonNull(block.getRegistryName()).getNamespace()))
				.collect(Collectors.toList());
	}

	@Override
	protected void addTables() {
		
	}
}
