package com.github.ebitiri.btooom.bomb;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.github.ebitiri.btooom.system.GameObject;


/**
 * 毒ガス。消えるまでの間、付近にいるプレイヤーに盲目と毒を付与。
 * 
 * @author ebi
 */
public class PoisonGas extends GameObject{

	private int dur;
	private Location loc;
	private int d2;
	
	/**
	 * 初期化
	 * @param duration　効果時間
	 * @param loc　効果中心
	 * @param dist　効果範囲（半径）
	 */
	public PoisonGas(int duration, Location loc, int dist){
		this.dur = duration;
		this.loc = loc;
		this.d2 = dist * dist;
	}
	
	@Override
	public void tick(){
		//効果時間をへらす
		dur--;
		
		//全Entityでループ
		for(Entity entity : loc.getWorld().getEntities()){
			//生き物なら
			if(entity instanceof LivingEntity){
				//範囲外なら次
				if(entity.getLocation().distanceSquared(loc) > d2) continue;	
				//キャスト
				LivingEntity le = (LivingEntity)entity;
				//盲目
				le.removePotionEffect(PotionEffectType.BLINDNESS);
				le.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 40, 1));
				//毒
				if(loc.getWorld().getFullTime() % 30 == 0){
					le.damage(1);
				}
			}
		}
	}

	@Override
	public boolean isValid(){
		return dur > 0;
	}
}
