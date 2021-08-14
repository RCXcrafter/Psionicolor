/*package com.rcx.psionicolor.misc;

import java.util.function.Supplier;

import com.rcx.psionicolor.Psionicolor;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;
import vazkii.psi.api.cad.ICADColorizer;

public class PsionicolorPacketHandler {

	private static final String PROTOCOL_VERSION = "1";
	public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
			new ResourceLocation(Psionicolor.modID, "main"),
			() -> PROTOCOL_VERSION,
			PROTOCOL_VERSION::equals,
			PROTOCOL_VERSION::equals
			);
	int id = 0;

	public void init() {
		INSTANCE.registerMessage(id++, ColorRequestMessage.class, ColorRequestMessage::encode, ColorRequestMessage::decode, ColorRequestMessage::handle);
		INSTANCE.registerMessage(id++, ColorSendMessage.class, ColorSendMessage::encode, ColorSendMessage::decode, ColorSendMessage::handle);
	}

	public static class ClientPacketHandler {

		public static void handlePacket(ColorRequestMessage msg, Supplier<NetworkEvent.Context> ctx) {
			ItemStack stack = msg.colorizer;
			if (stack.getItem() instanceof ICADColorizer)
				INSTANCE.sendToServer(new ColorSendMessage(((ICADColorizer) stack.getItem()).getColor(stack)));
		}
	}

	public static class ColorRequestMessage {

		public ItemStack colorizer;

		public ColorRequestMessage(ItemStack colorizer) {
			this.colorizer = colorizer;
		}

		public static void encode(ColorRequestMessage msg, PacketBuffer buffer) {
			buffer.writeItemStack(msg.colorizer, false);
		}

		public static ColorRequestMessage decode(PacketBuffer buffer) {
			return new ColorRequestMessage(buffer.readItemStack());
		}

		public static void handle(ColorRequestMessage msg, Supplier<NetworkEvent.Context> ctx) {
			ctx.get().enqueueWork(() ->
			// Make sure it's only executed on the physical client
			DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> ClientPacketHandler.handlePacket(msg, ctx))
					);
			ctx.get().setPacketHandled(true);
		}
	}

	public static class ColorSendMessage {

		public int color;

		public ColorSendMessage(int color) {
			this.color = color;
		}

		public static void encode(ColorSendMessage msg, PacketBuffer buffer) {
			buffer.writeInt(msg.color);
		}

		public static ColorSendMessage decode(PacketBuffer buffer) {
			return new ColorSendMessage(buffer.readInt());
		}

		public static void handle(ColorSendMessage msg, Supplier<NetworkEvent.Context> ctx) {
			ctx.get().enqueueWork(() -> {
				// Work that needs to be thread-safe (most work)
				//ServerPlayerEntity sender = ctx.get().getSender(); // the client that sent this packet
				// Do stuff
			});
			ctx.get().setPacketHandled(true);
		}
	}
}*/
