package week10.probs;

class CoachNode {
    String name;
    CoachNode prev;
    CoachNode next;

    CoachNode(String name) {
        this.name = name;
        this.prev = null;
        this.next = null;
    }
}

class Train {
    CoachNode head;
    CoachNode tail;

    Train() {
        this.head = null;
        this.tail = null;
    }

    void addLast(String name) {
        CoachNode newNode = new CoachNode(name);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    boolean remove(String name) {
        CoachNode curr = head;
        while (curr != null) {
            if (curr.name.equals(name)) {

                if (curr == head) {
                    head = curr.next;
                    if (head != null) {
                        head.prev = null;
                    } else {
                        tail = null;
                    }
                } else if (curr == tail) {
                    tail = curr.prev;
                    if (tail != null) {
                        tail.next = null;
                    } else {
                        head = null;
                    }
                } else {
                    curr.prev.next = curr.next;
                    curr.next.prev = curr.prev;
                }
                return true;
            }
            curr = curr.next;
        }
        return false;
    }

    void printForward() {
        StringBuilder sb = new StringBuilder("Forward: ");
        CoachNode curr = head;
        while (curr != null) {
            sb.append(curr.name);
            if (curr.next != null) {
                sb.append(" <-> ");
            }
            curr = curr.next;
        }
        System.out.println(sb.toString());
    }

    void printBackward() {
        StringBuilder sb = new StringBuilder("Backward: ");
        CoachNode curr = tail;
        while (curr != null) {
            sb.append(curr.name);
            if (curr.prev != null) {
                sb.append(" <-> ");
            }
            curr = curr.prev;
        }
        System.out.println(sb.toString());
    }
}

public class q4 {
    public static void main(String[] args) {
        Train train = new Train();
        train.addLast("Engine");
        train.addLast("A1");
        train.addLast("B1");
        train.addLast("B2");
        train.addLast("Guard");

        train.remove("B1");

        train.printForward();
        train.printBackward();
    }
}
