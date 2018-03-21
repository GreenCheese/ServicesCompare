package ru.greenc4eese.serviceCompare.objects;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TypePropertyObject extends CompareObject {
	public static final String TYPE = CompareObject.TYPEPROPERTY_OBJECT;
	private static final Set<String> OUTPUT = Collections.unmodifiableSet(new HashSet<>(Arrays.asList("serverscript", "clientscript")));

	TypePropertyObject(String name, Map<String, Map> props) {
		super(name, props);
	}

	public static boolean isValid(String name, Map<String, Map> props) {
		if (props == null) {
			return false;
		}

		if ("action".equals(props.get("type"))) {
			String code = String.valueOf(props.get("code"));
			if (code != null && !code.isEmpty()) {
				return true;
			}
		}
		return false;
	}

	@Override public String getType() {
		return TYPE;
	}

	@Override public Set<String> getDataSource() {
		return OUTPUT;
	}
}
