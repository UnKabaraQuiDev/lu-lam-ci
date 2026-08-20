package lu.kbra.flow_market_corr.db.table;
import lu.kbra.flow_market_corr.db.data.VendorData;
import lu.pcy113.pclib.db.DataBase;
import lu.pcy113.pclib.db.DataBaseTable;

public class VendorTable extends DataBaseTable<VendorData> {

	public VendorTable(DataBase dataBase) {
		super(dataBase);
	}

}
