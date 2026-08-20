package lu.kbra.flow_market.db.data;

import lu.pcy113.pclib.db.autobuild.column.Check;
import lu.pcy113.pclib.db.autobuild.column.Column;
import lu.pcy113.pclib.db.autobuild.column.ForeignKey;
import lu.pcy113.pclib.db.autobuild.column.PrimaryKey;
import lu.pcy113.pclib.db.impl.DataBaseEntry;

import lu.kbra.flow_market.db.table.ProductTable;
import lu.kbra.flow_market.db.table.TransactionTable;

public class TransactionProductData implements DataBaseEntry {

	@Column
	@PrimaryKey
	@ForeignKey(table = TransactionTable.class)
	protected long transactionId;
	@Column(length = 8)
	@PrimaryKey
	@ForeignKey(table = ProductTable.class)
	protected String productCode;
	@Column
	@Check("%NAME% > 0")
	protected int count;

}
