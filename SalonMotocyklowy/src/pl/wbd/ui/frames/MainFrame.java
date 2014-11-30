package pl.wbd.ui.frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import pl.wbd.database.connection.DBConnection;
import pl.wbd.panels.MainPanel;
import pl.wbd.ui.MenuBar;

@SuppressWarnings("serial")
public class MainFrame extends JFrame implements WindowListener{

	public static final Dimension frameSize = new Dimension (1100,720);
	private JPanel contentPane;
	protected MainPanel mainPanel;
	private DBConnection con;
	/**
	 * Create the frame.
	 */
	public MainFrame(DBConnection con) {
		this.con = con;
		setTitle("Salon motocyklowy - Zalogowano jako "+con.getCurrentUser());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 610);
		setMinimumSize(frameSize);
		setPreferredSize(frameSize);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setVisible(true);
		setJMenuBar(new MenuBar(this,con));
		mainPanel = new MainPanel(con);
		getContentPane().add(mainPanel);
		mainPanel.setVisible(true);
		setLocationRelativeTo(null);
		pack();
	}

	public MainFrame getMainFrame() {
		return this;
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {

		System.out.println("Closed");
		if (con.getConnection() != null)
			try {
				con.getConnection().close();
				System.out.println("Disconnected");
			}  catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
	}

	@Override
	public void windowClosing(WindowEvent e) {

		System.out.println("Closing");
		if (con.getConnection() != null)
			try {
				con.getConnection().close();
				System.out.println("Disconnected");
			}  catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
