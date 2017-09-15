package com.chaves.angrycars.scenes;

import org.cocos2d.layers.CCLayer;
import org.cocos2d.opengl.CCBitmapFontAtlas;

/**
 * @author gregorio.de.chaves
 *
 */
public class Score extends CCLayer {
	
	public int score;
	private CCBitmapFontAtlas text;
	
	public Score(){
		
		this.score = 0;
		
		this.text = CCBitmapFontAtlas.bitmapFontAtlas(String.valueOf(this.score),"UniSansBold_AlphaNum_50.fnt");
		
		this.text.setScale((float) 200 / 200);
		
		this.setPosition(DeviceSettings.screenWidth()-50, DeviceSettings.screenHeight()-50);
		this.addChild(this.text);
		
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void increase() {
		this.score += 3;
		this.text.setString(String.valueOf(this.score));
		}

}
