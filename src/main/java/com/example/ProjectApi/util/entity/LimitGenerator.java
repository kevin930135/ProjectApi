/** 
* @author 作者 Daniel
* @date 2018年6月15日 下午4:26:58 
* @version 
* @description:
*/
package com.example.ProjectApi.util.entity;

import com.example.ProjectApi.util.AttributeCheck;

public class LimitGenerator {
	private AttributeCheck attributeCheck = new AttributeCheck();
	private Integer offset;
	private Integer limit;
	private String limitSql;

	/**
	 * @return the offset
	 */
	public Integer getOffset() {
		return offset;
	}

	/**
	 * @param offset the offset to set
	 */
	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	/**
	 * @return the limit
	 */
	public Integer getLimit() {
		return limit;
	}

	/**
	 * @param limit the limit to set
	 */
	public void setLimit(Integer limit) {
		this.limit = limit;
	}

	/**
	 * @return the limitSql
	 */
	public String getLimitSql() {
		if (attributeCheck.objectNotNull(offset) && attributeCheck.objectNotNull(limit)) {
			limitSql = " LIMIT " + offset + ", " + limit;
		} else {
			limitSql = "";
		}
		return limitSql;
	}

	/**
	 * @param limitSql the limitSql to set
	 */
	public void setLimitSql(String limitSql) {
		this.limitSql = limitSql;
	}
}
