package onboarding;

import java.util.*;
import java.util.stream.Collectors;

public class Problem7 {
    private static Map<String, Integer> friendCandidate = new HashMap<>();
    private static Set<String> oldFriend = new HashSet<>();
    private static List<List<String>> friendData;
    public static List<String> solution(String user, List<List<String>> friends, List<String> visitors) {
        List<String> answer = Collections.emptyList();
        answer = getRecomendedFriends(user,friends,visitors);
        return answer;
    }

    private static List<String> getRecomendedFriends(String user, List<List<String>> friends, List<String> visitors){
        oldFriend.add(user);
        friendData=friends;
        List<String> olderFriends = getFriendOfUser(user);
        classifyOlderFriend(olderFriends);
        increaseFriendWeightByOldFriends(olderFriends);
        increaseFriendWeightByVisted(visitors);
        return getMostSuitableFriend(5);
    }

    private static List<String> getFriendOfUser(String user) {
        List<String> friendList = new ArrayList<>();
        for(int i=0; i<friendData.size(); i++){
            if(friendData.get(i).contains(user)){
                int friendIndex = (friendData.get(i).indexOf(user)+1)%2;
                friendList.add(friendData.get(i).get(friendIndex));
            }
        }
        return friendList;
    }

    private static void classifyOlderFriend(List<String> oldfriends){
        oldfriends.stream().forEach(a->oldFriend.add(a));
    }

    private static void increaseFriendWeightByOldFriends(List<String> friend){
        for(int i=0; i<friend.size(); i++){
            List<String> friendList = getFriendOfUser(friend.get(i));
            increaseWeightByRelationship(friendList);
        }
    }

    private static void increaseWeightByRelationship(List<String> friends){
        for(int i=0; i<friends.size(); i++){
            if(oldFriend.contains(friends.get(i))){
                continue;
            }
            increaseWeight(friends.get(i),10);
        }
    }

    private static void increaseWeight(String candidate, Integer score){
        if(friendCandidate.containsKey(candidate)){
            friendCandidate.replace(candidate, friendCandidate.get(candidate)+score);
            return;
        }
        friendCandidate.put(candidate,score);
    }

    private static void increaseFriendWeightByVisted(List<String> visitors){
        for(int i=0; i<visitors.size(); i++){
            if(oldFriend.contains(visitors.get(i))){
                continue;
            }
            increaseWeight(visitors.get(i),1);
        }
    }

    private static List<String> getMostSuitableFriend(int count){
        List<String> keySet =  new ArrayList<>(friendCandidate.keySet());
        keySet.sort((o1, o2) -> friendCandidate.get(o2).compareTo(friendCandidate.get(o1)));
        return keySet.stream().limit(count).collect(Collectors.toList());
    }

}
