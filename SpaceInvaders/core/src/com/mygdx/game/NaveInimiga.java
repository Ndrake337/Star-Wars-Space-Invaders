package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * @author mathe
 */
public class NaveInimiga {
    private static Texture texture;
    public final float WIDTH; // largura da janela
    public final float HEIGHT; // altura da janela
    Colisao colisor;

    
    float x,y;
    
    public int vidas;
    
    public float Origin;
    public float Destiny;
    
    public boolean remove = false;
    
    public NaveInimiga (float x,float y){
        this.x = x;
        this.y = y;
        Origin = x;
        Destiny = x+10;
        vidas = 2;
        if(texture == null){
            texture = new Texture("Tie-Fighter.png");      
        }
        WIDTH = 76.83f;
        HEIGHT = 75.16f;
        this.colisor = new Colisao(this.x, this.y, texture.getWidth()/6 , texture.getHeight()/6);
    }
    
    public void update(float deltaTime){
        boolean valida = true;
        float speed = 50;
        if(x == Origin && valida == false){
            x+= deltaTime + speed;
            y-=deltaTime + speed;
        }
        if(x == 5 && valida == true){
            y+=deltaTime + speed;
        }
        if(x == 10 && valida == true){
            valida = false;
            y-=deltaTime + speed;
            x-= deltaTime + speed;
        }
        if(x == 5 && valida == false){
            y+=deltaTime + speed;
        }
        if(x == Origin && valida == false){
            valida = true;
            y-=deltaTime + speed;
        }
        colisor.Move(x, y);
    }
    
    public void setRemove(){
        remove = true;
    }
    
    public void render (SpriteBatch batch){
        batch.draw(texture,x,y,texture.getWidth()/6,texture.getHeight()/6);
    }
    
    public Colisao getColisor(){
        return colisor;
    }
}
