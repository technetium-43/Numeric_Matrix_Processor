import org.hyperskill.hstest.stage.StageTest;
import org.hyperskill.hstest.testcase.CheckResult;
import org.hyperskill.hstest.testcase.SimpleTestCase;
import org.hyperskill.hstest.testcase.TestCase;
import processor.Main;

import java.util.List;
import java.util.stream.Collectors;

public class NumericMatrixProcessorTest extends StageTest {
    public NumericMatrixProcessorTest() {
        super(Main.class);
    }


    @Override
    public List<TestCase> generate() {
        return List.of(
            new SimpleTestCase(
                "4 5\n" +
                    "1 2 3 4 5\n" +
                    "3 2 3 2 1\n" +
                    "8 0 9 9 1\n" +
                    "1 3 4 5 6\n" +
                    "4 5\n" +
                    "1 1 4 4 5\n" +
                    "4 4 5 7 8\n" +
                    "1 2 3 9 8\n" +
                    "1 0 0 0 1",
                "2 3 7 8 10\n" +
                    "7 6 8 9 9\n" +
                    "9 2 12 18 9\n" +
                    "2 3 4 5 7"
            ).setCheckFunc(this::checkSolved),

            new SimpleTestCase(
                "2 3\n" +
                    "1 4 5\n" +
                    "4 5 5\n" +
                    "4 5\n" +
                    "0 1 0 4 5\n" +
                    "1 7 8 9 4\n" +
                    "1 2 3 5 6\n" +
                    "1 3 4 3 8",
                "ERROR"
            ).setCheckFunc(this::checkSolved),

            new SimpleTestCase(
                "4 5\n" +
                    "4 2 3 4 5 \n" +
                    "3 5 3 2 1\n" +
                    "8 0 9 9 1\n" +
                    "1 3 4 5 9\n" +
                    "4 5\n" +
                    "1 1 4 4 5\n" +
                    "4 4 5 7 8\n" +
                    "1 2 3 9 8\n" +
                    "1 0 0 0 1",
                "5 3 7 8 10\n" +
                    "7 9 8 9 9\n" +
                    "9 2 12 18 9\n" +
                    "2 3 4 5 10"
            ).setCheckFunc(this::checkSolved),

            new SimpleTestCase(
                "1 1\n" +
                    "1\n" +
                    "1 1\n" +
                    "2",
                "3"
            ).setCheckFunc(this::checkSolved),

            new SimpleTestCase(
                "1 2\n" +
                    "3 4\n" +
                    "1 2\n" +
                    "5 6",
                "8 10"
            ).setCheckFunc(this::checkSolved),

            new SimpleTestCase(
                "2 1\n" +
                    "1\n" +
                    "2\n" +
                    "2 1\n" +
                    "2\n" +
                    "1",
                "3\n" +
                    "3"
            ).setCheckFunc(this::checkSolved),

            new SimpleTestCase(
                "2 1\n" +
                    "2\n" +
                    "1\n" +
                    "1 2\n" +
                    "1 2",
                "ERROR"
            ).setCheckFunc(this::checkSolved)
        );
    }

    private CheckResult checkSolved(String reply, String clue) {

        clue = clue.strip();
        reply = reply.strip();

        if (reply.equals(clue)) {
            return CheckResult.correct();
        }

        if (clue.equals("ERROR")) {
            return CheckResult.wrong("");
        }

        List<String> user = reply.lines().collect(Collectors.toList());
        List<String> answ = clue.lines().collect(Collectors.toList());

        if (user.size() != answ.size()) {
            return CheckResult.wrong("");
        }

        for (int i = 0; i < user.size(); i++) {
            String userLine = user.get(i).strip();
            String answLine = answ.get(i).strip();
            if (!userLine.equals(answLine)) {
                return CheckResult.wrong("");
            }
        }

        return new CheckResult(true);
    }
}
