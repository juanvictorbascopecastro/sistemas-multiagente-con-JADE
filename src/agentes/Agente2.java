package agentes;

import java.util.Random;

import jade.core.AID;
import jade.core.Agent;
import jade.lang.acl.ACLMessage;

public class Agente2 extends Agent {
    ACLMessage message;
    ACLMessage mensajeRespueta;
    private int num;

    @Override
    protected void setup() {
        System.out.println("======================AGENTE 2==========================");
        
        message = new ACLMessage(ACLMessage.INFORM);
        message.addReceiver(new AID("master", AID.ISLOCALNAME));
        num = getNumeroRandom();
        message.setContent(Integer.toString(num));
        message.setConversationId("Agente2");
        this.send(message);
        System.out.println("Agente2 Envio: " + num);
        mensajeRespueta = this.blockingReceive();
        if(mensajeRespueta != null) {
            System.out.println("Le llego num Agente2");
        }
    }

    private int getNumeroRandom() { // generar un numero random menor a 70
        return new Random().nextInt(69) + 1;
    }
	
}
