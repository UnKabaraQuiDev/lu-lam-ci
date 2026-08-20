package lu.kbra.flow_market.db.data;
import java.sql.Timestamp;

import lu.pcy113.pclib.db.autobuild.column.AutoIncrement;
import lu.pcy113.pclib.db.autobuild.column.Column;
import lu.pcy113.pclib.db.autobuild.column.DefaultValue;
import lu.pcy113.pclib.db.autobuild.column.ForeignKey;
import lu.pcy113.pclib.db.autobuild.column.Nullable;
import lu.pcy113.pclib.db.autobuild.column.PrimaryKey;
import lu.pcy113.pclib.db.impl.DataBaseEntry;

import lu.kbra.flow_market.db.TransactionType;
import lu.kbra.flow_market.db.table.CashierTable;

public class TransactionData implements DataBaseEntry {

	@Column
	@PrimaryKey
	@AutoIncrement
	protected long id;
	@Column(length = 20)
	protected TransactionType type;
	@Column(length = 20)
	@Nullable
	protected String transactionTrace;
	@Column
	@ForeignKey(table = CashierTable.class)
	protected long cashierId;
	@Column
	@DefaultValue("CURRENT_TIMESTAMP")
	protected Timestamp timestamp;

}
