package company.Snap;


import java.util.PriorityQueue;

// 480
// https://leetcode.com/problems/sliding-window-median/description/

public class SlidingWindowMedian {
    PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>();

    public double[] medianSlidingWindow(int[] nums, int k) {
        double[] results = new double[nums.length - k + 1];

        for (int i = 0; i <= nums.length; i++) {
            if (i >= k) {
                results[i - k] = getMedian();
                remove(nums[i - k]);
            }
            if (i < nums.length) {
                add(nums[i]);
            }
        }

        return results;
    }

    private void add(final int num) {
        if (num < getMedian()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        rebalance();
    }

    private void rebalance() {
        if (maxHeap.size() > minHeap.size()) {
            minHeap.offer(maxHeap.poll());
        }

        if (minHeap.size() - maxHeap.size() > 1) {
            maxHeap.offer(minHeap.poll());
        }
    }

    private void remove(final int num) {
        if (num < getMedian()) {
            maxHeap.remove(num);
        } else {
            minHeap.remove(num);
        }

        rebalance();
    }

    private double getMedian() {
        if (minHeap.isEmpty() && maxHeap.isEmpty()) {
            return 0;
        }

        if (minHeap.size() == maxHeap.size()) {
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        }

        return (double)minHeap.peek();
    }
}
