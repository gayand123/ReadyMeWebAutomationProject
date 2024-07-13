package com.readyme.util;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import org.sikuli.script.FindFailed;
import org.sikuli.script.Screen;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestBase {

    public static WebDriver driver;  // initialize the  driver
    public static List<String> browserTabs;

    @BeforeSuite
    public void beforeSuite() {
        System.setProperty("webdriver.chrome.driver", "../ReadyMeWebAutomationProject/driver/chromedriver.exe");
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        //create webdriver object
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
      //  driver = new ChromeDriver();
        // maximizing the window
        driver.manage().window().maximize();
       // driver.get(DomainConstants.BASE_URL);
    }


 /*   public void myFirstTestCase() {
        //Chrome cdriver
        System.setProperty("webdriver.chrome.driver", "C:\\Gayan\\LexusAutomationProject\\ReadyMeWebAutomationProject\\driver\\chromedriver.exe");
        //create webdriver object
        WebDriver driver = new ChromeDriver();
        // maximizing the window
        driver.manage().window().maximize();
        driver.get(DomainConstants.BASE_URL);

    }*/


    @AfterSuite
    public void afterSuite() {
        //    driver.close();
    }

    /*  public void userLogin() throws InterruptedException {
          driver.get(DomainConstants.BASE_URL);
          if (isElementPresent(By.xpath(Elements.btnLogin))) {
              LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
              Thread.sleep(500);
              loginPage.clickEnglishTranslateButton();
              Thread.sleep(1000);
              loginPage.clearAndTypeUserName(DomainConstants.VALID_USER_NAME);
              Thread.sleep(500);
              loginPage.clearAndTypePassword(DomainConstants.VALID_PASSWORD);
              loginPage.clickLoginButton();
              Thread.sleep(500);
          } else {
              System.out.println("User is Already Loggin");
          }
      }
  */
    public void clearAndType(WebElement txtElement, String inputText) {
        try {
            txtElement.clear();
            txtElement.sendKeys(inputText);
        } catch (Exception e) {

        }
    }

    public void click(WebElement webElement) {
        try {
            webElement.click();
        } catch (Exception E) {

        }
    }

    public boolean isElementDisplayed(WebElement webElement) {
        try {
            if (webElement.isDisplayed()) {
                System.out.println("true");
                return true;
            } else {
                System.out.println("false");
                return false;
            }
        } catch (Exception e) {
            return false;
        }


    }

    public boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getElementsName(WebElement wb) {
        return wb.getText();
    }

    public boolean isElementPresentAndEnabled(WebElement webElement) {
        try {
            if (webElement.isEnabled() && webElement.isDisplayed()) {
                System.out.println("pass");
                return true;

            } else {
                System.out.println("fail");
                return false;
            }
        } catch (Exception e) {
            System.out.println("catch");
            return false;
        }
    }

    //Explicit wait
    public boolean isAttribtuePresent(WebElement element, String attribute) {
        Boolean result = false;
        try {
            String value = element.getAttribute(attribute);
            if (value != null) {
                result = true;
            }
        } catch (Exception e) {
            System.out.println("catch");
        }
        return result;
    }

    public boolean isAttribtuePresentAndContainText(WebElement element, String attribute, String attributeValue) {
        Boolean result = false;
        try {
            String value = element.getAttribute(attribute);
            if (value != null && value.contentEquals(attributeValue)) {
                result = true;
            } else {
                System.out.println("I quit");
            }
        } catch (Exception e) {
            System.out.println("catch");
        }
        return result;
    }

    public boolean isElementEnabled(WebElement element) {
        return element.isEnabled();
    }

    public String getAttributeValue(WebElement element, String attributevalue) {
        String attibutevalue = element.getAttribute(attributevalue);
        return attibutevalue;
    }

    public String getCurrentDate() {
        DateFormat currentDate = new SimpleDateFormat("MM/dd/yyyy");
        //System.out.println(currentDate);
        Date date = new Date();
        String TodayDate = currentDate.format(date);
        return TodayDate;
    }

    //covert date format to another date format yyyy-mm-dd to mm/dd/yyyy
    public String convertDateFormat(String result) throws ParseException {
        DateFormat originalFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat targetFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = originalFormat.parse(result);
        String formattedDate = targetFormat.format(date);  // mm/dd/yyyy
        return formattedDate;
    }

    //remove time from date
    public String removeTimeFromDate(String date) {
        String result = date.split(" ")[0];
        return result;
    }

    //add comma seperator to curency
    public String addCommaSeperator(String value) {
        double amount = Double.parseDouble(value);
        DecimalFormat formatter = new DecimalFormat("#,###.00");
        String dbAmount = formatter.format(amount);
        return dbAmount;
    }

    //covert date format to another date format yyyy-mm-dd to mm/dd/yyyy
    public String removeYearAndDate(String result) throws ParseException {
        DateFormat originalFormat = new SimpleDateFormat("M/dd/yyyy");
        DateFormat targetFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = originalFormat.parse(result);
        String formattedDate = targetFormat.format(date);  // mm/dd/yyyy
        return formattedDate;
    }

    public void openNewTabInBrowser() {
        //get window handlers as list
        browserTabs = new ArrayList<String>(driver.getWindowHandles());
        //switch to new tab
        driver.switchTo().window(browserTabs.get(1));

    }

    public void switchToPreviousTabInBrowser() {
        //then close tab and get back
        // System.out.println("unicorn" + driver.getTitle());
        driver.close();
        driver.switchTo().window(browserTabs.get(0));
        System.out.println(driver.switchTo().window(browserTabs.get(0)).getTitle());
    }

   public void imageFindAndClick(String imagePath) throws FindFailed {
        Screen screen = new Screen();
        screen.find(imagePath).click();
    }

    public void imageFindAndEnterValue(String imagePath, String text) throws FindFailed {
        Screen screen = new Screen();
        screen.find(imagePath).click();
        screen.type(text);
    }

}
