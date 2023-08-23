// Name : Kerem Haktan Kurt
// Date : 16/04/2023
// Summary :
//          barDraw() : Draw the picture behind the time bar
//          timeBarDraw(double startTime) : Draw the bar according the difference between currentTime and startTime
//                  (Turn from yellow to red and shorten its width).

public class Bar {

    double gameDuration = 40000;

    public void barDraw() {
        StdDraw.picture(8.0, -0.5, "bar.png", 16, 1);
    }

    public void timeBarDraw(double startTime) {
        StdDraw.enableDoubleBuffering();
        double timeDifference = System.currentTimeMillis() - startTime;

        // MANAGING THE TONE OF THE GREEN
        double greenTone = 256 - (timeDifference * 255 / 40000);
        // LOCATION OF TIME BAR
        double rectangleCenter = 8.0 - (timeDifference * 8 / 40000);
        StdDraw.setPenColor(255, (int) greenTone, 0);
        StdDraw.filledRectangle(rectangleCenter, -0.5, rectangleCenter, 0.25);

    }
}
