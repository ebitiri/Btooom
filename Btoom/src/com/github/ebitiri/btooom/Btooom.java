package com.github.ebitiri.btooom;


import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.ebitiri.btooom.system.GameObjectManager;
import com.github.ebitiri.command.CommandManager;
import com.github.ebitiri.command.CommandProperty;



/**
 * このPLのメインクラス
 * @author ebi
 */
public class Btooom extends JavaPlugin{

	private GameObjectManager gom;
	private PlayerFunction funcPlayer;
	
	
	@Override
	public void onEnable(){	
		//go管理初期化
		gom = new GameObjectManager(this);
		
		//コマンド管理初期化
		CommandManager com = new CommandManager(this);
		com.registerCommandsOf(this);
		
		//機能初期化
		funcPlayer = new PlayerFunction();
		funcPlayer.on(gom);
	}
	
	@Override
	public void onDisable(){
		//；。；
	}
	
	@CommandProperty(name = "vk", namespace = "btm", perm = "")
	public void valkit(Player player){
		//ダイヤ防具（頭以外）
		//player.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
		//player.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
		//player.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
		//
		//player.getInventory().setItem(0, BukkitUtil.createPotion(1, 8197));
		//player.getInventory().setItem(1, BukkitUtil.createPotion(1, 8197));
		//player.getInventory().setItem(2, new ItemStack(Material.BREAD, 3));
		//player.getInventory().setItem(3, new ItemStack(Material.IRON_SWORD));
		player.getInventory().setItem(4, new ItemStack(Material.FIREBALL, 8));
		player.getInventory().setItem(5, new ItemStack(Material.SNOW_BALL, 8));
		player.getInventory().setItem(6, new ItemStack(Material.ENDER_PEARL, 8));
		player.getInventory().setItem(7, new ItemStack(Material.TNT, 8));
		player.getInventory().setItem(8, BukkitUtil.createPotion(8, 8260));
	}
}
