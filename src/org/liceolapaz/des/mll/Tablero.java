package org.liceolapaz.des.mll;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.*;

public class Tablero extends JPanel{
	int filas,columnas,minas,destapados, marcados,vaciosRestantes;
	
	public Thread hilo;
	
	private Mina[][] botones;
	private Mina norte,noreste,este,sureste,sur,suroeste,oeste,noroeste;
	private Ventana ventana;

	public Tablero(int f,int c, int m,Ventana v){
		filas=f;
		columnas=c;
		minas=m;
		ventana=v;
		vaciosRestantes=f*c-m;
		this.setLayout(new GridLayout(f,c));
		botones=new Mina [f][c];
		for (int fi=0;fi<filas;fi++){
			for (int co=0;co<columnas;co++){
				botones[fi][co]=new Mina(this);
				this.add(botones[fi][co]);
			}
		}
		ponerMinas(m);
		hilo=new Thread(new HiloTiempo());
		hilo.start();
	}

	public void ponerMinas(int m){
		minas=m;
		int minasColocadas=0;
		Random aleatorio=new Random(Calendar.getInstance().getTimeInMillis());
		while(minasColocadas<minas){
			int f=aleatorio.nextInt(filas);
			int c=aleatorio.nextInt(columnas);
			if (botones[f][c].tieneMina()==false){
				botones[f][c].establecerMina(true);
				minasColocadas++;
			}
		}
	}

	public void perder(){
		for(int x=0;x<filas;x++)
			for(int y=0;y<columnas;y++){
				botones[x][y].mostrarTodo();
			}
		JDialog perder=new JDialog(ventana, "FIN DEL JUEGO");
		perder.setSize(300, 100);
		JLabel per=new JLabel ("¡¡OHHHHHHH!!\n ¡¡HAS ENCONTRADO UNA MINA!!");
		perder.add(per);
		perder.setVisible(true);
	}

	public void ganar(){
		marcados  = 0;
		destapados = 0;
		for(int f=0;f<filas;f++){
			for(int c=0;c<columnas;c++){
				if(botones[f][c].getEstado()!=1) {
					if(botones[f][c].getMarcado()==true) {
						marcados++;
					}
				} else {
					destapados++;
				}
			}
			if (marcados == minas && destapados == vaciosRestantes) {
				for(int fi=0;fi<filas;fi++){
					for(int co=0;co<columnas;co++){
						botones[fi][co].mostrarTodo();
					}
				}
				JDialog ganar=new JDialog(ventana, "ENHORABUENA");
				ganar.setSize(300, 100);
				JLabel gan=new JLabel ("¡¡HAS GANADO!!");
				ganar.add(gan);
				ganar.setVisible(true);
			}
		}
	}

	public void asignarVecinos(){
		for (int x=0; x<filas; x++){
			for (int y=0; y<columnas; y++){	
				if (x>0) //primera fila
					norte=botones[x-1][y];
				else
					norte=null;
				if(x<(filas-1))	//última fila
					sur=botones[x+1][y];
				else
					sur=null;
				if(y>0) //primera columna
					oeste=botones[x][y-1];
				else
					oeste=null;
				if(y<(columnas-1)) //última columna
					este=botones[x][y+1];
				else
					este=null;
				if(x>0&&y>0)
					noroeste=botones[x-1][y-1];
				else
					noroeste=null;
				if(y>0&&x<(filas-1))
					suroeste=botones[x+1][y-1];
				else
					suroeste=null;
				if(x<(filas-1)&&y<(columnas-1))
					sureste=botones[x+1][y+1];
				else
					sureste=null;
				if(y<(columnas-1)&&x>0)
					noreste=botones[x-1][y+1];
				else
					noreste=null;
				botones[x][y].vecinos(norte,noreste, este, sureste, sur, suroeste, oeste, noroeste);
			}
		}
	}
	
	private class HiloTiempo implements Runnable
	{
		public void run()
		{
			while(true)
				try {
					Thread.sleep(1000);
					ventana.nuevoSegundo();
				}catch (Exception e) { }
		}
	}

	public int getFilas() {
		return filas;
	}

	public void setFilas(int fi) {
		filas = fi;
	}

	public int getColumnas() {
		return columnas;
	}

	public void setColumnas(int co) {
		columnas = co;
	}

	public Ventana getVentana(){
		return ventana;
	}
	
	public int getDestapados() {
		return destapados;
	}

	public void setDestapados(int des) {
		destapados = des;
	}
	
	public int getMarcados() {
		return marcados;
	}

	public void setMarcados(int ma) {
		marcados = ma;
	}

	public int getnMinas() {
		return minas;
	}

	public void setnMinas(int mi) {
		minas = mi;
	}
}







