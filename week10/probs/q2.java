package week10.probs;

class OrderNode {
    int data;
    OrderNode next;

    OrderNode(int data) {
        this.data = data;
        this.next = null;
    }
}

public class q2 {

    public static OrderNode removeAll(OrderNode head, int code) {

        while (head != null && head.data == code) {
            head = head.next;
        }

        if (head == null) {
            return null;
        }

        OrderNode curr = head;
        while (curr != null && curr.next != null) {
            if (curr.next.data == code) {
                curr.next = curr.next.next;
            } else {
                curr = curr.next;
            }
        }

        return head;
    }

    public static void printList(OrderNode head) {
        if (head == null) {
            System.out.println("null");
            return;
        }

        StringBuilder sb = new StringBuilder();
        OrderNode curr = head;
        while (curr != null) {
            sb.append(curr.data).append(" -> ");
            curr = curr.next;
        }
        sb.append("null");
        System.out.println(sb.toString());
    }

    public static OrderNode createList(int[] values) {
        if (values == null || values.length == 0) {
            return null;
        }
        OrderNode head = new OrderNode(values[0]);
        OrderNode curr = head;
        for (int i = 1; i < values.length; i++) {
            curr.next = new OrderNode(values[i]);
            curr = curr.next;
        }
        return head;
    }

    public static void main(String[] args) {

        OrderNode list1 = createList(new int[]{5, 3, 5, 8, 5});
        list1 = removeAll(list1, 5);
        printList(list1);

        OrderNode list2 = createList(new int[]{5, 5, 5});
        list2 = removeAll(list2, 5);
        printList(list2);
    }
}
