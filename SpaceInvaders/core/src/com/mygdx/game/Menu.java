package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

public class Menu implements Screen{
    
    SpaceInvaders game;
    
    Texture Logo;
    Texture PlayButton1;
    Texture PlayButton2;
    Texture ExitButton1;
    Texture ExitButton2;
    
    private static final int EXIT_BUTTON_WIDTH = 300;
    private static final int EXIT_BUTTON_HEIGHT = 150;
    private static final int PLAY_BUTTON_WIDTH = 330;
    private static final int PLAY_BUTTON_HEIGHT = 150;
    
    public Menu( SpaceInvaders game ){
        this.game = game;
        Logo = new Texture("logo.png");
        PlayButton1 = new Texture("play_button_1.png");
        PlayButton2 = new Texture("play_button_2.png");
        ExitButton1 = new Texture("exit_button_1.png");
        ExitButton2 = new Texture("exit_button_2.png");
    }
    
    @Override
    public void show() {
        
    }

    @Override
    public void render(float f) {
        game.batch.begin();//Iniciando o Sprite batch
        game.batch.draw(Logo, 100, 250);
        int x = 250;
        
        if(Gdx.input.getX() < x + PLAY_BUTTON_WIDTH && Gdx.input.getX() > x && 600 - Gdx.input.getY()  < 150 + PLAY_BUTTON_HEIGHT && 600 - Gdx.input.getY() > 150){
            game.batch.draw(PlayButton2, 250, 150,330,150);
            if(Gdx.input.isTouched()){
                game.setScreen(new TelaJogo(game));
            }           
        }
        else{
            game.batch.draw(PlayButton1, 250, 150,330,150);
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
    public void dispose() {
        game.batch.dispose(); // libera o objeto que desenha em lote na placa de video
    }
    
}
