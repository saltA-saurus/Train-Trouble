import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.lang.*;
import java.util.List;


public class Culminating extends JPanel implements ActionListener, MouseListener{
  
  
  
  
  //-----VARIABLES-----\\
  JLabel output = new JLabel();
  
  ArrayList<Train> trainList = new ArrayList();
  
  Timer time = new Timer (10, this);
  double timeStart;
  String secondsDisplay = "", scoreDisplay = "";
  Font font1 = new Font("Copperplate Gothic Bold", Font.PLAIN, 40);
  Font font2 = new Font("Copperplate Gothic Bold", Font.PLAIN, 60);
  
//menu 0 = main menu, 1 = how to play, 2 = game, 3 = lose screen, 4 = win screen
  int menu = 0, difficulty = 0, counter = 0, counterMax, scoreTime = 0, scoreTrain = 0, scoreTotal = 0, lives = 3, seconds = 0, timeElapsed = 0,
    j1= 1, j2= 0, j3= 0, j4= 0, j5 = 0, secondsDisplayX = 512, scoreDisplayX = 685;
  
  //image variables
  static BufferedImage mainMenuE, mainMenuH, howToPlay, youSuck, youWon, background, heart, greenTrainR, greenTrainL, greenTrainU, greenTrainD, 
    orangeTrainR, orangeTrainL, orangeTrainU, orangeTrainD, blueTrainR, blueTrainL, blueTrainU, blueTrainD,
    purpleTrainR, purpleTrainL, purpleTrainU, purpleTrainD, yellowTrainR, yellowTrainL, yellowTrainU, yellowTrainD,
    redTrainR, redTrainL, redTrainU, redTrainD, railCurved1, railCurved2, railCurved3, railCurved4, railH, railV, tunnel;
  
  
  
  
  
  //-----CONSTRUCTOR-----\\
  //game timer, output, mouseListener
  Culminating () {
    time.start();
    add(output);
    this.addMouseListener(this);
  }
  
  
  
  
  
  //-----CREATE TRAINS-----\\
  public void createTrains(){
    Train t = new Train();
    counter += 1;
    //System.out.println("Counter: " + counter + "        CounterMax: " + counterMax + "        Timeelapsed: " + timeElapsed);
    
    if (difficulty == 0){
      if (timeElapsed < 20){
        counterMax = 350;}
      else if (timeElapsed >= 20 && timeElapsed < 45){
        counterMax = 300;}
      else if (timeElapsed >= 45 && timeElapsed < 100){
        counterMax = 250;}
    }
    
    else if (difficulty == 1){
      if (timeElapsed < 20){
        counterMax = 200;}
      else if (timeElapsed >= 20 && timeElapsed < 45){
        counterMax = 190;}
      else if (timeElapsed >= 45 && timeElapsed < 100){
        counterMax = 180;}
    }
    if (counter >= counterMax){
      trainList.add(t);
      counter = 0;
    }
  }
  
  
  
  
  
  //-----JUNCTION CHECK-----\\
  //checks trains as they go over a junction, and checks junction state, acts accordingly
  public void junctionCheck(){
    
    Iterator it = trainList.iterator();
    while(it.hasNext()) {
      Train t = (Train)it.next();
      //j1
      if (j1 == 1 && t.x == 45 && t.y == 395){
        t.velY = 0;
        t.velX = 1;
        t.x = 45;
        t.y = 420;
      }
      
      //j2
      if (j2 == 1 && t.x == 285 && t.y == 420){
        t.velY = -1;
        t.velX = 0;
        t.x = 300;
        t.y = 395;
      }
      
      //j3
      if (j3 == 1 && t.x == 300 && t.y == 195){
        t.velY = 0;
        t.velX = 1;
        t.x = 280;
        t.y = 215;
      }
      
      //j4
      if (j4 == 1 && t.x == 470 && t.y == 420){
        t.velY = 1;
        t.velX = 0;
        t.x = 484;
        t.y = 400;
      }
      
      //j5
      if (j5 == 0 && t.x == 484 && t.y == 680){
        t.velY = 0;
        t.velX = -1;
        t.x = 469;
        t.y = 680;
      }
      else if (j5 == 1 && t.x == 484 && t.y == 680 && t.velY == 1){
        t.velY = 0;
        t.velX = 1;
        t.x = 471;
        t.y = 680;
      }
      
    }
  }
  
  
  
  
  
