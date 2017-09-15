/**
 * 
 */
package com.chaves.angrycars.scenes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.cocos2d.actions.instant.CCCallFunc;
import org.cocos2d.actions.interval.CCFadeOut;
import org.cocos2d.actions.interval.CCScaleBy;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

/**
 * @author gregorio.de.chaves
 * 
 */
public class Coin extends CCSprite {

	private float x, y;
	
	public double moveSpeed = 3;
	
	List<Integer> listCoin = new ArrayList<Integer>();
	
	Random r = new Random();
	
	public Coin(String image) {
		super(image);
		generateList();
		Collections.shuffle(listCoin);
		x = listCoin.get(0);
		y = DeviceSettings.screenHeight();
	}

	private void generateList() {
		
		listCoin.add(123);
		listCoin.add(40);
		listCoin.add(285);
		listCoin.add(205);
		
	}

	public void start() {
		this.schedule("update");
	}

	public void update(float dt) {
		if (Runner.check().isGamePlaying() && ! Runner.check().isGamePaused()) {
			y -= getMoveSpeed();
			this.setPosition(DeviceSettings.screenResolution(CGPoint.ccp(x, y)));
		}
	}

	public void setDelegate(CoinsEngineDelegate delegate) {
	}
	
	public void catchCircle() {
		this.unschedule("update");
		float dt = 0.2f;
		CCScaleBy a1 = CCScaleBy.action(dt, 0.5f);
		CCFadeOut a2 = CCFadeOut.action(dt);
		CCSpawn s1 = CCSpawn.actions(a1, a2);
		CCCallFunc c1 = CCCallFunc.action(this, "removeMe");
		this.runAction(CCSequence.actions(s1, c1));
	}
	
	public void removeMe() {
		this.removeFromParentAndCleanup(true);
	}

	public double getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(double moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	
}