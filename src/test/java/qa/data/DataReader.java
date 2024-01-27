package qa.data;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.fasterxml.jackson.core.type.TypeReference;
public class DataReader
{

	public List<HashMap<String, String>> getJSONDataToMap() throws IOException
	{
		// read JSON to String 
		String jsonContent=FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//src//test//java//qa//data//PurchaseOrder.json") , StandardCharsets.UTF_8);
	
		// Convert To HashMap- Using Jackson batdind dependencies 
		
		ObjectMapper mapper= new ObjectMapper();
		  List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
	      });
		  return data;
		  
	// {map,map}
	
	
	}
}
