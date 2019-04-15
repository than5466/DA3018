package calculator;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class GraphicRealMain extends JFrame{

	JTextField text1, text2;
	JLabel label1, label2;
	newButton b1, b2, b3, b4, b5;
	numButton b10, b11, b12, b13, b14, b15, b16, b17, b18, b19, b20;
	JButton b6;

	Calc calc = new Calc();

	public class newButton {
		JButton button;

		newButton(int x, int y, int xSize, int ySize, String name) {
			button = new JButton(new AbstractAction(name) {
				@Override
				public void actionPerformed(ActionEvent e) {
					text1.setText(text1.getText() + " " + name + " ");
				}
			});
			button.setBounds(x, y, xSize, ySize);

		}
	}

	public class numButton {
		JButton button;

		numButton(int x, int y, int xSize, int ySize, String name) {
			button = new JButton(new AbstractAction(name) {
				@Override
				public void actionPerformed(ActionEvent e) {
					text1.setText(text1.getText() + name);
				}
			});
			button.setBounds(x, y, xSize, ySize);

		}
	}

	public void run() {
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
			} else if (var.equals("")) {
				
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
	}

	GraphicRealMain() {
		label1 = new JLabel("RPN input:");
		label1.setBounds(90, 30, 150, 20);
		label2 = new JLabel("Answer:");
		label2.setBounds(290, 30, 150, 20);

		text1 = new JTextField();
		text1.setBounds(50, 50, 150, 20);
		text2 = new JTextField();
		text2.setBounds(250, 50, 150, 20);
		text2.setEditable(false);

		b1 = new newButton(200, 100, 50, 30, "+");
		b2 = new newButton(200, 130, 50, 30, "-");
		b3 = new newButton(150, 190, 50, 30, "=");
		b4 = new newButton(200, 160, 50, 30, "*");
		b5 = new newButton(200, 190, 50, 30, "/");
		b10 = new numButton(100, 190, 50, 30, "0");
		b11 = new numButton(50, 100, 50, 30, "1");
		b12 = new numButton(100, 100, 50, 30, "2");
		b13 = new numButton(150, 100, 50, 30, "3");
		b14 = new numButton(50, 130, 50, 30, "4");
		b15 = new numButton(100, 130, 50, 30, "5");
		b16 = new numButton(150, 130, 50, 30, "6");
		b17 = new numButton(50, 160, 50, 30, "7");
		b18 = new numButton(100, 160, 50, 30, "8");
		b19 = new numButton(150, 160, 50, 30, "9");
		b20 = new numButton(50, 190, 50, 30, ".");

		b6 = new JButton("Push");
		
		AbstractAction buttonPressed = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                run();
            }
        };
        
		b6.addActionListener(buttonPressed);
		
		b6.getInputMap(javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW).
        put(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_ENTER,0), "ENTER_pressed");
		
		b6.getActionMap().put("ENTER_pressed", buttonPressed);

		add(b1.button);
		add(b2.button);
		add(b3.button);
		add(b4.button);
		add(b5.button);
		add(b6);
		add(b10.button);
		add(b11.button);
		add(b12.button);
		add(b13.button);
		add(b14.button);
		add(b15.button);
		add(b16.button);
		add(b17.button);
		add(b18.button);
		add(b19.button);
		add(b20.button);
		add(label1);
		add(label2);
		add(text1);
		add(text2);
		setSize(500, 300);
		setLayout(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	public static void main(String[] args) {
		new GraphicRealMain();
	}

}
