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
public class Task1 {


    public static void main(String[] args) {

        ArrayList<String> letters = new ArrayList<>(250);
        int[] alphabet = new int[26];
        int counterTwo = 0;
        int counterThree = 0;

        try (Scanner input = new Scanner(new FileInputStream("day_2.txt"))) {

            while (input.hasNext()) {
                letters.add(input.next());
            }

        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        }

        for (int k = 0; k < letters.size(); k++) {

            String s = letters.get(k);

            for (int i = 0; i < alphabet.length; i++) {
                for (int j = 0; j < s.length(); j++) {

                    if (s.charAt(j) == (char)(i + 97)) {
                        alphabet[i]++;
                    }
                }
            }

            boolean once2 = false;
            boolean once3 = false;

            for (int i = 0; i < alphabet.length; i++) {

                if (alphabet[i] == 2 && !once2) {
                    counterTwo++;
                    once2 = true;
                }

                if (alphabet[i] == 3 && !once3) {
                    counterThree++;
                    once3 = true;
                }

                if (once2 && once3) break;
            }
            for (int i = 0; i < alphabet.length; i++) {
                alphabet[i] = 0;
            }
        }

        int checksum = counterTwo * counterThree;
        System.out.println(checksum);
    }
}
