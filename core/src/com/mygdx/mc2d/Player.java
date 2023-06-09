package com.mygdx.mc2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;

public class Player extends Entity{
    OrthographicCamera cam;
    public Player(){
        img=new Texture(Gdx.files.internal("entities/steve.png"));
        cam = new OrthographicCamera(1280,720);
    }

    public void update(){
        cam.position.set(rect.x+rect.width/2,rect.y+rect.height/2,0);
        cam.update();
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            rect.x+=5;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            rect.x-=5;
        }
    }
}
