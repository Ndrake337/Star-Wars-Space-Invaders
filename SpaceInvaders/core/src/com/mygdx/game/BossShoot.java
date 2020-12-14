/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import static com.badlogic.gdx.math.MathUtils.random;



/**
 *
 * @author mathe
 */
public class BossShoot {
    int XSPEED = (random.nextInt() % 2 == 0) ? 250 : -250;
    private static int YSPEED = 250;
    public static final int DEFAULT_Y = 0;
    private static Texture texture;
    Colisao colisor;
    
    float x,y;
    
    public boolean remove = false;
    
    public BossShoot (float x,float y){
        this.x=x;
        this.y = y;
        
        if(texture == null){
            texture = new Texture("BossBullet.png");
        }
        this.colisor = new Colisao(this.x, this.y, texture.getWidth()/8 , texture.getHeight()/8);
    }
    
    public void update(float deltaTime){
        

        y -= YSPEED* deltaTime;
        x += XSPEED* deltaTime;
        if(y < 0 ||  x <0 || x> 800){
            remove = true;
        }
        colisor.Move(x, y);
    }
    
    public void render (SpriteBatch batch){
        batch.draw(texture, x,y,texture.getWidth()/8,texture.getHeight()/8);
    }
    
    public Colisao getColisor(){
        return colisor;
    }
    
}
