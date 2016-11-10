package com.travelzen.mongo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.bson.types.ObjectId;

public class KeyConverter {

	public static List<String> toStringList(Collection<ObjectId> list) {
		if (list == null) {
			return null;
		}
		List<String> result = new ArrayList<>(list.size());
		for (ObjectId oid : list) {
			result.add(toStringKey(oid));
		}
		return result;
	}

	public static List<ObjectId> toObjectIdList(Collection<String> list) {
		if (list == null) {
			return null;
		}
		List<ObjectId> result = new ArrayList<>(list.size());
		for (String s : list) {
			result.add(toObjectId(s));
		}
		return result;
	}

	public static String toStringKey(ObjectId id) {
		return id == null ? null : id.toString();
	}

	public static ObjectId toObjectId(String key) {
		if (key == null || key.isEmpty()) {
			return null;
		}
		return new ObjectId(key);
	}

}
