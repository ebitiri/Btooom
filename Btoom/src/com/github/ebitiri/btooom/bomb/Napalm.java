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
	
	public Napalm(Player shooter, Vector v0, int dist){
		setEntity(shooter.launchProjectile(EnderPearl.class));
		setVelocity(v0);
		
		this.dist = dist;
	}
	
	@Override
	public void onRemove(){
	
		Block b0 = getLocation().getBlock();
		
		for(int x = -dist; x <= dist; x++){
			for(int y = -dist; y <= dist; y++){
				for(int z = -dist; z <= dist; z++){
					
					Block b = b0.getRelative(x, y, z);
					if(b.getType() == Material.AIR && Math.random() > 0.7){
						b.setType(Material.FIRE);
					}
				}
			}
		}
		
	}
}
