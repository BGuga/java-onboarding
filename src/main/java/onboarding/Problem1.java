package onboarding;

import java.util.List;

class Problem1 {
    public static int solution(List<Integer> pobi, List<Integer> crong) {
        int answer = Integer.MAX_VALUE;
        answer = getGameResult(pobi, crong);
        return answer;
    }

    private static int getGameResult(List<Integer> userA, List<Integer> userB){
        int userAPoint = getMaxPoint(userA);
        int userBPoint = getMaxPoint(userB);


        if(userAPoint == -1 || userBPoint == -1){
            return -1;
        } else if (userAPoint>userBPoint) {
            return 1;
        } else if (userAPoint<userBPoint) {
            return 2;
        } else if (userAPoint == userBPoint) {
            return 0;
        }
        return -1;
    }

    private static int getMaxPoint(List<Integer> user){
        int leftMaxPoint;
        int rightMaxPoint;


        if(!pageValidation(user)){
            return -1;
        }

    }

    private static boolean pageValidation(List<Integer> user){
        if(user.size()!=2) {
            return false;
        }
        int leftPage = user.get(0);
        int rightPage = user.get(1);

        if(leftPage != rightPage-1){
            return false;
        }

        if(leftPage==1 || rightPage==400){
            return false;
        }

        if(leftPage<1 || rightPage>400){
            return false;
        }
        return true;
    }

}