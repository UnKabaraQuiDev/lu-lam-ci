import java.net.InetAddress;

public class PingService {

	public PingStatus ping(final Device device) {
		if (device == null || device.getIp() == null || device.getIp().isBlank()) {
			return PingStatus.UNKNOWN;
		}

		try {
			final InetAddress address = InetAddress.getByName(device.getIp());
			final boolean reachable = address.isReachable(1500);
			return reachable ? PingStatus.REACHABLE : PingStatus.FAILED;
		} catch (final Exception e) {
			return PingStatus.FAILED;
		}
	}

}