  //-----HOUSE CHECK-----\\
  //checks train colour when trains go over houses, acts accordingly
  public void houseCheck(){
    
    Iterator it = trainList.iterator();
    while (it.hasNext()) {
      Train t = (Train) it.next();
      //blue house
      if (t.x == 45 && t.y<=0 ) {
        
        if (t.col == 1){
          scoreTrain += 350;
        }
        else{
          lives--;
        }
        it.remove();
      }
      //green house
      if (t.x==300 && t.y<=0){
        if (t.col == 0){
          scoreTrain += 350;
        }
        else{
          lives--;
        }
        it.remove();
      }
      //yellow house
      if (t.x==650 && t.y==215){
        if (t.col == 3){
          scoreTrain += 350;
        }
        else{
          lives--;
        }
        it.remove();
      }
      //orange house
      if (t.x==650 && t.y==420){
        if (t.col == 5){
          scoreTrain += 350;
        }
        else{
          lives--;
        }
        it.remove();
      }
      //red house
      if (t.x==225 && t.y==680){
        if (t.col == 4){
          scoreTrain += 350;
        }
        else{
          lives--;
        }
        it.remove();
      }
      //purple house
      if (t.x==650 && t.y==680){
        if (t.col == 2){
          scoreTrain += 350;
        }
        else{
          lives--;
        }
        it.remove();
      }
    }
  }
  
  
  
  
  
  //-----LOSE CHECK-----\\
  public void lifeCheck(){
    if (lives == 0){
      menu = 3;
      //add remove all trains
    }
  }
  
  
  
  
  
  //-----WIN CHECK-----\\
  public void scoreWinCheck(){
   // if (scoreTotal >= 10000){
    //  menu = 4;
    //}
  }
  
  
  
  
  
  //-----GAME TIME-----\\
  //adds to score every 1 second and also displays time elapsed
  public void scoreSeconds(Graphics2D shape){
    timeElapsed = (int)((System.currentTimeMillis() - timeStart)/1000);
    secondsDisplay = Integer.toString(timeElapsed);
    shape.setFont(font1); 
    
    if (timeElapsed == 10) {
      secondsDisplayX = 496; 
    }
    scoreTime = timeElapsed * 80;
    shape.drawString(secondsDisplay, secondsDisplayX, 62);
  }
  
  

  
  //-----GAME SCORE-----\\
  public void gameScore(Graphics2D shape){
    scoreTotal = scoreTime + scoreTrain;
    scoreDisplay = Integer.toString(scoreTotal);
    shape.setFont(font1); 
    
    if (scoreTotal >= 10 && scoreTotal < 100){
      scoreDisplayX = 675;
    }
    else if (scoreTotal >= 100 && scoreTotal < 1000){
      scoreDisplayX = 659;
    }
    else if (scoreTotal >= 1000 && scoreTotal < 10000){
      scoreDisplayX = 643;
    }
    shape.drawString(scoreDisplay, scoreDisplayX, 62);
  }  
  
  
  
  
  
