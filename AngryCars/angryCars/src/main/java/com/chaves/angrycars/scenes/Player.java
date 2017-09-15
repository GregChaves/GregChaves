/**
 * 
 */
package com.chaves.angrycars.scenes;

import org.cocos2d.actions.interval.CCFadeOut;
import org.cocos2d.actions.interval.CCScaleBy;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.actions.interval.CCSpawn;
import org.cocos2d.nodes.CCSprite;

/**
 * @author gregorio.de.chaves
 * 
 */
public class Player extends CCSprite {

	float positionX;
	float positionY = 120;
	

	public Player(String filePath, float carroPosX, float carroPosY) {
		super(filePath);
		setPosition(carroPosX, carroPosY);
	}

	public void move(Player player) {
		
		if (Runner.check().isGamePlaying() && ! Runner.check().isGamePaused()) {
			
			if (player.getPosition().x == 123) {
				positionX = 40;
			}
			
			if (player.getPosition().x == 40){
				positionX = 123;
			}
			
			
			if (player.getPosition().x == 285) {
				positionX = 205;
			}
			
			if (player.getPosition().x == 205){
				positionX = 285;
			}
			
			setPosition(positionX, positionY);
		}
	}

	public void explode() {
		// Para o agendamento
	//	this.unschedule("update");
		// Cria efeitos
		float dt = 0.2f;
		CCScaleBy a1 = CCScaleBy.action(dt, 2f);
		CCFadeOut a2 = CCFadeOut.action(dt);
		CCSpawn s1 = CCSpawn.actions(a1, a2);
		// Roda os efeitos
		this.runAction(CCSequence.actions(s1));
	}

}
