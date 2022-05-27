package com.zalinius.jishoapi.data;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Sense {
	
	private static final String KANA_USUALLY_TAG = "Usually written using kana alone";
	
	
	private Set<String> tags;
	
	public Sense() {
	}
	
	public Set<String> getTags() {
		return tags;
	}
 	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public boolean usuallyWrittenUsingKanaAlone() {
		return tags.contains(KANA_USUALLY_TAG);
	}
	
	@Override
	public String toString() {
		return tags.toString();
	}
}
