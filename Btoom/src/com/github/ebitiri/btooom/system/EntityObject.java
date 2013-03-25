package com.github.ebitiri.btooom.system;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.util.Vector;



/**
 * {@link org.bukkit.entity.Entity}をラップする{@link GameObject}
 * 
 * @author ebi
 * @param <E> ラップするEntityの型
 */
public abstract class EntityObject<E extends Entity> extends GameObject{

	private E entity;
	
	/**
	 * 初期化
	 */
	protected EntityObject(){
		this.entity = null;
	}
	
	/**
	 * ラップ対象の設定
	 */
	protected final void setEntity(E entity){
		this.entity = entity;
		//TODO: mngへつーち
	}
	
	/**
	 * ラップされているEntityを取得
	 */
	public final E getEntity(){
		return entity;
	}
	
	/**
	 * 自分に関するイベントか
	 */
	public final boolean myEvent(EntityEvent event){
		return event.getEntity().equals(entity);
	}
	
	/**世界を取得*/
	public final World getWorld(){return entity.getWorld();}
	/**場所を取得*/
	public final Location getLocation(){return entity.getLocation();}
	/** 速度を取得*/
	public final Vector getVelocity(){return entity.getVelocity();}
	/**速度を設定*/
	public final void setVelocity(Vector v){entity.setVelocity(v);}
	
	@Override
	public final boolean isValid(){
		return entity == null ? false : entity.isValid();
	}
}
