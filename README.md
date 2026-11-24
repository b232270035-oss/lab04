# Лаборатори №4: JUnit 5 нэгжийн тест

- Оюутны нэр: Д. Дэлгэрмаа
- Оюутны код: B232270035
`java -version`:
```text
openjdk version "21.0.13" 2026-10-20
OpenJDK Runtime Environment (build 21.0.13+9-deb13u2-Debian)
OpenJDK 64-Bit Server VM (build 21.0.13+9-deb13u2-Debian, mixed mode, sharing)
```

`mvn -version`:
```text
Apache Maven 3.9.9
Maven home: /usr/share/maven
Java version: 21.0.13, vendor: Debian, runtime: /usr/lib/jvm/java-21-openjdk-amd64
Default locale: en, platform encoding: UTF-8
OS name: "linux", version: "6.12.117+deb13-amd64", arch: "amd64", family: "unix"
```

## Ажлын тайлан

Энэ төсөл Maven болон JUnit Jupiter 5.10.2 ашиглаж, Java 17 release target-оор compile хийнэ. GradeCalculator үсгэн дүнгийн хязгаар болон үнэлгээний жинлэсэн нийлбэрийг шалгаж, боломжгүй оноонд IllegalArgumentException шиднэ. `GradeCalculatorTest` нь AAA бүтэц, монгол @DisplayName, assertThrows болон letterGrade, totalScore-д зориулсан parameterized тестүүдтэй. Нийт 10 тестийн метод ажиллуулж, `results/mvn-test.txt`-д 22 тестийн тохиолдол `Failures: 0`, `BUILD SUCCESS` болсон. Мутацид `score >= 90` нөхцөлийг `score > 90` болгож, `results/mvn-test-mutant.txt`-д 22 тохиолдлоос 1 алдаа, `BUILD FAILURE` гарсныг хадгалсан. `letterGradeBoundaries` параметртэй тестийн 90 онооны мөр `expected: <A> but was: <B>` гэж унасан. Энэ хязгаарын тест яг 90 дээрх босгын алдааг илрүүлсэн тул хамгийн сонирхолтой нь байв. Мутацийн дараа нөхцөлийг `>= 90` болгон сэргээж, ногоон `mvn test`-ийг дахин ажиллуулсан.
