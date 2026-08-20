package custom;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Deprecated
public enum Direction {

	UP, DOWN, LEFT, RIGHT;

	public static Set<String> ALL = Collections
			.unmodifiableSet(new HashSet<>(Arrays.stream(values()).map(c -> c.name()).toList()));

}