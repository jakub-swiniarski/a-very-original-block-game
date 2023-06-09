package com.mygdx.mc2d;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.ArrayList;
import java.util.List;

public class MC2D extends ApplicationAdapter {
	SpriteBatch batch;
	boolean fullscreen;
	World world;
	Stage stage;
	Player steve;
	List<Dirt> dirt;
	List<Grass> grass;
	Vector3 mousePos;
	Icon icon;

	@Override
	public void create () {
		batch = new SpriteBatch();
		fullscreen=false;
		stage = new Stage();
		world = new World(new Vector2(0,0),false);
		steve=new Player();
		icon=new Icon();
		dirt=new ArrayList<Dirt>();
		for(int i=0; i<16; i++){ //not 16, bc additional column of blocks is needed
			for(int j=0; j<3; j++){
				Dirt newDirt= new Dirt();
				newDirt.rect.x=i*80-1280/2+40;
				newDirt.rect.y=j*80-4*80;
				dirt.add(newDirt);
			}
		}

		grass=new ArrayList<Grass>();
		for(int i=0; i<16; i++){
			Grass newGrass= new Grass();
			newGrass.rect.x=i*80-1280/2+40;
			newGrass.rect.y=3*80-4*80;
			grass.add(newGrass);
		}
	}

	@Override
	public void render () {
		ScreenUtils.clear(0, 1, 1, 1);
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

		steve.gridX=(int)Math.floor(mousePos.x/80)*80;
		steve.gridY=(int)Math.floor(mousePos.y/80)*80;
		if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT)){
			if(steve.currentBlock==1){
				Dirt newDirt= new Dirt();
				newDirt.rect.x=steve.gridX;
				newDirt.rect.y=steve.gridY;
				dirt.add(newDirt);
			}
			if(steve.currentBlock==2){
				Grass newGrass= new Grass();
				newGrass.rect.x=steve.gridX;
				newGrass.rect.y=steve.gridY;
				grass.add(newGrass);
			}
		}

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

		batch.setProjectionMatrix(stage.getBatch().getProjectionMatrix());
		batch.begin();
		batch.draw(icon.img,icon.rect.x,icon.rect.y);
		batch.end();

		steve.update();
		icon.update();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		steve.img.dispose();
		world.dispose();
		stage.dispose();
		for(int i=0; i<dirt.size();i++){
			dirt.get(i).img.dispose();
		}
		for(int i=0; i<grass.size();i++){
			grass.get(i).img.dispose();
		}
		icon.img.dispose();
	}
}
