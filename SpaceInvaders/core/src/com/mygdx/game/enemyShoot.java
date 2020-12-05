package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author mathe
 */
public class enemyShoot {
    public static final int SPEED = 250;
    public static final int DEFAULT_Y = 0;
    private static Texture texture;
    Colisao colisor;
    
    float x,y;
    
    public boolean remove = false;
    
    public enemyShoot (float x,float y){
        this.x=x;
        this.y = y;
        
        if(texture == null){
            texture = new Texture("Shot-two.png");
        }
        this.colisor = new Colisao(this.x, this.y, texture.getWidth()/8 , texture.getHeight()/8);
    }
    
    public void update(float deltaTime){
        y -= SPEED* deltaTime;
        if(y < 0){
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
