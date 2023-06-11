package com.mygdx.mc2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Player extends Entity{
    static Rectangle rect;
    OrthographicCamera cam;
    static int currentBlock;
    int gridX,gridY;
    static boolean falling,jumping;
    int jumpX;
    public Player(){
        rect=new Rectangle();
        rect.width=80;
        rect.height=80;
        rect.x=0;
        rect.y=500;
        img=new Texture(Gdx.files.internal("entities/player.png"));
        cam = new OrthographicCamera(1280,720);
        currentBlock=1;
        falling = true;
        jumping=false;
        jumpX=30;
    }

    public void update(){
        cam.position.set(rect.x+rect.width/2,rect.y+rect.height/2,0);
        cam.update();
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && !Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            rect.x+=5;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && !Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            rect.x-=5;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP) && !jumping && !falling){
            jumping=true;
            jumpX=30;
        }

        if(Gdx.input.isKeyPressed(Input.Keys.NUM_1)){
            currentBlock=1;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_2)){
            currentBlock=2;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.NUM_3)){
            currentBlock=3;
        }

        if(jumping && jumpX!=0){
            falling=false;
            rect.y+=5;
            jumpX--;
        }
        if(falling){
            rect.y-=5;
        }
        falling=true;
    }
}
