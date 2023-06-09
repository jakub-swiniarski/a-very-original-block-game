package com.mygdx.mc2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Player extends Entity{
    public Player(){
        img=new Texture(Gdx.files.internal("entities/steve.png"));
    }
}
