package week7.probs;

interface Remoteable {
    void connectToApp(String appId);
}

interface Schedulable {
    void scheduleAction(String time);
}

interface EnergyMonitorable {
    double getPowerConsumption();
}

class SmartThermostat extends Device implements Remoteable, Schedulable, EnergyMonitorable {
    private double targetTemperature;
    private double powerConsumption;

    public SmartThermostat(String deviceId, double targetTemperature) {
        super(deviceId);
        this.targetTemperature = targetTemperature;
        this.powerConsumption = 45.5;
    }

    @Override
    public void performPrimaryAction() {
        System.out.println(getDeviceId() + " is regulating temperature to " + targetTemperature + " degrees");
    }

    @Override
    public void connectToApp(String appId) {
        System.out.println(getDeviceId() + " connected to app: " + appId);
    }

    @Override
    public void scheduleAction(String time) {
        System.out.println(getDeviceId() + " scheduled for " + time);
    }

    @Override
    public double getPowerConsumption() {
        return powerConsumption;
    }
}

public class q2 {
    public static void main(String[] args) {
        SmartThermostat thermo = new SmartThermostat("THERMO-01", 22.5);
        thermo.turnOn();
        thermo.performPrimaryAction();
        thermo.connectToApp("HomeConnect");
        thermo.scheduleAction("08:00 AM");
        System.out.println("Power consumption: " + thermo.getPowerConsumption() + "W");
    }
}
