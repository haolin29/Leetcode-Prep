package airbnb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankSystem {
    // 要求实现
    // deposit(acc_id, amount, ts),
    // withdraw(acc_id, amount, ts),
    // current(acc_id),
    // balance(acc_id, start_ts, end_ts) （
    // 返回从start (exclusive)到 end (inclusive)的balance diff）。
    // 前三个时间O(1), 最后一个O(logN)。、
    // 之后口头讨论时候突然想到直接hashmap+timestamp list，依次添加，求balance二分搜索即可，不过没时间了。

    class Transaction {
        int amount, balance, time;

        public Transaction(int amount, int balance, int time) {
            this.amount = amount;
            this.balance = balance;
            this.time = time;
        }
    }

    private Map<Integer, List<Transaction>> map = new HashMap<>();

    public boolean deposit(int userId, int amount, int ts) {
        List<Transaction> list = map.getOrDefault(userId, new ArrayList<>());
        int balance = list.size() == 0 ? 0 : list.get(list.size() - 1).balance;

        if (balance + amount < 0) return false;
        list.add(new Transaction(amount, amount + balance, ts));
        map.put(userId, list);
        return true;
    }

    public boolean withdraw(int userId, int amount, int ts) {
        return deposit(userId, -amount, ts);
    }

    public Integer current(int userId) {
        if (map.containsKey(userId)) {
            return map.get(userId).get(map.get(userId).size() - 1).balance;
        }
        return null;
    }

    // start exclusive, end inclusive
    public Integer balance(int userId, int startTs, int endTs) {
        if (!map.containsKey(userId)) return null;

        List<Transaction> list = map.get(userId);
        return findRightBound(list, endTs) - findLeftBound(list, startTs);
    }

    private Integer findLeftBound(final List<Transaction> list, final int target) {
        int start = 0;
        int end = list.size() - 1;

        while( start < end) {
            int mid = start + (end - start) / 2;

            if (list.get(mid).time > target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return end == 0 ? 0 : list.get(end - 1).balance;
    }

    private Integer findRightBound(final List<Transaction> list, final int target) {
        int start = 0;
        int end = list.size() - 1;

        while( start < end) {
            int mid = start + (end - start) / 2;

            if (list.get(mid).time >= target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }

        return end == 0 ? 0 : list.get(end - 1).balance;
    }

}
