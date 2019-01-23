package com.example.ProjectApi.util.entity;

import com.example.ProjectApi.util.AttributeCheck;

public class WhereGenerator {
	private final AttributeCheck attributeCheck = new AttributeCheck();
	private String whereSql = "";
	private LikeGenerator likeGenerator = new LikeGenerator();
	private EqualGenerator equalGenerator = new EqualGenerator();
	private BetweenGenerator betweenGenerator = new BetweenGenerator();

	public String getWhereSql(boolean hasWhere) {
		final String likeSql = likeGenerator.getLikeSql();
		final String equalSql = equalGenerator.getEqualSql();
		final String betweenSql = betweenGenerator.getBetweenSql();
		final String where = hasWhere ? " WHERE " : " AND ";
		if (attributeCheck.stringsNotNull(equalSql, likeSql, betweenSql)) {
			whereSql = equalSql + " AND " + likeSql + " AND " + betweenSql;
		} else if (attributeCheck.stringsNotNull(equalSql, likeSql)) {
			whereSql = equalSql + " AND " + likeSql;
		} else if (attributeCheck.stringsNotNull(equalSql, betweenSql)) {
			whereSql = equalSql + " AND " + betweenSql;
		} else if (attributeCheck.stringsNotNull(likeSql, betweenSql)) {
			whereSql = likeSql + " AND " + betweenSql;
		} else if (attributeCheck.stringsNotNull(equalSql)) {
			whereSql = equalSql;
		} else if (attributeCheck.stringsNotNull(likeSql)) {
			whereSql = likeSql;
		} else if (attributeCheck.stringsNotNull(betweenSql)) {
			whereSql = betweenSql;
		}
		whereSql = where + whereSql;
		return whereSql;
	}

	public LikeGenerator getLikeGenerator() {
		return likeGenerator;
	}

	public void setLikeGenerator(LikeGenerator likeGenerator) {
		this.likeGenerator = likeGenerator;
	}

	public EqualGenerator getEqualGenerator() {
		return equalGenerator;
	}

	public void setEqualGenerator(EqualGenerator equalGenerator) {
		this.equalGenerator = equalGenerator;
	}

	public BetweenGenerator getBetweenGenerator() {
		return betweenGenerator;
	}

	public void setBetweenGenerator(BetweenGenerator betweenGenerator) {
		this.betweenGenerator = betweenGenerator;
	}

}
