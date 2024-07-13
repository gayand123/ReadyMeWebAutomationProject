package com.readyme.tests;

import com.readyme.pages.UserLoginPage;
import com.readyme.util.DomainConstants;
import com.readyme.util.TestBase;
import org.openqa.selenium.support.PageFactory;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.testng.Assert;
import org.testng.annotations.Test;

public class UserLoginTest extends TestBase {
    @Test
    public void testValidUserLogin() throws InterruptedException, FindFailed {
        driver.get(DomainConstants.BASE_URL);
        Thread.sleep(5000);
        imageFindAndEnterValue(DomainConstants.ImagePathOftxtuserName, DomainConstants.userName);
        imageFindAndEnterValue(DomainConstants.ImagePathOftxtpassword, DomainConstants.password);
        imageFindAndClick(DomainConstants.ImagePathOfbtnClick);
        // driver.switchTo().alert().sendKeys("axis-fe-devs");
        //   driver.switchTo().alert().sendKeys("H2a3aycwc$7mCo<llOZ@");
        //   driver.switchTo().alert().accept();
        UserLoginPage loginPage = PageFactory.initElements(driver, UserLoginPage.class);
        Thread.sleep(5000);
        loginPage.clickLearnerLogin();
        Thread.sleep(5000);
        loginPage.enterUserPhoneNumber();
        loginPage.enterUserPassword();
        loginPage.clickUserLogin();
        Thread.sleep(10000);
        String actualText = loginPage.getTextHome();
        Assert.assertEquals(actualText,"Home");
    }
}
