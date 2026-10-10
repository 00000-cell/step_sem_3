package week7.probs;

class SmartHomeLight extends Device implements Remoteable {
    public SmartHomeLight(String deviceId) {
        super(deviceId);
    }

    @Override
    public void performPrimaryAction() {
        System.out.println(getDeviceId() + " is glowing at 100% brightness");
    }

    @Override
    public void connectToApp(String appId) {
        System.out.println(getDeviceId() + " connected to app: " + appId);
    }
}

public class q5 {
    public static void connectAllToApp(Remoteable[] devices, String appId) {
        if (devices != null) {
            for (Remoteable r : devices) {
                if (r != null) {
                    r.connectToApp(appId);
                }
            }
        }
    }

    public static void main(String[] args) {
        SmartHomeLight light = new SmartHomeLight("LIGHT-01");
        SmartThermostat thermostat = new SmartThermostat("THERMO-01", 22.5);
        BasicLamp lamp = new BasicLamp("LAMP-01");
        SmartDoorLock lock = new SmartDoorLock("LOCK-01");

        light.turnOn();
        light.performPrimaryAction();

        thermostat.turnOn();
        thermostat.performPrimaryAction();

        lamp.turnOn();
        lamp.performPrimaryAction();

        System.out.println("Thermostat power draw: " + thermostat.getPowerConsumption() + "W");

        Remoteable[] remoteDevices = {light, thermostat, lock};
        connectAllToApp(remoteDevices, "HomeConnect");
    }
}
