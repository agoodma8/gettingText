package textModifier;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import javax.swing.SwingConstants;

public class gettingText {

	private JFrame frame;
	final JFileChooser inputChooser = new JFileChooser();
	final JFileChooser outputChooser= new JFileChooser();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					gettingText window = new gettingText();
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
	public gettingText() {
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
  
		//Selected File Labels
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
				String name=file.getName();
				label2.setText("Selected File: "+name);
			}
		}
		});
		
		//Format Button
		Button format= new Button ("Format");
		format.setBackground(Color.white);
		format.setForeground(Color.black);
		format.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try (Scanner scanner = new Scanner(new File(inputChooser.getSelectedFile().getAbsolutePath()))) {
					int max=80;
					StringBuffer buff= new StringBuffer(max);
			        while (scanner.hasNextLine()) {
			            Scanner sc= new Scanner(scanner.nextLine());
			            while (sc.hasNext()) {
			            	String nextWord=sc.next();
			            	if((buff.length()+ nextWord.length()+1 >max)){
			            		buff.append('\n');
			            		System.out.print(buff.toString()+ " ");
			            		buff=new StringBuffer(nextWord);
			            	}
			            	else {
			            		buff.append((buff.length()==0?"":"")+ nextWord);
			            	}
			            }
			            if (buff.length()>0) {
			            	System.out.print(buff.toString() + "\n");
			            }
			        }
			        //Here Should be the saving part so the bottom calculation will be done to the output and we get the final result
			        
			        
			        
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
System.out.println ("Num of lines"+ lines);
StringTokenizer STokenizer = new StringTokenizer (empty);
while (STokenizer.hasMoreTokens()) { //Count words
	String something = STokenizer.nextToken();
	words++;
}
double chars = inputChooser.getSelectedFile().length();
double Avg_L_L = chars / lines ;
System.out.println("Average Length is" + Avg_L_L );
System.out.println("Word Counter:" + words); 
double Avg_W_L = (double) words/lines ; 
System.out.println("Average Words/Line =" + Avg_W_L);
LineNumberReader LReader = new LineNumberReader(new FileReader(new File ( inputChooser.getSelectedFile().getAbsolutePath())));
int counter = 0 ; 
String EmptyLine = null;
while ((EmptyLine= LReader.readLine())!= null) {
if (EmptyLine.length()==0) {
	counter++;
}
		//Done here.
}
		LReader.close();
		System.out.println("Blank Lines Removed : " + counter );
			    } catch (FileNotFoundException evt) {
			        evt.printStackTrace();
			    } catch (IOException e1) {
					e1.printStackTrace();
				}
			 }	
		});
		
		//Statistics
		JLabel textField = new JLabel();
		textField.setFont(new Font("Dialog", Font.PLAIN, 9));
		textField.setText("Words:            Lines:          Blank Lines Removed:       "+"\n" + "(Avg Words/Line):     (Avg Line length): ");
		
		frame.setLayout(new GridLayout(7,1));
		frame.add(input);
		frame.add(label1);
		frame.add(output);
		frame.add(label2);
		frame.add(format);
		frame.add(textField);

		
	}
	
}















