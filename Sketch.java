import processing.core.PApplet;

public class Sketch extends PApplet {
	boolean boolUpPressed;
  boolean boolLeftPressed;
  boolean boolDownPressed;
  boolean boolRightPressed;
  boolean mouseClicked;
	float fltPlayerX;
  float fltPlayerY;
  float fltMouseX;
  float fltMouseY;
  float fltPlayerSpeed;
  int intCurrentState;

  /**
   * Called once at the beginning of execution, put your size all in this method
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
    fltPlayerX = 640;
    fltPlayerY = 360;
    fltPlayerSpeed = 1;
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
      gameOver();
    } else if (intCurrentState == 3) {
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

  public void gameplay() {
    background(210, 255, 173);
	  ellipse(fltPlayerX, fltPlayerY, 100, 100);

    // Vertical and horizontal movement
    if (boolUpPressed) {
      fltPlayerY -= fltPlayerSpeed;
    } else if (boolLeftPressed) {
      fltPlayerX -= fltPlayerSpeed;
    } else if (boolDownPressed) {
      fltPlayerY += fltPlayerSpeed;
    } else if (boolRightPressed) {
      fltPlayerX += fltPlayerSpeed;
    }

    // Diagonal movement
    if (boolUpPressed && boolLeftPressed) {
      fltPlayerY -= fltPlayerSpeed;
      fltPlayerX -= fltPlayerSpeed;
    } else if (boolUpPressed && boolRightPressed) {
      fltPlayerY -= fltPlayerSpeed;
      fltPlayerX += fltPlayerSpeed;
    } else if (boolDownPressed && boolLeftPressed) {
      fltPlayerY += fltPlayerSpeed;
      fltPlayerX -= fltPlayerSpeed;
    } else if (boolDownPressed && boolRightPressed) {
      fltPlayerY += fltPlayerSpeed;
      fltPlayerX += fltPlayerSpeed;
    }
  }

  public void gameOver() {
    background(255, 255, 255);
  }

  public void optionsMenu() {
    background(255, 0, 0);
  }

  /**
   * Checks if any arrow keys are pressed
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
    }
  }

  /**
   * Checks if any arrow keys are released
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
    }
  }
}