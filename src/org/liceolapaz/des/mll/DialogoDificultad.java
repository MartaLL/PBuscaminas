package org.liceolapaz.des.mll;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DialogoDificultad extends JDialog{
	private int valorFi, valorCo, valorMi;
	private String textoFi, textoCo, textoMi, nombre;
	
	private JRadioButton facil, medio, dificil, personalizado;
	private JLabel filas, columnas, minas;
	private JTextField fi, co, mi; 
	private JButton aceptar, cancelar;
	
	private Ventana ventana;
	
	public DialogoDificultad(Ventana v,String n){
		ventana=v;
		nombre=n;
		this.setSize(300,150);
		this.setResizable(false);
		this.setTitle("Nivel de dificultad");
		this.getContentPane().setLayout(new GridBagLayout());
		facil=new JRadioButton("Fácil");
		GridBagConstraints constraintsFacil=new GridBagConstraints();
		constraintsFacil.gridx=1;
		constraintsFacil.gridy=0;
		constraintsFacil.gridwidth=1;
		constraintsFacil.gridheight=1;
		this.getContentPane().add(facil,constraintsFacil);
		medio=new JRadioButton("Medio");
		GridBagConstraints constraintsMedio=new GridBagConstraints();
		constraintsMedio.gridx=2;
		constraintsMedio.gridy=0;
		constraintsMedio.gridwidth=1;
		constraintsMedio.gridheight=1;
		this.getContentPane().add(medio,constraintsMedio);
		dificil=new JRadioButton("Difícil");
		GridBagConstraints constraintsDificil=new GridBagConstraints();
		constraintsDificil.gridx=3;
		constraintsDificil.gridy=0;
		constraintsDificil.gridwidth=1;
		constraintsDificil.gridheight=1;
		this.getContentPane().add(dificil,constraintsDificil);
		personalizado=new JRadioButton("Personalizado");
		GridBagConstraints constraintsPersonalizado=new GridBagConstraints();
		constraintsPersonalizado.gridx=4;
		constraintsPersonalizado.gridy=0;
		constraintsPersonalizado.gridwidth=1;
		constraintsPersonalizado.gridheight=1;
		this.getContentPane().add(personalizado,constraintsPersonalizado);
		ButtonGroup grupo = new ButtonGroup();
		grupo.add(facil);
		grupo.add(medio);
		grupo.add(dificil);
		grupo.add(personalizado);

		do{
			personalizado.doClick();
		}while(this.isShowing());

		facil.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){

				if(facil.isSelected()){
					fi.setText("10");
					co.setText("10");
					mi.setText("10");
					fi.setEditable(false);
					co.setEditable(false);
					mi.setEditable(false);
				}
			}
		});
		medio.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				if(medio.isSelected()){
					fi.setText("15");
					co.setText("15");
					mi.setText("30");
					fi.setEditable(false);
					co.setEditable(false);
					mi.setEditable(false);
				}
			}
		});
		dificil.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				if(dificil.isSelected()){
					fi.setText("20");
					co.setText("20");
					mi.setText("40");
					fi.setEditable(false);
					co.setEditable(false);
					mi.setEditable(false);
				}
			}
		});
		personalizado.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				if(personalizado.isSelected()){
					fi.setText(" ");
					co.setText(" ");
					mi.setText(" ");
					fi.setEditable(true);
					co.setEditable(true);
					mi.setEditable(true);
				}
			}
		});
		filas=new JLabel("Filas");
		GridBagConstraints constraintsFilas=new GridBagConstraints();
		constraintsFilas.gridx=0;
		constraintsFilas.gridy=1;
		constraintsFilas.gridwidth=1;
		constraintsFilas.gridheight=1;
		this.getContentPane().add(filas,constraintsFilas);
		fi=new JTextField();
		GridBagConstraints constraintsFi=new GridBagConstraints();
		constraintsFi.gridx=1;
		constraintsFi.gridy=1;
		constraintsFi.gridwidth=1;
		constraintsFi.gridheight=1;
		constraintsFi.anchor = GridBagConstraints.WEST;
		constraintsFi.fill = GridBagConstraints.BOTH;
		constraintsFi.weighty = 1.0;
		this.getContentPane().add(fi,constraintsFi);
		constraintsFi.weighty = 0.0;
		fi.setHorizontalAlignment(SwingConstants.CENTER);
		columnas=new JLabel("Columnas");
		GridBagConstraints constraintsColumnas=new GridBagConstraints();
		constraintsColumnas.gridx=2;
		constraintsColumnas.gridy=1;
		constraintsColumnas.gridwidth=1;
		constraintsColumnas.gridheight=1;
		this.getContentPane().add(columnas,constraintsColumnas);
		co=new JTextField();
		GridBagConstraints constraintsCo=new GridBagConstraints();
		constraintsCo.gridx=3;
		constraintsCo.gridy=1;
		constraintsCo.gridwidth=1;
		constraintsCo.gridheight=1;
		constraintsCo.anchor = GridBagConstraints.WEST;
		constraintsCo.fill = GridBagConstraints.BOTH;
		constraintsCo.weighty = 1.0;
		this.getContentPane().add(co,constraintsCo);
		constraintsCo.weighty = 0.0;
		co.setHorizontalAlignment(SwingConstants.CENTER);
		minas=new JLabel("Minas");
		GridBagConstraints constraintsMinas=new GridBagConstraints();
		constraintsMinas.gridx=4;
		constraintsMinas.gridy=1;
		constraintsMinas.gridwidth=1;
		constraintsMinas.gridheight=1;
		this.getContentPane().add(minas,constraintsMinas);
		mi=new JTextField();
		GridBagConstraints constraintsMi=new GridBagConstraints();
		constraintsMi.gridx=5;
		constraintsMi.gridy=1;
		constraintsMi.gridwidth=1;
		constraintsMi.gridheight=1;
		constraintsMi.anchor = GridBagConstraints.WEST;
		constraintsMi.fill = GridBagConstraints.BOTH;
		constraintsMi.weighty = 1.0;
		this.getContentPane().add(mi,constraintsMi);
		constraintsMi.weighty = 0.0;
		mi.setHorizontalAlignment(SwingConstants.CENTER);
		aceptar=new JButton("Aceptar");
		GridBagConstraints constraintsA=new GridBagConstraints();
		constraintsA.gridx=0;
		constraintsA.gridy=2;
		constraintsA.gridwidth=1;
		constraintsA.gridheight=1;
		this.getContentPane().add(aceptar,constraintsA);
		aceptar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				textoFi=fi.getText();
				valorFi=(int) Float.parseFloat(textoFi);
				textoCo=co.getText();
				valorCo=(int)Float.parseFloat(textoCo);
				textoMi=mi.getText();
				valorMi=(int)Float.parseFloat(textoMi);
				if(valorMi>valorFi*valorCo){
					JDialog dialogo = new JDialog(ventana, "ERROR");
					dialogo.setSize(700,100);
					JLabel minas = new JLabel ("El número de minas debe ser inferior o igual a "+valorFi*valorCo + " para el numero de filas = "+valorFi +" y para el numero de columnas = "+valorCo);
					dialogo.add(minas);
					dialogo.setVisible(true);
				}else{
					ventana.eliminar();
					if(facil.isSelected()){
						ventana.nuevoFacil();
					}
					if(medio.isSelected()){
						ventana.nuevoMedio();
					}
					if(dificil.isSelected()){
						ventana.nuevoDificil();
					}
					if(personalizado.isSelected()){
						ventana.nuevoJuego(valorFi,valorCo,valorMi);	
					}
				}
				dispose();
			}
		});
		cancelar=new JButton("Cancelar");
		GridBagConstraints constraintsC=new GridBagConstraints();
		constraintsC.gridx=5;
		constraintsC.gridy=2;
		constraintsC.gridwidth=1;
		constraintsC.gridheight=1;
		this.getContentPane().add(cancelar,constraintsC);
		cancelar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				dispose();
			}
		});
		this.setVisible(true);
		this.pack();
	}
}

