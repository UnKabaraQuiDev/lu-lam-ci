package lu.kbra.flow_market_corr.db.table;
import lu.kbra.flow_market_corr.db.data.BillData;
import lu.pcy113.pclib.db.DataBase;
import lu.pcy113.pclib.db.DataBaseTable;

public class BillTable extends DataBaseTable<BillData> {

	public BillTable(DataBase dataBase) {
		super(dataBase);
	}

}
