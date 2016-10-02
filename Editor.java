
import java.awt.event.ActionEvent;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Editor extends JFrame {
	
	private JTextArea textarea = new JTextArea(20, 60); //20 row, 60 columns
	JFileChooser fc = new JFileChooser();
	
	//Constructor for window 
	public Editor(){
		JScrollPane scrollpane = new JScrollPane(textarea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		//filter for text files
		FileFilter txtFilter = new FileNameExtensionFilter("Pain text", "txt");
		fc.setFileFilter(txtFilter);
		
		//menu and menu items
		add(scrollpane);
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu file = new JMenu("file");
		menuBar.add(file);
		
		//adding actions to file menu items 
		file.add(Open);
		file.add(Save);
		file.addSeparator();
		file.add(Exit);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
	
	Action Open = new AbstractAction("Open File") {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
				openFile(fc.getSelectedFile().getAbsolutePath());
			}	
		}
	};
	
	Action Save = new AbstractAction("Save File") {
		

		@Override
		public void actionPerformed(ActionEvent e) {
			saveFile(); //add later
			
			
		}
	};
	
	Action Exit = new AbstractAction("Exit") {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	};
	
	public void openFile(String fileName){
		FileReader fr = null;
		try{
			fr = new FileReader(fileName);
			textarea.read(fr, null);
			fr.close();
			setTitle(fileName);
			
		} catch (IOException e){
			e.printStackTrace();
		}
	}
	
	public void saveFile(){
		if(fc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
			FileWriter fw = null;
			try{
				fw = new FileWriter(fc.getSelectedFile().getAbsolutePath() + ".txt");
				textarea.write(fw);
				fw.close();
			} catch (IOException e){
				e.printStackTrace();	
			}
		}
	}
	
	public static void main(String [] args){
		new Editor();
	}

}
