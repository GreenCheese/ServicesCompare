package ru.greenc4eese.serviceCompare;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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

	private final File configFile;
	private final List<String> comparePaths;
	private String comparatorPath;

	Configurator(File configFile) {
		this.configFile = configFile;
		comparePaths = new ArrayList<>(2);
		init();
	}

	public String getOuterFolder() {
		return outerFolder;
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
			comparePaths.add(f.getAbsolutePath());
		}

		if (outerFolder == null) {
			File f = new File(comparePaths.get(0));
			String s = f.getParentFile().getAbsolutePath();
			outerFolder = CommonUtils.concatPaths(s, "out");
		}
	}

	public List<String> getZipPaths() {
		return comparePaths;
	}
}