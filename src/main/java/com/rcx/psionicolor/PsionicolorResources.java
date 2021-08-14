package com.rcx.psionicolor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import com.rcx.psionicolor.item.ItemCADColorizerClock;
import com.rcx.psionicolor.item.ItemCADColorizerConfigurable;
import com.rcx.psionicolor.item.ItemCADColorizerFire;
import com.rcx.psionicolor.item.ItemCADColorizerFlickering;
import com.rcx.psionicolor.item.ItemCADColorizerGrape;
import com.rcx.psionicolor.item.ItemCADColorizerHybrid;
import com.rcx.psionicolor.item.ItemCADColorizerIndicator;
import com.rcx.psionicolor.item.ItemCADColorizerLemonLime;
import com.rcx.psionicolor.item.ItemCADColorizerRandom;
import com.rcx.psionicolor.item.ItemCADColorizerSlidingColors;
import com.rcx.psionicolor.item.ItemCADColorizerSwitchingColors;
import com.rcx.psionicolor.item.ItemCADColorizerTriggered;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import vazkii.psi.common.core.PsiCreativeTab;

public class PsionicolorResources {

	public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, Psionicolor.modID);
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Psionicolor.modID);

	/*
	 * BLOCKS
	 */


	/*
	 * ITEMS
	 */

	public static List<colorizerInfo> colorizers = new ArrayList<colorizerInfo>();

	public static Item.Properties psiProps = new Item.Properties().group(PsiCreativeTab.INSTANCE);

	//hybrid suspension colorizers
	public static final RegistryObject<Item> HYBRID_COLORIZER = registerColorizer("cad_colorizer_hybrid", () -> new ItemCADColorizerHybrid(psiProps), true, true);
	public static final RegistryObject<Item> INDICATOR_COLORIZER = registerColorizer("cad_colorizer_indicator", () -> new ItemCADColorizerIndicator(psiProps), true, true);
	public static final RegistryObject<Item> TRIGGERED_COLORIZER = registerColorizer("cad_colorizer_triggered", () -> new ItemCADColorizerTriggered(psiProps), true, true);

	//bright dye colorizers
	public static final RegistryObject<Item> WHITE_NEON_COLORIZER = registerColorizer("cad_colorizer_white_neon", () -> new ItemCADColorizerFlickering(psiProps, 0xFFFFFF));
	public static final RegistryObject<Item> ORANGE_NEON_COLORIZER = registerColorizer("cad_colorizer_orange_neon", () -> new ItemCADColorizerFlickering(psiProps, 0xFF8000));
	public static final RegistryObject<Item> MAGENTA_NEON_COLORIZER = registerColorizer("cad_colorizer_magenta_neon", () -> new ItemCADColorizerFlickering(psiProps, 0xFF00FF));
	public static final RegistryObject<Item> LIGHT_BLUE_NEON_COLORIZER = registerColorizer("cad_colorizer_light_blue_neon", () -> new ItemCADColorizerFlickering(psiProps, 0x80D9FF));
	public static final RegistryObject<Item> YELLOW_NEON_COLORIZER = registerColorizer("cad_colorizer_yellow_neon", () -> new ItemCADColorizerFlickering(psiProps, 0xFFFF00));
	public static final RegistryObject<Item> LIME_NEON_COLORIZER = registerColorizer("cad_colorizer_lime_neon", () -> new ItemCADColorizerFlickering(psiProps, 0x00FF00));
	public static final RegistryObject<Item> PINK_NEON_COLORIZER = registerColorizer("cad_colorizer_pink_neon", () -> new ItemCADColorizerFlickering(psiProps, 0xFF80FF));
	public static final RegistryObject<Item> GRAY_NEON_COLORIZER = registerColorizer("cad_colorizer_gray_neon", () -> new ItemCADColorizerFlickering(psiProps, 0x555555));
	public static final RegistryObject<Item> LIGHT_GRAY_NEON_COLORIZER = registerColorizer("cad_colorizer_light_gray_neon", () -> new ItemCADColorizerFlickering(psiProps, 0xAAAAAA));
	public static final RegistryObject<Item> CYAN_NEON_COLORIZER = registerColorizer("cad_colorizer_cyan_neon", () -> new ItemCADColorizerFlickering(psiProps, 0x00FFFF));
	public static final RegistryObject<Item> PURPLE_NEON_COLORIZER = registerColorizer("cad_colorizer_purple_neon", () -> new ItemCADColorizerFlickering(psiProps, 0x8000FF));
	public static final RegistryObject<Item> BLUE_NEON_COLORIZER = registerColorizer("cad_colorizer_blue_neon", () -> new ItemCADColorizerFlickering(psiProps, 0x0000FF));
	public static final RegistryObject<Item> BROWN_NEON_COLORIZER = registerColorizer("cad_colorizer_brown_neon", () -> new ItemCADColorizerFlickering(psiProps, 0x801F00));
	public static final RegistryObject<Item> GREEN_NEON_COLORIZER = registerColorizer("cad_colorizer_green_neon", () -> new ItemCADColorizerFlickering(psiProps, 0x008000));
	public static final RegistryObject<Item> RED_NEON_COLORIZER = registerColorizer("cad_colorizer_red_neon", () -> new ItemCADColorizerFlickering(psiProps, 0xFF0000));
	public static final RegistryObject<Item> BLACK_NEON_COLORIZER = registerColorizer("cad_colorizer_black_neon", () -> new ItemCADColorizerFlickering(psiProps, 0x232323));

	//configurable colorizer
	public static final RegistryObject<Item> CONFIGURABLE_COLORIZER = registerColorizer("cad_colorizer_configurable", () -> new ItemCADColorizerConfigurable(psiProps));

	//clock colorizer
	public static final RegistryObject<Item> CLOCK_COLORIZER = registerColorizer("cad_colorizer_clock", () -> new ItemCADColorizerClock(psiProps));

	//rockin colorizer
	public static final RegistryObject<Item> ROCKIN_COLORIZER = registerColorizer("cad_colorizer_rockin", () -> new ItemCADColorizerSlidingColors(psiProps, 0.05f, 0x0000FF, 0x0000FF, 0x0000FF, 0xFF0000, 0xFF0000, 0xFFFF00, 0xFFFF00, 0xFFFF00, 0xFF0000, 0xFF0000));

	//random colorizer
	public static final RegistryObject<Item> RANDOM_COLORIZER = registerColorizer("cad_colorizer_random", () -> new ItemCADColorizerRandom(psiProps));

	//soda colorizers
	public static final RegistryObject<Item> GRAPE_COLORIZER = registerColorizer("cad_colorizer_grape", () -> new ItemCADColorizerGrape(psiProps));
	public static final RegistryObject<Item> LEMON_LIME_COLORIZER = registerColorizer("cad_colorizer_lemon_lime", () -> new ItemCADColorizerLemonLime(psiProps));

	//fiery colorizer
	public static final RegistryObject<Item> FIRE_COLORIZER = registerColorizer("cad_colorizer_fire", () -> new ItemCADColorizerFire(psiProps));

	//clown vomit colorizer
	public static final RegistryObject<Item> RAINBOW_COLORIZER = registerColorizer("cad_colorizer_rainbow", () -> new ItemCADColorizerSwitchingColors(new Item.Properties(), 1.0f, 0xFF0000, 0xFFFF00, 0x00FF00, 0x00FFFF, 0x0000FF, 0xFF00FF));

	public static RegistryObject<Item> registerColorizer(final String name, final Supplier<? extends Item> sup) {
		return registerColorizer(name, sup, false, false);
	}

	public static RegistryObject<Item> registerColorizer(final String name, final Supplier<? extends Item> sup, boolean customModel, boolean customColors) {
		RegistryObject<Item> item = ITEMS.register(name, sup);
		colorizers.add(new colorizerInfo(item, customModel, customColors));
		return item;
	}

	public static class colorizerInfo {

		public final RegistryObject<Item> colorizer;
		public final boolean customModel;
		public final boolean customColors;

		public colorizerInfo(RegistryObject<Item> colorizer, boolean customModel, boolean customColors) {
			this.colorizer = colorizer;
			this.customModel = customModel;
			this.customColors = customColors;
		}
	}
}
