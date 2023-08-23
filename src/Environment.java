// Name : Kerem Haktan Kurt
// Date : 16/04/2023
// Summary : 1- Declare classes
//           2- Enter the Environment constructor and create the balls, ball list, player and arrow variable's
//              initial values
//           3- Check time and create canvas, enter the main loop draw background, bar and time bar
//           4- Draw all the balls and move them for next frame
//           5- Check if space is pressed then send the arrow. Check for ball and arrow collision
//           6- Draw the player and move it for next frame. Check for ball and player collision
//           7- When balls end, ball hits the player or time finishes quit the loop and finish the game with restart
//              or quit screen.

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.TreeMap;

public class Environment {

    //Declaring classes
    Bar myBar = new Bar();
    Ball firstBall = new Ball();
    Ball secondBall = new Ball();
    Ball thirdBall = new Ball();
    Player myPlayer = new Player();
    Arrow myArrow = new Arrow();

    Environment() {

        while (true) {

            boolean gameStatus = false;

            // Creating the three levels of balls
            firstBall.level = 0;
            firstBall.xCoordinate = 16.0 / 4;
            firstBall.yCoordinate = 0.5;
            firstBall.xVelocity = 16.0 / 300.0;
            firstBall.yVelocity = 0.03 * (Math.pow(1, 0.0005)) + 0.06;

            secondBall.level = 1;
            secondBall.xCoordinate = 16.0 / 3;
            secondBall.yCoordinate = 0.5;
            secondBall.xVelocity = -16.0 / 300.0;
            secondBall.yVelocity = 0.03 * (Math.pow(2, 0.0005)) + 0.06;

            thirdBall.level = 2;
            thirdBall.xCoordinate = 16.0 / 4;
            thirdBall.yCoordinate = 0.5;
            thirdBall.xVelocity = 16.0 / 300.0;
            thirdBall.yVelocity = 0.03 * (Math.pow(3, 0.0005)) + 0.06;

            // Creating an arraylist to store balls for easier calculations
            ArrayList<Ball> myBalls = new ArrayList<Ball>();
            myBalls.add(firstBall);
            myBalls.add(secondBall);
            myBalls.add(thirdBall);

            myPlayer.playerXCoord = 8;

            // MANAGING THE ARROW PROPERTIES
            myArrow.availability = true;
            myArrow.arrowTipCoordinate = 0.0;
            myArrow.arrowSpeed = 0.06;

            // CREATING THE BACKGROUND
            StdDraw.enableDoubleBuffering();
            backgroundVariables();

            double startTime = System.currentTimeMillis();

            while (true) {

                // MAKING SURE THAT GAME NO LONGER WORKS AFTER 40k ms
                if (System.currentTimeMillis() - startTime > 39999) {
                    gameStatus = false;
                    break;
                }
                // FIRST DRAWING BACKGROUND THEN TIME BAR
                backgroundDraw();
                myBar.barDraw();
                myBar.timeBarDraw(startTime);

                // DRAWING ALL THE BALLS ONTO THE SCREEN
                for (Ball tempBall : myBalls) {
                    tempBall.ballDrawing();
                    tempBall.ballCoordinate();
                }
                // IF NO BALL LEFT GAME IS WON
                if (myBalls.size() == 0) {
                    gameStatus = true;
                    break;
                }

                // IF SPACE PRESSED CREATE AND SEND THE ARROW
                if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE)) {
                    myArrow.availability = false;
                }
                if (myArrow.availability) {
                    // USE PLAYERS CURRENT POSITION THEN MAKE SURE IT IS NOT REACHABLE FOR ACCURATE ARROWING
                    myArrow.arrowStartingCoordinate = myPlayer.getPlayerCoordinate();
                } else {
                    // Checking for collisions then
                    // Creating the new balls and returning the arrow
                    for (int i = 0; i < 8; i++) {
                        if (!myArrow.availability) {
                            if (i < myBalls.size()) {
                                if (myBalls.get(i).arrowCollision(myArrow)) {
                                    myBalls.get(i).addBall(myBalls);
                                    myArrow.arrowStartingPoint();
                                    break;
                                }
                            }
                            myArrow.drawArrow();
                        }
                    }
                }

                // Controlling players movement
                if (StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) {
                    myPlayer.playerLeftMovement();
                } else if (StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) {
                    myPlayer.playerRightMovement();
                }
                myPlayer.playerDraw();

                // CHECKING IF ANY BALL HITS THE PLAYER
                boolean playerColIndex = false;
                for (Ball tempBall : myBalls) {
                    if (tempBall.playerCollision(myPlayer)) {
                        playerColIndex = true;
                    }
                }
                if (playerColIndex) {
                    break;
                }
                StdDraw.show();
                StdDraw.pause(15);
            }
            // Draw everything again for clearer look
            backgroundDraw();
            myPlayer.playerDraw();
            StdDraw.picture(8, 9.0 / 2.18, "game_screen.png", 16.0 / 3.8, 9.0 / 4.0);
            StdDraw.setPenColor(Color.black);
            StdDraw.setFont(new Font("Helvetica", Font.BOLD, 30));
            // Check if the game has won or lost
            while (true) {
                if (!gameStatus) {
                    StdDraw.text(8, 4.5, "GAME OVER!");
                    break;
                } else if (gameStatus) {
                    StdDraw.text(8, 4.5, "YOU WON!");
                    break;
                }
            }
            StdDraw.setFont(new Font("Helvetica", Font.ITALIC, 15));
            StdDraw.text(8, 9.0 / 2.3, "To Replay Click \"Y\"");
            StdDraw.text(8, 9.0 / 2.6, "To Quit Click \"N\"");
            StdDraw.show();
            // Restart or quit the game
            while (true) {
                if (StdDraw.isKeyPressed(KeyEvent.VK_Y)) {
                    break;
                } else if (StdDraw.isKeyPressed(KeyEvent.VK_N)) {
                    System.exit(0);
                }
            }
        }
    }

    // Method for drawing the background image onto desired position
    public void backgroundDraw() {
        StdDraw.picture(8, 4.5, "background.png", 16, 9);
    }


    // Creating the canvas
    public void backgroundVariables() {
        int canvasWidth = 800;
        int canvasHeight = 500;
        StdDraw.setCanvasSize(canvasWidth, canvasHeight);
        StdDraw.setXscale(0.0, 16.0);
        StdDraw.setYscale(-1.0, 9.0);
    }
}
