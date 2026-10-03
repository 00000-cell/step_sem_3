package week6.probs;

abstract class ArtPiece {
    private static int counter = 0;
    private final String pieceId;
    private final String title;

    public ArtPiece(String title) {
        this.title = title;
        counter++;
        this.pieceId = "ART-" + counter;
    }

    public String getPieceId() {
        return pieceId;
    }

    public String getTitle() {
        return title;
    }

    public abstract String describe();
}

class Painting extends ArtPiece {
    public Painting(String title) {
        super(title);
    }

    @Override
    public String describe() {
        return "Painting: " + getTitle() + ", framed on canvas";
    }
}

class Sculpture extends ArtPiece {
    public Sculpture(String title) {
        super(title);
    }

    @Override
    public String describe() {
        return "Sculpture: " + getTitle() + ", carved from stone";
    }
}

public class q2 {
    public static void main(String[] args) {
        Painting p = new Painting("Sunset Fields");
        System.out.println(p.describe());

        Sculpture s = new Sculpture("The Thinker II");
        System.out.println(s.describe());
    }
}
