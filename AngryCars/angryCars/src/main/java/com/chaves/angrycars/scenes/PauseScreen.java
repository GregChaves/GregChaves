package com.chaves.angrycars.scenes;

import org.cocos2d.layers.CCColorLayer;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.ccColor4B;

/**
 * @author gregorio.de.chaves
 *
 */
public class PauseScreen extends CCLayer implements ButtonDelegate{
	
	private Button resumeButton;
	private Button quitButton;
	private CCColorLayer background;
	private PauseDelegate delegate;

	public PauseScreen() {
		
		// habilita o toque na tela
		this.setIsTouchEnabled(true);
		
		// background
		this.background = CCColorLayer.node(ccColor4B.ccc4(0, 0, 0, 175),DeviceSettings.screenWidth(),DeviceSettings.screenHeight());
		this.addChild(this.background);
		
		// logo
		CCSprite title = CCSprite.sprite(Assets.LOGO);
		title.setPosition(DeviceSettings.screenResolution(CGPoint.ccp(DeviceSettings.screenWidth() /2 ,DeviceSettings.screenHeight() - 130 )));
		this.addChild(title);
		
		// Add Buttons
		this.resumeButton = new Button(Assets.PLAY);
		this.quitButton = new Button(Assets.EXIT);
		this.resumeButton.setDelegate(this);
		this.quitButton.setDelegate(this);
		this.addChild(this.resumeButton);
		this.addChild(this.quitButton);
		
				// Posiciona botoes
		this.resumeButton.setPosition(DeviceSettings.screenResolution(CGPoint.ccp(DeviceSettings.screenWidth() /2 ,DeviceSettings.screenHeight() - 250 )));
		this.quitButton.setPosition(DeviceSettings.screenResolution(CGPoint.ccp(DeviceSettings.screenWidth() /2 ,DeviceSettings.screenHeight() - 300 )));
		
	}

	@Override
	public void buttonClicked(Button sender) {
		
		if (sender == this.resumeButton) {
			this.delegate.resumeGame();
			this.removeFromParentAndCleanup(true);
			}
		
		if (sender == this.quitButton) {
			this.delegate.quitGame();
			}
		
	}
	
	public void setDelegate(PauseDelegate delegate) {
		this.delegate = delegate;
	}
	
}
