package com.rcx.psionicolor.item;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vazkii.psi.client.core.handler.ColorHandler;

public class ItemCADColorizerSlidingColors extends ItemCADColorizerBase {

	public final int[] colors;
	public final float speed;

	public ItemCADColorizerSlidingColors(Properties properties, float speed, int... colors) {
		super(properties);
		this.speed = speed;
		this.colors = colors;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public int getColor(ItemStack stack) {
		return ColorHandler.slideColor(colors, speed);
	}
}
