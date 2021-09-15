package com.rcx.psionicolor.spell;

import com.rcx.psionicolor.datagen.PsionicolorLang;
import com.rcx.psionicolor.item.ItemCADColorizerConfigurable;
import com.rcx.psionicolor.misc.ColorUtil;

import net.minecraft.item.ItemStack;
import vazkii.psi.api.PsiAPI;
import vazkii.psi.api.cad.EnumCADComponent;
import vazkii.psi.api.cad.ICAD;
import vazkii.psi.api.internal.Vector3;
import vazkii.psi.api.spell.Spell;
import vazkii.psi.api.spell.SpellContext;
import vazkii.psi.api.spell.SpellParam;
import vazkii.psi.api.spell.SpellRuntimeException;
import vazkii.psi.api.spell.param.ParamVector;
import vazkii.psi.api.spell.piece.PieceTrick;

public class PieceTrickFillCasterColorizer extends PieceTrick {

	SpellParam<Vector3> color;

	public PieceTrickFillCasterColorizer(Spell spell) {
		super(spell);
	}

	@Override
	public void initParams() {
		addParam(color = new ParamVector(PsionicolorLang.GENERIC_NAME_COLOR_RGB, SpellParam.GREEN, false, false));
	}

	@Override
	public Object execute(SpellContext context) throws SpellRuntimeException {
		Vector3 colorVal = this.getParamValue(context, color);
		ItemStack cad = PsiAPI.getPlayerCAD(context.caster);
		ItemStack colorizer = ((ICAD) cad.getItem()).getComponentInSlot(cad, EnumCADComponent.DYE);

		if (colorVal == null || !(colorizer.getItem() instanceof ItemCADColorizerConfigurable)) {
			throw new SpellRuntimeException(SpellRuntimeException.NULL_TARGET);
		}
		colorizer.getOrCreateTag().putInt(ItemCADColorizerConfigurable.COLOR, ColorUtil.RGBToInt((int) colorVal.x, (int) colorVal.y, (int) colorVal.z));
		((ICAD) cad.getItem()).setCADComponent(cad, colorizer);
		return null;
	}
}