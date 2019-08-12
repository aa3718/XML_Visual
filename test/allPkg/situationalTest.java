package allPkg;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class situationalTest {

    @Mock
    PolicyBuilder builder = new PolicyBuilder();
    Policy policy = builder.build();

    @Test
    void paint() {
    }

    @Test
    void addColorPermission() {
    }

    @Test
    void addColorProhibition() {
    }

    @Test
    void addColorDuty() {
    }

    @Test
    void addColorConstraint() {
    }
}