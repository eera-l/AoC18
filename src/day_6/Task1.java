package day_6;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * Created by Federica
 * federica.comuni0002@stud.hkr.se
 * on 06/12/2018.
 */
public class Task1 {

    private List<String> coordinates;
    private int[][] map = new int[350][350];
    private int[][] coInts;


    public static void main(String[] args) {

        Task1 task1 = new Task1();

        try {

            task1.coordinates = Files.readAllLines(new File("day_6.txt").toPath());

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        task1.coInts = new int[task1.coordinates.size()][2];
        task1.fillUpCoordinates();
        task1.drawCoordinates();
        task1.calculateDistance();
        task1.findArea();
    }

    private void fillUpCoordinates() {

        for (int i = 0; i < coordinates.size(); i++) {
            String[] coo = coordinates.get(i).split(", ");
            coInts[i][0] = Integer.parseInt(coo[0]);
            coInts[i][1] = Integer.parseInt(coo[1]);
        }

        /*for (int[] i : coInts) {
            for (int ii : i) {
                System.out.print(ii + " ");
            }
            System.out.println();
        }*/
    }

    private void drawCoordinates() {

        for (int x = 0; x < coordinates.size(); x++) {
            map[coInts[x][1]][coInts[x][0]] = x + 1;
        }



        /*for (int[] i : map) {
            for (int ii : i) {
                if (ii == 0) {
                    System.out.print(".");
                } else {
                    System.out.print(ii);
                }
            }
            System.out.println();
        }*/
    }

    private void calculateDistance() {

        for (int y = 0; y < map[0].length; y++) {
            for (int x = 0; x < map[y].length; x++) {

                int distance = Integer.MAX_VALUE;
                int temp = distance;
                int cooX = 0;
                for (int i = 0; i < coordinates.size(); i++) {
                    distance = Math.abs((x - coInts[i][1])) + Math.abs((y - coInts[i][0]));
                    if (distance < temp) {
                        temp = distance;
                        cooX = i + 1;
                    } else if (distance == temp) {
                        cooX = 0;
                    }

                }
                map[x][y] = cooX;
            }
        }
        for (int[] i : map) {
            for (int ii : i) {
                if (ii == 0) {
                    System.out.print(".");
                } else {
                    System.out.print(ii);
                }
            }
            System.out.println();
        }
    }

    private void findArea() {
        int[] counters = new int[coordinates.size() + 1];

        for (int y = 0; y < map[0].length; y++) {
            for (int x = 0; x < map[y].length; x++) {

                if (y == 0 || y == map[0].length - 1 || x == 0 || x == map[0].length - 1) {
                    counters[map[x][y]] = -1;
                }
            }
        }

        for (int y = 0; y < map[0].length; y++) {
            for (int x  = 0; x < map[y].length; x++) {

                if (y > 0 && y < map[0].length - 1 && x > 0 && x < map[0].length - 1) {
                    if (map[x][y] != 0 && counters[map[x][y]] != -1) {
                        counters[map[x][y]]++;
                    }
                }
            }
        }

        for (int i = 0; i < counters.length; i++) {
            System.out.printf("The area of #%d is %d%n", i, counters[i]);
        }

    }
}
