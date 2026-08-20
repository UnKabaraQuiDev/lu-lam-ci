public class DoubleDice {
	
	public static class DoubleDiceStats {
		
		private long dicesThrown, doubleDices;
		private double doubleDicePercentage;

		public DoubleDiceStats(long dt, long dd) {
			this.dicesThrown = dt;
			this.doubleDices = dd;
			this.doubleDicePercentage = (double) dd / dt * 100;
		}
		
		public long getDicesThrown() {
			return dicesThrown;
		}
		public double getDoubleDicePercentage() {
			return doubleDicePercentage;
		}
		public long getDoubleDices() {
			return doubleDices;
		}
		
	}

	public static DoubleDiceStats throwDices(long count) {
		long doubleDices = 0;
		for (long i = 0; i < count; i++) {
			doubleDices += ((int) (Math.random() * 6) == (int) (Math.random() * 6)) ? 1 : 0;
		}
		return new DoubleDiceStats(count, doubleDices);
	}
}