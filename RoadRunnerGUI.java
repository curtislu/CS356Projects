package roadrunnergui;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

class RoadRunnerGUI implements ActionListener {

	GridBagConstraints gbc = new GridBagConstraints();
	JButton jbtnOut, jbtnReset, jbtnRun, jbtnSelect;
	JCheckBox[] jcb = new JCheckBox[11];
	JFileChooser jfc = new JFileChooser();
	JFrame jfrm = new JFrame("RoadRunner Dynamic Analysis Framework - GUI");
	JLabel jlab = new JLabel("No file selected.");
	JMenuBar jmb = new JMenuBar();
	JMenuItem jmiRun, jmiOut;
	JPanel jpnl1, jpnl2, jpnl3, jpnlBot, jpnlTop;

	RoadRunnerGUI() {
		// Frame configurations
		jfrm.setSize(750, 500);
		jfrm.getContentPane().setLayout(new GridLayout(2, 1));
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
		JMenuItem tool1 = new JMenuItem("Atomizer");
		JMenuItem tool2 = new JMenuItem("BarrierEraser");
		JMenuItem tool3 = new JMenuItem("FastTrack");
		JMenuItem tool4 = new JMenuItem("HappensBefore");
		JMenuItem tool5 = new JMenuItem("LockSet");
		JMenuItem tool6 = new JMenuItem("ProtectingLock");
		JMenuItem tool7 = new JMenuItem("ReadShared");
		jmTool.add(tool1);
		jmTool.add(tool2);
		jmTool.add(tool3);
		jmTool.add(tool4);
		jmTool.add(tool5);
		jmTool.add(tool6);
		jmTool.add(tool7);
		// Submenu - Options
		JMenu jmOpt = new JMenu("Options");
		JMenuItem opt1 = new JMenuItem("Array");
		JMenuItem opt2 = new JMenuItem("Classpath");
		JMenuItem opt3 = new JMenuItem("MaxTID");
		JMenuItem opt4 = new JMenuItem("NoBarrier");
		jmOpt.add(opt1);
		jmOpt.add(opt2);
		jmOpt.add(opt3);
		jmOpt.add(opt4);
		JMenuItem jmiReset = new JMenuItem("Reset GUI", KeyEvent.VK_R);
		jmiReset.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.ALT_MASK));
		jmPref.add(jmTool);
		jmPref.add(jmOpt);
		jmPref.addSeparator();
		jmPref.add(jmiReset);
		jmb.add(jmPref);

		// Menubar - Help
		JMenu jmHelp = new JMenu("Help");
		JMenuItem jmiGet = new JMenuItem("Getting Started", KeyEvent.VK_G);
		jmiGet.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.ALT_MASK));
		JMenuItem jmiDoc = new JMenuItem("Documentation", KeyEvent.VK_D);
		jmiDoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.ALT_MASK));
		JMenuItem jmiAbout = new JMenuItem("About", KeyEvent.VK_A);
		jmiAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.ALT_MASK));
		jmHelp.add(jmiGet);
		jmHelp.add(jmiDoc);
		jmHelp.add(jmiAbout);
		jmb.add(jmHelp);

		jmiSelect.addActionListener(this);
		jmiRun.addActionListener(this);
		jmiOut.addActionListener(this);
		jmiExit.addActionListener(this);
		jmiReset.addActionListener(this);
		jmiGet.addActionListener(this);
		jmiDoc.addActionListener(this);
		jmiAbout.addActionListener(this);

		// Top panel
		jpnlTop = new JPanel();
		jpnlTop.setLayout(new GridLayout(1, 3));
		jpnlTop.setOpaque(true);
		
		// Tool grid
		jpnl1 = new JPanel();
		jpnl1.setLayout(new GridLayout(4, 2));
		jpnl1.setBorder(BorderFactory.createTitledBorder("Step 1"));
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
		jpnl2.setLayout(new GridLayout(2, 2));
		jpnl2.setBorder(BorderFactory.createTitledBorder("Step 2"));
		jpnl2.setOpaque(true);
		jcb[7] = new JCheckBox("Array");
		jcb[8] = new JCheckBox("Classpath");
		jcb[9] = new JCheckBox("MaxTID");
		jcb[10] = new JCheckBox("NoBarrier");
		for (int i = 7; i < 11; i++) {
			jcb[i].addActionListener(this);
			jpnl2.add(jcb[i]);
		}

		// Button grid
		jpnl3 = new JPanel();
		jpnl3.setLayout(new GridBagLayout());
		jpnl3.setBorder(BorderFactory.createTitledBorder("Step 3"));
		jpnl3.setOpaque(true);
		jbtnSelect = new JButton("Select Java File...");
		jbtnSelect.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets = new Insets(0, 0, 0, 0);
		jpnl3.add(jbtnSelect, gbc);
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets = new Insets(10, 0, 0, 0);
		jpnl3.add(jlab, gbc);
		jbtnRun = new JButton("Run Program");
		jbtnRun.setEnabled(false);
		jbtnRun.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.insets = new Insets(20, 0, 5, 0);
		jpnl3.add(jbtnRun, gbc);
		jbtnOut = new JButton("Output File");
		jbtnOut.setEnabled(false);
		jbtnOut.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.insets = new Insets(5, 0, 20, 0);
		jpnl3.add(jbtnOut, gbc);
		jbtnReset = new JButton("Reset GUI");
		jbtnReset.setEnabled(true);
		jbtnReset.addActionListener(this);
		gbc.gridx = 0;
		gbc.gridy = 4;
		gbc.insets = new Insets(0, 0, 0, 0);
		jpnl3.add(jbtnReset, gbc);
		
		// Bottom panel
		jpnlBot = new JPanel();
		
		jpnlTop.add(jpnl1);
		jpnlTop.add(jpnl2);
		jpnlTop.add(jpnl3);

		jfrm.setJMenuBar(jmb);
		jfrm.getContentPane().add(jpnlTop);
		jfrm.getContentPane().add(jpnlBot);
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

		if (comStr.equals("Reset GUI")) {
			for (int i = 0; i < 11; i++) {
				jcb[i].setSelected(false);
			}
			
			jlab.setText("No file selected.");
			jmiRun.setEnabled(false);
			jbtnRun.setEnabled(false);
			jmiOut.setEnabled(false);
			jbtnOut.setEnabled(false);
		}
		
		if (comStr.equals("Getting Started")) {
			JOptionPane.showMessageDialog(jfrm, "To use this GUI, simply follow the steps below: "
					+ "\n\n\n"
					+ "Step 1: Choose the desired tools."
					+ "\n\n"
					+ "Step 2: Choose the desired options."
					+ "\n\n"
					+ "Step 3a: Choose which java file to run."
					+ "\n\n"
					+ "Step 3b: Press \"Run Program\" to run the program."
					+ "\n\n"
					+ "Step 4: Press \"Output File\" to show the results in XML format.",
					"How to - RoadRunnerGUI", JOptionPane.INFORMATION_MESSAGE);
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
