/**
 * 
 */
package com.chaves.angrycars.scenes;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.layers.CCScene;
import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.sound.SoundEngine;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGRect;

import android.annotation.SuppressLint;

import com.chaves.angrycars.CCVerticalParallaxNode;
import com.chaves.angrycars.R.raw;
import com.swarmconnect.Swarm;
import com.swarmconnect.SwarmAchievement;
import com.swarmconnect.SwarmLeaderboard;

/**
 * @author gregorio.de.chaves
 * 
 */
public class GameScene extends CCLayer implements CoinsEngineDelegate, PauseDelegate {

	private static final int LEADERBOARD_ID = 18575;
	private CCVerticalParallaxNode background;
	private CoinEngine coinsEngine;
	private CCLayer coinsLayer;
	private List<Coin> coinsArray;
	private CCLayer playerLayer;
	private Player player1;
	private Player player2;
	@SuppressWarnings("unused")
	private GameScene delegate;
	private ArrayList<Player> playersArray1;
	private ArrayList<Player> playersArray2;
	private CCLayer scoreLayer;
	private Score score;
	private float streetSpeed = 120;
	private PauseScreen pauseScreen;
	private CCLayer layerTop;
	private String finalScore;

	private GameScene() {

		float sX = DeviceSettings.screenWidth() / 320.0f;
		float sY = DeviceSettings.screenHeight() / 480.0f;
		background = CCVerticalParallaxNode.node(sX, sY, true);
		
		background.addEntity(1f, Assets.GAME_BACKGROUND, 0);
		addChild(background);
 
		this.schedule("update");
		
		// coins
		this.coinsLayer = CCLayer.node();
		this.addChild(this.coinsLayer);

		// player
		this.playerLayer = CCLayer.node();
		this.addChild(this.playerLayer);
		
		// botoes do jogo
		GameButtons gameButtonsLayer = GameButtons.gameButtons();
		this.addChild(gameButtonsLayer);
		gameButtonsLayer.setDelegate(this);

		this.scoreLayer = CCLayer.node();
		this.addChild(this.scoreLayer);

		this.addGameObjects();
		preloadCache();
		
		this.layerTop = CCLayer.node();
		this.addChild(this.layerTop);
		
		SoundEngine.sharedEngine().playSound(CCDirector.sharedDirector().getActivity(), raw.super_mario_medley, true);
	}

	public static CCScene createGame() {

		CCScene scene = CCScene.node();
		GameScene layer = new GameScene();
		scene.addChild(layer);
		return scene;
	}

	@Override
	public void createCoin(Coin coin) {
		coin.setDelegate(this);
		this.coinsLayer.addChild(coin);
		
		if (this.score.score >= 30 && this.score.score <= 59){
			coin.setMoveSpeed(4);
		}else if (this.score.score >= 60 && this.score.score <= 89){
			coin.setMoveSpeed(5);
		}else if (this.score.score >= 90 && this.score.score <= 119){
			coin.setMoveSpeed(6);
		}else if (this.score.score >= 120 && this.score.score <= 149){
			coin.setMoveSpeed(6.5);
		}else if (this.score.score >= 150 && this.score.score <= 179){
			coin.setMoveSpeed(7.5);
		}else if (this.score.score >= 180){
			coin.setMoveSpeed(8.5);
		}
		
		coin.start();
		this.coinsArray.add(coin);
	}

