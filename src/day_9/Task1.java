package day_9;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

/**
 * Created by Federica
 * federica.comuni0002@stud.hkr.se
 * on 13/12/2018.
 */
public class Task1 {

    private int players;
    private int lastMarble;

    public static void main(String[] args) {

        Task1 task1 = new Task1();

        try {
            List<String> input = Files.readAllLines(new File("day_9.txt").toPath());
            task1.players = Integer.parseInt(input.get(0).substring(0, 3));
            task1.lastMarble = Integer.parseInt(input.get(0).substring(34, 39));

            System.out.println(task1.players);
            System.out.println(task1.lastMarble);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        task1.execute();
    }

    private void execute() {

        CircularLinkedList<Integer> marbles = new CircularLinkedList<>();
        int currentMarble = 0;
        int[] playersScore = new int[players];

        for (int i = 0; i <= lastMarble; i++) {

            if (i == 0 || i % 23 != 0) {
                if (i == 0) {
                    marbles.addFirst(i);
                    currentMarble = i;
                } else if (i == 1) {
                    marbles.addLast(i);
                    currentMarble = i;
                } else if (i == 2) {
                    marbles.addAt(i, 1);
                    currentMarble = 1;
                } else {
                    int index = (currentMarble + 2) % marbles.size();
                    marbles.addAt(i, index);
                    currentMarble = index;
                }
            } else {
                playersScore[i % players] += i;
                int index = (currentMarble - 7) % marbles.size();
                playersScore[i % players] += marbles.get(index);
                marbles.removeAt(index);
                currentMarble = index;
            }
        }

        int max = playersScore[0];
        for (int i : playersScore) {
            if (i > max) {
                max = i;
            }
        }
        System.out.println(max);
    }
}
