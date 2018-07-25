package ru.greenc4eese.serviceCompare;

import ru.greenc4eese.serviceCompare.objects.ObjectPrinter;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

//TODO Parse args \n\r
//TODO add some GUI
//TODO optional - result only differsn/show log by diference
//TODO fix - somtimes wrong folder (forexample fifa in cbddmo on test)
public class Main {

	public static void main(String[] args) throws IOException {
		Main m = new Main();
		if (args.length > 0) {
			m.run(args[0]);
		} else {
			m.run("");
		}
	}

	private void run(String config) throws IOException {
		File file;

		if (config != null && !config.isEmpty()) {
			file = new File(config);
		} else {
			ClassLoader classLoader = getClass().getClassLoader();
			file = new File(Objects.requireNonNull(classLoader.getResource("config.txt")).getFile());
		}

		Configurator conf = new Configurator(file);
		List<String> zipPaths = conf.getZipPaths();
		StringBuilder command = new StringBuilder(conf.getComparatorPath());
		SystemObjectParser objectParser = new SystemObjectParser();
		ObjectPrinter printer = new ObjectPrinter();

		for (String path : zipPaths) {

			//Unzip
			String unzippedPath = GZipFile.gunzipSingle(path, conf.postfix());
			File unzippedFile = new File(unzippedPath);

			//Parse
			objectParser.parse(unzippedFile);

			String fullPath = CommonUtils.concatPaths(conf.getOuterFolder(), unzippedFile.getName());

			//Print
			printer.printObjects(fullPath);

			command.append(" ").append(fullPath);

			//remove tmp
			CommonUtils.removeFile(unzippedPath);
		}

		System.out.println("command : " + command);
		Runtime.getRuntime().exec(String.valueOf(command));
	}
}