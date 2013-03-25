package com.github.ebitiri.btooom.item;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.github.ebitiri.btooom.BukkitUtil;
import com.github.ebitiri.btooom.bomb.Napalm;
import com.github.ebitiri.btooom.system.CustomItem;
import com.github.ebitiri.btooom.system.GameObjectManager;




public class NapalmItem extends CustomItem{
	
	private GameObjectManager gom;
	
	public NapalmItem(GameObjectManager gom){
		super(ItemNames.NAPALM, Material.FIREBALL);
		this.gom = gom;
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("右クリックすると向いている方向に投擲する。");
		lore.add("壁や生物など、何かに当たると、その時点で爆発し周囲を炎上させる。");
		setLore(lore);
	}

	@Override
	public boolean onRightClick(Player player){
		gom.add(new Napalm(player, BukkitUtil.throwV0(player, 2.0), 7));
		BukkitUtil.consumeItemInHand(player);
		return true;
	}

	@Override
	public boolean onLeftClick(Player player){
		return true;
	}
}
