package com.rcx.psionicolor.misc;

import java.util.Random;

import net.minecraft.util.math.MathHelper;
import vazkii.psi.client.core.handler.ClientTickHandler;

public class ColorUtil {

	public static int switchColor(int[] color, float speed) {
		int n = color.length;
		int phase = (int) (ClientTickHandler.total * speed * n / Math.PI) % n;
		return color[phase];
	}

	public static int flickerColor(int color, Random rand, int chance, int intensity) {
		if (rand.nextInt(chance) == 0) {
			int flicker = rand.nextInt(intensity);
			int red = ((0xFF0000 & color) >> 16) - flicker;
			int green = ((0x00FF00 & color) >> 8) - flicker;
			int blue = (0x0000FF & color) - flicker;
			return RGBToInt(red, green, blue);
		}
		return color;
	}

	public static int RGBToInt(int red, int green, int blue) {
		return (MathHelper.clamp(red, 0, 255) << 16) | (MathHelper.clamp(green, 0, 255) << 8) | MathHelper.clamp(blue, 0, 255);
	}
}
