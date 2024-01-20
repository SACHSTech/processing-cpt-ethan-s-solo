// Imports
import processing.core.PApplet;
import processing.core.PImage;
import java.util.ArrayList;

public class Sketch2 extends PApplet {
  // Variable Declaration
  int intGameState;
  int intEnemiesDestroyed;
	int intPlayerX;
  int intPlayerY;
  int intPlayerSpeed;
  int[] intEnemyX;
  int[] intEnemyY;
  int intEnemyMaxCount;
  boolean boolSpaceBar;
  boolean boolEnterPressed;
  boolean boolLeftPressed;
  boolean boolRightPressed;
  boolean boolTabPressed;
  boolean boolSPressed;
  boolean boolDPressed;
  boolean boolHPressed;
  boolean boolEPressed;
  boolean boolEasyGameplay;
  boolean boolHardGameplay;

  // Image Declaration
  PImage imgAstralWarStart;
  PImage imgAstralWarGameOver;
  PImage imgAstralWarDifficulty;
  PImage imgAstralWarPause;
  PImage imgAstralWarGameplayBG;
  PImage imgPlayerShip;
  PImage imgEnemyShip;
  PImage imgProjectile;
  
  // Array Declaration
  ArrayList<Float> fltProjectileX = new ArrayList<>();
  ArrayList<Float> fltProjectileY = new ArrayList<>();

  /**
   * Called once at the beginning of execution, put your size all in this method
   */
  public void settings() {
	// Size Call
    size(720, 720);
  }

  /** 
   * Called once at the beginning of execution.  Add initial set up
   * values here i.e background, stroke, fill etc.
   */
  public void setup() {
    background(210, 255, 173);

    // Variable Initialization
    intGameState = 0;
    intPlayerX = width / 2;
    intPlayerY = height - 45;
    intPlayerSpeed = 4;
    intEnemyMaxCount = 10;
    boolSpaceBar = false;
    boolEnterPressed = false;
    boolLeftPressed = false;
    boolRightPressed = false;
    boolTabPressed = false;
    boolSPressed = false;
    boolDPressed = false;
    boolEPressed = false;
    boolHPressed = false;
    boolEasyGameplay = false;
    boolHardGameplay = false;

    // Array Initialization
    intEnemyX = new int[intEnemyMaxCount];
    intEnemyY = new int[intEnemyMaxCount];

    // Image Initialization
    imgAstralWarStart = loadImage("Astral War Start.jpg");
    imgAstralWarGameOver = loadImage("Astral War Game Over.jpg");
    imgPlayerShip = loadImage("blue spaceship sprite transparent.png");
    imgAstralWarGameplayBG = loadImage("Astral War Gameplay Background (nm).png");
    imgEnemyShip = loadImage("red spaceship sprite transparent.png");
    imgProjectile = loadImage("one fireball sprite transparent.png");
    imgAstralWarDifficulty = loadImage("Astral War Difficulty Selection.jpg");
    imgAstralWarPause = loadImage("Astral War Pause Screen.jpg");

    // Image Resize
    imgPlayerShip.resize(imgPlayerShip.width / 7, imgPlayerShip.height / 7);
    imgEnemyShip.resize(imgEnemyShip.width / 9, imgEnemyShip.height / 9);
    imgProjectile.resize(imgProjectile.width / 9, imgProjectile.height / 9);

    // Set initial enemy positions
    for (int i = 0; i < intEnemyMaxCount; i++) {
      intEnemyX[i] = (int) random(width);
      intEnemyY[i] = (int) random(-height, -20);
    }
  }

  /**
   * Called repeatedly, anything drawn to the screen goes here
   */
  public void draw() {
    // Code that allows a switch between each section
    if (intGameState == 0) {
      startMenu();
    } else if (intGameState == 1) {
      gameplay();
    } else if (intGameState == 2) {
      paused();
    } else if (intGameState == 3) {
      gameOver();
    } else if (intGameState == 4) {
      hardGameplay();
    } else if (intGameState == 5) {
      chooseDifficulty();
    }
  }

  /**
   * Start menu is displayed and allows for continuation into difficulty selection screen
   */
  public void startMenu() {
    // Start Menu Background
    image(imgAstralWarStart, 0, 0);

    // Continue to gameplay if enter clicked
    if (boolEnterPressed) {
      intGameState = 5;
    }
  }

