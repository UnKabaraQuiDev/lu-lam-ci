import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @JsonSubTypes.Type(value = Rectangle.class, name = "rectangle"), @JsonSubTypes.Type(value = Line.class, name = "line") })
public interface CustomShape extends Shape {

	void setStartpoint(Point p1);

	void setEndpoint(Point p2);

	Point getStartpoint();

	Point getEndpoint();

	void draw(Graphics2D g2d);
	
	void setColor(Color col);
	
	Color getColor();

}
