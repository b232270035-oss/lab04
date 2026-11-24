package mn.edu.must.sqat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Дүн тооцоолуурын нэгжийн тест")
class GradeCalculatorTest {
    @Test
    @DisplayName("Ердийн оноонууд үсгэн дүнтэй зөв таарна")
    void typicalScoresHaveExpectedGrades() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();
        // Act & Assert
        assertEquals("A", calc.letterGrade(95));
        assertEquals("B", calc.letterGrade(85));
        assertEquals("C", calc.letterGrade(75));
        assertEquals("D", calc.letterGrade(65));
        assertEquals("F", calc.letterGrade(30));
    }

    @ParameterizedTest(name = "{0} оноо нь {1} дүн")
    @CsvSource({"90,A", "89.99,B", "80,B", "70,C", "60,D", "59.99,F", "0,F", "100,A"})
    @DisplayName("Үсгэн дүнгийн хязгаарууд зөв ажиллана")
    void letterGradeBoundaries(double score, String expected) {
        // Arrange
        GradeCalculator calc = new GradeCalculator();
        // Act
        String actual = calc.letterGrade(score);
        // Assert
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Доод болон дээд хязгаараас гадуурх дүнг хориглоно")
    void letterGradeRejectsOutOfRangeScores() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(-1));
        assertThrows(IllegalArgumentException.class, () -> calc.letterGrade(101));
    }

    @Test
    @DisplayName("Бүхэл онооны дээд утгуудыг зөвшөөрнө")
    void letterGradeAcceptsZeroAndOneHundred() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();
        // Act & Assert
        assertEquals("F", calc.letterGrade(0));
        assertEquals("A", calc.letterGrade(100));
    }

    @Test
    @DisplayName("Онооны нийлбэр 100 болно")
    void totalScoreAddsMaximumComponents() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();
        // Act
        double total = calc.totalScore(10, 40, 10, 10, 30);
        // Assert
        assertEquals(100.0, total);
    }

    @ParameterizedTest(name = "нийлбэр {0} + {1} + {2} + {3} + {4} = {5}")
    @CsvSource({"5,20,8,7,25,65", "0,0,0,0,0,0", "10,40,10,10,30,100"})
    @DisplayName("Хүчинтэй оноонуудын жинлэсэн нийлбэр зөв")
    void totalScoreForValidInputs(double att, double lab, double quiz1, double quiz2,
                                  double exam, double expected) {
        // Arrange
        GradeCalculator calc = new GradeCalculator();
        // Act
        double total = calc.totalScore(att, lab, quiz1, quiz2, exam);
        // Assert
        assertEquals(expected, total);
    }

    @Test
    @DisplayName("Ирцийн сөрөг оноонд алдаа шиднэ")
    void totalScoreRejectsNegativeAttendance() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> calc.totalScore(-5, 0, 0, 0, 0));
    }

    @Test
    @DisplayName("Лабораторийн дээд хязгаараас их оноонд алдаа шиднэ")
    void totalScoreRejectsLabAboveMaximum() {
        // Arrange
        GradeCalculator calc = new GradeCalculator();
        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> calc.totalScore(0, 41, 0, 0, 0));
    }

    @ParameterizedTest(name = "{0} хэсэгт зөвшөөрөгдөөгүй {1} оноо")
    @CsvSource({"attendance,-0.01", "lab,40.01", "quiz1,10.01", "quiz2,-1", "exam,30.01"})
    @DisplayName("Хэсэг бүрийн онооны хязгаарыг мөрдөнө")
    void totalScoreRejectsInvalidComponent(String component, double invalidScore) {
        // Arrange
        GradeCalculator calc = new GradeCalculator();
        double att = component.equals("attendance") ? invalidScore : 0;
        double lab = component.equals("lab") ? invalidScore : 0;
        double quiz1 = component.equals("quiz1") ? invalidScore : 0;
        double quiz2 = component.equals("quiz2") ? invalidScore : 0;
        double exam = component.equals("exam") ? invalidScore : 0;
        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> calc.totalScore(att, lab, quiz1, quiz2, exam));
    }
}
