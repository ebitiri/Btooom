package com.github.ebitiri.btooom.item;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.github.ebitiri.btooom.BukkitUtil;
import com.github.ebitiri.btooom.bomb.PoisonBomb;
import com.github.ebitiri.btooom.system.CustomItem;
import com.github.ebitiri.btooom.system.GameObjectManager;



public class PoisonBombItem extends CustomItem{
	
	private GameObjectManager gom;
	
	public PoisonBombItem(GameObjectManager gom){
		super(ItemNames.POISON, Material.POTION, 8260);
		this.gom = gom;
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("右クリックすると向いている方向に投擲する。");
		lore.add("壁や生物など、何かに当たると、その時点で爆発し、一定時間持続する毒ガスをばらまく。");
		lore.add("ガスの周囲にいる物はダメージと盲目の効果を受ける。");
		setLore(lore);
	}

	@Override
	public boolean onRightClick(Player player){
		gom.add(new PoisonBomb(player, BukkitUtil.throwV0(player, 2.0), 200, 20));
		BukkitUtil.consumeItemInHand(player);
		return true;
	}

	@Override
	public boolean onLeftClick(Player player){
		return true;
	}
}

