package train;

import hugtrain.ChatApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import hugtrain.train.Train;

@SpringBootTest(classes = ChatApplication.class)
public class TrainTest {

    @Autowired
    private Train train;

    @Test
    public void testTrain() {
        train.train();
    }
}