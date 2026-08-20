package lu.kbra.flow_market.view;

import java.util.List;

import javax.swing.JFrame;

import lu.kbra.flow_market.db.data.ProductData;
import lu.kbra.flow_market.db.data.TransactionData;
import lu.kbra.flow_market.db.data.VendorData;

public abstract class CashierMainFrame extends JFrame {

	protected DataBaseManager dbManager;

	protected TransactionData currentTransaction;
	protected List<ProductData> products;
	protected List<SupplementData> supplements;

	public abstract void startNewTransaction();

	public abstract void flushTransaction();

	public abstract void loadTransaction(long historyIndex);

	public abstract void deleteTransaction(long historyIndex);
	
	public abstract void loadTransactionHistory();

	// products
	public abstract void addProductEntry(String productCode, int count);

	public abstract void deleteProductEntry(String productCode);

	public abstract void updateProductEntry(String productCode, int count);

	public abstract List<ProductData> searchProduct(String name);

	// supplements
	public abstract void addSupplement(String vendorCode, float price);

	public abstract void deleteSupplement(String vendorCode);

	public abstract void updateSupplement(String vendorCode, float price);

	// vendor
	public abstract List<VendorData> searchVendors(String name);

}
