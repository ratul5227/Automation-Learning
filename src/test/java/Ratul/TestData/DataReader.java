package Ratul.TestData;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class DataReader {
	
	public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {
		
		//read json to string
		String jsonContent = FileUtils.readFileToString(new File(filepath),
				StandardCharsets.UTF_8);
		
		//String to HashMap jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data =  mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		return data;
		
	//We write same method in Base Test so that we can access this method in our main test file without creating object of this class.
	}

}
