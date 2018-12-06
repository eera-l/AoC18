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
public class Task1 {

    private char[] input;
    private ArrayList<Character> letters = new ArrayList<>();

    public static void main(String[] args) {
        Task1 task1 = new Task1();


        try {
            List<String> letters = Files.readAllLines(new File("day_5.txt").toPath());
            task1.input = letters.get(0).toCharArray();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        for (int i = 0; i < task1.input.length; i++) {
            task1.letters.add(task1.input[i]);
        }
        task1.findMatching();

    }

    private void findMatching() {

        for (int j = 0; j < 737; j++) {
            for (int i = 0; i < letters.size() - 1; i++) {

                if (letters.get(i) == letters.get(i + 1) - 32 || letters.get(i) == letters.get(i + 1) + 32) {
                    letters.remove(i + 1);
                    letters.remove(i);
                }
            }
        }

        System.out.println(letters.size());

    }
}
