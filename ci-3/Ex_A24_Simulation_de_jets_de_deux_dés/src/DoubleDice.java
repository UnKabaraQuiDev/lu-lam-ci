public class DoubleDice {
	public static class DoubleDiceStats {
		private long dicesThrown, doubleDices;
		private double doubleDicePercentage;
		public DoubleDiceStats(long dicesThrown, long doubleDices) {
			this.diceThrown = dt;
			this.doubleDices = dd;
			this.doubleDicePercentage = (double) dd/dt*100;
		}
	}

	public DoubleDiceStats throwDices(long count) {
		long doubleDices;
		for(long i = 0; i < count; i++) {
			doubleDices += ((int) (Math.random()*6) == (int) (Math.random()*6));
		}
		return new DoubleDiceStats(count, doubleDices);
	}
}