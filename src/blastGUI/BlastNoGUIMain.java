package blastGUI;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import blast.BlastController;

public class BlastNoGUIMain {

	private static final String dataBaseFile = new String("yeast.aa");
	private static final String dataBaseIndexes = new String("yeast.aa.indexs");

	public static void main(String args[]){
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				createAndShowGUI();
			}
		});
	}

	private static void createAndShowGUI() {
		JFrame frame= new JFrame(); //creo un JFrame
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //al pulsar la "X" se cierra la ventana

		JPanel panelUp= new JPanel(); //creo un Panel
		panelUp.setLayout(new FlowLayout());
		panelUp.setBackground(Color.PINK); //le pongo color rosa
		frame.getContentPane().add(panelUp,"North"); //lo quiero en la parte de arriba
		frame.setSize(800,700); //establezco el tamaño de mi aplicacion
		frame.setVisible(true); //pongo que sea visible

		JRadioButton buttonProtein= new JRadioButton("Proteinas"); //creo un radiobutton "proteinas"
		JRadioButton buttonSequence= new JRadioButton("Secuencia de nucleótidos"); //creo otro radiobutton "nucleotidos"
		JLabel label = new JLabel("Selecciona: "); //creo un label
		panelUp.add(label); //añado el label al panel1
		panelUp.add(buttonProtein);//añado el boton proteina al panel1
		panelUp.add(buttonSequence);//añado el boton nucleotido al panel1

		buttonProtein.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(buttonProtein.isSelected()) {
					buttonSequence.setSelected(false);
				}
			}
		});

		buttonSequence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(buttonSequence.isSelected()) {
					buttonProtein.setSelected(false);
				}
			}
		});

		JPanel panelDown= new JPanel();//creo el segundo Panel
		panelDown.setLayout(new FlowLayout());
		panelDown.setBackground(Color.YELLOW); //le pongo color amarillo
		frame.getContentPane().add(panelDown, "Center");//lo quiero en el centro 

		JComboBox<String> box = new JComboBox<String>();//creo el comboBox
		box.setEditable(true); //hago que se pueda escribir y borrar en el
		JLabel label2= new JLabel("Escribe aquí tu secuencia:"); //hago una etiqueta
		panelDown.add(label2);//añado la etiqueta al panel2
		panelDown.add(box); //añado el comboBox al panel2

		box.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String sequence= box.getEditor().getItem().toString();
				box.addItem(sequence);
			}
		});

		JLabel label3= new JLabel("Escribe un porcentaje entre 0 y 1"); //creo el label
		panelDown.add(label3); //lo añado al panel2

		JTextArea porcentaje= new JTextArea("",1,3); //creo un area de texto para poder escribir
		porcentaje.setEditable(true); //lo hago editable para poder escribir y borrar
		panelDown.add(porcentaje);//lo añado al panel2

		JButton buttonResultado= new JButton("Pulsa aquí para ver el resulado");//creo un boton para ver el resultado
		JTextArea resultado= new JTextArea(30,30); //creo un area de texto en el que salga el resultado, de un tamaño concreto
		JScrollPane sc= new JScrollPane(resultado);//para poder seguir leyendo hacia arriba y abajo, me creo una barra para
		//poder subir y bajar, e ir de izquierda a derecha
		sc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelDown.add(sc);
		panelDown.add(buttonResultado); //añado el boton para ver el resultado al panel2
		

		buttonResultado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String tipo= "Proteinas";
				if(buttonSequence.isSelected()) {
					tipo="Secuencia de nucleotidos";
				}
				float percentage=Float.parseFloat(porcentaje.getText());
				String finalsequence= box.getSelectedItem().toString();
				BlastController bCnt = new BlastController();
				try {
					if(tipo=="Proteinas") {
						String result = bCnt.blastQuery('p', dataBaseFile, dataBaseIndexes, percentage, finalsequence);
						resultado.setText(result);
					}else {
						String result=bCnt.blastQuery('n', dataBaseFile, dataBaseIndexes, percentage, finalsequence);
						resultado.setText(result);
					}
				} catch(Exception exc){
					resultado.setText("No existe la secuencia que has escrito");
				}

			}
		});


	}
}