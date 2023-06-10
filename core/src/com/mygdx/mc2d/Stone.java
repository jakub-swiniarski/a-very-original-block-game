package com.mygdx.mc2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Stone extends Block{
    public Stone(){
        img=new Texture(Gdx.files.internal("blocks/stone.png"));
    }
}
