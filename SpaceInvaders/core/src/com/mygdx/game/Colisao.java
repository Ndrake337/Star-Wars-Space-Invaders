/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mygdx.game;

/**
 *
 * @author mathe
 */
public class Colisao {
    float x,y;
    int widith, height;

    public Colisao(float x, float y, int widith, int height) {
        this.x = x;
        this.y = y;
        this.widith = widith;
        this.height = height;
        
    }
    public void Move(float x, float y){
        this.x = x;
        this.y = y;
    }
    
    public boolean colideCom( Colisao obj){
        return x < obj.x + obj.widith && y < obj.y + obj.height && x + widith > obj.x && y + height > obj.y;
    }
    
}
