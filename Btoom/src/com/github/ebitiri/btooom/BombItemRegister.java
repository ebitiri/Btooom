package com.github.ebitiri.btooom;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.github.ebitiri.btooom.bomb.Grenade;
import com.github.ebitiri.btooom.item.BombItem;
import com.github.ebitiri.btooom.item.BombLauncher;
import com.github.ebitiri.btooom.item.ItemNames;
import com.github.ebitiri.btooom.system.CustomItemManager;
import com.github.ebitiri.btooom.system.GameObjectManager;



public class BombItemRegister {

	
	
	public static void regist(CustomItemManager manager, final GameObjectManager gom){
		
		ArrayList<String> lore = new ArrayList<String>();
		String name = "";
		Material mat = Material.AIR;
		BombLauncher lau = null;
		
		//
		// グレネード
		//
		name = ItemNames.GRENADE;
		mat = Material.SNOW;
		lore.clear();
		lore.add("右クリックすると向いている方向に投擲する。");
		lore.add("壁や生物など、何かに当たると、その時点で爆発する。");
		lore.add("扱いやすいが、威力は低い部類。");
		lau = new BombLauncher(){
			@Override
			public void launch(Player shooter) {
				gom.add(new Grenade(shooter, BukkitUtil.throwV0(shooter, 2.0), 2F));
			}
		};
		manager.registerCustomItem(new BombItem(name, mat, lore, lau));
	}
}
