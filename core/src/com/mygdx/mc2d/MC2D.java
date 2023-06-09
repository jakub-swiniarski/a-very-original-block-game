package com.mygdx.mc2d;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

public class MC2D extends ApplicationAdapter {
	SpriteBatch batch;
	boolean fullscreen;
	Player steve;
	List<Dirt> dirt;
	List<Grass> grass;
	Vector3 mousePos;

	@Override
	public void create () {
		batch = new SpriteBatch();
		fullscreen=false;
		steve=new Player();
		dirt=new ArrayList<Dirt>();
		for(int i=0; i<16; i++){ //not 16, bc additional column of blocks is needed
			for(int j=0; j<3; j++){
				Dirt newDirt= new Dirt();
				newDirt.rect.x=i*80-1280/2;
				newDirt.rect.y=j*80-4*80;
				dirt.add(newDirt);
			}
		}

		grass=new ArrayList<Grass>();
		for(int i=0; i<16; i++){
			Grass newGrass= new Grass();
			newGrass.rect.x=i*80-1280/2;
			newGrass.rect.y=3*80-4*80;
			grass.add(newGrass);
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

		mousePos=new Vector3(Gdx.input.getX(),Gdx.input.getY(),0);
		steve.cam.unproject(mousePos);

		batch.setProjectionMatrix(steve.cam.combined);
		batch.begin();
		batch.draw(steve.img,steve.rect.x,steve.rect.y);
		for(int i=0; i<dirt.size();i++){
			if(dirt.get(i).rect.contains(mousePos.x,mousePos.y) && Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
				dirt.remove(i);
			}

			else if(dirt.get(i).rect.x+dirt.get(i).rect.width>steve.cam.position.x-steve.cam.viewportWidth/2 &&
			dirt.get(i).rect.x<steve.cam.position.x+steve.cam.viewportWidth/2 &&
			dirt.get(i).rect.y+dirt.get(i).rect.width>steve.cam.position.y-steve.cam.viewportHeight/2 &&
			dirt.get(i).rect.y<steve.cam.position.y+steve.cam.viewportHeight/2){
				batch.draw(dirt.get(i).img,dirt.get(i).rect.x,dirt.get(i).rect.y);
			}
		}

		for(int i=0; i<grass.size();i++) {
			if(grass.get(i).rect.contains(mousePos.x,mousePos.y) && Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
				grass.remove(i);
			}

			else if(grass.get(i).rect.x+grass.get(i).rect.width>steve.cam.position.x-steve.cam.viewportWidth/2 &&
					grass.get(i).rect.x<steve.cam.position.x+steve.cam.viewportWidth/2 &&
					grass.get(i).rect.y+grass.get(i).rect.width>steve.cam.position.y-steve.cam.viewportHeight/2 &&
					grass.get(i).rect.y<steve.cam.position.y+steve.cam.viewportHeight/2) {
				batch.draw(grass.get(i).img, grass.get(i).rect.x, grass.get(i).rect.y);
			}
		}
		batch.end();

		steve.update();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		steve.img.dispose();
		for(int i=0; i<dirt.size();i++){
			dirt.get(i).img.dispose();
		}
		for(int i=0; i<grass.size();i++){
			grass.get(i).img.dispose();
		}
	}
}
