package org.liceolapaz.des.mll;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class Mina extends JButton implements ActionListener{
	private int contadorMinas, estado;//0 tapado 1 destapado
	private boolean tieneMina,marcado;
	
	private final ImageIcon mina = new ImageIcon("img/mina.gif");
	private final ImageIcon bandera = new ImageIcon("img/bandera.png");
	private final ImageIcon uno=new ImageIcon("img/1.png");
	private final ImageIcon dos=new ImageIcon("img/2.png");
	private final ImageIcon tres=new ImageIcon("img/3.png");
	private final ImageIcon cuatro=new ImageIcon("img/4.png");
	private final ImageIcon cinco=new ImageIcon("img/5.png");
	private final ImageIcon seis=new ImageIcon("img/6.png");
	private final ImageIcon siete=new ImageIcon("img/7.png");
	private final ImageIcon ocho=new ImageIcon("img/8.png");
	
	private Mina norte, noreste, este, sureste, sur, suroeste, oeste,noroeste;
	private Tablero tablero;
	
	public Mina (Tablero t){
		estado=0;
		tablero=t;
		tieneMina=false;
		marcado=false;
		contadorMinas=0;
		this.addActionListener(this);
		this.addMouseListener(new EscuchadorBoton());
	}

	public void vecinos(Mina n, Mina ne,Mina e,Mina se,Mina s,Mina so, Mina o, Mina no){
		norte=n;
		noreste=ne;
		este=e;
		sureste=se;
		sur=s;
		suroeste=so;
		oeste=o;
		noroeste=no;
	}

	public int sumarMinas(){
		contadorMinas=0;
		if(norte!=null&&norte.tieneMina()==true){
			contadorMinas++;
		}
		if(noreste!=null&&noreste.tieneMina()==true){
			contadorMinas++;
		}
		if(este!=null&&este.tieneMina()==true){
			contadorMinas++;
		}
		if(sureste!=null&&sureste.tieneMina()==true){
			contadorMinas++;
		}
		if(sur!=null&&sur.tieneMina()==true){
			contadorMinas++;
		}
		if(suroeste!=null&&suroeste.tieneMina()==true){
			contadorMinas++;
		}
		if(oeste!=null&&oeste.tieneMina()==true){
			contadorMinas++;
		}
		if(noroeste!=null&&noroeste.tieneMina()==true){
			contadorMinas++;
		}
		return contadorMinas;
	}

	public void conmutar(){
		if (estado==0&&marcado==false){
			if(tieneMina==false){
				this.setBackground(Color.WHITE);
				if(this.sumarMinas()>0){
					this.insertarIconos();
				}
			}else{
				this.setBackground(Color.LIGHT_GRAY);
				this.setIcon(mina);
				this.setEnabled(false);
			}
			estado=1;
			if(this.sumarMinas()==0&&tieneMina==false)
			{
				if (norte!=null) 
					norte.conmutar();
				if (sur!=null) 
					sur.conmutar();
				if (este!=null) 
					este.conmutar();
				if (oeste!=null) 
					oeste.conmutar();
				if (noreste!=null) 
					noreste.conmutar();
				if (noroeste!=null) 
					noroeste.conmutar();
				if (sureste!=null) 
					sureste.conmutar();
				if (suroeste!=null) 
					suroeste.conmutar();
				this.setEnabled(false);
			}
		}
	}

	public void conmutarMarcado(){
		if (estado==0&&marcado==true){	
			tablero.getVentana().adelantar();
			if(tieneMina==false){
				this.setBackground(Color.WHITE);
				this.setIcon(null);
				if(this.sumarMinas()>0){
					this.insertarIconos();
				}
			}else{
				this.setBackground(Color.LIGHT_GRAY);
				this.setIcon(mina);
				this.setEnabled(false);
			}
			estado=1;
			if(this.sumarMinas()==0&&tieneMina==false)
			{
				if (norte!=null) 
					norte.conmutar();
				if (sur!=null) 
					sur.conmutar();
				if (este!=null) 
					este.conmutar();
				if (oeste!=null) 
					oeste.conmutar();
				if (noreste!=null) 
					noreste.conmutar();
				if (noroeste!=null) 
					noroeste.conmutar();
				if (sureste!=null) 
					sureste.conmutar();
				if (suroeste!=null) 
					suroeste.conmutar();
				this.setEnabled(false);
			}
		}
	}
	
	public void mostrarTodo(){
		if (Mina.this!=null){
			Mina.this.conmutar();
		}
		if(norte!=null){
			norte.conmutar();
		}
		if(noreste!=null){
			noreste.conmutar();
		}
		if(este!=null){
			este.conmutar();
		}
		if(sureste!=null){
			sureste.conmutar();
		}
		if(sur!=null){
			sur.conmutar();
		}
		if(suroeste!=null){
			suroeste.conmutar();
		}
		if(oeste!=null){
			oeste.conmutar();
		}
		if(noroeste!=null){
			noroeste.conmutar();
		}
		this.setEnabled(false);
	}

	public void establecerBandera(boolean m){
		marcado=m;
		if(marcado==true&&estado==0){
			marcado=true;
			this.setIcon(bandera);
			tablero.getVentana().retrasar();
		}else if(marcado==false&&estado==0){
			marcado=false;
			this.setIcon(null);
			tablero.getVentana().adelantar();
		}
	}

	public void insertarIconos(){
		if(contadorMinas==1)
			this.setIcon (uno);
		if(contadorMinas==2)
			this.setIcon(dos);
		if(contadorMinas==3)
			this.setIcon(tres);
		if(contadorMinas==4)
			this.setIcon(cuatro);
		if(contadorMinas==5)
			this.setIcon(cinco);
		if(contadorMinas==6)
			this.setIcon(seis);
		if(contadorMinas==7)
			this.setIcon(siete);
		if(contadorMinas==8)
			this.setIcon(ocho);	
	}
	
	public void actionPerformed(ActionEvent e) {
		tablero.asignarVecinos();
		if(this.tieneMina()==true)
			tablero.perder();
	}

	private class EscuchadorBoton extends MouseAdapter
	{
		public void mouseClicked(MouseEvent e)
		{
			switch(e.getButton())
			{
			case 1:
				conmutar();
				conmutarMarcado();
				break;
			case 3:
				if(getMarcado()==false)
					establecerBandera(true);
				else
					establecerBandera(false);
				break;
			}
			tablero.ganar();
		}
	}
	
	public boolean tieneMina() {
		return tieneMina;
	}

	public void establecerMina(boolean minaA){
		tieneMina = minaA;
	}

	public int getEstado(){
		return estado;
	}

	public void setEstado(int es) {
		estado = es;
	}
	
	public Boolean getMarcado() {
		return marcado;
	}
}

