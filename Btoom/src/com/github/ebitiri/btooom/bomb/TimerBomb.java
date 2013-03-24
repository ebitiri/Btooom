package com.github.ebitiri.btooom.bomb;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import com.github.ebitiri.btooom.BukkitUtil;
import com.github.ebitiri.btooom.system.EntityObject;


/**
 * 時限爆弾
 * @author ebi
 */
public class TimerBomb extends EntityObject<Item>{

	private int time;
	private float power;
	
	/**
	 * 初期化
	 * @param mat
	 * @param loc
	 * @param v0
	 * @param time
	 * @param power
	 */
	public TimerBomb(Location loc, Vector v0, int time, float power){
		//数量最大のスタックを作成（ドロップアイテムが勝手にまとまるのを防ぐ）
		ItemStack is = new ItemStack(Material.TNT, 64);
		setEntity(loc.getWorld().dropItem(loc, is));
		
		setVelocity(v0);
		getEntity().setPickupDelay(100);
		this.time = time;	
		this.power = power;
	}
	
	@Override
	public void tick(){
		//拾えない
		getEntity().setPickupDelay(100);
		//爆破
		if(time-- < 0){
			getEntity().remove();
		}
	}
	
	@Override
	public void onRemove(){
		BukkitUtil.safeExplode(getLocation(), power);
	}
	
}
