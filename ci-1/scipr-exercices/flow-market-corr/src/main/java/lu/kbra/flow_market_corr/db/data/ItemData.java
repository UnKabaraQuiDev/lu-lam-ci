package lu.kbra.flow_market_corr.db.data;

import java.sql.Timestamp;

import lu.kbra.flow_market_corr.db.table.BillTable;
import lu.kbra.flow_market_corr.db.table.VendorTable;
import lu.pcy113.pclib.db.autobuild.column.Check;
import lu.pcy113.pclib.db.autobuild.column.Column;
import lu.pcy113.pclib.db.autobuild.column.DefaultValue;
import lu.pcy113.pclib.db.autobuild.column.ForeignKey;
import lu.pcy113.pclib.db.autobuild.column.PrimaryKey;
import lu.pcy113.pclib.db.impl.DataBaseEntry;

public class ItemData implements DataBaseEntry {

	@Column
	@PrimaryKey
	@ForeignKey(table = BillTable.class)
	protected long billId;
	@Column(length = 6)
	@PrimaryKey
	@ForeignKey(table = VendorTable.class)
	protected String vendorCode;
	@Column
	@Check("%NAME% > 0")
	protected float price;
	@Column
	@DefaultValue("CURRENT_TIMESTAMP")
	protected Timestamp timestamp;

	public ItemData() {
	}

	public ItemData(long billId, String vendorCode, float price) {
		this.billId = billId;
		this.vendorCode = vendorCode;
		this.price = price;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Timestamp getTimestamps() {
		return timestamp;
	}

	public void setTimestamps(Timestamp timestamps) {
		this.timestamp = timestamps;
	}

	public long getBillId() {
		return billId;
	}

	@Override
	public String toString() {
		return "ItemData [billId=" + billId + ", vendorCode=" + vendorCode + ", price=" + price + ", timestamps=" + timestamp + "]";
	}

}
