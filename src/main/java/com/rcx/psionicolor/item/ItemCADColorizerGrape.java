package com.rcx.psionicolor.item;

import java.util.List;

import javax.annotation.Nullable;

import com.rcx.psionicolor.misc.ColorUtil;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
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
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag advanced) {
		this.addTooltip(stack, world, tooltip, advanced);
		super.appendHoverText(stack, world, tooltip, advanced);
	}
}
