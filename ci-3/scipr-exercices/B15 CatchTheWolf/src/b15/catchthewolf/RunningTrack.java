package b15.catchthewolf;

public class RunningTrack {

	private boolean running = false;
	private int wolfPosition = 0, playerPosition = 0, maxPosition = 25, maxWolfStep = 10;

	public RunningTrack(int maxPos, int maxWolfStep) {
		this.maxPosition = maxPos;
		this.maxWolfStep = maxWolfStep;
	}

	public boolean wolfEnded() {
		return this.wolfPosition >= this.maxPosition;
	}

	public boolean playerEnded() {
		return this.playerPosition >= this.maxPosition;
	}

	public boolean isEndReached() {
		return this.playerPosition >= this.maxPosition || this.wolfPosition >= this.maxPosition;
	}

	public boolean wolfCaught() {
		return this.playerPosition == this.wolfPosition;
	}

	public void start() {
		this.running = true;
		this.wolfPosition = 0;
		this.playerPosition = 0;
	}

	public void stop() {
		this.running = false;
	}

	public void addPlayer(int pos) {
		this.playerPosition += pos;
	}

	public void addWolf(int pos) {
		this.wolfPosition += pos;
	}

	public void addRandomWolf() {
		this.wolfPosition += (int) (Math.random() * maxWolfStep);
	}

	public int getPlayerPosition() {
		return playerPosition;
	}

	public int getWolfPosition() {
		return wolfPosition;
	}

	public boolean isRunning() {
		return running;
	}

	public void setPlayerPosition(int playerPosition) {
		this.playerPosition = playerPosition;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public void setWolfPosition(int wolfPosition) {
		this.wolfPosition = wolfPosition;
	}

	public int getMaxPosition() {
		return maxPosition;
	}

	public void setMaxPosition(int maxPosition) {
		this.maxPosition = maxPosition;
	}

	public int getMaxWolfStep() {
		return maxWolfStep;
	}

	public void setMaxWolfStep(int maxWolfStep) {
		this.maxWolfStep = maxWolfStep;
	}

}
