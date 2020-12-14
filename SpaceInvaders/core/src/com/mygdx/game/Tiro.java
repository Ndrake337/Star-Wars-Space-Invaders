
package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*
  Classe Responsavel pelos tiros a serem disparados
 */
public class Tiro {
    public static final int SPEED = 500;
    public static final int DEFAULT_Y = 0;
    private static Texture texture;
    public float WIDTH; // largura da janela
    public float HEIGHT; // altura da janela
    Colisao colisor;
    
    float x,y;
    
    public boolean remove = false;
    
    private Music explosionEffect;
    
    public Tiro (float x,float y){
        this.x=x;
        this.y=y;
        if(texture == null){
            texture = new Texture("Shot-One.png");
        }
        WIDTH = 12.5f;
        HEIGHT = 119;
        
        this.colisor = new Colisao(this.x,this.y, 13, 119);
    }
    
    public void update(float deltaTime){
        y += SPEED* deltaTime;
        if(y > Gdx.graphics.getHeight()){
            remove = true;
        }
        colisor.Move(x, y);
    }
    
    public void render (SpriteBatch batch){
        batch.draw(texture,x,y,texture.getWidth()/8,texture.getHeight()/8);
    }
    
    public Colisao getColisor(){
        return colisor;
    }
}
