package com.zalinius.jishoapi.data;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class SenseTest {
	
	@Test
	public void jsonInjection_withSampleSenseData_fillsKanaUsuallyTag() throws JsonMappingException, JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		String sampleUserData = sampleSenseData();

		Sense sense = objectMapper.readValue(sampleUserData, Sense.class);
		
		assertTrue(sense.usuallyWrittenUsingKanaAlone());
	}
	
	private static String sampleSenseData() {
		return 
				  "{\n"
				+ "\"english_definitions\": [\n"
				+ "\"hello\",\n"
				+ "\"good day\",\n"
				+ "\"good afternoon\"\n"
				+ "],\n"
				+ "\"parts_of_speech\": [],\n"
				+ "\"links\": [],\n"
				+ "\"tags\": [\n"
				+ "\"Usually written using kana alone\"\n"
				+ "],\n"
				+ "\"restrictions\": [],\n"
				+ "\"see_also\": [],\n"
				+ "\"antonyms\": [],\n"
				+ "\"source\": [],\n"
				+ "\"info\": [\n"
				+ "\"は is pronounced as わ; used during daytime\"\n"
				+ "]\n"
				+ "}\n"
				;
	}

}
