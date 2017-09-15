package com.chaves.angrycars;

import java.util.ArrayList;

import org.cocos2d.nodes.CCDirector;
import org.cocos2d.nodes.CCNode;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.nodes.CCTextureCache;
import org.cocos2d.opengl.CCTexture2D;
import org.cocos2d.types.CGPoint;
import org.cocos2d.types.CGSize;

public class CCVerticalParallaxNode extends CCNode {

	private ArrayList<ParallaxEntity> parallaxEntitieList;

	protected float mParallaxValue;

	private float sX, sY;

	private CGSize winSize;
	private boolean isScrollDown;

	public static CCVerticalParallaxNode node(float sX, float sY,
			boolean isScrollDown) {
		return new CCVerticalParallaxNode(sX, sY, isScrollDown);
	}

	public CCVerticalParallaxNode(float sX, float sY, boolean isScrollDown) {
		this.sX = sX;
		this.sY = sY;
		this.isScrollDown = isScrollDown;
		winSize = CCDirector.sharedDirector().displaySize();
	}

	public void addEntity(float mParallaxFactor, String spriteName, float pX) {
		if (parallaxEntitieList == null) {
			parallaxEntitieList = new ArrayList<ParallaxEntity>();
		}
		parallaxEntitieList.add(new ParallaxEntity(mParallaxFactor, spriteName,
				pX));
	}

	public void setParallaxValue(final float pParallaxValue) {
		this.mParallaxValue = pParallaxValue;
		invalidate();
	}

	public void invalidate() {
		if (parallaxEntitieList == null || parallaxEntitieList.size() <= 0) {
			return;
		}
		for (ParallaxEntity parallaxEntity : parallaxEntitieList) {
			parallaxEntity.invalidate();
		}
	}

	public class ParallaxEntity {
		final float mParallaxFactor;
		private ArrayList<CCSprite> spriteList = new ArrayList<CCSprite>();
		private float spriteHeight;

		public ParallaxEntity(float mParallaxFactor, String spriteName, float pX) {
			// TODO Auto-generated constructor stub
			this.mParallaxFactor = mParallaxFactor;
			init(spriteName, pX);
		}

		public void invalidate() {
			if (spriteList == null || spriteList.size() < 2
					|| mParallaxFactor <= 0) {
				return;
			}
			float incrIndex = mParallaxValue * mParallaxFactor * sY
					* (isScrollDown ? -1 : 1);
			for (CCSprite sprite : spriteList) {
				CGPoint pos = sprite.getPosition();
				pos.y += incrIndex;
				sprite.setPosition(pos);
			}

			CCSprite firstSprite = spriteList.get(0);

			CGPoint posFirst = firstSprite.getPosition();
			CGPoint posEnd = spriteList.get(spriteList.size() - 1)
					.getPosition();

			boolean isSwiped = false;
			if (isScrollDown) {
				if (posFirst.y < 0 && posFirst.y <= -spriteHeight) {
					posFirst.y = posEnd.y + spriteHeight;
					isSwiped = true;
				}
			} else {
				if (posFirst.y > (winSize.height - spriteHeight)
						&& posFirst.y >= winSize.height) {
					posFirst.y = posEnd.y - spriteHeight;
					isSwiped = true;
				}
			}
			if (isSwiped) {
				firstSprite.setPosition(posFirst);
				spriteList.remove(0);
				spriteList.add(firstSprite);
			}

		}

		private void init(String spriteName, float pX) {
			// TODO Auto-generated method stub
			CCTexture2D spriteTexture = CCTextureCache.sharedTextureCache()
					.addImage(spriteName);
			spriteHeight = spriteTexture.getHeight() * sY;
			if (pX != 0) {
				pX -= spriteTexture.getWidth() * sX / 2;
				if (pX < 0) {
					pX = 0;
				} else if (pX > winSize.width) {
					pX = winSize.width - spriteTexture.getWidth() * sX;
				}
			}
			if (isScrollDown) {
				// Init layouts for scroll down...
				float height = 0;
				float temp = winSize.height * 1.10f;
				float maxHeight = winSize.height + spriteHeight;
				if (maxHeight < temp) {
					maxHeight = temp;
				}
				do {
					CCSprite sprite1 = CCSprite.sprite(spriteTexture);
					sprite1.setAnchorPoint(CGPoint.zero());
					sprite1.setScaleX(sX);
					sprite1.setScaleY(sY);
					sprite1.setPosition(CGPoint.ccp(pX, height));
					height += spriteHeight;
					addChild(sprite1);
					spriteList.add(sprite1);
				} while (!(spriteList.size() >= 2 && height >= maxHeight));

				//				LogM.e("maxHeight : " + maxHeight);
				//				LogM.e(spriteList.size() + " : " + height);

			} else {
				// Init layouts for scroll up...
				float height = winSize.height - spriteHeight;
				float temp = -winSize.height * 0.10f - spriteHeight;
				float maxHeight = -spriteHeight * 2;
				if (maxHeight > temp) {
					maxHeight = temp;
				}
				do {
					CCSprite sprite1 = CCSprite.sprite(spriteTexture);
					sprite1.setAnchorPoint(CGPoint.zero());
					sprite1.setScaleX(sX);
					sprite1.setScaleY(sY);
					sprite1.setPosition(CGPoint.ccp(pX, height));
					height -= spriteHeight;
					addChild(sprite1);
					spriteList.add(sprite1);
				} while (!(spriteList.size() >= 2 && height <= maxHeight));
				//				LogM.e("maxHeight : " + maxHeight);
				//				LogM.e(spriteList.size() + " : " + height);

			}

		}

	}

}