  /**
   * Difficulty selection screen is displayed and allows for continuation into gameplay
   */
  public void chooseDifficulty() {
    image(imgAstralWarDifficulty, 0, 0);
    if (boolHPressed) {
      intGameState = 4;
      boolHardGameplay = true;
    } else if (boolEPressed) {
      intGameState = 1;
      boolEasyGameplay = true;
    }
  }

  /**
   * Gameplay mechanics are held within
   */
  public void gameplay() {
    // Gameplay Background
    image(imgAstralWarGameplayBG, 0, 0);

    // Enemies Destroyed Score Displayed
    fill(255, 255, 255);
    text("Score: " + intEnemiesDestroyed, 640, 30);

    // Player Image
    image(imgPlayerShip, intPlayerX - 35, intPlayerY - 30);

    fill(0, 255, 0);
    
    // Movement
    if (boolLeftPressed  && intPlayerX > 0) {
      intPlayerX -= intPlayerSpeed;
    } else if (boolRightPressed && intPlayerX < width) {
      intPlayerX += intPlayerSpeed;
    }

    // Speed Cheat Code
    if (boolSPressed && intPlayerSpeed != 10) {
      intPlayerSpeed = 10;
    } else if (boolDPressed && intPlayerSpeed == 10) {
      intPlayerSpeed = 4;
    }

    // Make enemy fly downwards
    for (int i = 0; i < intEnemyMaxCount; i++) {
      image(imgEnemyShip, intEnemyX[i] - 27, intEnemyY[i] - 27);
      intEnemyY[i] += 2;
      
      if (intEnemyY[i] > height) {
        intEnemyX[i] = (int) random(width);
        intEnemyY[i] = (int) random(-height, -20);
      }
    }
    
    // Pause
    if (boolTabPressed) {
      intGameState = 2;
    }

    // Allows projectiles to be show
    shootProjectile();

    // Checks for collision with projectiles and player or player and enemy
    checkCollision(CUSTOM);
  }

  /**
   * A harder version of gameplay mechanics are held within
   */
  public void hardGameplay() {
    // Gameplay Background
    image(imgAstralWarGameplayBG, 0, 0);

    // Enemies Destroyed Score Displayed
    fill(255, 255, 255);
    text("Score: " + intEnemiesDestroyed, 640, 30);

    // Player Image
    image(imgPlayerShip, intPlayerX - 35, intPlayerY - 30);

    fill(0, 255, 0);
    
    // Movement
    if (boolLeftPressed  && intPlayerX > 0) {
      intPlayerX -= intPlayerSpeed / 2;
    } else if (boolRightPressed && intPlayerX < width) {
      intPlayerX += intPlayerSpeed / 2;
    }

    // Speed Cheat Code
    if (boolSPressed && intPlayerSpeed != 10) {
      intPlayerSpeed = 10;
    } else if (boolDPressed && intPlayerSpeed == 10) {
      intPlayerSpeed = 4;
    }

    // Make enemy fly downwards
    for (int i = 0; i < intEnemyMaxCount; i++) {
      image(imgEnemyShip, intEnemyX[i] - 27, intEnemyY[i] - 27);
      intEnemyY[i] += 3;
      
      if (intEnemyY[i] > height) {
        intEnemyX[i] = (int) random(width);
        intEnemyY[i] = (int) random(-height, -20);
      }
    }
    
    // Pause
    if (boolTabPressed) {
      intGameState = 2;
    }

    // Allows projectiles to be show
    shootProjectile();

    // Checks for collision with projectiles and player or player and enemy
    checkCollision(CUSTOM);
  }

  /**
   * Game over screen code
   */
  public void gameOver() {
    // Background for game over screen
    image(imgAstralWarGameOver, 0, 0);
    
    // Reset player position
    intPlayerX = width / 2;
    intPlayerY = height - 45;

    // Reset enemy positions
    for (int i = 0; i < intEnemyMaxCount; i++) {
      intEnemyX[i] = (int) random(width);
      intEnemyY[i] = (int) random(-height, -20);
    }

    // Reset any projectiles
    fltProjectileX.clear();
    fltProjectileY.clear();

    // Reset any other score counts
    intEnemiesDestroyed = 0;

    // If enter pressed go to difficulty selection
    if (boolEnterPressed) {
      intGameState = 5;
      boolHardGameplay = false;
      boolEasyGameplay = false;
    }
  }

