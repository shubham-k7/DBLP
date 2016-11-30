import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

import javax.swing.text.PlainDocument;

import java.awt.*;
import java.awt.event.*;

/**
 * This code is made by Shubham Khanna and Dhruva Sahrawat as a part of Advanced
 * Programming Course. Monsoon '16
 */
public class GUI {
	private JFrame mainFrame;
	Query1 c_query1;
	private int counter = 0;
	private int cq = 0;
	private int k = 0;

	GUI() {
		new EntityResolver();
		c_query1 = new Query1();
		mainFrame = new JFrame();
		mainFrame.setSize(1600, 900);
		mainFrame.setLocationRelativeTo(null);
		mainFrame.setTitle("DBLP Query Engine");
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainFrame.add(headingPanel(), BorderLayout.NORTH);
		mainFrame.add(bodyPanel());
		mainFrame.setVisible(true);

	}

	private JTable results;
	private int i = 1;

	private JRadioButton newJRadioButton(String s) {
		JRadioButton b = new JRadioButton(s);
		b.setActionCommand(s);
		b.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
		return b;
	}

	private JPanel headingPanel() {
		JPanel hPanel = new JPanel();
		JLabel heading = new JLabel();
		heading.setText("DBLP Query Engine");
		heading.setFont(new Font("Calibri", Font.BOLD, 70));
		heading.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
		heading.setVisible(true);
		hPanel.add(heading);
		return hPanel;
	}

	private JTable table;

	/**
	 * This
	 */
	DefaultTableModel model;
	private int rowCount = 20;

	private JPanel bodyPanel() {
		JPanel bPanel = new JPanel();
		JPanel resultPanel = new JPanel();
		JPanel queryPanel = new JPanel();

		bPanel.setLayout(new BorderLayout());

		resultPanel.setBorder(BorderFactory.createTitledBorder("Results"));
		queryPanel.setBorder(BorderFactory.createTitledBorder("Query Select"));

		queryPanel.setPreferredSize(new Dimension(480, 700));
		resultPanel.setPreferredSize(new Dimension(1100, 700));

		queryPanel.setLayout(new GridLayout(8, 1));
		queryPanelSet(queryPanel, 0);

		// resultPanel.setLayout(new GridLayout(,1));
		resultPanelSet(resultPanel);

		bPanel.add(queryPanel, BorderLayout.WEST);
		bPanel.add(resultPanel, BorderLayout.EAST);

		return bPanel;
	}
	private int ans = 0;
	private int getNextEntries(JTable table, int index, String sp1, int val, int y1, int y2) {
		DefaultTableModel model = (DefaultTableModel) results.getModel();
		model.setRowCount(0);
		if(index == 0)
		{
			ans = c_query1.ret_searchresult().size();
			resultLabel.setText("Result Count: "+ans);
		}
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		for (int j = 0; j < 20; j++) {
			int a = (index * 20) + j + 1;
			if (a > c_query1.ret_searchresult().size()) {
				i = 1;
				counter = 0;
				globNext.setEnabled(false);
//				model.setRowCount(0);
				return 1;

			}
			Publications cur_publications = c_query1.ret_Search_result(counter);
			counter++;
			while (Integer.parseInt((cur_publications.ret_year())) > y2) {
				if (counter > c_query1.ret_searchresult().size()) {
					i = 1;
					ans = counter;
					resultLabel.setText("Result Count: "+ans);
					globNext.setEnabled(false);
					counter = 0;
					return 1;

				}

				cur_publications = c_query1.ret_Search_result(counter);
				counter++;
			}
			if (Integer.parseInt((cur_publications.ret_year())) >= y1
					&& Integer.parseInt((cur_publications.ret_year())) <= y2) {

				String[] temp = cur_publications.gui_output_format(sp1, val, a - 1);
				temp[0] = "";
				temp[0] += a;
				model.addRow(temp);
			}
		}
		return 0;
	}