  //-----(DRAW) GAME LOOP-----\\
  //draws game, calls up other methods
  public void gameLoop (Graphics2D shape){
    
    //creates trains
    createTrains();
    
    //checks various thing
    junctionCheck();
    houseCheck();
    lifeCheck();
    scoreWinCheck();
    
    //draws game background
    shape.drawImage(background, 0, 0, 800, 800, null);
    
    //tracks time and displays it
    scoreSeconds(shape);
    
    //draws the game score
    gameScore(shape);
    
    //junction shapes
    //j1
    if (j1 == 0){
      shape.drawImage(railV, 60, 400, 47, 110, null);}
    else if (j1 == 1){
      shape.drawImage(railCurved1, 59, 436, 72, 75, null);}
    //j2
    if (j2 == 0){
      shape.drawImage(railH, 285, 433, 107, 47, null);}
    else if (j2 == 1){
      shape.drawImage(railCurved3, 290, 400, 66, 75, null);}
    //j3
    if (j3 == 0){
      shape.drawImage(railV, 312, 185, 47, 130, null);}
    else if (j3 == 1){
      shape.drawImage(railCurved1, 312, 230, 66, 75, null);}
    //j4
    if (j4 == 1){
      shape.drawImage(railCurved2, 475, 430, 66, 75, null);}
    else if (j4 == 0){
      shape.drawImage(railH, 470, 433, 105, 47, null);}
    //j5
    if (j5 == 0){
      shape.drawImage(railCurved3, 478, 663, 66, 75, null);}
    else if (j5 == 1){
      shape.drawImage(railCurved4, 505, 672, 66, 70, null);}
    
    //draws hearts
    if (lives > 2){
      shape.drawImage(heart, 630, 150, 30, 30, null);}
    if (lives > 1){
      shape.drawImage(heart, 680, 150, 30, 30, null);}
    if (lives > 0){
      shape.drawImage(heart, 730, 150, 30, 30, null);}
    
    //draws trains
    Iterator it = trainList.iterator();
    while(it.hasNext()) {
      Train t = (Train)it.next();
      
      if (t.col == 0){
        if (t.velY>0){
          shape.drawImage(greenTrainD,t.x, t.y, 75, 115, null);}
        else if (t.velY<0){
          shape.drawImage(greenTrainU,t.x, t.y, 75, 115, null);}
        else if (t.velX>0){
          shape.drawImage(greenTrainR,t.x, t.y, 115, 75, null);}
        else if (t.velX<0){
          shape.drawImage(greenTrainL,t.x, t.y, 115, 75, null);}
      }
      else if (t.col == 1){
        if (t.velY>0){
          shape.drawImage(blueTrainD,t.x, t.y, 75, 115, null);}
        else if (t.velY<0){
          shape.drawImage(blueTrainU,t.x, t.y, 75, 115, null);}
        else if (t.velX>0){
          shape.drawImage(blueTrainR,t.x, t.y, 115, 75, null);}
        else if (t.velX<0){
          shape.drawImage(blueTrainL,t.x, t.y, 115, 75, null);}
      }
      else if (t.col == 2){
        if (t.velY>0){
          shape.drawImage(purpleTrainD,t.x, t.y, 75, 115, null);}
        else if (t.velY<0){
          shape.drawImage(purpleTrainU,t.x, t.y, 75, 115, null);}
        else if (t.velX>0){
          shape.drawImage(purpleTrainR,t.x, t.y, 115, 75, null);}
        else if (t.velX<0){
          shape.drawImage(purpleTrainL,t.x, t.y, 115, 75, null);}
      }
      else if (t.col == 3){
        if (t.velY>0){
          shape.drawImage(yellowTrainD,t.x, t.y, 75, 115, null);}
        else if (t.velY<0){
          shape.drawImage(yellowTrainU,t.x, t.y, 75, 115, null);}
        else if (t.velX>0){
          shape.drawImage(yellowTrainR,t.x, t.y, 115, 75, null);}
        else if (t.velX<0){
          shape.drawImage(yellowTrainL,t.x, t.y, 115, 75, null);}
      }
      else if (t.col == 4){
        if (t.velY>0){
          shape.drawImage(redTrainD,t.x, t.y, 75, 115, null);}
        else if (t.velY<0){
          shape.drawImage(redTrainU,t.x, t.y, 75, 115, null);}
        else if (t.velX>0){
          shape.drawImage(redTrainR,t.x, t.y, 115, 75, null);}
        else if (t.velX<0){
          shape.drawImage(redTrainL,t.x, t.y, 115, 75, null);}
      }
      else if (t.col == 5){
        if (t.velY>0){
          shape.drawImage(orangeTrainD,t.x, t.y, 75, 115, null);}
        else if (t.velY<0){
          shape.drawImage(orangeTrainU,t.x, t.y, 75, 115, null);}
        else if (t.velX>0){
          shape.drawImage(orangeTrainR,t.x, t.y, 115, 75, null);}
        else if (t.velX<0){
          shape.drawImage(orangeTrainL,t.x, t.y, 115, 75, null);}
      }
      shape.drawImage(tunnel,0,0,800,800,null);
    }
  }
  
  
  
  
  
