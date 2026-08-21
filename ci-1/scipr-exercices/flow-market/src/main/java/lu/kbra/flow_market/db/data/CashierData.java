package lu.kbra.flow_market.db.data;

import lu.pcy113.pclib.PCUtils;
import lu.pcy113.pclib.db.autobuild.column.AutoIncrement;
import lu.pcy113.pclib.db.autobuild.column.Column;
import lu.pcy113.pclib.db.autobuild.column.PrimaryKey;
import lu.pcy113.pclib.db.autobuild.column.Unique;
import lu.pcy113.pclib.db.impl.DataBaseEntry;

public class CashierData implements DataBaseEntry {

	@Column
	@PrimaryKey
	@AutoIncrement
	protected long id;
	@Column(length = 64)
	@Unique
	protected String username;
	@Column(length = PCUtils.SHA_256_CHAR_LENGTH)
	protected String password;

	public CashierData() {
	}

	public CashierData(String username, String password) {
		this.username = username;
		this.password = password;
	}

}
