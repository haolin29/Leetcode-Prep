package linkedlist;

import base.ListNode;

public class _21_MergeTwoSortedLists {

    // Merge the two lists in a one sorted list. The list should be made by splicing together the nodes of the first two lists.
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode p1 = list1;
        ListNode p2 = list2;

        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (p1 != null && p2 != null) {
            if (p1.val > p2.val) {
                cur.next = p2;
                p2 = p2.next;
            } else {
                cur.next = p1;
                p1= p1.next;
            }
            cur = cur.next;
        }

        if (p1 != null) {
            cur.next = p1;
        }

        if (p2 != null) {
            cur.next = p2;
        }

        return dummy.next;
    }
}
