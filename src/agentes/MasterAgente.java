package agentes;


import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;

public class MasterAgente extends Agent{
    GuiNumero myGui;
    private int num1 = 0, num2 = 0;

    @Override
    protected void setup() {
        System.out.println("======================AGENTE MASTER==========================");
        myGui = new GuiNumero(this);		// Creando la GUI
		myGui.showGui();

		// Registramos el servicio de paginas amarillas. Si lo utulize
		DFAgentDescription dfd = new DFAgentDescription();
		dfd.setName(getAID());
		ServiceDescription sd = new ServiceDescription();
		sd.setType("Subasta");
		sd.setName("JADE-Subasta-Objetos");
		dfd.addServices(sd);
		try {
			DFService.register(this, dfd);
		}
		catch (FIPAException fe) {     
			fe.printStackTrace();
		}

        // addBehaviour(new RecibirMensaje());
        addBehaviour(new Behaviour() {

            @Override
            public void action() {
                ACLMessage message = myAgent.blockingReceive();
                ACLMessage respuesta;
                if(message != null) {
                    System.out.println("MASTER RECIBIO DE " + message.getConversationId() + " EL NUMERO: " + message.getContent() + "!!!!!!!!!!!!" );
                    if(message.getConversationId().equals("Agente1")) num1 = Integer.parseInt(message.getContent());
                    if(message.getConversationId().equals("Agente2")) num2 = Integer.parseInt(message.getContent());
                    respuesta = message.createReply();
                    respuesta.setContent("CONFIRMACION!");
                    send(respuesta);
                    Operaciones();
                } else {
                    System.out.println("Agente receptor informa: No recibio mensaje!");
                }
            }

            @Override
            public boolean done() {
                return done;
            }
            boolean done = false;
        });
    }
    // Put agent clean-up operations here
	protected void takeDown() {
		// Deregister from the yellow pages
		try {
			DFService.deregister(this);
		}
		catch (FIPAException fe) {
			fe.printStackTrace();
		}
        
		// Close the GUI
		myGui.dispose();
		// Printout a dismissal message
		System.out.println("Hasta luego "+getAID().getName()+" terminado.");
	}
    
    private void Operaciones() {
        if(num1 != 0 && num2 != 0) {
            myGui.LNumerosR.setText( num1 + " y " + num2 + "!!!!!");
            myGui.LSumaR.setText(Integer.toString(num1 + num2));
            myGui.LrestaR.setText(Integer.toString(num1 - num2));
            myGui.LmultiplicacionR.setText(Integer.toString(num1 * num2));
            myGui.LdivisionR.setText(Integer.toString(num1 / num2));
        }
    }
}