  //-----DRAW MAIN MENU-----\\
  public void mainMenu (Graphics2D shape){
    
    if (difficulty == 0){
      shape.drawImage(mainMenuE, 0, 0, 800, 800, null);
    }
    else if (difficulty == 1){
      shape.drawImage(mainMenuH, 0, 0, 800, 800, null);
    }
  }
  
  
  
  
  
  //-----DRAW HOW TO PLAY SCREEN-----\\
  public void howToPlay (Graphics2D shape){
    shape.drawImage(howToPlay, 0, 0, 800, 800, null);
  }
  
  
  
  
  
  //-----DRAW LOSE SCREEN-----\\
  public void gameOver (Graphics2D shape){
    shape.drawImage(youSuck, 0, 0, 800, 800, null);
    shape.setColor(Color.WHITE);
    shape.setFont(font2); 
    shape.drawString(scoreDisplay, 309, 390);
    shape.drawString(secondsDisplay + " seconds", 207, 560);
  }
  
  
  
  
  
  //-----DRAW WIN SCREEN-----\\
  public void gameWin (Graphics2D shape){
    shape.drawImage(youWon, 0, 0, 800, 800, null);
  }
  
  
  
  
  
  //-----PAINT COMPONENT-----\\
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D shape = (Graphics2D) g;
    
    //draws different sections of the game
    
    if (menu == 0){
      mainMenu(shape);
    }
    
    else if (menu == 1){
      howToPlay(shape);
    }
    
    else if (menu == 2){
      gameLoop(shape);
    }
    
    else if (menu == 3){
      gameOver(shape);
    }
    
