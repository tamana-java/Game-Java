package com.shoolini.gaming.sprites;

import java.awt.Graphics;

import javax.swing.ImageIcon;

public class Enemy extends sprite {

	private int speed;


	public Enemy(int x, int speed) {
		y = 20;
		this.x = x;
		this.speed = speed;
		w = 200;
		h = 200;
		
		image = new ImageIcon(Enemy.class.getResource("spider.gif"));
	}

	
	public void move() {
		if(y>700)
		{ y=0;}
		y = y + speed ;
	}
}