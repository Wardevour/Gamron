package com.gamron;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.Sys;
import org.lwjgl.opengl.GL11;
import org.lwjgl.input.Keyboard;
import gui.EscMenu;

// http://www.lwjgl.org/wiki/index.php?title=Main_Page

public class MainClass {

	// time at last frame
	long lastFrame;
 
	/** frames per second */
	int fps;
	/** last fps time */
	long lastFPS;
	
	/** is VSync Enabled */
	boolean vsync;
	
	int width = 1024;
	
	int height = 768;

	public void start() {
		try {
			Display.setDisplayMode(new DisplayMode(width,height));
			Display.setFullscreen(true);
			Display.create();
			Keyboard.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		// init OpenGL here
		GL11.glViewport(0,0,width,height);
		
 		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, width, height, 0, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);

		//GL11.glEnable(GL11.GL_TEXTURE_2D);
		//GL11.glShadeModel(GL11.GL_SMOOTH);
		//GL11.glDisable(GL11.GL_DEPTH_TEST);
		//GL11.glDisable(GL11.GL_LIGHTING);
		//GL11.glClearDepth(1);
		GL11.glEnable(GL11.GL_BLEND);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		
		lastFPS = getTime();

		while (!Display.isCloseRequested()) {
			// render OpenGL here
			
			//Display.sync(60);

			pollInput();
			
			if (!EscMenu.isOpen){
				GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
			} else {
				EscMenu.drawMenu();
			}
			
			GL11.glFlush();
			Display.update();

			updateFPS();
		}
		
		Display.destroy();
	}

 	public void pollInput(){
		while (Keyboard.next()) {
			if (Keyboard.getEventKeyState()) {
				if (Keyboard.getEventKey() == Keyboard.KEY_F) {
					System.out.println("F Key Pressed");
				}
				
				if (Keyboard.getEventKey() == Keyboard.KEY_V) {
					System.out.println("V Key Pressed");
				}
				
				if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
					System.out.println("ESC Key Pressed");
				}
			} 
			
			else {
				if (Keyboard.getEventKey() == Keyboard.KEY_F) {
					System.out.println("F Key Released");
					setDisplayMode(1024, 768, !Display.isFullscreen());
				}
				
				if (Keyboard.getEventKey() == Keyboard.KEY_V) {
					System.out.println("V Key Released");
					vsync = !vsync;
					Display.setVSyncEnabled(vsync);
				}
				
				if (Keyboard.getEventKey() == Keyboard.KEY_ESCAPE) {
					System.out.println("ESC Key Released");
					if (!EscMenu.isOpen){
						EscMenu.isOpen = true;
						Display.setVSyncEnabled(false);
						
					} else {EscMenu.isOpen = false;}
				}
			}
		}
 	}
 
	/**
	* Set the display mode to be used 
	* 
	* @param width The width of the display required
	* @param height The height of the display required
	* @param fullscreen True if we want fullscreen mode
	*/
	
	public void setDisplayMode(int width, int height, boolean fullscreen) {

		// return if requested DisplayMode is already set
		if ((Display.getDisplayMode().getWidth() == width) && 
		(Display.getDisplayMode().getHeight() == height) && 
		(Display.isFullscreen() == fullscreen)) {
			return;
		}

		try {
			DisplayMode targetDisplayMode = null;
		
			if (fullscreen) {
				DisplayMode[] modes = Display.getAvailableDisplayModes();
				int freq = 0;
				
				for (int i=0;i<modes.length;i++) {
					DisplayMode current = modes[i];
					
					if ((current.getWidth() == width) && (current.getHeight() == height)) {
						if ((targetDisplayMode == null) || (current.getFrequency() >= freq)) {
							if ((targetDisplayMode == null) || (current.getBitsPerPixel() > targetDisplayMode.getBitsPerPixel())) {
								targetDisplayMode = current;
								freq = targetDisplayMode.getFrequency();
							}
						}

						// if we've found a match for bpp and frequence against the 
						// original display mode then it's probably best to go for this one
						// since it's most likely compatible with the monitor
						if ((current.getBitsPerPixel() == Display.getDesktopDisplayMode().getBitsPerPixel()) &&
						(current.getFrequency() == Display.getDesktopDisplayMode().getFrequency())) {
							targetDisplayMode = current;
							break;
						}
					}
				}
				
			} else { targetDisplayMode = new DisplayMode(width,height);}

		if (targetDisplayMode == null) {
			System.out.println("Failed to find value mode: "+width+"x"+height+" fs="+fullscreen);
			return;
		}

		Display.setDisplayMode(targetDisplayMode);
		Display.setFullscreen(fullscreen);
			
		} catch (LWJGLException e) { System.out.println("Unable to setup mode "+width+"x"+height+" fullscreen="+fullscreen + e);}
	}

	// Get the accurate system time 
	// @return The system time in milliseconds

	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	//Calculate the FPS and set it in the title bar
	
	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			Display.setTitle("FPS: " + fps);
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

	public static void main( String[] args)
	{
		System.out.println("LWJGL Ver. : " + Sys.getVersion());
		MainClass mainClass = new MainClass();
		mainClass.start();
	}

}