package awesomeGuy.jusjus.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

@SuppressWarnings("deprecation")
public class UtilItem {

	public static Material notUnlocked = Material.INK_SACK;
	public static byte notUnlockedData = 8;

	public static boolean itemDurabiltiy = true;

	public static ItemStack create(Material m, String n, List<String> l) {
		ItemStack i = new ItemStack(m);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(n);
		im.setLore(l);
		i.setItemMeta(im);
		return i;
	}

	public static ItemStack create(Material m, int amount, String n, List<String> l) {
		ItemStack i = new ItemStack(m, amount, (byte) 0);
		
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(n);
		im.setLore(l);
		i.setItemMeta(im);
		return i;
	}

	public static ItemStack create(Material m, String n) {
		ItemStack i = new ItemStack(m);
		
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(n);
		i.setItemMeta(im);
		return i;
	}

	public static ItemStack create(Material m) {
		ItemStack i = new ItemStack(m);
		
		ItemMeta im = i.getItemMeta();
		i.setItemMeta(im);
		return i;
	}

	public static ItemStack create(Material m, byte d) {
		ItemStack i = new ItemStack(m, 1, d);
		
		ItemMeta im = i.getItemMeta();
		i.setItemMeta(im);
		return i;
	}

	public static ItemStack create(Material m, byte d, String name) {
		ItemStack i = new ItemStack(m, 1, d);
		
		ItemMeta meta = i.getItemMeta();
		meta.setDisplayName(name);
		i.setItemMeta(meta);
		return i;
	}

	public static ItemStack create(Material m, byte d, int amount) {
		ItemStack i = new ItemStack(m, amount, d);
		
		ItemMeta im = i.getItemMeta();
		i.setItemMeta(im);
		return i;
	}

	public static ItemStack create(Material m, byte d, String n, List<String> l) {
		ItemStack i = new ItemStack(m, 1, d);
		
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(n);
		im.setLore(l);
		i.setItemMeta(im);
		return i;
	}

	public static ItemStack create(Material m, int amount) {
		ItemStack i = new ItemStack(m, amount, (byte) 0);
		
		ItemMeta im = i.getItemMeta();
		i.setItemMeta(im);
		return i;
	}

	public static ItemStack create(Material m, int amount, String n) {
		ItemStack i = new ItemStack(m, amount, (byte) 0);
		
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(n);
		i.setItemMeta(im);
		return i;
	}

	public static ItemStack create(Material m, String name, Enchantment ench, int level, String... lore) {
		ItemStack i = new ItemStack(m);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(name);
		im.setLore(Arrays.asList(lore));
		i.setItemMeta(im);
		i.addUnsafeEnchantment(ench, level);
		return i;
	}

	public static ItemStack rename(ItemStack i, String name) {
		
		ItemMeta meta = i.getItemMeta();
		meta.setDisplayName(name);
		i.setItemMeta(meta);
		return i;
	}

	public static ItemStack create(Material m, byte d, String n, List<String> l, int amount) {
		ItemStack i = new ItemStack(m, amount, d);
		
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(n);
		im.setLore(l);
		i.setItemMeta(im);
		return i;
	}

	public static ItemStack create(Material m, byte d, int amount, String n, List<String> l) {
		ItemStack i = new ItemStack(m, amount, d);
		
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(n);
		im.setLore(l);
		i.setItemMeta(im);
		return i;
	}

	public static List<ItemStack> sortByAmount(List<ItemStack> oldItems) {
		List<Integer> amounts = new ArrayList<>();
		for (ItemStack i : oldItems) {
			if (i == null)
				continue;
			int amount = i.getAmount();
			if (amount == 0) {
				amount = 1;
			}
			amounts.add(i.getAmount());
		}
		Collections.sort(amounts);
		List<ItemStack> newItems = new ArrayList<>();
		for (Integer i : amounts) {
			if (oldItems.size() > 0) {
				for (ItemStack item : oldItems) {
					if (item.getAmount() == i) {
						newItems.add(item);
						oldItems.remove(item);
					}
				}
			}
		}
		return newItems;
	}

	@Deprecated
	public static boolean itemHasDisplayName(ItemStack i) {
		if (i == null)
			return false;
		if (!i.hasItemMeta())
			return false;
		if (!i.getItemMeta().hasDisplayName())
			return false;
		if (i.getItemMeta().hasDisplayName())
			return true;
		return false;
	}

	public static boolean hasDisplayName(ItemStack item) {
		if (item == null)
			return false;
		if (!item.hasItemMeta())
			return false;
		return item.getItemMeta().hasDisplayName();
	}

	public static boolean itemHasLore(ItemStack i) {
		if (i == null)
			return false;
		if (!i.hasItemMeta())
			return false;
		if (!i.getItemMeta().hasLore())
			return false;
		if (i.getItemMeta().hasLore())
			return true;
		return false;
	}

	public static Color convertByteToColor(byte d) {
		Color c = null;
		if (d == 0)
			c = Color.WHITE;
		if (d == 1)
			c = Color.ORANGE;
		if (d == 2)
			c = Color.FUCHSIA;
		if (d == 3)
			c = Color.BLUE;
		if (d == 4)
			c = Color.YELLOW;
		if (d == 5)
			c = Color.LIME;
		if (d == 6)
			c = Color.FUCHSIA;
		if (d == 7)
			c = Color.GRAY;
		if (d == 8)
			c = Color.GRAY;
		if (d == 9)
			c = Color.TEAL;
		if (d == 10)
			c = Color.PURPLE;
		if (d == 11)
			c = Color.BLUE;
		if (d == 12)
			c = Color.MAROON;
		if (d == 13)
			c = Color.GREEN;
		if (d == 14)
			c = Color.RED;
		if (d == 15)
			c = Color.BLACK;
		return c;
	}

