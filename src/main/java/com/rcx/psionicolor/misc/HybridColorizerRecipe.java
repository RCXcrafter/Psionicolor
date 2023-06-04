package com.rcx.psionicolor.misc;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.annotation.Nonnull;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.rcx.psionicolor.item.ItemCADColorizerHybrid;

import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;
import vazkii.psi.api.cad.ICADColorizer;

public class HybridColorizerRecipe extends ShapedRecipe {

	static int MAX_WIDTH = 3;
	static int MAX_HEIGHT = 3;
	public static final RecipeSerializer<HybridColorizerRecipe> SERIALIZER = new Serializer();

	public HybridColorizerRecipe(ResourceLocation idIn, String groupIn, int recipeWidthIn, int recipeHeightIn, NonNullList<Ingredient> recipeItemsIn, ItemStack recipeOutputIn) {
		super(idIn, groupIn, recipeHeightIn, recipeHeightIn, recipeItemsIn, recipeOutputIn);
	}

	@Nonnull
	@Override
	public ItemStack assemble(@Nonnull CraftingContainer inv) {
		ItemStack result = super.assemble(inv);
		if (!result.isEmpty()) {
			boolean primary = true;
			for (int i = 0; i < inv.getContainerSize(); i++) {
				ItemStack stack = inv.getItem(i).copy();
				if (!stack.isEmpty() && stack.getItem() instanceof ICADColorizer) {
					if (stack.hasTag() && stack.getTag().contains("psi_contributor_name"))
						stack.getTag().remove("psi_contributor_name");
					if (primary) {
						result.getOrCreateTag().put(ItemCADColorizerHybrid.PRIMARY_COLORIZER, stack.serializeNBT());
						if (stack.getItem() instanceof ItemCADColorizerHybrid) {
							int nesting = 1;
							if (stack.hasTag() && stack.getTag().contains(ItemCADColorizerHybrid.COLORIZER_NESTING))
								nesting += stack.getTag().getInt(ItemCADColorizerHybrid.COLORIZER_NESTING);
							result.getOrCreateTag().putInt(ItemCADColorizerHybrid.COLORIZER_NESTING, nesting);
						}
						primary = false;
					} else {
						if (stack.getItem() instanceof ItemCADColorizerHybrid) {
							int nesting = 1;
							if (result.hasTag() && result.getTag().contains(ItemCADColorizerHybrid.COLORIZER_NESTING))
								nesting = result.getTag().getInt(ItemCADColorizerHybrid.COLORIZER_NESTING);
							if (stack.hasTag() && stack.getTag().contains(ItemCADColorizerHybrid.COLORIZER_NESTING))
								nesting += stack.getTag().getInt(ItemCADColorizerHybrid.COLORIZER_NESTING);
							result.getOrCreateTag().putInt(ItemCADColorizerHybrid.COLORIZER_NESTING, nesting);
						}
						result.getOrCreateTag().put(ItemCADColorizerHybrid.SECONDARY_COLORIZER, stack.serializeNBT());
					}
				}
			}
		}
		return result;
	}

	@Nonnull
	@Override
	public RecipeSerializer<?> getSerializer() {
		return SERIALIZER;
	}

	static NonNullList<Ingredient> dissolvePattern(String[] p_44203_, Map<String, Ingredient> p_44204_, int p_44205_, int p_44206_) {
		NonNullList<Ingredient> nonnulllist = NonNullList.withSize(p_44205_ * p_44206_, Ingredient.EMPTY);
		Set<String> set = Sets.newHashSet(p_44204_.keySet());
		set.remove(" ");

		for(int i = 0; i < p_44203_.length; ++i) {
			for(int j = 0; j < p_44203_[i].length(); ++j) {
				String s = p_44203_[i].substring(j, j + 1);
				Ingredient ingredient = p_44204_.get(s);
				if (ingredient == null) {
					throw new JsonSyntaxException("Pattern references symbol '" + s + "' but it's not defined in the key");
				}

				set.remove(s);
				nonnulllist.set(j + p_44205_ * i, ingredient);
			}
		}

		if (!set.isEmpty()) {
			throw new JsonSyntaxException("Key defines symbols that aren't used in pattern: " + set);
		} else {
			return nonnulllist;
		}
	}

	@VisibleForTesting
	static String[] shrink(String... toShrink) {
		int i = Integer.MAX_VALUE;
		int j = 0;
		int k = 0;
		int l = 0;

		for(int i1 = 0; i1 < toShrink.length; ++i1) {
			String s = toShrink[i1];
			i = Math.min(i, firstNonSpace(s));
			int j1 = lastNonSpace(s);
			j = Math.max(j, j1);
			if (j1 < 0) {
				if (k == i1) {
					++k;
				}

				++l;
			} else {
				l = 0;
			}
		}

		if (toShrink.length == l) {
			return new String[0];
		} else {
			String[] astring = new String[toShrink.length - l - k];

			for(int k1 = 0; k1 < astring.length; ++k1) {
				astring[k1] = toShrink[k1 + k].substring(i, j + 1);
			}

			return astring;
		}
	}

