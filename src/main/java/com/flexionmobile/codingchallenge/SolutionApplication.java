package com.flexionmobile.codingchallenge;

import com.flexionmobile.codingchallenge.integration.IntegrationTestRunner;
import com.flexionmobile.codingchallenge.solution.IntegrationImpl;

public class SolutionApplication {
    public SolutionApplication() {
    }

    public static void main(String[] args){
        IntegrationTestRunner integrationTestRunner = new IntegrationTestRunner();
        IntegrationImpl integration = new IntegrationImpl();
        integrationTestRunner.runTests(integration);
    }
}
