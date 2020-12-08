package testRunners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;


@RunWith(Cucumber.class)
@CucumberOptions(features="RyanairFlightbooking/src/test/resources/feature/booking.feature", glue= {"stepDefination"}
,monochrome=true ,
plugin= {"pretty" , "html:target/HtmlReports"})

public class runner {

}

