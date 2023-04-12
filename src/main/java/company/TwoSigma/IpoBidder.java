package company.TwoSigma;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class IpoBidder {

    public static void main(String[] args) {
        // {{1, 5, 5, 0}, {2, 7, 8, 1}, {3, 7, 5, 1}, {4, 10, 3, 3}},  3 => 3
        List<List<Integer>> bids1 = Arrays.asList(Arrays.asList(1, 2, 5, 0), Arrays.asList(2, 1, 4, 2), Arrays.asList(3, 5, 4, 6));

        // [[1, 5, 5, 0], [2, 7, 8, 1], [3, 7, 5, 1], [4, 10, 3, 3]], 18))  # [4]
        List<List<Integer>> bids2 = Arrays.asList(Arrays.asList(1, 5, 5, 0), Arrays.asList(2, 7, 8, 1), Arrays.asList(3, 7, 5, 1), Arrays.asList(4, 10, 3, 3));

        // [[1, 5, 5, 0], [2, 7, 8, 1], [3, 7, 5, 1], [4, 10, 3, 3]], 20))  # []
        List<List<Integer>> bids3 = Arrays.asList(Arrays.asList(1, 5, 5, 0), Arrays.asList(2, 7, 8, 1), Arrays.asList(3, 7, 5, 1), Arrays.asList(4, 10, 3, 3));

        IpoBidder testSubject = new IpoBidder();


        System.out.println(testSubject.getUnallottedUsers(bids1, 3).toString());
        System.out.println(testSubject.getUnallottedUsers(bids2, 18).toString());
        System.out.println(testSubject.getUnallottedUsers(bids3, 20).toString());
        System.out.println("------");
        System.out.println(testSubject.ipo(bids1, 3).toString());
        System.out.println(testSubject.ipo(bids2, 18).toString());
        System.out.println(testSubject.ipo(bids3, 20).toString());
    }

//    Output: all bidders without shares, ascending order

    //    先根据price(desc),timestamp(asc) sort bid sorted(bid, key=lambda b: (-bid[2], bid[3])
//    然后用collections.OrderedDict 从高到低group bids; dict: {price: [bids]}
//    接着iterate dict.
//    如果totalShares <= 0， 将dict[price] 的所有id 加入result list
//    else: 如果totalShares>= len(dict[price]), 说明这一组的每个人都能至少获得一份股票， totalShares -= sum(dict[price].shares)
//    如果totalShares < len(dict[price]), 说明 dict[price][totalShares:] 拿不到股票，将这些人加入result list
//    用bisect.insort 来加入result list
    public List<Integer> ipo(List<List<Integer>> bids, int totalShares) {
        // price : num of bids
        // group the bids by price
        Map<Integer, Integer> priceMap = new HashMap<>();

        // populate the price map with same price bid times
        for (List<Integer> bid : bids) {
            int price = bid.get(2);
            priceMap.put(price, priceMap.getOrDefault(price, 0) + 1);
        }

        bids.sort((a, b) -> {
            if (Objects.equals(a.get(2), b.get(2))) {
                // sort by time ascending
                return a.get(3) - b.get(3);
            } else {
                return b.get(2) - a.get(2);
            }
        });

        // start to allocate bidders
        int i = 0;
        int offset = 0;

        while (i < bids.size() && totalShares > 0) {
            int bidNum = priceMap.get(bids.get(i).get(2));
            int shares = 0;
            // count shares needed
            for (int j = 0; j < bidNum; j++) {
                shares += bids.get(i + j).get(1);
            }
            // can't satisfied
            if (shares > totalShares) {
                offset = totalShares;
                break;
            }
            totalShares -= shares;
            i += bidNum;
        }

        int idx = i + offset;
        List<Integer> res = new ArrayList<>();
        for (int j = idx; j < bids.size(); j++) {
            res.add(bids.get(idx + (j - idx)).get(0));
        }

        Collections.sort(res);
        return res;
    }


    private static final int USERID_IDX = 0;
    private static final int SHARES_IDX = 1;
    private static final int PRICE_IDX = 2;
    private static final int TS_IDX = 3;

    public List<Integer> getUnallottedUsers(List<List<Integer>> bids, int totalShares) {
        List<Integer> result = new ArrayList<>();

        if (bids.size() == 0 || totalShares == 0) {
            return result;
        }

        bids.sort((bid1, bid2) -> {
            if (!Objects.equals(bid1.get(PRICE_IDX), bid2.get(PRICE_IDX))) {
                return bid2.get(PRICE_IDX) - bid1.get(PRICE_IDX);
            } else {
                return bid1.get(TS_IDX) - bid2.get(TS_IDX);
            }
        });

        int left = 0;
        while (left < bids.size()) {
            List<Integer> curBid = bids.get(left);
            if (totalShares <= 0) {
                result.add(curBid.get(USERID_IDX));
                left++;
                continue;
            }

            //round robin
            int size = 0;
            int shares = 0;
            int right = left;
            // count same price bidders
            while (right < bids.size()) {
                List<Integer> nextBid = bids.get(right);
                if (nextBid.get(PRICE_IDX) != curBid.get(PRICE_IDX)) {
                    break;
                }

                size++;
                shares += nextBid.get(SHARES_IDX);
                right++;
            }

            //check if cur totalShares satisfying
            if (size <= totalShares) {
                totalShares = Math.max(0, totalShares - shares);
            } else {
                int idx = left + totalShares;

                //users of [idx, right - 1] not allocated
                for (int i = idx; i < right; i++) {
                    result.add(bids.get(i).get(USERID_IDX));
                }
            }
            left = right;
        }

        Collections.sort(result);
        return result;
    }
}

