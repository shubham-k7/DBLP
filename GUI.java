import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.PlainDocument;
import java.awt.*;
import java.awt.event.*;
import javax.swing.text.PlainDocument;

public class GUI
{
		private JFrame mainFrame;
		GUI()
		{
			mainFrame = new JFrame();
			mainFrame.setSize(1600,900);
			mainFrame.setLocationRelativeTo(null);
			mainFrame.setTitle("DBLP Query Engine");
			mainFrame.setLayout(new BorderLayout());
			mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);

			mainFrame.add(headingPanel(),BorderLayout.NORTH);
			mainFrame.add(bodyPanel());
			mainFrame.setVisible(true);
		}
	private JTable results;
	private int i = 0;
	private JRadioButton newJRadioButton(String s)
	{
		JRadioButton b = new JRadioButton(s);
		b.setActionCommand(s);
		b.setFont(new Font("Comic Sans MS",Font.BOLD,22));
		return b;
	}
	private JPanel headingPanel()
		{
			JPanel hPanel= new JPanel();
			JLabel heading = new JLabel();
			heading.setText("DBLP Query Engine");
			heading.setFont(new Font("Calibri", Font.BOLD,70));
			heading.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			heading.setVisible(true);
			hPanel.add(heading);
			return hPanel;
		}
	private JTable table;


	DefaultTableModel model;
	private int rowCount= 20;
	private JPanel bodyPanel()
	{
		JPanel bPanel = new JPanel();
		JPanel resultPanel = new JPanel();
		JPanel queryPanel = new JPanel();

		bPanel.setLayout(new BorderLayout());

		resultPanel.setBorder(BorderFactory.createTitledBorder("Results"));
		queryPanel.setBorder(BorderFactory.createTitledBorder("Query Select"));


		queryPanel.setPreferredSize(new Dimension(480,700));
		resultPanel.setPreferredSize(new Dimension(1100,700));


		queryPanel.setLayout(new GridLayout(10,1));
		queryPanelSet(queryPanel);

//		resultPanel.setLayout(new GridLayout(,1));
		resultPanelSet(resultPanel);

		bPanel.add(queryPanel,BorderLayout.WEST);
		bPanel.add(resultPanel,BorderLayout.EAST);

		return bPanel;
	}
	private String[][] getNextEntries(JTable table, int index)
	{
		return null;
	}
	private void resultPanelSet(JPanel resultPanel)
	{

		String[] headings = {"S_No","Authors","Title","Pages","Year","Volume","Journal/Book Title","URL"};
		String[][] data = new String[20][8];
		DefaultTableModel model = new DefaultTableModel(20,headings.length);
		model.setColumnIdentifiers(headings);
		table = new JTable(model);
		table.setVisible(true);
		table.setPreferredSize(new Dimension(1080,500));
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setFillsViewportHeight(true);
		JScrollPane sp = new JScrollPane(table);
		sp.setSize(new Dimension(900,900));
		JPanel yolo = new JPanel();
		yolo.add(sp);
		JButton next = new JButton("Next");
		next.setPreferredSize(new Dimension(100,30));
		next.setFont(new Font("Comic Sans MS",Font.BOLD,15));
		next.setVisible(true);
		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getNextEntries(table,i);
			}
		});
