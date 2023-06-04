package com.rcx.psionicolor.datagen;

import com.rcx.psionicolor.Psionicolor;
import com.rcx.psionicolor.PsionicolorResources;
import com.rcx.psionicolor.item.ITooltipItem;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.LanguageProvider;

public class PsionicolorLang extends LanguageProvider {

	public static final String GENERIC_NAME_COLORIZER = "psionicolor.spellparam.colorizer";
	public static final String GENERIC_NAME_COLOR_RGB = "psionicolor.spellparam.color_rgb";
	public static final String GENERIC_NAME_COLOR_HSV = "psionicolor.spellparam.color_hsv";

	public static final String SELECTOR_NEARBY_COLORIZERS = "selector_nearby_colorizers";
	public static final String OPERATOR_ENTITY_COLORIZER = "operator_entity_colorizer";
	public static final String OPERATOR_CONFIGURED_COLOR = "operator_configured_color";
	public static final String OPERATOR_RGB_TO_HSV = "operator_rgb_to_hsv";
	public static final String OPERATOR_HSV_TO_RGB = "operator_hsv_to_rgb";
	public static final String TRICK_CONJURE_COLORED_BLOCK = "trick_conjure_colored_block";
	public static final String TRICK_CONJURE_COLORED_LIGHT = "trick_conjure_colored_light";
	public static final String TRICK_CONJURE_SOLID_BLOCK = "trick_conjure_solid_block";
	public static final String TRICK_FILL_COLORIZER = "trick_fill_colorizer";
	public static final String TRICK_FILL_CASTER_COLORIZER = "trick_fill_caster_colorizer";

	public PsionicolorLang(DataGenerator gen) {
		super(gen, Psionicolor.modID, "en_us");
	}

	@Override
	protected void addTranslations() {
		add(PsionicolorResources.WHITE_NEON_COLORIZER.get(), "Neon White CAD Colorizer");
		add(PsionicolorResources.ORANGE_NEON_COLORIZER.get(), "Neon Orange CAD Colorizer");
		add(PsionicolorResources.MAGENTA_NEON_COLORIZER.get(), "Neon Magenta CAD Colorizer");
		add(PsionicolorResources.LIGHT_BLUE_NEON_COLORIZER.get(), "Neon Light Blue CAD Colorizer");
		add(PsionicolorResources.YELLOW_NEON_COLORIZER.get(), "Neon Yellow CAD Colorizer");
		add(PsionicolorResources.LIME_NEON_COLORIZER.get(), "Neon Lime CAD Colorizer");
		add(PsionicolorResources.PINK_NEON_COLORIZER.get(), "Neon Pink CAD Colorizer");
		add(PsionicolorResources.GRAY_NEON_COLORIZER.get(), "Neon Gray CAD Colorizer");
		add(PsionicolorResources.LIGHT_GRAY_NEON_COLORIZER.get(), "Neon Light Gray CAD Colorizer");
		add(PsionicolorResources.CYAN_NEON_COLORIZER.get(), "Neon Cyan CAD Colorizer");
		add(PsionicolorResources.PURPLE_NEON_COLORIZER.get(), "Neon Purple CAD Colorizer");
		add(PsionicolorResources.BLUE_NEON_COLORIZER.get(), "Neon Blue CAD Colorizer");
		add(PsionicolorResources.BROWN_NEON_COLORIZER.get(), "Neon Brown CAD Colorizer");
		add(PsionicolorResources.GREEN_NEON_COLORIZER.get(), "Neon Green CAD Colorizer");
		add(PsionicolorResources.RED_NEON_COLORIZER.get(), "Neon Red CAD Colorizer");
		add(PsionicolorResources.BLACK_NEON_COLORIZER.get(), "Neon Black CAD Colorizer");

		add(PsionicolorResources.HYBRID_COLORIZER.get(), "Hybrid Suspension CAD Colorizer");
		add(PsionicolorResources.INDICATOR_COLORIZER.get(), "Indicating Suspension CAD Colorizer");
		addTooltip(PsionicolorResources.INDICATOR_COLORIZER.get(), "Fades to the secondary color as your psi drains");
		add(PsionicolorResources.TRIGGERED_COLORIZER.get(), "Triggered Suspension CAD Colorizer");
		addTooltip(PsionicolorResources.TRIGGERED_COLORIZER.get(), "Turns to the secondary color when you cast a spell");
		add(PsionicolorResources.CONFIGURABLE_COLORIZER.get(), "Configurable CAD Colorizer");
		add(PsionicolorResources.CLOCK_COLORIZER.get(), "Clock CAD Colorizer");
		add(PsionicolorResources.ROCKIN_COLORIZER.get(), "Rockin CAD Colorizer");
		add(PsionicolorResources.RANDOM_COLORIZER.get(), "Randomized CAD Colorizer");
		add(PsionicolorResources.GRAPE_COLORIZER.get(), "Grape Flavored CAD Colorizer");
		addTooltip(PsionicolorResources.GRAPE_COLORIZER.get(), "Not suitable for consumption");
		add(PsionicolorResources.LEMON_LIME_COLORIZER.get(), "Lemon-Lime Flavored CAD Colorizer");
		addTooltip(PsionicolorResources.LEMON_LIME_COLORIZER.get(), "Not suitable for consumption");
		add(PsionicolorResources.FIRE_COLORIZER.get(), "Fiery CAD Colorizer");
		add(PsionicolorResources.RAINBOW_COLORIZER.get(), "Clown Vomit CAD Colorizer");

		//add(PsionicolorResources.SOLID_CONJURED_BLOCK.get(), "Conjured Block");

		add(GENERIC_NAME_COLORIZER, "Colorizer");
		add(GENERIC_NAME_COLOR_RGB, "Color RGB");
		add(GENERIC_NAME_COLOR_HSV, "Color HSV");
		addPiece(SELECTOR_NEARBY_COLORIZERS, "Selector: Nearby Colorizers", "Selects colorizer items near the given position");
		addPiece(OPERATOR_ENTITY_COLORIZER, "Operator: Entity Colorizer", "Gets the colorizer in the cad of the selected entity, can't be modified");
		addPiece(OPERATOR_CONFIGURED_COLOR, "Operator: Configured Color", "Gets the current Color from a configurable colorizer");
		addPiece(OPERATOR_RGB_TO_HSV, "Operator: RGB To HSV", "Converts Red Green Blue to Hue Saturation Value");
		addPiece(OPERATOR_HSV_TO_RGB, "Operator: HSV To RGB", "Converts Hue Saturation Value To Red Green Blue");
		addPiece(TRICK_CONJURE_COLORED_BLOCK, "Trick: Conjure Colored Block", "Conjures a fragile block");
		addPiece(TRICK_CONJURE_COLORED_LIGHT, "Trick: Conjure Colored Light", "Conjures a fragile light");
		addPiece(TRICK_CONJURE_SOLID_BLOCK, "Trick: Conjure Solid Block", "Conjures a solid colored block");
		addPiece(TRICK_FILL_COLORIZER, "Trick: Fill Colorizer", "Fills a configurable colorizer with the specified color");
		addPiece(TRICK_FILL_CASTER_COLORIZER, "Trick: Fill Caster Colorizer", "Fills a configurable colorizer in the caster's CAD with the specified color");
	}

	public void addTooltip(Item key, String tooltip) {
		add(key.getDescriptionId() + ITooltipItem.tooltipKey, tooltip);
	}

	public void addPiece(String key, String name, String desc) {
		add(Psionicolor.modID + ".spellpiece." + key, name);
		add(Psionicolor.modID + ".spellpiece." + key + ".desc", desc);
	}
}
