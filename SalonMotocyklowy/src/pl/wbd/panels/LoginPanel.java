package pl.wbd.panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;

import pl.wbd.database.connection.DBConnection;
import pl.wbd.ui.frames.MainFrame;
import pl.wbd.ui.frames.MotorcycleMain;
import pl.wbd.util.JTextFieldLimit;



@SuppressWarnings("serial")
public class LoginPanel extends JPanel {

	private static final Dimension buttonSize = new Dimension(90, 20);
	private JTextField setLogin;
	private JPasswordField setPass;
	private static JLabel errorText;
	private String login;
	private DBConnection con;
	/**
	 * Create the panel.
	 */
	public LoginPanel(final MotorcycleMain loginFrame) {
		setLayout(new BorderLayout());
		JPanel northPanel = new JPanel();
		JPanel southPanel = new JPanel();
		errorText = new JLabel(" ");
		errorText.setForeground(Color.RED);
		add(northPanel, BorderLayout.NORTH);
		northPanel.add(new JLabel("Salon motocyklowy ")).setFont(
				new Font("Monotype Corsiva", Font.BOLD, 30));

		JPanel centerPanel = new JPanel();
		add(centerPanel, BorderLayout.CENTER);
		short rows = 5;
		short cols = 2;
		centerPanel.setLayout(new GridLayout(rows, cols, 2,0));

		JPanel[][] panelHolder = new JPanel[rows][cols];

		for (int m = 0; m < rows; m++) {
			for (int n = 0; n < cols; n++) {
				panelHolder[m][n] = new JPanel();
				panelHolder[m][n].setOpaque(true);
				centerPanel.add(panelHolder[m][n]);
			}
		}

		Action connectedToDB = new AbstractAction() {		
			@Override
			public void actionPerformed(ActionEvent arg0) {
				login = setLogin.getText();
				con = new DBConnection(login, setPass
						.getPassword());
				if (con.getConnectionStatus() == true) {
					loginFrame.setVisible(false);
					new MainFrame(con);
				}

				setPass.setText(null);
				setLogin.setText(null);
			}				
		};
		
		panelHolder[0][0].add(errorText);
		panelHolder[1][0].add(new JLabel("Użytkownik:"));
		setLogin = new JTextField(10);
		setLogin.setHorizontalAlignment(SwingConstants.CENTER);
		setLogin.setDocument(new JTextFieldLimit(12));
		panelHolder[2][0].add(setLogin);

		panelHolder[1][1].add(new JLabel("Hasło:"));
		setPass = new JPasswordField(10);
		setPass.setHorizontalAlignment(SwingConstants.CENTER);
		setPass.setDocument(new JTextFieldLimit(12));
		panelHolder[2][1].add(setPass);

		JButton connect = new JButton("Zaloguj");

		panelHolder[3][0].add(connect);
		connect.setPreferredSize(buttonSize);
		connect.addActionListener(connectedToDB);

		setPass.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "connectedToDB");
		setPass.getActionMap().put("connectedToDB", connectedToDB);
		setLogin.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "connectedToDB");
		setLogin.getActionMap().put("connectedToDB", connectedToDB);
		JButton exit = new JButton("Wyjdź");
		exit.setPreferredSize(buttonSize);
		panelHolder[3][1].add(exit);
		exit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				setPass.setText(null);
				setLogin.setText(null);
				System.exit(0);
			}
		});
		
		southPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		add(southPanel, BorderLayout.SOUTH);
		southPanel.add(errorText);
	}
	
	public static JLabel getErrorText() {
		return errorText;
	}
	/*protected DBConnection getDBConnection() {
		return con;
	}*/
	
}
