package com.rcx.psionicolor.item;

import java.util.List;

import javax.annotation.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
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
		if (stack != null && stack.hasTag() && stack.getTag().hasUUID(OWNING_PLAYER) && Minecraft.getInstance().level != null) {
			Player player = Minecraft.getInstance().level.getPlayerByUUID(stack.getTag().getUUID(OWNING_PLAYER));
			if (player != null) {
				PlayerData data = PlayerDataHandler.get(player);
				return ColorHandler.slideColorTime(getSecondaryColor(stack), getPrimaryColor(stack), (float) (Math.PI * data.getAvailablePsi() / ((float) data.getTotalPsi())));
			}
		}
		return getPrimaryColor(stack);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag advanced) {
		this.addTooltip(stack, world, tooltip, advanced);
		super.appendHoverText(stack, world, tooltip, advanced);
	}
}
