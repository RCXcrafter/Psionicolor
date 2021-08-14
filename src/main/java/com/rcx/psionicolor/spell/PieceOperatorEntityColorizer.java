package com.rcx.psionicolor.spell;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import vazkii.psi.api.PsiAPI;
import vazkii.psi.api.cad.EnumCADComponent;
import vazkii.psi.api.cad.ICAD;
import vazkii.psi.api.spell.Spell;
import vazkii.psi.api.spell.SpellContext;
import vazkii.psi.api.spell.SpellParam;
import vazkii.psi.api.spell.SpellRuntimeException;
import vazkii.psi.api.spell.param.ParamEntity;
import vazkii.psi.api.spell.piece.PieceOperator;

public class PieceOperatorEntityColorizer extends PieceOperator {

	SpellParam<Entity> player;

	public PieceOperatorEntityColorizer(Spell spell) {
		super(spell);
	}

	@Override
	public void initParams() {
		addParam(player = new ParamEntity(SpellParam.GENERIC_NAME_TARGET, SpellParam.YELLOW, false, false));
	}

	@Override
	public Object execute(SpellContext context) throws SpellRuntimeException {
		Entity e = this.getParamValue(context, player);

		if (e == null || !(e instanceof PlayerEntity) || PsiAPI.getPlayerCAD((PlayerEntity) e).isEmpty()) {
			throw new SpellRuntimeException(SpellRuntimeException.NULL_TARGET);
		}
		ItemStack cad = PsiAPI.getPlayerCAD((PlayerEntity) e);
		return new ItemEntity(context.caster.world, context.focalPoint.getPosX(), context.focalPoint.getPosY(), context.focalPoint.getPosZ(), ((ICAD) cad.getItem()).getComponentInSlot(cad, EnumCADComponent.DYE));
	}

	@Override
	public Class<?> getEvaluationType() {
		return Entity.class;
	}
}