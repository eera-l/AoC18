package day_3;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Federica
 * federica.comuni0002@stud.hkr.se
 * on 03/12/2018.
 */
@SuppressWarnings("Duplicates")
public class Task1 {

    private String[] claims = new String[1229];
    private byte[][] fabric = new byte[1200][1200];
    private int[] leftBorder = new int[1229];
    private int[] topBorder = new int[1229];
    private int[] width = new int[1229];
    private int[] height = new int[1229];


    public static void main(String[] args) {

        Task1 task1 = new Task1();

        try (Scanner input = new Scanner(new FileInputStream("day_3.txt"))) {

            int i = 0;
            while (input.hasNextLine()) {
                task1.claims[i] = input.nextLine();
                i++;
            }

        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        }

        task1.substringMeasures();
    }

    private void substringMeasures() {

        for (int i = 0; i < claims.length; i++) {
            int atIndex = 0;
            int commaIndex = 0;
            int columnsIndex = 0;
            int timesIndex = 0;

            for (int j = 0; j < claims[i].length(); j++) {
                if (claims[i].charAt(j) == '@') {
                    atIndex = j;
                }

                if (claims[i].charAt(j) == ',') {
                    commaIndex = j;
                }

                if (claims[i].charAt(j) == ':') {
                    columnsIndex = j;
                }

                if (claims[i].charAt(j) == 'x') {
                    timesIndex = j;
                }
            }

            String lBorder = claims[i].substring(atIndex + 2, commaIndex);
            String tBorder = claims[i].substring(commaIndex + 1, columnsIndex);
            String w = claims[i].substring(columnsIndex + 2, timesIndex);
            String h = claims[i].substring(timesIndex + 1);

            leftBorder[i] = Integer.parseInt(lBorder);
            topBorder[i] = Integer.parseInt(tBorder);
            width[i] = Integer.parseInt(w);
            height[i] = Integer.parseInt(h);
        }

        calculateOverlapping();
    }

    private void calculateOverlapping() {
        for (int i = 0; i < leftBorder.length; i++) {

            for (int y = 0; y < height[i]; y++) {
                for (int x = 0; x < width[i]; x++) {

                    if (fabric[x + leftBorder[i]][y + topBorder[i]] == 0) {
                        fabric[x + leftBorder[i]][y + topBorder[i]] = 1;
                    } else if (fabric[x + leftBorder[i]][y + topBorder[i]] == 1) {
                        fabric[x + leftBorder[i]][y + topBorder[i]] = 120;
                    }

                }
            }
        }
        /*for (byte[] b : fabric) {
            for (byte bb : b) {
                System.out.print(bb);
            }
            System.out.println();
        }*/

        countOverlapping();
    }

    private void countOverlapping() {
        int counter = 0;

        for (byte[] b : fabric) {
            for (byte bb : b) {
                if (bb == 120)
                    counter++;
            }
        }

        System.out.println(counter);
    }
}
