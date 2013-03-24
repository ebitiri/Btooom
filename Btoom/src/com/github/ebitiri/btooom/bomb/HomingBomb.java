package com.github.ebitiri.btooom.bomb;


import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.util.Vector;

import com.github.ebitiri.btooom.BukkitUtil;
import com.github.ebitiri.btooom.system.EntityObject;



/**
 * 追跡爆弾
 * @author ebi
 */
public class HomingBomb extends EntityObject<Projectile>{

	private LivingEntity target;
	private double speed;
	private double cons;
	private float power;
	
	/**
	 * 
	 * @param speed　速さ
	 * @param cons　慣性
	 * @param power　爆破力
	 */
	public HomingBomb(Player shooter, Vector v0, double speed, double cons, float power){
		//発射
		EnderPearl ep = shooter.launchProjectile(EnderPearl.class);
		ep.setVelocity(v0);
		setEntity(ep);
		
		this.target = null;
		this.speed = speed;
		this.power = power;
		this.cons = cons;
	}
	
	@Override
	public void tick(){
		//標的設定
		if(getEntity().getTicksLived() > 20 && target == null){
			//標的セット
			target = BukkitUtil.getNearestLiving(getLocation(), 2500, getEntity().getShooter());
		}
		
		//標的が設定されていたら
		if(target != null){
			//標的が死んだら見失う
			if(target.isDead()){
				target = null;
				return;
			}
			//差ベクトル　ｘ　速度
			Vector velo = target.getEyeLocation().subtract(getLocation()).toVector().normalize().multiply(speed);
			//前回の速度との加重平均とって慣性っぽくする。
			Vector preV = getVelocity().normalize().multiply(speed);
			setVelocity(velo.add(preV.multiply(cons).multiply(1.0 / (1.0 + cons))));
		}
	}
	
	@Override
	public void onRemove(){	
		BukkitUtil.safeExplode(getLocation(), power);
	}
}
