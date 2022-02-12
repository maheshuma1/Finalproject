package com.BB;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Play extends JPanel implements KeyListener,ActionListener {

	private boolean Go = false;
	private int points = 0;
	private int totalBricks = 10;
	private Timer t;
	private int delay = 8;
	private int player = 310;
	private int ballxp = 250;
	private int ballyp = 550;
	private int balldirx = -1;
	private int balldiry = -2;
	private Map m;
	
	public Play() {
		m = new Map(6, 7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		t = new Timer(delay, this);
		t.start();
	}
	
	public void paint(Graphics g) {
		
		g.setColor(Color.BLACK);
		g.fillRect(1, 1, 650, 650);
		
		m.draw((Graphics2D)g);
		
		g.setColor(Color.green);
		g.setFont(new Font("serif",Font.ITALIC,20));
		g.drawString("POINTS : "+points, 400, 20);
		
		
		
		
		if(ballyp>600) {
			Go = false;
			balldirx = 0;
			balldiry = 0;
			g.setColor(Color.red);
			g.setFont(new Font("serif",Font.BOLD,35));
			g.drawString("GAME OVER!..., Points : "+points, 150, 250);
			
			g.setFont(new Font("serif",Font.BOLD,30));
			g.drawString("PRESS ENTER TO START GAME", 120, 350);
		}
		
		g.setColor(Color.PINK);
		g.fillRect(0, 0, 3, 650);
		g.fillRect(0, 0, 650, 3);
		g.fillRect(650, 0, 3, 600);
		
		g.setColor(Color.GREEN);
		g.fillRect(player, 600, 100, 8);
		
		g.setColor(Color.RED);
		g.fillOval(ballxp, ballyp, 15, 15);
		
		g.dispose();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		t.start();
		if(Go) {
			if(new Rectangle(ballxp,ballyp,15,15).intersects(new Rectangle(player,600,100,8))) {
				balldiry = -balldiry;
			}	
			
	loop:	for(int i=0; i<m.m.length;i++) {
				for (int j = 0; j < m.m[0].length; j++) {
					if(m.m[i][j]>0) {
						int bx = j*m.bW+80;
						int by = i*m.bH+50;
						int bw = m.bW;
						int bh = m.bH;
						Rectangle r = new Rectangle(bx,by,bw,bh);
						Rectangle ballr = new Rectangle(ballxp,ballyp,20,20);
						Rectangle brickr = r;
						
						if(ballr.intersects(brickr)) {
							m.setBricksCount(0, i, j);
							totalBricks--;
							points+= 1;
							if(ballxp+18 <= brickr.x || ballxp+1 >= brickr.x + brickr.width) {
								
							}else {
								balldiry = -balldiry;
							}
							break loop;
						}
						
					}
				}
			}
			
			ballxp+=balldirx;
			ballyp+=balldiry;
			if(ballxp<0) {
				balldirx = -balldirx;
			}
			if(ballyp<0) {
				balldiry = -balldiry;
			}
			if(ballxp>600) {
				balldirx = -balldirx;
			}
		}
		repaint();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode()==KeyEvent.VK_RIGHT) {
			if(player>=600) {
				player=600;
			}else {
				moveRight();
			}
		}
		
		if(e.getKeyCode()==KeyEvent.VK_LEFT) {
			if(player<10) {
				player=10;
			}else {
				moveLeft();
			}
		}         
		
		if(e.getKeyCode()==KeyEvent.VK_ENTER) {
			if(!Go) {
				Go=true;
				points = 0;
				totalBricks = 42;
				delay = 8;
				player = 310;
				ballxp = 250;
				ballyp = 550;
				balldirx = -1;
				balldiry = -2;
				m = new Map(6, 7);
				repaint();
			}
		}   
	}

	public void moveRight() {
		Go = true;
		player+= 20;
	}
	
	public void moveLeft() {
		Go = true;
		player-= 20;
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
