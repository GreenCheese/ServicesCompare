package ru.greenc4eese.serviceCompare;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class Configurator {
	private static final String POSTFIX = "-unzip";
	private static final Set<String> SRC_FILE_KEYS = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("comp1", "comp2")));
	private static String outerFolder;
	private static String outerPath;

	private final File configFile;
	private final List<Path> comparePathes;
	private String comparatorPath;

	Configurator(File configFile) {

		this.configFile = configFile;
		comparePathes = new ArrayList<>(2);
		init();
	}

	public static String getOuterFolder() {
		return outerFolder;
	}

	public static void setOuterFolder(String outerFolder) {
		Configurator.outerFolder = outerFolder;
	}

	public static String getOuterPath() {
		return outerPath;
	}

	public static void setOuterPath(String outerPath) {
		Configurator.outerPath = CommonUtils.concatPaths(outerFolder, outerPath);
	}

	public String getComparatorPath() {
		return comparatorPath;
	}

	public String postfix() {
		return POSTFIX;
	}

	private void init() {
		JSONParser parser = new JSONParser();
		Object configObj = null;
		try {
			configObj = parser.parse(new FileReader(configFile));
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}

		Map<String, Object> configMap = (Map) configObj;
		outerFolder = (String) Objects.requireNonNull(configMap).get("resultFolder");
		comparatorPath = (String) configMap.get("comparator");

		for (String key : SRC_FILE_KEYS) {
			String path = (String) configMap.get(key);
			File f = new File(path);
			try {
				comparePathes.add(Paths.get(f.getCanonicalPath()));
			} catch (IOException e) {
				System.out.println("Incorrect path " + path);
				System.exit(0);
			}
		}

		if (outerFolder == null) {
			outerFolder = CommonUtils.concatPaths(comparePathes.get(0).getParent().toString(), "out");
		}
	}

	public List<Path> getZipPathes() {
		return comparePathes;
	}
}
