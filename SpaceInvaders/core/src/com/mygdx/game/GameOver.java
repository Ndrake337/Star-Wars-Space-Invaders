/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class GameOver implements Screen {
    
    SpaceInvaders game;
    Texture Logo;
    Texture PlayAgainButton1;
    Texture PlayAgainButton2;
    Texture ExitButton1;
    Texture ExitButton2;
    Texture bg;
    
    private static final int EXIT_BUTTON_WIDTH = 300;
    private static final int EXIT_BUTTON_HEIGHT = 150;
    private static final int PLAY_BUTTON_WIDTH = 330;
    private static final int PLAY_BUTTON_HEIGHT = 150;

    public GameOver(SpaceInvaders game) {
        this.game = game;
    }
    
    @Override
    public void show() {
        this.game = game;
        Logo = new Texture("game_over.png");
        bg = new Texture("bg2.jpg");
        PlayAgainButton1 = new Texture("playAgain_button_1.png");
        PlayAgainButton2 = new Texture("playAgain_button_2.png");
        ExitButton1 = new Texture("exit_button_1.png");
        ExitButton2 = new Texture("exit_button_2.png");
    }
    
    @Override
    public void render(float f) {
        game.batch.begin();
        
        game.batch.draw(bg, 0, 0);
        
        game.batch.draw(Logo, 30, 440);
        
        int x = 250;
        
        if(Gdx.input.getX() < x + PLAY_BUTTON_WIDTH && Gdx.input.getX() > x && 600 - Gdx.input.getY()  < 150 + PLAY_BUTTON_HEIGHT && 600 - Gdx.input.getY() > 150){
            game.batch.draw(PlayAgainButton2, 100, 150,651,150);
            if(Gdx.input.isTouched()){
                game.setScreen(new TelaJogo(game));
            }           
        }
        else{
            game.batch.draw(PlayAgainButton1, 100, 150,651,150);
        }
        
        if(Gdx.input.getX() < x + EXIT_BUTTON_WIDTH && Gdx.input.getX() > x && 600 - Gdx.input.getY()  < 25 + EXIT_BUTTON_HEIGHT && 600 - Gdx.input.getY() > 25){
            game.batch.draw(ExitButton2, 250, 25,300,150);
            if(Gdx.input.isTouched()){
                Gdx.app.exit();
            }
        }
        else{
            game.batch.draw(ExitButton1, 250, 25,300,150);
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
    public void dispose() {}
    
}
