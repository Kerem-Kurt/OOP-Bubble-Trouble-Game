// Name : Kerem Haktan Kurt
// Date : 16/04/2023
// Summary :
//          playerDraw() : Draw the player just on top of the bar with the desired X position.
//          playerRightMovement() : Move the player right by its velocity.
//          playerLeftMovement() : Move the player left by its velocity.
//          getPlayerCoordinate() : Get the X coordinate of the player

public class Player {
    double playerXCoord;
    double playerXVelocity;

    public void playerDraw() {
        if (playerXCoord < 0.45) {
            playerXVelocity = 0;
            playerXCoord = 0.45;
        } else if (playerXCoord - 16.0 > -0.45) {
            playerXVelocity = 0;
            playerXCoord = 16.0 - 0.45;
        }
        StdDraw.picture(playerXCoord, 0.53, "player_back.png", 27.0 * 9.0 / 37.0 / 8.0, 9.0 / 8.0);
    }

    public void playerRightMovement() {
        playerXVelocity = 16.0 * 2.5 / 300;
        playerXCoord += playerXVelocity;

    }

    public void playerLeftMovement() {
        playerXVelocity = -16.0 * 2.5 / 300;
        playerXCoord += playerXVelocity;
    }

    public double getPlayerCoordinate() {
        return this.playerXCoord;
    }
}
