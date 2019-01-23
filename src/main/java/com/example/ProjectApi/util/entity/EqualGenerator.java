package com.example.ProjectApi.util.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.ProjectApi.util.AttributeCheck;

public class EqualGenerator {
	final AttributeCheck attributeCheck = new AttributeCheck();
	private HashMap<String, String> equalHashMap;
	private String equalOrSql = "";
	private String equalOrSqlString; // 組equalOrSql string使用
	private String equalSql;

	public void setEqualHashMap(String column, String value) {
		if (!attributeCheck.objectNotNull(equalHashMap)) {
			equalHashMap = new HashMap<>();
		}
		if (attributeCheck.stringsNotNull(column) && attributeCheck.stringsNotNull(value)) {
			equalHashMap.put(column, value);
		}
	}

	public void setEqualOrSql(String column, List<String> valueList) {
		equalOrSqlString = "";
		if (attributeCheck.stringsNotNull(column) && attributeCheck.objectNotNull(valueList)) {
			final int valueListSize = valueList.size();
			for (int i = 0; i < valueListSize; i++) {
				if (i + 1 != valueListSize) { // 非最後一筆 OR
					equalOrSqlString += column + " = '" + valueList.get(i) + "' OR ";
				} else { // 最後一筆 AND
					equalOrSqlString += column + " = '" + valueList.get(i) + "' ";
				}
			}
			if (attributeCheck.stringsNotNull(equalOrSqlString)) {
				equalOrSql += "(" + equalOrSqlString + ") AND ";
			}
		}
	}

	public String getEqualSql() {
		equalSql = "";
		if (attributeCheck.objectNotNull(getEqualHashMap()) && getEqualHashMap().size() > 0) {
			for (Map.Entry<String, String> entry : getEqualHashMap().entrySet()) {
				equalSql += entry.getKey() + " = '" + entry.getValue() + "' AND ";
			}
		}
		if (attributeCheck.stringsNotNull(equalOrSql)) {
			equalSql += equalOrSql;
		}
		if (attributeCheck.stringsNotNull(equalSql)) {
			equalSql = " " + equalSql.substring(0, equalSql.length() - 4) + " ";
		}
		return equalSql;
	}

	public HashMap<String, String> getEqualHashMap() {
		return equalHashMap;
	}

}
