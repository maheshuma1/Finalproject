package com.BB;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Iterator;

public class Map {
	public int m[][];
	public int bW;
	public int bH;
	
	public Map(int r, int c) {
		m = new int[r][c];
		for(int i=0; i<m.length;i++) {
			for (int j = 0; j < m[0].length; j++) {
				m[i][j] =1;
			}
		}
		bW = 540/c;
		bH = 150/r;
	}
	
	public void draw(Graphics2D gph) {
		for(int i=0; i<m.length;i++) {
			for (int j = 0; j < m[0].length; j++) {
				if(m[i][j] > 0) {
					gph.setColor(Color.ORANGE);
					gph.fillRect(j*bW+70, i*bH+40, bW,bH);
					gph.setStroke(new BasicStroke(3));
					gph.setColor(Color.black);
					gph.drawRect(j*bW+70, i*bH+40, bW,bH);
				}
			}
		}
	}
	
	public void setBricksCount(int count, int r, int c) {
		m[r][c] = count;
	}
	
}
