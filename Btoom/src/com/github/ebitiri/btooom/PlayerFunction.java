package com.github.ebitiri.btooom;



import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.github.ebitiri.btooom.bomb.Grenade;
import com.github.ebitiri.btooom.bomb.HomingBomb;
import com.github.ebitiri.btooom.bomb.Napalm;
import com.github.ebitiri.btooom.bomb.PoisonBomb;
import com.github.ebitiri.btooom.bomb.TimerBomb;
import com.github.ebitiri.btooom.system.Function;
import com.github.ebitiri.btooom.system.GameObject;





/**
 * プレイヤーに関する機能
 * @author ebi
 */
public class PlayerFunction extends Function{

	/**
	 * 右クリ
	 * @param event
	 */
	@EventHandler
	public void onInteract(PlayerInteractEvent event){
		//右クリ
		if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
			//情報色々
			ItemStack is = event.getItem();
			Player player = event.getPlayer();
			Location el = player.getEyeLocation();
			Vector v = BukkitUtil.throwV0(player, 2.0);
			GameObject go = null;
			//種類に応じてオブジェクト作成
			switch(is.getType()){
			case TNT: 
				go = new TimerBomb(el, v, 200, 6.5f); 
				break;
			case POTION:
				if(is.getDurability() == 8260){
					go = new PoisonBomb(player, v, 200, 13);
				}
				break;
			case ENDER_PEARL:
				go = new HomingBomb(player, v, 0.5, 1.0, 4.5F);
				break;
			case SNOW_BALL:
				go = new Grenade(player, v, 3.5F);
				break;
			case FIREBALL:
				go = new Napalm(player, v, 7);
				break;
			default:
				break;
			}
			//オブジェクトが作成できたら、アイテム消費、登録、イベキャンセル
			if(go != null){
				BukkitUtil.consumeItemInHand(player, is);
				getManager().add(go);
				event.setCancelled(true);
			}
		}
	}
	
	/**
	 * Playerテレポート
	 */
	@EventHandler
	public void onTeleport(PlayerTeleportEvent event){
		//パールのテレポートをキャンセル
		if(event.getCause() == TeleportCause.ENDER_PEARL){
			event.setCancelled(true);
		}
	}
	
}