    else if (menu == 4){
      gameWin(shape);
    }
  }
  
  
  
  
  
  //-----ACTION METHOD-----\\
  public void actionPerformed(ActionEvent arg0) {
    //moves trains
    Iterator it = trainList.iterator();
    while (it.hasNext()) {
      Train t = (Train)it.next();
      t.x += t.velX;
      t.y += t.velY;
    }
    //repaints screen
    repaint();
  }
  
  
  
  
  
  //-----MAIN MENU BUTTONS-----\\
  public void mainMenuClick (MouseEvent player){
    
    //play button
    if (player.getX() >= 25 && player.getX() <= 320 && player.getY() >= 375 && player.getY() <= 460){
      menu = 2;
      timeStart = System.currentTimeMillis();
    }
    
    //how to play button
    if (player.getX() >= 470 && player.getY() >= 370 && player.getX() <= 770 && player.getY() <= 460){
      menu = 1;
    }
    
    //difficulty buttons
    if (difficulty == 1 && player.getX() >= 60 && player.getX() <= 200 && player.getY() >= 610 && player.getY() <= 670){
      difficulty = 0;
      //60 - 200 x
      //610 - 670 y
      //easy button
    }
    else if (difficulty == 0 && player.getX() >= 60 && player.getX() <= 200 && player.getY() >= 695 && player.getY() <= 750){
      difficulty = 1;
      //60 - 200 x
      //695 - 750 y
      //hard button
    }
    
    //exit button
    if (player.getX() >= 555 && player.getX() <= 770 && player.getY() >= 515 && player.getY() <= 590){
      System.exit(0); 
    }
    
  }
  
  
  
  
  
  //-----HOW TO PLAY SCREEN BUTTON-----\\
  public void howToPlayClick (MouseEvent player){
    if (player.getX() >= 290 && player.getX() <= 510 && player.getY() >= 665 && player.getY() <= 735)
      menu = 0;
  }
  
  
  
  
  
  //-----JUNCTION MOUSE DETECTION-----\\
  public void gameClick (MouseEvent player){
    
    if (player.getX() >=30 && player.getX() <=135 && player.getY() >=390 && player.getY() <=520 && j1 == 1){
      j1 = 0;
    }
    else if (player.getX() >=30 && player.getX() <=135 && player.getY() >=390 && player.getY() <=520 && j1 == 0){
      j1 = 1;
    }
    
    if (player.getX() >=280 && player.getX() <=385 && player.getY() >=390 && player.getY() <=520 && j2 == 1){
      j2 = 0;
    }
    else if (player.getX() >=280 && player.getX() <=385 && player.getY() >=390 && player.getY() <=520 && j2 == 0){
      j2 = 1;
    }
    
    if (player.getX() >=280 && player.getX() <=385 && player.getY() >=180 && player.getY() <=315 && j3 == 1){
      j3 = 0;
    }
    else if (player.getX() >=280 && player.getX() <=385 && player.getY() >=180 && player.getY() <=315 && j3 == 0){
      j3 = 1;
    }
    
    if (player.getX() >=470 && player.getX() <=575 && player.getY() >=390 && player.getY() <=520 && j4 == 1){
      j4 = 0;
    }
    else if (player.getX() >=470 && player.getX() <=575 && player.getY() >=390 && player.getY() <=520 && j4 == 0){
      j4 = 1;
    }
    
    if (player.getX() >=470 && player.getX() <=575 && player.getY() >=655 && player.getY() <=785 && j5 == 1){
      j5 = 0;
    }
    else if (player.getX() >=470 && player.getX() <=575 && player.getY() >=655 && player.getY() <=785 && j5 == 0){
      j5 = 1;
    }
    
    
  }
  
  
  
  
  
  //-----LOSE SCREEN BUTTON-----\\
  public void gameOverClick (MouseEvent player){
    if (menu == 3 && player.getX() >=255 && player.getX() <=545 && player.getY() >=640 && player.getY() <=710){
      menu = 0;
      resetGame();
    }
  }
  
  
  
  
  
  //-----WIN SCREEN BUTTON-----\\
  public void gameWinClick (MouseEvent player){
    if (menu == 4 && player.getX() >=255 && player.getX() <=545 && player.getY() >=640 && player.getY() <=710){
      menu = 0;
      resetGame();
    }
  }
  
  
  
  
  
  //-----RESET ALL GAME VALUES-----\\
  public void resetGame(){
    lives = 3;
    counter = 0;
    scoreTrain = 0;
    scoreTime = 0;
    scoreTotal = 0;
    secondsDisplayX = 515;
    scoreDisplayX = 690;
    trainList.removeAll(trainList);
  }
  
  
  
  
  
  //-----MOUSE CLICK DETECTION-----\\
  public void mousePressed(MouseEvent player) {
    
    
    //main menu
    if (menu == 0) {
      mainMenuClick(player);
    }
    
    //how to play
    else if (menu == 1){
      howToPlayClick(player);
    }
    
    // game
    else if (menu == 2){
      gameClick(player);
    }
    
    //game over screen
    else if (menu == 3){
      gameOverClick(player);
    }
    
    //game win screen
    else if (menu == 4){
      gameWinClick(player);
    }
  }
  
  
  
  
  
  //-----UNUSED METHODS-----\\
  public void mouseEntered(MouseEvent player) {}
  public void mouseReleased(MouseEvent player) {}
  public void mouseClicked(MouseEvent player) {}
  public void mouseExited(MouseEvent player) {}
  
  
  
  
  
  //-----LOAD IMAGES-----\\
  public static void images() {
    try {
      background = ImageIO.read(new File ("Background.jpg"));
      greenTrainL = ImageIO.read(new File ("GreenTrainL.png"));
      greenTrainR = ImageIO.read(new File ("GreenTrainR.png"));
      greenTrainU = ImageIO.read(new File ("GreenTrainU.png"));
      greenTrainD = ImageIO.read(new File ("GreenTrainD.png"));
      blueTrainL = ImageIO.read(new File ("blueTrainL.png"));
      blueTrainR = ImageIO.read(new File ("blueTrainR.png"));
      blueTrainU = ImageIO.read(new File ("blueTrainU.png"));
      blueTrainD = ImageIO.read(new File ("blueTrainD.png"));
      purpleTrainL = ImageIO.read(new File ("purpleTrainL.png"));
      purpleTrainR = ImageIO.read(new File ("purpleTrainR.png"));
      purpleTrainU = ImageIO.read(new File ("purpleTrainU.png"));
      purpleTrainD = ImageIO.read(new File ("purpleTrainD.png"));
      yellowTrainL = ImageIO.read(new File ("yellowTrainL.png"));
      yellowTrainR = ImageIO.read(new File ("yellowTrainR.png"));
      yellowTrainU = ImageIO.read(new File ("yellowTrainU.png"));
      yellowTrainD = ImageIO.read(new File ("yellowTrainD.png"));
      redTrainL = ImageIO.read(new File ("redTrainL.png"));
      redTrainR = ImageIO.read(new File ("redTrainR.png"));
      redTrainU = ImageIO.read(new File ("redTrainU.png"));
      redTrainD = ImageIO.read(new File ("redTrainD.png"));
      orangeTrainL = ImageIO.read(new File ("orangeTrainL.png"));
      orangeTrainR = ImageIO.read(new File ("orangeTrainR.png"));
      orangeTrainU = ImageIO.read(new File ("orangeTrainU.png"));
      orangeTrainD = ImageIO.read(new File ("orangeTrainD.png"));
      railH = ImageIO.read(new File ("StraightRailH.png"));
      railV = ImageIO.read(new File ("StraightRailV.png"));
      railCurved1 = ImageIO.read(new File ("CurvedRail1.png"));
      railCurved2 = ImageIO.read(new File ("CurvedRail2.png"));
      railCurved3 = ImageIO.read(new File ("CurvedRail3.png"));
      railCurved4 = ImageIO.read(new File ("CurvedRail4.png"));
      heart = ImageIO.read(new File ("Heart.png"));
      mainMenuE = ImageIO.read(new File ("MainMenuE.jpg"));
      mainMenuH = ImageIO.read(new File ("MainMenuH.jpg"));
      howToPlay = ImageIO.read(new File ("HowToPlay.jpg"));
      youSuck = ImageIO.read(new File ("YouSuck.jpg"));
      youWon = ImageIO.read(new File ("YouWon.jpg"));
      tunnel = ImageIO.read(new File ("tunnel.png"));
    }
    catch  (IOException e){
      System.out.println("IMAGE NOT FOUND");
    }
  }
  
  
  
  
  
  //-----JFRAME-----\\
  public static void gameWindow () {
    Culminating i = new Culminating ();
    JFrame T = new JFrame ("Train Trouble");
    T.add(i);
    T.setSize (800, 829);
    T.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    T.setVisible (true);
    T.setResizable(false);
  }
  
  
  
  
  
  //-----MAIN METHOD-----\\
  public static void main (String[] args) {
    gameWindow();
    images();
  }
  
}

