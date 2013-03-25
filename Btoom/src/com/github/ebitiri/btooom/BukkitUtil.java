package com.github.ebitiri.btooom;

import java.util.Collection;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;


/**
 * Bukkit関連のユーティリティ
 * @author ebi
 *
 */
public class BukkitUtil {

	/**インスタンス化不可*/
	private BukkitUtil(){
	}
	
	
	/**
	 * 
	 */
	public static void explode(Location l, float power, boolean breakBlock, boolean fire){
		l.getWorld().createExplosion(l.getX(), l.getY(), l.getZ(), power, fire, breakBlock);
	}
	
	
	
	/**
	 * 手持ちのアイテムを１つ減らす。１つしかなければ消去。
	 * @param player
	 * @param is
	 */
	public static void consumeItemInHand(Player player){
		ItemStack is = player.getItemInHand();
		if(is.getAmount() == 1){
			player.getInventory().remove(is);
		}else{
			is.setAmount(is.getAmount() - 1);
		}
	}
	
	/**
	 * ポーションをつくる
	 * @param stack
	 * @param dmg
	 * @return
	 */
	public static ItemStack createPotion(int stack, int dmg){
		ItemStack is = new ItemStack(Material.POTION, stack);
		is.setDurability((short)dmg);
		return is;
	}
	
	/**
	 * プレイヤーが向いている方向へ物を投げるときの速度を計算
	 */
	public static Vector throwV0(Player player, double speed){
		return player.getLocation().getDirection().normalize().multiply(speed);
	}
	
	/**
	 * 基準位置から最も近い位置に居るEntityを取得
	 * @param pos 基準位置
	 * @param maxD2  最大距離の２乗。　この範囲内に候補がいなければnullを返す。
	 * @param exc 候補に入っていても除外する。
	 * @return
	 */
	public static LivingEntity getNearestLiving(Location pos, int maxD2, LivingEntity exc){
		//標的候補
		Collection<Entity> entities = pos.getWorld().getEntities();
		
		LivingEntity res = null;
		double nd2 = maxD2;
		
		//一番近いやつを探す
		for(Entity entity : entities){
			//型
			if(entity instanceof LivingEntity){
				//例外
				if(entity.equals(exc)) continue;
				//距離一番近い？
				double d2 = entity.getLocation().distanceSquared(pos);
				if(nd2 > d2){
					res = (LivingEntity)entity;
					nd2 = d2;
				}
			}
		}
		
		return res;
	}
}
