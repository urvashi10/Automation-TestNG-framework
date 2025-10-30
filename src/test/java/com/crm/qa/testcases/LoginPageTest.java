package com.crm.qa.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Test class for Login Page functionality
 * Tests login page elements and validations
 */
public class LoginPageTest extends CommonTest {
    private static final Logger logger = LogManager.getLogger(LoginPageTest.class);
    private SoftAssert softAssert;

    public LoginPageTest() {
        super();
        softAssert = new SoftAssert();
    }

    /**
     * Verify the title of the login page
     * Expected title: "CRMPRO"
     */
    @Test(priority = 1, description = "Verify login page title is correct")
    public void verifyLoginPageTitle() {
        logger.info("Starting login page title verification test");
        String title = loginPage.validateLoginPageTitle();
        logger.info("Login Page Title: {}", title);
        Assert.assertEquals(title, "CRMPRO", 
            "Login page title mismatch. Expected: 'CRMPRO', Actual: '" + title + "'");
        logger.info("Login page title verification completed successfully");
    }

    /**
     * Verify that the PHP logo is displayed on the login page
     * Also checks if the logo is properly aligned and visible
     */
    @Test(priority = 2, description = "Verify PHP logo visibility and properties")
    public void verifyPhpLogoIsDisplayed() {
        logger.info("Starting PHP logo verification test");
        try {
            boolean isLogoDisplayed = loginPage.validatePhpImage();
            softAssert.assertTrue(isLogoDisplayed, "PHP logo is not displayed on login page");
            
            // Additional logo validations can be added here using softAssert
            // For example: logo size, position, etc.
            
            softAssert.assertAll();
            logger.info("PHP logo verification completed successfully");
        } catch (Exception e) {
            logger.error("Error during PHP logo verification: {}", e.getMessage());
            throw e;
        }
    }

    /**
     * Verify error message for invalid login attempts
     * This test uses invalid credentials to ensure proper error handling
     */
    @Test(priority = 3, description = "Verify error message for invalid login")
    public void verifyInvalidLoginError() {
        logger.info("Starting invalid login test");
        try {
            loginPage.enterUsername("invalid_user");
            loginPage.enterPassword("invalid_pass");
            loginPage.clickLoginButton();
            
            String errorMessage = loginPage.getErrorMessage();
            Assert.assertNotNull(errorMessage, "Error message is not displayed for invalid login");
            Assert.assertTrue(errorMessage.contains("Invalid"), 
                "Error message should indicate invalid credentials");
            
            logger.info("Invalid login test completed successfully");
        } catch (Exception e) {
            logger.error("Error during invalid login test: {}", e.getMessage());
            throw e;
        }
    }
}
