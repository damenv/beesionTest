package com.beesion.qa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.sound.midi.SysexMessage;

public class LoginTests {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        String seleniumDriver = System.getProperty("seleniumDriver", "chrome");
        String operativeSystem = System.getProperty("operativeSystem", "chrome");

        if(operativeSystem == "windows"){
            setWindowsDrivers(seleniumDriver);
        } else {
            setMacOSDrivers(seleniumDriver);
        }

        driver.get("https://www.xoom.com/sign-in-xoom");
    }

    @AfterMethod
    public void cleanup(){
        System.out.println("Starting Cleanup");
        System.out.println("Closing browser");
        driver.close();
    }

    @Test
    public void loginTest() {
        System.out.println("Testing login to xoom's website");
        driver.manage().window().maximize();

        //Get Elements required for login
        System.out.println("Retrieving web elements: username, password and login button");

        WebElement username = driver.findElement(By.id("username"));
        System.out.println("Typing username");
        username.sendKeys("testaccount@xoom.com");

        WebElement password = driver.findElement(By.id("password"));
        System.out.println("Typing password");
        password.sendKeys("asdf1234");

        WebElement loginButton = driver.findElement(By.id("login"));
        System.out.println("Logging in");
        sleep(5000);

        //Validations
        String expectedUrl = "https://www.xoom.com/myaccount";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Actual Url is not the same as expected Url.");

        WebElement welcomeMessage = driver.findElement(By.xpath("/html//div[@id='mainContent']/div[@class='layout-contained']/div[@class='container-fluid']//div[@class='welcomeWrapContent']/h1[@class='heading-1']"));

        String actualMessage = welcomeMessage.getText();
        Assert.assertTrue(actualMessage.contains("Welcome,"), "Welcome message does not include the expected text \"Welcome,\". \n Actual Message: " + actualMessage);
    }

    @Test
    public void failedLoginTest() {
        System.out.println("Testing failed login to xoom's website");
        driver.manage().window().maximize();

        //Get Elements required for login
        System.out.println("Retrieving web elements: username, password and login button");

        WebElement username = driver.findElement(By.id("username"));
        System.out.println("Typing username");
        username.sendKeys("testaccount@xoom.com");

        WebElement password = driver.findElement(By.id("password"));
        System.out.println("Typing password");
        password.sendKeys("asdf1234");

        WebElement loginButton = driver.findElement(By.id("login"));
        System.out.println("Logging in");
        sleep(5000);

        //Validations
        String expectedUrl = "https://www.xoom.com/sign-in-xoom";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Actual Url is not the same as expected Url.");
    }

    private void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void setMacOSDrivers(String seleniumDriver){
        switch(seleniumDriver){
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver");
                driver = new FirefoxDriver();
                break;
            default:
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
                driver = new ChromeDriver();
                break;

        }
    }

    private void setWindowsDrivers(String seleniumDriver){
        switch(seleniumDriver){
            case "firefox":
                System.setProperty("webdriver.gecko.driver", "src/main/resources/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            default:
                System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
                driver = new ChromeDriver();
                break;
        }
    }
}
