package com.rcx.psionicolor.spell;

import com.rcx.psionicolor.datagen.PsionicolorLang;
import com.rcx.psionicolor.item.ItemCADColorizerConfigurable;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import vazkii.psi.api.cad.ICADColorizer;
import vazkii.psi.api.internal.Vector3;
import vazkii.psi.api.spell.Spell;
import vazkii.psi.api.spell.SpellContext;
import vazkii.psi.api.spell.SpellParam;
import vazkii.psi.api.spell.SpellRuntimeException;
import vazkii.psi.api.spell.param.ParamEntity;
import vazkii.psi.api.spell.piece.PieceOperator;

public class PieceOperatorConfiguredColor extends PieceOperator {

	SpellParam<Entity> colorizer;

	public PieceOperatorConfiguredColor(Spell spell) {
		super(spell);
	}

	@Override
	public void initParams() {
		addParam(colorizer = new ParamEntity(PsionicolorLang.GENERIC_NAME_COLORIZER, SpellParam.YELLOW, false, false));
	}

	@Override
	public Object execute(SpellContext context) throws SpellRuntimeException {
		Entity e = this.getParamValue(context, colorizer);

		if (e == null || !(e instanceof ItemEntity) || !(((ItemEntity) e).getItem().getItem() instanceof ICADColorizer)) {
			throw new SpellRuntimeException(SpellRuntimeException.NULL_TARGET);
		}

		ItemStack stack = ((ItemEntity) e).getItem();

		if (stack != null && stack.hasTag() && stack.getTag().contains(ItemCADColorizerConfigurable.COLOR)) {
			int color = stack.getTag().getInt(ItemCADColorizerConfigurable.COLOR);
			return new Vector3((0xFF0000 & color) >> 16, (0x00FF00 & color) >> 8, 0x0000FF & color);
		} else
			throw new SpellRuntimeException(SpellRuntimeException.NULL_TARGET);
	}

	@Override
	public Class<?> getEvaluationType() {
		return Vector3.class;
	}
}
