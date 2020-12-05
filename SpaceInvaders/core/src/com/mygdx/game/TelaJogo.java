package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.util.ArrayList;

public class TelaJogo implements Screen{
        ArrayList<Tiro> tiros;
        ArrayList<NaveInimiga> inimigos;
        ArrayList<enemyShoot> tirosInimigos;
        private Nave xwing;
        private NaveInimiga inimigo;
	private Texture img;
        private Texture lifeBar;
	private FPSLogger fpsGame;
        private BarraDeVida XWingHealthBar;
        private TextureRegion imgRegion[];
        private int WIDTH; // largura da janela
        private int HEIGHT; // altura da janela
        public static final float shootWaitTime = 0.3f;
        public static final float shootEnemyWaitTime = 1.2f;
        public static final int totalInimigos = 5;
        float shootTimer;
        float shootEnemyTimer;
        boolean criarInimigos = true;
        boolean Alternador = true;
        SpaceInvaders game;

        public TelaJogo(SpaceInvaders game) {
            this.game = game;
        }
	
        @Override
	public void show () {
            WIDTH = Gdx.graphics.getWidth();
            HEIGHT = Gdx.graphics.getHeight();    
            fpsGame = new FPSLogger();
	    xwing = new Nave(400,0);
            tiros = new ArrayList<Tiro>();
            lifeBar = new Texture("LifeBarl.png");
            imgRegion = new TextureRegion[4];
            imgRegion[0]= new TextureRegion(lifeBar,15,0,228,64);
            imgRegion[1]= new TextureRegion(lifeBar,255,0,228,64);
            imgRegion[2]= new TextureRegion(lifeBar,495,0,228,64);
            imgRegion[3]= new TextureRegion(lifeBar,735,0,228,64);
            tirosInimigos = new ArrayList<enemyShoot>();
            inimigos = new ArrayList<NaveInimiga>();
            img = new Texture("bg2.jpg");
            XWingHealthBar = new BarraDeVida(0, 0);
            shootTimer = 0;  
            shootEnemyTimer = 0;  
	}

        @Override
	public void render ( float f ) {              
                fpsGame.log(); // apresenta o fps instantaneo da janela
		game.batch.begin();//Iniciando o Sprite batch
                game.batch.draw(img,0,0);//background
                float delta = Gdx.graphics.getDeltaTime();//variavel Delta Time para calculo de velocidade
                /*
                
                    Tiros XWing
                
                */
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
                
                /*
                
                    Spawn Inimigos
                
                */
                float startx = 50;
                float starty = 500;
                
                //cria Inimigos
                for(int i = 0; i<=totalInimigos; i++){
                    if(i<6 && criarInimigos == true){
                        inimigos.add(new NaveInimiga(startx, starty));
                        startx += 150;
                        if(i == 4){
                            criarInimigos = false;
                        }
                    }
                }
                
                //Tiros dos Inimigos Basicos
                ArrayList<NaveInimiga> inimigosMortos = new ArrayList<NaveInimiga>();
                for(NaveInimiga enemy : inimigos ){
                    enemy.render(game.batch);
                    //Tiros
                    shootEnemyTimer +=delta; 
                    if(shootEnemyTimer>=shootEnemyWaitTime){
                        shootEnemyTimer = 0;
                        tirosInimigos.add(new enemyShoot(enemy.x-2,enemy.y-2));
                    }
                    //Colisão entre nave e inimigos
                        if(xwing.getColisor().colideCom(enemy.getColisor())){
                            xwing.vidas-=1;
                            enemy.vidas-=1;
                            if(enemy.vidas == 0 && xwing.vidas != 1){
                                inimigosMortos.add(enemy);
                            }
                        }
                   
                }
                
                
                //Update Tiros
                
                ArrayList<enemyShoot> tirosInimigosParaRemover = new ArrayList<enemyShoot>();
                for(enemyShoot tiro : tirosInimigos ){
                    tiro.update(delta);
                    if(tiro.remove){
                        tirosInimigosParaRemover.add(tiro);
                    }
                }
                
                for(enemyShoot tiro : tirosInimigos ){
                    tiro.render(game.batch);
                }
                
                
                
                //update Xwing
                xwing.update(delta);
                
                //XWing Render
                xwing.render(game.batch);
                
                //Após Todos os Updates, Verificando Colisões
                
                for(Tiro disparo : tiros){
                    for(NaveInimiga enemy : inimigos){
                        if(disparo.getColisor().colideCom(enemy.getColisor()) == true && enemy.vidas == 1){
                            inimigosMortos.add(enemy);
                            tirosRemovidos.add(disparo);
                        }
                        else if(disparo.getColisor().colideCom(enemy.getColisor())== true && enemy.vidas > 1){
                            enemy.vidas -= 1;
                            tirosRemovidos.add(disparo);
                        }
                    }
                }
                for(enemyShoot edisparos: tirosInimigos){
                    if(edisparos.getColisor().colideCom(xwing.getColisor())){
                           tirosInimigosParaRemover.add(edisparos);
                           xwing.HealthBarUpdate();
                           System.out.println(xwing.vidas);
                       }  
                    }
                tirosInimigos.removeAll(tirosInimigosParaRemover);
                inimigos.removeAll(inimigosMortos); 
                tiros.removeAll(tirosRemovidos);
                
                xwing.HealthBar.render(game.batch);
                
                if(xwing.vidas == 0){
                    game.setScreen(new GameOver(game));
                }
                                
                game.batch.end();// finaliza a sequencia de desenho
	}
	public void resize () {
	}@Override
	public void pause () {
	}@Override
	public void resume () {
	}
	@Override
	public void dispose () {
		game.batch.dispose(); // libera o objeto que desenha em lote na placa de video
		img.dispose(); // libera a textura
	}

        @Override
        public void resize(int i, int i1) {}

        @Override
        public void hide() {}
}