package com.rcx.psionicolor;

import com.rcx.psionicolor.PsionicolorResources.colorizerInfo;
import com.rcx.psionicolor.datagen.PsionicolorBlockStates;
import com.rcx.psionicolor.datagen.PsionicolorBlockTags;
import com.rcx.psionicolor.datagen.PsionicolorItemModels;
import com.rcx.psionicolor.datagen.PsionicolorItemTags;
import com.rcx.psionicolor.datagen.PsionicolorLang;
import com.rcx.psionicolor.datagen.PsionicolorLootTables;
import com.rcx.psionicolor.datagen.PsionicolorRecipes;
import com.rcx.psionicolor.item.ICastTriggeredColorizer;
import com.rcx.psionicolor.item.IPlayerboundColorizer;
import com.rcx.psionicolor.item.ItemCADColorizerBase;
import com.rcx.psionicolor.item.ItemCADColorizerHybrid;
import com.rcx.psionicolor.misc.HybridColorizerRecipe;
import com.rcx.psionicolor.spell.PsionicolorSpellPieces;

import net.minecraft.client.renderer.color.ItemColors;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import vazkii.psi.api.cad.CADTakeEvent;
import vazkii.psi.api.cad.EnumCADComponent;
import vazkii.psi.api.cad.ICAD;
import vazkii.psi.api.spell.PreSpellCastEvent;
import vazkii.psi.common.item.ItemCAD;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("psionicolor")
@Mod.EventBusSubscriber(bus = Bus.MOD)
public class Psionicolor {

	public static final String modID = "psionicolor";

	public Psionicolor() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

		PsionicolorResources.BLOCKS.register(bus);
		PsionicolorResources.ITEMS.register(bus);

		MinecraftForge.EVENT_BUS.register(this);
		MinecraftForge.EVENT_BUS.register(new ColorizerEvents());
	}

	public static class ColorizerEvents {

		@SubscribeEvent
		public void onCadTake(CADTakeEvent event) {
			Item cadItem = event.getCad().getItem();
			if (cadItem instanceof ICAD) {
				ItemStack dyeStack = ((ICAD) cadItem).getComponentInSlot(event.getCad(), EnumCADComponent.DYE);
				if (dyeStack.getItem() instanceof IPlayerboundColorizer) {
					dyeStack.getOrCreateTag().putUniqueId(IPlayerboundColorizer.OWNING_PLAYER, PlayerEntity.getUUID(event.getPlayer().getGameProfile()));
					ItemCAD.setComponent(event.getCad(), dyeStack);
				}
			}
		}

		@SubscribeEvent
		public void onSpellCast(PreSpellCastEvent event) {
			ItemStack dyeStack = ((ICAD) event.getCad().getItem()).getComponentInSlot(event.getCad(), EnumCADComponent.DYE);
			if (updateTriggeredColorizer(dyeStack, event.getCad(), event.getPlayer().world.getGameTime()))
				ItemCAD.setComponent(event.getCad(), dyeStack);
			if (dyeStack.getItem() instanceof IPlayerboundColorizer) {
				dyeStack.getOrCreateTag().putUniqueId(IPlayerboundColorizer.OWNING_PLAYER, PlayerEntity.getUUID(event.getPlayer().getGameProfile()));
				ItemCAD.setComponent(event.getCad(), dyeStack);
			}
		}

		public boolean updateTriggeredColorizer(ItemStack dyeStack, ItemStack cad, Long time) {
			boolean flag = false;
			if (dyeStack.getItem() instanceof ICastTriggeredColorizer) {
				dyeStack.getOrCreateTag().putLong(ICastTriggeredColorizer.LAST_CAST, time);
				flag = true;
			}
			if (dyeStack.getItem() instanceof ItemCADColorizerHybrid) {
				ItemStack primary = ((ItemCADColorizerHybrid) dyeStack.getItem()).getContainedColorizer(dyeStack, ItemCADColorizerHybrid.PRIMARY_COLORIZER);
				boolean primaryChanged = updateTriggeredColorizer(primary, cad, time);
				if (primaryChanged)
					dyeStack.getTag().put(ItemCADColorizerHybrid.PRIMARY_COLORIZER, primary.serializeNBT());
				flag = flag || primaryChanged;
				ItemStack secondary = ((ItemCADColorizerHybrid) dyeStack.getItem()).getContainedColorizer(dyeStack, ItemCADColorizerHybrid.SECONDARY_COLORIZER);
				boolean secondaryChanged = updateTriggeredColorizer(secondary, cad, time);
				if (secondaryChanged)
					dyeStack.getTag().put(ItemCADColorizerHybrid.SECONDARY_COLORIZER, secondary.serializeNBT());
				flag = flag || secondaryChanged;
			}
			return flag;
		}

		@SubscribeEvent
		public void craftColorizer(PlayerEvent.ItemCraftedEvent event) {
			if (event.getCrafting().getItem() instanceof IPlayerboundColorizer) {
				event.getCrafting().getOrCreateTag().putUniqueId(IPlayerboundColorizer.OWNING_PLAYER, PlayerEntity.getUUID(event.getPlayer().getGameProfile()));
			}
		}
	}

	@SubscribeEvent
	public static void registerSerializers(RegistryEvent.Register<IRecipeSerializer<?>> event) {
		HybridColorizerRecipe.SERIALIZER.setRegistryName(new ResourceLocation(modID, "crafting_hybrid_colorizer_shaped"));
		event.getRegistry().register(HybridColorizerRecipe.SERIALIZER);
	}

	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> evt) {
		PsionicolorSpellPieces.init();
	}

	@SubscribeEvent
	public static void gatherData(GatherDataEvent event) {
		DataGenerator gen = event.getGenerator();

		if (event.includeClient()) {
			gen.addProvider(new PsionicolorLang(gen));
			// Let blockstate provider see generated item models by passing its existing file helper
			ItemModelProvider itemModels = new PsionicolorItemModels(gen, event.getExistingFileHelper());
			gen.addProvider(itemModels);
			gen.addProvider(new PsionicolorBlockStates(gen, itemModels.existingFileHelper));
		} if (event.includeServer()) {
			gen.addProvider(new PsionicolorLootTables(gen));
			gen.addProvider(new PsionicolorRecipes(gen));
			BlockTagsProvider blockTags = new PsionicolorBlockTags(gen, event.getExistingFileHelper());
			gen.addProvider(blockTags);
			gen.addProvider(new PsionicolorItemTags(gen, blockTags, event.getExistingFileHelper()));
		}
	}

	@EventBusSubscriber(modid = modID, value = Dist.CLIENT, bus = Bus.MOD)
	public static class PsionicolorClient {

		@SubscribeEvent
		static void itemColors(ColorHandlerEvent.Item event) {
			final ItemColors colors = event.getItemColors();
			for (colorizerInfo colorizer : PsionicolorResources.colorizers) {
				if (!colorizer.customColors)
					colors.register((stack, tintIndex) -> tintIndex != 1 ? -1 : ((ItemCADColorizerBase) stack.getItem()).getColor(stack), colorizer.colorizer.get());
			}
			colors.register((stack, tintIndex) -> tintIndex != 1 ? tintIndex != 2 ? -1 : ((ItemCADColorizerHybrid) stack.getItem()).getSecondaryColor(stack) : ((ItemCADColorizerHybrid) stack.getItem()).getPrimaryColor(stack),
					PsionicolorResources.HYBRID_COLORIZER.get(),
					PsionicolorResources.INDICATOR_COLORIZER.get(),
					PsionicolorResources.TRIGGERED_COLORIZER.get());
		}
	}
}
