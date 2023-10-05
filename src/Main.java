// Ctrl + Mayuscula + p Crear un proyecto Java

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;

public class Main {
    public static void main(String[] args) {
        System.out.println("EJECUTANDO...");
        Runtime rt = Runtime.instance();
        Profile p = new ProfileImpl();
        p.setParameter(Profile.MAIN_HOST, "localhost"); //192.168.1.102
        p.setParameter(Profile.GUI, "true");
        ContainerController cc = rt.createMainContainer(p);
        AgentController ac;
        try {
            ac = cc.createNewAgent("master", "agentes.MasterAgente", null);
            ac.start();
            ac = cc.createNewAgent("agente1", "agentes.Agente1", null);
            ac.start();
            ac = cc.createNewAgent("agente2", "agentes.Agente2", null);
            ac.start();
            // Boot.main(new String[]{"-cp", "C:/Users/juanv/Downloads/SHC131-Taller de especialidad/JADE/lib/jade.jar", "-gui"});
        } catch(StaleProxyException e){
            e.printStackTrace();
        }
    }
}