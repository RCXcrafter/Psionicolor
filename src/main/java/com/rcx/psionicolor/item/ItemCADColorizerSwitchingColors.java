package com.rcx.psionicolor.item;

import com.rcx.psionicolor.misc.ColorUtil;

import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemCADColorizerSwitchingColors extends ItemCADColorizerBase {

	public final int[] colors;
	public final float speed;

	public ItemCADColorizerSwitchingColors(Properties properties, float speed, int... colors) {
		super(properties);
		this.speed = speed;
		this.colors = colors;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public int getColor(ItemStack stack) {
		return ColorUtil.switchColor(colors, speed);
	}
}
