package day_2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Federica
 * federica.comuni0002@stud.hkr.se
 * on 02/12/2018.
 */
public class Task2 {

    public static void main(String[] args) {
        ArrayList<String> letters = new ArrayList<>(250);


        try (Scanner input = new Scanner(new FileInputStream("day_2.txt"))) {

            while (input.hasNext()) {
                letters.add(input.next());
            }

        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        }

        int firstSentence = 0;
        int faulty = 0;
        boolean broken = false;
        for (int i = 0; i < letters.size() - 1; i++) {
            for (int k = i + 1; k < letters.size(); k++) {

                int once = 0;
                for (int j = 0; j < letters.get(i).length(); j++) {
                    if (letters.get(i).charAt(j) != letters.get(k).charAt(j) && once == 0) {
                        once = 1;
                        faulty = j;
                    } else if (letters.get(i).charAt(j) != letters.get(k).charAt(j) && once == 1) {
                        once = 2;
                        break;
                    }
                }
                if (once == 1) {
                    firstSentence = i;
                    System.out.println(letters.get(i));
                    System.out.println(letters.get(k));
                    broken = true;
                    break;
                }
            }
            if (broken) break;

        }

        StringBuilder finalLetters = new StringBuilder(letters.get(firstSentence));
        finalLetters = finalLetters.deleteCharAt(faulty);
        System.out.println(finalLetters);
    }
}
