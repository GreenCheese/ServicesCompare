package ru.greenc4eese.serviceCompare;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class Main {

	public static void main(String[] args) throws IOException {
		Main m = new Main();
		m.run(args[0]);

	}

	private void run(String config) throws IOException {
		File file;

		if (config != null && !config.isEmpty()) {
			file = new File(config);
		} else {
			ClassLoader classLoader = getClass().getClassLoader();
			file = new File(Objects.requireNonNull(classLoader.getResource(config)).getFile());
		}

		Configurator conf = new Configurator(file);

		List<Path> zipPaths = conf.getZipPathes();
		StringBuilder command = new StringBuilder(conf.getComparatorPath());

		for (Path path : zipPaths) {
			String unzippedPath = GZipFile.gunzipSingle(path.toString(), conf.postfix());
			SystemObjectParser objectParser = new SystemObjectParser(unzippedPath);
			String resPath = objectParser.operate();
			command.append(" ").append(resPath);
			CommonUtils.removeFile(unzippedPath);
		}
		//System.out.println("command : " + command);
		Runtime.getRuntime().exec(String.valueOf(command));
	}
}
