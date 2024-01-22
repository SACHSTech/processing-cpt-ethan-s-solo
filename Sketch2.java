
import processing.core.PApplet;

public class Sketch2 extends PApplet {
	boolean boolUpPressed;
  boolean boolLeftPressed;
  boolean boolDownPressed;
  boolean boolRightPressed;
  boolean boolXPressed;
	float fltPlayerX;
  float fltPlayerY;
  float fltMouseX;
  float fltMouseY;
  float fltPlayerSpeed;
  int intProjectileMax;
  float fltProjectileX[];
  float fltProjectileY[];
  boolean boolProjectileInMotion;
  boolean boolProjectileHide[];
  float fltProjectileSpeed;
  int intCurrentState;
  String strLeadingEdge;
  
  /**
   * Called once at the beginning of execution, put your size call in this method
   */
  public void settings() {
	// Size call
    size(1280, 720);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    background(210, 255, 173);
    boolUpPressed = false;
    boolLeftPressed = false;
    boolDownPressed = false;
    boolRightPressed = false;
    boolXPressed = false;
    fltPlayerX = 640;
    fltPlayerY = 360;
    fltPlayerSpeed = 2;
    intProjectileMax = 30;
    fltProjectileX = new float[intProjectileMax];
    fltProjectileY = new float[intProjectileMax];
    boolProjectileInMotion = false;
    boolProjectileHide = new boolean[intProjectileMax];
    fltProjectileSpeed = 2;
    intCurrentState = 0;
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    if (intCurrentState == 0) {
      startMenu();
    } else if (intCurrentState == 1) {
      gameplay();
    } else if (intCurrentState == 2) {
      gameplay();
    } else if (intCurrentState == 3) {
      gameOver();
    } else if (intCurrentState == 4) {
      optionsMenu();
    }
  }

  public void startMenu() {
    background(0, 0, 0);
    textAlign(CENTER);
    textSize(100);
    text("TITLE OF MY GAME", 640, 360);
    textSize(25);
    text("PRESS START TO BEGIN", 640, 400);

    if (mousePressed && mouseX > 501 && mouseX < 778 && mouseY < 402 && mouseY > 381) {
      intCurrentState = 1;
    }
  }

  public void cinematicOne() {
    background(0, 0, 0);
    textAlign(CENTER);
    text("storyline intro", 640, 360);
    background(0, 0, 0);
  }

