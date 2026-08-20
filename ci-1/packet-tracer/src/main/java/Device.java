import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Device {
	private int x;
	private int y;
	private String parent;
	private String type;
	private String name;
	private String ip;
	private List<Device> subDevices = new ArrayList<>();
	private PingStatus pingStatus = PingStatus.UNKNOWN;

	public Device() {
	}

	public Device(String parent, String type, String name, String ip) {
		this.parent = parent;
		this.type = type;
		this.name = name;
		this.ip = ip;
	}

	public Device(final int x, final int y, final String parent, final String type, final String name, final String ip) {
		this.x = x;
		this.y = y;
		this.parent = parent;
		this.type = type;
		this.name = name;
		this.ip = ip;
		this.pingStatus = PingStatus.UNKNOWN;
	}

	public int getX() {
		return this.x;
	}

	public void setX(final int x) {
		this.x = x;
	}

	public int getY() {
		return this.y;
	}

	public void setY(final int y) {
		this.y = y;
	}

	public String getParent() {
		return this.parent;
	}

	public void setParent(final String parent) {
		this.parent = parent;
	}

	public String getType() {
		return this.type;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public String getName() {
		return this.name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(final String ip) {
		this.ip = ip;
	}

	public List<Device> getSubDevices() {
		return this.subDevices;
	}

	public void setSubDevices(final List<Device> subDevices) {
		this.subDevices = subDevices != null ? subDevices : new ArrayList<>();
	}

	public PingStatus getPingStatus() {
		return this.pingStatus;
	}

	public void setPingStatus(final PingStatus pingStatus) {
		this.pingStatus = pingStatus;
	}

	@JsonIgnore
	public int getCenterX() {
		return this.x + 40;
	}

	@JsonIgnore
	public int getCenterY() {
		return this.y + 25;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof final Device device)) {
			return false;
		}
		return Objects.equals(this.name, device.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.name);
	}
}