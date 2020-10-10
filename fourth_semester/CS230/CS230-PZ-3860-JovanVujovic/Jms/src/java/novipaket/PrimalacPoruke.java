package novipaket;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "jms/mojRed")
    ,
@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue")
})
public class PrimalacPoruke implements MessageListener {

    public PrimalacPoruke() {
    }

    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            System.out.println("Primljena poruka: "
                    + textMessage.getText());
        } catch (JMSException ex) {
            Logger.getLogger(PrimalacPoruke.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }
}
