package com.rcx.psionicolor.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import vazkii.psi.api.cad.ICADColorizer;
import vazkii.psi.common.item.component.ItemCADComponent;

public abstract class ItemCADColorizerBase extends ItemCADComponent implements ICADColorizer {

	public ItemCADColorizerBase(Item.Properties properties) {
		super(properties);
	}

	@Override
	public String getContributorName(ItemStack stack) {
		return "";
	}

	@Override
	public void setContributorName(ItemStack stack, String name) {
	}
}
