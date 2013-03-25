package com.github.ebitiri.btooom.system;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


/**
 * カスタムItem    うっちさんのぱくり^q^
 * @author ebi
 */
public abstract class CustomItem{

	private String name;
	private ItemStack base;
	
	/**
	 * 
	 * @param name
	 * @param mat
	 */
	public CustomItem(String name, Material mat){
		this(name,mat,0);
	}
	
	/**
	 * 
	 * @param name
	 * @param mat
	 * @param dmg
	 */
	public CustomItem(String name, Material mat, int dmg){
		base = new ItemStack(mat);
		base.setDurability((short)dmg);
		ItemMeta meta = base.getItemMeta();
		meta.setDisplayName(name);
		base.setItemMeta(meta);
		this.name = name;
	}
	
	/**
	 * 
	 * @param lore
	 */
	protected void setLore(List<String> lore){
		ItemMeta meta = base.getItemMeta();
		meta.setLore(new ArrayList<String>(lore));
		base.setItemMeta(meta);
	}
	
	/**
	 * {@link ItemStack}を作成
	 */
	public ItemStack create(int amount){
		ItemStack res = base.clone();
		res.setAmount(amount);
		return res;
	}
	
	/**
	 * 表示名の取得
	 */
	public String getDisplayName(){
		return name;
	}
	
	/**
	 * 右クリック動作
	 * @param player 使用者
	 * @return	本来の動作をキャンセルするか
	 */
	public abstract boolean onRightClick(Player player);
	
	/**
	 * 左クリック動作
	 * @param player 使用者
	 * @return	本来の動作をキャンセルするか
	 */
	public abstract boolean onLeftClick(Player player);

}
