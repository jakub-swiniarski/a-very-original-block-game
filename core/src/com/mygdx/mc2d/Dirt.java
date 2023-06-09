package com.mygdx.mc2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Dirt extends Block{
    public Dirt(){
        img=new Texture(Gdx.files.internal("blocks/dirt.png"));
    }
}
