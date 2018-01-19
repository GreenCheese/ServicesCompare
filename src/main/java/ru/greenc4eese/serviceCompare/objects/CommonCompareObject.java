package ru.greenc4eese.serviceCompare.objects;

import ru.greenc4eese.serviceCompare.CommonUtils;
import ru.greenc4eese.serviceCompare.Configurator;

import java.io.IOException;

public abstract class CommonCompareObject {
	private final String name;
	private ComparePath path;

	CommonCompareObject(String name) {
		this.name = name;
	}

	CommonCompareObject(String name, ComparePath path) {
		this.name = name;
		this.path = path;
	}

	public ComparePath getPath() {
		return path;
	}

	public void setPath(ComparePath path) {
		this.path = path;
	}

	private String getParentPathString() {
		String parentPath;
		parentPath = path == null ? Configurator.getOuterPath() : path.getStringPath();
		return parentPath;
	}

	String getStringPath() {
		return CommonUtils.concatPaths(getParentPathString(), name);
	}

	String getStringPath(String objectNamePostfix) {
		return CommonUtils.concatPaths(getParentPathString(), name + "_" + objectNamePostfix);
	}

	void createObject() throws IOException {
		CommonUtils.createDir(getStringPath());
	}
}
