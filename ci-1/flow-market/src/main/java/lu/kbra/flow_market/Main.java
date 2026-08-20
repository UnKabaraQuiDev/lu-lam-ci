package lu.kbra.flow_market;

import lu.kbra.flow_market.db.data.CashierData;
import lu.kbra.flow_market.db.data.ProductData;
import lu.kbra.flow_market.db.data.VendorData;
import lu.kbra.flow_market.db.table.CashierTable;
import lu.kbra.flow_market.db.table.ProductTable;
import lu.kbra.flow_market.db.table.TransactionProductTable;
import lu.kbra.flow_market.db.table.TransactionSupplementTable;
import lu.kbra.flow_market.db.table.TransactionTable;
import lu.kbra.flow_market.db.table.VendorTable;
import lu.kbra.flow_market.db.view.VendorProfitView;
import lu.pcy113.pclib.db.DataBase;
import lu.pcy113.pclib.db.DataBaseConnector;

public class Main {

	public static final void main(String[] args) {
		final DataBaseConnector conn = new DataBaseConnector("user", "pass", "localhost", 3306);

		final DataBase db = new DataBase(conn, "scipr_flow_market");
		db.create().run();

		final CashierTable cashiers = new CashierTable(db);
		cashiers.create().run();
		final VendorTable vendors = new VendorTable(db);
		vendors.create().run();
		final ProductTable products = new ProductTable(db);
		products.create().run();
		final TransactionTable transactions = new TransactionTable(db);
		transactions.create().run();
		final TransactionProductTable transactionProducts = new TransactionProductTable(db);
		transactionProducts.create().run();
		final TransactionSupplementTable transactionSupplements = new TransactionSupplementTable(db);
		transactionSupplements.create().run();

		final VendorProfitView vendorProfit = new VendorProfitView(db);
//		System.out.println(vendorProfit.getCreateSQL());
//		vendorProfit.exists().thenCompose(f -> f ? vendorProfit.drop() : NextTask.empty()).run();
		vendorProfit.create().run();
//		final CashierProfitView cashierProfit = new CashierProfitView(db);
//		cashierProfit.create().run();

		final CashierData cashier1 = cashiers.loadIfExistsElseInsert(new CashierData("user", "pass")).run();
		final CashierData cashier2 = cashiers.loadIfExistsElseInsert(new CashierData("user1", "pass1")).run();

		final VendorData vendor1 = vendors.loadPKIfExistsElseInsert(new VendorData("vend11", 0.8f, "aaaa-bbbb-cccc-...", "name1")).run();
		final VendorData vendor2 = vendors.loadPKIfExistsElseInsert(new VendorData("vend22", 0.68f, "dddd-bbbb-cccc-...", "name2")).run();

		final ProductData prod1 = products
				.loadPKIfExistsElseInsert(new ProductData("prod1111", "Name Prod 1", vendor1.getVendorCode(), 100))
				.run();
		final ProductData prod2 = products
				.loadPKIfExistsElseInsert(new ProductData("prod1112", "Name Prod 2", vendor1.getVendorCode(), 50))
				.run();
		final ProductData prod3 = products
				.loadPKIfExistsElseInsert(new ProductData("prod1113", "Name Prod 3", vendor2.getVendorCode(), 10))
				.run();
		final ProductData prod4 = products
				.loadPKIfExistsElseInsert(new ProductData("prod1114", "Name Prod 4", vendor2.getVendorCode(), 1000))
				.run();

		vendorProfit.all().forEachRemaining(System.out::println);
	}

}
