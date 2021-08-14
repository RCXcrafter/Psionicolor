package com.rcx.psionicolor.spell;

import java.util.function.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import vazkii.psi.api.cad.ICADColorizer;
import vazkii.psi.api.spell.Spell;
import vazkii.psi.api.spell.SpellContext;
import vazkii.psi.common.spell.selector.entity.PieceSelectorNearby;

public class PieceSelectorNearbyColorizers extends PieceSelectorNearby {

	public PieceSelectorNearbyColorizers(Spell spell) {
		super(spell);
	}

	@Override
	public Predicate<Entity> getTargetPredicate(SpellContext context) {
		return this::accept;
	}

	public boolean accept(Entity e) {
		if (e instanceof ItemEntity)
			return ((ItemEntity) e).getItem().getItem() instanceof ICADColorizer;

		return false;
	}
}
