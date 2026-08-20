import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JList;

public class NumberInfos implements Iterable<NumberInfo> {

	private String title;

	private List<NumberInfo> infos = new ArrayList<>();

	public NumberInfos() {
	}

	public NumberInfos(String title) {
		this.title = title;
	}

	public void draw(Graphics2D g2d, DrawPanel panel) {
		final Dimension size = panel.getSize();
		final double max = getMaxValue();
		final double height = size.getHeight(), width = size.getWidth();
		final double barWidth = width / infos.size();

		for (int i = 0; i < infos.size(); i++) {
			NumberInfo info = infos.get(i);

			double barHeight = info.getValue() / max * height;

			Rectangle2D.Double rect = new Rectangle2D.Double(barWidth * i, height - barHeight, barWidth, barHeight);

			g2d.setColor(i % 2 == 0 ? Color.YELLOW : Color.ORANGE);
			g2d.fill(rect);

			g2d.setColor(Color.RED);
			g2d.draw(rect);

			g2d.setColor(panel.getForeground());
			g2d.drawString(Double.toString(info.getValue()), (int) (barWidth * i + 5), (int) height - g2d.getFontMetrics().getHeight());
			g2d.drawString(info.getLabel(), (int) (barWidth * i + 5), (int) height);
		}
	}

	public void update(JList list) {
		list.setListData(toArray());
	}

	public double getMaxValue() {
		return infos.stream().mapToDouble(NumberInfo::getValue).max().orElse(0);
	}
	
	public double getTotalValue() {
		return infos.stream().mapToDouble(NumberInfo::getValue).sum();
	}

	public void add(double value, String label) {
		this.add(new NumberInfo(value, label));
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public NumberInfo get(int index) {
		return infos.get(index);
	}

	public boolean isEmpty() {
		return infos.isEmpty();
	}

	public NumberInfo remove(int index) {
		return infos.remove(index);
	}

	public int size() {
		return infos.size();
	}

	public boolean add(NumberInfo arg0) {
		return infos.add(arg0);
	}

	public void clear() {
		infos.clear();
	}

	public Object[] toArray() {
		return infos.toArray();
	}

	@Override
	public String toString() {
		return infos.toString();
	}

	@Override
	public Iterator<NumberInfo> iterator() {
		return infos.iterator();
	}

}
