// Name : Kerem Haktan Kurt
// Date : 16/04/2023
// Summary :
//          drawArrow() : Increase its y coordinate by its speed then draw the arrow then make it unavailable.
//          arrowStartingPoint() : Turn arrow back to the bottom and make it available.
//          getArrowCoordinate() : Get the Y coordinate of the tip.
public class Arrow {
    boolean availability;
    double arrowSpeed;
    double arrowTipCoordinate;
    double arrowStartingCoordinate;

    public void drawArrow() {
        if (arrowTipCoordinate > 9.0) {
            arrowStartingPoint();
            return;
        }
        arrowTipCoordinate += arrowSpeed;
        StdDraw.picture(arrowStartingCoordinate, arrowTipCoordinate / 2, "arrow.png", 0.2, arrowTipCoordinate);
        this.availability = false;
    }

    public void arrowStartingPoint() {
        this.availability = true;
        arrowTipCoordinate = 0.0;
    }

    public double getArrowCoordinate() {
        return arrowTipCoordinate;
    }
}
