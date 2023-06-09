package com.mygdx.mc2d;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

public class MC2D extends ApplicationAdapter {
	SpriteBatch batch;
	boolean fullscreen;
	Player steve;
	List<Dirt> dirt;

	@Override
	public void create () {
		batch = new SpriteBatch();
		fullscreen=false;
		steve=new Player();
		dirt=new ArrayList<Dirt>();
		for(int i=0; i<17; i++){ //not 16, bc additional column of blocks is needed
			for(int j=0; j<3; j++){
				Dirt newDirt= new Dirt();
				newDirt.rect.x=i*80;
				newDirt.rect.y=j*80;
				dirt.add(newDirt);
			}
		}
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
			Gdx.graphics.setWindowedMode(1280,720);
		}
		batch.begin();
		batch.draw(steve.img,steve.rect.x,steve.rect.y);
		for(int i=0; i<dirt.size();i++){
			if(dirt.get(i).rect.contains(Gdx.input.getX(),720-Gdx.input.getY()) && Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
				dirt.remove(i);
			}

			batch.draw(dirt.get(i).img,dirt.get(i).rect.x,dirt.get(i).rect.y);
		}
		batch.end();

		steve.checkForInput();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		steve.img.dispose();
		for(int i=0; i<dirt.size();i++){
			dirt.get(i).img.dispose();
		}
	}
}
