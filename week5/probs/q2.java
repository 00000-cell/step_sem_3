package week5.probs;

abstract class Assignment {
    private String title;
    private int maxMarks;
    private int dueDay;

    public Assignment(String title, int maxMarks, int dueDay) {
        this.title = title;
        this.maxMarks = maxMarks;
        this.dueDay = dueDay;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxMarks() {
        return maxMarks;
    }

    public int getDueDay() {
        return dueDay;
    }

    public abstract double calculateLatePenaltyPercent(int daysLate);
}

class CodingAssignment extends Assignment {
    public CodingAssignment(String title, int maxMarks, int dueDay) {
        super(title, maxMarks, dueDay);
    }

    @Override
    public double calculateLatePenaltyPercent(int daysLate) {
        return daysLate > 0 ? daysLate * 10.0 : 0.0;
    }
}

class WrittenAssignment extends Assignment {
    public WrittenAssignment(String title, int maxMarks, int dueDay) {
        super(title, maxMarks, dueDay);
    }

    @Override
    public double calculateLatePenaltyPercent(int daysLate) {
        return daysLate > 0 ? daysLate * 20.0 : 0.0;
    }
}

class AssignmentStudent {
    private String name;

    public AssignmentStudent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Submission {
    private AssignmentStudent student;
    private Assignment assignment;
    private int submissionDay;
    private String status;
    private double finalMarks;

    public Submission(AssignmentStudent student, Assignment assignment, int submissionDay) {
        this.student = student;
        this.assignment = assignment;
        this.submissionDay = submissionDay;
        this.status = "Submitted";

        int daysLate = Math.max(0, submissionDay - assignment.getDueDay());
        if (daysLate == 0) {
            System.out.println(student.getName() + "'s submission for '" + assignment.getTitle() + "' received (on time). Status: Submitted.");
        } else {
            System.out.println(student.getName() + "'s submission for '" + assignment.getTitle() + "' received (" + daysLate + " days late). Status: Submitted.");
        }
    }

    public boolean resubmit(int newSubmissionDay) {
        if ("Graded".equals(status)) {
            System.out.println("Cannot resubmit: '" + assignment.getTitle() + "' has already been graded.");
            return false;
        }
        this.submissionDay = newSubmissionDay;
        int daysLate = Math.max(0, submissionDay - assignment.getDueDay());
        if (daysLate == 0) {
            System.out.println(student.getName() + "'s submission for '" + assignment.getTitle() + "' received (on time). Status: Submitted.");
        } else {
            System.out.println(student.getName() + "'s submission for '" + assignment.getTitle() + "' received (" + daysLate + " days late). Status: Submitted.");
        }
        return true;
    }

    public void grade(double awardedMarks) {
        if (!"Submitted".equals(status)) {
            System.out.println("Submission cannot be graded.");
            return;
        }
        int daysLate = Math.max(0, submissionDay - assignment.getDueDay());
        double penaltyPercent = assignment.calculateLatePenaltyPercent(daysLate);
        this.finalMarks = awardedMarks * (1.0 - penaltyPercent / 100.0);
        this.status = "Graded";

        if (penaltyPercent > 0) {
            System.out.printf("%s graded: %d/%d after %d%% late penalty. Status: Graded.%n",
                    student.getName(), (int) Math.round(finalMarks), assignment.getMaxMarks(), (int) penaltyPercent);
        } else {
            System.out.printf("%s graded: %d/%d. Status: Graded.%n",
                    student.getName(), (int) Math.round(finalMarks), assignment.getMaxMarks());
        }
    }
}

public class q2 {
    public static void main(String[] args) {
        CodingAssignment codingLab = new CodingAssignment("Linked List Lab", 50, 10);
        WrittenAssignment essay = new WrittenAssignment("Design Essay", 50, 12);

        AssignmentStudent asha = new AssignmentStudent("Asha");
        AssignmentStudent ravi = new AssignmentStudent("Ravi");

        Submission ashaSub = new Submission(asha, codingLab, 10);
        Submission raviSub = new Submission(ravi, essay, 14);

        ashaSub.grade(45);
        raviSub.grade(40);

        ashaSub.resubmit(15);
    }
}
