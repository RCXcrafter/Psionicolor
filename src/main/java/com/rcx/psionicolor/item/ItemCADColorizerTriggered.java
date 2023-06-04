package com.rcx.psionicolor.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vazkii.psi.client.core.handler.ClientTickHandler;
import vazkii.psi.client.core.handler.ColorHandler;

public class ItemCADColorizerTriggered extends ItemCADColorizerHybrid implements ICastTriggeredColorizer, ITooltipItem {

	float length = 40.0f;

	public ItemCADColorizerTriggered(Properties properties) {
		super(properties);
	}

	@SuppressWarnings("resource")
	@Override
	@OnlyIn(Dist.CLIENT)
	public int getColor(ItemStack stack) {
		if (stack != null && stack.hasTag() && stack.getTag().contains(LAST_CAST) && Minecraft.getInstance().level != null) {
			Long lastCast = stack.getTag().getLong(LAST_CAST);
			float delta = (Minecraft.getInstance().level.getGameTime() - lastCast) + ClientTickHandler.partialTicks;

			if (delta < length)
				return ColorHandler.slideColorTime(getSecondaryColor(stack), getPrimaryColor(stack), (float) (Math.PI * delta / length));
		}
		return getPrimaryColor(stack);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag advanced) {
		this.addTooltip(stack, world, tooltip, advanced);
		super.appendHoverText(stack, world, tooltip, advanced);
	}
}
