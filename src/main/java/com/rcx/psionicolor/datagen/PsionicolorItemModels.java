package com.rcx.psionicolor.datagen;

import com.rcx.psionicolor.Psionicolor;
import com.rcx.psionicolor.PsionicolorResources;
import com.rcx.psionicolor.PsionicolorResources.colorizerInfo;

import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.RegistryObject;

public class PsionicolorItemModels extends ItemModelProvider {

	public PsionicolorItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
		super(generator, Psionicolor.modID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		for (colorizerInfo colorizer : PsionicolorResources.colorizers) {
			if (!colorizer.customModel)
				colorizerModel(colorizer.colorizer);
		}
		withExistingParent(PsionicolorResources.HYBRID_COLORIZER.getId().getPath(), new ResourceLocation("item/generated"))
		.texture("layer0", "psionicolor:item/hybrid_dye_cannister")
		.texture("layer1", "psionicolor:item/hybrid_dye_cannister_inside_primary")
		.texture("layer2", "psionicolor:item/hybrid_dye_cannister_inside_secondary")
		.texture("layer3", "psionicolor:item/hybrid_dye_cannister_overlay");
		
		withExistingParent(PsionicolorResources.INDICATOR_COLORIZER.getId().getPath(), new ResourceLocation("item/generated"))
		.texture("layer0", "psionicolor:item/indicator_dye_cannister")
		.texture("layer1", "psionicolor:item/hybrid_dye_cannister_inside_primary")
		.texture("layer2", "psionicolor:item/indicator_dye_cannister_inside_secondary")
		.texture("layer3", "psionicolor:item/hybrid_dye_cannister_overlay");
		
		withExistingParent(PsionicolorResources.TRIGGERED_COLORIZER.getId().getPath(), new ResourceLocation("item/generated"))
		.texture("layer0", "psionicolor:item/triggered_dye_cannister")
		.texture("layer1", "psionicolor:item/hybrid_dye_cannister_inside_primary")
		.texture("layer2", "psionicolor:item/triggered_dye_cannister_inside_secondary")
		.texture("layer3", "psionicolor:item/hybrid_dye_cannister_overlay");
	}

	public void itemWithModel(RegistryObject<? extends Item> registryObject, String model) {
		ResourceLocation id = registryObject.getId();
		ResourceLocation textureLocation = new ResourceLocation(id.getNamespace(), "item/" + id.getPath());
		singleTexture(id.getPath(), new ResourceLocation(model), "layer0", textureLocation);
	}

	public void colorizerModel(RegistryObject<? extends Item> registryObject) {
		withExistingParent(registryObject.getId().getPath(), "psionicolor:item/standard_colorizer");
	}
}
