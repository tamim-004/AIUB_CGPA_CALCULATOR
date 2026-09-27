public class CourseCalculator{
    public static String getGrade(double number) {
        if (number >= 90) return "A+";
        else if (number >= 85) return "A";
        else if (number >= 80) return "B+";
        else if (number >= 75) return "B";
        else if (number >= 70) return "C+";
        else if (number >= 65) return "C";
        else if (number >= 60) return "D+";
        else if (number >= 50) return "D";
        else return "F";
    }
    public static double getGradePoint(String grade) {
        switch (grade) {
            case "A+": return 4.00;
            case "A": return 3.75;
            case "B+": return 3.50;
            case "B": return 3.25;
            case "C+": return 3.00;
            case "C": return 2.75;
            case "D+": return 2.50;
            case "D": return 2.25;
            default: return 0.00;
        }
    }
    public static double calculateCGPA(double prevTotalGP, double prevCredits, double newTotalGP, double newCredits) {
        return (prevTotalGP + newTotalGP) / (prevCredits + newCredits);
    }
}
