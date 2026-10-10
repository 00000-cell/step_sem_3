package week7.probs;

class SmartDoorLock implements Remoteable {
    private String lockId;

    public SmartDoorLock(String lockId) {
        this.lockId = lockId;
    }

    public String getLockId() {
        return lockId;
    }

    @Override
    public void connectToApp(String appId) {
        System.out.println(lockId + " connected to app: " + appId);
    }
}

public class q3 {
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
        SmartDoorLock lock = new SmartDoorLock("LOCK-01");
        lock.connectToApp("HomeConnect");

        Remoteable[] devices = {
            new SmartDoorLock("LOCK-01"),
            new SmartDoorLock("LOCK-02")
        };
        connectAllToApp(devices, "HomeConnect");
    }
}
