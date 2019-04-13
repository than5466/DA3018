package calculator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

import javax.swing.JFrame;

public class GraphicMain extends JFrame {
	JTextField text1, text2;
	JLabel label1, label2;
	JButton button;
	
	Calc calc = new Calc();

	GraphicMain() {
		label1 = new JLabel("Input field");
		label1.setBounds(90, 30, 150, 20);
		label2 = new JLabel("Output field");
		label2.setBounds(90, 80, 150, 20);
		
		text1 = new JTextField();
		text1.setBounds(50, 50, 150, 20);
		text2 = new JTextField();
		text2.setBounds(50, 100, 150, 20);
		text2.setEditable(false);
		button = new JButton(new AbstractAction("Push") {
	        @Override
	        public void actionPerformed( ActionEvent e ) {
	        	String[] split_input = text1.getText().split(" ");
	        	text1.setText("");
	        	for (String var : split_input) {

					if (var.equals("+") || var.equals("-") || var.equals("*") || var.equals("/")) {
						calc.performCalc(var);

					} else if (var.equals("=")) {
						if (calc.stack.is_empty()) {
							System.out.println("Stack is empty, nothing to show.");
						} else {
							calc.stack.pop();
							String output = String.valueOf(calc.stack.value);
							text2.setText(output);
							calc.stack.push(calc.stack.value);
						}
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
						}
					}

	        }
	    }});
		
		button.setBounds(50,150,200,100);
		add(label1);
		add(label2);
		add(text1);
		add(text2);
		add(button);
		setSize(300, 300);
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[]args) {
		new GraphicMain();
	}
}