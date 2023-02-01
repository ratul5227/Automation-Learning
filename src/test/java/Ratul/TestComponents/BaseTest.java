package Ratul.TestComponents;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import Ratul.SeleniumTestNGFrameworkDesign.PageObject.loginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
	public WebDriver driver;
	public loginPage loginPageObj;
	
	public WebDriver initializeDriver() throws IOException {
		
		//properties class
		Properties prop = new Properties();//Properties is a subclass of Hashtable. It is used to maintain a list of values
		//in which the key is a string and the value is also a string. it can be used to store and retrieve string type data
		//from the properties file. 
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Ratul\\resources\\GlobalData.properties");
		//System.getProperty("user.dir") using this we are geting the local system project path so that when its run in another machine
		//then it will get that system project path.
		//which is C:\\Users\\ACER\\eclipse-workspace\\SeleniumTestNGFrameworkDesign
		
		prop.load(fis); //as this load method take Input stream only thats why create input stream of properties file location
		
		//using Java ternary operator instead of if else
		
		String BrowserName = System.getProperty("Browser")!=null ? System.getProperty("Browser"): prop.getProperty("Browser");
		// we can get system veriable using  System.getProperty
		
		//String BrowserName = prop.getProperty("Browser"); //Getting the value of global variable browser 
		
		if(BrowserName.contains("Chrome"))
		{
		ChromeOptions options = new ChromeOptions(); //we are using chrome options class to run our test in headless mode.
			
		WebDriverManager.chromedriver().setup(); //using this the appropriate chromedriver will automatically download in this project
		
		if (BrowserName.contains("headless")) 
		{
		options.addArguments("headless"); //using this we are sending headless to the options object sothat chrome have the knowledge
		//to run the test in headless mode. if at runtime we don't gove headless then the object will not have any argument then
		//it will run in normal mode
		}
		
		driver = new ChromeDriver(options);
	
		driver.manage().window().setSize(new Dimension(1440,900)); //using this opening browser in full screen in headless mode as well as normal mpde.
		}
		else if(BrowserName.equalsIgnoreCase("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			//Firefox browser invoke code
		}
		else if(BrowserName.equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			//Edge browser invoke code
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		return driver;
	}
	
	@BeforeMethod (alwaysRun=true)
	public loginPage launchApplication() throws IOException {
		driver = initializeDriver();
		loginPageObj = new loginPage(driver);
		loginPageObj.goTo();
		return loginPageObj;
		
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}
	
	
public List<HashMap<String, String>> getJsonDataToMap(String filepath) throws IOException {
		
		//read json to string
		String jsonContent = FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);
		
		//String to HashMap jackson Databind
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String,String>> data =  mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		return data;
	}


public String getScreenShot (String testcaseName, WebDriver Driver) throws IOException {
	TakesScreenshot ss = (TakesScreenshot)driver;
	File sourse = ss.getScreenshotAs(OutputType.FILE);
	File StoreLocation = new File(System.getProperty("user.dir")+"//reports//"+ testcaseName+".png");
	FileUtils.copyFile(sourse, StoreLocation);
	return System.getProperty("user.dir")+"//reports//"+ testcaseName+".png" ; 
	
}

}
