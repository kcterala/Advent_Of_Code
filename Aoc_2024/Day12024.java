package Aoc_2024;

import utils.AocUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class Day12024 {
    public static void main(String[] args) throws IOException, InterruptedException {
        final List<String> lines = AocUtils.getPuzzleInput(2024, 1);
        final ArrayList<Integer> list1 = new ArrayList<>();
        final ArrayList<Integer> list2 = new ArrayList<>();

        for (final String line : lines) {
            final String[] values = line.split("   ");
            list1.add(Integer.parseInt(values[0]));
            list2.add(Integer.parseInt(values[1]));
        }
        part1(list1, list2);
    }

    public static void part1(final ArrayList<Integer> list1,
                             final ArrayList<Integer> list2) throws IOException {
        int ans = 0;
        Collections.sort(list1);
        Collections.sort(list2);

        for (int i = 0; i < list1.size(); i++) {
            ans += Math.abs(list1.get(i) - list2.get(i));
        }
        System.out.println(ans);
    }

    public static void part2(final ArrayList<Integer> list1,
                             final ArrayList<Integer> list2) {
        int ans = 0;
        for (final Integer integer : list1) {
            int count = 0;
            for (final Integer value : list2) {
                if (Objects.equals(integer, value)) {
                    count++;
                }
            }

            ans += (count * integer);
        }
        System.out.println(ans);
    }
}
