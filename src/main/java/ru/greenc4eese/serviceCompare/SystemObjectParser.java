package ru.greenc4eese.serviceCompare;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ru.greenc4eese.serviceCompare.objects.CompareManager;
import ru.greenc4eese.serviceCompare.objects.CompareObject;
import ru.greenc4eese.serviceCompare.objects.ComparePath;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class SystemObjectParser {
	private final String pathToFile;

	SystemObjectParser(String pathToFiles) {
		this.pathToFile = pathToFiles;
	}

	private Object getJson(String value) {
		if (value == null || value.length() == 0) {
			return null;
		}
		try {
			return new JSONParser().parse(value);
		} catch (Exception e) {
			System.out.println("Ошибка при парсинге ");
			System.out.println(e.getMessage());
		}
		return null;
	}

	public Map getJsonObject(String value) {
		Object json = getJson(value);
		return json instanceof Map ? (Map) json : null;
	}

	protected List getJsonArray(String value) {
		Object json = getJson(value);
		return json instanceof List ? (List) json : null;
	}

	private Map<String, Map> getJsonObject(File file) {
		try (FileReader f = new FileReader(file)) {
			return (JSONObject) new JSONParser().parse(f);
		} catch (IOException e) {
			System.out.println("Ошибка при чтении файла :" + file.getName());
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (ParseException e) {
			System.out.println("Ошибка при парсинге файла :" + file.getName());
			System.out.println(e.getMessage());
		}
		return null;
	}

	private File parse() {
		System.out.println("parsing file : " + pathToFile);

		File file = new File(pathToFile);
		CompareManager cm = CompareManager.getInstance();
		Map<String, Map> object = getJsonObject(file);
		Predicate<Map.Entry<String, Map>> p = e -> ComparePath.TYPES.contains(e.getValue().get("type")) || CompareObject.TYPES.contains(e.getValue().get("type"));
		object.entrySet().stream().filter(p).forEach(cm::addItem);

		System.out.println("parsing file successful");

		return file;
	}

	public String operate() {
		File file = parse();

		try {
			//TODO
			Path p = Paths.get(file.getCanonicalPath());
			String filename = p.getFileName().toString();
			Configurator.setOuterPath(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}

		CompareManager cm = CompareManager.getInstance();
		cm.printObjects();
		return Configurator.getOuterPath();
	}
}
