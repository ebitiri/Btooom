package com.github.ebitiri.btooom.system;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.Plugin;




/**
 * {@link GameObject}の管理者。
 * 
 * @author ebi
 */
public class GameObjectManager{

	private LinkedList<GameObject> list;
	private LinkedList<GameObject> addList;
	private HashMap<Entity, EntityObject<?>> entities;
	private Plugin plugin;
	
	/**
	 * 利用プラグインを指定して初期化
	 */
	public GameObjectManager(Plugin plugin){
		this.list = new LinkedList<GameObject>();
		this.addList = new LinkedList<GameObject>();
		this.entities = new HashMap<Entity, EntityObject<?>>();
		this.plugin = plugin;
		
		//スケジューラ登録
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new IR(), 0, 1);
	}
	
	/**
	 * 管理対象の追加
	 */
	public void add(GameObject go){
		//登録済み？
		if(go.manager != null) return;
		
		//管理者設定
		go.manager = this;
		//追加リストへ
		addList.add(go);
		//イベント登録
		Bukkit.getPluginManager().registerEvents(go, plugin);
		
		//Entity関連オブジェクトなら、逆引きマップに追加
		if(go instanceof EntityObject<?>){
			EntityObject<?> eo = (EntityObject<?>)go;
			entities.put(eo.getEntity(), eo);
		}
	}
	
	/**
	 * 
	 */
	public EntityObject<?> getObjectFromEntity(Entity entity){
		return entities.get(entity);
	}
	
	/**
	 * tick更新用内部クラス
	 * @author ebi
	 */
	private class IR implements Runnable{
		@Override
		public void run(){
			//追加リストを処理
			list.addAll(addList);
			addList.clear();
			
			//管理対象全てでループ
			Iterator<GameObject> ite = list.iterator();
			while(ite.hasNext()){
				//next
				GameObject go = ite.next();
				//有効なら更新
				if(go.isValid()){
					go.tick();
				}
				//無効なら削除＆登録解除
				else{
					go.onRemove();
					ite.remove();
					HandlerList.unregisterAll(go);
					if(go instanceof EntityObject<?>){
						entities.remove((EntityObject<?>)go);
					}
				}
			}
		}
	}
}
