package day_10;

/**
 * Created by Federica
 * federica.comuni0002@stud.hkr.se
 * on 15/12/2018.
 */
public class Point {

    public int x;
    public int y;
    public int velX;
    public int velY;

    public Point(int x, int y, int velX, int velY) {
        this.x = x;
        this.y = y;
        this.velX = velX;
        this.velY = velY;
    }

    public void move() {
        x += velX;
        y += velY;
    }
}
