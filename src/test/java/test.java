import jdk.jfr.Description;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

import java.util.*;

public class test {

    @Test
    @Description("컴퓨터가 랜덤으로 겹치지 않는 3개의 숫자를 뽑아 순서대로 배열에 저장")
    void makeRandNumArray(){
        ArrayList<Integer> numArray = new ArrayList<>();
        while(numArray.size() < 3){
            Random random = new Random();
            // 1~9 난수 생성
            int n = random.nextInt(8) + 1;
            // 배열에 없는 경우 숫자 추가
            if(!numArray.contains(n)) {
                numArray.add(n);
            }
        }
        for (Integer integer : numArray) {
            System.out.println(integer);
        }
    }

    @Test
    @Description("플레이어가 겹치지 않는 3개의 숫자를 입력해 순서대로 배열에 저장")
    void makeUserNumArray() {
        Scanner scanner = new Scanner(System.in);
//        String nStr = scanner.next(); // 3자리 숫자 입력
        String nStr = "243";

        char[] charArray = nStr.toCharArray();
        ArrayList<Integer> userNumArray = new ArrayList<>();

        for (char c : charArray) {
            if(checkDuplicate(charArray, c)){ // 중복인 경우 에러 발생시킴
                throw new IllegalArgumentException();
            }
            else{
                userNumArray.add(Integer.parseInt(String.valueOf(c)));
            }
        }

        for (Integer integer : userNumArray) {
            System.out.println(integer);
        }
//        return userNumArray;
    }

    @Description("플레이어가 입력한 숫자(현 배열)에서 중복된 문자가 있는지 판별")
    private boolean checkDuplicate(char[] charArray, char c) {
        int cnt = 0;
        for (char c1 : charArray) {
            if(c1 == c){cnt++;}
        }
        if(cnt > 1){ return true;}
        else{return false;}
    }

    @Test
    @Description("플레이어가 입력한 숫자에 대한 결과 판별")
    void showResult() {
        ArrayList<Integer> userNumArray = new ArrayList<>(List.of(1, 2, 3));
        ArrayList<Integer> randNumArray = new ArrayList<>(List.of(2, 1, 3));

        // 1. 스트라이크 개수 정상 확인
        assertThat(countStrike(userNumArray, randNumArray)).isEqualTo(1) ;
        // 2. 볼 개수 정상 확인
        assertThat(countBall(userNumArray, randNumArray)).isEqualTo(2) ;
    }

    @Description("볼 개수 계산")
    private static int countBall(ArrayList<Integer> userNumArray, ArrayList<Integer> randNumArray) {
        int ballCnt = 0;
        for(int i = 0; i < userNumArray.size(); i++){
            if(randNumArray.contains(userNumArray.get(i)) && !Objects.equals(randNumArray.get(i), userNumArray.get(i))){
                ballCnt++;
            }
        }
        return ballCnt;
    }

    @Description("스트라이크 개수 계산")
    private static int countStrike(ArrayList<Integer> userNumArray, ArrayList<Integer> randNumArray) {
        int strikeCnt = 0;
        for(int i = 0; i < userNumArray.size(); i++){
            if(Objects.equals(userNumArray.get(i), randNumArray.get(i))){
                strikeCnt++;
            }
        }
        return strikeCnt;
    }

}
