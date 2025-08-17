import java.util.Arrays;

public class Main {
    public static void main(String[] args) {


        Solution5_5 sol = new Solution5_5();

//        int[][] arr1 = {{1,4}, {3,2}, {4,1}};
//        int[][] arr2 = {{3,3}, {3,3}, {3,3}};

        int[][] arr1 = {{2,3,2}, {4,2,4}, {3,1,4}};
        int[][] arr2 = {{5,4,3}, {2,4,1}, {3,1,1}};
        System.out.println(Arrays.deepToString(sol.solution3(arr1, arr2)));
    }
}
