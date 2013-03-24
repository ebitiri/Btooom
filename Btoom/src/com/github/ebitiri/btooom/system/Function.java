package com.github.ebitiri.btooom.system;


/**
 * ゲーム全体に影響を与えるような機能<br>
 * {@link #on(GameObjectManager)}、{@link #off()}でオン/オフする。
 * @author ebi
 */
public abstract class Function extends GameObject{

	private boolean isOn;
	
	/**
	 * 機能オン
	 */
	public void on(GameObjectManager manager){
		this.isOn = true;
		manager.add(this);
	}
	
	/**
	 * 機能オフ
	 */
	public void off(){
		this.isOn = false;
	}
	
	@Override
	public boolean isValid(){
		return this.isOn;
	}
}
