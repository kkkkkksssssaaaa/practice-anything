import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLOutput;

class JavaTest {

    int a = 1;
    Integer b = 1;

    @AfterEach
    void afterEach() {
        System.out.println("after a=" + a);
        System.out.println("after b=" + b);
    }

    @Test
    void doThreadSafetyTest() {
        fun1(a);
    }

    @Test
    void doNonThreadSafetyTest() {
        fun2(b);
    }

    private void fun1(int a) {
        a++;

        System.out.println("a=" + a);
    }

    private void fun2(Integer b) {
        b++;

        System.out.println("b=" + b);
    }
}
