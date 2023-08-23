// Name : Kerem Haktan Kurt
// Date : 16/04/2023
// Summary :
//          ballDrawing() : Draw the ball according to their levels and coordinates.
//          ballCoordinate() : Change the ball of the coordinate by its velocities.
//          changeBallXDirection() and changeBallYDirection() : Change the direction of their X and Y axis speeds.
//          removeBall(ArrayList<Ball> myBallList) : Remove the current ball from myBallList.
//          addBall(ArrayList<Ball> myBallList) : Add two balls one level lower than the current ball
//                  with opposite X axis speeds to the myBallList.
//          arrowCollision(Arrow myArrow) : Check if myArrow collides with the ball by first checking the range between
//                  the center of the ball and the tip of the arrow, then checking if the ball hits the arrow from x
//                  direction.
//          playerCollision(Player myPlayer) : Check if myPlayer collides with the ball by virtually creating
//                  a bigger box with its dimensions increased by the radius of each ball, then looking for the distance
//                  between that and the center of the ball.

import java.util.ArrayList;

public class Ball {
    public int level;
    public double xCoordinate;
    public double yCoordinate;
    public double xVelocity;
    public double yVelocity;

    public void ballDrawing() {
        StdDraw.enableDoubleBuffering();
        double radius;
        if (this.level == 2) {
            radius = 0.5 * 0.0175 * 2.0 * 2.0 * 2.0;
        } else if (this.level == 1) {
            radius = 0.5 * 0.0175 * 2.0 * 2.0;
        } else {
            radius = 0.5 * 0.0175 * 2.0;
        }
        if ((16.0 - this.xCoordinate < 8 * radius) || (this.xCoordinate < 8 * radius)) {
            changeBallXDirection();
        }
        if (this.yCoordinate < 8 * radius) {
            changeBallYDirection();
        }
        StdDraw.picture(xCoordinate, yCoordinate, "ball.png", 16 * radius, 16 * radius);
    }

    public void ballCoordinate() {
        this.xCoordinate += xVelocity;
        this.yCoordinate += yVelocity;
        this.yVelocity -= 0.0003 * 9;
    }

    public void changeBallXDirection() {
        this.xVelocity = -xVelocity;
    }

    public void changeBallYDirection() {
        this.yVelocity = 0.03 * (Math.pow(level + 1, 1.0005)) + 0.06;
    }

    public void removeBall(ArrayList<Ball> myBallList) {
        myBallList.remove(this);
    }

    public void addBall(ArrayList<Ball> myBallList) {
        if (this.level == 0) {
            removeBall(myBallList);
            return;
        }
        removeBall(myBallList);

        // CREATING TWO BALLS WITH OPPOSITE X VELOCITIES AND A LOWER LEVEL
        Ball firstNewBall = new Ball();
        firstNewBall.level = this.level - 1;
        firstNewBall.xCoordinate = this.xCoordinate;
        firstNewBall.yCoordinate = this.yCoordinate;
        firstNewBall.xVelocity = this.xVelocity;
        firstNewBall.yVelocity = 0.03 * (Math.pow(firstNewBall.level + 1, 0.0005)) + 0.06;

        Ball secondNewBall = new Ball();
        secondNewBall.level = this.level - 1;
        secondNewBall.xCoordinate = this.xCoordinate;
        secondNewBall.yCoordinate = this.yCoordinate;
        secondNewBall.xVelocity = -this.xVelocity;
        secondNewBall.yVelocity = 0.03 * (Math.pow(firstNewBall.level + 1, 0.0005)) + 0.06;

        myBallList.add(firstNewBall);
        myBallList.add(secondNewBall);
    }

    public boolean arrowCollision(Arrow myArrow) {
        // TIP OF THE ARROW COLLISION
        if (Math.pow(this.yCoordinate - myArrow.getArrowCoordinate(), 2) + Math.pow(this.xCoordinate - myArrow.arrowStartingCoordinate, 2)
                < Math.pow(8 * 0.5 * 0.0175 * 2.0 * Math.pow(2, this.level), 2)) {
            return true;
        }
        // BODY OF THE ARROW COLLISION
        else if (Math.pow(this.xCoordinate - myArrow.arrowStartingCoordinate, 2) < Math.pow(8 * 0.5 * 0.0175 * 2.0 * Math.pow(2, this.level), 2)) {
            return this.yCoordinate < myArrow.getArrowCoordinate();
        }

        return false;
    }

    public boolean playerCollision(Player myPlayer) {

        // Declaring and assigning border values to the player
        double playersCoord = myPlayer.getPlayerCoordinate();
        double rightBorderCoordinate = playersCoord + 0.2;
        double leftBorderCoordinate = playersCoord - 0.2;
        double topCoord = 0.9;

        double radiusOfBall = 8 * 0.5 * 0.0175 * 2.0 * Math.pow(2, this.level);

        // Checking if the balls coordinates are in a rectangle with edges
        // (width of character + radius and height of character + radius)
        if ((this.xCoordinate < (rightBorderCoordinate + radiusOfBall)) & (this.xCoordinate > (leftBorderCoordinate - radiusOfBall))) {
            if (this.yCoordinate < topCoord + radiusOfBall) {
                return true;
            }
        }

        return false;
    }

}
