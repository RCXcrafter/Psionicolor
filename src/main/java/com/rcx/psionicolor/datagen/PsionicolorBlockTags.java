package com.rcx.psionicolor.datagen;

import com.rcx.psionicolor.Psionicolor;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class PsionicolorBlockTags extends BlockTagsProvider {

	public PsionicolorBlockTags(DataGenerator gen, ExistingFileHelper existingFileHelper) {
		super(gen, Psionicolor.modID, existingFileHelper);
	}

	@Override
	protected void registerTags() {

		/*tag(BlockTags.bind(new ResourceLocation(Materialis.MODID, "test").toString()))
		.add(Blocks.DIAMOND_BLOCK)
		.addTag(BlockTags.STONE_BRICKS)
		.addTag(net.minecraftforge.common.Tags.Blocks.COBBLESTONE)
		.addOptional(new ResourceLocation("chisel", "marble/raw"))
		.addOptionalTag(new ResourceLocation("forge", "storage_blocks/ruby"));

		// Hopefully sorting issues
		tag(BlockTags.bind(new ResourceLocation(Materialis.MODID, "thing/one").toString()))
		.add(Blocks.COBBLESTONE);
		tag(BlockTags.bind(new ResourceLocation(Materialis.MODID, "thing/two").toString()))
		.add(Blocks.DIORITE);
		tag(BlockTags.bind(new ResourceLocation(Materialis.MODID, "thing/three").toString()))
		.add(Blocks.ANDESITE);

		tag(BlockTags.bind(new ResourceLocation(Materialis.MODID, "things").toString()))
		.add(Blocks.COBBLESTONE)
		.add(Blocks.DIORITE)
		.add(Blocks.ANDESITE);*/
	}
}
