package week10.probs;

class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class SinglyLinkedList {
    Node head;
    int size;

    SinglyLinkedList() {
        this.head = null;
        this.size = 0;
    }

    void addFirst(int token) {
        Node newNode = new Node(token);
        newNode.next = head;
        head = newNode;
        size++;
    }

    void addLast(int token) {
        Node newNode = new Node(token);
        if (head == null) {
            head = newNode;
        } else {
            Node curr = head;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = newNode;
        }
        size++;
    }

    void insertAt(int index, int token) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        if (index == 0) {
            addFirst(token);
            return;
        }

        Node newNode = new Node(token);
        Node prev = head;
        for (int i = 0; i < index - 1; i++) {
            prev = prev.next;
        }

        newNode.next = prev.next;
        prev.next = newNode;
        size++;
    }

    void printList() {
        Node curr = head;
        StringBuilder sb = new StringBuilder();
        while (curr != null) {
            sb.append(curr.data).append(" -> ");
            curr = curr.next;
        }
        sb.append("null");
        System.out.println(sb.toString());
    }
}

public class q1 {
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();

        list.addLast(101);
        list.addLast(102);
        list.addLast(103);

        list.addFirst(100);
        list.addLast(104);
        list.insertAt(2, 150);

        list.printList();
    }
}
