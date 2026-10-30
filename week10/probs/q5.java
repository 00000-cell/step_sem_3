package week10.probs;

class PlayerNode {
    String name;
    PlayerNode next;

    PlayerNode(String name) {
        this.name = name;
        this.next = null;
    }
}

class CircularPlayerList {
    PlayerNode tail;

    CircularPlayerList() {
        this.tail = null;
    }

    void addLast(String name) {
        PlayerNode newNode = new PlayerNode(name);
        if (tail == null) {
            newNode.next = newNode;
            tail = newNode;
        } else {
            newNode.next = tail.next;
            tail.next = newNode;
            tail = newNode;
        }
    }

    void printTurns(int turns) {
        if (tail == null || turns <= 0) {
            return;
        }

        PlayerNode curr = tail.next;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < turns; i++) {
            sb.append(curr.name);
            if (i < turns - 1) {
                sb.append(" ");
            }
            curr = curr.next;
        }
        System.out.println(sb.toString());
    }
}

public class q5 {
    public static void main(String[] args) {
        CircularPlayerList game = new CircularPlayerList();
        game.addLast("Asha");
        game.addLast("Ravi");
        game.addLast("Neha");

        game.printTurns(7);
    }
}
