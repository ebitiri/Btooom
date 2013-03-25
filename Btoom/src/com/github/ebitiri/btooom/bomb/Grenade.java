package com.github.ebitiri.btooom.bomb;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.util.Vector;

import com.github.ebitiri.btooom.system.EntityObject;


/**
 * ふつーな爆弾
 * @author ebi
 */
public class Grenade extends EntityObject<Projectile>{

	private float power;
	
	/**
	 * 初期化
	 */
	public Grenade(Player shooter, Vector v0, float power){
		Snowball sb = shooter.launchProjectile(Snowball.class);
		sb.setVelocity(v0);
		setEntity(sb);
		
		this.power = power;
	}
	
	@Override
	public void onRemove(){
		getLocation().getWorld().createExplosion(getLocation().subtract(getVelocity()), power);
	}
}
