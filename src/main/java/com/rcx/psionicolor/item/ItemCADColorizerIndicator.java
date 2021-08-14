package com.rcx.psionicolor.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import vazkii.psi.client.core.handler.ColorHandler;
import vazkii.psi.common.core.handler.PlayerDataHandler;
import vazkii.psi.common.core.handler.PlayerDataHandler.PlayerData;

public class ItemCADColorizerIndicator extends ItemCADColorizerHybrid implements IPlayerboundColorizer, ITooltipItem {

	public ItemCADColorizerIndicator(Properties properties) {
		super(properties);
	}

	@SuppressWarnings("resource")
	@Override
	@OnlyIn(Dist.CLIENT)
	public int getColor(ItemStack stack) {
		if (stack != null && stack.hasTag() && stack.getTag().hasUniqueId(OWNING_PLAYER) && Minecraft.getInstance().world != null) {
			PlayerEntity player = Minecraft.getInstance().world.getPlayerByUuid(stack.getTag().getUniqueId(OWNING_PLAYER));
			if (player != null) {
				PlayerData data = PlayerDataHandler.get(player);
				return ColorHandler.slideColorTime(getSecondaryColor(stack), getPrimaryColor(stack), (float) (Math.PI * data.getAvailablePsi() / ((float) data.getTotalPsi())));
			}
		}
		return getPrimaryColor(stack);
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag advanced) {
		this.addTooltip(stack, world, tooltip, advanced);
		super.addInformation(stack, world, tooltip, advanced);
	}
}
