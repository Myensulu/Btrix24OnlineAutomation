package com.cybertek.pages.login_navigation;

import com.cybertek.utilities.Pages;
import com.cybertek.utilities.TestBase;
import com.cybertek.utilities.ExcelUtil;
import com.cybertek.utilities.Pages;
import com.cybertek.utilities.TestBase;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.util.Arrays;

public class DDTLoginTests extends TestBase {

    @Test(dataProvider = "credentials_info") // dataprovider comes from testng
    public void loginTestWithDataProvider( String username, String password) throws SkipException{
        //Pages page = new Pages();
        Pages page = new Pages();
        extentLogger = report.createTest("Data Driver Testing with excel");

        page.loginPage().login(username, password);
        String actualTitle =driver.getTitle();
        String expectedTitle = "Portal";
        String expectedTitle2 = "EULA violation";
        boolean b1 = actualTitle.equals(expectedTitle);
        boolean b2= actualTitle.equals(expectedTitle2);
        if(b1 || b2){
            extentLogger.pass("Login as "+username+" successful");
        }else
            extentLogger.pass("Login as "+username+" not successful");

    }
    @DataProvider(name="credentials_info")
    public Object[][] credentials(){
        ExcelUtil btrix = new ExcelUtil("src/test/resources/BtrixCredentials.xlsx", "Sheet1");
        return btrix.getDataArray();
    }
}
