/** 
* @author 作者 Daniel
* @date 2018年6月19日 下午2:09:26 
* @version 
* @description:
*/
package com.example.ProjectApi.util.entity;

import com.example.ProjectApi.util.AttributeCheck;

public class OrderGenerator {
	private AttributeCheck attributeCheck = new AttributeCheck();
	private boolean asc;
	private String order;
	private String orderSql = "";

	public boolean isAsc() {
		return asc;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public String getOrderSql() {
		if (attributeCheck.stringsNotNull(order)) {
			orderSql = " ORDER BY " + order + " " + (asc == true ? "ASC" : "DESC");
		}
		return orderSql;
	}

	public void setOrderSql(String orderSql) {
		this.orderSql = orderSql;
	}
}
