package com.zalinius.jishoapi.data;

import java.util.List;

public class ApiResponse {
	
	private ResponseMeta meta;
	private List<Entry> data;
	
	public ApiResponse() {
	}

	public ResponseMeta getMeta() {
		return meta;
	}

	public void setMeta(ResponseMeta meta) {
		this.meta = meta;
	}

	public List<Entry> getData() {
		return data;
	}

	public void setData(List<Entry> data) {
		this.data = data;
	}
	
	public boolean isEmpty() {
		return data.isEmpty();
	}
	
}
