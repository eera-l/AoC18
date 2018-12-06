package day_6;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Federica
 * federica.comuni0002@stud.hkr.se
 * on 06/12/2018.
 */
@SuppressWarnings("Duplicates")
public class Task2 {

    private List<String> coordinates;
    private int[][] map = new int[350][350];
    private int[][] coInts;
    private final int MAX_DISTANCE = 10_000;


    public static void main(String[] args) {

        Task2 task2 = new Task2();

        try {

            task2.coordinates = Files.readAllLines(new File("day_6.txt").toPath());

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        task2.coInts = new int[task2.coordinates.size()][2];
        task2.fillUpCoordinates();
        task2.drawCoordinates();
        task2.calculateDistance();
        task2.findRegion();
    }

    private void fillUpCoordinates() {

        for (int i = 0; i < coordinates.size(); i++) {
            String[] coo = coordinates.get(i).split(", ");
            coInts[i][0] = Integer.parseInt(coo[0]);
            coInts[i][1] = Integer.parseInt(coo[1]);
        }

    }

    private void drawCoordinates() {

        for (int x = 0; x < coordinates.size(); x++) {
            map[coInts[x][1]][coInts[x][0]] = x + 1;
        }
    }

    private void calculateDistance() {

        for (int y = 0; y < map[0].length; y++) {
            for (int x = 0; x < map[y].length; x++) {

                int distance;
                int[] distances = new int[coordinates.size()];
                int cooX = 0;
                for (int i = 0; i < coordinates.size(); i++) {
                    distance = Math.abs((x - coInts[i][1])) + Math.abs((y - coInts[i][0]));
                    distances[i] = distance;
                }

                int sum = Arrays.stream(distances).sum();
                if (sum < MAX_DISTANCE) {
                    cooX = 50;
                }
                map[x][y] = cooX;
            }
        }

        for (int[] i : map) {
            for (int ii : i) {
                if (ii == 0) {
                    System.out.print(".");
                } else {
                    System.out.print("#");
                }
            }
            System.out.println();
        }
    }

    private void findRegion() {

        int area = 0;

        for (int[] i : map) {
            for (int ii : i) {
                if (ii == 50) {
                    area++;
                }
            }
        }

        System.out.println(area);
    }
}
