/**
 * 
 */
package com.chaves.angrycars.scenes;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.types.CGPoint;

/**
 * @author gregorio.de.chaves
 * 
 */
public class GameButtons extends CCLayer implements ButtonDelegate {

	private Button moveButton1;
	private Button moveButton2;
	private GameScene delegate;
	private Button pauseButton;

	public static GameButtons gameButtons() {
		return new GameButtons();
	}

	public GameButtons() {

		// Habilita o toque na tela
		this.setIsTouchEnabled(true);
		this.moveButton1 = new Button(Assets.MOVE_BUTTON1);
		this.moveButton2 = new Button(Assets.MOVE_BUTTON2);
		this.pauseButton = new Button(Assets.PAUSE);

		// Configura as delegações
		this.moveButton1.setDelegate(this);
		this.moveButton2.setDelegate(this);
		this.pauseButton.setDelegate(this);

		setButtonspPosition();

		// Adiciona os botões na tela
		addChild(moveButton1);
		addChild(moveButton2);
		addChild(pauseButton);

	}

	@Override
	public void buttonClicked(Button sender) {

		if (sender.equals(this.moveButton1)) {
			this.delegate.moveP1();
			return;
		}

		if (sender.equals(this.moveButton2)) {
			this.delegate.moveP2();
			return;
		}
		
		if (sender.equals(this.pauseButton)) {
			this.delegate.pauseGameAndShowLayer();
		}
		
	}

	private void setButtonspPosition() {
		moveButton1.setPosition(DeviceSettings.screenResolution(CGPoint.ccp(DeviceSettings.screenWidth() - 280, 40)));
		moveButton2.setPosition(DeviceSettings.screenResolution(CGPoint.ccp(DeviceSettings.screenWidth() - 35, 40)));
		pauseButton.setPosition(DeviceSettings.screenResolution(CGPoint.ccp(40,DeviceSettings.screenHeight() - 30 )));
	}
	
	public void setDelegate(GameScene gameScene) {
		this.delegate = gameScene;
		
	}

}
