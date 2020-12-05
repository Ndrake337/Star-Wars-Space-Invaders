
package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/*
  Classe Responsavel pelos tiros a serem disparados
 */
public class Nave {
    private static Texture texture;
    public int WIDTH; // largura da janela
    public int HEIGHT; // altura da janela
    Colisao colisor;
    BarraDeVida HealthBar;
    
    public float x,y;
    
    public int vidas;
    
    public boolean remove = false;
    
    public Nave (float x,float y){
        this.x = x;
        this.y = y;
        vidas = 3;
        WIDTH = 95;
        HEIGHT = 87;
        if(texture == null){
            texture = new Texture("XWing.png");
        }
        
        this.colisor = new Colisao(this.x, this.y,texture.getWidth()/3,texture.getHeight()/3);
        this.HealthBar = new BarraDeVida(0, 0);
    }
    
    public void update(float deltaTime){
        float speed = 25;
        if( Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyJustPressed(Input.Keys.W)){
            y += deltaTime + speed;
        } 
        else if( Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyJustPressed(Input.Keys.S)){
            y += deltaTime - speed;
        } 
        else if( Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyJustPressed(Input.Keys.A)){
            x += deltaTime - speed;
        } 
        else if( Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyJustPressed(Input.Keys.D)){
            x += deltaTime + speed;
        }

        if(x<0){
            x = 0;
        }
        if(x>705.3F){
            x = 705.3F;
        }
        if(y > 513 || y<0){
            if(y<0){
                y = 0;
            }
            if(y>513){
                y = 513;
            }
        }
        
        colisor.Move(x, y);
    }
    
    public void HealthBarUpdate(){
        this.vidas -= 1;
        if(this.vidas <= 0){
            this.vidas = 0;
        }
        HealthBar.update(this.vidas);
    }
    
    
    public void render (SpriteBatch batch){
        batch.draw(texture,x,y,texture.getWidth()/3,texture.getHeight()/3);
        HealthBar.render(batch);
    }   
    
    public Colisao getColisor(){
        return colisor;
    }
    
    
}
