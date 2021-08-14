package com.rcx.psionicolor.spell;

import net.minecraft.block.BlockState;
import vazkii.psi.api.spell.EnumSpellStat;
import vazkii.psi.api.spell.Spell;
import vazkii.psi.api.spell.SpellCompilationException;
import vazkii.psi.api.spell.SpellMetadata;
import vazkii.psi.common.block.BlockConjured;

public class PieceTrickConjureColoredLight extends PieceTrickConjureColoredBlock {

	public PieceTrickConjureColoredLight(Spell spell) {
		super(spell);
	}

	@Override
	public void addStats(SpellMetadata meta) throws SpellCompilationException {
		meta.addStat(EnumSpellStat.POTENCY, 25);
		meta.addStat(EnumSpellStat.COST, 100);
	}

	@Override
	public BlockState messWithState(BlockState state) {
		return state.with(BlockConjured.LIGHT, true);
	}
}
