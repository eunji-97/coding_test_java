import java.util.Arrays;

public class Main {
    public static void main(String[] args) {


        Solution5_5 sol = new Solution5_5();

//        int[][] arr1 = {{1,4}, {3,2}, {4,1}};
//        int[][] arr2 = {{3,3}, {3,3}, {3,3}};

        int N = 5;
        int[] stages = {2,1,2,6,2,4,3,3};
        System.out.println(Arrays.toString(sol.solution4(N, stages)));
    }
}
