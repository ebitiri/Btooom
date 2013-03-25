package com.github.ebitiri.btooom;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import com.github.ebitiri.btooom.item.AntiGBItem;
import com.github.ebitiri.btooom.item.GrenadeItem;
import com.github.ebitiri.btooom.item.HomingBombItem;
import com.github.ebitiri.btooom.item.ItemNames;
import com.github.ebitiri.btooom.item.NapalmItem;
import com.github.ebitiri.btooom.item.PoisonBombItem;
import com.github.ebitiri.btooom.item.TimerBombItem;
import com.github.ebitiri.btooom.system.CustomItemManager;
import com.github.ebitiri.btooom.system.GameObjectManager;
import com.github.ebitiri.command.CommandManager;
import com.github.ebitiri.command.CommandProperty;



/**
 * このPLのメインクラス
 * @author ebi
 */
public class Btooom extends JavaPlugin implements Listener{

	private CommandManager com;
	private GameObjectManager gom;
	private CustomItemManager cim;
	
	@Override
	public void onEnable(){	
		//go管理初期化
		gom = new GameObjectManager(this);
		
		//コマンド管理初期化
		com = new CommandManager(this);
		com.registerCommandsOf(this);
		
		//カスタムアイテム管理初期化
		cim = new CustomItemManager();
		cim.on(gom);
		
		
		Bukkit.getPluginManager().registerEvents(this, this);
	}
	
	@EventHandler
	public void onTP(PlayerTeleportEvent eve){
		if(eve.getCause() == TeleportCause.ENDER_PEARL){
			eve.setCancelled(true);
		}
	}
	
	@Override
	public void onDisable(){
		//；。；
	}
	
	@CommandProperty(name = "vk", namespace = "btm", perm = "")
	public void valkit(Player player){
		//ダイヤ防具（頭以外）
		player.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));
		player.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
		player.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
		
		player.getInventory().setItem(0, cim.create(ItemNames.GRENADE, 3));
		player.getInventory().setItem(1, cim.create(ItemNames.ANTIGRAV, 1));
		player.getInventory().setItem(2, cim.create(ItemNames.HOMING, 3));
		player.getInventory().setItem(3, cim.create(ItemNames.NAPALM, 6));
		player.getInventory().setItem(4, cim.create(ItemNames.TIMER, 8));
		player.getInventory().setItem(5, cim.create(ItemNames.POISON, 2));
		
	}
}
