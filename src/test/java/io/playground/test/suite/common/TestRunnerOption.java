package io.playground.test.suite.common;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.testng.xml.XmlSuite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Data
@Builder
@Setter(AccessLevel.NONE)
public class TestRunnerOption {
    
    @Builder.Default
    private XmlSuite.ParallelMode parallelMode = XmlSuite.ParallelMode.NONE;

    private boolean dataProviderParallel;

    @Builder.Default
    private List<String> testFilter = new ArrayList<>();

    @Builder.Default
    private int threadCount = Thread.activeCount() > 2 ? Thread.activeCount() - 2 : Thread.activeCount();
    
    public XmlSuite.ParallelMode getParallelMode() {
        XmlSuite.ParallelMode systemMode = XmlSuite.ParallelMode.getValidParallel(System.getProperty("tests.parallel", "none"));
        if (!systemMode.equals(XmlSuite.ParallelMode.NONE)) {
            return systemMode;
        } else {
            return parallelMode;
        }
    }

    public boolean isDataProviderParallel() {
        boolean systemDataParallel = Boolean.parseBoolean(System.getProperty("tests.dataParallel"));
        if (systemDataParallel) {
            return true;
        } else {
            return dataProviderParallel;
        }
    }
    
    public List<String> getTestFilter() {
        String[] systemFilterArray = System.getProperty("tests", "").split(",");
        List<String> systemTestFilter = systemFilterArray.length > 0 && !systemFilterArray[0].isEmpty() 
                ? Arrays.asList(systemFilterArray) 
                : List.of();
        if (!systemTestFilter.isEmpty()) {
            return List.copyOf(systemTestFilter);
        } else {
            return List.copyOf(testFilter);
        }
    }
}
