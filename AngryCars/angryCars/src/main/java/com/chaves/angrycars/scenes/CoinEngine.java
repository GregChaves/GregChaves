/**
 * 
 */
package com.chaves.angrycars.scenes;

import java.util.Random;

import org.cocos2d.layers.CCLayer;

/**
 * @author gregorio.de.chaves
 * 
 */
public class CoinEngine extends CCLayer {

	private CoinsEngineDelegate delegate;

	public CoinEngine() {
		this.schedule("circlesEngine", 2.0f / 10f);
	}

	public void circlesEngine(float dt) {
		if (Runner.check().isGamePlaying() && ! Runner.check().isGamePaused()) {
			// sorte: 1 em 10 gera uma nova moeda!
			if (new Random().nextInt(7) == 0) {
				this.getDelegate().createCoin(new Coin(Assets.COIN));
			}
		}
	}

	public void setDelegate(CoinsEngineDelegate delegate) {
		this.delegate = delegate;
	}

	public CoinsEngineDelegate getDelegate() {
		return delegate;
	}

}
