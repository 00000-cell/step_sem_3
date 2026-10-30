package week10.probs;

class QueueNode {
    int data;
    QueueNode next;

    QueueNode(int data) {
        this.data = data;
        this.next = null;
    }
}

public class q3 {

    public static QueueNode reverse(QueueNode head) {
        QueueNode prev = null;
        QueueNode curr = head;
        QueueNode next = null;

        while (curr != null) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }

    public static void printList(QueueNode head) {
        if (head == null) {
            System.out.println("null");
            return;
        }

        StringBuilder sb = new StringBuilder();
        QueueNode curr = head;
        while (curr != null) {
            sb.append(curr.data).append(" -> ");
            curr = curr.next;
        }
        sb.append("null");
        System.out.println(sb.toString());
    }

    public static QueueNode createList(int[] values) {
        if (values == null || values.length == 0) {
            return null;
        }
        QueueNode head = new QueueNode(values[0]);
        QueueNode curr = head;
        for (int i = 1; i < values.length; i++) {
            curr.next = new QueueNode(values[i]);
            curr = curr.next;
        }
        return head;
    }

    public static void main(String[] args) {

        QueueNode q1 = createList(new int[]{1, 2, 3, 4});
        q1 = reverse(q1);
        printList(q1);

        QueueNode q2 = createList(new int[]{9});
        q2 = reverse(q2);
        printList(q2);
    }
}
