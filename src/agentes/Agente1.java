package agentes;

import java.util.Random;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class Agente1 extends Agent {
    ACLMessage message;
    ACLMessage mensajeRespueta;
    private int num;
    
    @Override
    protected void setup(){
        System.out.println("======================AGENTE 1==========================");
        message = new ACLMessage(ACLMessage.INFORM);
        message.addReceiver(new AID("master", AID.ISLOCALNAME));
        num = getNumeroRandom();
        message.setContent(Integer.toString(num));
        message.setConversationId("Agente1");
        this.send(message);
        System.out.println("Agente1 Envio: " + num);
        mensajeRespueta = this.blockingReceive();
        if(mensajeRespueta != null) {
            System.out.println("Le llego num Agente1");
        }
        // addBehaviour(new EnviaNumero()); // Envia un mensaje una vez creada
    }
    
    private int getNumeroRandom() { // generar un numero random mayor a 700
        return new Random().nextInt(700) + 701;
    }
}
