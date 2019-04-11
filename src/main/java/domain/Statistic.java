package domain;

import java.util.List;

public class Statistic {

    private int resultOfRank[];
    private List<Rank> rankList;
    private double reward;
    private int money;

    Statistic(List<Rank> rankList, int money) {
        resultOfRank = new int[8];
        this.rankList = rankList;
        this.money = money;
    }

    public void print() {
        System.out.println(Message.STATISTIC_MESSAGE);
        statistic();
        for (int i = 3; i <= 7; i++) {
            Rank rank = Rank.valueOf((i >= 6 ? (i - 1) : i), (i == 6));                               /* i가 6일때는 맞은개수가 5개이고 i가 7일때는 맞은개수가 6개이기 때문에 i-1을 실행한다.*/
            System.out.format(i == 6 ? Message.MATCH_MESSAGE_BONUS : Message.MATCH_MESSAGE, (i >= 6 ? i - 1 : i), rank.getWinningMoney(), resultOfRank[i]).toString();                  /* i-1을 하는이유는 위와 같다.*/
            reward += rank.getWinningMoney() * resultOfRank[i];
        }
        printReward();
    }

    private void statistic() {
        for (Rank rank : rankList) {
            CountOfRank(rank);
        }
    }

    private void CountOfRank(Rank rank) {
        if (Rank.SECOND == rank || Rank.FIRST == rank) {
            resultOfRank[rank.getCountOfMatch() + 1]++;
            return;
        }
        resultOfRank[rank.getCountOfMatch()]++;
    }

    private void printReward() {
        System.out.format(Message.GAIN_RATE_MESSAGE, (reward / money));
    }
}
