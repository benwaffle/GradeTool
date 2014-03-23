package gradetool;

import gradetool.gui.*;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

import com.benwaffle.pslib.PSlib;

public class GradeTool {
	public static void main(String[] args) {
		System.setProperty("socksProxyHost", "127.0.0.1");
		System.setProperty("socksProxyPort", "8888");
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}

		// init thread
		Thread psInit = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					PSlib.init();
				} catch (Exception e){
					JOptionPane.showMessageDialog(null, 
							"I can't connect to PowerSchool. Bye for now...", 
							"Error", JOptionPane.ERROR_MESSAGE);
					System.exit(1);
				}
			}
		});
		psInit.start();
		
		LoginWindow lw = new LoginWindow(psInit);
	}
}
