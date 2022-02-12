package com.BB;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame j = new JFrame();
		Play g = new Play();
		j.setBounds(5,5,650,650);
		j.setTitle("BrickBreaker");
		j.setResizable(false);
		j.setVisible(true);
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.add(g);
	}
}