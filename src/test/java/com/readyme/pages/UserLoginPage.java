package com.readyme.pages;

import com.readyme.util.Elements;
import com.readyme.util.TestBase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserLoginPage extends TestBase {
    @FindBy(xpath = Elements.btnLearnerLogin)
    private WebElement btnLearnerLogin;
    @FindBy(xpath = Elements.txtPhoneNumber)
    private WebElement txtPhoneNumber;
    @FindBy(xpath = Elements.txtPassword)
    private WebElement txtPassword;

    @FindBy(xpath = Elements.btnLogin)
    private WebElement btnLogin;
    @FindBy(xpath = Elements.lblHome)
    private WebElement lblHome;

    public void clickLearnerLogin() {
        click(btnLearnerLogin);
    }

    public void enterUserPhoneNumber() {
        clearAndType(txtPhoneNumber,"711013726");
    }
    public void enterUserPassword() {
        clearAndType(txtPassword,"Gayan@123abc");
    }
    public void clickUserLogin() {
        click(btnLogin);
    }
    public String  getTextHome(){
      return   getElementsName(lblHome);
    }
}
