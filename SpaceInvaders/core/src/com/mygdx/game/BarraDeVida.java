
package com.mygdx.game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
/*
  Classe Responsavel pelos tiros a serem disparados
 */
public class BarraDeVida {
    private static Texture lifeBar;
    public int WIDTH; // largura da janela
    public int HEIGHT; // altura da janela
    private final TextureRegion imgRegion[];
    private int i;
    
    float x,y;
    
    public BarraDeVida (float x,float y){
        this.x = x;
        this.y = y;
        lifeBar = new Texture("LifeBarl.png");
        imgRegion = new TextureRegion[4];
        imgRegion[0]= new TextureRegion(lifeBar,12,0,428,64);
        imgRegion[1]= new TextureRegion(lifeBar,444,0,428,64);
        imgRegion[2]= new TextureRegion(lifeBar,876,0,428,64);
        imgRegion[3]= new TextureRegion(lifeBar,1308,0,428,64);
        i = 0;
        
    }
    
    public int update(int vida){
        if(vida == 3){
            this.i = 0;
        }
        else if(vida == 2){
            this.i = 1;
        }
        else if(vida == 1){
            this.i = 2;
        }
        else if(vida == 0){
            this.i = 3;
        }
        return this.i;
    }
    
    public void render (SpriteBatch batch){
        batch.draw(imgRegion[this.i],0,0,imgRegion[this.i].getRegionWidth()/2,imgRegion[this.i].getRegionHeight()/2);
    }
    
}
