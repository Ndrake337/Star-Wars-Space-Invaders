package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import static com.mygdx.game.TelaJogo.shootEnemyWaitTime;
import static com.mygdx.game.TelaJogo.shootWaitTime;
import java.util.ArrayList;

public class TelaBoss implements Screen{
    SpaceInvaders game;
    
    ArrayList<Tiro> tiros;
    ArrayList<BossShoot> tirosDoBoss;
    private Boss boss;   
    private Nave xwing;
    private Texture img;
    private FPSLogger fpsGame;
    
    float shootTimer;
    float shootEnemyTimer;
    
    BitmapFont scoreFont;
    
    public TelaBoss( SpaceInvaders game , Nave xwing){
        this.game = game;
     
        this.xwing = xwing;
        
    }
    
    @Override
    public void show() {
        img = new Texture("bg2.jpg");
        fpsGame = new FPSLogger();
        boss = new Boss(332,300, game);
        tiros = new ArrayList<Tiro>();
        tirosDoBoss = new ArrayList<BossShoot>();
        shootTimer = 0;  
        shootEnemyTimer = 0;
        scoreFont = new BitmapFont(Gdx.files.internal("fontes/score.fnt"));
        
    }

    @Override
    public void render(float f) {
        float delta = Gdx.graphics.getDeltaTime();
        fpsGame.log(); // apresenta o fps instantaneo da janela
        game.batch.begin();//Iniciando o Sprite batch
        game.batch.draw(img,0,0);//background

        
        boss.update(delta);
        xwing.update(delta);
        
        xwing.render(game.batch);
        boss.render(game.batch);
        
        shootTimer +=delta;             
        if(Gdx.input.isKeyPressed(Input.Keys.SPACE) && shootTimer>=shootWaitTime){
            shootTimer = 0;
            tiros.add(new Tiro(xwing.x-2,xwing.y));
            tiros.add(new Tiro(xwing.x+84.6f,xwing.y));
        }
        
        //Update Tiros
        ArrayList<Tiro> tirosRemovidos = new ArrayList<Tiro>();
        for(Tiro tiro : tiros ){
            tiro.update(delta);
            if(tiro.remove){
                tirosRemovidos.add(tiro);
            }
        }
        for(Tiro tiro : tiros ){
            tiro.render(game.batch);
        }
        
        if(xwing.getColisor().colideCom(boss.getColisor())){
            xwing.HealthBarUpdate();
        }
        for(Tiro disparo : tiros){
            if(disparo.getColisor().colideCom(boss.getColisor()) == true){
                boss.updateVidas();
                tirosRemovidos.add(disparo);
            }
        }
        
        ArrayList<BossShoot> tirosInimigosParaRemover = new ArrayList<BossShoot>();
        for(BossShoot tiro : tirosDoBoss ){
            tiro.update(delta);
            if(tiro.remove){
                tirosInimigosParaRemover.add(tiro);
            }
        }
                
        for(BossShoot tiro : tirosDoBoss ){
            tiro.render(game.batch);
        }

        shootEnemyTimer +=delta; 
        if(shootEnemyTimer>=shootEnemyWaitTime){
            shootEnemyTimer = 0;
            tirosDoBoss.add(new BossShoot(boss.x,boss.y-90));
            tirosDoBoss.add(new BossShoot(boss.x+50,boss.y-30));
            tirosDoBoss.add(new BossShoot(boss.x-50,boss.y-30));
        }
        
        for(BossShoot tiro : tirosDoBoss ){
            if(tiro.getColisor().colideCom(xwing.getColisor())){
                xwing.HealthBarUpdate();
                tirosInimigosParaRemover.add(tiro);
            }
        }
        
        if(boss.vidas == 0 && xwing.vidas > 0){
            xwing.score +=1000;
            game.setScreen(new EndGame(game, xwing.score));
        }
        if(boss.vidas != 0 && xwing.vidas == 0){
            game.setScreen(new GameOver(game, xwing));
            
        }
        GlyphLayout scoreLayout = new GlyphLayout(scoreFont, "Pontos: " + xwing.score);
        scoreFont.draw(game.batch, scoreLayout, Gdx.graphics.getWidth() - scoreLayout.width , scoreLayout.height);
                
        tiros.removeAll(tirosRemovidos);
        tirosDoBoss.removeAll(tirosInimigosParaRemover);
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
