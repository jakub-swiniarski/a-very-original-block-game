package com.mygdx.mc2d;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
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
	Player player;
	List<Dirt> dirt;
	List<Grass> grass;
	List<Stone> stone;
	Vector3 mousePos;
	//Icon icon;
	Rectangle grid;

	@Override
	public void create () {
		batch = new SpriteBatch();
		fullscreen=false;
		stage = new Stage();
		world = new World(new Vector2(0,0),false);
		player=new Player();
		//icon=new Icon();
		dirt=new ArrayList<Dirt>();
		for(int i=0; i<17; i++){
			for(int j=0; j<3; j++){
				Dirt newDirt= new Dirt();
				newDirt.rect.x=i*80-1280/2;
				newDirt.rect.y=j*80-4*80;
				dirt.add(newDirt);
			}
		}

		stone=new ArrayList<Stone>();
		for(int i=0; i<17; i++){
			for(int j=0; j<3; j++){
				Stone newStone= new Stone();
				newStone.rect.x=i*80-1280/2;
				newStone.rect.y=-560+j*80;
				stone.add(newStone);
			}
		}

		grass=new ArrayList<Grass>();
		for(int i=0; i<17; i++){
			Grass newGrass= new Grass();
			newGrass.rect.x=i*80-1280/2;
			newGrass.rect.y=3*80-4*80;
			grass.add(newGrass);
		}

		grid= new Rectangle();
		grid.width=80;
		grid.height=80;
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
		player.cam.unproject(mousePos);

		player.gridX=(int)Math.floor(mousePos.x/80)*80; //FIND A WAY TO PREVENT BLOCKS FROM STACKING ON TOP OF EACH OTHER (RIGHT-CLICKING ON AN EXISTING BLOCK)
		player.gridY=(int)Math.floor(mousePos.y/80)*80;
		grid.x=player.gridX;
		grid.y=player.gridY;
		if(Gdx.input.isButtonPressed(Input.Buttons.RIGHT) && !grid.overlaps(player.rect)){
			if(player.currentBlock==1){
				Dirt newDirt= new Dirt();
				newDirt.rect.x=player.gridX;
				newDirt.rect.y=player.gridY;
				dirt.add(newDirt);
			}
			if(player.currentBlock==2){
				Grass newGrass= new Grass();
				newGrass.rect.x=player.gridX;
				newGrass.rect.y=player.gridY;
				grass.add(newGrass);
			}
			if(player.currentBlock==3){
				Stone newStone= new Stone();
				newStone.rect.x=player.gridX;
				newStone.rect.y= player.gridY;
				stone.add(newStone);
			}
		}

		batch.setProjectionMatrix(player.cam.combined);
		batch.begin();
		batch.draw(player.img,player.rect.x,player.rect.y);
		for(int i=0; i<dirt.size();i++){
			dirt.get(i).collisionCheck();
			if(dirt.get(i).rect.contains(mousePos.x,mousePos.y) && Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
				dirt.remove(i);
			}

			else if(dirt.get(i).rect.x+dirt.get(i).rect.width>player.cam.position.x-player.cam.viewportWidth/2 &&
			dirt.get(i).rect.x<player.cam.position.x+player.cam.viewportWidth/2 &&
			dirt.get(i).rect.y+dirt.get(i).rect.width>player.cam.position.y-player.cam.viewportHeight/2 &&
			dirt.get(i).rect.y<player.cam.position.y+player.cam.viewportHeight/2){
				batch.draw(dirt.get(i).img,dirt.get(i).rect.x,dirt.get(i).rect.y);
			}
		}

		for(int i=0; i<grass.size();i++) {
			grass.get(i).collisionCheck();
			if(grass.get(i).rect.contains(mousePos.x,mousePos.y) && Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
				grass.remove(i);
			}

			else if(grass.get(i).rect.x+grass.get(i).rect.width>player.cam.position.x-player.cam.viewportWidth/2 &&
					grass.get(i).rect.x<player.cam.position.x+player.cam.viewportWidth/2 &&
					grass.get(i).rect.y+grass.get(i).rect.width>player.cam.position.y-player.cam.viewportHeight/2 &&
					grass.get(i).rect.y<player.cam.position.y+player.cam.viewportHeight/2) {
				batch.draw(grass.get(i).img, grass.get(i).rect.x, grass.get(i).rect.y);
			}
		}

		for(int i=0; i<stone.size();i++) {
			stone.get(i).collisionCheck();
			if(stone.get(i).rect.contains(mousePos.x,mousePos.y) && Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
				stone.remove(i);
			}

			else if(stone.get(i).rect.x+stone.get(i).rect.width>player.cam.position.x-player.cam.viewportWidth/2 &&
					stone.get(i).rect.x<player.cam.position.x+player.cam.viewportWidth/2 &&
					stone.get(i).rect.y+stone.get(i).rect.width>player.cam.position.y-player.cam.viewportHeight/2 &&
					stone.get(i).rect.y<player.cam.position.y+player.cam.viewportHeight/2) {
				batch.draw(stone.get(i).img, stone.get(i).rect.x, stone.get(i).rect.y);
			}
		}
		batch.end();

		batch.setProjectionMatrix(stage.getBatch().getProjectionMatrix());
		batch.begin();
		//batch.draw(icon.img,icon.rect.x,icon.rect.y);
		batch.end();

		player.update();
		//icon.update();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		player.img.dispose();
		world.dispose();
		stage.dispose();
		for(int i=0; i<dirt.size();i++){
			dirt.get(i).img.dispose();
		}
		for(int i=0; i<grass.size();i++){
			grass.get(i).img.dispose();
		}
		for(int i=0; i<stone.size();i++){
			stone.get(i).img.dispose();
		}
		//icon.img.dispose();
	}
}
