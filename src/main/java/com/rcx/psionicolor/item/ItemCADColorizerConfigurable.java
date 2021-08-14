package com.rcx.psionicolor.item;

import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ItemCADColorizerConfigurable extends ItemCADColorizerBase {

	public static final String COLOR = "psionicolor:color";

	public ItemCADColorizerConfigurable(Properties properties) {
		super(properties);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public int getColor(ItemStack stack) {
		if (stack != null && stack.hasTag() && stack.getTag().contains(COLOR)) {
			return stack.getTag().getInt(COLOR);
		}
		return DEFAULT_SPELL_COLOR;
	}
}
