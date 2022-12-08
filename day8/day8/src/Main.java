import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static int rangerRowLeft(List<List<Integer>> mat, int row, int column, int target) {

        if (mat.get(row).get(column) >= target) {
            return 1;
        }
        if (row == 0 || row == mat.size() - 1) {
            return 1;
        }
        return 1 + rangerRowLeft(mat, row - 1, column, target);
    }

    public static int rangeRowRight(List<List<Integer>> mat, int row, int column, int target) {

        if (mat.get(row).get(column) >= target) {
            return 1;
        }
        if (row == 0 || row == mat.size() - 1) {
            return 1;
        }
        return 1 + rangeRowRight(mat, row + 1, column, target);
    }

    public static int rangeColRight(List<List<Integer>> mat, int row, int column, int target) {
        if (mat.get(row).get(column) >= target) {
            return 1;
        }
        if (column == 0 || column == mat.get(0).size() - 1) {
            return 1;
        }
        return 1 + rangeColRight(mat, row, column + 1, target);
    }

    public static int rangeColLeft(List<List<Integer>> mat, int row, int column, int target) {
        if (mat.get(row).get(column) >= target) {
            return 1;
        }
        if (column == 0 || column == mat.get(0).size() - 1) {
            return 1;
        }
        return 1 + rangeColLeft(mat, row, column - 1, target);
    }

    public static int range(List<List<Integer>> mat, int row, int column) {
        boolean isVisible = false;
        if (row == 0 || column == 0 || row == mat.size() - 1 || column == mat.get(0).size() - 1) {
            return 1;
        }
        int ret = 0;
        int target = mat.get(row).get(column);
        ret = rangerRowLeft(mat, row - 1, column, target) * rangeColLeft(mat, row, column - 1, target) *
                rangeRowRight(mat, row + 1, column, target) * rangeColRight(mat, row, column + 1, target);
        return ret;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("input.txt")));
        List<List<Integer>> mat = new ArrayList<>();
        while (bufferedReader.ready()) {
            mat.add(bufferedReader.readLine().chars().mapToObj(x -> Integer.valueOf(x - 48)).collect(Collectors.toList()));
        }

        int visibleTrees = 0;
        System.out.println(visibleTrees);
        for (int i = 0; i < mat.size(); i++) {
            for (int j = 0; j < mat.get(0).size(); j++) {
                int a = range(mat, i, j);
                if (a > visibleTrees) {
                    visibleTrees = a;
                }
            }
        }
        System.out.println(visibleTrees);

    }
}