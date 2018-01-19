package ru.greenc4eese.serviceCompare;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;

public class GZipFile {
	private static final int BUFFER_SIZE = 4096;

	public static List<String> gunzipMany(List<String> zipPathes, String postFix) {
		List<String> unzippedPaths = new ArrayList<>();
		for (String path : zipPathes) {
			String unzippedFile = path + postFix;
			try {
				GZipFile.gunzipIt(path, unzippedFile);
				unzippedPaths.add(unzippedFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return unzippedPaths;
	}

	public static String gunzipSingle(String zipPath, String postFix) {
		String unzippedFile = zipPath + postFix;
		try {
			GZipFile.gunzipIt(zipPath, unzippedFile);
		} catch (Exception e) {
			System.out.println("Ошибка при попытке разархивирования файла" + zipPath);
			e.printStackTrace();
		}
		return unzippedFile;
	}

	/**
	 * GunZip it
	 */
	private static void gunzipIt(String inputFile, String outputFile) {
		System.out.println("UnGZipping...");
		byte[] buffer = new byte[BUFFER_SIZE];

		try {
			GZIPInputStream gzis = new GZIPInputStream(new FileInputStream(inputFile));
			FileOutputStream out = new FileOutputStream(outputFile);

			int len;
			while ((len = gzis.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}

			gzis.close();
			out.close();

			System.out.println("Done unGZip");

		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}