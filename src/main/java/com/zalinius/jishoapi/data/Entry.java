package com.zalinius.jishoapi.data;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.zalinius.jishoapi.data.tags.JlptTag;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Entry {
	private String slug;
	
	private boolean isCommon;
	private Set<String> tags;
	private Set<JlptTag> jlpt;
	private List<Sense> senses;
	
	
	
	public String getSlug() {
		return slug;
	}
	public void setSlug(String slug) {
		this.slug = slug;
	}

	public boolean isCommon() {
		return isCommon;
	}
	public void setCommon(boolean isCommon) {
		this.isCommon = isCommon;
	}

	public Set<String> getTags() {
		return tags;
	}
	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public Set<JlptTag> getJlpt() {
		return jlpt;
	}
	public void setJlpt(Set<JlptTag> jlpt) {
		this.jlpt = jlpt;
	}
	
	public List<Sense> getSenses() {
		return senses;
	}
	public void setSenses(List<Sense> senses) {
		this.senses = senses;
	}

	
	public boolean isOnWanikani() {
		return tags.stream().anyMatch(s -> s.contains("wanikani"));
	}
	public int wanikaniLevel() {
		if(!isOnWanikani()) {
			throw new RuntimeException("Not on wanikani, thus no level");
		}
		
		List<Integer> wanikaniTags = tags.stream().filter(s -> s.contains("wanikani")).map(tag -> tag.substring("wanikani".length())).map(tag -> Integer.parseInt(tag)).sorted().collect(Collectors.toList());
		return wanikaniTags.get(0);
	}
	
	public boolean usuallyWrittenUsingKanaAlone() {
		return senses.stream().anyMatch(Sense::usuallyWrittenUsingKanaAlone);
	}

	@Override
	public String toString() {
		return slug;			
	}
}
