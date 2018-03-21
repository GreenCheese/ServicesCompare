package ru.greenc4eese.serviceCompare.objects;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class CompareObject extends CommonCompareObject {

	public final static String SERVICE_OBJECT = "service";

	public final static String LAYER_OBJECT = "layer";

	public final static String SCENARIO_ACTION_OBJECT = "scenarioaction";

	public final static String TYPEPROPERTY_OBJECT = "typeproperty";

	public final static String REPORTDATA_OBJECT = "reportdata";

	public final static String TREE_DEFENITION_SCRIPT_OBJECT = "treedefinitionscript";

	public static final Set<String> TYPES = Collections.unmodifiableSet(new HashSet<>(Arrays.asList( //
			SERVICE_OBJECT, //
			LAYER_OBJECT, //
			SCENARIO_ACTION_OBJECT, //
			TYPEPROPERTY_OBJECT, //
			REPORTDATA_OBJECT,//
			TREE_DEFENITION_SCRIPT_OBJECT)));

	private Map<String, Map> props;

	@Override abstract String getType();

	CompareObject(String name, Map<String, Map> props) {
		super(name);
		this.props = props;
	}

	@Override public Map<String, String> getData() {
		Map<String, String> data = new HashMap<>();
		Map<String, Map> props = getProps();
		if (props != null) {
			for (String s : getDataSource()) {
				data.put(s, String.valueOf(props.get(s)));
			}
		}
		return data;
	}

	private Map getProps() {
		return props;
	}

	public Set<String> getDataSource() {
		return null;
	}
}
