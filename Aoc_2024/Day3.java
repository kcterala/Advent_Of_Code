package Aoc_2024;

import utils.AocUtils;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day3 {
    public static void main(String[] args) throws IOException, InterruptedException {
        final List<String> lines = AocUtils.getPuzzleInput(2024, 3);
        final String joinedLine = String.join("", lines);
        sumAllMulsButDont(List.of(joinedLine));
    }

    private static void sumAllMulsButDont(final List<String> lines) {
        long ans = 0;

        final Pattern mulsPattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");;
        final Pattern doPattern = Pattern.compile("do\\(\\)");
        final Pattern dontPattern = Pattern.compile("don't\\(\\)");

        for (final String line : lines) {
            final Map<Integer, List<Long>> mulsIndexes = new HashMap<>();
            final List<Integer> dosIndexes = new ArrayList<>();
            final List<Integer> dontsIndexes = new ArrayList<>();

            final Matcher mulsMatcher = mulsPattern.matcher(line);
            while (mulsMatcher.find()) {
                final long a = Integer.parseInt(mulsMatcher.group(1));
                final long b = Integer.parseInt(mulsMatcher.group(2));
                final int index = mulsMatcher.start();
                mulsIndexes.put(index, List.of(a, b));
            }

            final Matcher dosMatcher = doPattern.matcher(line);
            while (dosMatcher.find()) {
                final int index = dosMatcher.start();
                dosIndexes.add(index);
            }


            final Matcher dontsMatcher = dontPattern.matcher(line);
            while (dontsMatcher.find()) {
                final int index = dontsMatcher.start();
                dontsIndexes.add(index);
            }

            ans += getMulsSum(mulsIndexes, dosIndexes, dontsIndexes);
        }

        System.out.println(ans);

    }

    private static long getMulsSum(final Map<Integer, List<Long>> mulsIndexes,
                                   final List<Integer> dosIndexes,
                                   final List<Integer> dontsIndexes) {
        long ans = 0;
        for (final Map.Entry<Integer, List<Long>> entry : mulsIndexes.entrySet()) {
            final int index = entry.getKey();
            final int mostRecentDo = dosIndexes.stream()
                    .filter(doIndex -> doIndex < index)
                    .max(Integer::compareTo)
                    .orElse(-1);

            final int mostRecentDont = dontsIndexes.stream()
                    .filter(dontIndex -> dontIndex < index)
                    .max(Integer::compareTo)
                    .orElse(-1);

            if (mostRecentDont == -1 || (mostRecentDo > mostRecentDont)) {
                final List<Long> mul = entry.getValue();
                ans += (mul.get(0) * mul.get(1));
            }
        }

        return ans;
    }

    private static void sumAllMuls(final List<String> lines) {
        final Pattern pattern = Pattern.compile("mul\\((\\d{1,3}),(\\d{1,3})\\)");;
        int ans = 0;
        for (final String line: lines) {
            final Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                final int a = Integer.parseInt(matcher.group(1));
                final int b = Integer.parseInt(matcher.group(2));
                ans += a * b;
            }
        }
        System.out.println(ans);
    }


}
