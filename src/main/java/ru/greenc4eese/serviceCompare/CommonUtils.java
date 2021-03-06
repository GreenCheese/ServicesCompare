package ru.greenc4eese.serviceCompare;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

public class CommonUtils {

	public static void removeFile(String pathToFile) throws IOException {
		Files.delete(Paths.get(pathToFile));
	}

	public static void mkdirs(File outDir, String path) {
		File d = new File(outDir, path);
		if (!d.exists()) {
			d.mkdirs();
		}
	}

	public static void createFile(String pathFileName) throws IOException {
		File f = new File(pathFileName);
		f.getParentFile().mkdirs();
		f.createNewFile();
	}

	public static void createDir(String pathFileName) {
		File f = new File(pathFileName);
	}

	public static void createFile(String pathFileName, String data) throws IOException {
		createFile(pathFileName);
		Path file = Paths.get(pathFileName);
		Files.write(file, Collections.singleton(data), Charset.forName("UTF-8"));
	}

	public static void createFile(String pathFileName, String postfix, String data) throws IOException {
		if (data == null || data.equals("null") || data.trim().isEmpty()) {
			return;
		}

		createDir(pathFileName);
		String fullPath;
		if (postfix != null) {
			fullPath = CommonUtils.concatPaths(pathFileName, postfix);
		} else {
			fullPath = pathFileName;
		}

		createFile(fullPath, data);
	}

	public static String concatPaths(String p1, String p2) {
		return p1 + File.separator + p2;
	}
}
