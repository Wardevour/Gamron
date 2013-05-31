package gui;

import java.awt.Font;
import java.io.InputStream;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

public class EscMenu{

	static Font awtFont = new Font("Times New Roman", Font.BOLD, 30);
	static TrueTypeFont menuFont = new TrueTypeFont(awtFont, true);
	
	public static boolean isOpen = false;
	
        public static void drawMenu(){
		GL11.glColor3f(1.0f,0.0f,0.0f);
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2d( 0.0, 0.0);
			GL11.glVertex2d( 1024.0, 0.0);
			GL11.glVertex2d( 1024.0, 768.0);
			GL11.glVertex2d( 0.0, 768.0);
		GL11.glEnd();
		
		menuFont.drawString(452,324, "P A U S E", Color.black);
	}
}