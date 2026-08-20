import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

public class Shapes {

	private static final ObjectMapper MAPPER = new ObjectMapper();
	static {
		final SimpleModule colorModule = new SimpleModule();
		colorModule.addSerializer(Color.class, new JsonSerializer<Color>() {
			@Override
			public void serialize(Color color, JsonGenerator gen, SerializerProvider serializers) throws IOException {
				String hex = String.format("#%02x%02x%02x", color.getRed(), color.getGreen(), color.getBlue());
				gen.writeString(hex);
			}
		});

		colorModule.addDeserializer(Color.class, new JsonDeserializer<Color>() {
			@Override
			public Color deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
				String hex = p.getText();
				return Color.decode(hex);
			}
		});

		MAPPER.registerModule(colorModule);
	}

	private List<CustomShape> shapes = new ArrayList<CustomShape>();

	public void draw(Graphics2D g2d) {
		g2d.setColor(Color.BLACK);

		shapes.forEach(l -> l.draw(g2d));
	}

	public List<CustomShape> getShapes() {
		return shapes;
	}

	public void setShapes(List<CustomShape> lines) {
		this.shapes = lines;
	}

	public boolean add(CustomShape shape) {
		return shapes.add(shape);
	}

	public void clear() {
		shapes.clear();
	}

	public Iterator<CustomShape> iterator() {
		return shapes.iterator();
	}

	public CustomShape remove(int index) {
		return shapes.remove(index);
	}

	public int size() {
		return shapes.size();
	}

	public Stream<CustomShape> stream() {
		return shapes.stream();
	}

	public String asJSON() throws JsonProcessingException {
		return MAPPER.writerFor(new TypeReference<List<CustomShape>>() {
		}).writeValueAsString(shapes);
	}

	public void fromJSON(String json) throws JsonMappingException, JsonProcessingException {
		this.shapes = MAPPER.readValue(json, new TypeReference<List<CustomShape>>() {
		});
	}

	public void appendJSON(String json) throws JsonMappingException, JsonProcessingException {
		this.shapes.addAll(MAPPER.readValue(json, new TypeReference<List<CustomShape>>() {
		}));
	}

}
