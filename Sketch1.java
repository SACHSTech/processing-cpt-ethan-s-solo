import processing.core.PApplet;
import java.util.Random;

public class Sketch1 extends PApplet {
	// Variables
  int intCurrentState;
  int intGameType;
  String strLeadingEdge;
  boolean boolEPressed;
  boolean boolRPressed;
  boolean boolUpPressed;
  boolean boolLeftPressed;
  boolean boolRightPressed;
  boolean boolDownPressed;
  boolean boolTabPressed;
  float fltPlayerX;
  float fltPlayerY;
  float fltPlayerSpeed;
  boolean boolPacGhostMode;
  float fltBlockSize;
	
  
  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
    size(720, 720);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    background(210, 255, 173);

    // Variable Declaration
    intCurrentState = 0;
    fltPlayerX = 360;
    fltPlayerY = 360;
    fltPlayerSpeed = 2;
    fltBlockSize = 40;
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    if (intCurrentState == 0) {
      startMenu();
    } else if (intCurrentState == 1) {
      easyGameplay();
    } else if (intCurrentState == 2) {
      hardGameplay();
    } else if (intCurrentState == 3) {
      settingsMenu();
    } else if (intCurrentState == 4) {

    } else if (intCurrentState == 5) {

    }
  }

  public void startMenu() {
    background(0, 0, 0);
    textAlign(CENTER);
    textSize(100);
    text("PACMAN", 360, 340);
    textSize(25);
    text("PRESS E TO BEGIN EASY MODE", 360, 380);
    text("PRESS R TO BEGIN HARD MODE", 360, 410);

    if (boolEPressed) {
      intCurrentState = 1;
      intGameType = 1;
    } else if (boolRPressed) {
      intCurrentState = 2;
      intGameType = 2;
    }
  }

  public void easyGameplay() {
    background(0, 0, 0);
    blockLayout();
    fill(20, 250, 20);
    ellipse(fltPlayerX, fltPlayerY, 20, 20);

    controls();
    pacGhostMode();

    if (boolTabPressed) {
      intCurrentState = 3;
    }
  }

  public void hardGameplay() {
    background(30, 0, 0);
    blockLayout();
    fill(20, 250, 20);
    ellipse(fltPlayerX, fltPlayerY, 20, 20);

    controls();

    if (boolTabPressed) {
      intCurrentState = 3;
    }
  }

  public void settingsMenu() {
    background(0, 0, 0);
    ellipse(690, 30, 20, 20);
    
    if (boolTabPressed && intGameType == 1) {
      intCurrentState = 1;
    } else if (boolTabPressed && intGameType == 2) {
      intCurrentState = 2;
    }
  }

  public void controls() {
    if (strLeadingEdge == "UP") {
      fltPlayerY -= fltPlayerSpeed;
    } else if (strLeadingEdge == "LEFT") {
      fltPlayerX -= fltPlayerSpeed;
    } else if (strLeadingEdge == "RIGHT") {
      fltPlayerX += fltPlayerSpeed;
    } else if (strLeadingEdge == "DOWN") {
      fltPlayerY += fltPlayerSpeed;
    }
  }

  public void blockLayout() {
    Random random = new Random();
    for (float x = 0; x < width; x += fltBlockSize) {
      for (float y = 0; y < height; y += fltBlockSize) {
        if (random.nextFloat() < 0.2) {
          fill(0, 40, 255);
          noStroke();
          rect(x, y, fltBlockSize, fltBlockSize);
        }
      }
    }
    fill(255, 0, 0);
    rect(310, 325, 100, 70);
    noLoop(); // this doesnt work cuz it stops eveyrthing in draw from looping (player cant move)
  }

  public void pacGhostMode() {
    if (boolPacGhostMode) {
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
  }

  public void keyPressed() {
    if (keyCode == 69) {
      boolEPressed = true;
    } else if (keyCode == 82) {
      boolRPressed = true;
    } else if (keyCode == UP) {
      boolUpPressed = true;
      strLeadingEdge = "UP";
    } else if (keyCode == LEFT) {
      boolLeftPressed = true;
      strLeadingEdge = "LEFT";
    } else if (keyCode == RIGHT) {
      boolRightPressed = true;
      strLeadingEdge = "RIGHT";
    } else if (keyCode == DOWN) {
      boolDownPressed = true;
      strLeadingEdge = "DOWN";
    } else if (keyCode == 9) {
      boolTabPressed = true;
    }
  }

  public void keyReleased() {
    if (keyCode == 69) {
      boolEPressed = false;
    } else if (keyCode == 82) {
      boolRPressed = false;
    } else if (keyCode == UP) {
      boolUpPressed = false;
    } else if (keyCode == LEFT) {
      boolLeftPressed = false;
    } else if (keyCode == RIGHT) {
      boolRightPressed = false;
    } else if (keyCode == DOWN) {
      boolDownPressed = false;
    } else if (keyCode == 9) {
      boolTabPressed = false;
    }
  }
}
