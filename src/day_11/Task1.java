package day_11;


/**
 * Created by Federica
 * federica.comuni0002@stud.hkr.se
 * on 21/12/2018.
 */
@SuppressWarnings("Duplicates")
public class Task1 {

    private final int SERIAL_NUMBER = 5177;

    public static void main(String[] args) {
        Task1 task1 = new Task1();

        task1.completeTask();
    }

    private void completeTask() {

        int maxPowerLevel = 0;
        int x = 1;
        int y = 1;

        for (int i = 1; i <= 298; i++) {
            for (int j = 1; j <= 298; j++) {
                int totalSquarePower = 0;

                for (int k = i; k < i + 3; k++) {
                    for (int l = j; l < j + 3; l++) {
                        int rackID = calculateRackID(l);
                        int powerLevel = calculatePowerLevel(rackID, k);
                        int number = addSerialNumber(powerLevel);
                        int numberAndRackID = multiplyWithRackID(number, rackID);
                        int hundreds = returnHundredsDigit(numberAndRackID);
                        int finalPowerLevel = subtractFive(hundreds);

                        totalSquarePower += finalPowerLevel;
                    }
                }
                if (totalSquarePower > maxPowerLevel) {
                    maxPowerLevel = totalSquarePower;
                    x = j;
                    y = i;
                }
            }
        }

        System.out.println(maxPowerLevel);
        System.out.println(x);
        System.out.println(y);
    }

    private int calculateRackID(int x) {

        return x + 10;
    }

    private int calculatePowerLevel(int rackID, int y) {

        return rackID * y;
    }

    private int addSerialNumber(int input) {

        return input + SERIAL_NUMBER;
    }

    private int multiplyWithRackID(int number, int rackID) {

        return number * rackID;
    }

    private int returnHundredsDigit(int number) {

        int hundred = 0;

        if (number >= 100 && number < 1000) {
            hundred = number / 100;
        } else if (number >= 1000) {
            number /= 100;
            hundred = number % 10;
        }
        return hundred;
    }

    private int subtractFive(int number) {

        return number - 5;
    }

}