	private static int firstNonSpace(String str) {
		int i;
		for(i = 0; i < str.length() && str.charAt(i) == ' '; ++i) {
		}

		return i;
	}

	private static int lastNonSpace(String str) {
		int i;
		for(i = str.length() - 1; i >= 0 && str.charAt(i) == ' '; --i) {
		}

		return i;
	}

	static String[] patternFromJson(JsonArray p_44197_) {
		String[] astring = new String[p_44197_.size()];
		if (astring.length > MAX_HEIGHT) {
			throw new JsonSyntaxException("Invalid pattern: too many rows, " + MAX_HEIGHT + " is maximum");
		} else if (astring.length == 0) {
			throw new JsonSyntaxException("Invalid pattern: empty pattern not allowed");
		} else {
			for(int i = 0; i < astring.length; ++i) {
				String s = GsonHelper.convertToString(p_44197_.get(i), "pattern[" + i + "]");
				if (s.length() > MAX_WIDTH) {
					throw new JsonSyntaxException("Invalid pattern: too many columns, " + MAX_WIDTH + " is maximum");
				}

				if (i > 0 && astring[0].length() != s.length()) {
					throw new JsonSyntaxException("Invalid pattern: each row must be the same width");
				}

				astring[i] = s;
			}

			return astring;
		}
	}

	static Map<String, Ingredient> keyFromJson(JsonObject p_44211_) {
		Map<String, Ingredient> map = Maps.newHashMap();

		for(Entry<String, JsonElement> entry : p_44211_.entrySet()) {
			if (entry.getKey().length() != 1) {
				throw new JsonSyntaxException("Invalid key entry: '" + (String)entry.getKey() + "' is an invalid symbol (must be 1 character only).");
			}

			if (" ".equals(entry.getKey())) {
				throw new JsonSyntaxException("Invalid key entry: ' ' is a reserved symbol.");
			}

			map.put(entry.getKey(), Ingredient.fromJson(entry.getValue()));
		}

		map.put(" ", Ingredient.EMPTY);
		return map;
	}

	public static class Serializer extends net.minecraftforge.registries.ForgeRegistryEntry<RecipeSerializer<?>>  implements RecipeSerializer<HybridColorizerRecipe> {
		public HybridColorizerRecipe fromJson(ResourceLocation p_44236_, JsonObject p_44237_) {
			String s = GsonHelper.getAsString(p_44237_, "group", "");
			Map<String, Ingredient> map = HybridColorizerRecipe.keyFromJson(GsonHelper.getAsJsonObject(p_44237_, "key"));
			String[] astring = HybridColorizerRecipe.shrink(HybridColorizerRecipe.patternFromJson(GsonHelper.getAsJsonArray(p_44237_, "pattern")));
			int i = astring[0].length();
			int j = astring.length;
			NonNullList<Ingredient> nonnulllist = HybridColorizerRecipe.dissolvePattern(astring, map, i, j);
			ItemStack itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(p_44237_, "result"));
			return new HybridColorizerRecipe(p_44236_, s, i, j, nonnulllist, itemstack);
		}

		public HybridColorizerRecipe fromNetwork(ResourceLocation p_44239_, FriendlyByteBuf p_44240_) {
			int i = p_44240_.readVarInt();
			int j = p_44240_.readVarInt();
			String s = p_44240_.readUtf();
			NonNullList<Ingredient> nonnulllist = NonNullList.withSize(i * j, Ingredient.EMPTY);

			for(int k = 0; k < nonnulllist.size(); ++k) {
				nonnulllist.set(k, Ingredient.fromNetwork(p_44240_));
			}

			ItemStack itemstack = p_44240_.readItem();
			return new HybridColorizerRecipe(p_44239_, s, i, j, nonnulllist, itemstack);
		}

		public void toNetwork(FriendlyByteBuf p_44227_, HybridColorizerRecipe p_44228_) {
			p_44227_.writeVarInt(p_44228_.getWidth());
			p_44227_.writeVarInt(p_44228_.getHeight());
			p_44227_.writeUtf(p_44228_.getGroup());

			for(Ingredient ingredient : p_44228_.getIngredients()) {
				ingredient.toNetwork(p_44227_);
			}

			p_44227_.writeItem(p_44228_.getResultItem());
		}
	}
}