package day_7;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Federica
 * federica.comuni0002@stud.hkr.se
 * on 07/12/2018.
 */

@SuppressWarnings("Duplicates")
public class Task2 {

    private List<String> instructions;
    private ArrayList<Character>[] byRequired = new ArrayList[26];
    private ArrayList<Character>[] byRequiring = new ArrayList[26];
    private byte[] canGo = new byte[26];
    private int[] workers = new int[5];
    private String finalOrder = "";
    private int generalCounter = 1;


    public static void main(String[] args) {

        Task2 task2 = new Task2();

        try {
            task2.instructions = Files.readAllLines(new File("day_7.txt").toPath());

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        task2.fillOrders();
        task2.startSteps();
    }

    private void fillOrders() {

        for (int i = 0; i < byRequired.length; i++) {
            byRequired[i] = new ArrayList<>();
        }

        for (int i = 0; i < byRequiring.length; i++) {
            byRequiring[i] = new ArrayList<>();
        }

        for (int i = 0; i < instructions.size(); i++) {
            char required = instructions.get(i).charAt(5);
            char requiring = instructions.get(i).charAt(36);


            byRequired[required - 65].add(requiring);
            byRequiring[requiring - 65].add(required);
        }
    }

    private void startSteps() {

        for (int i = 0; i < byRequiring.length; i++) {
            if (byRequiring[i].size() == 0 && canGo[i] == 0) {
                canGo[i] = 1;
            }
        }

        for (int i = 0; i < workers.length; i++) {
           assignWork(i);
        }

        completeTasks();
    }

    private void assignWork(int workerIndex) {

        for (int i = 0; i < canGo.length; i++) {
            if (canGo[i] == 1) {
                workers[workerIndex] = i + 60;
                canGo[i] = 3;
                break;
            }

        }
        if (workers[workerIndex] == 0)
            workers[workerIndex] = -1;

    }

    private void completeTasks() {

        int[] copy = Arrays.copyOf(workers, workers.length);
        while (finalOrder.length() < 26) {
            generalCounter++;

            for (int j = 0; j < workers.length; j++) {
                if (workers[j] > 0) {
                    workers[j]--;
                } else if (workers[j] == 0) {

                    canGo[copy[j] - 60] = 2;
                    finalOrder += (char)(copy[j] + 5);
                    freeOtherTasks(copy[j] - 60);
                    for (int k = 0; k < workers.length; k++) {
                        if (workers[k] < 0 || (workers[k] == 0 && k == j)) {
                            assignWork(k);
                            copy[k] = workers[k];
                        }
                    }


                }
            }
        }
        System.out.println(generalCounter);

        System.out.println(finalOrder);
    }

    private void freeOtherTasks(int i) {

        for (int j = 0; j < byRequired[i].size(); j++) {

            byRequiring[byRequired[i].get(j) - 65].remove(Character.valueOf((char) (i + 65)));

            if (byRequiring[byRequired[i].get(j) - 65].size() == 0) {
                canGo[byRequired[i].get(j) - 65] = 1;
            }
        }
    }


}
