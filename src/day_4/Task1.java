package day_4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Federica
 * federica.comuni0002@stud.hkr.se
 * on 04/12/2018.
 */
@SuppressWarnings("Duplicates")
public class Task1 {

    private ArrayList<String> strings = new ArrayList<>();
    private boolean[][] sleeping = new boolean[260][60];
    private ArrayList<String> guards = new ArrayList<>();
    private int[][] guardDates = new int[23][20];
    private int[] datesIndexes = new int[260];

    public static void main(String[] args) {

        Task1 task1 = new Task1();

        try (Scanner input = new Scanner(new FileInputStream("day_4.txt"))) {

            while (input.hasNextLine()) {
                task1.strings.add(input.nextLine());
            }

        } catch (FileNotFoundException fe) {
            fe.printStackTrace();
        }

        task1.sortStrings();
    }

    private void sortStrings() {

        Collections.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date date1 = new Date();
                Date date2 = new Date();
                try {
                    date1 = dateFormat.parse(o1.substring(1, 17));
                    date2 = dateFormat.parse(o2.substring(1, 17));

                } catch (ParseException pe) {
                    pe.printStackTrace();
                }


                return date1.compareTo(date2);
            }
        });

        for (int i = 0; i < 23; i++) {
            for (int j = 0; j < 20; j++) {
                guardDates[i][j] = -1;
            }

        }
        divideIntoDates();
        divideIntoGuards();

        matchGuardsWithDates();
        findSleepingSchedule();
    }

    private void divideIntoDates() {

        int counter = 0;
        for (int i = 0; i < strings.size(); i++) {

            if (strings.get(i).substring(19, 24).equals("Guard")) {

                if (findDateRightString(strings.get(i))) {
                    datesIndexes[counter++] = i + 1;
                } else {
                    datesIndexes[counter++] = i;
                }
            }
        }

        //System.out.println(Arrays.toString(datesIndexes));
    }

    private void divideIntoGuards() {

        for (int i = 0; i < strings.size(); i++) {

            if (strings.get(i).substring(19, 24).equals("Guard")) {

                String id = findGuardId(strings.get(i));
                if (!guards.contains(id)) {
                    guards.add(id);
                }
                //guards.add(strings.get(i).substring(26, strings.get(i).charAt(29) == ' '? 29 : 30));
            }
        }
    }

    private void matchGuardsWithDates() {


        for (int i = 0; i < guards.size(); i++) {
            int dateCounter = 0;
            for (int j = 0; j < strings.size(); j++) {
                if (strings.get(j).substring(19, 24).equals("Guard")) {
                    if (findGuardId(strings.get(j)).equals(guards.get(i))) {
                        if (findDateRightString(strings.get(j))) {
                            guardDates[i][dateCounter++] = j + 1;
                        } else {
                            guardDates[i][dateCounter++] = j;
                        }
                    }
                }
            }
        }

        /*for (int[] i : guardDates) {
            for (int ii : i) {
                System.out.print(ii + " ");
            }
            System.out.println();
        }*/
    }

    private String findGuardId(String wholeString) {

        String id = "";

        if (wholeString.substring(19, 24).equals("Guard")) {
            if (wholeString.charAt(29) == ' ') {
                id = wholeString.substring(26, 29);
            } else {
                id = wholeString.substring(26, 30);
            }
        }

        return id;
    }

    private boolean findDateRightString(String wholeString) {

        if (wholeString.substring(19, 24).equals("Guard")) {
            if (wholeString.substring(12, 14).equals("23")) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    //We have 260 dates
    /*private void findDateNumber() {

        int dateCounter = 0;

        for (int i = 0; i < strings.size(); i++) {

            if (strings.get(i).substring(19, 24).equals("Guard")) {
                dateCounter++;
            }
        }

        System.out.println(dateCounter);
    }*/

    private void findSleepingSchedule() {

        ArrayList<Integer> timeStamps = new ArrayList<>();
        int dateCounter = 0;

        int startPoint;
        int endPoint;
        for (int i = 0; i < strings.size(); i++) {

            timeStamps.clear();
            if (strings.get(i).substring(19, 24).equals("Guard")) {

                startPoint = i + 1;


                int counter = startPoint;
                while (counter < 1002 && !strings.get(counter).substring(19, 24).equals("Guard")) {
                    counter++;
                }
                endPoint = counter;

                for (int j = startPoint; j < endPoint; j++) {
                    timeStamps.add(Integer.parseInt(strings.get(j).substring(15, 17)));
                }


                if (timeStamps.size() > 0) {
                    for (int j = 0; j < timeStamps.size() - 1; j++) {
                        for (int k = timeStamps.get(j); k < timeStamps.get(j + 1); k++) {
                            if (j % 2 == 1) {
                                sleeping[dateCounter][k] = true;
                            } else {
                                sleeping[dateCounter][k] = false;
                            }
                        }

                    }
                    for (int j = 0; j < timeStamps.get(0); j++) {
                        sleeping[dateCounter][j] = true;
                    }
                    for (int j = timeStamps.get(timeStamps.size() - 1); j < 60; j++) {
                        if (timeStamps.size() % 2 == 1) {
                            sleeping[dateCounter][j] = false;
                        } else {
                            sleeping[dateCounter][j] = true;
                        }
                    }
                } else {
                    for (int j = 0; j < 60; j++) {
                        sleeping[dateCounter][j] = true;
                    }
                }
                dateCounter++;
            }
        }

        findGuardsMinutes();
    }

    private void findGuardsMinutes() {

        int guardIndex = 0;
        int tmp = 0;
        int guardCounter;
        for (int i = 0; i < guards.size(); i++) {

            guardCounter = 0;
            for (int j = 0; j < guardDates[i].length; j++) {

                if (guardDates[i][j] != -1) {
                    int dateI = 0;
                    for (int k = 0; k < datesIndexes.length; k++) {
                        if(datesIndexes[k] == guardDates[i][j]) {
                            dateI = k;
                            break;
                        }
                    }

                    for (int k = 0; k < sleeping[dateI].length; k++) {
                        if (!sleeping[dateI][k]) {
                            guardCounter++;
                        }
                    }

                }
            }
            if (guardCounter > tmp) {
                tmp = guardCounter;
                guardIndex = i;
            }


        }

        countMostAsleepMinute(guardIndex);
    }

    private void countMostAsleepMinute(int guardIndex) {

        int[] rightGuardDates = Arrays.copyOf(guardDates[guardIndex], 16); //hardcoded: be careful
        int minute = 0;
        int minuteCounter = 0;
        int tmp = 0;
        for (int j = 0; j < rightGuardDates.length; j++) {

            for (int k = 0; k < datesIndexes.length; k++) {
                if (datesIndexes[k] == rightGuardDates[j]) {
                    rightGuardDates[j] = k;
                    break;
                }
            }
        }

        for (int i = 0; i < 60; i++) {

            minuteCounter = 0;
            for (int j = 0; j < rightGuardDates.length; j++) {

                if (!sleeping[rightGuardDates[j]][i]) {
                    minuteCounter++;
                }
            }

            if (minuteCounter > tmp) {
                tmp = minuteCounter;
                minute = i;
            }


        }

        System.out.println("The answer is: " + Integer.parseInt(guards.get(guardIndex)) * minute);
        printGuardsGraphically();

    }

    private void printGuardsGraphically() {

        for (int i = 0; i < guards.size(); i++) {
            for (int j = 0; j < guardDates[i].length; j++) {

                if (guardDates[i][j] != -1) {

                    int dateIndex = 0;
                    for (int k = 0; k < datesIndexes.length; k++) {
                        if (datesIndexes[k] == guardDates[i][j]) {
                            dateIndex = k;
                            break;
                        }
                    }

                    for (int l = 0; l < sleeping[dateIndex].length; l++) {
                        if (sleeping[dateIndex][l]) {
                            System.out.print(".");
                        } else {
                            System.out.print("#");
                        }
                    }
                    System.out.println();
                }
            }
            System.out.println(guards.get(i));
            System.out.println();
            System.out.println();
            System.out.println();

        }
        System.out.println("The answer is: 743 * 32 = " + 743 * 32);
    }
}
