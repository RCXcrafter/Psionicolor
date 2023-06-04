package com.rcx.psionicolor.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

public interface ITooltipItem {

	public static String tooltipKey = ".tooltip"; 

	public default void addTooltip(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag advanced) {
		tooltip.add(new TranslatableComponent(stack.getItem().getDescriptionId() + tooltipKey).withStyle(ChatFormatting.GRAY));
	}
}
