package com.github.ebitiri.btooom.item;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.github.ebitiri.btooom.BukkitUtil;
import com.github.ebitiri.btooom.bomb.HomingBomb;
import com.github.ebitiri.btooom.system.CustomItem;
import com.github.ebitiri.btooom.system.GameObjectManager;




public class HomingBombItem extends CustomItem{
	
	private GameObjectManager gom;
	
	public HomingBombItem(GameObjectManager gom){
		super(ItemNames.HOMING, Material.ENDER_PEARL);
		this.gom = gom;
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("右クリックすると向いている方向に投擲する。");
		lore.add("１秒後、一番近くにいる生物を標的にして追跡するようになる。");
		lore.add("威力は低い。");
		setLore(lore);
	}

	@Override
	public boolean onRightClick(Player player){
		gom.add(new HomingBomb(player, BukkitUtil.throwV0(player, 2.0), 0.7, 1.0, 2.5F));
		BukkitUtil.consumeItemInHand(player);
		return true;
	}

	@Override
	public boolean onLeftClick(Player player){
		return true;
	}
}