	@SuppressLint("UseValueOf") private void addGameObjects() {

		this.coinsArray = new ArrayList<Coin>();
		this.coinsEngine = new CoinEngine();
		
		//this.player1 = new Player(Assets.CARRO1, new Float(DeviceSettings.screenWidth() - 197), 115); //meio
		this.player1 = new Player(Assets.CARRO1, new Float(DeviceSettings.screenWidth() - 280), 120);
		this.playerLayer.addChild(this.player1);
		
		this.playersArray1 = new ArrayList<Player>();
		this.playersArray1.add(this.player1);
		
		this.player2 = new Player(Assets.CARRO2, new Float(DeviceSettings.screenWidth() - 115), 120); //final
		//this.player2 = new Player(Assets.CARRO2, new Float(DeviceSettings.screenWidth() - 35), 115);
		this.playerLayer.addChild(this.player2);
		
		this.playersArray2 = new ArrayList<Player>();
		this.playersArray2.add(this.player2);
		
		// placar
		this.score = new Score();
		this.scoreLayer.addChild(this.score);
		
	}

	public void onEnter() {
		super.onEnter();
		
		Runner.check().setGamePlaying(true);
		Runner.check().setGamePaused(false);
		
		SoundEngine.sharedEngine().setEffectsVolume(1f);
		SoundEngine.sharedEngine().setSoundVolume(1f);
		
		this.schedule("checkHits");
		this.startEngines();
	}

	private void startEngines() {
		this.addChild(this.coinsEngine);
		this.coinsEngine.setDelegate(this);
	}

	public void setDelegate(GameScene gameScene) {
		this.delegate = gameScene;
	}

	public void moveP1() {
		player1.move(player1);
	}
	
	public void moveP2() {
		player2.move(player2);
	}

	public CGRect getBoarders(CCSprite object) {
		CGRect rect = object.getBoundingBox();
		CGPoint GLpoint = rect.origin;
		CGRect GLrect = CGRect.make(GLpoint.x, GLpoint.y, rect.size.width,
				rect.size.height);
		return GLrect;
	}

