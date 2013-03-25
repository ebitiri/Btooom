package com.github.ebitiri.btooom.item;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;

import com.github.ebitiri.btooom.BukkitUtil;
import com.github.ebitiri.btooom.system.CustomItem;

/**
 * 爆弾系のアイテム
 * @author ebi
 */
public class BombItem extends CustomItem{
	
	private BombLauncher launcher;
	
	/**
	 * 初期化
	 * @param name ボム名
	 * @param mat ボムアイテムの素材
	 * @param lore 説明文
	 * @param launcher ボムを発射するクラス？
	 */
	public BombItem(String name, Material mat, List<String> lore, BombLauncher launcher){
		super(name, mat);
		setLore(lore);
		this.launcher = launcher;
	}

	@Override
	public boolean onRightClick(Player player){
		launcher.launch(player);
		BukkitUtil.consumeItemInHand(player);
		return true;
	}

	@Override
	public boolean onLeftClick(Player player){
		return true;
	}
}

