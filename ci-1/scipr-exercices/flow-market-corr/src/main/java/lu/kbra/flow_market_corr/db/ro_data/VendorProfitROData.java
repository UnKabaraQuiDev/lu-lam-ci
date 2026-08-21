package lu.kbra.flow_market_corr.db.ro_data;

import lu.pcy113.pclib.db.autobuild.column.Column;
import lu.pcy113.pclib.db.autobuild.column.PrimaryKey;
import lu.pcy113.pclib.db.impl.DataBaseEntry.ReadOnlyDataBaseEntry;

public class VendorProfitROData implements ReadOnlyDataBaseEntry {

	@Column
	@PrimaryKey
	protected String vendorCode;

	@Column
	protected float totalMoney;
	@Column(name = "vendor_money")
	protected float vendorMoney;
	@Column(name = "orga_money")
	protected float orgaMoney;

	@Override
	public String toString() {
		return "VendorProfitROData [vendorCode=" + vendorCode + ", totalMoney=" + totalMoney + ", vendorMoney=" + vendorMoney
				+ ", orgaMoney=" + orgaMoney + "]";
	}

}
