package com.rcx.psionicolor.item;

import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vazkii.psi.client.core.handler.ColorHandler;

public class ItemCADColorizerClock extends ItemCADColorizerBase {

	public static int[] dayColors = new int[] {
			0xFF8133,
			0xFFE240,
			0xFFE970, //midday
			0xFFE240,
			0xFF8133,
			0x185392,
			0x0E3968, //midnight
			0x185392
	};

	public static int timePerPhase = 24000 / dayColors.length;

	public ItemCADColorizerClock(Properties properties) {
		super(properties);
	}

	@SuppressWarnings("resource")
	@Override
	@OnlyIn(Dist.CLIENT)
	public int getColor(ItemStack stack) {
		if (Minecraft.getInstance().world != null) {
			int time = (int) Minecraft.getInstance().world.getDayTime() % 24000;
			int previousColor = time / timePerPhase;
			return ColorHandler.slideColorTime(dayColors[previousColor], dayColors[(previousColor + 1) % dayColors.length], (float) ((time % timePerPhase * Math.PI) / ((float) timePerPhase)));
		}
		return 0;
	}
}
