package fallingApples;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTextField;

public class JFrameFallingApples extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanelBosque panelBosque;
	private JLabel lblPuntaje;
	private JLabel labelPuntaje;
	private JLabel lblManzanasPerdidas;
	private JLabel labelManzanasPerdidas;
	private JButton botonSalir;
	private JLabel labelMessage;
	private JTextField textFieldNombre;
	private JButton botonJugar;
	private JLabel lblIngreseSuNombre;
	private String nombreDeJugador;
	private JButton botonReiniciar;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameFallingApples frame = new JFrameFallingApples();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFrameFallingApples() {
		
		Dimension tamanoPantalla = Toolkit.getDefaultToolkit().getScreenSize();
		int tamanoInicioWindows = 35;
		int tamanoMenuPrograma = 100;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, tamanoPantalla.width,tamanoPantalla.height-tamanoInicioWindows);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		double width = tamanoPantalla.getWidth();
		double height = tamanoPantalla.getHeight();
		
		this.setLocation((int)((width-this.getWidth())/2),0);
		
		panelBosque = new JPanelBosque(width,tamanoPantalla.height-tamanoInicioWindows-tamanoMenuPrograma, this);
		panelBosque.setBackground(Color.GRAY);
		panelBosque.setBounds(0, 0, tamanoPantalla.width,tamanoPantalla.height-tamanoInicioWindows-tamanoMenuPrograma);

		contentPane.add(panelBosque);
		
		this.lblPuntaje = new JLabel("Puntaje:");
		this.lblPuntaje.setBounds(10, 669, 46, 14);
		this.lblPuntaje.setVisible(false);
		this.contentPane.add(this.lblPuntaje);
		
		this.labelPuntaje = new JLabel("0");
		this.labelPuntaje.setFont(new Font("Tahoma", Font.PLAIN, 40));
		this.labelPuntaje.setBounds(66, 644, 46, 40);
		this.labelPuntaje.setVisible(false);
		this.contentPane.add(this.labelPuntaje);
		
		this.lblManzanasPerdidas = new JLabel("Manzanas Perdidas:");
		this.lblManzanasPerdidas.setBounds(114, 669, 122, 14);
		this.lblManzanasPerdidas.setVisible(false);
		this.contentPane.add(this.lblManzanasPerdidas);
		
		this.labelManzanasPerdidas = new JLabel("0");
		this.labelManzanasPerdidas.setFont(new Font("Tahoma", Font.PLAIN, 40));
		this.labelManzanasPerdidas.setBounds(231, 644, 46, 40);
		this.labelManzanasPerdidas.setVisible(false);
		this.contentPane.add(this.labelManzanasPerdidas);
		
		this.botonSalir = new JButton("Salir");
		botonSalir.addActionListener(this);
		this.botonSalir.setBounds(1237, 671, 89, 23);
		this.contentPane.add(this.botonSalir);
		
		this.labelMessage = new JLabel("");
		this.labelMessage.setBounds(600, 644, 617, 39);
		this.labelMessage.setVisible(false);
		this.contentPane.add(this.labelMessage);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setBounds(456, 644, 132, 20);
		contentPane.add(textFieldNombre);
		textFieldNombre.setColumns(10);
		
		botonJugar = new JButton("Jugar");
		botonJugar.addActionListener(this);
		botonJugar.setBounds(616, 643, 89, 23);
		contentPane.add(botonJugar);
		
		lblIngreseSuNombre = new JLabel("Ingrese su nombre:");
		lblIngreseSuNombre.setBounds(308, 647, 116, 14);
		contentPane.add(lblIngreseSuNombre);
		
		botonReiniciar = new JButton("Reiniciar");
		botonReiniciar.addActionListener(this);
		botonReiniciar.setBounds(1237, 644, 89, 23);
		contentPane.add(botonReiniciar);
	}
	
	public void modificarPuntaje(String puntaje)
	{
		labelPuntaje.setText(puntaje);
	}
	
	public void modificarManzanasPerdidas(String cantidadManzanas)
	{
		labelManzanasPerdidas.setText(cantidadManzanas);
	}
	public void modificarMensaje(String msj,Color color,Font f )
	{
		labelMessage.setFont(f);
		labelMessage.setForeground(color);
		labelMessage.setText(msj);
	}
	public String getNombreJugador()
	{
		return nombreDeJugador;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
		if (arg0.getSource()==botonSalir)
		{
			System.out.println("Terminando...");
			System.exit(0);
		}
		if(arg0.getSource()==botonJugar)
		{
			String nombre= textFieldNombre.getText();
			if(nombre.isEmpty())
			{
				JOptionPane.showMessageDialog(this, "Ingrese su nombre para poder jugar.");
			}
			else
			{
				nombreDeJugador = nombre;
				JOptionPane.showMessageDialog(this, "Bienvenido al juego Falling Apples!");
				this.textFieldNombre.setVisible(false);
				this.lblIngreseSuNombre.setVisible(false);
				this.botonJugar.setVisible(false);

				this.lblPuntaje.setVisible(true);
				this.labelPuntaje.setVisible(true);
				this.lblManzanasPerdidas.setVisible(true);
				this.labelManzanasPerdidas.setVisible(true);
				this.labelMessage.setVisible(true);
				panelBosque.empezarJuego();
			}
		}
		if(arg0.getSource()==botonReiniciar)
		{
			
			panelBosque.pararJuego();
			panelBosque.empezarJuego();
			contentPane.remove(panelBosque);
			panelBosque.setFocusable(true);
			contentPane.add(panelBosque);
			labelMessage.setText("");
			labelManzanasPerdidas.setText("0");
			labelPuntaje.setText("0");
		}
	}
}
