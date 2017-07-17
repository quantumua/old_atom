package com.betamedia.atom.core.api.crm.form.entities;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

public class QuestionScoreRead {
	 private static final Logger logger = LogManager.getLogger(QuestionScoreRead.class);
	 private static final String INITIALIZATION_ERROR_MESSAGE = "Failed to read Scoring table";
	 private String scoreQuestionsPath = "/testslibrary/src/main/resources/data/accountAdditionalDetailsData.csv";
	 private List<ScoreWebData> scorePerQuestion = null;
	
	 public void readCsv(){
		 			 
		 HeaderColumnNameMappingStrategy<ScoreWebData> obtaining = new HeaderColumnNameMappingStrategy<>();
		 obtaining.setType(ScoreWebData.class);
		 CsvToBean<ScoreWebData> csvToBean = new CsvToBean<>();
		 try (InputStream resourceInputStream = new ClassPathResource(scoreQuestionsPath ).getInputStream();) {
			 scorePerQuestion = csvToBean.parse(obtaining, new CSVReader(new InputStreamReader(resourceInputStream)));
		 }catch (IOException e) {
	            logger.error(INITIALIZATION_ERROR_MESSAGE, e);
	            throw new RuntimeException(INITIALIZATION_ERROR_MESSAGE);
	        }
	 }
		 

}
