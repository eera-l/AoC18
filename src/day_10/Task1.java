package day_10;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Federica
 * federica.comuni0002@stud.hkr.se
 * on 14/12/2018.
 */
public class Task1 {

    private List<String> input;
    private byte[][] grid;
    private ArrayList<Point> points = new ArrayList<>();

    public static void main(String[] args) {

        Task1 task1 = new Task1();

        try {
            task1.input = Files.readAllLines(new File("day_10.txt").toPath());

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        for (int i = 0; i < task1.input.size(); i++) {
            String xS = task1.input.get(i).substring(10, 16);
            if (xS.charAt(0) == ' ') {
                xS = xS.substring(1);
            }
            String yS = task1.input.get(i).substring(18, 24);

            if (yS.charAt(0) == ' ') {
                yS = yS.substring(1);
            }

            String velXS = task1.input.get(i).substring(36, 38);
            if (velXS.charAt(0) == ' ') {
                velXS = velXS.substring(1);
            }

            String velYS = task1.input.get(i).substring(40, 42);
            if (velYS.charAt(0) == ' ') {
                velYS = velYS.substring(1);
            }

            int x = Integer.parseInt(xS);
            int y = Integer.parseInt(yS);
            int velX = Integer.parseInt(velXS);
            int velY = Integer.parseInt(velYS);

            task1.points.add(new Point(x, y, velX, velY));
        }

        task1.runGenerator();
    }

    private void runGenerator() {


        grid = new byte[500][500];

        for (int i = 0; i < 10760; i++) {
            for (int j = 0; j < points.size(); j++) {
                points.get(j).move();
            }

            if (i >= 10680 && i <= 10681) {
                int maxX = points.get(0).x;
                int minX = points.get(0).x;

                int maxY = points.get(0).y;
                int minY = points.get(0).y;

                int diffX = Integer.MAX_VALUE;
                int diffY = Integer.MAX_VALUE;

                for (int k = 1; k < points.size(); k++) {
                    if (points.get(k).x > maxX) {
                        maxX = points.get(k).x;
                    }

                    if (points.get(k).x < minX) {
                        minX = points.get(k).x;
                    }

                    if (points.get(k).y > maxY) {
                        maxY = points.get(k).y;
                    }

                    if (points.get(k).y < minY) {
                        minY = points.get(k).y;
                    }
                    if ((maxX - minX) < diffX) {
                        diffX = maxX - minX;
                        System.out.printf("Iteration #%d: diffX = %d%n", i, diffX);
                    }

                    if ((maxY - minY) < diffY) {
                        diffY = maxY - minY;
                        System.out.printf("Iteration #%d: diffY = %d%n", i, diffY);
                    }
                }

             /* System.out.println(maxX);
                System.out.println(minX);
                System.out.println(maxY);
                System.out.println(minY);
                System.out.println();*/

                int origoX = (maxX + Math.abs(minX)) / 2;
                int origoY = (maxY + Math.abs(minY)) / 2;

                fillGrid(origoX, origoY);
                draw();
                System.out.println();
                System.out.println("===============");
                System.out.println();
            }
        }




    }

    private void fillGrid(int origoX, int origoY) {
        for (int i = 0; i < points.size(); i++) {
            grid[origoY + points.get(i).y][origoX + points.get(i).x] = 1;
        }
    }

    private void draw() {

        for (byte[] b : grid) {
            for (byte bb : b) {
                if (bb == 0) {
                    System.out.print(".");
                } else {
                    System.out.print("#");
                }
            }
            System.out.println();
        }
    }
}
