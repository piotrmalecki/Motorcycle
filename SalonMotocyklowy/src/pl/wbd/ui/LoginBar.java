package pl.wbd.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;




@SuppressWarnings("serial")
public class LoginBar extends JMenuBar {

	public LoginBar() {
		super();
		
		JMenu help = new JMenu("Pomoc");
		this.add(help);
		
		JMenuItem htc = new JMenuItem("Jak się połączyć");
		help.add(htc);
		htc.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JMenuItem about = new JMenuItem("O programie");
		help.add(about);
		about.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new aboutDialog();		
			}
		});
	}

}
