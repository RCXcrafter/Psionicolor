package com.rcx.psionicolor.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
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
		if (stack != null && stack.hasTag() && stack.getTag().contains(LAST_CAST) && Minecraft.getInstance().world != null) {
			Long lastCast = stack.getTag().getLong(LAST_CAST);
			float delta = (Minecraft.getInstance().world.getGameTime() - lastCast) + ClientTickHandler.partialTicks;

			if (delta < length)
				return ColorHandler.slideColorTime(getSecondaryColor(stack), getPrimaryColor(stack), (float) (Math.PI * delta / length));
		}
		return getPrimaryColor(stack);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag advanced) {
		this.addTooltip(stack, world, tooltip, advanced);
		super.addInformation(stack, world, tooltip, advanced);
	}
}
