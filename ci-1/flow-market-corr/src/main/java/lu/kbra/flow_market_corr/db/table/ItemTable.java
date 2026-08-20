package lu.kbra.flow_market_corr.db.table;

import lu.kbra.flow_market_corr.db.data.ItemData;
import lu.pcy113.pclib.db.DataBase;
import lu.pcy113.pclib.db.DataBaseTable;

public class ItemTable extends DataBaseTable<ItemData> {

	public ItemTable(DataBase dataBase) {
		super(dataBase);
	}

}
