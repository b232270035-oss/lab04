package mn.edu.must.sqat;

/** Calculates letter grades and the weighted course total. */
public class GradeCalculator {
    /** Returns the letter grade for a score from 0 through 100. */
    public String letterGrade(double score) {
        if (!Double.isFinite(score) || score < 0 || score > 100) {
            throw new IllegalArgumentException("Score must be between 0 and 100");
        }
        if (score >= 90) return "A";
        if (score >= 80) return "B";
        if (score >= 70) return "C";
        if (score >= 60) return "D";
        return "F";
    }

    /** Returns the weighted sum for attendance, lab, quizzes, and exam. */
    public double totalScore(double att, double lab, double quiz1, double quiz2, double exam) {
        checkRange("attendance", att, 10);
        checkRange("lab", lab, 40);
        checkRange("quiz1", quiz1, 10);
        checkRange("quiz2", quiz2, 10);
        checkRange("exam", exam, 30);
        return att + lab + quiz1 + quiz2 + exam;
    }

    private void checkRange(String name, double score, double maximum) {
        if (!Double.isFinite(score) || score < 0 || score > maximum) {
            throw new IllegalArgumentException(name + " must be between 0 and " + maximum);
        }
    }
}
