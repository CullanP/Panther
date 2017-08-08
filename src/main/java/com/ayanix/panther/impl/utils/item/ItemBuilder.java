/*
 *                                                            _...---.._
 *                                                        _.'`       -_ ``.
 *                                                    .-'`                 `.
 *                                                 .-`                     q ;
 *                                              _-`                       __  \
 *                                          .-'`                  . ' .   \ `;/
 *                                      _.-`                    /.      `._`/
 *                              _...--'`                        \_`..._
 *                           .'`                         -         `'--:._
 *                        .-`                           \                  `-.
 *                       .                               `-..__.....----...., `.
 *                      '                   `'''---..-''`'              : :  : :
 *                    .` -                '``                           `'   `'
 *                 .-` .` '             .``
 *             _.-` .-`   '            .
 *         _.-` _.-`    .' '         .`
 * (`''--'' _.-`      .'  '        .'
 *  `'----''        .'  .`       .`
 *                .'  .'     .-'`    _____               _    _
 *              .'   :    .-`       |  __ \             | |  | |
 *              `. .`   ,`          | |__) |__ _  _ __  | |_ | |__    ___  _ __
 *               .'   .'            |  ___// _` || '_ \ | __|| '_ \  / _ \| '__|
 *              '   .`              | |   | (_| || | | || |_ | | | ||  __/| |
 *             '  .`                |_|    \__,_||_| |_| \__||_| |_| \___||_|
 *             `  '.
 *             `.___;
 */
package com.ayanix.panther.impl.utils.item;

import com.ayanix.panther.utils.item.IItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Panther - Developed by Lewes D. B.
 * All rights reserved 2017.
 */
public class ItemBuilder implements IItemBuilder
{

	protected Material                      material;
	protected int                           amount;
	protected short                         data;
	protected String                        name;
	protected List<String>                  lore;
	protected HashMap<Enchantment, Integer> enchants;
	protected Color                         color;

	public ItemBuilder(Material material)
	{
		this.material = material;
		this.amount = 1;
		this.data = 0;
		this.name = "";
		this.lore = new ArrayList<>();
		this.enchants = new HashMap<>();
		this.color = Color.AQUA;
	}

	@Override
	public IItemBuilder amount(int amount)
	{
		if (amount < 1 || amount > 64)
		{
			throw new IllegalArgumentException("Amount cannot be less than 1 or greater than 64");
		}

		this.amount = amount;

		return this;
	}

	@Override
	public IItemBuilder data(short data)
	{
		if (data < 0)
		{
			throw new IllegalArgumentException("Data cannot be negative");
		}

		this.data = data;

		return this;
	}

	@Override
	public IItemBuilder name(String name)
	{
		this.name = name;

		return this;
	}

	@Override
	public IItemBuilder lore(List<String> lore)
	{
		this.lore = lore;

		return this;
	}

	@Override
	public IItemBuilder enchant(Enchantment enchantment, int level)
	{
		this.enchants.put(enchantment, level);

		return this;
	}

	@Override
	public IItemBuilder color(Color color)
	{
		this.color = color;

		return this;
	}

	@Override
	public ItemStack build()
	{
		ItemStack item = new ItemStack(material);

		item.setAmount(amount);
		item.setDurability(data);

		ItemMeta meta = Bukkit.getItemFactory().getItemMeta(material);

		if (!name.isEmpty())
		{
			meta.setDisplayName(name);
		}

		meta.setLore(lore);

		LeatherArmorMeta armorMeta = (LeatherArmorMeta) meta;
		armorMeta.setColor(color);

		item.setItemMeta(armorMeta);

		for (Map.Entry<Enchantment, Integer> entry : enchants.entrySet())
		{
			item.addUnsafeEnchantment(entry.getKey(), entry.getValue());
		}

		return item;
	}
}