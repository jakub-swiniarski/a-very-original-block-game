package com.mygdx.mc2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Icon {
    Rectangle rect;
    Texture img;

    public Icon(){
        rect=new Rectangle();
        rect.x=0;
        rect.y=720-80-3;
        rect.width=80;
        rect.height=80;
        //img=new Texture(Gdx.files.internal("icons/dirt.png"));
    }

    public void update(){
        if(Player.currentBlock==1){
            //img=new Texture(Gdx.files.internal("icons/dirt.png"));
        }
        else if(Player.currentBlock==2){
            //img=new Texture(Gdx.files.internal("icons/grass.png"));
        }
    }
}
