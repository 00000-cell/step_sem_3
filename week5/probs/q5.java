package week5.probs;

import java.util.*;

interface NotificationChannel {
    String getChannelName();
    void send(BroadcasterStudent student, String message);
}

class EmailChannel implements NotificationChannel {
    @Override
    public String getChannelName() {
        return "Email";
    }

    @Override
    public void send(BroadcasterStudent student, String message) {
        System.out.println("[Email → " + student.getName() + "] " + message + ".");
    }
}

class SmsChannel implements NotificationChannel {
    @Override
    public String getChannelName() {
        return "SMS";
    }

    @Override
    public void send(BroadcasterStudent student, String message) {
        System.out.println("[SMS → " + student.getName() + "] " + message + ".");
    }
}

class AppChannel implements NotificationChannel {
    @Override
    public String getChannelName() {
        return "App";
    }

    @Override
    public void send(BroadcasterStudent student, String message) {
        System.out.println("[App → " + student.getName() + "] " + message + ".");
    }
}

class BroadcasterStudent {
    private String name;
    private String department;
    private List<NotificationChannel> channels;

    public BroadcasterStudent(String name, String department, List<NotificationChannel> channels) {
        this.name = name;
        this.department = department;
        this.channels = channels;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public List<NotificationChannel> getChannels() {
        return channels;
    }
}

class NoticeBoard {
    private List<BroadcasterStudent> students = new ArrayList<>();

    public void registerStudent(BroadcasterStudent student) {
        students.add(student);
    }

    public void postNotice(String title, List<String> targetDepartments) {
        if (title == null || title.trim().isEmpty() || targetDepartments == null || targetDepartments.isEmpty()) {
            System.out.println("Cannot post notice: At least one target department is required.");
            return;
        }

        System.out.println("Notice '" + title + "' posted to " + String.join(", ", targetDepartments) + ".");

        for (BroadcasterStudent s : students) {
            if (targetDepartments.contains(s.getDepartment())) {
                for (NotificationChannel channel : s.getChannels()) {
                    channel.send(s, title);
                }
            }
        }
    }
}

public class q5 {
    public static void main(String[] args) {
        NotificationChannel email = new EmailChannel();
        NotificationChannel sms = new SmsChannel();
        NotificationChannel app = new AppChannel();

        BroadcasterStudent asha = new BroadcasterStudent("Asha", "CSE", Arrays.asList(email, app));
        BroadcasterStudent ravi = new BroadcasterStudent("Ravi", "ECE", Collections.singletonList(sms));

        NoticeBoard noticeBoard = new NoticeBoard();
        noticeBoard.registerStudent(asha);
        noticeBoard.registerStudent(ravi);

        noticeBoard.postNotice("Lab Closed Tomorrow", Collections.singletonList("CSE"));
        noticeBoard.postNotice("Fee Deadline Extended", Arrays.asList("CSE", "ECE"));
        noticeBoard.postNotice("Sports Day", Collections.emptyList());
    }
}
