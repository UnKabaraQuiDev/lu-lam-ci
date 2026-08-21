package lu.kbra.flow_market.db.table;

import lu.pcy113.pclib.db.DataBase;
import lu.pcy113.pclib.db.DataBaseTable;

import lu.kbra.flow_market.db.data.TransactionProductData;

public class TransactionProductTable extends DataBaseTable<TransactionProductData> {

	public TransactionProductTable(DataBase dataBase) {
		super(dataBase);
	}

}
