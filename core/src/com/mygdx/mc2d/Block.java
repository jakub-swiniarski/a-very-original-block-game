package com.mygdx.mc2d;

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

    public void collisionCheck(){
        if(Player.rect.y<rect.y+rect.height && Player.rect.y+Player.rect.height>rect.y){
            if(Player.rect.x+Player.rect.width>rect.x && Player.rect.x+Player.rect.width<rect.x+rect.width/2){
                Player.rect.x=rect.x-Player.rect.width;
            }
            if(Player.rect.x<rect.x+rect.width && Player.rect.x>rect.x+rect.width/2){
                Player.rect.x=rect.x+rect.width;
            }
        }
    }
}
