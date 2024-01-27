package qa.testcomponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import qa.pageobject.LandingPage;


public class BaseTest {

	public  WebDriver driver;
	public LandingPage landingPage;
	
	public WebDriver initilizeBrowser() throws IOException
	{
		Properties prop=new Properties();
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"//src//main//java//qa//resources//GlobalData.properties");
		prop.load(fis);
		String browserName=System.getProperty("browser")!=null ?System.getProperty("browser"): prop.getProperty("browser");
		//String browserName=prop.getProperty("browser");
		if(browserName.contains("chrome"))
		{
			ChromeOptions option=new ChromeOptions();
			if(browserName.contains("headless"))
			{
			  option.addArguments("headless");
			}
	    driver = new ChromeDriver(option);
	    driver.manage().window().setSize(new Dimension(1440,900));
	    
		}
		else if (browserName.equalsIgnoreCase("firefox"))
		{
			driver=new FirefoxDriver();
		}
		else if (browserName.equalsIgnoreCase("edge"))
		{
			driver=new EdgeDriver();
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public LandingPage launchApplication() throws IOException
	{
		driver=initilizeBrowser();
	    landingPage=new LandingPage(driver);
	    landingPage.goTo();
		return landingPage;
	}
	
	
	@AfterMethod(alwaysRun=true)
	public void tearDown()
	{
		driver.quit();
	}
	
	public List<HashMap<String, String>> getJSONDataToMap(String filepath) throws IOException
	{
		// read JSON to String 
		String jsonContent=FileUtils.readFileToString(new File(filepath) , StandardCharsets.UTF_8);
	
		// Convert To HashMap- Using Jackson batdind dependencies 
		
		ObjectMapper mapper= new ObjectMapper();
		  List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>() {
	      });
		  return data;
		  
	// {map,map}
		  //System.getProperty("user.dir")+"//src//test//java//qa//data//PurchaseOrder.json"
	
	}
	
	public String getScreenShot(String testCaseName, WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File souce=ts.getScreenshotAs(OutputType.FILE);
		File file= new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
		FileUtils.copyFile(souce, file);
		return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
	}
	
}
