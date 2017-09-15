package com.chaves.angrycars.scenes;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGPoint;

public class GameOverScreen extends CCLayer implements ButtonDelegate {

	private ScreenBackground background;
	private Button beginButton;
	private Button exitButton;

	public GameOverScreen() {
		// background
		this.background = new ScreenBackground(Assets.TITLE_BACKGROUND);
		this.background.setPosition(DeviceSettings.screenResolution(CGPoint.ccp(DeviceSettings.screenWidth() / 2.0f, DeviceSettings.screenHeight() / 2.0f)));

		this.addChild(this.background);
		// image
		CCSprite title = CCSprite.sprite(Assets.GAMEOVER);
		title.setPosition(DeviceSettings.screenResolution(CGPoint.ccp(DeviceSettings.screenWidth() / 2,DeviceSettings.screenHeight() - 130)));
		this.addChild(title);
		// habilita o toque na tela
		this.setIsTouchEnabled(true);
		this.beginButton = new Button(Assets.PLAY);
		this.exitButton = new Button(Assets.EXIT);
		this.beginButton.setPosition(DeviceSettings.screenResolution(CGPoint.ccp(DeviceSettings.screenWidth() / 2, DeviceSettings.screenHeight() - 300)));
		this.exitButton.setPosition(DeviceSettings.screenResolution(CGPoint.ccp(DeviceSettings.screenWidth() / 2, DeviceSettings.screenHeight() - 350)));
		this.beginButton.setDelegate(this);
		this.exitButton.setDelegate(this);
		addChild(this.beginButton);
		addChild(this.exitButton);

	}

	public CCScene scene() {
		CCScene scene = CCScene.node();
		scene.addChild(this);
		return scene;

	}
	
	@Override
	public void buttonClicked(Button sender) {
		if (sender.equals(this.beginButton)) {
			SoundEngine.sharedEngine().pauseSound();
			CCDirector.sharedDirector().replaceScene(new TitleScreen().scene());
		}
		if (sender.equals(this.exitButton)) {
			System.exit(0);
		}
	}
	
	
}
