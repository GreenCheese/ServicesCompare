package ru.greenc4eese.serviceCompare.objects;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ComparePath extends CommonCompareObject {
	public static final Set<String> TYPES = Collections.unmodifiableSet(new HashSet<>(Arrays.asList( //
			"layergroup", //
			"reportgroup", //
			"vis", //
			"actionsgroup",//
			"reportgroup" //
	)));

	ComparePath(String name) {
		super(name);
	}

	ComparePath(String name, ComparePath path) {
		super(name, path);
	}

	@Override String getType() {
		return "";
	}

	@Override Map getData() {
		return null;
	}

}
