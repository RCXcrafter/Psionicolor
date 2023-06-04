package com.rcx.psionicolor.datagen;

import com.rcx.psionicolor.Psionicolor;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class PsionicolorBlockStates extends BlockStateProvider {

	public PsionicolorBlockStates(DataGenerator gen, ExistingFileHelper exFileHelper) {
		super(gen, Psionicolor.modID, exFileHelper);
	}

	@Override
	protected void registerStatesAndModels() {
		//simpleBlock(PsionicolorResources.SOLID_CONJURED_BLOCK.get());
		
		//getVariantBuilder(PsionicolorResources.SOLID_CONJURED_BLOCK.get()).partialState().setModels(new ConfiguredModel(cubeAll(PsionicolorResources.SOLID_CONJURED_BLOCK.get())));
		
		
		
	}

	public void blockWithItem(RegistryObject<? extends Block> registryObject) {
		//block model
		simpleBlock(registryObject.get());
		
		//itemblock model
		ResourceLocation id = registryObject.getId();
		ResourceLocation textureLocation = new ResourceLocation(id.getNamespace(), "block/" + id.getPath());
		itemModels().cubeAll(id.getPath(), textureLocation);
	}

	public void fluid(RegistryObject<? extends Block> fluid) {
		ResourceLocation name = fluid.get().getRegistryName();
		simpleBlock(fluid.get(), models().cubeAll(name.getPath(), new ResourceLocation(name.getNamespace(), ModelProvider.BLOCK_FOLDER + "/fluid/" + name.getPath().replace("block", "still"))));
	}
	
}
