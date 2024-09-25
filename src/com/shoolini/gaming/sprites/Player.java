package com.shoolini.gaming.sprites;

import java.awt.Graphics;
import javax.swing.ImageIcon;

public class Player extends sprite {
	

	public Player() { // a constructor for the player to be access easly
		w = 200;
		h =200;
		x = 20;
		y = 400;
		image = new ImageIcon(Player.class.getResource("Player.gif"));
	}

public boolean outofscreen() {
	return x>1400;
}


	

}
