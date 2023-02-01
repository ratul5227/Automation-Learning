package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features="src/test/java/cucumber",glue="Ratul.stepDefinition",monochrome=true,
tags = "@ErrorValidation",plugin= {"html:target/cucumber.html"})
//here feature attribute is used for give cucumber feature file location.
//here glue attribute is used for cucumber stepDefinition location.
//monochrome is used for to get cucumber file in readable form.
//Plugin is used for report here html is the report type and after : is report store location

//AbstractTestNGCucumberTests this class we needs to extends to run TestNG related data in cucumber

public class TestNGTestRunnerForCucumber extends AbstractTestNGCucumberTests{

}

//To run cucumber using maven command 
//We needs to create profile in pom file and instead of <suiteXmlFiles> <suiteXmlFile> xml file<suiteXmlFile><suiteXmlFiles>
//we needs to add <includes><include>**/filename.jave</include></includes>
// then we can run from terminal using mvn test -PCucumber
