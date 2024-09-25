package com.shoolini.gaming;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.shoolini.gaming.sprites.Enemy;
import com.shoolini.gaming.sprites.Player;

public class Board extends JPanel {
	Timer timer;
	BufferedImage backgroundImage;
	Player player;
	Enemy enemies[] = new Enemy[3];

	public Board() {
		setSize(1400, 720);
		loadBackgroundImage();
		player = new Player();
		loadEnemies();
		gameLoop();
		bindEvents();
		setFocusable(true);
	}

	private void gameOver(Graphics pen) {
		if (player.outofscreen()) {
			pen.setFont(new Font("times", Font.BOLD, 30));
			pen.setColor(Color.RED);
			pen.drawString("GAME WIN..", 1400 / 2, 720 / 2);
			timer.stop();
			return;
		}
		for (Enemy enemy : enemies) {
			if (isCollide(enemy)) {
				pen.setFont(new Font("times", Font.BOLD, 30));
				pen.setColor(Color.RED);
				pen.drawString("GAME OVER..", 1400 / 2, 720 / 2);
				timer.stop();
			}
		}
	}

	private boolean isCollide(Enemy enemy) {
		int xDistance = Math.abs(player.x - enemy.x);
		int yDistance = Math.abs(player.y - enemy.y);
		int maxh = Math.max(player.h, enemy.h);
		int maxw = Math.max(player.w, enemy.w);
		return xDistance <= maxw - 82 && yDistance <= maxh - 82;
	}

	private void bindEvents() {
		addKeyListener(new KeyListener() {

			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
					player.speed = 10;
				} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
					player.speed = -5;
				}

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				player.speed = 0;
			}
		});

	}

	private void loadEnemies() {
		int x = 400; // just assuming and idea .
		int gap = 400;
		int speed = 5;
		for (int i = 0; i < enemies.length; i++) {
			enemies[i] = new Enemy(x, speed);
			x = x + gap;
			speed = speed + 5;
		}
	}

	private void gameLoop() {
		timer = new Timer(50, (e) -> repaint());
		timer.start();
	}

	private void loadBackgroundImage() {
		try {
			backgroundImage = ImageIO.read(Board.class.getResource("game.back.jpg"));
		} catch (IOException e) {
			System.out.println("Background Image not found...");
			System.exit(1);
			e.printStackTrace();
		}
	}

	private void printEnemies(Graphics pen) {
		for (Enemy enemy : enemies) {
			enemy.draw(pen);
			enemy.move();
		}
	}

	public void paintComponent(Graphics pen) {
		super.paintComponent(pen);
		pen.drawImage(backgroundImage, 0, 0, 1400, 720, null);
		player.draw(pen);
		player.move();
		printEnemies(pen);
		gameOver(pen);
	}
}