package codexbuilder;

import org.junit.platform.suite.api.SelectPackages;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectPackages({
    "codexbuilder.repository",
    "codexbuilder.viewmodel"
})
public class CodexBuilderTest {
}