	public static ItemStack createColouredArmour(Material m, Color colour) {
		ItemStack i = new ItemStack(m);
		i = applyItemFlags(i);
		LeatherArmorMeta lam = (LeatherArmorMeta) i.getItemMeta();
		lam.setColor(colour);
		i.setItemMeta(lam);
		return i;
	}

	public static ItemStack createColouredArmour(Material m, Color colour, String n, List<String> l) {
		ItemStack i = new ItemStack(m);
		i = applyItemFlags(i);
		LeatherArmorMeta lam = (LeatherArmorMeta) i.getItemMeta();
		lam.setDisplayName(n);
		lam.setLore(l);
		lam.setColor(colour);
		i.setItemMeta(lam);
		return i;
	}

	public static ItemStack createColouredArmour(Material m, Color colour, String n) {
		ItemStack i = new ItemStack(m);
		i = applyItemFlags(i);
		LeatherArmorMeta lam = (LeatherArmorMeta) i.getItemMeta();
		lam.setDisplayName(n);
		lam.setColor(colour);
		i.setItemMeta(lam);
		return i;
	}

	public static ItemStack createSkull(String name, List<String> lore) {
		ItemStack i = new ItemStack(397, 1, (byte) 3);
		i = applyItemFlags(i);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(name);
		im.setLore(lore);
		i.setItemMeta(im);
		return i;
	}

	public static ItemStack createSkull(String name, List<String> lore, String owner) {
		ItemStack i = new ItemStack(397, 1, (byte) 3);
		i = applyItemFlags(i);
		SkullMeta im = (SkullMeta) i.getItemMeta();
		try {
			im.setOwner(ChatColor.stripColor(owner));
		} catch (Exception ignored) {
		}
		im.setDisplayName(name);
		im.setLore(lore);
		i.setItemMeta(im);
		return i;
	}

	public static ItemStack createSkull(String name, String owner) {
		ItemStack i = new ItemStack(397, 1, (byte) 3);
		i = applyItemFlags(i);
		SkullMeta im = (SkullMeta) i.getItemMeta();
		try {
			im.setOwner(ChatColor.stripColor(owner));
		} catch (Exception ignored) {
		}
		im.setDisplayName(name);
		i.setItemMeta(im);
		return i;
	}

	public static ItemStack createSkull(String name, List<String> lore, int data) {
		ItemStack i = new ItemStack(397, 1, (byte) data);
		i = applyItemFlags(i);
		SkullMeta im = (SkullMeta) i.getItemMeta();
		im.setDisplayName(name);
		im.setLore(lore);
		i.setItemMeta(im);
		return i;
	}

	public static ItemStack create(int m, int d) {
		ItemStack i = new ItemStack(m, 1, (byte) d);
		i = applyItemFlags(i);
		return i;
	}

	public static ItemStack create(Material m, Enchantment ench, int level) {
		ItemStack i = new ItemStack(m);
		i = applyItemFlags(i);
		i.addUnsafeEnchantment(ench, level);
		return i;
	}

	public static ItemStack create(Material m, String name, int amount) {
		ItemStack i = new ItemStack(m, amount);
		i = applyItemFlags(i);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(name);
		i.setItemMeta(im);
		return i;
	}

	public static ItemStack setArmourColour(ItemStack i, Color c) {
		Material t = i.getType();
		if (!(t == Material.LEATHER_HELMET || t == Material.LEATHER_CHESTPLATE || t == Material.LEATHER_LEGGINGS
				|| t == Material.LEATHER_BOOTS)) {
			return i;
		}
		LeatherArmorMeta m = (LeatherArmorMeta) i.getItemMeta();
		m.setColor(c);
		i.setItemMeta(m);
		i = applyItemFlags(i);
		return i;
	}

	public static boolean compareItems(ItemStack i1, ItemStack i2) {
		if (i1 == null || i2 == null)
			return false;
		if (i1.getType() != i2.getType())
			return false;
		if (!i1.hasItemMeta() && !i2.hasItemMeta())
			return true;
		if (!i1.getItemMeta().hasDisplayName() && !i2.getItemMeta().hasDisplayName())
			return true;
		if (!i1.getItemMeta().hasDisplayName() && i2.getItemMeta().hasDisplayName())
			return false;
		if (i1.getItemMeta().hasDisplayName() && !i2.getItemMeta().hasDisplayName())
			return false;
		if (i1.getItemMeta().getDisplayName().equals(i2.getItemMeta().getDisplayName())) {
			if (!i1.getItemMeta().hasLore() && !i2.getItemMeta().hasLore())
				return true;
			if (i1.getItemMeta().getLore() == null || i2.getItemMeta().getLore() == null)
				return true;
			if (i1.getItemMeta().getLore().equals(i2.getItemMeta().getLore()))
				return true;
		}
		return false;
	}

	public static ItemStack applyItemFlags(ItemStack item) {
		return applyItemFlags(item, true);
	}

	public static ItemStack applyItemFlags(ItemStack item, boolean apply) {
		apply = itemDurabiltiy;
		if (!apply)
			return item;
		ItemMeta meta = item.getItemMeta();
		meta.spigot().setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack removeItemFlags(ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		meta.spigot().setUnbreakable(true);
		meta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack addGlow(ItemStack item) {
		item.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		item.setItemMeta(meta);
		return item;
	}

	public static ItemStack hideAttributes(ItemStack item) {
		ItemMeta meta = item.getItemMeta();
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		item.setItemMeta(meta);
		return item;
	}

}