import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import java.util.Random;

@Controller
public class DataController {
    private final SimpMessagingTemplate template;

    public DataController(SimpMessagingTemplate template) {
        this.template = template;
    }

    public void sendData() {
        Random random = new Random();
        while (true) {
            int value = random.nextInt(100);
            template.convertAndSend("/topic/data", value);
            try { Thread.sleep(2000); } catch (InterruptedException e) { e.printStackTrace(); }
        }
    }
}
