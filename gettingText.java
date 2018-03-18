
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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
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
BufferedWriter BWriter = null;
FileWriter Fwriter= null;
Fwriter= new FileWriter (inputChooser.getSelectedFile().getAbsolutePath());
BWriter= new BufferedWriter (Fwriter);
BWriter.write(buff.toString());
if (BWriter!=null)
	BWriter.close();
if(Fwriter!=null)
	Fwriter.close();
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

