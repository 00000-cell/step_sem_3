package week5.probs;

abstract class WashType {
    private String name;
    private int durationMinutes;
    private double charge;

    public WashType(String name, int durationMinutes, double charge) {
        this.name = name;
        this.durationMinutes = durationMinutes;
        this.charge = charge;
    }

    public String getName() {
        return name;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public double getCharge() {
        return charge;
    }
}

class QuickWash extends WashType {
    public QuickWash() {
        super("Quick", 30, 20.00);
    }
}

class NormalWash extends WashType {
    public NormalWash() {
        super("Normal", 45, 30.00);
    }
}

class HeavyWash extends WashType {
    public HeavyWash() {
        super("Heavy", 60, 45.00);
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class WashCycle {
    private Student student;
    private WashingMachine machine;
    private WashType washType;

    public WashCycle(Student student, WashingMachine machine, WashType washType) {
        this.student = student;
        this.machine = machine;
        this.washType = washType;
    }

    public Student getStudent() {
        return student;
    }

    public WashingMachine getMachine() {
        return machine;
    }

    public WashType getWashType() {
        return washType;
    }
}

class WashingMachine {
    private String machineId;
    private boolean busy;
    private WashCycle currentCycle;

    public WashingMachine(String machineId) {
        this.machineId = machineId;
        this.busy = false;
        this.currentCycle = null;
    }

    public String getMachineId() {
        return machineId;
    }

    public boolean isBusy() {
        return busy;
    }

    public boolean startWash(Student student, WashType washType) {
        if (busy) {
            System.out.println("Machine " + machineId + " is currently busy.");
            return false;
        }
        this.busy = true;
        this.currentCycle = new WashCycle(student, this, washType);
        System.out.printf("%s wash started on %s for %s (%d min). Charge: ₹%.2f.%n",
                washType.getName(), machineId, student.getName(),
                washType.getDurationMinutes(), washType.getCharge());
        return true;
    }

    public void completeCycle() {
        if (busy) {
            this.busy = false;
            this.currentCycle = null;
            System.out.println(machineId + " cycle completed. " + machineId + " is now free.");
        }
    }
}

public class q1 {
    public static void main(String[] args) {
        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        WashType quick = new QuickWash();
        WashType normal = new NormalWash();
        WashType heavy = new HeavyWash();

        m1.startWash(asha, quick);
        m1.startWash(ravi, heavy);
        m2.startWash(ravi, heavy);
        m1.completeCycle();
        m1.startWash(neha, normal);
    }
}
