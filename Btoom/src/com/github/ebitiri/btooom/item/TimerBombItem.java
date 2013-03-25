package com.github.ebitiri.btooom.item;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.github.ebitiri.btooom.BukkitUtil;
import com.github.ebitiri.btooom.bomb.TimerBomb;
import com.github.ebitiri.btooom.system.CustomItem;
import com.github.ebitiri.btooom.system.GameObjectManager;

public class TimerBombItem extends CustomItem{
	
	private GameObjectManager gom;
	
	public TimerBombItem(GameObjectManager gom){
		super(ItemNames.TIMER, Material.TNT);
		this.gom = gom;
		
		ArrayList<String> lore = new ArrayList<String>();
		lore.add("右クリックすると向いている方向に投擲する。");
		lore.add("投擲後、１０秒経つと爆発する。");
		lore.add("物に当っても爆発しないが、火や強い爆風に触れると爆発する。");
		lore.add("扱いにくいが、威力は高い。");
		setLore(lore);
	}

	@Override
	public boolean onRightClick(Player player){
		gom.add(new TimerBomb(player.getEyeLocation(), BukkitUtil.throwV0(player, 2.0), 200, 4F));
		BukkitUtil.consumeItemInHand(player);
		return true;
	}

	@Override
	public boolean onLeftClick(Player player){
		return true;
	}
}

