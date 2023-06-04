package com.rcx.psionicolor.item;

import java.awt.Color;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vazkii.psi.client.core.handler.ClientTickHandler;

public class ItemCADColorizerFire extends ItemCADColorizerBase {

	public ItemCADColorizerFire(Properties properties) {
		super(properties);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public int getColor(ItemStack stack) {
		float shift = (float) Math.sin(ClientTickHandler.total * 0.1);
		return Color.HSBtoRGB(shift * 0.04f + 0.0833f, Math.min(1.0f, Math.max((((float) Math.sin(ClientTickHandler.total * 0.4)) * 0.125f + 0.875f), -shift * 0.5f + 1.0f)), 1.0f);
	}
}
