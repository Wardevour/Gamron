package com.gamron.dgenerate;

import java.util.Random;

public class RpgDice
{
	int sides;
	
	public RpgDice (int s){
		sides = s;
	}
	
	public int roll()
	{
		Random value = new Random();
		int v = value.nextInt(sides);
		return v;
	}
}
