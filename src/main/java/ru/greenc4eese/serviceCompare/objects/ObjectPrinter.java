package ru.greenc4eese.serviceCompare.objects;

import ru.greenc4eese.serviceCompare.CommonUtils;

import java.io.IOException;
import java.util.Map;

public class ObjectPrinter {
	public void printObjects(String basePath) {
		Map<String, CommonCompareObject> allObjects = CompareManager.getInstance().getAllObjects();
		System.out.println("Creating objects...");

		for (CommonCompareObject cco : allObjects.values()) {
			if (cco instanceof CompareObject) {
				String localPath = cco.getStringPath(basePath);
				try {
					Map<String, String> data = cco.getData();
					for (String key : data.keySet()) {
						CommonUtils.createFile(localPath, key, data.get(key));
					}
				} catch (IOException e) {
					System.out.println("Ошибка при формировании файла объекта " + localPath);
					e.printStackTrace();
				}
			}
		}
		System.out.println("Objects created");
	}
}
