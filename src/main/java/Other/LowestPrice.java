package Other;/*
Instructions to candidate.
  1) Run this code in the REPL to observe its behaviour. The
   execution entry point is main.
  2) Consider adding some additional tests in doTestsPass().
  3) Implement getLowestPrices() correctly.
  4) If time permits, some possible follow-ups.

Question:
A popular online retailer allows vendors to specify different prices in advance
for the same item throughout the day. We now need to design an algorithm that
helps identify the lowest price for the item at any point of the day.

Assumptions:
1) For the algorithm, assume all vendors are selling the same product
and there is only one product being sold. Given a list that has
vendor information - ( startTime, endTime, price ) of the deal,
return a sorted list with different possible intervals and
the least price of the product during the interval.

2) The interval is inclusive of start and end time.

3) All the 3 values passed by the vendor are integers.
*/

import java.util.*;

public class LowestPrice {
    private class Interval {
        int startTime;
        int endTime;
        int price;

        Interval(int startTime, int endTime, int price) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.price = price;
        }
    }

    static class Point {
        boolean isStart;
        int price;
        int time;

        public Point(boolean isStart, int price, int time) {
            this.isStart = isStart;
            this.price = price;
            this.time = time;
        }
    }

    private List<Interval> getLowestPrices(List<Interval> vendors) {
        List<Point> points = new ArrayList<>();

        for (Interval in : vendors) {
            Point start = new Point(true, in.price, in.startTime);
            Point end = new Point(false, in.price, in.endTime);

            points.add(start);
            points.add(end);
        }

        points.sort((p1, p2) -> p1.time - p2.time);

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        List<Interval> res = new ArrayList<>();
        Integer last = null;
        for (Point point : points) {
            if (last == null) {
                last = point.time;
            }

            if (point.isStart) {
                // has overlap
                if (!pq.isEmpty() && pq.peek() > point.price) {
                    res.add(new Interval(last, point.time, pq.peek()));
                    // new start
                    last = point.time;
                }

                pq.offer(point.price);
            } else {
                // don't need the price anymore
                pq.remove(point.price);
                // last one or new lowest price
                if (pq.isEmpty() || pq.peek() > point.price) {
                    res.add(new Interval(last, point.time, point.price));

                    last = point.time;
                }
            }
        }

        for (Interval in : res) {
            System.out.println(in.startTime);
            System.out.println(in.endTime);
            System.out.println(in.price);
            System.out.println("-----------");
        }

        return res;

    }

    /**
     * Returns true if the tests pass. Otherwise, false.
     */
    private boolean doTestsPass() {
        Interval[] sampleInput = { new Interval(1, 5, 20), new Interval(3, 8, 15), new Interval(7, 10, 8) };
        Interval[] expectedOutput = { new Interval(1, 3, 20), new Interval(3, 7, 15), new Interval(7, 10, 8) };

        List<Interval> inputList = new ArrayList<>(Arrays.asList(sampleInput));
        List<Interval> expectedList = new ArrayList<>(Arrays.asList(expectedOutput));

        return expectedList.equals(getLowestPrices(inputList));
    }

    /**
     * Execution entry point.
     */
    public static void main(String[] args) {
        LowestPrice solution = new LowestPrice();
        if (solution.doTestsPass()) {
            System.out.println("All tests passed");
        } else {
            System.out.println("Tests failed");
        }
    }
}