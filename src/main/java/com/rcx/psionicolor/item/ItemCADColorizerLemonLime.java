package com.rcx.psionicolor.item;

import java.awt.Color;
import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vazkii.psi.client.core.handler.ClientTickHandler;

public class ItemCADColorizerLemonLime extends ItemCADColorizerBase implements ITooltipItem {

	public ItemCADColorizerLemonLime(Properties properties) {
		super(properties);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public int getColor(ItemStack stack) {
		return Color.HSBtoRGB(((float) Math.sin(ClientTickHandler.total * 0.1)) * 0.0833f + 0.25f, ((float) Math.sin(ClientTickHandler.total * 0.4)) * 0.25f + 0.75f, 1.0f);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag advanced) {
		this.addTooltip(stack, world, tooltip, advanced);
		super.appendHoverText(stack, world, tooltip, advanced);
	}
}
