
public class PrintJob {

	private String title;
	private int duration;
	private long startedTime = -1;

	public PrintJob(String title, int duration) {
		this.title = title;
		this.duration = duration;
	}

	public String getTitle() {
		return title;
	}

	public int getDuration() {
		return duration;
	}

	public long getStartedTime() {
		return startedTime;
	}

	public void setStartedTime(long startedTime) {
		this.startedTime = startedTime;
	}

	public int getElapsedTime() {
		return (int) (System.currentTimeMillis() - startedTime);
	}
	
	public int getRemainingTime() {
		return duration - getElapsedTime();
	}

	public boolean isStarted() {
		return startedTime != -1;
	}

	public boolean isDone() {
		return System.currentTimeMillis() > startedTime + duration;
	}

}
