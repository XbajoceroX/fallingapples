package fallingApples;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Manzana {
	
	public double x;
	public double y;
	private double velocidad;
	
	public int tipoDeManzana;
	private int delay;
	private int ancho;
	private int alto;
	public int size;
	public int puntaje;
	
	private BufferedImage image;
	private Image sImage;
	
	public int posicionesX[];
	
	public Manzana(int alto,int ancho,int tipoDeManzana)
	{
		this.alto = alto;
		this.ancho = ancho;
		this.tipoDeManzana = tipoDeManzana;
		initManzana();
		velocidad=1;
		
		try 
		{
			switch(tipoDeManzana){
		case 0://inmadura
			image = ImageIO.read(new File("img/inmadura.png"));
			break;
		case 1://madura
			image = ImageIO.read(new File("img/madura.png"));
			break;
		case 2://podrida
			image = ImageIO.read(new File("img/podrida.png"));
			break;
			}
		}
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sImage = image.getScaledInstance(size, size, Image.SCALE_SMOOTH);
	}
	
	public void dibujar(Graphics g, Manzana[] manzanas)
	{
		boolean colisiona = this.colisionaConOtrasManzanas(manzanas);
		if(delay == 0 && !colisiona)
			g.drawImage(sImage,(int) x, (int)y, null);
		if(delay == 0 && colisiona)
		{
			initManzana();
			delay=0;
		}
	}
	
	public void caer()
	{
		if(delay>0)
			delay--;
		else if(y<=alto)
			y+=velocidad;
		else
			initManzana();
	}
	
	public void initManzana()
	{
		Random r = new Random();
		y = r.nextInt(50);
		System.out.println("volver a generar x");
		
		x = r.nextInt(ancho)-size;
		
		velocidad = regularVelocidad(y);
		delay = r.nextInt(alto*2) + 1;
		
		switch (tipoDeManzana) {
			case 0:
				size = 30;
				puntaje = 1;
				break;
			case 1:
				size = 50;
				puntaje = 2;
				break;
			case 2:
				size = 50;
				puntaje = -3;
				break;
		}
		
	}
	
	private double regularVelocidad(double y)
	{
		double miVelocidad = 0.5;
		if(y>=0&&y<=15)
			miVelocidad=1.8;
		if(y>=16&&y<=30)
			miVelocidad=1.2;
		if(y>=31&&y<=50)
			miVelocidad=0.7;
		return miVelocidad;
	}
	
	public boolean colisionaConOtrasManzanas(Manzana[] manzanas)
	{
		boolean colisiona = false;
		for(int i=0;i<manzanas.length;i++)
		{
			if(this.colisiono(manzanas[i]) && manzanas[i]!= this && manzanas[i].delay==0)
			{
				System.out.println("Tipo manzana colisionada "+manzanas[i].tipoDeManzana);
				colisiona=true;
				i=manzanas.length;
			}
		}
		return colisiona;
	}
	
	public boolean colisiono(Manzana m)
	{
		if(x<m.x)
		{
			if(x+size >= m.x && m.y + m.size >= y)
				return true;
		}
		else
		{
			if(m.x+m.size>=x && m.y+m.size>=y)
				return true;
		}
		return false;
	}
	
	
}
