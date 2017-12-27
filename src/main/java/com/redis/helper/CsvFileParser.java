package com.redis.helper;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Component;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.redis.model.Company;

@Component
public class CsvFileParser {

	
	public List<Company> parseCompanyCSV() throws IOException {
		Reader reader = Files.newBufferedReader(Paths.get("/home/abhimanyuk/Downloads/NYSE.csv"));
		CsvToBean<Company> csvToBean = new CsvToBeanBuilder<Company>(reader).withType(Company.class)
				.withIgnoreLeadingWhiteSpace(true).build();
		List<Company> companyList = csvToBean.parse();
		return companyList;

	}
}
