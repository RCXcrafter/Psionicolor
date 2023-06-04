package com.rcx.psionicolor.datagen;

import com.rcx.psionicolor.Psionicolor;
import com.rcx.psionicolor.PsionicolorResources;
import com.rcx.psionicolor.PsionicolorResources.colorizerInfo;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;

public class PsionicolorItemTags extends ItemTagsProvider {

	public static final TagKey<Item> COLORIZERS = ItemTags.create(new ResourceLocation("materialis", "psi_colorizers"));
	public static final TagKey<Item> COLORIZERS_COMPONENTS = ItemTags.create(new ResourceLocation("psi", "colorizers"));

	public PsionicolorItemTags(DataGenerator gen, BlockTagsProvider blockTags, ExistingFileHelper existingFileHelper) {
		super(gen, blockTags, Psionicolor.modID, existingFileHelper);
	}

	@Override
	protected void addTags() {
		for (colorizerInfo colorizer : PsionicolorResources.colorizers) {
			tag(COLORIZERS).add(colorizer.colorizer.get());
			tag(COLORIZERS_COMPONENTS).add(colorizer.colorizer.get());
		}
		tag(COLORIZERS_COMPONENTS).addOptional(new ResourceLocation("psi", "cad_colorizer_white"))
		.addOptional(new ResourceLocation("psi", "cad_colorizer_orange"))
		.addOptional(new ResourceLocation("psi", "cad_colorizer_magenta"))
		.addOptional(new ResourceLocation("psi", "cad_colorizer_light_blue"))
		.addOptional(new ResourceLocation("psi", "cad_colorizer_yellow"))
		.addOptional(new ResourceLocation("psi", "cad_colorizer_lime"))
		.addOptional(new ResourceLocation("psi", "cad_colorizer_pink"))
		.addOptional(new ResourceLocation("psi", "cad_colorizer_gray"))
		.addOptional(new ResourceLocation("psi", "cad_colorizer_light_gray"))
		.addOptional(new ResourceLocation("psi", "cad_colorizer_cyan"))
		.addOptional(new ResourceLocation("psi", "cad_colorizer_purple"))
		.addOptional(new ResourceLocation("psi", "cad_colorizer_blue"))
		.addOptional(new ResourceLocation("psi", "cad_colorizer_brown"))
		.addOptional(new ResourceLocation("psi", "cad_colorizer_green"))
		.addOptional(new ResourceLocation("psi", "cad_colorizer_red"))
		.addOptional(new ResourceLocation("psi", "cad_colorizer_black"))
		.addOptional(new ResourceLocation("psi", "cad_colorizer_rainbow"))
		.addOptional(new ResourceLocation("psi", "cad_colorizer_psi"))
		.addOptional(new ResourceLocation("psi", "cad_colorizer_empty"));
	}
}
