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
@SuppressWarnings("Duplicates")
public class Task2 {

    private int players;
    private int lastMarble;
    private CircularLinkedList.Node currentNode;

    public static void main(String[] args) {

        Task2 task2 = new Task2();

        try {
            List<String> input = Files.readAllLines(new File("day_9.txt").toPath());
            task2.players = Integer.parseInt(input.get(0).substring(0, 3));
            task2.lastMarble = Integer.parseInt(input.get(0).substring(34, 39)) * 100;

            System.out.println(task2.players);
            System.out.println(task2.lastMarble);

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        task2.execute(true);
    }

    private void execute(boolean run) {

        CircularLinkedList<Integer> marbles = new CircularLinkedList<>();
        long[] playersScore = new long[players];

        for (int i = 0; i <= lastMarble; i++) {

            if (i == 0 || i % 23 != 0) {
                if (i == 0) {
                    currentNode = marbles.addFirst(i);
                } else if (i == 1) {
                    currentNode = marbles.addLast(i);
                } else if (i == 2) {
                    currentNode = marbles.addAfterCurrent(currentNode, i);
                } else {
                    currentNode = marbles.addAfterCurrent(currentNode, i);
                }
            } else {
                playersScore[i % players] += i;
                Object[] data = marbles.removeAfterCurrent(currentNode, 7);
                playersScore[i % players] += (Integer)data[0];
                currentNode = (CircularLinkedList.Node)data[1];
            }
        }

        long max = playersScore[0];
        for (long i : playersScore) {
            if (i > max) {
                max = i;
            }
        }
        System.out.println(max);
    }
}
