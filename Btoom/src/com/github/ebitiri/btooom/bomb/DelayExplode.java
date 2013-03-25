package com.github.ebitiri.btooom.bomb;

import org.bukkit.Location;

import com.github.ebitiri.btooom.BukkitUtil;
import com.github.ebitiri.btooom.system.GameObject;


/**
 *　見えない＆移動しない　時限爆弾のようなもの
 * @author ebi
 */
public class DelayExplode extends GameObject{

	private float power;
	private Location loc;
	private int time;
	private boolean safe;
	
	/**
	 * 初期化
	 * @param loc	爆破位置
	 * @param time	爆破までの時間(tick)
	 * @param power	威力
	 */
	public DelayExplode(Location loc, int time, float power, boolean safe){
		this.loc = loc;
		this.time = time;
		this.power = power;
		this.safe = safe;
	}
	
	@Override
	public void tick(){
		time--;
	}
	
	@Override
	public boolean isValid() {
		return time > 0;
	}
	
	@Override
	public void onRemove(){
		BukkitUtil.explode(loc, power, !safe, false);
	}
	
}
