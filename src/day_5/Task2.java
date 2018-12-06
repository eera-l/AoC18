package day_5;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Federica
 * federica.comuni0002@stud.hkr.se
 * on 05/12/2018.
 */
@SuppressWarnings("Duplicates")
public class Task2 {


    private char[] input;
    private ArrayList<Character> letters = new ArrayList<>();

    public static void main(String[] args) {
        Task2 task2 = new Task2();


        try {
            List<String> letters = Files.readAllLines(new File("day_5.txt").toPath());
            task2.input = letters.get(0).toCharArray();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        for (int i = 0; i < task2.input.length; i++) {
            task2.letters.add(task2.input[i]);
        }

        task2.findLetter();
    }

    private void findLetter() {

        int length = Integer.MAX_VALUE;
        ArrayList<Character> copy = new ArrayList<>(letters);
        for (int i = 65; i < 91; i++) {

            letters = new ArrayList<>(copy);
            for (int j = 0; j < letters.size(); j++) {

                if (letters.get(j) == (char)i || letters.get(j) == (char)(i + 32)) {
                    letters.remove(j);
                    j--;
                }
            }
            int temp = findMatching();
            if (temp < length) {
                length = temp;
                System.out.println(length);
                System.out.println((char)i);
            }
        }

    }

    private int findMatching() {

        for (int j = 0; j < 5000; j++) {
            boolean stop = true;
            for (int i = 0; i < letters.size() - 1; i++) {

                if (letters.get(i) == letters.get(i + 1) - 32 || letters.get(i) == letters.get(i + 1) + 32) {
                    letters.remove(i + 1);
                    letters.remove(i);
                    stop = false;
                }
            }
            if (stop) break;
        }
        return letters.size();
    }
}
