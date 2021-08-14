package com.rcx.psionicolor.item;

import java.util.List;

import javax.annotation.Nullable;

import com.rcx.psionicolor.misc.ColorUtil;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vazkii.psi.client.core.handler.ClientTickHandler;

public class ItemCADColorizerGrape extends ItemCADColorizerBase implements ITooltipItem {

	public ItemCADColorizerGrape(Properties properties) {
		super(properties);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public int getColor(ItemStack stack) {
		float w = (float) (Math.sin(ClientTickHandler.total * 0.4) * 0.5 + 0.5) * 0.1f;
		int red = (int) ((0.5f + w) * 255);
		int green = 30;
		int blue = (int) (((Math.sin(ClientTickHandler.total * 0.1) * 0.5 + 0.5) * 0.5f + 0.25f + w) * 255);
		return ColorUtil.RGBToInt(red, green, blue);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag advanced) {
		this.addTooltip(stack, world, tooltip, advanced);
		super.addInformation(stack, world, tooltip, advanced);
	}
}
