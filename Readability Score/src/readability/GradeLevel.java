package readability;

import java.util.Arrays;
import java.util.List;

public class GradeLevel {
    private List<Integer> grade;

    public GradeLevel() {
        this.grade = Arrays.asList(
                6,
                7,
                9,
                10,
                11,
                12,
                13,
                14,
                15,
                16,
                17,
                18,
                24,
                24
        );
    }

    public int findAge(int i) {
        if (i >= grade.size()) {
            return grade.get(grade.size() - 1);
        }
        return grade.get(i);
    }
}
