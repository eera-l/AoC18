package day_7;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Federica
 * federica.comuni0002@stud.hkr.se
 * on 07/12/2018.
 */
@SuppressWarnings("Duplicates")
public class Task1 {

    private List<String> instructions;
    private ArrayList<Character>[] byRequired = new ArrayList[26];
    private ArrayList<Character>[] byRequiring = new ArrayList[26];
    private byte[] canGo = new byte[26];
    private String finalOrder = "";

    public static void main(String[] args) {

        Task1 task1 = new Task1();

        try {
            task1.instructions = Files.readAllLines(new File("day_7.txt").toPath());

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        task1.fillOrders();
        task1.startSteps();
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

      /*  for (int i = 0; i < byRequired.length; i++) {
            System.out.print((char)(i + 65) + ": ");
            for (char c : byRequired[i]) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
        System.out.println("==========================");

        for (int i = 0; i < byRequiring.length; i++) {
            System.out.print((char)(i + 65) + ": ");
            for (char c : byRequiring[i]) {
                System.out.print(c + " ");
            }
            System.out.println();
        }*/

    }

    private void startSteps() {


        for (int i = 0; i < byRequiring.length; i++) {
            if (byRequiring[i].size() == 0 && canGo[i] == 0) {
                canGo[i] = 1;
            }
        }

        while (finalOrder.length() < 26) {
            freeAvailable();
        }
    }

    private void freeAvailable() {

        int i = pickFirstAlphabetically();
        if (i == -1) {
            startSteps();
        }
        for (int j = 0; j < byRequired[i].size(); j++) {

            byRequiring[byRequired[i].get(j) - 65].remove(Character.valueOf((char) (i + 65)));

            if (byRequiring[byRequired[i].get(j) - 65].size() == 0) {
                canGo[byRequired[i].get(j) - 65] = 1;
            }
        }

        System.out.println(finalOrder);

    }

    private int pickFirstAlphabetically() {

        for (int i = 0; i < canGo.length; i++) {
            if (canGo[i] == 1) {
                finalOrder += (char)(i + 65);
                canGo[i] = 2;
                return i;
            }
        }

        return -1;
    }
}
