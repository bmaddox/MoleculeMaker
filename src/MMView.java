import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class MMView extends JFrame
{
	final int WINDOW_WIDTH = 400;
	final int WINDOW_HEIGHT = 600;
	
	MMController mmc;
	MoleculeGrid sketch;
	JMenuBar menuBar;
	ElementAttributesModifier mod;
	JPanel center = new JPanel();
	JPanel south = new JPanel();
	
	public MMView(MMController mmc)
	{
		this.mmc = mmc;
		createGUI();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	/**
	 * Initializes the view's components, adds them to the frame, then shows the
	 * window.
	 */
	private void createGUI()
	{
		this.setLayout(new BorderLayout());

		createMenuBar();
		
		// Graph panel
		sketch = new MoleculeGrid(mmc);
		mod = new ElementAttributesModifier(null);

		add(sketch, BorderLayout.CENTER);
		
		// Set up/add to SOUTH
		south.add(mod);
		add(south, BorderLayout.SOUTH);
		
		this.setTitle("Molecule Maker � By Derek Cannon");
		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setVisible(true);
	}
	
	private void createMenuBar()
	{
		// File menu (thanks to: http://www.java-tips.org/java-se-tips/javax.swing/how-to-create-menu-bar.html)
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("File");
		JMenu graphMenu = new JMenu("Graph");
		
		// File submenu
		JMenuItem exportAction = new JMenuItem("Export to XML");
		JMenuItem quitAction = new JMenuItem("Quit");
		fileMenu.add(exportAction);
		fileMenu.addSeparator();
		fileMenu.add(quitAction);
		menuBar.add(fileMenu);
		
		menuBar.add(graphMenu);
		JMenuItem clearElementsAction = new JMenuItem("Clear Element");
		JMenuItem clearBondsAction = new JMenuItem("Clear Bonds");
		graphMenu.add(clearElementsAction);
		graphMenu.add(clearBondsAction);
		
		// ActionListeners:
		exportAction.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mmc.exportToXML();
			}
		});
		quitAction.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mmc.quit();
			}
		});
		
		clearElementsAction.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mmc.clearElements();
			}
		});
		clearBondsAction.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				mmc.clearBonds();
			}
		});
	}
	
	public void displayElementAttributes(Element e)
	{	
		mmc.displayElementAttributes(e); // logic for this belongs in controller
	}
	
	
}