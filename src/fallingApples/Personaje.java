package fallingApples;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Personaje {
	
	private int x;
	private int y;
	private int largo;
	private int ancho;
	
	private int sizex;
	
	private String pathImagenDerecha;
	private String pathImagenIzquierda;
	
	private BufferedImage image;
	public Image sImage;
	
	
	Personaje(int x,int y,int largo,int ancho)
	{
		
		this.x = x;
		this.y = y;
		this.largo = largo;
		this.ancho = ancho;
		sizex = 70;
		pathImagenDerecha = "img/recolectorPequenoDerecha.png";
		pathImagenIzquierda = "img/recolectorPequenoIzquierda.png";
		
		setImagen(pathImagenDerecha, largo, ancho);
		
		
	}
	
	public void dibujar(Graphics g)
	{
		g.drawImage(image, x, y, null);
	}
	
	public void moverDerecha()
	{
		setImagen(pathImagenDerecha, largo, ancho);
		x+=10;
		
		if(x>ancho)
			x=-80;
	}
	
	public void moverIzquierda()
	{
		setImagen(pathImagenIzquierda, largo, ancho);
		x-=10;
		
		if(x<-80)
			x=ancho;
		
	}
	
	public void setImagen(String path,int largo,int ancho)
	{
		try 
		{
			image = ImageIO.read(new File(path));
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sImage = image.getScaledInstance(largo, ancho, Image.SCALE_SMOOTH);
	}
	
	public boolean colisiono(Manzana m)
	{
		if(x<m.x)
		{
			if(x+sizex >= m.x && m.y + m.size >= y)
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
