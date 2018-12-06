package day_1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Federica
 * federica.comuni0002@stud.hkr.se
 * on 01/12/2018.
 */
public class Task2 {

    public static void main(String[] args) {

        ArrayList<Integer> numbers = new ArrayList<>();
        ArrayList<Integer> sums = new ArrayList<>();
        int sum = 0;

        try (Scanner input = new Scanner(new FileInputStream("day_1.txt"))) {

            while (input.hasNextInt()) {
                numbers.add(input.nextInt());
            }

        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        }


        boolean broken = false;
        for (int j = 0; j < 500; j++) {
            for (int i = 0; i < numbers.size(); i++) {
                sum += numbers.get(i);
                if (!sums.contains(sum)) {
                    sums.add(sum);
                } else {
                    System.out.println(sum);
                    broken = true;
                    break;
                }
            }
            if (broken)
                break;
        }


    }
}
