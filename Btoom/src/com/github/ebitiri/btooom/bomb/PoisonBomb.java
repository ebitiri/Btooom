package com.github.ebitiri.btooom.bomb;



import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.util.Vector;

import com.github.ebitiri.btooom.system.EntityObject;


/**
 * 毒ガスボム
 * @author ebi
 */
public class PoisonBomb extends EntityObject<Projectile>{

	private int dur, dis;
	
	/**
	 * 初期化。
	 */
	public PoisonBomb(Player shooter, Vector v0, int dur, int dis){
		//発射
		Projectile pro = shooter.launchProjectile(EnderPearl.class);
		pro.setVelocity(v0);
		setEntity(pro);
		
		this.dis = dis;
		this.dur = dur;
	}
	
	@Override
	public void onRemove(){
		//ガスをまいてから消える
		getManager().add(new PoisonGas(dur, getLocation(), dis));
	}
}
