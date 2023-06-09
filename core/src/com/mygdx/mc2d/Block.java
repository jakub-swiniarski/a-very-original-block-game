package com.mygdx.mc2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Block {
    Rectangle rect;
    Texture img;

    public Block(){
        rect=new Rectangle();
        rect.width=80;
        rect.height=80;
        rect.x=0;
        rect.y=0;
    }

    public void checkForInteraction(){
        if(rect.contains(Gdx.input.getX(),720-Gdx.input.getY()) && Gdx.input.isButtonPressed(Input.Buttons.LEFT)){
            System.out.println("collision detected");
        }
    }
}