	private int y1, y2;
	int next_status=0;
	private JButton globNext;
	int index = 1;
	private void resultPanelSet(JPanel resultPanel) {
		next_status = 0;
		String[] headings = { "S_No", "Authors", "Title", "Pages", "Year", "Volume", "Journal/Book Title", "URL" };
		// String[][] data = new String[20][8];
		DefaultTableModel model = new DefaultTableModel(rowCount, headings.length);
		model.setColumnIdentifiers(headings);
		model.setRowCount(0);
		table = new JTable(model);
		table.setVisible(true);
		table.setPreferredSize(new Dimension(1080, 500));
		table.setPreferredScrollableViewportSize(table.getPreferredSize());
		table.setFillsViewportHeight(true);
		JScrollPane sp = new JScrollPane(table);
		sp.setSize(new Dimension(900, 900));
		JPanel yolo = new JPanel();
		yolo.add(sp);
		JButton next = new JButton("Next");
		next.setPreferredSize(new Dimension(100, 30));
		next.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		next.setVisible(true);
		globNext = next;

		next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (cq == 1){
					next_status = getNextEntries(table, i++, sp1, val, y1, y2);
					if(next_status==1)
					{
						next.setEnabled(false);
					}
				}
				else if (cq == 2){
					next_status = getNextEntries2(table, index++, k);
					if(next_status==1)
					{
						next.setEnabled(false);
					}
				}
					
			}
		});
		// yolo.setSize(600,800);
		resultPanel.add(yolo);
		// ,BorderLayout.CENTER);
		JLabel countLabel = new JLabel("Result Count: ");
		countLabel.setPreferredSize(new Dimension(400, 30));
		countLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
		countLabel.setVisible(true);
		JPanel as = new JPanel();
		as.add(countLabel);
		resultLabel = countLabel;
		resultPanel.add(as);
		resultPanel.add(next);// ,BorderLayout.SOUTH);
		results = table;
	}

	private void queryPanelSet(JPanel queryPanel, int choice) {
		String list[] = { "Queries", "Query 1", "Query 2", "Query 3" };
		JComboBox<String> queryList = new JComboBox<String>(list);
		ActionListener cb = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = (String) queryList.getSelectedItem();
				switch (s) {
				case "Query 1":
					cq = 1;
					query1(queryPanel);
					break;
				case "Query 2":
					cq = 2;
					query2(queryPanel);
					break;
				case "Query 3":
					cq = 3;
					query3(queryPanel);
					break;
				case "Queries":
					break;
				}
			}
		};

		queryList.addActionListener(cb);

		queryList.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		queryList.setPreferredSize(new Dimension(150, 40));
		if (choice == 0) {
			queryList.setEditable(true);
			queryList.setSelectedItem("Queries");
			queryList.setEditable(false);
		}
		queryList.setVisible(true);
		JPanel lol = new JPanel();
		lol.add(queryList);
		lol.setVisible(true);
		queryPanel.add(lol);
	}

	private int val;
	private String sp1;
	private JLabel resultLabel;

	private void query1(JPanel queryPanel) {

		cq = 1;
		queryPanel.setEnabled(false);
		queryPanel.setVisible(false);
		queryPanel.removeAll();
		queryPanelSet(queryPanel, 1);
		// (JComboBox) ((JPanel) queryPanel.getComponent(0)).getComponent(0);
		// queryPanel.getComponent(0)

		String list[] = { "Name", "Title" };
		JComboBox<String> searchSelect = new JComboBox<String>(list);

		searchSelect.setPreferredSize(new Dimension(150, 40));
		searchSelect.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		searchSelect.setEditable(true);
		searchSelect.setSelectedItem("Search By");
		searchSelect.setEditable(false);
		searchSelect.setVisible(true);

		JPanel lol1 = new JPanel();
		lol1.add(searchSelect);
		lol1.setVisible(true);

		JLabel nameLabel = new JLabel("Name/Title");
		nameLabel.setPreferredSize(new Dimension(150, 30));
		nameLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
		nameLabel.setVisible(true);
		JTextField nameText = new JTextField(20);
		nameText.setPreferredSize(new Dimension(200, 30));
		queryPanel.add(lol1);
		JPanel lol2 = new JPanel();
		lol2.add(nameLabel);
		lol2.add(nameText);
		lol2.setVisible(true);
		queryPanel.add(lol2);

		JLabel yearLabel = new JLabel("Since Year");
		yearLabel.setPreferredSize(new Dimension(150, 30));
		yearLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
		yearLabel.setVisible(true);
		JTextField yearText = new JTextField("YYYY", 4);
		yearText.setDocument(new JTextFieldLimit(4));
		yearText.setText("YYYY");

		yearText.setPreferredSize(new Dimension(200, 30));
		JPanel lol3 = new JPanel();
		lol3.add(yearLabel);
		lol3.add(yearText);
		lol3.setVisible(true);
		queryPanel.add(lol3);

		JLabel customLabel = new JLabel("Custom Range");
		customLabel.setPreferredSize(new Dimension(150, 30));
		customLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
		customLabel.setVisible(true);
		JTextField c1Text = new JTextField("YYYY", 4);
		c1Text.setDocument(new JTextFieldLimit(4));
		c1Text.setText("YYYY");
		c1Text.setPreferredSize(new Dimension(200, 30));
		JTextField c2Text = new JTextField("YYYY", 4);
		c2Text.setPreferredSize(new Dimension(200, 30));
		c2Text.setDocument(new JTextFieldLimit(4));
		c2Text.setText("YYYY");
		JLabel dashLabel = new JLabel("-");
		dashLabel.setPreferredSize(new Dimension(20, 30));
		dashLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
		dashLabel.setVisible(true);
		JPanel lol4 = new JPanel();
		lol4.setLayout(new FlowLayout(FlowLayout.LEADING, 20, 10));
		lol4.add(customLabel);
		lol4.add(c1Text);
		lol4.add(dashLabel);
		lol4.add(c2Text);
		lol4.setVisible(true);
		queryPanel.add(lol4);

		JPanel butPanel1 = new JPanel();
		JPanel butPanel2 = new JPanel();
		butPanel1.setLayout(new FlowLayout(FlowLayout.CENTER));
		butPanel2.setLayout(new FlowLayout(FlowLayout.CENTER));
		JRadioButton o1 = newJRadioButton("Sort By Year");
		JRadioButton o2 = newJRadioButton("Sort By Relevance");
		JRadioButton o3 = newJRadioButton("Display Results from Year");
		JRadioButton o4 = newJRadioButton("Display Results in Custom Range");
		/*
		 * o1.setFont(new Font("Comic Sans MS", Font.PLAIN, 15)); o2.setFont(new
		 * Font("Comic Sans MS", Font.PLAIN, 15)); o3.setFont(new
		 * Font("Comic Sans MS", Font.PLAIN, 15)); o4.setFont(new
		 * Font("Comic Sans MS", Font.PLAIN, 15));
		 */
		o1.setPreferredSize(new Dimension(400, 40));
		o2.setPreferredSize(new Dimension(400, 40));
		o3.setPreferredSize(new Dimension(400, 40));
		o4.setPreferredSize(new Dimension(400, 40));
		o1.setSelected(true);
		ButtonGroup gp = new ButtonGroup();
		gp.add(o1);
		gp.add(o3);
		gp.add(o4);
		gp.add(o2);
		butPanel1.add(o1);
		butPanel1.add(o3);
		butPanel2.add(o4);
		butPanel2.add(o2);

		butPanel1.setVisible(true);
		butPanel2.setVisible(true);
		queryPanel.add(butPanel1);
		queryPanel.add(butPanel2);

		JPanel butPanel3 = new JPanel();
		butPanel3.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		JButton b1 = new JButton("Search");
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// System.out.println("Search was pressed!");
				// boolean formatCorrect = checkFormats(yearText,c1Text,c2Text);
				val = searchSelect.getSelectedIndex();
				sp1 = nameText.getText();
				
				if(sp1.equals(""))
				{
					
				}
//				System.out.println("sp1: " + sp1 + "  val: " + val + " cquery1 " + (c_query1 == null));
//				c_query1.parsing(sp1, val);
//				Sorting_output.sort_by_date(c_query1.ret_searchresult());
				if (o1.isSelected()) {
					System.out.println("sp1: " + sp1 + "  val: " + val + " cquery1 " + (c_query1 == null));
					c_query1.parsing(sp1, val);
					Sorting_output.sort_by_date(c_query1.ret_searchresult());
					counter = 0;
					y1 = 0;
					y2 = Integer.MAX_VALUE;
					getNextEntries(table, 0, sp1, val, y1, y2);
				}
				if (o2.isSelected()) {
					System.out.println("sp1: " + sp1 + "  val: " + val + " cquery1 " + (c_query1 == null));
					c_query1.parsing(sp1, val);
					Sorting_output.sort_by_date(c_query1.ret_searchresult());
					counter = 0;
					y1 = 0;
					y2 = Integer.MAX_VALUE;
					Sorting_output.sort_by_relevance(c_query1.ret_searchresult());
					getNextEntries(table, 0, sp1, val, y1, y2);
				}
				if (o3.isSelected() && checkFormats(yearText)) {
					System.out.println("sp1: " + sp1 + "  val: " + val + " cquery1 " + (c_query1 == null));
					c_query1.parsing(sp1, val);
					Sorting_output.sort_by_date(c_query1.ret_searchresult());
					counter = 0;
					y1 = Integer.parseInt(yearText.getText());
					y2 = Integer.MAX_VALUE;
					getNextEntries(table, 0, sp1, val, y1, y2);

				} else if (o4.isSelected()) {
					System.out.println("sp1: " + sp1 + "  val: " + val + " cquery1 " + (c_query1 == null));
					c_query1.parsing(sp1, val);
					Sorting_output.sort_by_date(c_query1.ret_searchresult());
					counter = 0;
					if (checkFormats(c1Text) && checkFormats(c2Text)) {
						if ((Integer.parseInt(c1Text.getText()) >= Integer.parseInt(c2Text.getText()))) {
							JOptionPane.showMessageDialog(null, "Lower bound should be less than upper bound");
						} else {
							y1 = Integer.parseInt(c1Text.getText());
							y2 = Integer.parseInt(c2Text.getText());
							getNextEntries(table, 0, sp1, val, y1, y2);
						}
					}
					// JOptionPane.showMessageDialog(null,"Sorting by
					// customRange");
				}

				// DefaultTableModel model = (DefaultTableModel)
				// results.getModel();
				// model.setRowCount(0);
				// DefaultTableCellRenderer rightRenderer = new
				// DefaultTableCellRenderer();
				// rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
				// table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
				// for (int i = 0; i < 20; i++) {
				// String a = Integer.toString(i + 1);
				// model.addRow(new Object[] { a, "fdsafdsa", "fdsafdsa",
				// "fdsa", "fdsa", "fdsa", "fdsa", "htrhgr" });
				// }
//				if (true) {
//					// JOptionPane.showMessageDialog(null,"String is correct");
//					// table = new JTable(model);
//				}
			}
		});
		JButton b2 = new JButton("Reset");
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				query1(queryPanel);
				DefaultTableModel model = (DefaultTableModel) results.getModel();
				model.setRowCount(0);
				next_status=0;
				// model.setRowCount(20);
				resultLabel.setText("Result Count: ");
				globNext.setEnabled(true);
				
			}
		});
		b2.setBackground(Color.MAGENTA);
		b1.setPreferredSize(new Dimension(100, 30));
		b2.setPreferredSize(new Dimension(100, 30));
		b1.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		b2.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		b1.setVisible(true);
		b2.setVisible(true);
		butPanel3.add(b1);
		butPanel3.add(b2);
		butPanel3.setVisible(true);
		queryPanel.add(butPanel3);

		queryPanel.revalidate();
		queryPanel.setVisible(true);
		queryPanel.setEnabled(true);

	}

	private boolean checkFormats(JTextField a) {
		String s1 = a.getText();
		// String s2 = b.getText();
		// String s3 = c.getText();
		// System.out.println("s1: "+s1);
		// System.out.println("s2: "+s2);
		// System.out.println("s3: "+s3);2
		boolean flag = true;
		if (!s1.matches(".*[0-9].*")) {
			JOptionPane.showMessageDialog(null, "Invalid Entry! Only numbers! Try again");
//			throw new CustomEx("Invalid Entry! Only numbers! Try again");
			flag = false;
		} else if (s1.length() != 4) {
			JOptionPane.showMessageDialog(null, "Invalid Entry! Exactly 4 Digits please");
			flag = false;
		}
		return flag;
	}

	public class JTextFieldLimit extends PlainDocument {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private int limit;

		JTextFieldLimit(int limit) {
			super();
			this.limit = limit;
		}

		public void insertString(int offset, String str, AttributeSet attr) throws BadLocationException {
			if (str == null)
				return;

			if ((getLength() + str.length()) <= limit) {
				super.insertString(offset, str, attr);
			}
		}
	}

	private int getNextEntries2(JTable table, int index, int n) {
		DefaultTableModel model = (DefaultTableModel) results.getModel();
		model.setRowCount(0);
		model.setColumnIdentifiers(new Object[] { "S. No", "Author Name" });
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		Author temp;
		for (int i = 0; i < 20; i++) {
			if (counter >= Author.ret_total_no_of_distinct_authors()) {
				resultLabel.setText("Result Count: "+ans);
				globNext.setEnabled(false);
				counter = 0;
				System.out.println("inside 2, ans = : "+ans);
//				model.setRowCount(0);
				return 1;
			}
			temp = Author.ret_person(counter);
			while (temp.ret_no_of_publications() <= n && counter < Author.ret_total_no_of_distinct_authors()) {

				temp = Author.ret_person(counter);
				counter++;			
			}
			if(counter >= Author.ret_total_no_of_distinct_authors())
			{
//				ans= counter;
				resultLabel.setText("Result Count: "+ans);
				globNext.setEnabled(false);
				counter = 0;
//				model.setRowCount(0);
				System.out.println("inside 3, ans = : "+ans);
				return 1;
			}
			if (temp.ret_no_of_publications() > n) {
				model.addRow(new Object[] { Integer.toString((20 * index + i+1)), temp.ret_name() });
				counter++;
				ans = 20 * index + i+1;
				if(counter >= Author.ret_total_no_of_distinct_authors())
				{
					resultLabel.setText("Result Count: "+ans);
				}
			}
		}
		if(counter >= Author.ret_total_no_of_distinct_authors())
		{
//			ans= counter;
			resultLabel.setText("Result Count: "+ans);
			globNext.setEnabled(false);
			counter = 0;
			System.out.println("inside 1, ans = : "+ans);
//			model.setRowCount(0);
			return 1;
		}
		return 0;
	}

	private void query2(JPanel queryPanel) {

		cq = 2;
		queryPanel.setVisible(false);
		queryPanel.removeAll();
		queryPanelSet(queryPanel, 2);

		JLabel numPanel = new JLabel("Number of Publications");
		numPanel.setPreferredSize(new Dimension(300, 30));
		numPanel.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
		numPanel.setVisible(true);
		JTextField numText = new JTextField(4);
		numText.setEditable(true);
		numText.setPreferredSize(new Dimension(200, 30));
		JPanel lol2 = new JPanel();
		lol2.add(numPanel);
		lol2.add(numText);
		lol2.setVisible(true);
		queryPanel.add(lol2);

		JPanel butPanel2 = new JPanel();
		butPanel2.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
		JButton b1 = new JButton("Search");
		JButton b2 = new JButton("Reset");
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// System.out.println("Search was pressed!");
				// checkFormats();
				int n = Integer.parseInt(numText.getText());
				// Query2.execute();
				next_status=0;
				counter = 0;
				Query2.execute();
				k = n;
				getNextEntries2(table, 0, n);
				if(next_status ==1)
				{
					globNext.setEnabled(false);
				}
			}
		});
		b2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				query2(queryPanel);
				DefaultTableModel model = (DefaultTableModel) results.getModel();
				model.setRowCount(0);
//				resultPanelSet(resultPanel);
				globNext.setEnabled(true);
				resultLabel.setText("Result Count: ");
			}
		});
		b2.setBackground(Color.MAGENTA);
		b1.setPreferredSize(new Dimension(100, 30));
		b2.setPreferredSize(new Dimension(100, 30));
		b1.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
		b2.setFont(new Font("Comic Sans MS", Font.BOLD, 15));
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

		queryPanelSet(queryPanel, 3);

		queryPanel.revalidate();
		queryPanel.setVisible(true);
		queryPanel.setEnabled(true);
	}

	public static void main(String[] args) {
		new GUI();
	}
}