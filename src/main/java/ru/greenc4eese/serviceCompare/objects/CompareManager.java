package ru.greenc4eese.serviceCompare.objects;

import java.util.HashMap;
import java.util.Map;

public class CompareManager {
	private static final CompareManager instance = new CompareManager();
	private final Map<String, CommonCompareObject> allObjects = new HashMap<>();

	private CompareManager() {
	}

	public static CompareManager getInstance() {
		return instance;
	}

	public Map<String, CommonCompareObject> getAllObjects() {
		return allObjects;
	}

	public void addItem(Map.Entry<String, Map> i) {
		Map tmpVal = i.getValue();
		String systemKey = i.getKey();
		String code = String.valueOf(tmpVal.get("key")); //userkey
		String type = String.valueOf(tmpVal.get("type"));
		Map<String, Map> props = (Map<String, Map>) tmpVal.get("props");

		String name = null;
		if (props != null) {
			name = String.valueOf(props.get("name"));
		}

		Map<String, Map> rels = (Map<String, Map>) tmpVal.get("rels");

		if (ComparePath.TYPES.contains(type)) {
			//syskey, prop->name; code - не всегда :
			ComparePath cp = new ComparePath(name);
			CommonCompareObject cco = allObjects.get(systemKey);

			if (cco != null) {
				cp.setPath(cco.getPath());
			}

			allObjects.put(systemKey, cp);

			if (rels != null) {
				//syskey, parent
				for (Map<String, String> value : rels.values()) {
					String relSystemKey = String.valueOf(value.get("id"));

					cco = allObjects.get(relSystemKey);
					if (cco == null) {
						//link related object to parent-level object - cp
						ComparePath relCp = new ComparePath(null, cp);
						allObjects.put(relSystemKey, relCp);
					} else {
						cco.setPath(cp);
					}
				}
			}
		}

		if (CompareObject.TYPES.contains(type)) {
			CompareObject co = new CompareObject(code, props);
			CommonCompareObject cco = allObjects.get(systemKey);

			if (cco != null) {
				co.setPath(cco.getPath());
			}
			allObjects.put(systemKey, co);
		}
	}

	public void reset() {
		allObjects.clear();
	}
}
