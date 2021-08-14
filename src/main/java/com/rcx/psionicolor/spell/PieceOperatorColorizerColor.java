package com.rcx.psionicolor.spell;

import com.rcx.psionicolor.datagen.PsionicolorLang;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import vazkii.psi.api.cad.ICADColorizer;
import vazkii.psi.api.internal.Vector3;
import vazkii.psi.api.spell.Spell;
import vazkii.psi.api.spell.SpellContext;
import vazkii.psi.api.spell.SpellParam;
import vazkii.psi.api.spell.SpellRuntimeException;
import vazkii.psi.api.spell.param.ParamEntity;
import vazkii.psi.api.spell.piece.PieceOperator;

public class PieceOperatorColorizerColor extends PieceOperator {

	SpellParam<Entity> colorizer;

	public PieceOperatorColorizerColor(Spell spell) {
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
		if (context.caster instanceof ServerPlayerEntity)
			return null;

		ItemStack stack = ((ItemEntity) e).getItem();

		//PsionicolorPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) context.caster), new PsionicolorPacketHandler.ColorRequestMessage(stack));

		
		int color = ((ICADColorizer) stack.getItem()).getColor(stack);
		return new Vector3((0xFF0000 & color) >> 16, (0x00FF00 & color) >> 8, 0x0000FF & color);
	}

	@Override
	public Class<?> getEvaluationType() {
		return Vector3.class;
	}
}
