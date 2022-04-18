package org.example.View;

import org.example.Controller.MonoUtils;
import org.example.Controller.Operations;
import org.example.Model.Polynomial;

import javax.swing.*;
import java.awt.*;

public class GUI extends JPanel {

    public Polynomial polyOne;
    public Polynomial polyTwo;
    public Polynomial polyThree;

    static JTextField polyFieldOne;
    static JTextField polyFieldTwo;

    static JButton polyOneSubmit;
    static JButton polyTwoSubmit;
    static JButton addCalc;
    static JButton subCalc;
    static JButton multiplyCalc;
    static JButton derivateFirstCalc;
    static JButton derivateSecondCalc;

    static JTextArea addResult;
    static JTextArea subResult;
    static JTextArea multiplyResult;
    static JTextArea derivateFirstResult;
    static JTextArea derivateSecondResult;

    static JLabel addLabel;
    static JLabel subLabel;
    static JLabel multiplyLabel;
    static JLabel derivateFirstLabel;
    static JLabel derivateSecondLabel;
    static JLabel polyOneLabel;
    static JLabel polyTwoLabel;
    static JLabel polyTitleLabel;
    static JLabel compTitleLabel;
    static JLabel impLabel;


    public GUI() {
        MonoUtils utils=new MonoUtils();

        polyFieldOne=new JTextField(100);
        polyFieldTwo=new JTextField(100);

        polyOneSubmit=new JButton("Submit");
        polyOneSubmit.addActionListener((event)->{
            if(polyFieldOne.getText().equals(""))
            {// if the field corresponding to the first polynomial is left empty and the user attempts to submit it, the program will throw an error
                polyOne=null;
                final JPanel panel = new JPanel();

                JOptionPane.showMessageDialog(panel, "First polynomial can't be empty", "Error", JOptionPane.ERROR_MESSAGE);
            }else {//else it will continue with the parsing of the polynomial and will clear the operations fields that depend on this polynomial
                polyOne = utils.stringToPoly(polyFieldOne.getText());
                addResult.setText("");
                subResult.setText("");
                multiplyResult.setText("");
                derivateFirstResult.setText("");
            }
        });
        polyTwoSubmit=new JButton("Submit");
        polyTwoSubmit.addActionListener((event)->{
            if(polyFieldTwo.getText().equals(""))
            {// if the field corresponding to the first polynomial is left empty and the user attempts to submit it, the program will throw an error
                polyTwo=null;
                final JPanel panel = new JPanel();

                JOptionPane.showMessageDialog(panel, "Second polynomial can't be empty", "Error", JOptionPane.ERROR_MESSAGE);
            }else {//else it will continue with the parsing of the polynomial and will clear the operations fields that depend on this polynomial
                polyTwo=utils.stringToPoly(polyFieldTwo.getText());
                //System.out.println(polyTwo.toString());
                addResult.setText("");
                subResult.setText("");
                multiplyResult.setText("");
                derivateSecondResult.setText("");
            }

        });
        //in the case of the operations, if the program detects that one of the polynomials was left empty/was not submitted, it will throw an error to the user telling them
        //the operation could not be computed
        addCalc=new JButton("Calculate");
        addCalc.addActionListener((event)->{
            if(polyOne==null || polyTwo==null)
            {
                addResult.setText("");
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "One or both polynomials are empty, addition cannot be calculated", "Error", JOptionPane.ERROR_MESSAGE);
            }else
            {
                polyThree=Operations.addition(polyOne,polyTwo);
                addResult.setText(polyThree.toString());
            }
        });
        subCalc=new JButton("Calculate");
        subCalc.addActionListener((event)->{
            if(polyOne==null || polyTwo==null)
            {
                subResult.setText("");
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "One or both polynomials are empty, subtraction cannot be calculated", "Error", JOptionPane.ERROR_MESSAGE);
            }else
            {
                polyThree=Operations.subtraction(polyOne,polyTwo);
                subResult.setText(polyThree.toString());
            }
        });
        multiplyCalc=new JButton("Calculate");
        multiplyCalc.addActionListener((event)->{
            if(polyOne==null || polyTwo==null)
            {
                multiplyResult.setText("");
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "One or both polynomials are empty, multiplication cannot be calculated", "Error", JOptionPane.ERROR_MESSAGE);
            }else
            {
                polyThree=Operations.multiplication(polyOne,polyTwo);
                multiplyResult.setText(polyThree.toString());
            }
        });
        derivateFirstCalc=new JButton("Calculate");
        derivateFirstCalc.addActionListener((event)->{
            if(polyOne==null)
            {
                derivateFirstResult.setText("");
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "First polynomial is empty, derivation cannot be calculated", "Error", JOptionPane.ERROR_MESSAGE);
            }else
            {
                polyThree=Operations.derivation(polyOne);
                derivateFirstResult.setText(polyThree.toString());
            }
        });
        derivateSecondCalc=new JButton("Calculate");
        derivateSecondCalc.addActionListener((event)->{
            if(polyTwo==null)
            {
                derivateSecondResult.setText("");
                final JPanel panel = new JPanel();
                JOptionPane.showMessageDialog(panel, "Second polynomial is empty, derivation cannot be calculated", "Error", JOptionPane.ERROR_MESSAGE);
            }else
            {
                polyThree=Operations.derivation(polyTwo);
                derivateSecondResult.setText(polyThree.toString());
            }
        });

        addResult=new JTextArea(1,40);
        addResult.setEditable(false);
        subResult=new JTextArea(1,40);
        subResult.setEditable(false);
        multiplyResult=new JTextArea(1,40);
        multiplyResult.setEditable(false);
        derivateFirstResult=new JTextArea(1,40);
        derivateFirstResult.setEditable(false);
        derivateSecondResult=new JTextArea(1,40);
        derivateSecondResult.setEditable(false);

        addLabel=new JLabel("Addition");
        subLabel=new JLabel("Subtraction");
        multiplyLabel=new JLabel("Multiplication");
        derivateFirstLabel=new JLabel("Derivation 1st");
        derivateSecondLabel=new JLabel("Derivation 2nd");
        polyOneLabel=new JLabel("Polynomial One:");
        polyTwoLabel=new JLabel("Polynomial Two:");
        polyTitleLabel=new JLabel("Polynomials");
        compTitleLabel=new JLabel("Computations");
        impLabel=new JLabel("NOTE: Input the polynomials as follows: ±ax^b±cx^d.., where a,b,c etc. are non-zero integers");

    }
    private static void displayGUI()
    {
        JFrame frame = new JFrame("Polynomial Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GUI gui=new GUI();
        gui.setBackground(Color.lightGray);
        gui.setSize(600,600);

        frame.pack();
        frame.setLayout(null);
        gui.setLayout(null);

        frame.setSize(600,600);
        //poly title label
        polyTitleLabel.setBounds(0,0,640,40);
        polyTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        polyTitleLabel.setFont(polyTitleLabel.getFont().deriveFont(30.0f));
        gui.add(polyTitleLabel);
        //polyOne submit
        polyOneLabel.setBounds(10,60,300,40);
        polyOneLabel.setFont(polyOneLabel.getFont().deriveFont(15.0f));
        gui.add(polyOneLabel);
        polyFieldOne.setBounds(130,60,350,40);
        polyFieldOne.setFont(polyFieldOne.getFont().deriveFont(18f));
        gui.add(polyFieldOne);
        polyOneSubmit.setBounds(482,60,100,39);
        gui.add(polyOneSubmit);
        //polyTwo submit
        polyTwoLabel.setBounds(10,110,300,40);
        polyTwoLabel.setFont(polyTwoLabel.getFont().deriveFont(15.0f));
        gui.add(polyTwoLabel);
        polyFieldTwo.setBounds(130,110,350,40);
        polyFieldTwo.setFont(polyFieldTwo.getFont().deriveFont(18f));
        gui.add(polyFieldTwo);
        polyTwoSubmit.setBounds(482,110,100,39);
        gui.add(polyTwoSubmit);
        //Note label
        impLabel.setBounds(10,160,640,20);
        impLabel.setFont(impLabel.getFont().deriveFont(impLabel.getFont().getStyle() | Font.BOLD));
        impLabel.setForeground(Color.RED);
        gui.add(impLabel);
        //computation title label
        compTitleLabel.setBounds(0,200,640,40);
        compTitleLabel.setHorizontalAlignment(JLabel.CENTER);
        compTitleLabel.setFont(compTitleLabel.getFont().deriveFont(30.0f));
        gui.add(compTitleLabel);
        //Addition
        addLabel.setBounds(10,270,300,40);
        addLabel.setFont(addLabel.getFont().deriveFont(15.0f));
        gui.add(addLabel);
        addResult.setBounds(130,270,350,40);
        addResult.setFont(addResult.getFont().deriveFont(18f));
        gui.add(addResult);
        addCalc.setBounds(482,270,100,39);
        gui.add(addCalc);
        //Subtraction
        subLabel.setBounds(10,320,300,40);
        subLabel.setFont(subLabel.getFont().deriveFont(15.0f));
        gui.add(subLabel);
        subResult.setBounds(130,320,350,40);
        subResult.setFont(subResult.getFont().deriveFont(18f));
        gui.add(subResult);
        subCalc.setBounds(482,320,100,39);
        gui.add(subCalc);
        //Multiplication
        multiplyLabel.setBounds(10,370,300,40);
        multiplyLabel.setFont(multiplyLabel.getFont().deriveFont(15.0f));
        gui.add(multiplyLabel);
        multiplyResult.setBounds(130,370,350,40);
        multiplyResult.setFont(multiplyResult.getFont().deriveFont(18f));
        gui.add(multiplyResult);
        multiplyCalc.setBounds(482,370,100,39);
        gui.add(multiplyCalc);
        //Derivate first polynomial
        derivateFirstLabel.setBounds(10,420,300,40);
        derivateFirstLabel.setFont(derivateFirstLabel.getFont().deriveFont(15.0f));
        gui.add(derivateFirstLabel);
        derivateFirstResult.setBounds(130,420,350,40);
        derivateFirstResult.setFont(derivateFirstResult.getFont().deriveFont(18f));
        gui.add(derivateFirstResult);
        derivateFirstCalc.setBounds(482,420,100,39);
        gui.add(derivateFirstCalc);
        //Derivate second polynomial
        derivateSecondLabel.setBounds(10,470,300,40);
        derivateSecondLabel.setFont(derivateSecondLabel.getFont().deriveFont(15.0f));
        gui.add(derivateSecondLabel);
        derivateSecondResult.setBounds(130,470,350,40);
        derivateSecondResult.setFont(derivateSecondResult.getFont().deriveFont(18f));
        gui.add(derivateSecondResult);
        derivateSecondCalc.setBounds(482,470,100,39);
        gui.add(derivateSecondCalc);
        frame.add(gui);
        frame.setVisible(true);
    }

    public static void main(String[] args)
    {
        javax.swing.SwingUtilities.invokeLater(GUI::displayGUI);
    }
}
