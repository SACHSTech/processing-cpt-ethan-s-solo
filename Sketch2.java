import processing.core.PApplet;
import java.util.ArrayList;

public class Sketch2 extends PApplet {
	int intPlayerX;
  int intPlayerY;
  int[] intEnemyX;
  int[] intEnemyY;
  int intEnemyMaxCount = 10;
	
  ArrayList<Float> fltProjectileX = new ArrayList<>();
  ArrayList<Float> fltProjectileY = new ArrayList<>();

  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// put your size call here
    size(720, 720);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    background(210, 255, 173);
    intPlayerX = width / 2;
    intPlayerY = height - 45;

    intEnemyX = new int[intEnemyMaxCount];
    intEnemyY = new int[intEnemyMaxCount];

    for (int i = 0; i < intEnemyMaxCount; i++) {
      intEnemyX[i] = (int) random(width);
      intEnemyY[i] = (int) random(-height, -20);
    }
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
	  background(0, 0, 255);

    fill(255, 0, 0);
    ellipse(intPlayerX, intPlayerY, 20, 20);

    fill(0, 255, 0);
    for (int i = 0; i < intEnemyMaxCount; i++) {
      ellipse(intEnemyX[i], intEnemyY[i], 20, 20);
      intEnemyY[i] += 2;
      
      if (intEnemyY[i] > height) {
        intEnemyX[i] = (int) random(width);
        intEnemyY[i] = (int) random(-height, -20);
      }
    }
    
    for (int i = 0; i < intEnemyMaxCount; i++) {
      if (dist(intPlayerX, intPlayerY, intEnemyX[i], intEnemyY[i]) < 20 / 2 + 20 / 2) {
        background(0, 0, 255);
        fill(0);
        textAlign(CENTER);
        textSize(30);
        text("Game Over", width / 2, height / 2);
        noLoop();
      }
    }
  }
  
  public void keyPressed() {
    if (keyCode == LEFT && intPlayerX > 0) {
      intPlayerX -= 10;
    } else if (keyCode == RIGHT && intPlayerX < width) {
      intPlayerX += 10;
    }
  }
}