//		yolo.setSize(600,800);
		resultPanel.add(yolo);
		//,BorderLayout.CENTER);
		resultPanel.add(next);//,BorderLayout.SOUTH);
		results = table;
	}

	private void queryPanelSet(JPanel queryPanel)
		{
			String list[] = {"Query 1","Query 2","Query 3"};
			JComboBox queryList = new JComboBox(list);
			ActionListener cb = new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String s = (String) queryList.getSelectedItem();
					switch (s)
					{
						case "Query 1":
							query1(queryPanel);
							break;
						case "Query 2":
							query2(queryPanel);
							break;
						case "Query 3":
							query3(queryPanel);
							break;
					}
				}
			};

			queryList.addActionListener(cb);
			queryList.setEditable(true);
			queryList.setFont(new Font("Comic Sans MS", Font.BOLD,20));
			queryList.setPreferredSize(new Dimension(150,40));
			queryList.setSelectedItem("Queries");
			queryList.setEditable(false);
			queryList.setVisible(true);
			JPanel lol = new JPanel();
			lol.add(queryList);
			lol.setVisible(true);
			queryPanel.add(lol);
		}
	private void query1(JPanel queryPanel) {


		queryPanel.setEnabled(false);
		queryPanel.setVisible(false);
		queryPanel.removeAll();
		queryPanelSet(queryPanel);
//        (JComboBox) ((JPanel) queryPanel.getComponent(0)).getComponent(0);
//        queryPanel.getComponent(0)

		String list[] = {"Name","Title"};
		JComboBox searchSelect = new JComboBox(list);

		searchSelect.setPreferredSize(new Dimension(150,40));
		searchSelect.setFont(new Font("Comic Sans MS", Font.BOLD,20));
		searchSelect.setEditable(true);
		searchSelect.setSelectedItem("Search By");
		searchSelect.setEditable(false);
		searchSelect.setVisible(true);

		JPanel lol1 = new JPanel();
		lol1.add(searchSelect);
		lol1.setVisible(true);

		JLabel nameLabel = new JLabel("Name/Title");
		nameLabel.setPreferredSize(new Dimension(150,30));
		nameLabel.setFont(new Font("Comic Sans MS", Font.BOLD,22));
		nameLabel.setVisible(true);
		JTextField nameText = new JTextField(20);
		nameText.setPreferredSize(new Dimension(200,30));
		queryPanel.add(lol1);
		JPanel lol2 = new JPanel();
		lol2.add(nameLabel);
		lol2.add(nameText);
		lol2.setVisible(true);
		queryPanel.add(lol2);

		JLabel yearLabel = new JLabel("Since Year");
		yearLabel.setPreferredSize(new Dimension(150,30));
		yearLabel.setFont(new Font("Comic Sans MS", Font.BOLD,22));
		yearLabel.setVisible(true);
		JTextField yearText= new JTextField("YYYY",4);
		yearText.setDocument(new JTextFieldLimit(4));
		yearText.setText("YYYY");

		yearText.setPreferredSize(new Dimension(200,30));
		JPanel lol3 = new JPanel();
		lol3.add(yearLabel);
		lol3.add(yearText);
		lol3.setVisible(true);
		queryPanel.add(lol3);

		JLabel customLabel = new JLabel("Custom Range");
		customLabel.setPreferredSize(new Dimension(150,30));
		customLabel.setFont(new Font("Comic Sans MS", Font.BOLD,22));
		customLabel.setVisible(true);
		JTextField c1Text= new JTextField("YYYY",4);
		c1Text.setDocument(new JTextFieldLimit(4));
		c1Text.setText("YYYY");
		c1Text.setPreferredSize(new Dimension(200,30));
		JTextField c2Text = new JTextField("YYYY",4);
		c2Text.setPreferredSize(new Dimension(200,30));
		c2Text.setDocument(new JTextFieldLimit(4));
		c2Text.setText("YYYY");
		JLabel dashLabel = new JLabel("-");
		dashLabel.setPreferredSize(new Dimension(20,30));
		dashLabel.setFont(new Font("Comic Sans MS", Font.BOLD,22));
		dashLabel.setVisible(true);
		JPanel lol4 = new JPanel();
		lol4.setLayout(new FlowLayout(FlowLayout.LEADING,20,10));
		lol4.add(customLabel);
		lol4.add(c1Text);
		lol4.add(dashLabel);
		lol4.add(c2Text);
		lol4.setVisible(true);
		queryPanel.add(lol4);

		JPanel butPanel = new JPanel();
		butPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		JRadioButton o1 = newJRadioButton("Sort By Year");
		JRadioButton o2 = newJRadioButton("Sort By Relevance");
		o1.setPreferredSize(new Dimension(250,30));
		o2.setPreferredSize(new Dimension(250,40));
		ButtonGroup gp = new ButtonGroup();
		gp.add(o1);
		gp.add(o2);
		butPanel.add(o1);
		butPanel.add(o2);
		butPanel.setVisible(true);
		queryPanel.add(butPanel);

		JPanel butPanel2 = new JPanel();
        butPanel2.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
		JButton b1 = new JButton("Search");
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				System.out.println("Search was pressed!");
				boolean formatCorrect = checkFormats(yearText,c1Text,c2Text);
				if(formatCorrect)
				{
					JOptionPane.showMessageDialog(null,"String is correct");
					DefaultTableModel model = (DefaultTableModel) results.getModel();
					model.addRow(new Object[]{"Yolo", "fdsafdsa", "fdsafdsa", "fdsa","fdsa","fdsa","fdsa","htrhgr"});
					table = new JTable(model);
				}
			}
		});
		JButton b2 = new JButton("Reset");
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				query1(queryPanel);
			}
		});
		b2.setBackground(Color.MAGENTA);
		b1.setPreferredSize(new Dimension(100,30));
		b2.setPreferredSize(new Dimension(100,30));
		b1.setFont(new Font("Comic Sans MS",Font.BOLD,15));
		b2.setFont(new Font("Comic Sans MS",Font.BOLD,15));
		b1.setVisible(true);
		b2.setVisible(true);
		butPanel2.add(b1);
		butPanel2.add(b2);
		butPanel2.setVisible(true);
		queryPanel.add(butPanel2);

		queryPanel.revalidate();
		queryPanel.setVisible(true);
		queryPanel.setEnabled(true);


	}
	private boolean checkFormats(JTextField a,JTextField b,JTextField c)
	{
		String s1 = a.getText();
		String s2 = b.getText();
		String s3 = c.getText();
//		System.out.println("s1: "+s1);
//		System.out.println("s2: "+s2);
//		System.out.println("s3: "+s3);
		boolean flag = true;
		if(s1.matches(".*[A-Za-z].*") || s1.length()!=4)
		{
			JOptionPane.showMessageDialog(null,"Invalid Entry! Only numbers! Try again");

			flag=false;
		}
		else if (s2.matches(".*[A-Za-z].*") || s2.length()!=4)
		{
			JOptionPane.showMessageDialog(null,"Invalid Entry! Only numbers! Try again");
//			JOptionPane.showMessageDialog(null,"Invalid Year Range! Try Again");
			flag=false;
		}
		else if(s3.matches(".*[A-Za-z].*") || s3.length()!=4)
		{
			JOptionPane.showMessageDialog(null,"Invalid Entry! Only numbers! Try again");
//			JOptionPane.showMessageDialog(null,"Invalid Year Range! Try Again");
			flag=false;
		}
		else if(s1.length()!=4)
		{
			JOptionPane.showMessageDialog(null,"Invalid Entry! Exactly 4 Digits please");
			flag=false;
		}
		else if(s2.length()!=4)
		{
			JOptionPane.showMessageDialog(null,"Invalid Entry! Exactly 4 Digits please");
			flag=false;
		}
		else if(s3.length()!=4)
		{
			JOptionPane.showMessageDialog(null,"Invalid Entry! Exactly 4 Digits please");
			flag=false;
		}
		return flag;
	}

	public class JTextFieldLimit extends PlainDocument {
		private int limit;

		JTextFieldLimit(int limit) {
			super();
			this.limit = limit;
		}

		public void insertString( int offset, String  str, AttributeSet attr ) throws BadLocationException {
			if (str == null) return;

			if ((getLength() + str.length()) <= limit) {
				super.insertString(offset, str, attr);
			}
		}
	}
	private void query2(JPanel queryPanel) {

		queryPanel.setVisible(false);
		queryPanel.removeAll();
		queryPanelSet(queryPanel);

		JLabel numPanel = new JLabel("Number of Publications");
		numPanel.setPreferredSize(new Dimension(300,30));
		numPanel.setFont(new Font("Comic Sans MS", Font.BOLD,22));
		numPanel.setVisible(true);
		JTextField numText = new JTextField(4);
		numText.setEditable(false);
		numText.setPreferredSize(new Dimension(200,30));
		JPanel lol2 = new JPanel();
		lol2.add(numPanel);
		lol2.add(numText);
		lol2.setVisible(true);
		queryPanel.add(lol2);

		JPanel butPanel2 = new JPanel();
        butPanel2.setLayout(new FlowLayout(FlowLayout.CENTER,20,20));
        JButton b1 = new JButton("Search");
		JButton b2 = new JButton("Reset");
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				System.out.println("Search was pressed!");
//				checkFormats();
			}
		});
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				query2(queryPanel);
			}
		});
		b2.setBackground(Color.MAGENTA);
		b1.setPreferredSize(new Dimension(100,30));
		b2.setPreferredSize(new Dimension(100,30));
		b1.setFont(new Font("Comic Sans MS",Font.BOLD,15));
		b2.setFont(new Font("Comic Sans MS",Font.BOLD,15));
		b1.setVisible(true);
		b2.setVisible(true);
		butPanel2.add(b1);
		butPanel2.add(b2);
		butPanel2.setVisible(true);
		queryPanel.add(butPanel2);

		queryPanel.revalidate();
		queryPanel.setVisible(true);
        queryPanel.setEnabled(true);
	}

	private void query3(JPanel queryPanel) {
		queryPanel.setVisible(false);
		queryPanel.removeAll();

		queryPanelSet(queryPanel);

		queryPanel.revalidate();
		queryPanel.setVisible(true);
		queryPanel.setEnabled(true);
	}
		public static void main(String[] args)
		{
			new GUI();
		}
}
