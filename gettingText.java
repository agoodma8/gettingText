package gettingText;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
//import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JLabel;
//import javax.swing.JPanel;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
//import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
//import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
//import javax.swing.SwingConstants;

public class gettingText1 {

	private JFrame frame;
	final JFileChooser inputChooser = new JFileChooser();
	final JFileChooser outputChooser= new JFileChooser();
	BufferedWriter writer =null;
	File newFile = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gettingText1 window = new gettingText1();
					window.frame.setVisible(true);
		
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public gettingText1() {
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
	frame.setTitle("Text Modifier");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel label1=new JLabel();
		JLabel label2=new JLabel();
		//Input File Button
		Button input = new Button("Select Input File\r\n");
		input.setSize(20, 50 );
		input.setBackground(Color.WHITE);
		input.setForeground(Color.BLACK);
		input.setFont(UIManager.getFont("FileChooser.listFont"));
		input.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//final JFileChooser inputChooser = new JFileChooser();
				inputChooser.setDialogTitle("Select a Text File");
				inputChooser.setAcceptAllFileFilterUsed(false);
				FileNameExtensionFilter txtFilter = new FileNameExtensionFilter ("Text Files","txt");
				inputChooser.addChoosableFileFilter(txtFilter);
				int returnVal= inputChooser.showOpenDialog(null);
				if (returnVal==JFileChooser.APPROVE_OPTION) {
					File file=inputChooser.getSelectedFile();
					String name=file.getName();
					label1.setText("Selected File: "+name);
				}
			}
		});
		
		//Output File Button
		Button output = new Button("Select Output File\r\n");
		
		output.setBackground(Color.WHITE);
		output.setForeground(Color.BLACK);
		output.setFont(UIManager.getFont("FileChooser.listFont"));
		output.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			outputChooser.setDialogTitle("Select a Text File");
			outputChooser.setAcceptAllFileFilterUsed(false);
			FileNameExtensionFilter txtFilter = new FileNameExtensionFilter ("Text Files","txt");
			outputChooser.addChoosableFileFilter(txtFilter);
			int returnVal= outputChooser.showOpenDialog(null);
			if (returnVal==JFileChooser.APPROVE_OPTION) {
				File file=outputChooser.getSelectedFile();
				String name =file.getName();
				label2.setText("Selected File: "+name);
			
				
			}
		}
		});
		
		//Format Button
		JLabel textField = new JLabel();
		textField.setFont(new Font("Dialog", Font.PLAIN, 9));
		textField.setText("Words:            Lines:          Blank Lines Removed:       "+"\n" + "(Avg Words/Line):     (Avg Line length): ");
		Button format= new Button ("Format");
		format.setBackground(Color.white);
		format.setForeground(Color.black);
		format.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try (Scanner scanner = new Scanner(new File(inputChooser.getSelectedFile().getAbsolutePath()))) {
					newFile = new File(outputChooser.getSelectedFile().getAbsolutePath());
					writer = new BufferedWriter(new FileWriter(newFile));
					int max=80;
					StringBuffer buff= new StringBuffer(max);
					StringBuffer temp = null;
			        while (scanner.hasNextLine()) {	
			            Scanner sc= new Scanner(scanner.nextLine());
			            
			            while (sc.hasNext()) {
			            	
			            	String nextWord = sc.next();
			            	if(temp != null && (temp.length() + nextWord.length()+1 > max)){
			            	
			            		
			            		writer.write(buff.toString() + " ");
			            		writer.newLine();
			            		buff=new StringBuffer(nextWord);
			            		temp = buff;
			            	}
			            	else {
			            		
			            		buff.append((buff.length()==0 ? "": "") + nextWord + " ");
			            		temp = buff;
			            
			            		
			            	} 
			            }
			            
			            if (buff.length() > 0) {
			            	
			            	writer.write(buff.toString() + "");
			            	buff = new StringBuffer(max-temp.length());
			            }
			            
			           sc.close();
			            
			        }
			        
			        
			        
			       
			        
			        
			        
//This Part is for counting Words + Lines . 
String line = "", empty = "";
int words = 0;
int lines = 0;
FileReader FReader= new FileReader (inputChooser.getSelectedFile().getAbsolutePath());
BufferedReader BReader = new BufferedReader (FReader);
while((line = BReader.readLine())!=null) {
	empty += line + " ";
	lines ++ ;
}

//System.out.println ("Num of lines"+ lines);
StringTokenizer STokenizer = new StringTokenizer (empty);
words = STokenizer.countTokens();
BReader.close();
double chars = inputChooser.getSelectedFile().length();
double Avg_L_L = chars / lines ;
//System.out.println("Average Length is" + Avg_L_L );
//System.out.println("Word Counter:" + words); 
double Avg_W_L = (double) words/lines ; 
//System.out.println("Average Words/Line =" + Avg_W_L);
LineNumberReader LReader = new LineNumberReader(new FileReader(new File (inputChooser.getSelectedFile().getAbsolutePath())));
int counter = 0 ; 
String EmptyLine = null;
while ((EmptyLine= LReader.readLine())!= null) {
if (EmptyLine.length()==0) {
	counter++;
}
		//Done here.
}
textField.setText("Words:   " + words + "        Lines:   "
		+ lines + "       Blank Lines Removed:   " + counter 
		+ "    "+"\n" + "(Avg Words/Line):   " + Avg_L_L
				+ "  (Avg Line length): " + Avg_W_L);
		LReader.close();
		//System.out.println("Blank Lines Removed : " + counter );
			    } catch (FileNotFoundException evt) {
			        evt.printStackTrace();
			    } catch (IOException e1) {
					e1.printStackTrace();
				}
				finally {
					try {
						writer.close();
					} catch (FileNotFoundException ev) {
				        ev.printStackTrace();
				    } catch (IOException e2) {
						e2.printStackTrace();
					}
					
				}
			 }	
		});
		
		JButton btnNewButton = new JButton("Left Justification");
		btnNewButton.setSize(50,50);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		
		JButton btnNewButton_1 = new JButton("Right Justification");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		
		frame.getContentPane().setLayout(new GridLayout(9,1));
		frame.getContentPane().add(input);
		frame.getContentPane().add(label1);
		frame.getContentPane().add(output);
		frame.getContentPane().add(label2);
		frame.getContentPane().add(btnNewButton_1);
		frame.getContentPane().add(btnNewButton);
		frame.getContentPane().add(format);
		frame.getContentPane().add(textField);
	}
}

		
