package lu.kbra.flow_market.db.data;

import lu.pcy113.pclib.db.autobuild.column.Check;
import lu.pcy113.pclib.db.autobuild.column.Column;
import lu.pcy113.pclib.db.autobuild.column.ForeignKey;
import lu.pcy113.pclib.db.autobuild.column.PrimaryKey;
import lu.pcy113.pclib.db.impl.DataBaseEntry;

import lu.kbra.flow_market.db.table.TransactionTable;
import lu.kbra.flow_market.db.table.VendorTable;

public class TransactionSupplementData implements DataBaseEntry {

	@Column
	@PrimaryKey
	@ForeignKey(table = TransactionTable.class)
	protected long transactionId;
	@Column(length = 6)
	@PrimaryKey
	@ForeignKey(table = VendorTable.class)
	protected String vendorCode;

	@Column
	@Check("%NAME% > 0")
	protected float price;
	
}
