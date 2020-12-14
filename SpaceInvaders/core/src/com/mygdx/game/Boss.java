/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author mathe
 */
public class Boss {
    
    SpaceInvaders game;
    private static Texture texture;
    Colisao colisor;
    float x,y;
    public int vidas;
    public float Origin;
    public float Destiny;
    public boolean remove = false;
    public boolean Limite = false;
    public Boss (float x, float y, SpaceInvaders game){
        this.x = x;
        this.y = y;
        Origin = x;
        Destiny = x+10;
        vidas = 18;
        if(texture == null){
            texture = new Texture("boss1.png");      
        }
        this.colisor = new Colisao(this.x, this.y, texture.getWidth() , texture.getHeight());
    }
    
    public void update(float deltaTime){
        int speed = 8;
        if(x== 674){
            Limite = false;  
        }
        if(x==0){
           Limite = true;
        }
        if(Limite == false){
            x -= deltaTime + speed;
        }
        if(Limite == true){
            x += deltaTime + speed;
        }
        
        if(x<= 0){
            x = 0;
        }
        if(x>= 674){
            x = 674;
        }
        this.colisor.Move(x, y);
    }
    
    public void setRemove(){
        remove = true;
    }
    public void updateVidas(){
        this.vidas -= 1;
    }

    public void render (SpriteBatch batch){
        batch.draw(texture,this.x,this.y);
    }
    
    public Colisao getColisor(){
        return colisor;
    }
}
