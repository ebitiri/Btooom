package com.github.ebitiri.btooom.bomb;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.util.Vector;

import com.github.ebitiri.btooom.BukkitUtil;
import com.github.ebitiri.btooom.system.EntityObject;



/**
 * 反重力爆弾
 * 
 * @author ebi
 */
public class AntiGravityBomb extends EntityObject<Projectile>{

	private int d;
	
	public AntiGravityBomb(Player shooter, Vector v0){
		setEntity(shooter.launchProjectile(EnderPearl.class));
		setVelocity(v0);
		
		d = 3;
	}
	
	@Override
	public void onRemove(){
		//音だけ
		BukkitUtil.explode(getLocation(), 0F, false, false);
		
		//Entity巻き上げ
		int d2 = d * d;
		for(Entity en : getWorld().getEntities()){
			if(en.getLocation().distance(getLocation()) < d2){
				en.setVelocity(new Vector(0, 2.0, 0));
			}
		}
		
		//ブロック巻き上げ
		Block b0 = getLocation().getBlock();
		for(int x = -d; x <= d; x++){
			for(int y = -d; y <= d; y++){
				for(int z = -d; z <= d; z++){
					//相対位置からブロック取得
					Block b = b0.getRelative(x, y, z);
					//ブロックの種類
					int id = b.getTypeId();
					//壊せない奴はナシ
					if(id == 0 || id == 7 || id == 49) continue;
					if(8 <= id && id <= 11) continue;
					//飛ばす
					FallingBlock fb = getWorld().spawnFallingBlock(b.getLocation(), b.getTypeId(), b.getData());
					fb.setVelocity(new Vector(0, 1.5 + Math.random(), 0));
					//ブロックを消す
					b.setType(Material.AIR);
				}
			}
		}
	}
}
