/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Timer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mathe
 */
public class Introscreen implements Screen{
    SpaceInvaders game;
    private Texture texture;
    private Timer delayTester; 
    private TextureRegion imgRegion[];    
    int j;
    
    public Introscreen( SpaceInvaders game ){
        this.game = game;
    }
    
    @Override
    public void show() {
        texture = new Texture("IntroScreen2.png");
        int aux = 0;
        imgRegion = new TextureRegion[28];
        for(int i= 0; i<28; i++){
            imgRegion[i]= new TextureRegion(texture,aux,0,800,600);
            aux +=800;
        }
        j=0;
    }

    @Override
    public void render(float f) {
        
        game.batch.begin();//Iniciando o Sprite batch
        game.batch.draw(imgRegion[j%28],0,0);
        try {
            Thread.sleep(150);
                    } catch (InterruptedException ex) {
            Logger.getLogger(Introscreen.class.getName()).log(Level.SEVERE, null, ex);
        }
        j++;
        if(j>28){
            game.setScreen(new Menu(game));
        }
        game.batch.end();
    }

    @Override
    public void resize(int i, int i1) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        game.batch.dispose(); // libera o objeto que desenha em lote na placa de video
    }
}
