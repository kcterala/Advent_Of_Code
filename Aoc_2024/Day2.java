package Aoc_2024;

import utils.AocUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Day2 {
    public static void main(String[] args) throws IOException, InterruptedException {
        final List<String> lines = AocUtils.getPuzzleInput(2024, 2);
        countSafeReportsWithDampner(lines);
    }


    public static void countSafeReports(final List<String> lines) {
        int count = 0;
        for (final String line: lines) {
            final Integer[] reports = Arrays.stream(line.split(" "))
                    .map(Integer::parseInt)
                    .toArray(Integer[]::new);

            if ((isAllIncreasing(reports) || isAllDecreasing(reports)) && isSafeDistanceBetweenLevels(reports)) {
                count++;
            }
        }

        System.out.println(count);
    }

    public static void countSafeReportsWithDampner(final List<String> lines) {
        int count = 0;
        for (final String line: lines) {
            final Integer[] reports = Arrays.stream(line.split(" "))
                    .map(Integer::parseInt)
                    .toArray(Integer[]::new);

            if ((isAllIncreasing(reports) || isAllDecreasing(reports)) && isSafeDistanceBetweenLevels(reports)) {
                count++;
            } else {
                if (canThisBeSafe(reports)) {
                    count++;
                }
            }

        }

        System.out.println(count);
    }

    private static boolean canThisBeSafe(final Integer[] reports) {
        int size = reports.length;
        for (int i = 0;  i < size; i++) {
            final int currIndex = i;
            final Integer[] arr = IntStream.range(0, size)
                    .filter(index -> index != currIndex)
                    .map(index -> reports[index])
                    .boxed()
                    .toArray(Integer[]::new);

            if ((isAllIncreasing(arr) || isAllDecreasing(arr)) && isSafeDistanceBetweenLevels(arr)){
                return true;
            }
        }

        return false;
    }

    private static boolean isSafeDistanceBetweenLevels(final Integer[] reports) {
        for (int i = 0; i < reports.length - 1; i++) {
            final int distance = Math.abs(reports[i] - reports[i + 1]);
            if (distance < 1 || distance > 3) {
                return false;
            }
        }

        return true;
    }

    public static boolean isAllIncreasing(final Integer[] reports) {
        for (int i = 0; i < reports.length - 1; i++) {
            if (reports[i] >= reports[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static boolean isAllDecreasing(final Integer[] reports) {
        for (int i = 0; i < reports.length - 1; i++) {
            if (reports[i] <= reports[i + 1]) {
                return false;
            }
        }
        return true;
    }
}
