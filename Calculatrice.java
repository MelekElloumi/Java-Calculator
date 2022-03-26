/*

Travail fait par Melek Elloumi Gl2 G1
Je me suis basé sur le cours de openclassroom:
https://openclassrooms.com/fr/courses/26832-apprenez-a-programmer-en-java/23832-tp-creez-une-calculatrice

J'ai ajouté: 
-un fix au problème de plusieurs virgules par nombre
-une solution lorsqu'on appuie sur égale plus qu'une fois, 
 la calculatrice refait la même opération avec le même 2ème opérande
 (et non pas avec le résultat)
 
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Fenetre extends JFrame{
	
	private JPanel panNb,panOp,panAff,pan;
	private String[] bCh = {"0","1", "2", "3", "4", "5", "6", "7", "8", "9", ".", "=", "C", "+", "-", "*", "/"};
	private JButton[] bNb = new JButton[bCh.length];
	private JLabel lAff;
	
	private double ch1,ch2;
	private boolean clicOper = false, update = false, virg = false;
	private String oper = "";
	
	public Fenetre(){
		this.setTitle("Calculatrice (par Melek_Elloumi_GL2)");
		this.setSize(370,410);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		
		panNb = new JPanel();
		panNb.setBackground(Color.LIGHT_GRAY);
		panNb.setPreferredSize(new Dimension(270, 350));
		for (int i=1;i<10;i++){
			bNb[i]=new JButton(bCh[i]);
			bNb[i].setPreferredSize(new Dimension(70, 70));
			bNb[i].addActionListener(new chifListener());
			panNb.add(bNb[i]);
		}
		bNb[0] = new JButton(bCh[0]);
		bNb[0].setPreferredSize(new Dimension(70, 70));
		bNb[0].addActionListener(new chifListener());	
		panNb.add(bNb[0]);
		bNb[10] = new JButton(bCh[10]);
		bNb[10].setPreferredSize(new Dimension(70, 70));
		bNb[10].addActionListener(new chifListener());
		panNb.add(bNb[10]);
		bNb[11] = new JButton(bCh[11]);
		bNb[11].setPreferredSize(new Dimension(70, 70));
		bNb[11].addActionListener(new egalListener());
		panNb.add(bNb[11]);
	    
		panOp = new JPanel();
		panOp.setBackground(Color.GRAY);
		panNb.setPreferredSize(new Dimension(90, 350));
		for (int i=12;i<bNb.length;i++){
			bNb[i]=new JButton(bCh[i]);
			bNb[i].setPreferredSize(new Dimension(60, 60));
			panOp.add(bNb[i]);
		}
		bNb[12].addActionListener(new CListener());
		bNb[13].addActionListener(new plusListener());
		bNb[14].addActionListener(new moinsListener());
		bNb[15].addActionListener(new foisListener());
		bNb[16].addActionListener(new divListener());
		
		
		panAff = new JPanel();
		panAff.setPreferredSize(new Dimension(360, 50));
		Font police = new Font("Arial", Font.BOLD, 20);		
		lAff=new JLabel("0");
		lAff.setFont(police);
		lAff.setHorizontalAlignment(JLabel.CENTER);
        lAff.setPreferredSize(new Dimension(360, 45));
		panAff.add(lAff);
		
		pan = new JPanel();
		pan.setLayout(new BorderLayout());
		pan.add(panAff,BorderLayout.NORTH);
		pan.add(panNb,BorderLayout.CENTER); 
		pan.add(panOp,BorderLayout.SOUTH);
		this.setContentPane(pan);		
		this.setVisible(true);
		System.out.println("Calculatrice est en marche");
		
	}
	
	private void calcul(){
		if(oper.equals("+")){
			ch1 = ch1 + ch2;
			lAff.setText(String.valueOf(ch1));
		}
		if(oper.equals("-")){
			ch1 = ch1 - ch2;
			lAff.setText(String.valueOf(ch1));
		}          
		if(oper.equals("*")){
			ch1 = ch1 * ch2;
			lAff.setText(String.valueOf(ch1));
		}     
		if(oper.equals("/")){
			try{
				ch1 = ch1 / ch2;
				lAff.setText(String.valueOf(ch1));
			} catch(ArithmeticException e) {
				lAff.setText("0");
			}
		}
	}

	
	class CListener implements ActionListener{
    
		public void actionPerformed(ActionEvent e) {
			ch1=0;
			lAff.setText("0");
			update=true;
			virg=false;
			clicOper=false;
			oper="";
		}
	}
	
	class plusListener implements ActionListener{
    
		public void actionPerformed(ActionEvent e) {
			if(clicOper){
				calcul();
				lAff.setText(String.valueOf(ch1));
			}else{
				ch1 = Double.valueOf(lAff.getText()).doubleValue();
				clicOper = true;
			}
			oper = "+";
			update = true;
			virg=false;
		}
	}
	
	class moinsListener implements ActionListener{
    
		public void actionPerformed(ActionEvent e) {
			if(clicOper){
				calcul();
				lAff.setText(String.valueOf(ch1));
			}else{
				ch1 = Double.valueOf(lAff.getText()).doubleValue();
				clicOper = true;
			}
			oper = "-";
			update = true;
			virg=false;
		}
	}
	
	class foisListener implements ActionListener{
    
		public void actionPerformed(ActionEvent e) {
			if(clicOper){
				calcul();
				lAff.setText(String.valueOf(ch1));
			}else{
				ch1 = Double.valueOf(lAff.getText()).doubleValue();
				clicOper = true;
			}
			oper = "*";
			update = true;
			virg=false;
		}
	}
	
	class divListener implements ActionListener{
    
		public void actionPerformed(ActionEvent e) {
			if(clicOper){
				calcul();
				lAff.setText(String.valueOf(ch1));
			}else{
				ch1 = Double.valueOf(lAff.getText()).doubleValue();
				clicOper = true;
			}
			oper = "/";
			update = true;
			virg=false;			
		}
	}
	
	class egalListener implements ActionListener{
    
		public void actionPerformed(ActionEvent e) {
			if(clicOper){
				ch2=Double.valueOf(lAff.getText()).doubleValue();
			}
			calcul();
			virg=false;
			update = true;
			clicOper = false;       
		}
	}
	
	class chifListener implements ActionListener{
    
		public void actionPerformed(ActionEvent e) {
			String str = ((JButton)e.getSource()).getText();
			if (str.equals(".")){
				if (!virg){
					virg=true;
				}else{
					return;
				}
			}
			
			if(update){
				update = false;
			}else{
				if(!lAff.getText().equals("0"))
					str = lAff.getText() + str;
			}
			lAff.setText(str);
		}        
	}
}	
	

public class Calculatrice{

	public static void main (String args []){
		Fenetre f = new Fenetre();
	}
	
}