	private boolean checkRadiusHitsOfArray(List<? extends CCSprite> array1,
			List<? extends CCSprite> array2, GameScene gameScene, String hit) {

		boolean result = false;
		for (int i = 0; i < array1.size(); i++) {
			// Pega objeto do primeiro array
			CGRect rect1 = getBoarders(array1.get(i));
			for (int j = 0; j < array2.size(); j++) {
				// Pega objeto do segundo array
				CGRect rect2 = getBoarders(array2.get(j));
				// Verifica colisão
				if (CGRect.intersects(rect1, rect2)) {
					System.out.println("Colision Detected: " + hit);
					result = true;

					Method method;
					try {
						method = GameScene.class.getMethod(hit, CCSprite.class,	CCSprite.class);
						method.invoke(gameScene, array1.get(i), array2.get(j));
						
					} catch (SecurityException e1) {
						e1.printStackTrace();
					} catch (NoSuchMethodException e1) {
						e1.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return result;
	}
	
	private boolean checkRadiusOfArray(List<? extends CCSprite> array1, GameScene gameScene, String hit) {

		boolean result = false;
		for (int i = 0; i < array1.size(); i++) {
			// Pega objeto do primeiro array
			CGRect rect1 = getBoarders(array1.get(i));
				// Pega objeto do segundo array
				CGRect rect2 = CGRect.make(105, -15, 60, 60);
				CGRect rect3 = CGRect.make(185, -15, 60, 60);
				CGRect rect4 = CGRect.make(265, -15, 60, 60);
				CGRect rect5 = CGRect.make(18, -15, 60, 60);
				// Verifica colisão
				if (CGRect.intersects(rect1, rect2) || 
						CGRect.intersects(rect1, rect3) || 
						CGRect.intersects(rect1, rect4) ||
						CGRect.intersects(rect1, rect5)
						) {
					
					System.out.println("Colision Detected: " + hit);
					result = true;

					Method method;
					try {
						method = GameScene.class.getMethod(hit);
						method.invoke(gameScene);
						
					} catch (SecurityException e1) {
						e1.printStackTrace();
					} catch (NoSuchMethodException e1) {
						e1.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
		}
		return result;
	}

	public void checkHits(float dt) {
		this.checkRadiusHitsOfArray(this.coinsArray, this.playersArray1, this,"playerHit");
		this.checkRadiusHitsOfArray(this.coinsArray, this.playersArray2, this,"playerHit");
		this.checkRadiusOfArray(this.coinsArray, this, "coinMiss");
	}

	@Override
	public void upPoint(Coin coin) {
		this.coinsArray.remove(coin);
	}
	
	public void playerHit(CCSprite coin, CCSprite player) {
		((Coin) coin).catchCircle();
		SoundEngine.sharedEngine().playEffect(CCDirector.sharedDirector().getActivity(), raw.smb_coin);
		this.score.increase();
		
		if (this.score.score == 30){
			this.streetSpeed += 50;
			SwarmAchievement.unlock(22471);
		}else if (this.score.score == 60){
			this.streetSpeed += 50;
			SwarmAchievement.unlock(22473);
		}else if (this.score.score == 90){
			this.streetSpeed += 50;
			SwarmAchievement.unlock(22475);
		}else if (this.score.score == 120){
			this.streetSpeed += 50;
			SwarmAchievement.unlock(22477);
		}else if (this.score.score == 150){
			this.streetSpeed += 50;
			SwarmAchievement.unlock(22479);
		}else if (this.score.score == 180){
			this.streetSpeed += 50;
			SwarmAchievement.unlock(22481);
		}
		
		this.coinsArray.remove(coin);
		}
	
	public void coinMiss() {
		SoundEngine.sharedEngine().pauseSound();
		SoundEngine.sharedEngine().playEffect(CCDirector.sharedDirector().getActivity(), raw.smb_mariodie);
		finalScore = String.valueOf(this.score.score);
		scoreSubmit(finalScore);
		Swarm.showAchievements();
		CCDirector.sharedDirector().replaceScene(new GameOverScreen().scene());
	}
	
	private void scoreSubmit(String rank) {
		SwarmLeaderboard leaderboard = new SwarmLeaderboard();
		leaderboard.submitScore(LEADERBOARD_ID, new Float(rank));

    }
	
	public void update(float t) {
		if (!Runner.check().isGamePaused() && Runner.check().isGamePlaying()) {
			background.setParallaxValue(this.streetSpeed * t);
		}
	}
	
	private void preloadCache() {
		
		SoundEngine.sharedEngine().setSoundVolume(5f);
		SoundEngine.sharedEngine().preloadEffect(CCDirector.sharedDirector().getActivity(),raw.super_mario_medley);
		SoundEngine.sharedEngine().setEffectsVolume(3f);
		SoundEngine.sharedEngine().preloadEffect(CCDirector.sharedDirector().getActivity(),raw.smb_coin);
		SoundEngine.sharedEngine().preloadEffect(CCDirector.sharedDirector().getActivity(),raw.smb_mariodie);
	}
	
	private void pauseGame() {
		if (!Runner.check().isGamePaused() && Runner.check().isGamePlaying()) {
			Runner.setGamePaused(true);
		}
	}
	
	@Override
	public void resumeGame() {
		if (Runner.check().isGamePaused() || ! Runner.check().isGamePlaying()) {
			// Continua o jogo
			this.pauseScreen = null;
			Runner.setGamePaused(false);
			this.setIsTouchEnabled(true);
		}
	}
	
	@Override
	public void quitGame() {
		SoundEngine.sharedEngine().setEffectsVolume(0f);
		SoundEngine.sharedEngine().setSoundVolume(0f);
		CCDirector.sharedDirector().replaceScene(new TitleScreen().scene());
	}
	
	public void pauseGameAndShowLayer() {
		if (Runner.check().isGamePlaying() && ! Runner.check().isGamePaused()) {
			this.pauseGame();
		}
		if (Runner.check().isGamePaused() && Runner.check().isGamePlaying() &&
			this.pauseScreen == null) {
			this.pauseScreen = new PauseScreen();
			this.layerTop.addChild(this.pauseScreen);
			this.pauseScreen.setDelegate(this);
		}
	}
	
}
