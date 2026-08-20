package lu.kbra.flow_market.db.data;

import lu.pcy113.pclib.db.autobuild.column.Check;
import lu.pcy113.pclib.db.autobuild.column.Column;
import lu.pcy113.pclib.db.autobuild.column.PrimaryKey;
import lu.pcy113.pclib.db.autobuild.column.Unique;
import lu.pcy113.pclib.db.impl.DataBaseEntry;

public class VendorData implements DataBaseEntry {

	@Column(length = 6)
	@PrimaryKey
	protected String vendorCode;
	@Column
	@Check("%NAME% BETWEEN 0 AND 1")
	protected float percentage;
	@Column(length = 120)
	@Unique
	protected String account;
	@Column(length = 35)
	@Unique(1)
	protected String name;

	public VendorData() {
	}

	public VendorData(String vendorCode, float percentage, String account, String name) {
		this.vendorCode = vendorCode;
		this.percentage = percentage;
		this.account = account;
		this.name = name;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "VendorData [vendorCode=" + vendorCode + ", percentage=" + percentage + ", account=" + account + "]";
	}

}
