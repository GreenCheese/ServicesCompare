package ru.greenc4eese.serviceCompare.objects;

import ru.greenc4eese.serviceCompare.CommonUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
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

	@Override
	public void createObject() throws IOException {
		if (props != null) {
			String data;
			for (String s : OUTPUT) {
				data = String.valueOf(props.get(s));
				if (data != null) {
					String pathFileName = getStringPath(s);
					CommonUtils.createFile(pathFileName, data);
				}
			}
		}
	}
}
