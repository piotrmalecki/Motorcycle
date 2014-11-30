package pl.wbd.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import pl.wbd.database.connection.DBConnection;
import pl.wbd.ui.frames.MainFrame;
import pl.wbd.ui.frames.MotorcycleMain;


@SuppressWarnings("serial")
public class MenuBar extends JMenuBar {

	protected JMenuItem reconnect;
	protected JMenuItem disconnect;
	protected JMenuItem exit;

	public MenuBar(final MainFrame mainFrame, final DBConnection con) {
		super();
		JMenu menu = new JMenu("Plik");
		this.add(menu);

		disconnect = new JMenuItem("Rozłącz");
		disconnect.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainFrame.setVisible(false);
				MotorcycleMain.getMainFrame().setVisible(true);
				try { 
					if (con.getConnection() != null)
						con.getConnection().close();
						System.out.println("Disconnected");
				}
				catch (SQLException er) {
					System.err.println("Error disconnecting user from database");
					er.printStackTrace();
				}
			}
		});
		menu.add(disconnect);

		exit = new JMenuItem("Zamknij");
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					try {
						if (con.getConnection() != null) {
							con.getConnection().close();
							System.out.println("Disconnected");
						}
					} catch (SQLException e1) {
						System.err.println("Error disconnecting user from database");
						e1.printStackTrace();
					}
				System.exit(0);
			}
		});
		menu.add(exit);

		JMenu help = new JMenu("Pomoc");
		JMenuItem about = new JMenuItem("O Programie");
		about.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new aboutDialog();
			}
		});
		this.add(help);
		help.add(about);

	}

	public void disableItem(JMenuItem menuItem) {
		menuItem.setEnabled(false);
		revalidate();
	}

	public void enableItem(JMenuItem menuItem) {
		menuItem.setEnabled(true);
		revalidate();
	}

}
