package com.mygdx.mc2d;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;

public class Entity {
    Rectangle rect;
    Texture img;

    public Entity(){
        rect=new Rectangle();
        rect.width=80;
        rect.height=80;
        rect.x=0;
        rect.y=0;
    }
}
