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

public class PieceOperatorHSVToRGB extends PieceOperator {

	SpellParam<Vector3> color;

	public PieceOperatorHSVToRGB(Spell spell) {
		super(spell);
	}

	@Override
	public void initParams() {
		addParam(color = new ParamVector(PsionicolorLang.GENERIC_NAME_COLOR_HSV, SpellParam.GREEN, false, false));
	}

	@Override
	public Object execute(SpellContext context) throws SpellRuntimeException {
		Vector3 colorVal = this.getParamValue(context, color);
		if (colorVal == null) {
			throw new SpellRuntimeException(SpellRuntimeException.NULL_TARGET);
		}
		int colorRGB = Color.HSBtoRGB((float) colorVal.x, (float) colorVal.y, (float) colorVal.z);

		return new Vector3((0xFF0000 & colorRGB) >> 16, (0x00FF00 & colorRGB) >> 8, 0x0000FF & colorRGB);
	}

	@Override
	public Class<?> getEvaluationType() {
		return Vector3.class;
	}
}
