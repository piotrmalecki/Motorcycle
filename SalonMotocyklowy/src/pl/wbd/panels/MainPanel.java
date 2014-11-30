package pl.wbd.panels;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import pl.wbd.database.connection.DBConnection;
import pl.wbd.user.UserLevel;

@SuppressWarnings("serial")
public class MainPanel extends JPanel {
	
	public static final Dimension panelSize = new Dimension (1100,700);
	public JTabbedPane tabbedPane;
	private DBConnection con;
	/**
	 * Create the panel.
	 */
	public MainPanel(DBConnection con) {
		setSize(panelSize);
		setPreferredSize(panelSize);
		JPanel topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		this.add( topPanel );
		this.con = con;
		this.setLayout(new BorderLayout());
		tabbedPane = new JTabbedPane();
		
		initialize();
	}

	private void initialize()
	{			
		if (con.getUserGrants().equals(UserLevel.NULL)) {
			tabbedPane.addTab("Motocykle", null, new JPanel(), "Obejrzyj oferowane motocykle");
		} else if (con.getUserGrants().equals(UserLevel.SPRZEDAWCA)) {
			tabbedPane.addTab("Motocykle", null, new JPanel(), "Oferta motocykli");
			tabbedPane.addTab("Transakcje", null, null, "Przeprowadzone transakcje");
			tabbedPane.addTab("Jazdy próbne", null, null, "Przeprowadzone jazdy próbne");
			tabbedPane.addTab("Klienci", null, null, "Zarejestrowani klienci");
			tabbedPane.addTab("Oddziały",null,null,"Oddzialy firmy");
			tabbedPane.addTab("Moje Konto", null, null, "Ustawienia i statystyki użytkownika");
		} else {
			tabbedPane.addTab("Motocykle", null, new JPanel(), "Oferta motocykli");
			tabbedPane.addTab("Transakcje", null, null, "Przeprowadzone transakcje");
			tabbedPane.addTab("Jazdy próbne", null, null, "Przeprowadzone jazdy próbne");
			tabbedPane.addTab("Klienci", null, null, "Zarejestrowani klienci");
			tabbedPane.addTab("Oddziały",null,null,"Oddziały firmy");
			tabbedPane.addTab("Personel", null, null, "Dane zatrudnionych pracowników");
			tabbedPane.addTab("Moje Konto", null, null, "Ustawienia i statystyki użytkownika");
		}
		
		tabbedPane.setSize(panelSize);
		tabbedPane.setPreferredSize(panelSize);
		tabbedPane.setMinimumSize(panelSize);
		tabbedPane.addChangeListener(new ChangeListener() {

	            @Override
	            public void stateChanged(ChangeEvent e) {

	                Component p =   ((JTabbedPane) e.getSource()).getSelectedComponent();
	                Dimension panelDim = p.getPreferredSize();
	                Dimension nd = new Dimension(panelDim);
	                tabbedPane.setPreferredSize(nd);
	            }
	        });
		
		this.add(tabbedPane, BorderLayout.CENTER);
		
		tabbedPane.addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent e) {
	            if (con.getUserGrants().equals(UserLevel.NULL)) {
	    			tabbedPane.setComponentAt(0, new MotorcyclePanel());
	    		} else if (con.getUserGrants().equals(UserLevel.SPRZEDAWCA)) {
	    			
	    			if (tabbedPane.getSelectedIndex() == 0)
	    				tabbedPane.setComponentAt(0, new MotorcyclePanel());
	    			else if (tabbedPane.getSelectedIndex() == 1)
	    				tabbedPane.setComponentAt(1, new TransactionPanel());
	    			else if (tabbedPane.getSelectedIndex() == 2)
	    				tabbedPane.setComponentAt(2, new TryoutPanel());
	    			else if (tabbedPane.getSelectedIndex() == 3)
	    				tabbedPane.setComponentAt(3, new ClientsPanel(con));
	    			else if (tabbedPane.getSelectedIndex() == 4)
	    				tabbedPane.setComponentAt(4, new DepartPanel(con));
	    			else if (tabbedPane.getSelectedIndex() == 5)
	    				tabbedPane.setComponentAt(5, new MyAccount(con));
	    		} else {
	    			if (tabbedPane.getSelectedIndex() == 0)
	    				tabbedPane.setComponentAt(0, new MotorcyclePanel());
	    			else if (tabbedPane.getSelectedIndex() == 1)
	    				tabbedPane.setComponentAt(1, new TransactionPanel());
	    			else if (tabbedPane.getSelectedIndex() == 2)
	    				tabbedPane.setComponentAt(2, new TryoutPanel());
	    			else if (tabbedPane.getSelectedIndex() == 3)
	    				tabbedPane.setComponentAt(3, new ClientsPanel(con));
	    			else if (tabbedPane.getSelectedIndex() == 4)
	    				tabbedPane.setComponentAt(4, new DepartPanel(con));
	    			else if (tabbedPane.getSelectedIndex() == 5) 
		    			tabbedPane.setComponentAt(5, new StaffPanel(con));
	    			else if (tabbedPane.getSelectedIndex() == 6)
	    				tabbedPane.setComponentAt(6, new MyAccount(con));
	    		}
	        }
	    });
	}
	
	public void disableTab (int tab) {
		tabbedPane.setEnabledAt(tab, false);
	}
	
	
}
