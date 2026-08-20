
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import javax.swing.JPanel;

import lu.pcy113.pclib.PCUtils;
import lu.pcy113.pclib.builder.ThreadBuilder;
import lu.pcy113.pclib.impl.ExceptionSupplier;

public class Balls {

	public static final int BALL_SIZE_MAX = 50;
	private int X_COUNT, Y_COUNT;

	private ArrayList<Ball> balls = new ArrayList<Ball>();
	private ArrayList<Ball>[][] quad;

	public Balls(JPanel panel) {
		X_COUNT = panel.getWidth() / BALL_SIZE_MAX + 1;
		Y_COUNT = panel.getHeight() / BALL_SIZE_MAX + 1;

		this.quad = new ArrayList[X_COUNT][Y_COUNT];
		for (int x = 0; x < X_COUNT; x++) {
			quad[x] = new ArrayList[Y_COUNT];
			for (int y = 0; y < Y_COUNT; y++) {
				quad[x][y] = new ArrayList<Ball>();
			}
		}
	}

	// Changed to Graphics2D
	public void draw(Graphics2D g2d) {
		balls.forEach(b -> b.draw(g2d));
	}

	public void add(Ball ball) {
		int ix = (int) (ball.x / BALL_SIZE_MAX), iy = (int) (ball.y / BALL_SIZE_MAX);

		// System.err.println(X_COUNT + " " + Y_COUNT);
		// System.out.println(ix + " " + iy);

		quad[ix][iy].add(ball);
		balls.add(ball);
	}

	public void cleanUp() {
		balls.removeIf(Ball::isToDelete);
		Arrays.stream(quad).flatMap(q -> Arrays.stream(q)).forEach(q -> q.removeIf(Ball::isToDelete));
	}

	public void move(int width, int height) {
		balls.forEach(b -> b.move(width, height));
	}

	public Ball getSelectedBall(int x, int y) {
		return balls.parallelStream().filter(b -> b.isInside(x, y)).findFirst().orElse(null);
	}

	public List<ScoreEntries> entries = new ArrayList<>();
	public static List<ScoreEntries> reg = new ArrayList<>();
	public static Set<String> failed = new HashSet<>();

	public void determineNumberOfNeighbors() {
		entries.clear();

		final int TIMEOUT = 8;

		startInterruptTimer(TIMEOUT, TimeUnit.MINUTES, this::_singleThreadFor, "Single Thread For");

		startInterruptTimer(TIMEOUT, TimeUnit.MINUTES, this::_multiThreadFor, "Multi Thread For");

		startInterruptTimer(TIMEOUT, TimeUnit.MINUTES, this::_singleThreadStream, "Single Thread Stream");

		startInterruptTimer(TIMEOUT, TimeUnit.MINUTES, this::_parallelThreadStream, "Parallel Thread Stream");

		startInterruptTimer(TIMEOUT, TimeUnit.MINUTES, this::_nestedParallelThreadStream, "Nested Parallel Thread Stream");

		startInterruptTimer(TIMEOUT, TimeUnit.MINUTES, this::_singleThreadQuad, "Single Thread Quad");

		startInterruptTimer(TIMEOUT, TimeUnit.MINUTES, this::_multiThreadQuad, "Multi Thread Quad");

		System.out.println(PCUtils.repeatString("\n", 2));
		System.out.println("Scoreboard:");
		entries.stream().sorted((a, b) -> a.time > b.time ? 1 : -1).forEach(s -> System.out.println(s.name + " | " + s.count + " | " + ((double) s.time / 1_000_000) + "ms"));

		reg.addAll(entries);
	}

	private ExecutorService startInterruptTimer(long delay, TimeUnit unit, ExceptionSupplier<Long> run, String type) {
		if(failed.contains(type)) {
			System.out.println(type + " @ " + balls.size() + " | " + "skipped");
			entries.add(new ScoreEntries(-1, type, balls.size()));
			
			return null;
		}
		
		final ExecutorService executor = Executors.newSingleThreadExecutor();

		final Future<Long> future = executor.submit(() -> run.get());

		try {
			final long elapsedTimeNs = future.get(delay, unit);

			System.out.println(type + " @ " + balls.size() + " | " + ((double) elapsedTimeNs / 1_000_000) + "ms");

			entries.add(new ScoreEntries(elapsedTimeNs, type, balls.size()));
		} catch (TimeoutException e) {
			future.cancel(true);
			System.out.println(type + " @ " + balls.size() + " | " + "stopped");
			entries.add(new ScoreEntries(-1, type, balls.size()));
			failed.add(type);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			executor.shutdown();
		}

		return executor;
	}

