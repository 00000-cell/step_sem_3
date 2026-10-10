package week7.probs;

abstract class Device {
    private String deviceId;
    private boolean powerOn;

    public Device(String deviceId) {
        this.deviceId = deviceId;
        this.powerOn = false;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public boolean isPowerOn() {
        return powerOn;
    }

    public void turnOn() {
        powerOn = true;
        System.out.println(deviceId + " is now ON");
    }

    public void turnOff() {
        powerOn = false;
        System.out.println(deviceId + " is now OFF");
    }

    public abstract void performPrimaryAction();
}

class SmartLight extends Device {
    public SmartLight(String deviceId) {
        super(deviceId);
    }

    @Override
    public void performPrimaryAction() {
        System.out.println(getDeviceId() + " is glowing at 100% brightness");
    }
}

public class q1 {
    public static void main(String[] args) {
        SmartLight light = new SmartLight("LIGHT-01");
        light.turnOn();
        light.performPrimaryAction();
        light.turnOff();
    }
}
