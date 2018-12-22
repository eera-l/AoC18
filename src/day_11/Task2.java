package day_11;

/**
 * Created by Federica
 * federica.comuni0002@stud.hkr.se
 * on 21/12/2018.
 */
@SuppressWarnings("Duplicates")
public class Task2 {

    private final int SERIAL_NUMBER = 5177;

    public static void main(String[] args) {
        Task2 task2 = new Task2();


        task2.computeSummedAreaTable();
    }

    private void computeSummedAreaTable() {
        int[][] matrix = new int[300][300];
        int[][] auxMatrix = new int[300][300];

        //Populate the matrix with each fuel cell's power level
        for (int i = 0; i < 300; i++) {
            for (int j = 0; j < 300; j++) {
                int rackID = calculateRackID(j + 1);
                int pLevel = calculatePowerLevel(rackID, i + 1);
                int numSN = addSerialNumber(pLevel);
                int numMul = multiplyWithRackID(numSN, rackID);
                int hundred = returnHundredsDigit(numMul);
                matrix[i][j] = subtractFive(hundred);
            }
        }

        processMatrices(matrix, auxMatrix);

        int maxPower = 0;
        int x = 0;
        int y = 0;
        int side = 0;

        for (int i = 1; i <= 300; i++) {
            int finalPower = 0;
            for (int j = 0; j < 300; j++) {
                for (int k = 0; k < 300; k++) {

                    if (k + (i - 1) < 300 && j + (i - 1) < 300) {
                        finalPower = sumSquare(auxMatrix, j, k, j + (i - 1), k + (i - 1));
                        if (finalPower > maxPower) {
                            maxPower = finalPower;
                            x = k + 1;
                            y = j + 1;
                            side = i;
                        }
                    }
                }
            }
        }

        System.out.println(maxPower);
        System.out.println(x);
        System.out.println(y);
        System.out.println(side);

    }

    //Solved using summed area table algorithm
    private void processMatrices(int[][] matrix, int[][] auxMatrix) {

        for (int i = 0; i < matrix.length; i++) {
            auxMatrix[0][i] = matrix[0][i];
        }

        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                auxMatrix[i][j] = matrix[i][j] + auxMatrix[i - 1][j];
            }
        }

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 1; j < matrix.length; j++) {
                auxMatrix[i][j] += auxMatrix[i][j - 1];
            }
        }
    }

    private int sumSquare(int[][] auxMatrix, int tlr, int tlc, int brr, int brc) {

        int result = auxMatrix[brr][brc];

        if (tlr > 0) {
            result = result - auxMatrix[tlr - 1][brc];
        }

        if (tlc > 0) {
            result = result - auxMatrix[brr][tlc - 1];
        }

        if (tlr > 0 && tlc > 0) {
            result = result + auxMatrix[tlr - 1][tlc - 1];
        }

        return result;
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
