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
public class Task1 {

    public static void main(String[] args) {

        ArrayList<Integer> numbers = new ArrayList<>();

        try (Scanner input = new Scanner(new FileInputStream("day_1.txt"))) {

            while (input.hasNextInt()) {
                numbers.add(input.nextInt());
            }

        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        }

        System.out.println(numbers.stream().mapToInt(Integer::intValue).sum());
    }
}
