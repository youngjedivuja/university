
import javax.annotation.Resource;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Queue;

@Named
@RequestScoped
public class JMSPorukaController {

    @Inject
    private JMSPorukaModel jmsPorukaModel;

    @Resource(mappedName = "jms/mojRed")
    private Queue mojRed;

    @Inject
    @JMSConnectionFactory("java:comp/DefaultJMSConnectionFactory")
    private JMSContext context;

    public String sendMsg() {
        sendJMSMessageToMojRed(jmsPorukaModel.getMsgText());
        return "potvrda";
    }

    private void sendJMSMessageToMojRed(String messageData) {
        context.createProducer().send(mojRed, messageData);
    }

}
