package com.github.ebitiri.btooom.bomb;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.util.Vector;

import com.github.ebitiri.btooom.system.EntityObject;

/**
 * ナパーム弾
 * @author ebi
 *
 */
public class Napalm extends EntityObject<Projectile>{

	private int dist;
	
	/**
	 * 初期化
	 * @param shooter	投げる人
	 * @param v0 	初速
	 * @param dist 	効果範囲
	 */
	public Napalm(Player shooter, Vector v0, int dist){
		setEntity(shooter.launchProjectile(EnderPearl.class));
		setVelocity(v0);
		
		this.dist = dist;
	}
	
	@Override
	public void onRemove(){
		//爆破位置のブロック
		Block b0 = getLocation().getBlock();
		
		//ランダム１００回
		int n = dist * dist * 3;
		for(int i = 0; i < n; i++){
			//円柱範囲
			for(int y = -3; y <= 3; y++){
				double r = Math.random() * dist;
				double t = Math.random() * Math.PI * 2;
				Block b = b0.getRelative((int)(r * Math.cos(t)), y, (int)(r * Math.sin(t)));
				if(b.getType() == Material.AIR){
					b.setType(Material.FIRE);
				}
			}
		}
	}
}
