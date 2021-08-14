package com.rcx.psionicolor.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public interface ITooltipItem {

	public static String tooltipKey = ".tooltip"; 

	public default void addTooltip(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag advanced) {
		tooltip.add(new TranslationTextComponent(stack.getItem().getTranslationKey() + tooltipKey).mergeStyle(TextFormatting.GRAY));
	}
}
