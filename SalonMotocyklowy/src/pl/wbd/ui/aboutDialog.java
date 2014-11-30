package pl.wbd.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class aboutDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Create the dialog.
	 */
	public aboutDialog() {
		setAlwaysOnTop(true);
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setTitle("Salon Motocyklowy - O programie");
		setResizable(false);
		setBounds(0, 0, 295, 317);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblDbconnector = new JLabel("Salon motocyklowy");
		lblDbconnector.setFont(new Font("Monotype Corsiva", Font.BOLD, 31));
		
		JLabel lblAutorProgramu = new JLabel("Autorzy:");
		lblAutorProgramu.setFont(new Font("Verdana", Font.ITALIC, 14));
		
		JLabel lblMateuszFilipowicz = new JLabel("Mateusz Filipowicz");
		lblMateuszFilipowicz.setFont(new Font("Verdana", Font.BOLD, 14));
		
		JLabel lblPiotrMalecki = new JLabel("Piotr Małecki");
		lblPiotrMalecki.setFont(new Font("Verdana", Font.BOLD, 14));
		
		JLabel lblDataWydania = new JLabel("Data wydania:");
		lblDataWydania.setFont(new Font("Verdana", Font.ITALIC, 14));
		
		JLabel lblData = new JLabel("2013.01.20");
		lblData.setFont(new Font("Verdana", Font.BOLD, 14));
		
		JLabel lblVersion = new JLabel("Version");
		lblVersion.setFont(new Font("Verdana", Font.ITALIC, 14));
		
		JLabel lbla = new JLabel("0.1a");
		lbla.setFont(new Font("Verdana", Font.BOLD, 14));
		
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblDbconnector, GroupLayout.PREFERRED_SIZE, 259, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
					.addGap(108)
					.addComponent(lblAutorProgramu, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(110))
				.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
					.addGap(66)
					.addComponent(lblMateuszFilipowicz, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(70))
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addContainerGap(112, Short.MAX_VALUE)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup()
							.addGap(10)
							.addComponent(lbla))
						.addComponent(lblVersion))
					.addGap(114))
				.addGroup(Alignment.LEADING, gl_contentPanel.createSequentialGroup()
					.addGap(92)
					.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(lblData)
						.addComponent(lblPiotrMalecki, GroupLayout.DEFAULT_SIZE, 168, Short.MAX_VALUE)
						.addComponent(lblDataWydania, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(5)
					.addComponent(lblDbconnector, GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblAutorProgramu, GroupLayout.PREFERRED_SIZE, 17, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblMateuszFilipowicz)
					.addGap(3)
					.addComponent(lblPiotrMalecki)
					.addGap(18)
					.addComponent(lblDataWydania)
					.addGap(4)
					.addComponent(lblData)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblVersion)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lbla)
					.addContainerGap())
		);
		contentPanel.setLayout(gl_contentPanel);
		{
			
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						setVisible(false);					
					}
				});
				okButton.setPreferredSize(new Dimension (76,28));
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
		}
	}
}
