package com.rcx.psionicolor.spell;

import com.rcx.psionicolor.datagen.PsionicolorLang;
import com.rcx.psionicolor.item.ItemCADColorizerConfigurable;
import com.rcx.psionicolor.misc.ColorUtil;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import vazkii.psi.api.internal.Vector3;
import vazkii.psi.api.spell.EnumSpellStat;
import vazkii.psi.api.spell.Spell;
import vazkii.psi.api.spell.SpellCompilationException;
import vazkii.psi.api.spell.SpellContext;
import vazkii.psi.api.spell.SpellMetadata;
import vazkii.psi.api.spell.SpellParam;
import vazkii.psi.api.spell.SpellRuntimeException;
import vazkii.psi.api.spell.param.ParamEntity;
import vazkii.psi.api.spell.param.ParamVector;
import vazkii.psi.api.spell.piece.PieceTrick;

public class PieceTrickFillColorizer extends PieceTrick {

	SpellParam<Vector3> color;
	SpellParam<Entity> colorizer;

	public PieceTrickFillColorizer(Spell spell) {
		super(spell);
	}

	@Override
	public void initParams() {
		addParam(color = new ParamVector(PsionicolorLang.GENERIC_NAME_COLOR, SpellParam.GREEN, false, false));
		addParam(colorizer = new ParamEntity(PsionicolorLang.GENERIC_NAME_COLORIZER, SpellParam.YELLOW, false, false));
	}

	@Override
	public void addToMetadata(SpellMetadata meta) throws SpellCompilationException {
		super.addToMetadata(meta);
		meta.addStat(EnumSpellStat.POTENCY, 50);
		meta.addStat(EnumSpellStat.COST, 100);
	}

	@Override
	public Object execute(SpellContext context) throws SpellRuntimeException {
		Vector3 colorVal = this.getParamValue(context, color);
		Entity e = this.getParamValue(context, colorizer);
		if (colorVal == null || e == null || !(e instanceof ItemEntity) || !(((ItemEntity) e).getItem().getItem() instanceof ItemCADColorizerConfigurable)) {
			throw new SpellRuntimeException(SpellRuntimeException.NULL_TARGET);
		}
		ItemStack colorizerStack = ((ItemEntity) e).getItem().copy();
		colorizerStack.getOrCreateTag().putInt(ItemCADColorizerConfigurable.COLOR, ColorUtil.RGBToInt((int) colorVal.x, (int) colorVal.y, (int) colorVal.z));
		((ItemEntity) e).setItem(colorizerStack);
		return null;
	}
}