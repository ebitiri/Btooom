package com.github.ebitiri.btooom.system;

import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;



/**
 * 自作？アイテムを管理する機能
 * @author ebi
 */
public class CustomItemManager extends Function{

	private HashMap<String, CustomItem> map;
	
	public CustomItemManager(){
		map = new HashMap<String, CustomItem>();
	}
	
	/**
	 * アイテムを登録
	 */
	public void registerCustomItem(CustomItem item){
		map.put(item.getDisplayName(), item);
	}
	
	/**
	 * 表示名から{@link ItemStack}を作成。　指定した表示名のアイテムが登録されていなければnullを返す。
	 */
	public ItemStack create(String displayName, int amount){
		CustomItem i = map.get(displayName);
		if(i == null){
			return null;
		}
		else{
			return i.create(amount);
		}
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event){
		//左クリック系か
		boolean left;
		
		//アクションの種類をチェック
		switch(event.getAction()){
		case LEFT_CLICK_AIR:
		case LEFT_CLICK_BLOCK:
			left = true;
			break;
		case RIGHT_CLICK_AIR:
		case RIGHT_CLICK_BLOCK:
			left = false;
			break;
		default:
			return;
		}
		
		//いろいろ取得
		ItemStack is = event.getItem();
		Player player = event.getPlayer();
	
		//素手なら対象外
		if(is == null)return;
		
		//表示名からアイテム取得
		CustomItem item = map.get(is.getItemMeta().getDisplayName());
		
		//見つかったなら使用する。
		if(item != null){
			boolean cancel;
			if(left){
				cancel = item.onLeftClick(player);
			}
			else{
				cancel = item.onRightClick(player);
			}
			event.setCancelled(cancel);
		}
	}
}
