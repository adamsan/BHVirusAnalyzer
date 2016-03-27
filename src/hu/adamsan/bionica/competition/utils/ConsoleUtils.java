package hu.adamsan.bionica.competition.utils;

public class ConsoleUtils {
    public static void printlnSlow(String str) {
        int waitingTimeMS = calculateSpeed(str.length());
        for (int i = 0; i < str.length(); i++) {
            System.out.print(str.charAt(i));
            waitIfNotTheSameAsPreviousChar(str, i, i - 1, waitingTimeMS);
        }
        System.out.println();
    }

    private static void waitIfNotTheSameAsPreviousChar(String str, int currentIndex, int previousIndex, int waitingTimeMS) {
        if (previousIndex <= 0 || str.charAt(currentIndex) != str.charAt(previousIndex)) {
            try {
                Thread.sleep(waitingTimeMS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static int calculateSpeed(int length) {
        int waitingTimeMS = 30;
        if (length < 30)
            return waitingTimeMS;
        if (length < 80) {
            return waitingTimeMS / 4;
        }
        if (length < 120) {
            return waitingTimeMS / 6;
        }
        return waitingTimeMS / 10;
    }
}