	private long _singleThreadFor() throws InterruptedException {
		//@formatter:off
		return PCUtils.nanoTime(() -> {
			for(Ball b : balls) {
				int neighborCount = 0;
				
				for(Ball b2 : balls) {
					if(b.isTouching(b2)) {
						neighborCount++;
					}
				}
				
				b.setNeighborCount(neighborCount - 1);
			}
		});
		//@formatter:on
	}

	private long _multiThreadFor() throws InterruptedException {
		//@formatter:off
		return PCUtils.nanoTime(() -> {
			for(Ball b : balls) {
				ThreadBuilder.create(() -> {
					int neighborCount = 0;
					
					for(Ball b2 : balls) {
						if(b.isTouching(b2)) {
							neighborCount++;
						}
					}
					
					b.setNeighborCount(neighborCount - 1);
				}).start();
			}
		});
		//@formatter:on
	}

	private long _singleThreadStream() throws InterruptedException {
		//@formatter:off
		return PCUtils.nanoTime(() -> balls.stream().forEach(b -> b.setNeighborCount((int) (balls.stream().filter(b2 -> b.isTouching(b2)).count() - 1))));
		//@formatter:on
	}

	private long _parallelThreadStream() throws InterruptedException {
		//@formatter:off
		return PCUtils.nanoTime(() -> balls.parallelStream().forEach(b -> b.setNeighborCount((int) (balls.stream().filter(b2 -> b.isTouching(b2)).count() - 1))));
		//@formatter:on
	}

	private long _nestedParallelThreadStream() throws InterruptedException {
		//@formatter:off
		return PCUtils.nanoTime(() -> balls.parallelStream().forEach(b -> b.setNeighborCount((int) (balls.parallelStream().filter(b2 -> b.isTouching(b2)).count() - 1))));
		//@formatter:on
	}

	private long _singleThreadQuad() throws InterruptedException {
		//@formatter:off
		return PCUtils.nanoTime(() -> {
			for(int ix = 0; ix < quad.length; ix++) {
				ArrayList<Ball>[] xQuad = quad[ix];
				for(int iy = 0; iy < xQuad.length; iy++) {
					final ArrayList<Ball> yQuad = xQuad[iy];
					
					for(Ball b : yQuad) {
						int count = 0;
						for(int dx = -1; dx <= 1; dx++) {
							for(int dy = -1; dy <= 1; dy++) {
								int idx = ix + dx, idy = iy + dy;
								
								// oob
								if(idx < 0 || idx >= X_COUNT)
									continue;
								if(idy < 0 || idy >= Y_COUNT)
									continue;
								
								
								for(Ball b2 : quad[idx][idy]) {
									if(b.isTouching(b2)) {
										count++;
									}
								}
							}	
						}
						
						b.setNeighborCount(count-1);
					}
				}
			}
		});
		//@formatter:on
	}

	private long _multiThreadQuad() throws InterruptedException {
		//@formatter:off
		return PCUtils.nanoTime(() -> {
			for(int ix = 0; ix < quad.length; ix++) {
				ArrayList<Ball>[] xQuad = quad[ix];
				for(int iy = 0; iy < xQuad.length; iy++) {
					final int _ix = ix, _iy = iy;
					
					final ArrayList<Ball> yQuad = xQuad[iy];
					
					ThreadBuilder.create(() -> {
						for(Ball b : yQuad) {
							int count = 0;
							for(int dx = -1; dx <= 1; dx++) {
								for(int dy = -1; dy <= 1; dy++) {
									int idx = _ix + dx, idy = _iy + dy;
									
									// oob
									if(idx < 0 || idx >= X_COUNT)
										continue;
									if(idy < 0 || idy >= Y_COUNT)
										continue;
									
									
									for(Ball b2 : quad[idx][idy]) {
										if(b.isTouching(b2)) {
											count++;
										}
									}
								}	
							}
							
							b.setNeighborCount(count-1);
						}
					}).start();
				}
			}
		});
		//@formatter:on
	}

	public static class ScoreEntries {
		public long time;
		public String name;
		public int count;

		public ScoreEntries(long time, String name, int count) {
			super();
			this.time = time;
			this.name = name;
			this.count = count;
		}

	}

}
