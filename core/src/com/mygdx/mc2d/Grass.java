package com.mygdx.mc2d;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Grass extends Block{
    public Grass(){
        img=new Texture(Gdx.files.internal("blocks/grass.png"));
    }
}
