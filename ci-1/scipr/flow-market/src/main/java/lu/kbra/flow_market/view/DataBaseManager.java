package lu.kbra.flow_market.view;

import java.util.List;
import java.util.Optional;

import lu.kbra.flow_market.db.data.CashierData;
import lu.kbra.flow_market.db.data.ProductData;
import lu.kbra.flow_market.db.data.TransactionData;
import lu.kbra.flow_market.db.data.TransactionProductData;
import lu.kbra.flow_market.db.data.TransactionROData;
import lu.kbra.flow_market.db.data.VendorData;
import lu.kbra.flow_market.db.table.CashierTable;
import lu.kbra.flow_market.db.table.ProductTable;
import lu.kbra.flow_market.db.table.TransactionProductTable;
import lu.kbra.flow_market.db.table.TransactionSupplementTable;
import lu.kbra.flow_market.db.table.VendorTable;
import lu.kbra.flow_market.db.view.VendorProfitView;
import lu.pcy113.pclib.datastructure.pair.Pair;
import lu.pcy113.pclib.db.DataBase;
import lu.pcy113.pclib.db.DataBaseConnector;

public abstract class DataBaseManager {

	protected DataBase db;

	protected CashierTable cashiers;
	protected ProductTable products;
	protected VendorTable vendors;
	protected TransactionProductTable transactionProducts;
	protected TransactionSupplementTable transactionSupplements;

	protected VendorProfitView vendorProfit;

	protected CashierData cashier;
	
	public DataBaseManager(DataBaseConnector connector) {
		this.db = new DataBase(connector, "scipr_flow_market");
	}

	// cashier
	public abstract Optional<CashierData> login(String username, String password);

	public abstract void logout();

	// transactions
	public abstract TransactionROData createTransaction(List<ProductData> products, List<SupplementData> supplements);

	public abstract List<TransactionROData> getTransactionHistory();

	public abstract Pair<List<TransactionProductData>, List<TransactionSupplementTable>> getTransactionDetails();

	public abstract TransactionROData updateTransaction(TransactionData t, List<ProductData> products, List<SupplementData> supplements);

	public abstract void deleteTransaction(TransactionData d);

	// products
	public abstract ProductData createProduct(String vendorCode, String productCode, String name, float price);

	public abstract ProductData updateProduct(String productCode, String name, float price);

	public abstract void deleteProduct(String productCode);

	// vendors
	public abstract VendorData createVendor(String vendorCode, float percentage);

	public abstract VendorData updateVendor(String vendorCode, float percentage);

	public abstract void deleteVendor(String vendorCode);

}
