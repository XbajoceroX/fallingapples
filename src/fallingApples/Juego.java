package fallingApples; 

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;

import javax.imageio.ImageIO;

public class Juego {

	private Personaje personaje;
	private Manzana[] manzanas;
	private Sonido sonido;
	private int alto;

	private int cantidadTotalManzanas;
	private int contadorManzanasPerdidas;
	private int contadorPuntaje;
	private String nombreJugador;
	private boolean seguirJuego;

	JFrameFallingApples frameRecolector;

	public Juego(int cantidadPodridas,int cantidadInmaduras,int cantidadMaduras, int ancho, int alto,JFrameFallingApples frameRecolector)
	{
		nombreJugador = frameRecolector.getNombreJugador();
		sonido = new Sonido();
		this.alto=alto;
		this.frameRecolector = frameRecolector;
		contadorManzanasPerdidas = 0;
		contadorPuntaje=0;
		seguirJuego = true;
		personaje = new Personaje(0, alto-130, alto, ancho);
		sonido.playBackgroundSound();

		cantidadTotalManzanas = cantidadInmaduras+cantidadMaduras+cantidadPodridas;
		manzanas = new Manzana[cantidadTotalManzanas];

		for(int i=0;i<cantidadPodridas;i++)
		{
			manzanas[i] = new Manzana(alto, ancho, 2);
		}
		for(int i=cantidadPodridas;i<cantidadPodridas+cantidadMaduras;i++)
		{
			manzanas[i] = new Manzana(alto, ancho, 1);
		}
		for(int i=cantidadPodridas+cantidadMaduras;i<cantidadTotalManzanas;i++)
		{	
			manzanas[i] = new Manzana(alto, ancho, 0);
		}

	}

	public void empezar()
	{
		if(seguirJuego)
		{
			for (int i=0; i<cantidadTotalManzanas; i++)
				manzanas[i].caer();
		}
	}
	public void dibujar(Graphics g)
	{
		if(seguirJuego)
		{
			for (int i=0; i<cantidadTotalManzanas; i++)
			{
				manzanas[i].dibujar(g,manzanas);
				personaje.dibujar(g);
			}
		}
	}

	public void moverPersonajeDerecha()
	{
		if(seguirJuego)
			personaje.moverIzquierda();
	}
	public void moverPersonajeIzquierda()
	{
		if(seguirJuego)
			personaje.moverDerecha();
	}

	public void verificarColision()
	{
		for(int i=0;i<cantidadTotalManzanas;i++)
		{
			if(personaje.colisiono(manzanas[i]))
			{
				int puntajeManzana= manzanas[i].puntaje;

				manzanas[i].initManzana();
				System.out.println("Comiste manzana: "+puntajeManzana);
				Font f = new Font("Monospaced", Font.BOLD, 28);

				switch(manzanas[i].tipoDeManzana){
				case 0://inmadura
					frameRecolector.modificarMensaje(nombreJugador + " sumaste " + puntajeManzana + " punto!",Color.BLUE,f);
					break;
				case 1://madura
					frameRecolector.modificarMensaje(nombreJugador + " sumaste " + puntajeManzana + " puntos!",Color.BLUE,f);
					break;
				case 2://podrida
					frameRecolector.modificarMensaje(nombreJugador + " perdiste " + puntajeManzana*-1 +" puntos!",Color.RED,f);
					break;
				}

				if(manzanas[i].tipoDeManzana==2)
					sonido.playManzanaPodridaAgarradaSound();
				else
					sonido.playManzanaRecogidaSound();

				gestionarPuntajes(puntajeManzana);
			}

			if(manzanas[i].y>= alto-1 && manzanas[i].tipoDeManzana!=2) //pierdes una manzana buena
			{
				manzanas[i].initManzana();
				contadorManzanasPerdidas++;
				System.out.println("manzanas perdidas"+contadorManzanasPerdidas);
				frameRecolector.modificarManzanasPerdidas(Integer.toString(contadorManzanasPerdidas));
				Font f = new Font("Monospaced", Font.BOLD, 28);
				frameRecolector.modificarMensaje(nombreJugador+" perdiste una manzana!", Color.ORANGE, f);

				gestionarPuntajeManzanasPerdidas();

			}
		}
	}

	private void gestionarPuntajes(int puntajeManzana){
		contadorPuntaje += puntajeManzana;
		frameRecolector.modificarPuntaje(Integer.toString(contadorPuntaje));

		if(contadorPuntaje>=50)
		{
			seguirJuego = false;
			contadorPuntaje =50;

			System.out.println("Ganaste!");
			Font f = new Font("Monospaced", Font.BOLD, 36);
			frameRecolector.modificarMensaje(nombreJugador+" ganaste!",Color.GREEN,f);
			frameRecolector.modificarPuntaje(Integer.toString(contadorPuntaje));
			sonido.stopBackgroundSound();
			sonido.playWinnerSound();
		}
		if(contadorPuntaje<=0)
		{
			contadorPuntaje=0;
			seguirJuego = false;

			System.out.println("Perdiste!");
			Font f = new Font("Verdana", Font.BOLD, 36);
			frameRecolector.modificarMensaje(nombreJugador+" perdiste!",Color.RED,f);
			frameRecolector.modificarPuntaje(Integer.toString(contadorPuntaje));
			sonido.stopBackgroundSound();
			sonido.playDerrotaSound();
		}
	}
	private void gestionarPuntajeManzanasPerdidas()
	{
		if(contadorManzanasPerdidas == 15)
		{
			Font f = new Font("Monospaced", Font.BOLD, 36);
			frameRecolector.modificarMensaje(nombreJugador+" perdiste!",Color.RED,f);
			frameRecolector.modificarManzanasPerdidas(Integer.toString(contadorManzanasPerdidas));
			seguirJuego = false;
			System.out.println("Perdiste!");
			sonido.stopBackgroundSound();
			sonido.playDerrotaSound();
		}

	}
	public void pararLaMusica()
	{
		sonido.stopBackgroundSound();
	}
}
