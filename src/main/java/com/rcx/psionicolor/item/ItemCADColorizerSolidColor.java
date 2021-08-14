package com.rcx.psionicolor.item;

import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemCADColorizerSolidColor extends ItemCADColorizerBase {

	public final int color;

	public ItemCADColorizerSolidColor(Properties properties, int color) {
		super(properties);
		this.color = color;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public int getColor(ItemStack stack) {
		return color;
	}
}
