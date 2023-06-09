package com.mygdx.mc2d;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class MC2D extends ApplicationAdapter {
	SpriteBatch batch;
	boolean fullscreen;
	Player steve;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		fullscreen=false;
		steve=new Player();
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 0, 0, 1);
		if(Gdx.input.isKeyJustPressed(Input.Keys.F11)){
			fullscreen=!fullscreen;
		}
		if(fullscreen){
			Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
		}
		else{
			Gdx.graphics.setWindowedMode(1600,900);
		}
		batch.begin();
		batch.draw(steve.img,steve.rect.x,steve.rect.y);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		steve.img.dispose();
	}
}
