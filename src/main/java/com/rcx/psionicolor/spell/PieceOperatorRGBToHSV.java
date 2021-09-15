package com.rcx.psionicolor.spell;

import java.awt.Color;

import com.rcx.psionicolor.datagen.PsionicolorLang;

import vazkii.psi.api.internal.Vector3;
import vazkii.psi.api.spell.Spell;
import vazkii.psi.api.spell.SpellContext;
import vazkii.psi.api.spell.SpellParam;
import vazkii.psi.api.spell.SpellRuntimeException;
import vazkii.psi.api.spell.param.ParamVector;
import vazkii.psi.api.spell.piece.PieceOperator;

public class PieceOperatorRGBToHSV extends PieceOperator {

	SpellParam<Vector3> color;

	public PieceOperatorRGBToHSV(Spell spell) {
		super(spell);
	}

	@Override
	public void initParams() {
		addParam(color = new ParamVector(PsionicolorLang.GENERIC_NAME_COLOR_RGB, SpellParam.GREEN, false, false));
	}

	@Override
	public Object execute(SpellContext context) throws SpellRuntimeException {
		Vector3 colorVal = this.getParamValue(context, color);
		if (colorVal == null) {
			throw new SpellRuntimeException(SpellRuntimeException.NULL_TARGET);
		}
		float[] hsvValues = Color.RGBtoHSB((int) colorVal.x, (int) colorVal.y, (int) colorVal.z, null);

		return new Vector3(hsvValues[0], hsvValues[1], hsvValues[2]);
	}

	@Override
	public Class<?> getEvaluationType() {
		return Vector3.class;
	}
}
