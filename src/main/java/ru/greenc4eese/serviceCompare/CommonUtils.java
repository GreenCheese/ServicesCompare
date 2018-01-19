package ru.greenc4eese.serviceCompare;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class CommonUtils {

	public static void removeFiles(List<String> pathToFiles) throws IOException {
		for (String pathToFile : pathToFiles) {
			removeFile(pathToFile);
		}
	}

	public static void removeFile(String pathToFile) throws IOException {
		Files.delete(Paths.get(pathToFile));
	}

	public static void mkdirs(File outDir, String path) {
		File d = new File(outDir, path);
		if (!d.exists()) {
			d.mkdirs();
		}
	}

	public static String getUserDir() {
		return System.getProperty("user.dir");
	}

	public static void createFile(String pathFileName) throws IOException {
		File f = new File(pathFileName);
		f.getParentFile().mkdirs();
		f.createNewFile();
	}

	public static void createDir(String pathFileName) {
		File f = new File(pathFileName);
		f.mkdirs();
	}

	public static void createFile(String pathFileName, String data) throws IOException {
		createFile(pathFileName);
		Path file = Paths.get(pathFileName);
		Files.write(file, Collections.singleton(data), Charset.forName("UTF-8"));
	}

	public static String concatPaths(String p1, String p2) {
		return p1 + File.separator + p2;
	}
}
