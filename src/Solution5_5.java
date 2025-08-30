import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

public class Solution5_5 {

    // 서로 다른 인덱스에 있는 2개의 수를 뽑아 더해 만들 수 있는 모든 수를 배열에 오름차순으로 담아 반환하세요
    // 조건 1. numbers의 길이는 2 이상 100 이하
    // 조건 2. numbers의 모든 수는 0 이상 100 이하
    public int[] solution1(int[] numbers){
//        List<Integer> sumList = new ArrayList<>();
//
//        for(int i = 0; i < numbers.length; i++){
//            for(int j = i+1; j < numbers.length; j++){
//                if(i == j){
//                    continue;
//                }
//                sumList.add(numbers[i]+ numbers[j]);
//            }
//        }
//        Collections.sort(sumList);
//
//        return sumList.stream().mapToInt(Integer::intValue).distinct().toArray();

        HashSet<Integer> set = new HashSet<>(); // 중복을 피할 수 있다

        for(int i = 0; i < numbers.length-1; i++){ // -1로 마지막에 불필요한 연산을 막음
            for(int j = i+1; j < numbers.length; j++){
                set.add(numbers[i]+ numbers[j]);
            }
        }

        return set.stream().mapToInt(Integer::intValue).sorted().toArray();

    }

    // 수포자가 문제를 찍습니다
    // 1번 수포자 : 1234512345...
    // 2번 수포자 : 212324252123...
    // 3번 수포자 : 3311224455331122...
    //answers가 주어졌을때 누가 가장 많이 맞혔는지 배열에 담아 반환하세요
    // 조건 1 : 시험은 최대 10000문제
    // 조건 2 : 많이 맞힌 사람이 여러명이면 오름차순해서 반환하세요
    public int[] solution2(int[] answer){
//        int[] user1 = {1,2,3,4,5};
//        int[] user2 = {2,1,2,3,2,4,2,5};
//        int[] user3 = {3,3,1,1,2,2,4,4,5,5};
//        Map<Integer, Integer> scoreMap = new LinkedHashMap<>();
//        scoreMap.put(1, 0);
//        scoreMap.put(2, 0);
//        scoreMap.put(3, 0);
//
//        for(int i = 0; i < answer.length; i++){
//            int ans = answer[i];
//            scoreMap.put(1, user1[i % user1.length] == ans ? scoreMap.get(1)+1 : scoreMap.get(1));
//            scoreMap.put(2, user2[i % user2.length] == ans ? scoreMap.get(2)+1 : scoreMap.get(2));
//            scoreMap.put(3, user3[i % user3.length] == ans ? scoreMap.get(3)+1 : scoreMap.get(3));
//        }
//
//        scoreMap = scoreMap.entrySet()
//                .stream()
//                .sorted(Map.Entry.comparingByValue()) // 값 기준 오름차순
//                .collect(Collectors.toMap(
//                        Map.Entry::getKey,
//                        Map.Entry::getValue,
//                        (oldValue, newValue) -> oldValue,
//                        LinkedHashMap::new // 순서 유지
//                ));
//
//        List<Integer> result = new ArrayList<>();
//        for(Map.Entry<Integer, Integer> entry : scoreMap.entrySet()){
//            if(result.isEmpty() && entry.getValue() == 0){
//                return new int[1];
//            }
//
//            if(result.isEmpty()){
//                result.add(entry.getKey());
//                continue;
//            }else if(scoreMap.entrySet().iterator().next().getValue() == entry.getValue()){
//                result.add(entry.getKey());
//                continue;
//            }
//
//            break;
//        }
//
//        return result.stream().mapToInt(Integer::intValue).sorted().toArray();

        //패턴
        int[][] pattern = {
                {1,2,3,4,5},
                {2,1,2,3,2,4,2,5},
                {3,3,1,1,2,2,4,4,5,5}
        };

        //점수 저장
        int[] scores = new int[3];

        for(int i = 0; i < answer.length; i++){
            for(int j = 0; j < pattern.length; j++){
                if(answer[i] == pattern[j][i % pattern[j].length]){
                    scores[j]++;
                }
            }
        }

        //가장 높은 점수 저장
        int maxScore = Arrays.stream(scores).max().getAsInt();

        ArrayList<Integer> resultList = new ArrayList<>();

        for(int i = 0; i < scores.length; i++){
            if(scores[i] == maxScore){
                resultList.add(i+1);
            }
        }

        return resultList.stream().mapToInt(Integer::intValue).toArray(); // 어차피 scores에서 차례로 올라가므로 정렬 필요 없음

    }

    //5. 배열 문제 5번
    // 2차원 배열 arr1, arr2를 입력받아 arr1에 arr2를 곱한 결과를 반환하세요
    // 조건1. 배열 행과 열의 길이는 2 이상 100 이하
    // 조건2. 행렬의 데이터는 -10이상 20이하
    public int[][] solution3(int[][] arr1, int[][] arr2) {
        //1. 행렬 길이 ex. 3x2 2x3 ? >> 3x3
        int rowNum = arr1.length;
        int colNum = arr2[0].length;

        int[][] result = new int[rowNum][colNum];


        //2. 곱셈
        for(int i = 0; i < rowNum; i++){

            int[] arr1Row = arr1[i];
            for(int j = 0; j < colNum; j++){

                for(int k = 0; k < arr2.length; k++){
                    result[i][j] += arr1Row[k] * arr2[k][j];
                }
            }
        }

        return result;
    }

