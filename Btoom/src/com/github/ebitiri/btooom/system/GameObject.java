package com.github.ebitiri.btooom.system;

import org.bukkit.event.Listener;


/**
 * ゲームの要素。イベントリスナが自動的に登録され、毎tick更新される。
 * 
 * @author ebi
 */
public abstract class GameObject implements Listener{

	GameObjectManager manager;
	
	/**
	 * 管理者を取得
	 */
	public GameObjectManager getManager(){
		return manager;
	}
	
	/**
	 * 毎tick呼ばれる
	 */
	public void tick(){
	}
	
	/**
	 * falseを返すと管理者から削除される。イベントリスナ解除も行う。
	 * @return
	 */
	public abstract boolean isValid();
	
	/**
	 * 管理者から削除される時に呼ばれる。
	 */
	public void onRemove(){
		
	}
}
