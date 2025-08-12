import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Solution5_4 {

    //정수 배열을 정렬해서 반환하는 함수를 완성하세요.
    // 조건1. 정수배열의 길이는 2 이상 10^5 이하입니다.
    // 조건2. 배열의 각 데이터 값은 -100,000 이상 100,000 이하입니다.
    public int[] solution1(int[] arr){
        Arrays.sort(arr); //sort는 원본 배열 자체를 정렬한다.

//        Integer[] boxedArr = Arrays.stream(arr).boxed().toArray(Integer[]::new); // 내림차순
//        Arrays.sort(boxedArr, Collections.reverseOrder());


        return arr;
    }

    //정수 배열을 받아 중복값 제거, 내림차순으로 정렬해서 반환하세요.
    // 조건1. 배열 길이는 2 < x < 1000
    // 조건2. 배열의 각 데이터 값은 -100,000 이상 100,000 이하입니다.
    public int[] solution2(int[] arr){

//        Set<Integer> solSet = Arrays.stream(arr).boxed().collect(Collectors.toSet());
//        Integer[] solList = solSet.toArray(new Integer[solSet.size());
//        Arrays.sort(solList, Collections.reverseOrder());
//        return Arrays.stream(solList).mapToInt(Integer::intValue).toArray();

        Integer[] resultArr = Arrays.stream(arr).boxed().distinct().toArray(Integer[]::new); // 중복 제거
        return Arrays.stream(resultArr).mapToInt(Integer::intValue).toArray(); // mapToInt 언박싱


    }
}
