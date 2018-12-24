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
public class Task2 {

    private List<String> input;
    private final long LONG_GEN = 50_000_000_000L;

    public static void main(String[] args) {

        Task2 task2 = new Task2();

        try {
            task2.input = Files.readAllLines(new File("day_12.txt").toPath());

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        task2.splitStrings();
        task2.countPlants();
    }

    private void splitStrings() {

        input.set(0, ".........." + input.get(0).substring(15) + "......................");
        StringBuilder s = new StringBuilder(input.get(0));

        //the pattern stabilizes at 101
        for (long i = 0; i < 101; i++) {

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
        }

        long totalSum = countPlants() + (countPlantsCounter() * (LONG_GEN - 101));
        System.out.println("Total sum after 50 000 000 000 generations: " + totalSum);
    }

    private int countPlants() {

        int totalSum = 0;

        for (int i = 0; i < input.get(0).length(); i++) {

            if (input.get(0).charAt(i) == '#') {
                totalSum += i - 10;
            }
        }

        return totalSum;
    }

    private int countPlantsCounter() {
        int totalPlants = 0;

        for (int i = 0; i < input.get(0).length(); i++) {

            if (input.get(0).charAt(i) == '#') {
                totalPlants++;
            }
        }

        return totalPlants;
    }
}
