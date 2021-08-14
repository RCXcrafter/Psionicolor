package com.rcx.psionicolor.spell;

import javax.annotation.Nullable;

import com.rcx.psionicolor.PsionicolorResources;
import com.rcx.psionicolor.datagen.PsionicolorLang;
import com.rcx.psionicolor.item.ItemCADColorizerConfigurable;
import com.rcx.psionicolor.misc.ColorUtil;

import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import vazkii.psi.api.cad.ICADColorizer;
import vazkii.psi.api.internal.Vector3;
import vazkii.psi.api.spell.EnumSpellStat;
import vazkii.psi.api.spell.Spell;
import vazkii.psi.api.spell.SpellCompilationException;
import vazkii.psi.api.spell.SpellContext;
import vazkii.psi.api.spell.SpellMetadata;
import vazkii.psi.api.spell.SpellParam;
import vazkii.psi.api.spell.SpellRuntimeException;
import vazkii.psi.api.spell.param.ParamEntity;
import vazkii.psi.api.spell.param.ParamNumber;
import vazkii.psi.api.spell.param.ParamVector;
import vazkii.psi.api.spell.piece.PieceTrick;
import vazkii.psi.common.block.BlockConjured;
import vazkii.psi.common.block.base.ModBlocks;
import vazkii.psi.common.block.tile.TileConjured;
import vazkii.psi.common.spell.trick.block.PieceTrickConjureBlock;

public class PieceTrickConjureColoredBlock extends PieceTrick {

	SpellParam<Vector3> position;
	SpellParam<Number> time;
	SpellParam<Vector3> color;
	SpellParam<Entity> colorizer;

	public PieceTrickConjureColoredBlock(Spell spell) {
		super(spell);
	}

	@Override
	public void initParams() {
		addParam(position = new ParamVector(SpellParam.GENERIC_NAME_POSITION, SpellParam.BLUE, false, false));
		addParam(time = new ParamNumber(SpellParam.GENERIC_NAME_TIME, SpellParam.RED, true, false));
		addParam(color = new ParamVector(PsionicolorLang.GENERIC_NAME_COLOR, SpellParam.GREEN, true, false));
		addParam(colorizer = new ParamEntity(PsionicolorLang.GENERIC_NAME_COLORIZER, SpellParam.YELLOW, true, false));
	}

	@Override
	public void addToMetadata(SpellMetadata meta) throws SpellCompilationException {
		super.addToMetadata(meta);
		addStats(meta);
	}

	public void addStats(SpellMetadata meta) throws SpellCompilationException {
		meta.addStat(EnumSpellStat.POTENCY, 15);
		meta.addStat(EnumSpellStat.COST, 20);
	}

	@Override
	public Object execute(SpellContext context) throws SpellRuntimeException {
		Vector3 positionVal = this.getParamValue(context, position);
		Number timeVal = this.getParamValue(context, time);
		Vector3 colorVal = this.getParamValue(context, color);
		Entity colorizerVal = this.getParamValue(context, colorizer);

		if (positionVal == null) {
			throw new SpellRuntimeException(SpellRuntimeException.NULL_VECTOR);
		}
		if (!context.isInRadius(positionVal)) {
			throw new SpellRuntimeException(SpellRuntimeException.OUTSIDE_RADIUS);
		}
		BlockPos pos = positionVal.toBlockPos();
		World world = context.focalPoint.getEntityWorld();

		if (!world.isBlockModifiable(context.caster, pos)) {
			return null;
		}

		ItemStack colorizerStack = ItemStack.EMPTY;
		if (colorizerVal != null && colorizerVal instanceof ItemEntity && ((ItemEntity) colorizerVal).getItem().getItem() instanceof ICADColorizer) {
			colorizerStack = ((ItemEntity) colorizerVal).getItem();
		}

		conjure(context, timeVal, pos, world, messWithState(ModBlocks.conjured.getDefaultState()), colorVal, colorizerStack);

		return null;
	}

	public static void conjure(SpellContext context, @Nullable Number timeVal, BlockPos pos, World world, BlockState state, @Nullable Vector3 color, @Nullable ItemStack colorizer) {
		if (color != null || colorizer != ItemStack.EMPTY) {
			if (world.getBlockState(pos).getBlock() != state.getBlock()) {
				if (PieceTrickConjureBlock.conjure(world, pos, context.caster, state)) {
					if (timeVal != null && timeVal.intValue() > 0) {
						int val = timeVal.intValue();
						world.getPendingBlockTicks().scheduleTick(pos, state.getBlock(), val);
					}
					TileEntity tile = world.getTileEntity(pos);

					if (colorizer == ItemStack.EMPTY) {
						colorizer = new ItemStack(PsionicolorResources.CONFIGURABLE_COLORIZER.get());
						colorizer.getOrCreateTag().putInt(ItemCADColorizerConfigurable.COLOR, ColorUtil.RGBToInt((int) color.x, (int) color.y, (int) color.z));
					}
					if (tile instanceof TileConjured && !colorizer.isEmpty()) {
						((TileConjured) tile).colorizer = colorizer;
					}

				}
			}
		} else {
			PieceTrickConjureBlock.conjure(context, timeVal, pos, world, state);
		}
	}

	public BlockState messWithState(BlockState state) {
		return state.with(BlockConjured.SOLID, true);
	}
}
