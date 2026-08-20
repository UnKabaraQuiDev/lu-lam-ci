package lu.kbra.flow_market.db.table;
import lu.pcy113.pclib.db.DataBase;
import lu.pcy113.pclib.db.DataBaseTable;

import lu.kbra.flow_market.db.data.TransactionData;

public class TransactionTable extends DataBaseTable<TransactionData> {

	public TransactionTable(DataBase dataBase) {
		super(dataBase);
	}

}
