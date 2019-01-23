package com.example.ProjectApi.util.entity;

import com.example.ProjectApi.util.AttributeCheck;

public class BetweenGenerator {
	final AttributeCheck attributeCheck = new AttributeCheck();
	private String betweenSql;

	public String getBetweenSql() {
		if (attributeCheck.stringsNotNull(betweenSql)) {
			return betweenSql;
		}
		return "";
	}

	public void setBetweenSql(String betweenField, String start, String end) {
		if (attributeCheck.stringsNotNull(start) && attributeCheck.stringsNotNull(end)) {
			betweenSql = betweenField + " BETWEEN '" + start + "' AND '" + end + "'";
		} else if (attributeCheck.stringsNotNull(start)) {
			betweenSql = betweenField + " >= '" + start + "'";
		} else {
			betweenSql = betweenField + " <= '" + end + "'";
		}
	}

}
