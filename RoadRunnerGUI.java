package roadrunnergui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

class RoadRunnerGUI implements ActionListener {

	GridBagConstraints gbc = new GridBagConstraints();
	JButton jbtnOut, jbtnRun, jbtnSelect;
	JCheckBox[] jcb = new JCheckBox[17];
	JFileChooser jfc = new JFileChooser();
	JFrame jfrm = new JFrame("RoadRunner Dynamic Analysis Framework");
	JLabel jlab = new JLabel("No file selected.");
	JMenuBar jmb = new JMenuBar();
	JMenuItem jmiRun, jmiOut;
	JPanel jpnl1, jpnl2, jpnl3, jpnl4;

	RoadRunnerGUI() {
		// Frame configurations
		jfrm.setSize(500, 500);
		jfrm.getContentPane().setLayout(new GridLayout(2, 2));
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jfrm.setLocationRelativeTo(null);
		jfrm.setVisible(true);
		jfc.setFileFilter(new JavaFileFilter());

		// Menubar - File
		JMenu jmFile = new JMenu("File");
		JMenuItem jmiSelect = new JMenuItem("Select Java File...", KeyEvent.VK_S);
		jmiSelect.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.ALT_MASK));
		jmiRun = new JMenuItem("Run Program", KeyEvent.VK_P);
		jmiRun.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.ALT_MASK));
		jmiRun.setEnabled(false);
		jmiOut = new JMenuItem("Output File", KeyEvent.VK_O);
		jmiOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.ALT_MASK));
		jmiOut.setEnabled(false);
		JMenuItem jmiExit = new JMenuItem("Exit", KeyEvent.VK_E);
		jmiExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.ALT_MASK));
		jmFile.add(jmiSelect);
		jmFile.add(jmiRun);
		jmFile.add(jmiOut);
		jmFile.addSeparator();
		jmFile.add(jmiExit);
		jmb.add(jmFile);

		// Menubar - Preferences
		JMenu jmPref = new JMenu("Preferences");
		// Submenu - Tools
		JMenu jmTool = new JMenu("Tools");
		JMenuItem tool1 = new JMenuItem("Tool 1");
		JMenuItem tool2 = new JMenuItem("Tool 2");
		JMenuItem tool3 = new JMenuItem("Tool 3");
		JMenuItem tool4 = new JMenuItem("Tool 4");
		JMenuItem tool5 = new JMenuItem("Tool 5");
		jmTool.add(tool1);
		jmTool.add(tool2);
		jmTool.add(tool3);
		jmTool.add(tool4);
		jmTool.add(tool5);
		// Submenu - Options
		JMenu jmOpt = new JMenu("Options");
		JMenuItem opt1 = new JMenuItem("Option 1");
		JMenuItem opt2 = new JMenuItem("Option 2");
		JMenuItem opt3 = new JMenuItem("Option 3");
		JMenuItem opt4 = new JMenuItem("Option 4");
		JMenuItem opt5 = new JMenuItem("Option 5");
		jmOpt.add(opt1);
		jmOpt.add(opt2);
		jmOpt.add(opt3);
		jmOpt.add(opt4);
		jmOpt.add(opt5);
		JMenuItem jmiReset = new JMenuItem("Reset", KeyEvent.VK_R);
		jmiReset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.ALT_MASK));
		jmPref.add(jmTool);
		jmPref.add(jmOpt);
		jmPref.addSeparator();
		jmPref.add(jmiReset);
		jmb.add(jmPref);

		// Menubar - Help
		JMenu jmHelp = new JMenu("Help");
		JMenuItem jmiDoc = new JMenuItem("Documentation", KeyEvent.VK_D);
		jmiDoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.ALT_MASK));
		JMenuItem jmiAbout = new JMenuItem("About", KeyEvent.VK_A);
		jmiAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_MASK));
		jmHelp.add(jmiDoc);
		jmHelp.add(jmiAbout);
		jmb.add(jmHelp);

		jmiSelect.addActionListener(this);
		jmiRun.addActionListener(this);
		jmiOut.addActionListener(this);
		jmiExit.addActionListener(this);
		jmiReset.addActionListener(this);
		jmiDoc.addActionListener(this);
		jmiAbout.addActionListener(this);

		// Tool grid
		jpnl1 = new JPanel();
		jpnl1.setLayout(new GridLayout(4, 2));
		jpnl1.setBorder(BorderFactory.createTitledBorder("Tools"));
		jpnl1.setOpaque(true);
		jcb[0] = new JCheckBox("Atomizer");
		jcb[1] = new JCheckBox("BarrierEraser");
		jcb[2] = new JCheckBox("FastTrack");
		jcb[3] = new JCheckBox("HappensBefore");
		jcb[4] = new JCheckBox("LockSet");
		jcb[5] = new JCheckBox("ProtectingLock");
		jcb[6] = new JCheckBox("ReadShared");
		for (int i = 0; i < 7; i++) {
			jcb[i].addActionListener(this);
			jpnl1.add(jcb[i]);
		}

		// Option grid
		jpnl2 = new JPanel();
		jpnl2.setLayout(new GridLayout(5, 2));
		jpnl2.setBorder(BorderFactory.createTitledBorder("Options"));
		jpnl2.setOpaque(true);
		jcb[7] = new JCheckBox("Option 1");
		jcb[8] = new JCheckBox("Option 2");
		jcb[9] = new JCheckBox("Option 3");
		jcb[10] = new JCheckBox("Option 4");
		jcb[11] = new JCheckBox("Option 5");
		jcb[12] = new JCheckBox("Option 6");
		jcb[13] = new JCheckBox("Option 7");
		jcb[14] = new JCheckBox("Option 8");
		jcb[15] = new JCheckBox("Option 9");
		jcb[16] = new JCheckBox("Option 10");
		for (int i = 7; i < 17; i++) {
			jcb[i].addActionListener(this);
			jpnl2.add(jcb[i]);
		}

		// Java file grid
		jpnl3 = new JPanel();
		jpnl3.setLayout(new GridBagLayout());
		jpnl3.setBorder(BorderFactory.createTitledBorder("Java File"));
		jpnl3.setOpaque(true);
		jbtnSelect = new JButton("Select Java File...");
		jbtnSelect.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 40, 0);
		jpnl3.add(jbtnSelect, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		jpnl3.add(jlab, gbc);

		// Button grid
		jpnl4 = new JPanel();
		jpnl4.setLayout(new GridBagLayout());
		jpnl4.setBorder(BorderFactory.createTitledBorder("Execute"));
		jpnl4.setOpaque(true);
		jbtnRun = new JButton("Run Program");
		jbtnRun.setEnabled(false);
		jbtnRun.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 40, 0);
		jpnl4.add(jbtnRun, gbc);
		jbtnOut = new JButton("Output File");
		jbtnOut.setEnabled(false);
		jbtnOut.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 1;
		jpnl4.add(jbtnOut, gbc);

		jfrm.setJMenuBar(jmb);
		jfrm.getContentPane().add(jpnl1);
		jfrm.getContentPane().add(jpnl2);
		jfrm.getContentPane().add(jpnl3);
		jfrm.getContentPane().add(jpnl4);
	}

	public void actionPerformed(ActionEvent ae) {
		String comStr = ae.getActionCommand();

		if (comStr.equals("Select Java File...")) {
			int result = jfc.showOpenDialog(null);

			if (result == JFileChooser.APPROVE_OPTION) {
				jlab.setText("Selected file is: " + jfc.getSelectedFile().getName());
				jmiRun.setEnabled(true);
				jbtnRun.setEnabled(true);
				jmiOut.setEnabled(true);
				jbtnOut.setEnabled(true);
			} else {
				jlab.setText("No file selected.");
			}
		}

		if (comStr.equals("Exit")) {
			System.exit(0);
		}

		if (comStr.equals("Reset")) {
			for (int i = 0; i < 17; i++) {
				jcb[i].setSelected(false);
			}
			
			jlab.setText("No file selected.");
			jmiRun.setEnabled(false);
			jbtnRun.setEnabled(false);
			jmiOut.setEnabled(false);
			jbtnOut.setEnabled(false);
		}

		if (comStr.equals("Documentation")) {
			try {
				// Change path to pdf according to laptop.
				Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler C:\\Users\\Danny\\Documents\\NetBeansProjects\\RoadrunnerGUI\\RDAF.pdf");
				p.waitFor();
			} catch (Exception error) {
				error.printStackTrace();
			}
		}

		if (comStr.equals("About")) {
			JOptionPane.showMessageDialog(jfrm, "This GUI was made by:"
					+ "\n\n"
					+ "- Chuc, Jonathan"
					+ "\n"
					+ "- Ho, Duy"
					+ "\n"
					+ "- Jung, Jay"
					+ "\n"
					+ "- Lu, Curtis"
					+ "\n\n"
					+ "© 2014",
					"Credits", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new RoadRunnerGUI();
			}
		});
	}
}

class JavaFileFilter extends FileFilter {

	public boolean accept(File file) {
		if (file.getName().endsWith(".java")) {
			return true;
		}

		if (file.isDirectory()) {
			return true;
		}

		return false;
	}

	public String getDescription() {
		return "Java Files (*.java)";
	}
}
