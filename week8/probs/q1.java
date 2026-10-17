package week8.probs;

import java.util.*;

class LibraryMember {
    private String membershipPin;
    String branchCode;
    protected double finesOwed;
    public String displayName;

    public LibraryMember(String membershipPin, String branchCode, double finesOwed, String displayName) {
        this.membershipPin = membershipPin;
        this.branchCode = branchCode;
        this.finesOwed = finesOwed;
        this.displayName = displayName;
    }
}

class AccessChecker {
    public static String classifyAccess(String fieldModifier, String accessorContext) {
        switch (fieldModifier) {
            case "private":
                return "SAME_CLASS".equals(accessorContext) ? "ALLOWED" : "DENIED";
            case "default":
                return ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) ? "ALLOWED" : "DENIED";
            case "protected":
                return ("SAME_CLASS".equals(accessorContext) || "SAME_PACKAGE".equals(accessorContext)) ? "ALLOWED" : "DENIED";
            case "public":
                return "ALLOWED";
            default:
                return "DENIED";
        }
    }

    public static String summarizeByModifier(String[][] attempts) {
        String[] modifiers = {"private", "default", "protected", "public"};
        Map<String, int[]> counts = new LinkedHashMap<>();

        for (String mod : modifiers) {
            counts.put(mod, new int[]{0, 0});
        }

        if (attempts != null) {
            for (String[] attempt : attempts) {
                if (attempt.length >= 2) {
                    String mod = attempt[0];
                    String context = attempt[1];
                    String result = classifyAccess(mod, context);
                    if (counts.containsKey(mod)) {
                        if ("ALLOWED".equals(result)) {
                            counts.get(mod)[0]++;
                        } else {
                            counts.get(mod)[1]++;
                        }
                    }
                }
            }
        }

        List<String> parts = new ArrayList<>();
        for (String mod : modifiers) {
            int[] res = counts.get(mod);
            parts.add(mod + ": " + res[0] + " allowed / " + res[1] + " denied");
        }

        return String.join(" | ", parts);
    }
}

public class q1 {
    public static void main(String[] args) {
        System.out.println(AccessChecker.classifyAccess("private", "SAME_CLASS"));
        System.out.println(AccessChecker.classifyAccess("protected", "DIFFERENT_PACKAGE"));

        String[][] attempts = {
            {"private", "SAME_CLASS"},
            {"private", "SAME_PACKAGE"},
            {"default", "SAME_PACKAGE"},
            {"default", "DIFFERENT_PACKAGE"},
            {"protected", "SAME_PACKAGE"},
            {"protected", "SAME_CLASS"},
            {"public", "DIFFERENT_PACKAGE"}
        };

        System.out.println(AccessChecker.summarizeByModifier(attempts));
    }
}
