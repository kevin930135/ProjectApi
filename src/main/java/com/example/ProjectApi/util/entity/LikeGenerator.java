/** 
* @author 作者 Daniel
* @date 2018年6月19日 下午2:09:18 
* @version 
* @description:
*/
package com.example.ProjectApi.util.entity;

import java.util.ArrayList;
import java.util.List;

import com.example.ProjectApi.util.AttributeCheck;

public class LikeGenerator {
	private AttributeCheck attributeCheck = new AttributeCheck();
	private String like;
	private String likeSql;
	private List<String> likeFieldList = new ArrayList<>(); // 搜尋欄位 List

	public String getLike() {
		return like != null ? like : "";
	}

	public void setLike(String like) {
		this.like = like;
	}

	public String getLikeSql() {
		likeSql = "";
		if (attributeCheck.listNotNull_Zero(likeFieldList)) {
			for (int i = 0; i < likeFieldList.size(); i++) {
				likeSql += likeFieldList.get(i) + " LIKE '%" + this.getLike() + "%' OR ";
			}
			likeSql = " (" + likeSql.substring(0, likeSql.length() - 3) + ") ";
		}
		return likeSql;
	}

	public void setLikeSql(String likeSql) {
		this.likeSql = likeSql;
	}

	public List<String> getLikeFieldList() {
		return likeFieldList;
	}

	public void setLikeFieldList(List<String> likeFieldList) {
		this.likeFieldList = likeFieldList;
	}
}
