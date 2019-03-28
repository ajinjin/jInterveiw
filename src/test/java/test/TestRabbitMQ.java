package test;

import com.gu.MySpringApplication;
import com.gu.mq.SendRabbitMQ;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = MySpringApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestRabbitMQ {

    @Autowired
    private SendRabbitMQ sendRabbitMQ;

    public void testMQ(){
        sendRabbitMQ.send();
    }


}
