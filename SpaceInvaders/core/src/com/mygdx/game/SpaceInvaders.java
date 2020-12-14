package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class SpaceInvaders extends Game {

        public SpriteBatch batch;
	
        @Override
	public void create () {
            batch = new SpriteBatch();
            this.setScreen(new Introscreen(this));
	}
        
	@Override
	public void render () {
            super.render();
	}
        
}
