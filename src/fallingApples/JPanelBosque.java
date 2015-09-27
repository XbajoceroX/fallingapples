package fallingApples;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.Timer;

public class JPanelBosque extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Timer timer;
	private BufferedImage image;
	private Image sImage;
	private double alto;
	private double ancho;
	private JFrameFallingApples frameFallingApples;
	private Juego juego;
	private KeyStroke k;

	/**
	 * Create the panel.
	 * 
	 */
	public JPanelBosque(double ancho,double alto,JFrameFallingApples frameRecolector) {
		this.alto = alto;
		this.ancho = ancho;
		frameFallingApples = frameRecolector;
		//leerTeclado();
		this.setKey(KeyEvent.VK_LEFT, -5);
		this.setKey(KeyEvent.VK_RIGHT, 5);
		try 
		{
			image = ImageIO.read(new File("img/inicioJuego.png"));
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sImage = image.getScaledInstance((int)ancho, (int)alto, Image.SCALE_SMOOTH);

	}

	private void setKey(int code, final int dx)
	{
		this.k = KeyStroke.getKeyStroke(code, 0);

		this.getInputMap(WHEN_IN_FOCUSED_WINDOW).put(k, k.toString());

		this.getActionMap().put(k.toString(), new AbstractAction() {
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
				System.out.println("Key code: "+dx);
				if(dx == -5)
				{
					juego.moverPersonajeIzquierda();
				}
				if(dx == 5)
				{
					juego.moverPersonajeDerecha();
				}
				repaint();
			}
		});	
	}

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(sImage, 0, 0, null);
		if(juego!=null)
			juego.dibujar(g);
	}

	public void empezarJuego()
	{
		ActionListener actionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				juego.empezar();
				juego.verificarColision();
				repaint();
			}
		};
		timer = new Timer(10, actionListener);
		juego = new Juego(2, 5, 5, (int)ancho,(int) alto,frameFallingApples);

		try 
		{
			image = ImageIO.read(new File("img/background.jpg"));
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sImage = image.getScaledInstance((int)ancho, (int)alto, Image.SCALE_SMOOTH);
		
		timer.start();
	}
	public void pararJuego()
	{
		timer.stop();
		juego.pararLaMusica();
	}


}


