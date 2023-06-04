package com.rcx.psionicolor.item;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vazkii.psi.api.cad.ICADColorizer;
import vazkii.psi.client.core.handler.ColorHandler;

public class ItemCADColorizerHybrid extends ItemCADColorizerBase {

	public static final String PRIMARY_COLORIZER = "psionicolor:primary_colorizer";
	public static final String SECONDARY_COLORIZER = "psionicolor:secondary_colorizer";
	public static final String COLORIZER_NESTING = "psionicolor:colorizer_nesting_level";

	public ItemCADColorizerHybrid(Properties properties) {
		super(properties);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public int getColor(ItemStack stack) {
		float speed = 0.1f; //each level of nesting causes the levels above to become slower
		if (stack != null && stack.hasTag() && stack.getTag().contains(COLORIZER_NESTING)) {
			speed = (float) (speed * Math.pow(0.9, stack.getTag().getInt(COLORIZER_NESTING)));
		}
		return ColorHandler.slideColor(new int[] {getPrimaryColor(stack), getSecondaryColor(stack)}, speed);
	}

	@OnlyIn(Dist.CLIENT)
	public int getPrimaryColor(ItemStack stack) {
		return this.getContainedColor(stack, PRIMARY_COLORIZER, 0x0094FF);
	}

	@OnlyIn(Dist.CLIENT)
	public int getSecondaryColor(ItemStack stack) {
		return this.getContainedColor(stack, SECONDARY_COLORIZER, 0xFF6B00);
	}

	@OnlyIn(Dist.CLIENT)
	public int getContainedColor(ItemStack stack, String tag, int fallback) {
		if (stack != null && stack.hasTag() && stack.getTag().contains(tag)) {
			ItemStack colorizer = ItemStack.of(stack.getTag().getCompound(tag));
			if (colorizer.getItem() instanceof ICADColorizer) {
				return ((ICADColorizer) colorizer.getItem()).getColor(colorizer);
			}
		}
		return fallback;
	}

	public ItemStack getContainedColorizer(ItemStack stack, String tag) {
		if (stack != null && stack.hasTag() && stack.getTag().contains(tag)) {
			return ItemStack.of(stack.getTag().getCompound(tag));
		}
		return ItemStack.EMPTY;
	}
}
