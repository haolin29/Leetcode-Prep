package company.FB;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

import base.Interval;

public class _253_MeetingRoomsII {
    public int minMeetingRooms(Interval[] intervals) {
        if (intervals == null || intervals.length == 0 ) {
            return 0;
        }

        Arrays.sort(intervals, (i1, i2) -> i1.start - i2.start);

        Queue<Interval> minHeap = new PriorityQueue<>((i1, i2) -> i1.end - i2.end);

        minHeap.offer(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            Interval minEnd = minHeap.poll();

            // if current meeting start time >= earliest ending meeting, then can merge
            if (intervals[i].start >= minEnd.end) {
                minEnd.end = intervals[i].end;
            } else {
                minHeap.offer(intervals[i]);
            }

            minHeap.offer(minEnd);
        }

        return minHeap.size();
    }
}
