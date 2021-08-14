package com.rcx.psionicolor.item;

import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vazkii.psi.client.core.handler.ClientTickHandler;
import vazkii.psi.client.core.handler.ColorHandler;

public class ItemCADColorizerRandom extends ItemCADColorizerBase {

	public final Random rand = new Random();
	public int ticksPerColor = 20;

	public ItemCADColorizerRandom(Properties properties) {
		super(properties);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public int getColor(ItemStack stack) {
		float time = (ClientTickHandler.total % ticksPerColor) / (float) ticksPerColor;
		int phase = ((int) ClientTickHandler.total) / ticksPerColor;
		return ColorHandler.slideColorTime(new Random(phase).nextInt(0xFFFFFF + 1), new Random(phase + 1).nextInt(0xFFFFFF + 1), (float) (time * Math.PI));
	}
}
