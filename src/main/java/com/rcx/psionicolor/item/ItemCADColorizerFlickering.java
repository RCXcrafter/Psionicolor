package com.rcx.psionicolor.item;

import java.util.Random;

import com.rcx.psionicolor.misc.ColorUtil;

import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemCADColorizerFlickering extends ItemCADColorizerBase {

	public final int color;
	public final int intensity;
	public final int chance;
	public final Random rand = new Random();

	public ItemCADColorizerFlickering(Properties properties, int color, int intensity, int chance) {
		super(properties);
		this.color = color;
		this.intensity = intensity;
		this.chance = chance;
	}

	public ItemCADColorizerFlickering(Properties properties, int color) {
		this(properties, color, 35, 30);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public int getColor(ItemStack stack) {
		return ColorUtil.flickerColor(color, rand, chance, intensity);
	}
}
