import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;



public class PlanoCartesiano extends JFrame{
	
    PlanoCartesiano(){
    	//Agregar Componentes
    	Container contenedorPrinc = getContentPane();
    	contenedorPrinc.setLayout(new BoxLayout(contenedorPrinc,BoxLayout.Y_AXIS ));
    	
    	//Lienzo nuevo creado a partir de la clase
    	Lienzo lienzo = new Lienzo();
    	contenedorPrinc.add(lienzo);
    	
        //JPanel lienzo = new JPanel();
    	//lienzo.setPreferredSize(new Dimension (800,400));
    	//lienzo.setBackground(Color.YELLOW);
    	JLabel lblLienzo = new JLabel("Lienzo: ");
    
  
    	lienzo.add(lblLienzo);
    	contenedorPrinc.add(lienzo);
    	
    	JPanel panelBotones  = new JPanel();
    	panelBotones.setLayout(new FlowLayout());
    	
    	//Agregar Botones
    	TitledBorder titPanelBotones = BorderFactory.createTitledBorder("");
        panelBotones.setBorder(titPanelBotones);
    	JLabel lblAncho = new JLabel("Ancho: ");
    	lblAncho.setPreferredSize(new Dimension (50,20));
    	
    	JTextField edtAncho = new JTextField();
    	edtAncho.setPreferredSize(new Dimension (100,20));
    	
    	JLabel lblAlto = new JLabel("Alto: ");
    	lblAlto.setPreferredSize(new Dimension (50,20));
    	
    	JTextField edtAlto = new JTextField();
    	edtAlto.setPreferredSize(new Dimension (100,20));
    	
    	JButton btnTamañoLienzo = new JButton("Establecer");
    	panelBotones.add(lblAncho);
    	panelBotones.add(edtAncho);
    	panelBotones.add(lblAlto);
    	panelBotones.add(edtAlto);
    	panelBotones.add(btnTamañoLienzo);
    	contenedorPrinc.add(panelBotones);
    	
    	JButton btnColorLienzo = new JButton("Cambiar color...");
    	panelBotones.add(btnColorLienzo);
    	contenedorPrinc.add(panelBotones);
    	
    	btnColorLienzo.addActionListener(
    			new ActionListener(){

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO Auto-generated method stub
						Color colorLienzo = JColorChooser.showDialog(
								btnColorLienzo, 
								"Seleccionar color", 
								lienzo.getColorFondo());
						if (colorLienzo != null){
							lienzo.setColorFondo(colorLienzo);
						}
					}	
    			  }
    			);
    	/*---------------------------
    	class MiClase implements ActionListener{
    	    public void actionPerformed(ActionEvent event){
    	        lienzo.setPreferredSize(new Dimension(
    	                Integer.parseInt(edtAncho.getText()),
    	                Integer.parseInt(edtAlto.getText()))
    	        );
    	    }

    	}

    	MiClase oyente = new MiClase();

    	btnTamañoLienzo.addActionListener(oyente);
    	---------------------------*/

    	//Oyente como clase anónima
    	btnTamañoLienzo.addActionListener(
    	       new ActionListener(){
    	            public void actionPerformed(ActionEvent event){
    	            	/*
    	                lienzo.setPreferredSize(new Dimension(
    	                        Integer.parseInt(edtAncho.getText()),
    	                        Integer.parseInt(edtAlto.getText()))
    	                );*/
    	            	PlanoCartesiano.this.setSize(
    	            		 Integer.parseInt(edtAncho.getText()),
    	    	                Integer.parseInt(edtAlto.getText())
    	    	          );
    	            	}
    	            }
    	       );
             }
    
    public static void main(String [] args){
    	PlanoCartesiano plano2D = new PlanoCartesiano();
    	plano2D.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	plano2D.setResizable(false);
    	plano2D.setVisible(true);
    	plano2D.setSize(800,600);
    }
    
}
