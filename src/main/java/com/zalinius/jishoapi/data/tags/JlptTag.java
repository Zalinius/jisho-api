package com.zalinius.jishoapi.data.tags;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum JlptTag {
	@JsonProperty("jlpt-n1")
	JLPT_N1,
	@JsonProperty("jlpt-n2")
	JLPT_N2,
	@JsonProperty("jlpt-n3")
	JLPT_N3,
	@JsonProperty("jlpt-n4")
	JLPT_N4,
	@JsonProperty("jlpt-n5")
	JLPT_N5;
}
