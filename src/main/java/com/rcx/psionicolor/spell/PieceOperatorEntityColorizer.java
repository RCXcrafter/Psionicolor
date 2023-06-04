package com.rcx.psionicolor.spell;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
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

		if (e == null || !(e instanceof Player) || PsiAPI.getPlayerCAD((Player) e).isEmpty()) {
			throw new SpellRuntimeException(SpellRuntimeException.NULL_TARGET);
		}
		ItemStack cad = PsiAPI.getPlayerCAD((Player) e);
		return new ItemEntity(context.caster.level, context.focalPoint.getX(), context.focalPoint.getY(), context.focalPoint.getZ(), ((ICAD) cad.getItem()).getComponentInSlot(cad, EnumCADComponent.DYE));
	}

	@Override
	public Class<?> getEvaluationType() {
		return Entity.class;
	}
}