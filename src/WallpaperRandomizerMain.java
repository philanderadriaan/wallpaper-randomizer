import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class WallpaperRandomizerMain {

	/**
	 * Properties file name.
	 */
	private static final String PROP_FILE_NAME = "config.properties";

	/**
	 * Source folder key in properties file.
	 */
	private static final String SRC_KEY = "src";

	/**
	 * Staging folder key in properties file.
	 */
	private static final String STG_KEY = "stg";

	/**
	 * Destination folder key in properties file.
	 */
	private static final String DEST_KEY = "dest";

	/**
	 * Properties object.
	 */
	private static final Properties PROP = new Properties();

	public static void main(String[] args) throws Exception {

		try {
			// Set look and feel to system.
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

			// Load properties file if exists.
			PROP.load(new FileInputStream(PROP_FILE_NAME));

			// Get staging and destination folders.
			File stgFolder = getFolder(STG_KEY);
			File destFolder = getFolder(DEST_KEY);

			// RNG
			int random = new Random().nextInt(1000);
			log("RNG: " + random);

			if (random == 0 || Integer.parseInt(args[0]) == 1) {
				log("Loading wallpapers.");

				// Get source folder.
				File srcFolder = getFolder(SRC_KEY);

			}

			if (random % 10 == 0) {
				log("Randomizing wallpapers.");
				// Clear off destination folder.
				for (File destObj : destFolder.listFiles()) {
					log("Deleting " + destObj.getAbsolutePath());
					destObj.delete();
					log("Deleted " + destObj.getAbsolutePath());
				}

			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), e.getClass().toString(), JOptionPane.ERROR_MESSAGE);
			throw e;
		}

	}

	/**
	 * Get folders from properties.
	 * 
	 * @param key
	 * @return
	 * @throws FileNotFoundException
	 */
	private static File getFolder(String key) throws FileNotFoundException {
		// Separate folders from properties by delimiter.
		String[] folderNames = ((String) PROP.get(key)).split(",");
		List<File> folders = new ArrayList<File>();
		for (String folderName : folderNames) {
			File folder = new File(folderName);
			if (folder.isDirectory()) {
				folders.add(folder);
			} else {
				throw new FileNotFoundException("Folder not selected.");
			}
		}

		// Returns a random folder from a list of folder.
		return folders.get(new Random().nextInt(folders.size()));
	}

	/**
	 * Writes to log.
	 * 
	 * @param msg
	 */
	private static void log(String msg) {
		System.out.println(new Date() + "\t" + msg);
	}

}