  /**
   * 
   * Pause screen code
   */
  public void paused() {
    image(imgAstralWarPause, 0, 0);
    text("PAUSED", 360, 360);
    
    if (boolEnterPressed && boolEasyGameplay) {
      intGameState = 1;
    } else if (boolEnterPressed && boolHardGameplay) {
      intGameState = 4;
    }
  }

  /**
   * Contains the code for projectiles being shot
   */
  public void shootProjectile() {
    for (int i = 0; i < fltProjectileX.size(); i++) {
      moveProjectile(i);
      displayProjectile(i);
      checkCollision(i);
    }
  }

  /**
   * Contains the code for projectile movement
   * @param lst The index of the specific projectile
   */
  public void moveProjectile(int lst) {
    float newX = fltProjectileX.get(lst);
    float newY = fltProjectileY.get(lst) - 7;
    fltProjectileX.set(lst, newX);
    fltProjectileY.set(lst, newY);
  }

  /**
   * The code for displaying the projectile
   * @param lst The index of the specific projectile
   */
  public void displayProjectile(int lst) {
    image(imgProjectile, fltProjectileX.get(lst) - 15, fltProjectileY.get(lst) - 10);
  }

  /**
   * Checks for collision between player and enemy or enemy and projectile
   * @param lst The index of the specific projectile
   */
  public void checkCollision(int lst) {
    // Collision between player and enemy
    for (int i = 0; i < intEnemyMaxCount; i++) {
      if (dist(intPlayerX, intPlayerY, intEnemyX[i], intEnemyY[i]) < 20 / 2 + 20 / 2) {
        for (int j = i; j < intEnemyMaxCount - 1; j++) {
          intEnemyX[j] = intEnemyX[j + 1];
          intEnemyY[j] = intEnemyY[j + 1];
        }
        intEnemyX[intEnemyMaxCount - 1] = (int) random(width);
        intEnemyY[intEnemyMaxCount - 1] = (int) random(-height, -20);
        intGameState = 3;
      }
    }

    // Collision between enemy and projectile
  try {
    for (int i = 0; i < intEnemyMaxCount; i++) {
      float d = dist(fltProjectileX.get(lst), fltProjectileY.get(lst), intEnemyX[i], intEnemyY[i]);
      if (d < 10 / 2 + 20 / 2) {
        fltProjectileX.remove(lst);
        fltProjectileY.remove(lst);
        intEnemyX[i] = (int) random(width);
        intEnemyY[i] = (int) random(-height, -20);
        intEnemiesDestroyed += 1;
      }
    }} catch (Exception e){

    }
  }

  /**
   * Checks if various keys are pressed
   */
  public void keyPressed() {
    if (keyCode == LEFT) {
      boolLeftPressed = true;
    } else if (keyCode == RIGHT) {
      boolRightPressed = true;
    } else if (keyCode == 32) {
      boolSpaceBar = true;
      fltProjectileX.add((float) intPlayerX);
      fltProjectileY.add((float) intPlayerY);
    } else if (keyCode == 10) {
      boolEnterPressed = true;
    } else if (keyCode == 9) {
      boolTabPressed = true;
    } else if (keyCode == 'S') {
      boolSPressed = true;
    } else if (keyCode == 'D') {
      boolDPressed = true;
    } else if (keyCode == 'E') {
      boolEPressed = true;
    } else if (keyCode == 'H') {
      boolHPressed = true;
    }
  }

  /**
   * Checks if various keys are released
   */
  public void keyReleased() {
    if (keyCode == 32) {
      boolSpaceBar = false;
    } else if (keyCode == 10) {
      boolEnterPressed = false;
    } else if (keyCode == RIGHT) {
      boolRightPressed = false;
    } else if (keyCode == LEFT) {
      boolLeftPressed = false;
    } else if (keyCode == 9) {
      boolTabPressed = false;
    } else if (keyCode == 'S') {
      boolSPressed = false;
    } else if (keyCode == 'D') {
      boolDPressed = false;
    } else if (keyCode == 'E') {
      boolEPressed = false;
    } else if (keyCode == 'H') {
      boolHPressed = false;
    }
  }
}