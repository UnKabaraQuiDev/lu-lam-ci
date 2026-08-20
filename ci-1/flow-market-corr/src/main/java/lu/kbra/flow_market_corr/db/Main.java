package lu.kbra.flow_market_corr.db;

import lu.kbra.flow_market_corr.db.data.CashierData;
import lu.kbra.flow_market_corr.db.data.VendorData;
import lu.kbra.flow_market_corr.db.table.CashierTable;
import lu.kbra.flow_market_corr.db.table.ItemTable;
import lu.kbra.flow_market_corr.db.table.BillTable;
import lu.kbra.flow_market_corr.db.table.VendorTable;
import lu.pcy113.pclib.db.DataBase;
import lu.pcy113.pclib.db.DataBaseConnector;

public class Main {

	public static final void main(String[] args) {
		final DataBaseConnector conn = new DataBaseConnector("user", "pass", "localhost", 3306);

		final DataBase db = new DataBase(conn, "scipr_flow_market_corr");
		db.create().run();

		final CashierTable cashiers = new CashierTable(db);
		cashiers.create().run();
		final VendorTable vendors = new VendorTable(db);
		vendors.create().run();
		final BillTable transactions = new BillTable(db);
		transactions.create().run();
		final ItemTable transactionSupplements = new ItemTable(db);
		transactionSupplements.create().run();

		final CashierData cashier1 = cashiers.loadIfExistsElseInsert(new CashierData("192...")).run();
		final CashierData cashier2 = cashiers.loadIfExistsElseInsert(new CashierData("199...")).run();

		final VendorData vendor1 = vendors.loadPKIfExistsElseInsert(new VendorData("vend11", 0.8f, "aaaa-bbbb-cccc-...", "name1")).run();
		final VendorData vendor2 = vendors.loadPKIfExistsElseInsert(new VendorData("vend22", 0.68f, "dddd-bbbb-cccc-...", "name2")).run();
	}

}
