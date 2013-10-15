package org.liceolapaz.des.mll;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Ventana extends JFrame{
	private int contadorMinas,contadorTiempo,filas,columnas,minas;
	boolean jugando;
	private String ruta;
	
	private JPanel p,p1;
	private JMenuBar menu;
	private JMenu partida,opciones;
	private JMenuItem nueva,guardar,cargar,salir,dificultad;
	private JCheckBox resultados;
	private JLabel bus,pulsa,autor,tiempo1,numeroT,mina1;
	private JLabel numeroM;
	private JButton jugar;
	
	private final ImageIcon minaPrincipal = new ImageIcon("img/minaP.jpg");
	private final ImageIcon nuevaP = new ImageIcon("img/nueva.gif");
	private final ImageIcon cargarP = new ImageIcon ("img/cargar.png");
	private final ImageIcon guardarP = new ImageIcon ("img/guardar.png");
	private final ImageIcon salirP = new ImageIcon ("img/salir.jpg");
	private final ImageIcon dificultadD = new ImageIcon ("img/dificultad.png");
	private final ImageIcon tiempo=new ImageIcon("img/tiempo.png");
	private final ImageIcon mina=new ImageIcon("img/mina.gif");
	
	private Tablero t;

	public Ventana(){
		this.setSize(300,350);
		this.setResizable(false);
		this.setTitle("Juego del Buscaminas");

		p=new JPanel();
		this.add(p);
		p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
		p.setAlignmentX(Component.CENTER_ALIGNMENT);
		p.setAlignmentY(Component.CENTER_ALIGNMENT);
		p.setVisible(true);

		bus=new JLabel("Buscaminas");
		p.add(bus);
		bus.setAlignmentX(Component.CENTER_ALIGNMENT);
		bus.setAlignmentY(Component.CENTER_ALIGNMENT);

		jugar=new JButton();
		jugar.setIcon(minaPrincipal);
		jugar.setBackground(new Color(255,255,255));
		p.add(jugar);
		jugar.setAlignmentX(Component.CENTER_ALIGNMENT);
		jugar.setAlignmentY(Component.CENTER_ALIGNMENT);
		jugar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				eliminarP();
				añadir(10,10,10);
				numeroT.setText(""+0);
			}
		});

		pulsa=new JLabel("Pulsa en la imagen para empezar a jugar");
		p.add(pulsa);
		pulsa.setAlignmentX(Component.CENTER_ALIGNMENT);
		pulsa.setAlignmentY(Component.CENTER_ALIGNMENT);

		autor=new JLabel("Autor: Marta Losada López");
		p.add(autor);
		autor.setAlignmentX(Component.CENTER_ALIGNMENT);
		autor.setAlignmentY(Component.CENTER_ALIGNMENT);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	public void eliminar(){
		this.remove(t);//se elimina el trablero actual
		t.hilo.stop();
	}

	public void eliminarP(){
		remove(p);
		p.setVisible(false);
	}

	public void guardar(){
		JFileChooser guardar=new JFileChooser();
		int valor = guardar.showOpenDialog(this);
		guardar.setCurrentDirectory(new File("partidas"));
		if(valor == JFileChooser.APPROVE_OPTION) {
			ruta = guardar.getSelectedFile().getAbsolutePath();
			File a=new File(ruta);
			try {
				FileWriter fw = new FileWriter(ruta, true);
				BufferedWriter br=new BufferedWriter(fw);
				PrintWriter pw = new PrintWriter(br);
				pw.println("Filas "+ filas + " Columnas "+ columnas +" Minas "+minas+" Tiempo "+contadorTiempo);
				pw.close();
				br.close();
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	public void cargar(){	
		JFileChooser cargar=new JFileChooser();
		int valor = cargar.showOpenDialog(this);
		cargar.setCurrentDirectory(new File("partidas"));
		if(valor == JFileChooser.APPROVE_OPTION) {
			ruta = cargar.getSelectedFile().getAbsolutePath();
			File fi=new File(ruta);
			try {
				FileReader fr = new FileReader(fi);
				BufferedReader br = new BufferedReader(fr);
				while (true)
				{

				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void añadir(int fi,int co,int mi){
		minas=mi;
		filas=fi;
		columnas=co;
		jugando=true;
		contadorTiempo=0;
		setLayout(new BorderLayout()); 	
		t=new Tablero(fi,co,mi,this);
		this.add(t);
		this.setSize(900,720);
		t.setBorder(BorderFactory.createLineBorder(Color.black,3)); 
		this.revalidate();
		t.setVisible(true);

		menu=new JMenuBar();
		this.setJMenuBar(menu);

		partida=new JMenu("Partida");
		menu.add(partida);

		nueva=new JMenuItem("Nueva partida",KeyEvent.VK_N);
		nueva.setIcon(nuevaP);
		nueva.setMnemonic(KeyEvent.VK_N);
		nueva.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
		nueva.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				eliminar();
				nuevoFacil();
			}
		});
		partida.add(nueva);

		guardar=new JMenuItem("Guardar partida",KeyEvent.VK_G);
		guardar.setIcon(guardarP);
		guardar.setMnemonic(KeyEvent.VK_G);
		guardar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G,ActionEvent.CTRL_MASK));
		guardar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				guardar();
			}
		});
		partida.add(guardar);

		cargar=new JMenuItem("Cargar partida",KeyEvent.VK_C);
		cargar.setIcon(cargarP);
		cargar.setMnemonic(KeyEvent.VK_C);
		cargar.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
		cargar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				cargar();
			}
		});
		partida.add(cargar);
		partida.addSeparator();

		salir=new JMenuItem("Salir",KeyEvent.VK_S);
		salir.setIcon(salirP);
		salir.setMnemonic(KeyEvent.VK_S);
		salir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
		salir.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				System.exit(EXIT_ON_CLOSE);
			}
		});
		partida.add(salir);

		opciones=new JMenu("Opciones");
		menu.add(opciones);

		resultados=new JCheckBox("Almacenar resultados");
		resultados.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				if(resultados.isSelected()){
					try {
						FileWriter res = new FileWriter("resultados\\resultados.txt", true);
						BufferedWriter br=new BufferedWriter(res);
						PrintWriter pw = new PrintWriter(br);
						pw.println("Nivel Fácil: "+"Filas "+filas+"| Columnas "+columnas+"| Minas "+minas+"| Tiempo "+contadorTiempo+"| Botones destapados "+t.getDestapados()+"| Botones marcados "+t.getMarcados());
						pw.close();
					} catch (FileNotFoundException ex) {
						ex.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		opciones.add(resultados);

		dificultad=new JMenuItem("Nivel de dificultad");
		dificultad.setIcon(dificultadD);
		dificultad.setMnemonic(KeyEvent.VK_D);
		dificultad.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,ActionEvent.CTRL_MASK));
		dificultad.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e){
				DialogoDificultad dificultad=new DialogoDificultad(Ventana.this,"Dificultad");
			}
		});
		opciones.add(dificultad);

		add(t);

		p1 = new JPanel();
		p1.setLayout(new FlowLayout());

		mina1=new JLabel();
		mina1.setIcon(mina);
		p1.add(mina1);

		minas=t.getnMinas();
		numeroM = new JLabel(""+minas);
		numeroM.setBorder(BorderFactory.createLineBorder(Color.black,2)); 
		numeroM.setPreferredSize(new Dimension(185, 30));
		numeroM.setHorizontalAlignment(SwingConstants.CENTER);
		p1.add(numeroM);	

		tiempo1=new JLabel();
		tiempo1.setIcon(tiempo);
		p1.add(tiempo1);

		numeroT=new JLabel();
		numeroT.setBorder(BorderFactory.createLineBorder(Color.black,2));
		numeroT.setPreferredSize(new Dimension(185, 30));
		numeroT.setHorizontalAlignment(SwingConstants.CENTER);
		p1.add(numeroT);

		add(p1,BorderLayout.SOUTH);
	}

	public void nuevoSegundo(){
		if(jugando == true)
		{
			contadorTiempo++;
			numeroT.setText(""+contadorTiempo);
		}
	}
	
	public void nuevoJuego(int f, int c, int m){
		jugando=true;
		contadorTiempo=0;
		minas=m;
		numeroM.setText(""+m);
		t=new Tablero(f,c,m,this);
		
		this.add(t);
		this.revalidate();
		this.repaint();
		this.setVisible(true);
	}

	public void nuevoFacil(){
		jugando=true;
		contadorTiempo=0;
		minas=10;
		numeroM.setText(""+10);
		t=new Tablero(10,10,10,this);
		t.setBorder(BorderFactory.createLineBorder(Color.black,3)); 
		this.add(t);
		this.revalidate();
		this.repaint();
		this.setVisible(true);
	}
	
	public void nuevoMedio(){
		jugando=true;
		contadorTiempo=0;
		minas=30;
		numeroM.setText(""+30);
		t=new Tablero(15,15,30,this);
		t.setBorder(BorderFactory.createLineBorder(Color.black,3)); 
		this.add(t);
		this.revalidate();
		this.repaint();
		this.setVisible(true);
	}
	
	public void nuevoDificil(){
		jugando=true;
		contadorTiempo=0;
		minas=40;
		numeroM.setText(""+40);
		t=new Tablero(20,20,40,this);
		t.setBorder(BorderFactory.createLineBorder(Color.black,3)); 
		this.add(t);
		this.revalidate();
		this.repaint();
		this.setVisible(true);
	}
	
	public void retrasar(){
		minas = minas - 1;
		numeroM.setText(""+minas);
	}
	
	public void adelantar() {
		minas = minas + 1;
		numeroM.setText(""+minas);
	}
	
	public JLabel getNumeroM(){
		return numeroM;
	}
}
