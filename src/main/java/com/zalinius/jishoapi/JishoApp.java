package com.zalinius.jishoapi;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.boot.web.client.RestTemplateBuilder;

import com.zalinius.jishoapi.data.Entry;

public class JishoApp {

	public static void main(String[] args) {
		String endpoint = "words";
		String searchQuery = "#jlpt-n5";

		JishoDataSource jishoDataSource = new JishoDataSource(new RestTemplateBuilder());
		
		List<Entry> alln5 = jishoDataSource.getAllEntries(endpoint, searchQuery);
		
		System.out.println("There are " + alln5.size() + " jlpt-n5 vocab items on Jisho");
		System.out.println();

		Predicate<Entry> learnedFromWanikani = e -> e.isOnWanikani() && !e.usuallyWrittenUsingKanaAlone();
		
		List<Entry> wanikaniN5 = alln5.stream().filter(learnedFromWanikani).sorted((a,b) -> a.wanikaniLevel() - b.wanikaniLevel()).collect(Collectors.toList());
	    System.out.println("On Wanikani: " + wanikaniN5.size());
	    System.out.println(wanikaniN5);
	    System.out.println("Highest level: " + wanikaniN5.stream().map(e -> e.wanikaniLevel()).reduce(0, Math::max));
	    System.out.println("There are " + wanikaniN5.stream().filter(e -> e.wanikaniLevel() > 20).count());
		System.out.println();

		List<Entry> nonWanikaniN5 = alln5.stream().filter(learnedFromWanikani.negate()).collect(Collectors.toList());
	    System.out.println("Non Wanikani: " + nonWanikaniN5.size());
	    System.out.println(nonWanikaniN5);
	}

}
