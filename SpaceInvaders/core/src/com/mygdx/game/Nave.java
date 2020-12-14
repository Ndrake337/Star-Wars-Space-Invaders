
package com.mygdx.game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*
  Classe Responsavel pelos tiros a serem disparados
 */
public class Nave {
    private static Texture texture;
    public int WIDTH; // largura da janela
    public int HEIGHT; // altura da janela
    Colisao colisor;
    BarraDeVida HealthBar;
    private final TextureRegion imgRegion[];
    public float x,y;
    int i;
    public int vidas;
    public int score;
    public boolean remove = false;
    
    public Nave (float x,float y, int vidas){
        this.x = x;
        this.y = y;
        score = 0;
        this.vidas = vidas;
        WIDTH = 95;
        HEIGHT = 87;
        texture = new Texture("XWingSprite.png");
        imgRegion = new TextureRegion[3];
        imgRegion[0]= new TextureRegion(texture,0,0,284,261);
        imgRegion[1]= new TextureRegion(texture,287,0,284,261);
        imgRegion[2]= new TextureRegion(texture,566,0,284,261);
        
        this.colisor = new Colisao(this.x, this.y,284/3,261/3);
        this.HealthBar = new BarraDeVida(0, 0);
    }
    
    public void update(float deltaTime){
        float speed = 25;
        if( Gdx.input.isKeyPressed(Input.Keys.W) || Gdx.input.isKeyJustPressed(Input.Keys.W)){
            y += deltaTime + speed;
            this.i = 0;
        } 
        else if( Gdx.input.isKeyPressed(Input.Keys.S) || Gdx.input.isKeyJustPressed(Input.Keys.S)){
            y += deltaTime - speed;
            this.i = 0;
        } 
        else if( Gdx.input.isKeyPressed(Input.Keys.A) || Gdx.input.isKeyJustPressed(Input.Keys.A)){
            x += deltaTime - speed;
            this.i = 2;
        } 
        else if( Gdx.input.isKeyPressed(Input.Keys.D) || Gdx.input.isKeyJustPressed(Input.Keys.D)){
            x += deltaTime + speed;
            this.i = 1;
        }
        else{
           this.i = 0; 
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
        batch.draw(imgRegion[this.i],this.x,this.y,imgRegion[this.i].getRegionWidth()/3,imgRegion[this.i].getRegionHeight()/3);
        HealthBar.render(batch);
    }   
    
    public Colisao getColisor(){
        return colisor;
    }
    
    
}
