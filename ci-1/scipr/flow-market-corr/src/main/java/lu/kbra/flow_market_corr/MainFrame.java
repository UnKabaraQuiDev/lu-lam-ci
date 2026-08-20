package lu.kbra.flow_market_corr;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import lu.kbra.flow_market_corr.db.TransactionType;
import lu.kbra.flow_market_corr.db.data.BillData;
import lu.kbra.flow_market_corr.db.data.CashierData;
import lu.kbra.flow_market_corr.db.data.ItemData;
import lu.kbra.flow_market_corr.db.data.VendorData;
import lu.kbra.flow_market_corr.db.table.BillTable;
import lu.kbra.flow_market_corr.db.table.CashierTable;
import lu.kbra.flow_market_corr.db.table.ItemTable;
import lu.kbra.flow_market_corr.db.table.VendorTable;
import lu.pcy113.pclib.db.DataBase;
import lu.pcy113.pclib.db.DataBaseConnector;
import lu.pcy113.pclib.db.query.QueryBuilder;

public class MainFrame extends JFrame {

	protected DataBase database;
	protected VendorTable vendors;
	protected BillTable bills;
	protected ItemTable items;
	protected CashierTable cashiers;

	protected CashierData currentCashier;
	protected BillData currentTransaction;

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField vendorCode;

