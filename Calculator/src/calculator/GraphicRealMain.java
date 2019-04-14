package calculator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class GraphicRealMain extends JFrame{
	
	JTextField text1, text2;
	JLabel label1, label2;
	newButton b1,b2,b3,b4,b5;
	JButton b6;
	
	Calc calc = new Calc();
	
	public class newButton {
		JButton button;

		newButton(int x, int y, int xSize, int ySize, String name) {
			button = new JButton(new AbstractAction(name) {
				@Override
				public void actionPerformed(ActionEvent e) {
					text1.setText(text1.getText()+ " " + name + " ");
				}
			});
			button.setBounds(x,y,xSize,ySize);

		}
	}
	GraphicRealMain(){
		label1=new JLabel("RPN input:");
		label1.setBounds(90,30,150,20);
		label2=new JLabel("Answer:");
		label2.setBounds(90,80,150,20);

		text1=new JTextField();
		text1.setBounds(50,50,150,20);
		text2=new JTextField();
		text2.setBounds(50,100,150,20);
		text2.setEditable(false);
		
		b1 = new newButton(50,150,50,50,"+");
		b2 = new newButton(100,150,50,50,"-");
		b3 = new newButton(150,150,100,50,"=");
		b4 = new newButton(50,200,50,50,"*");
		b5 = new newButton(100,200,50,50,"/");
		
		b6 = new JButton(new AbstractAction("Push") {
	        @Override
	        public void actionPerformed( ActionEvent e ) {
	        	text2.setText("");
	        	String[] split_input = text1.getText().split(" ");
	        	text1.setText("");
	        	for (String var : split_input) {

					if (var.equals("+") || var.equals("-") || var.equals("*") || var.equals("/")) {
						calc.performCalc(var);
						if (calc.error_flag) {
							text2.setText("Calculation cancelled.");
						}

					} else if (var.equals("=")) {
						if (calc.stack.is_empty()) {
							text2.setText("Stack is empty.");
						} else {
							calc.stack.pop();
							String output = String.valueOf(calc.stack.value);
							text2.setText(output);
							calc.stack.push(calc.stack.value);
						}
					}
					else if (var.equals("")){
						
					}

					else {
						try {
							double num = Double.parseDouble(var);
							if (calc.stack.pointer < calc.stack.length) {
								calc.stack.push(num);
							} else {
								text2.setText("Stack is full.");
							}
						} catch (Exception f) {
							text2.setText("Symbol not allowed.");
							break;
						}
					}

	        }
	    }});
		b6.setBounds(150,200,100,50);
		
		
		add(b1.button);
		add(b2.button);
		add(b3.button);
		add(b4.button);
		add(b5.button);
		add(b6);
		add(label1);
		add(label2);
		add(text1);
		add(text2);
		setSize(300, 300);
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public static void main(String[]args) {
		new GraphicRealMain();
	}
	
}
