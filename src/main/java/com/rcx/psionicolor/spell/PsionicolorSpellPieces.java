package com.rcx.psionicolor.spell;

import com.rcx.psionicolor.Psionicolor;
import com.rcx.psionicolor.datagen.PsionicolorLang;

import net.minecraft.util.ResourceLocation;
import vazkii.psi.api.PsiAPI;
import vazkii.psi.api.spell.Spell;
import vazkii.psi.api.spell.SpellPiece;
import vazkii.psi.common.lib.LibPieceGroups;
import vazkii.psi.common.spell.base.ModSpellPieces.PieceContainer;

public class PsionicolorSpellPieces {

	public static PieceContainer selectorNearbyColorizers;

	public static PieceContainer operatorEntityColorizer;
	//public static PieceContainer operatorColorizerColor;

	public static PieceContainer trickConjureColoredBlock;
	public static PieceContainer trickConjureColoredLight;
	public static PieceContainer trickFillColorizer;
	public static PieceContainer trickFillCasterColorizer;

	public static void init() {
		selectorNearbyColorizers = register(PieceSelectorNearbyColorizers.class, PsionicolorLang.SELECTOR_NEARBY_COLORIZERS, LibPieceGroups.ENTITIES_INTRO);

		operatorEntityColorizer = register(PieceOperatorEntityColorizer.class, PsionicolorLang.OPERATOR_ENTITY_COLORIZER, LibPieceGroups.ENTITIES_INTRO);
		//operatorColorizerColor = register(PieceOperatorColorizerColor.class, PsionicolorLang.OPERATOR_COLORIZER_COLOR, LibPieceGroups.VECTORS_INTRO);

		trickConjureColoredBlock = register(PieceTrickConjureColoredBlock.class, PsionicolorLang.TRICK_CONJURE_COLORED_BLOCK, LibPieceGroups.BLOCK_CONJURATION);
		trickConjureColoredLight = register(PieceTrickConjureColoredLight.class, PsionicolorLang.TRICK_CONJURE_COLORED_LIGHT, LibPieceGroups.BLOCK_CONJURATION);
		trickFillColorizer = register(PieceTrickFillColorizer.class, PsionicolorLang.TRICK_FILL_COLORIZER, LibPieceGroups.MISC_TRICKS);
		trickFillCasterColorizer = register(PieceTrickFillCasterColorizer.class, PsionicolorLang.TRICK_FILL_CASTER_COLORIZER, LibPieceGroups.MISC_TRICKS);
	}

	public static PieceContainer register(Class<? extends SpellPiece> clazz, String name, String group) {
		return register(clazz, name, group, false);
	}

	public static PieceContainer register(Class<? extends SpellPiece> clazz, String name, String group, boolean main) {
		PsiAPI.registerSpellPieceAndTexture(new ResourceLocation(Psionicolor.modID, name), clazz);
		PsiAPI.addPieceToGroup(clazz, new ResourceLocation(Psionicolor.modID, group), main);
		return (Spell s) -> SpellPiece.create(clazz, s);
	}
}
