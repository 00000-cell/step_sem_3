package week5.probs;

abstract class MembershipPlan {
    private String planName;
    private int months;

    public MembershipPlan(String planName, int months) {
        this.planName = planName;
        this.months = months;
    }

    public String getPlanName() {
        return planName;
    }

    public int getMonths() {
        return months;
    }

    public abstract double calculateFee();
}

class MonthlyPlan extends MembershipPlan {
    public MonthlyPlan() {
        super("Monthly", 1);
    }

    @Override
    public double calculateFee() {
        return 1 * 1000.00;
    }
}

class QuarterlyPlan extends MembershipPlan {
    public QuarterlyPlan() {
        super("Quarterly", 3);
    }

    @Override
    public double calculateFee() {

        return 3 * 1000.00 * 0.90;
    }
}

class AnnualPlan extends MembershipPlan {
    public AnnualPlan() {
        super("Annual", 12);
    }

    @Override
    public double calculateFee() {

        return 12 * 1000.00 * 0.75;
    }
}

class GymMember {
    private String name;

    public GymMember(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

enum GymStatus {
    ACTIVE,
    FROZEN,
    EXPIRED
}

class Membership {
    private GymMember member;
    private MembershipPlan plan;
    private double fee;
    private GymStatus status;

    public Membership(GymMember member, MembershipPlan plan) {
        this.member = member;
        this.plan = plan;
        this.fee = plan.calculateFee();
        this.status = GymStatus.ACTIVE;

        System.out.printf("%s membership created for %s. Fee: ₹%,.2f. Status: Active.%n",
                plan.getPlanName(), member.getName(), fee);
    }

    public void checkIn() {
        if (status == GymStatus.ACTIVE) {
            System.out.println(member.getName() + " checked in successfully.");
        } else if (status == GymStatus.FROZEN) {
            System.out.println("Check-in denied: " + member.getName() + "'s membership is Frozen.");
        } else {
            System.out.println("Check-in denied: " + member.getName() + "'s membership is Expired.");
        }
    }

    public void freeze() {
        if (status == GymStatus.EXPIRED) {
            System.out.println("Cannot freeze an Expired membership.");
            return;
        }
        this.status = GymStatus.FROZEN;
        System.out.println(member.getName() + "'s membership frozen. Status: Frozen.");
    }

    public void unfreeze() {
        if (status == GymStatus.EXPIRED) {
            System.out.println("Cannot unfreeze an Expired membership.");
            return;
        }
        this.status = GymStatus.ACTIVE;
        System.out.println(member.getName() + "'s membership unfrozen. Status: Active.");
    }

    public void expire() {
        this.status = GymStatus.EXPIRED;
        System.out.println(member.getName() + "'s membership expired. Status: Expired.");
    }
}

public class q4 {
    public static void main(String[] args) {
        GymMember asha = new GymMember("Asha");
        GymMember ravi = new GymMember("Ravi");

        Membership ashaMembership = new Membership(asha, new QuarterlyPlan());
        Membership raviMembership = new Membership(ravi, new MonthlyPlan());

        ashaMembership.checkIn();
        ashaMembership.freeze();
        ashaMembership.checkIn();

        raviMembership.expire();
        raviMembership.freeze();
    }
}
