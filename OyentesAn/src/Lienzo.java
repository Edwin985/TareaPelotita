import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class Lienzo extends JPanel{
	
	private Color colorFondo;
	private int XOrigen = 100, YOrigen = 100;
	private int OrigenPltX = XOrigen - 200;
    private int OrigenPltY = YOrigen - 250;
	private accionesRaton oyenteRaton;
public Lienzo(){
	super();
	//DibujaCirculito
	this.addKeyListener(new KeyListener() {

        @Override
        public void keyPressed(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
            	OrigenPltX = OrigenPltX + 15;
                repaint();
            }

            if(e.getKeyCode() == KeyEvent.VK_LEFT) {
            	OrigenPltX = OrigenPltX - 15;
                repaint();
            }

            if(e.getKeyCode() == KeyEvent.VK_DOWN) {
            	OrigenPltY = OrigenPltY + 15;
                repaint();
            }

            if(e.getKeyCode() == KeyEvent.VK_UP) {
            	OrigenPltY = OrigenPltY - 15;
                repaint();
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

    });
    this.setFocusable(true);
    this.requestFocusInWindow();
	
	//obtener ancho y alto del JFrame
	this.setPreferredSize(new Dimension (800,400));
	colorFondo = Color.YELLOW;
	this.setBackground(colorFondo); 
	
	
	accionesRaton oyenteRaton = new accionesRaton();
	this.addMouseListener(oyenteRaton);
	this.addMouseMotionListener(oyenteRaton);
}
public void paintComponent (Graphics g){
	g.setColor(this.colorFondo);
	g.fillRect(0, 0, this.getWidth(), this.getHeight());
	
	
	//Dibujar ejeX y ejeY
	 
	//XOrigen = this.getWidth() / 2;
	//YOrigen = this.getHeight() / 2;
	 
	g.setColor(Color.red);
	g.drawLine(0, YOrigen, this.getWidth(), YOrigen);
    g.drawLine(XOrigen, 0,XOrigen , this.getHeight());
    
    
	//calculos
	int nValoresNeg = (XOrigen/40) * -1;
	int nValoresPos = (this.getWidth() - XOrigen/40);
	
	
	for (int valorX = nValoresNeg; valorX <= nValoresPos; valorX++){
		//Dibujar las marcas del eje x
		g.drawLine(XOrigen + (valorX * 40), YOrigen-10 , XOrigen + (valorX * 40), YOrigen+10);
		//Dibujar numeros en marcas del eje x
		g.drawString(" " + valorX, XOrigen + (valorX * 40), YOrigen+20);
		
		/*
		//Dibujar las marcas negativas del eje x
		g.drawLine(XOrigen - (valorX * 40), YOrigen-10 , XOrigen - (valorX * 40), YOrigen+10);
		//Dibujar numeros negativos en marcas del eje x
	    g.drawString(" " + (valorX * -1), XOrigen - (valorX * 40), YOrigen+20);
	    */
	}
	int nValoresNeg2 = (YOrigen/40) * -1;
	int nValoresPos2 = (this.getHeight() - YOrigen/40);
	
	
	for (int valorY = nValoresNeg2; valorY <= nValoresPos2; valorY++){
		//Dibujar las marcas del eje Y
		g.drawLine(XOrigen-10, YOrigen + (valorY * 40) , XOrigen + 10, YOrigen + (valorY * 40));
		//Dibujar numeros en marcas del eje Y
    if (valorY!=0){
	    g.drawString(" " + valorY * -1, XOrigen + 20, YOrigen + (valorY * 40));
		}
	}	
	dibujaGraficaSeno(g);
	 graficarFigura(g);
	repaint();
 }
public Color getColorFondo() {
	return colorFondo;
}
public void setColorFondo(Color colorFondo) {
	this.colorFondo = colorFondo;
}


public void dibujaGraficaSeno (Graphics g){
	int factorX = 1, factorY = 100;
	int coordPantX, coordPantY;
	double gradRad, seno;
	g.setColor(Color.BLUE);
	
	for (int grad = 0; grad < 361; grad++){
		
		gradRad = Math.toRadians(grad);
		
		seno = Math.sin(gradRad);
		
		coordPantX = XOrigen + (grad * factorX);
		coordPantY = (int) (YOrigen - (seno * factorY));
		//dibujar el punto (x,y)
		g.fillRect(coordPantX, coordPantY, 5, 5);
	}
	
}
//Clase oyente interna
class accionesRaton extends MouseAdapter implements MouseMotionListener {
	boolean  bandMueveOrigen = false;
	
	@Override
	public void mouseClicked(MouseEvent e) {
		//Click derecho activa modificacion de coord origen
		if (e.getButton() == MouseEvent.BUTTON3){
			//saber si le di click
			if (((e.getX() >= XOrigen-20) && (e.getX() <= XOrigen+20)) && 
			    ((e.getY() >= YOrigen-20) && (e.getY() <= YOrigen+20))){
				bandMueveOrigen = true;
			}
		}//Click derecho deactiva modificacion de coord origen
   else if (e.getButton() == MouseEvent.BUTTON1){
			bandMueveOrigen = false;
		}
		
		/*
		System.out.println("X = " + e.getX() + " Y = " + e.getY()
        + " Origen = (" + XOrigen + " ," + YOrigen + ")"
        + " Botón = " + e.getButton() + " mover = " + bandMueveOrigen);
		*/
		
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		//Si la bandera esta activa cambiar x,y del origen con la posicion actual del cursor
		/*
		System.out.println("Pos (" + e.getX() + "," + e.getY()
        + " Origen = (" + XOrigen + " ," + YOrigen + ")"
        + " Botón = " + e.getButton() + " mover = " + bandMueveOrigen);
		*/
	
		if(bandMueveOrigen){
			XOrigen = e.getX();
			YOrigen = e.getY();
			repaint();
	  }	
	}
	
  }

public void graficarFigura(Graphics g){
    g.setColor(Color.BLUE);
    g.fillOval(OrigenPltX + 185, OrigenPltY + 235,30,30);

}
}
