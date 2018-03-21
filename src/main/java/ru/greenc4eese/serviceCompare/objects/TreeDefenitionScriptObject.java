package ru.greenc4eese.serviceCompare.objects;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TreeDefenitionScriptObject extends CompareObject {
	public static final String TYPE = CompareObject.TREE_DEFENITION_SCRIPT_OBJECT;
	private static final Set<String> OUTPUT = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("childrenscript", "childrentypesscript")));

	TreeDefenitionScriptObject(String name, Map<String, Map> props) {
		super(name, props);
	}

	@Override public String getType() {
		return TYPE;
	}

	@Override public Set<String> getDataSource() {
		return OUTPUT;
	}
}
