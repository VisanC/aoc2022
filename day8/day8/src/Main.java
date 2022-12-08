import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    public static int doesItBlockRowLeft(List<List<Integer>> mat, int row, int column, int target) {

        if (mat.get(row).get(column) >= target) {
            return 1;
        }
        if (row == 0 || row == mat.size() - 1) {
            return 1;
        }
        return 1+doesItBlockRowLeft(mat, row - 1, column, target);
    }

    public static int doesItBlockRowRight(List<List<Integer>> mat, int row, int column, int target) {

        if (mat.get(row).get(column) >= target) {
            return 1;
        }
        if (row == 0 || row == mat.size() - 1) {
            return 1;
        }
        return  1+doesItBlockRowRight(mat, row + 1, column, target);
    }

    public static int doesItBlockColRight(List<List<Integer>> mat, int row, int column, int target) {
        if (mat.get(row).get(column) >= target) {
            return 1;
        }
        if (column == 0 || column == mat.get(0).size() - 1) {
            return 1;
        }
        return 1+doesItBlockColRight(mat, row, column + 1, target);
    }
    public static int doesItBlockColLeft(List<List<Integer>> mat, int row, int column, int target) {
        if ( mat.get(row).get(column) >= target) {
            return 1;
        }
        if (column == 0 || column == mat.get(0).size() - 1) {
            return 1;
        }
        return 1+doesItBlockColLeft(mat, row, column - 1, target);
    }

    public static int isVisible(List<List<Integer>> mat, int row, int column) {
        boolean isVisible = false;
        if (row == 0 || column == 0 || row == mat.size() - 1 || column == mat.get(0).size() - 1) {
            return 1;
        }
        int ret = 0;
        int target = mat.get(row).get(column);
        ret= doesItBlockRowLeft(mat,row-1,column,target) * doesItBlockColLeft(mat,row,column-1,target)*
                doesItBlockRowRight(mat,row+1,column,target) * doesItBlockColRight(mat,row,column+1,target);
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
                int a = isVisible(mat, i, j);
                if(a>visibleTrees){
                    visibleTrees=a;
                }
                if (a == 0)
                    System.out.println(a + "  coords " + i + " " + j + " value " + mat.get(i).get(j));
            }
        }
        System.out.println(visibleTrees);

    }
}