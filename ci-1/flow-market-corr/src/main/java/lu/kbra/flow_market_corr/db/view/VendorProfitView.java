package lu.kbra.flow_market_corr.db.view;

import lu.pcy113.pclib.db.DataBase;
import lu.pcy113.pclib.db.DataBaseView;
import lu.pcy113.pclib.db.annotations.view.DB_View;
import lu.pcy113.pclib.db.annotations.view.ViewColumn;
import lu.pcy113.pclib.db.annotations.view.ViewTable;
import lu.pcy113.pclib.db.annotations.view.ViewTable.Type;
import lu.pcy113.pclib.db.loader.BufferedPagedEnumeration;
import lu.kbra.flow_market_corr.db.ro_data.VendorProfitROData;
import lu.kbra.flow_market_corr.db.table.ProductTable;
import lu.kbra.flow_market_corr.db.table.TransactionProductTable;
import lu.kbra.flow_market_corr.db.table.BillTable;
import lu.kbra.flow_market_corr.db.table.ItemTable;
import lu.kbra.flow_market_corr.db.table.VendorTable;

@DB_View(
		name = "vendor_profit",
		tables = {
//				@ViewTable(typeName = TransactionTable.class),
				@ViewTable(
						typeName = BillTable.class//,
//						join = Type.LEFT,
//						on = "transaction_product.transaction_id = transaction.id"
				),
				@ViewTable(
						typeName = ItemTable.class,
						join = Type.LEFT,
						on = "item.bill_id = transaction_product.product_code",
						columns = {
								@ViewColumn(func = "SUM(COALESCE(product.price * transaction_product.count, 0)) + SUM(COALESCE(transaction_supplement.price, 0))", asName = "total_money"),
								@ViewColumn(
										func = "(SUM(COALESCE(product.price * transaction_product.count, 0)) + SUM(COALESCE(transaction_supplement.price, 0))) * percentage",
										asName = "vendor_money"
								),
								@ViewColumn(
										func = "(SUM(COALESCE(product.price * transaction_product.count, 0)) + SUM(COALESCE(transaction_supplement.price, 0))) * (1-percentage)",
										asName = "orga_money"
								) }
				),
				@ViewTable(
						typeName = VendorTable.class,
						join = Type.LEFT,
						on = "vendor.vendor_code = product.vendor_code",
						columns = { @ViewColumn(name = "vendor_code") }
				),
				@ViewTable(
						typeName = ItemTable.class,
						join = Type.LEFT,
						on = "transaction_supplement.vendor_code = product.vendor_code"
				) },
		groupBy = { "vendor_code" }
)
public class VendorProfitView extends DataBaseView<VendorProfitROData> {

	public VendorProfitView(DataBase dataBase) {
		super(dataBase);
	}

	public BufferedPagedEnumeration<VendorProfitROData> all() {
		return new BufferedPagedEnumeration<>(25, this);
	}

}
