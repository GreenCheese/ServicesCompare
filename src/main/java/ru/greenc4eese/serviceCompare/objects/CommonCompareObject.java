package ru.greenc4eese.serviceCompare.objects;

import ru.greenc4eese.serviceCompare.CommonUtils;

import java.util.Map;

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

	private String getParentPathString(String basePath) {
		String parentPath;
		parentPath = path == null ? basePath : path.getStringPath(basePath);
		return parentPath;
	}

	String getStringPath(String basePath) {
		return CommonUtils.concatPaths(getParentPathString(basePath), name);
	}

	abstract Map getData();

	abstract String getType();
}
