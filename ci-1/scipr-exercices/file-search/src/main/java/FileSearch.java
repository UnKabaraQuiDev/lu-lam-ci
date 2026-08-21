
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class FileSearch {

	public static void main(String[] args) {
		final Scanner scanner = new Scanner(System.in);

		System.out.print("Enter directory path: ");
		final String directoryPath = scanner.nextLine();

		System.out.print("Search recursively? (yes/no): ");
		final boolean recursive = scanner.nextLine().trim().equalsIgnoreCase("yes");

		System.out.print("Enter name to search for: ");
		final String searchTerm = scanner.nextLine();

		final File directory = new File(directoryPath);

		if (!directory.exists() || !directory.isDirectory()) {
			System.out.println("Invalid directory path.");
			scanner.close();
			return;
		}

		System.out.println("Searching for files containing: " + searchTerm);
		searchDirectory(directory, searchTerm, recursive);

		scanner.close();
	}

	private static void searchDirectory(File dir, String searchTerm, boolean recursive) {
		final File[] files = dir.listFiles();
		if (files == null)
			return;

		for (File file : files) {
			if (file.getName().contains(searchTerm)) {
				System.out.println(file.getAbsolutePath());
			}

			if (file.getAbsolutePath().contains("OneDrive")) {
				System.err.println("Ignoring: " + file.getAbsolutePath());
				continue;
			}

			if (Files.isSymbolicLink(Paths.get(file.getAbsolutePath()))) {
				System.err.println("Ignoring symlink: " + file.getAbsolutePath());
				continue;
			}

			if (recursive && file.isDirectory()) {
				searchDirectory(file, searchTerm, true);
			}
		}
	}

}