    //5. 배열 문제 6번
    // 실패율을 반환하세요 (스테이지에 도달했으나 아직 클리어하지 못한 플레이어 수 / 스테이지에 도달한 플레이어 수)
    // 조건1. 스테이지 개수 N은 1~500
    // 조건2. 스테이지의 길이는 1~200000
    // 조건3. 스테이지에는 1 ~ N+1 의 자연수가 있음
    // 조건4. 실패율이 높은 스테이지 부터 내림차순으로 번호가 담겨진 배열을 반환 (같은 실패율이면 작은 번호의 스테이지가 먼저 오게)
    public int[] solution4(int N, int[] stages){

//        // 실패율을 담을 배열
//        int stagesCount = stages.length;
//        Map<Integer, Double> failStagesMap = new HashMap<>();
//
//        stages = Arrays.stream(stages).sorted().toArray();
//        for(int i = 1; i < N+1; i++){
//            failStagesMap.put(i, 0.0);
//        }
//
//        for (int stage : stages) {
//            if(stage > N){
//                continue;
//            }
//
//            failStagesMap.put(stage, failStagesMap.get(stage) + 1);
//        }
//
//
//        for(Map.Entry<Integer, Double> failStage : failStagesMap.entrySet()){
//
//            int failCount = failStage.getValue().intValue();
//            failStagesMap.put(failStage.getKey(), failStage.getValue() / stagesCount);
//            stagesCount -= failCount;
//        }
//
//        return failStagesMap.entrySet().stream().sorted((o1, o2) ->
//                o1.getValue().equals(o2.getValue()) ? Integer.compare(o1.getKey(),
//                        o2.getKey()) : Double.compare(o2.getValue(),
//                        o1.getValue())).mapToInt(Map.Entry::getKey).toArray();

        // 풀이
        //1. 도전자 수 구하기
        int[] challenger = new int[N+2]; //배열은 0부터 시작하므로 N+1까지 담기위한 전략
        for(int stage : stages){
            challenger[stage] += 1;
        }

        //2. 스테이지 별 실패한 사용자 수
        HashMap<Integer, Double> fails = new HashMap<>();
        double total = stages.length;

        for(int i = 1; i <= N; i++){
            if(challenger[i] == 0){
                fails.put(i, 0.0);
            }else {
                fails.put(i, challenger[i] / total);
                total -= challenger[i];
            }
        }

        return fails.entrySet().stream().sorted((o1, o2) ->
                o1.getValue().equals(o2.getValue()) ? Integer.compare(o1.getKey(),
                        o2.getKey()) : Double.compare(o2.getValue(),
                        o1.getValue())).mapToInt(Map.Entry::getKey).toArray();

        //stages를 정렬하는것보다 카운팅 배열을 사용하는 편이 더 효율적임

        //hashmap은 정렬 보장이 안된다?
        //자바에서 Hashmap, Hashset은 key 로 정수형을 사용할 때 0~65535까자는 키값과 동일함
        //마치 오름차순을 보장하는 것처럼 버킷에 저장되는 구조임 하지만 65535를 넘어가면 보장되지 않음
        //이러한 구조가 자바 17버전까지는 유효하기 때문에 여기서는 스테이지를 정렬하는 로직이 없는것!
        //정렬하는 로직이 있어도 시간복잡도에는 큰 영향이 없다.
    }

    // 좌표에서 한번도 지나가지 않은 길을 지난 횟수 구하기 (좌표가 아니라!!)
    // 조건 1. dirs는 'U', 'D', 'R', 'L'만 주어짐
    // 조건 2. dirs의 길이는 500 이하의 자연수
    // 조건 3. -5, 5를 벗어나면 무시
    private static final HashMap<Character, int[]> location = new HashMap<>(); // 경로 기록용

    public int solution5(String dirs){

        HashMap<Character, int[]> location = new HashMap<>();

        location.put('U', new int[]{0, 1});
        location.put('D', new int[]{0, -1});
        location.put('L', new int[]{-1, 0});
        location.put('R', new int[]{1, 0});

        int x = 0;
        int y = 0;

        Set<String> answer = new HashSet<>(); // 중복 막기

        for(int i = 0; i < dirs.length(); i++){
            int[] offset = location.get(dirs.charAt(i));
            int horizontal = x + offset[0];
            int vertical = y + offset[1];

            if(horizontal < 6 && horizontal > -6 && vertical < 6 && vertical > -6){
                answer.add(x + " " + y + " " + horizontal + " " + vertical);
                answer.add(horizontal + " " + vertical + " " + x + " " + y); //길이기 때문에 반대 방향도 얻어야 함

                x = horizontal;
                y = vertical;
            }
        }

        return answer.size() / 2; //양방향에서 단방향으로 계산하기

    }
}
