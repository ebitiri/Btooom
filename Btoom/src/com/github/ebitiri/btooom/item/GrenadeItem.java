package com.github.ebitiri.btooom.item;



import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.github.ebitiri.btooom.BukkitUtil;
import com.github.ebitiri.btooom.bomb.Grenade;
import com.github.ebitiri.btooom.system.CustomItem;
import com.github.ebitiri.btooom.system.GameObjectManager;



public class GrenadeItem extends CustomItem{
	
	private GameObjectManager gom;
	
	public GrenadeItem(GameObjectManager gom){
		super(ItemNames.GRENADE, Material.SNOW_BALL);
		this.gom = gom;
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("右クリックすると向いている方向に投擲する。");
		lore.add("壁や生物など、何かに当たると、その時点で爆発する。");
		lore.add("扱いやすいが、威力は低い部類。");
		setLore(lore);
	}

	@Override
	public boolean onRightClick(Player player){
		gom.add(new Grenade(player, BukkitUtil.throwV0(player, 2.0), 2F));
		BukkitUtil.consumeItemInHand(player);
		return true;
	}

	@Override
	public boolean onLeftClick(Player player){
		return true;
	}
}
