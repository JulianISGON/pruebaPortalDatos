package enlace.screenplay;

import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;


import static io.cucumber.core.options.Constants.FILTER_TAGS_PROPERTY_NAME;
import static io.cucumber.core.options.Constants.GLUE_PROPERTY_NAME;
import static io.cucumber.core.options.Constants.PLUGIN_PROPERTY_NAME;


@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(
        key = GLUE_PROPERTY_NAME,
        value = "enlace.screenplay.stepdefinitions"
)
@ConfigurationParameter(
        key = FILTER_TAGS_PROPERTY_NAME,
        value = "@test"
)
@ConfigurationParameter(
        key = PLUGIN_PROPERTY_NAME,
        value = "pretty, net.serenitybdd.cucumber.core.plugin.SerenityReporter"
)
public class CucumberTestSuite {
}
