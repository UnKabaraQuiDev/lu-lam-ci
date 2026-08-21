package lu.kbra.flow_market_corr.db.data;

import java.sql.Timestamp;

import lu.kbra.flow_market_corr.db.TransactionType;
import lu.kbra.flow_market_corr.db.table.CashierTable;
import lu.pcy113.pclib.db.autobuild.column.AutoIncrement;
import lu.pcy113.pclib.db.autobuild.column.Column;
import lu.pcy113.pclib.db.autobuild.column.DefaultValue;
import lu.pcy113.pclib.db.autobuild.column.ForeignKey;
import lu.pcy113.pclib.db.autobuild.column.Nullable;
import lu.pcy113.pclib.db.autobuild.column.PrimaryKey;
import lu.pcy113.pclib.db.impl.DataBaseEntry;

public class BillData implements DataBaseEntry {

	@Column
	@PrimaryKey
	@AutoIncrement
	protected long id;
	@Column(length = 20)
	@Nullable
	protected TransactionType payementMethod;
	@Column
	@ForeignKey(table = CashierTable.class)
	protected long cashierId;
	@Column
//	@DefaultValue("CURRENT_TIMESTAMP")
	@Nullable
	protected Timestamp timestamp;

	public BillData() {
	}	

	public BillData(long cashierId) {
		this.cashierId = cashierId;
	}

	public BillData(TransactionType payementMethod, long cashierId, Timestamp timestamp) {
		this.payementMethod = payementMethod;
		this.cashierId = cashierId;
		this.timestamp = timestamp;
	}

	public BillData(long id, TransactionType payementMethod, long cashierId, Timestamp timestamp) {
		this.id = id;
		this.payementMethod = payementMethod;
		this.cashierId = cashierId;
		this.timestamp = timestamp;
	}

	public TransactionType getPayementMethod() {
		return payementMethod;
	}

	public void setPayementMethod(TransactionType payementMethod) {
		this.payementMethod = payementMethod;
	}

	public long getCashierId() {
		return cashierId;
	}

	public void setCashierId(long cashierId) {
		this.cashierId = cashierId;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "BillData [id=" + id + ", payementMethod=" + payementMethod + ", cashierId=" + cashierId + ", timestamp=" + timestamp + "]";
	}

}
