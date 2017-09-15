/**
 * 
 */
package com.chaves.angrycars.scenes;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.transitions.CCFadeTransition;
import org.cocos2d.types.CGPoint;

import com.swarmconnect.Swarm;

/**
 * @author gregorio.de.chaves
 * 
 */
public class MenuButtons extends CCLayer implements ButtonDelegate {

	private Button playButton;
	private Button highscoredButton;
	private Button exitButton;
	private Button soundButton;

	public MenuButtons() {
		
		this.setIsTouchEnabled(true);
		
		this.playButton = new Button(Assets.PLAY);
		this.highscoredButton = new Button(Assets.HIGHSCORE);
		this.exitButton = new Button(Assets.EXIT);
		this.soundButton = new Button(Assets.SOUND);
		
		this.playButton.setDelegate(this);
		this.highscoredButton.setDelegate(this);
		this.exitButton.setDelegate(this);
		this.soundButton.setDelegate(this);
		
		setButtonspPosition();
		
		addChild(playButton);
		addChild(highscoredButton);
		addChild(exitButton);
		addChild(soundButton);
	}
	
	private void setButtonspPosition() {
		// Buttons Positions
		playButton.setPosition(DeviceSettings.screenResolution(CGPoint.ccp(DeviceSettings.screenWidth() /2 ,DeviceSettings.screenHeight() - 250 )));
		highscoredButton.setPosition(DeviceSettings.screenResolution(CGPoint.ccp(DeviceSettings.screenWidth() /2 ,DeviceSettings.screenHeight() - 300 )));
		exitButton.setPosition(DeviceSettings.screenResolution(CGPoint.ccp(DeviceSettings.screenWidth() /2 ,DeviceSettings.screenHeight() - 350 )));
		soundButton.setPosition(DeviceSettings.screenResolution(CGPoint.ccp(DeviceSettings.screenWidth() /2 - 100,DeviceSettings.screenHeight() - 420 )));
	}
	
	@Override
	public void buttonClicked(Button sender) {
		
		if (sender.equals(this.playButton)) {
			System.out.println("Button clicked: Play");
			CCDirector.sharedDirector().replaceScene(CCFadeTransition.transition(1.0f, GameScene.createGame()));
			return;
		}
		if (sender.equals(this.highscoredButton)) {
			Swarm.showDashboard();
			return;
		}
		if (sender.equals(this.exitButton)) {
			System.exit(0);
			return;
		}
		if (sender.equals(this.soundButton)) {
			System.out.println("Button clicked: Sound");
			return;
		}
	}

}
