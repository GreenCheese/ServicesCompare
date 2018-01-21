package ru.greenc4eese.serviceCompare.objects;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CompareObject extends CommonCompareObject {
	public static final Set<String> TYPES = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("reportdata", "service")));
	private static final Set<String> OUTPUT = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("arguments", "model", "script")));
	private Map<String, Map> props;

	public CompareObject(String name) {
		super(name);
	}

	public CompareObject(String name, ComparePath path) {
		super(name, path);
	}

	CompareObject(String name, Map<String, Map> props) {
		super(name);
		this.props = props;
	}

	@Override public Map<String, String> getData() {
		Map<String, String> data = new HashMap<>();
		if (props != null) {
			for (String s : OUTPUT) {
				data.put(s, String.valueOf(props.get(s)));
			}
		}
		return data;
	}
}