  public void gameplay() {
    background(210, 255, 173);
    
    // Options menu open button
    stroke(255, 255, 255);
    strokeWeight(3);
    line(1240, 20, 1260, 20);
    line(1240, 27, 1260, 27);
    line(1240, 34, 1260, 34);

    // Player
	  ellipse(fltPlayerX, fltPlayerY, 100, 100);

    // Vertical and horizontal movement
    if (boolUpPressed) {
      fltPlayerY -= fltPlayerSpeed;
      strLeadingEdge = "UP";
    } else if (boolLeftPressed) {
      fltPlayerX -= fltPlayerSpeed;
      strLeadingEdge = "LEFT";
    } else if (boolDownPressed) {
      fltPlayerY += fltPlayerSpeed;
      strLeadingEdge = "DOWN";
    } else if (boolRightPressed) {
      fltPlayerX += fltPlayerSpeed;
      strLeadingEdge = "RIGHT";
    }

    // Diagonal movement
    if (boolUpPressed && boolLeftPressed) {
      fltPlayerY -= fltPlayerSpeed;
      fltPlayerX -= fltPlayerSpeed;
      strLeadingEdge = "UP-LEFT";
    } else if (boolUpPressed && boolRightPressed) {
      fltPlayerY -= fltPlayerSpeed;
      fltPlayerX += fltPlayerSpeed;
      strLeadingEdge = "UP-RIGHT";
    } else if (boolDownPressed && boolLeftPressed) {
      fltPlayerY += fltPlayerSpeed;
      fltPlayerX -= fltPlayerSpeed;
      strLeadingEdge = "DOWN-LEFT";
    } else if (boolDownPressed && boolRightPressed) {
      fltPlayerY += fltPlayerSpeed;
      fltPlayerX += fltPlayerSpeed;
      strLeadingEdge = "DOWN-RIGHT";
    }

    // Shoot projectile in direction faced
    if (strLeadingEdge == "UP" && boolXPressed) {
      for (int n = 0; n < intProjectileMax; n++) {
      fltProjectileX[n] = fltPlayerX;
      fltProjectileY[n] = fltPlayerY;
      boolProjectileHide[n] = false;
    }
    boolProjectileInMotion = true;
  }
  for (int i = 0; i < intProjectileMax; i++) {
    if (!boolProjectileHide[i]) {
      fill(255, 255, 255);
      ellipse(fltProjectileX[i], fltProjectileY[i], 40, 40);
      fltProjectileY[i] -= fltProjectileSpeed;
      
      if (fltProjectileY[i] > height) {
        boolProjectileHide[i] = true;
        boolProjectileInMotion = false;
      }
    }
  }/*
    } else if (strLeadingEdge == "LEFT" && boolXPressed) {
      
    } else if (strLeadingEdge == "DOWN" && boolXPressed) {
      
    } else if (strLeadingEdge == "RIGHT" && boolXPressed) {
      
    } else if (strLeadingEdge == "UP-LEFT" && boolXPressed) {
      
    } else if (strLeadingEdge == "UP-RIGHT" && boolXPressed) {
      
    } else if (strLeadingEdge == "DOWN-LEFT" && boolXPressed) {
      
    } else if (strLeadingEdge == "DOWN-RIGHT" && boolXPressed) {
      
    }*/

    // Melee attack in direction faced
    // code here

    // Special move -- have a bar that charges up by hitting the enemies
    // if collision with enemy bar and bar is not full += 1, else if c clicked bar = 0 and special move used

    // Player health bar
    // if enemy attack colllides with player and player health is greater than 0 then health bar -= 1, else if player health = 0
    // die

    // Switch to options menu
    if (mousePressed && mouseX > 1239 && mouseX < 1261 && mouseY > 19 && mouseY < 35) {
      intCurrentState = 3;
    }
  }

  public void gameOver() {
    background(255, 255, 255);
  }

  public void optionsMenu() {
    background(39, 40, 48);
    textAlign(CENTER);
    textSize(25);
    text("SETTINGS", 640, 30);
    textSize(20);
    // any settings here
    // have a slider below, idk how it will work but yeah

    // Options menu close button
    stroke(255, 255, 255);
    strokeWeight(3);
    line(1240, 20, 1260, 20);
    line(1240, 27, 1260, 27);
    line(1240, 34, 1260, 34);

    // Switch to gameplay, fix this becuz it glitches due to being mouse pressed
    /*
    fix the glitch that happens due to mouse pressed
    if (mousePressed && mouseX > 1239 && mouseX < 1261 && mouseY > 19 && mouseY < 35) {
      intCurrentState = 1;
    }
    */
  }

  /**
   * Checks if any keys are pressed
   * Changes the boolean flag to true based on the keyCode pressed
   */
  public void keyPressed() {
    if (keyCode == UP) {
      boolUpPressed = true;
    } else if (keyCode == LEFT) {
      boolLeftPressed = true;
    } else if (keyCode == DOWN) {
      boolDownPressed = true;
    } else if (keyCode == RIGHT) {
      boolRightPressed = true;
    } else if (keyCode == 88) {
      boolXPressed = true;
    }
  }

  /**
   * Checks if any keys are released
   * Changes the boolean flag to false based on the keyCode pressed
   */
  public void keyReleased() {
    if (keyCode == UP) {
      boolUpPressed = false;
    } else if (keyCode == LEFT) {
      boolLeftPressed = false;
    } else if (keyCode == DOWN) {
      boolDownPressed = false;
    } else if (keyCode == RIGHT) {
      boolRightPressed = false;
    } else if (keyCode == 88) {
      boolXPressed = false;
    }
  }
}