	public MainFrame() throws UnknownHostException {
		database = new DataBase(new DataBaseConnector("user", "pass", "localhost", 3306), "scipr_flow_market_corr");
		database.create().run();

		vendors = new VendorTable(database);
		bills = new BillTable(database);
		items = new ItemTable(database);
		cashiers = new CashierTable(database);

		vendors.create().run();
		cashiers.create().run();
		bills.create().run();
		items.create().run();

		currentCashier = cashiers.loadIfExistsElseInsert(new CashierData(InetAddress.getLocalHost().getHostAddress())).run();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 412);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);

		JPopupMenu popup = new JPopupMenu();

		vendorCode = new JTextField();
		vendorCode.setColumns(10);
		vendorCode.getDocument().addDocumentListener(new DocumentListener() {
			public void insertUpdate(DocumentEvent e) {
				showSuggestions();
			}

			public void removeUpdate(DocumentEvent e) {
				showSuggestions();
			}

			public void changedUpdate(DocumentEvent e) {
			}

			private void showSuggestions() {
				popup.setVisible(false);
				popup.removeAll();

				String input = vendorCode.getText().toLowerCase();
				if (input.isEmpty())
					return;

				for (VendorData word : vendors
						.query(QueryBuilder
								.<VendorData>select()
								.where(cb -> cb
										.match("vendor_code", "LIKE", input + "%")
										.or(cc -> cc.match("name", "LIKE", "%" + input + "%")))
								.list())
						.run()) {
					JMenuItem item = new JMenuItem(word.getName() + " (" + word.getVendorCode() + ")");
					item.addActionListener(e -> {
						vendorCode.setText(word.getVendorCode());
						popup.setVisible(false);
					});
					popup.add(item);
				}

				if (popup.getComponentCount() > 0) {
					popup.show(vendorCode, 0, vendorCode.getHeight());
				}
			}
		});

		JLabel lblNewLabel = new JLabel("Vendor Code");

		JLabel lblPrice = new JLabel("Price");

		JSpinner price = new JSpinner();
		price.setModel(new SpinnerNumberModel(0f, 0, 1000000, 0.01));

		JList list = new JList();

		JLabel lblTotal = new JLabel("total");

		JButton btnSubmitAdd = new JButton("Submit & Add");
		btnSubmitAdd.addActionListener(a -> {
			if (currentTransaction == null) {
				currentTransaction = bills.insertAndReload(new BillData(currentCashier.getId())).run();
			}

			items
					.loadPKIfExists(
							new ItemData(currentTransaction.getId(), vendorCode.getText(), ((Double) price.getValue()).floatValue()))
					.toOptional()
					.run()
					.ifPresentOrElse(e -> {
						e.setPrice(e.getPrice() + (float) price.getValue());
						items.updateAndReload(e).run();
					}, () -> {
						items
								.insertAndReload(new ItemData(currentTransaction.getId(), vendorCode.getText(),
										((Double) price.getValue()).floatValue()))
								.run();
					});

			vendorCode.setText("");
			price.setValue(0);

			final List<ItemData> ll = items
					.query(QueryBuilder.<ItemData>select().where(cb -> cb.match("bill_id", "=", currentTransaction.getId())).list())
					.run();
			list.setListData(ll.stream().map(c -> c.getVendorCode() + " | " + c.getPrice()).toArray());
			lblTotal.setText(Double.toString(ll.stream().mapToDouble(c -> c.getPrice()).sum()) + "€");
		});

		JComboBox<String> comboBox = new JComboBox<>(
				Arrays.stream(TransactionType.values()).map(c -> c.name().toLowerCase()).toArray(String[]::new));

		JButton btnSubmitSave = new JButton("Pay");
		btnSubmitSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (currentTransaction == null) {
					System.err.println("No open transaction.");
					return;
				}

				currentTransaction.setPayementMethod(TransactionType.valueOf(((String) comboBox.getSelectedItem()).toUpperCase()));
				if (!vendorCode.getText().isBlank()) {
					items
							.insertAndReload(new ItemData(currentTransaction.getId(), vendorCode.getText(),
									((Double) price.getValue()).floatValue()))
							.thenConsume(e -> {
								vendorCode.setText("");
								price.setValue(0);
							})
							.run();
				}

				currentTransaction.setTimestamp(Timestamp.from(Instant.now()));
				bills.updateAndReload(currentTransaction).thenConsume(c -> {
					currentTransaction = null;
					list.setListData(new Object[0]);
				}).run();
				lblTotal.setText("");
			}
		});

		JButton btnNewBill = new JButton("New bill");
		btnNewBill.addActionListener(a -> {
			if (currentTransaction == null) {
				currentTransaction = bills.insertAndReload(new BillData(currentCashier.getId())).run();
			} else {
				System.err.println("close current transaction first");
			}
		});

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel
				.setHorizontalGroup(gl_panel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel
								.createSequentialGroup()
								.addContainerGap(88, Short.MAX_VALUE)
								.addGroup(gl_panel
										.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_panel.createSequentialGroup().addGap(12).addComponent(btnNewBill))
										.addGroup(gl_panel
												.createParallelGroup(Alignment.TRAILING)
												.addGroup(gl_panel
														.createSequentialGroup()
														.addComponent(btnSubmitAdd)
														.addGap(56)
														.addComponent(comboBox, GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_panel
														.createSequentialGroup()
														.addGroup(gl_panel
																.createParallelGroup(Alignment.LEADING)
																.addComponent(lblNewLabel)
																.addComponent(lblPrice))
														.addGap(51)
														.addGroup(gl_panel
																.createParallelGroup(Alignment.TRAILING, false)
																.addComponent(price,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addComponent(vendorCode)))
												.addComponent(btnSubmitSave)))
								.addGap(97))
						.addGroup(gl_panel
								.createSequentialGroup()
								.addGap(93)
								.addComponent(list, GroupLayout.PREFERRED_SIZE, 271, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(76, Short.MAX_VALUE))
						.addGroup(Alignment.TRAILING,
								gl_panel.createSequentialGroup().addContainerGap(199, Short.MAX_VALUE).addComponent(lblTotal).addGap(181)));
		gl_panel
				.setVerticalGroup(gl_panel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel
								.createSequentialGroup()
								.addGap(39)
								.addGroup(gl_panel
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(vendorCode,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblNewLabel))
								.addGap(18)
								.addGroup(gl_panel
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(lblPrice)
										.addComponent(price,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addGap(18)
								.addGroup(gl_panel
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnSubmitAdd)
										.addComponent(comboBox,
												GroupLayout.PREFERRED_SIZE,
												GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(gl_panel
										.createParallelGroup(Alignment.BASELINE)
										.addComponent(btnNewBill)
										.addComponent(btnSubmitSave))
								.addGap(15)
								.addComponent(lblTotal)
								.addPreferredGap(ComponentPlacement.RELATED)
								.addComponent(list, GroupLayout.PREFERRED_SIZE, 142, GroupLayout.PREFERRED_SIZE)
								.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);

	}

	public static void main(String[] args) {
		EventQueue.invokeLater(() -> {
			try {
				MainFrame frame = new MainFrame();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}
