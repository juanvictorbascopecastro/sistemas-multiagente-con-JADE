package agentes;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GuiNumero extends JFrame{

    private MasterAgente myAgent;
	
	public JTextField TPropuestaNumero;
	public JLabel LSuma, Lresta, Lmultiplicacion, Ldivision, LNumeros;
	public JLabel LSumaR, LrestaR, LmultiplicacionR, LdivisionR, LNumerosR;

	
	
	GuiNumero(MasterAgente a) {
		super(a.getLocalName());
		
		myAgent = a;
		
		setTitle("Jugador: "+a.getAID().getName());
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(4, 2));
	
		LNumeros = new JLabel("  OPERACIONES ENTRE:",JLabel.LEFT);
		LNumerosR = new JLabel("...");
		p.add(LNumeros);
		p.add(LNumerosR);

		LSuma = new JLabel("  Suma:",JLabel.LEFT);
		LSumaR = new JLabel("...");
		p.add(LSuma);
		p.add(LSumaR);

		Lresta = new JLabel("  Resta:",JLabel.LEFT);
		LrestaR = new JLabel("...");
		p.add(Lresta);
		p.add(LrestaR);

		Lmultiplicacion = new JLabel("  Multiplicacion:",JLabel.LEFT);
		LmultiplicacionR = new JLabel("...");
		p.add(Lmultiplicacion);
		p.add(LmultiplicacionR);

		Ldivision = new JLabel("  Division:",JLabel.LEFT);
		LdivisionR = new JLabel("...");
		p.add(Ldivision);
		p.add(LdivisionR);

		
		getContentPane().add(p, BorderLayout.CENTER);
		/*
		JButton addButton = new JButton("Enviar Numero");
		addButton.addActionListener( new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				try {
					String numero_enviar = TPropuestaNumero.getText().trim();
					myAgent.IntroducirNumeroJugador(numero_enviar);
					TPropuestaNumero.setText("");
				}
				catch (Exception e) {
					JOptionPane.showMessageDialog(JugadorGui.this, "Invalid values. "+e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE); 
				}
			}
		} );
		p = new JPanel();
		p.add(addButton);
		*/
		getContentPane().add(p, BorderLayout.SOUTH);
		
		// Make the agent terminate when the user closes 
		// the GUI using the button on the upper right corner	
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				myAgent.doDelete();
			}
		} );
		
		setResizable(false);
	}
	
	public void showGui() {
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int centerX = (int)screenSize.getWidth() / 2;
		int centerY = (int)screenSize.getHeight() / 2;
		setLocation(centerX - getWidth() / 2, centerY - getHeight() / 2);
		super.setVisible(true);
	}	
}
