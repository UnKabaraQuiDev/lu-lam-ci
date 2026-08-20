package qsd;
import java.io.File;
import java.io.IOException;

public interface Dictionnary {

	boolean contains(String word);

	void load(File f) throws IOException;

}
