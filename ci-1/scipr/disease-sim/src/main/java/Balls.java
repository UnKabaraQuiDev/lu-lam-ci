
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.apache.commons.collections4.queue.CircularFifoQueue;
import org.joml.Math;
import org.joml.Vector2f;

import lu.pcy113.pclib.PCUtils;
import lu.pcy113.pclib.swing.JLineGraph;

public class Balls {

	public static record Stats(long count, long dead, long healthy, long imune, long sick) {

		public Stats(long dead, long healthy, long imune, long sick) {
			this(dead + healthy + imune + sick, dead, healthy, imune, sick);
		}

	}

	private static final float SPEED = 10f;

	private CircularFifoQueue<Stats> history = new CircularFifoQueue<>(10000);
	private ArrayList<Ball> balls = new ArrayList<Ball>();

	private JPanel panel;
	private JLineGraph graphPanel;

	private JLineGraph.ChartData healthySeries;
	private JLineGraph.ChartData sickSeries;
	private JLineGraph.ChartData deadSeries;
	private JLineGraph.ChartData imuneSeries;

	public Balls(JPanel panel, JLineGraph graphPanel) {
		this.panel = panel;
		this.graphPanel = graphPanel;

		graphPanel.setMajorAxisColor(Color.GRAY);
		graphPanel.setMinorAxisColor(Color.WHITE);
		// graphPanel.setUseMinorAxisSteps(false);
		// graphPanel.setAnnotateMinorAxis(false);
		graphPanel.setUseFixedPadding(false);
		graphPanel.setMinorAxisStep(100);

		graphPanel.setNextFilled(true);
		graphPanel.setNextBorder(false);
		healthySeries = graphPanel.createSeries("healthy").setFillColor(DiseaseState.HEALTHY.getColor());
		sickSeries = graphPanel.createSeries("sick").setFillColor(DiseaseState.SICK.getColor());
		imuneSeries = graphPanel.createSeries("imune").setFillColor(DiseaseState.IMUNE.getColor());
		deadSeries = graphPanel.createSeries("dead").setFillColor(DiseaseState.DEAD.getColor());
	}

	public void draw(Graphics2D g2d) {
		g2d.setStroke(new BasicStroke(3));

		balls.stream().sorted((a, b) -> {
			// Grey balls should come first (drawn first = behind)
			if (a.getState() == DiseaseState.DEAD && b.getState() != DiseaseState.DEAD) {
				return -1;
			} else if (a.getState() != DiseaseState.DEAD && b.getState() == DiseaseState.DEAD) {
				return 1;
			} else {
				return 0; // keep relative order for non-grey balls
			}
		}).forEachOrdered(mb -> mb.draw(g2d));
	}

	public void add(Ball ball) {
		balls.add(ball);
	}

	public Ball getSelectedBall(int x, int y) {
		return balls.parallelStream().filter(b -> b.isInside(x, y)).findFirst().orElse(null);
	}

	public void doPhysics(float dTime) {
		final List<Ball> toAdd = new ArrayList<>();

		for (int i = 0; i < balls.size(); i++) {
			final Ball a = balls.get(i);
			for (int j = i + 1; j < balls.size(); j++) {
				final Ball b = balls.get(j);

				if (a.bounce(b)) {
					toAdd
							.add(new Ball(new Vector2f(a.getPosition()),
									new Vector2f((float) (Math.random() * 2 - 1), (float) (Math.random() * 2 - 1)).normalize(), 5));
				}
			}
		}

		if (Math.random() < 0.01f) {
			balls.get(PCUtils.randomIntRange(0, balls.size())).setState(DiseaseState.SICK);
		}

		final long nowTime = System.currentTimeMillis();
		balls.removeIf(b -> b.getState() == DiseaseState.DEAD && nowTime > b.getInfectedTime());

		final float speed = SPEED * dTime;
		final int width = panel.getWidth(), height = panel.getHeight();
		balls.forEach(b -> b.doPhysics(speed, width, height));

		history
				.add(new Stats(balls.stream().filter(b -> b.getState() == DiseaseState.DEAD).count(),
						balls.stream().filter(b -> b.getState() == DiseaseState.HEALTHY).count(),
						balls.stream().filter(b -> b.getState() == DiseaseState.IMUNE).count(),
						balls.stream().filter(b -> b.getState() == DiseaseState.SICK).count()));

		healthySeries.setValues(history.parallelStream().map(c -> (double) c.count).toList());
		sickSeries.setValues(history.parallelStream().map(c -> (double) (c.sick + c.imune + c.dead)).toList());
		imuneSeries.setValues(history.parallelStream().map(c -> (double) (c.imune + c.dead)).toList());
		deadSeries.setValues(history.parallelStream().map(c -> (double) c.dead).toList());

		graphPanel.repaint();

		balls.addAll(toAdd);
	}

	public int size() {
		return balls.size();
	}

	public void recap() {
		System.out.println(" ======= ");
		System.out.println(" Sick: " + balls.stream().filter(p -> p.getState() == DiseaseState.SICK).count());
		System.out.println(" Dead: " + balls.stream().filter(p -> p.getState() == DiseaseState.DEAD).count());
		System.out.println(" ======= ");
	}

}