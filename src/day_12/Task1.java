package day_12;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * Created by Federica
 * federica.comuni0002@stud.hkr.se
 * on 23/12/2018.
 */
@SuppressWarnings("Duplicates")
public class Task1 {

    private List<String> input;
    private final int SHORT_GEN = 20;

    public static void main(String[] args) {

        Task1 task1 = new Task1();

        try {
            task1.input = Files.readAllLines(new File("day_12.txt").toPath());

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        task1.splitStrings();
        task1.countPlants();

    }

    private void splitStrings() {

        input.set(0, ".........." + input.get(0).substring(15) + "......................");
        StringBuilder s = new StringBuilder(input.get(0));

        for (long i = 0; i < SHORT_GEN; i++) {

            for (int j = 0; j < input.get(0).length() - 5; j++) {
                boolean theresRule = false;
                for (int k = 2; k < input.size(); k++) {
                    String state = input.get(0).substring(j, j + 5);
                    String rule = input.get(k).substring(0 , 5);

                    if (state.equals(rule)) {
                        if (input.get(k).substring(input.get(k).length() - 1).equals("#")) {
                            s.setCharAt(j + 2, '#');
                            theresRule = true;
                            break;
                        } else if (input.get(k).substring(input.get(k).length() - 1).equals(".")) {
                            s.setCharAt(j + 2, '.');
                            theresRule = true;
                            break;
                        }
                    }
                }
                if (!theresRule) {
                    s.setCharAt(j + 2, '.');
                }

            }
            s = s.append("....");
            input.set(0, s.toString());
            System.out.println(i + ": " + input.get(0));
        }

    }

    private void countPlants() {

        int totalSum = 0;

        for (int i = 0; i < input.get(0).length(); i++) {

            if (input.get(0).charAt(i) == '#') {
                totalSum += i - 10;
            }
        }

        System.out.println("Final sum: " + totalSum);
    }
}
