package register;


import com.github.javafaker.Faker;
import org.junit.BeforeClass;
import framework.Configuration;
import java.awt.BorderLayout;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;



public class RegisterTest {

    private static WebDriver driver;    
    WebElement signInNameFieldElement = driver.findElement(By.id("fragment-46_username"));    
    WebElement emailAdressFieldElement = driver.findElement(By.id("fragment-46_email"));
    WebElement passwordFieldElement = driver.findElement(By.id("fragment-46_password"));
    WebElement confirmPasswordFieldElement = driver.findElement(By.id("fragment-46_password2"));
    WebElement countryFieldBoxElement = driver.findElement(By.id("fragment-46_profileFieldsForm_717170147_country"));
    WebElement firstNameFieldElement = driver.findElement(By.id("fragment-46_profileFieldsForm_1835267210_first-name"));
    WebElement lastNameFieldElement = driver.findElement(By.id("fragment-46_profileFieldsForm_614460147_last-name"));
    WebElement promotionalEmailCheckBoxElement = driver.findElement(By.id("fragment-46_profileFieldsForm_237248213_oktocontact"));
    WebElement termsOfServiceCheckBoxElement = driver.findElement(By.id("fragment-46_acceptAgreement"));
    WebElement joinNowButtonElement = driver.findElement(By.cssSelector("a[class='internal-link create-account submit-button button']"));
    
    protected Faker faker = new Faker();    
    String signInNameDefaultPositiveValue = "aA1" + faker.name().firstName();
    String emailDefaultPositiveValue = faker.internet().emailAddress();
    String passwordDefaultPositiveValue = "aA1" + faker.internet().password();
    String confirmPasswordDefaultPositiveValue = "aA1" + faker.internet().password();
    String firstNameDefaultPositiveValue = faker.name().firstName();
    String lastNameDefaultPositiveValue = faker.name().lastName();
    String s = "defaultPositive";
    String e = "defaultPositive";
    String p = "defaultPositive";
    String f = "defaultPositive";
    String l = "defaultPositive";
    ArrayList<String> signInNameNegativeTestCasesList = new ArrayList<>(Arrays.asList("", "   ", "a", "ab", " abc", "...", "---", "___"));
    ArrayList<String> signInNamePositiveTestCasesList = new ArrayList<>(Arrays.asList("1234", "12345678901234567890", "abc", "aA1.-_"));
    ArrayList<String> emailNegativeTestCasesList = new ArrayList<>(Arrays.asList("@2.3", "12.3", "1@.3", "1@23", "1@2.", "1@2.3@", " test@test.com"));
    ArrayList<String> passwordNegativeTestCasesList = new ArrayList<>(Arrays.asList("a","123456","abcdefg","ABCDEFG","1234567","abcABCD","abc1234","ABC1234"));
    ArrayList<String> passwordPositiveTestCasesList = new ArrayList<>(Arrays.asList("aA12345","aA123456","aA123456789012345678"," aA1`~!@#$%^&*()-_=+[{]};:'\"\\\\,<.>/?"));
    ArrayList<String> firstAndLastNameNegativeTestCasesList = new ArrayList<>(Arrays.asList("", "  ", "Z"," Al","Al3","aaaa"));
    ArrayList<String> firstAndLastNamePositiveTestCasesList = new ArrayList<>(Arrays.asList("Al","Joe","abba","McArthur", "O'Connnor"));
    int i = 0;
    int y = 0;
        
    @BeforeClass
    public static void setUpClass() throws IOException {
        Configuration.init();
        System.setProperty("webdriver.chrome.driver", Configuration.chromeDriverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://community.element14.com/user/createuser?ReturnUrl=%2F");
    }
    
    @AfterClass
    public static void tearDownClass() throws InterruptedException {
        Thread.sleep(1500);
        driver.quit();
    }
    
    
    
    
    
    
    public void inputAllFieldsFromLists(String s, String e, String p, String f, String l, String pr, String t) throws InterruptedException{        
        if (s == "negativeTestCases")
            y = signInNameNegativeTestCasesList.size();
            else if (s == "positiveTestCases")
                y = signInNamePositiveTestCasesList.size();
            else if (e == "negativeTestCases")
                y = emailNegativeTestCasesList.size();
            else if (p == "negativeTestCases")
                y = passwordNegativeTestCasesList.size();
            else if (p == "positiveTestCases")
                y = passwordPositiveTestCasesList.size();
            else if (f == "negativeTestCases")
                y = firstAndLastNameNegativeTestCasesList.size();
            else if (f == "positiveTestCases")
                y = firstAndLastNamePositiveTestCasesList.size();
            else if (l == "negativeTestCases")
                y = firstAndLastNameNegativeTestCasesList.size();
            else if (l == "positiveTestCases")
                y = firstAndLastNamePositiveTestCasesList.size();
            else y=0;
        for (i=0; i<y; i++){                           
          
            driver.get("https://community.element14.com/user/createuser?ReturnUrl=%2F");
            Thread.sleep(1500); 
            WebElement signInNameFieldElement = driver.findElement(By.id("fragment-46_username"));
            if (s == "negativeTestCases")
                signInNameFieldElement.sendKeys(signInNameNegativeTestCasesList.get(i));            
                else if (s == "positiveTestCases")
                    signInNameFieldElement.sendKeys(signInNamePositiveTestCasesList.get(i));
                else
                    signInNameFieldElement.sendKeys(signInNameDefaultPositiveValue);
            
                
            WebElement emailAdressFieldElement = driver.findElement(By.id("fragment-46_email"));            
            if (e == "negativeTestCases")
                emailAdressFieldElement.sendKeys(emailNegativeTestCasesList.get(i));                                   
                else
                    emailAdressFieldElement.sendKeys(emailDefaultPositiveValue);          
            
            WebElement passwordFieldElement = driver.findElement(By.id("fragment-46_password"));            
            WebElement confirmPasswordFieldElement = driver.findElement(By.id("fragment-46_password2"));                        
            if (p == "negativeTestCases") {
                passwordFieldElement.sendKeys(passwordNegativeTestCasesList.get(i));            
                confirmPasswordFieldElement.sendKeys(passwordNegativeTestCasesList.get(i));                
                }
                else if (p == "positiveTestCases") {
                    passwordFieldElement.sendKeys(passwordPositiveTestCasesList.get(i));            
                    confirmPasswordFieldElement.sendKeys(passwordPositiveTestCasesList.get(i));
                }
                else {
                    passwordFieldElement.sendKeys(passwordDefaultPositiveValue);   
                    confirmPasswordFieldElement.sendKeys(passwordDefaultPositiveValue);   
                }
            
            WebElement firstNameFieldElement = driver.findElement(By.id("fragment-46_profileFieldsForm_1835267210_first-name"));
            if (f == "negativeTestCases") 
                firstNameFieldElement.sendKeys(firstAndLastNameNegativeTestCasesList.get(i));
                else if (f == "positiveTestCases") 
                firstNameFieldElement.sendKeys(firstAndLastNamePositiveTestCasesList.get(i));
                    else
                    firstNameFieldElement.sendKeys(firstNameDefaultPositiveValue);
            
            WebElement lastNameFieldElement = driver.findElement(By.id("fragment-46_profileFieldsForm_614460147_last-name"));
            if (l == "negativeTestCases") 
                lastNameFieldElement.sendKeys(firstAndLastNameNegativeTestCasesList.get(i));
            else if (l == "positiveTestCases") 
                lastNameFieldElement.sendKeys(firstAndLastNamePositiveTestCasesList.get(i));
                else
                    lastNameFieldElement.sendKeys(lastNameDefaultPositiveValue);
            
            WebElement promotionalEmailCheckBoxElement = driver.findElement(By.id("fragment-46_profileFieldsForm_237248213_oktocontact"));
            if (pr == "prChecked")
                promotionalEmailCheckBoxElement.click();
            
            WebElement termsOfServiceCheckBoxElement = driver.findElement(By.id("fragment-46_acceptAgreement"));
            if (t == "tossChecked")
                termsOfServiceCheckBoxElement.click();
            
            WebElement joinNowButtonElement = driver.findElement(By.cssSelector("a[class='internal-link create-account submit-button button']"));            
            joinNowButtonElement.click();              
            Thread.sleep(3500);      
            // driver.navigate().refresh();  
        }
    }
    
    
    public void inputAllFieldsFromCustomData(String c1, String c2, String c3, String c4, String c5, String c6, String c7, String c8) throws InterruptedException{
        WebElement signInNameFieldElement = driver.findElement(By.id("fragment-46_username"));
        signInNameFieldElement.sendKeys(c1);
        WebElement emailAdressFieldElement = driver.findElement(By.id("fragment-46_email"));            
        emailAdressFieldElement.sendKeys(c2);
        WebElement passwordFieldElement = driver.findElement(By.id("fragment-46_password"));
        passwordFieldElement.sendKeys(c3);
        WebElement confirmPasswordFieldElement = driver.findElement(By.id("fragment-46_password2"));
        confirmPasswordFieldElement.sendKeys(c4);
        WebElement firstNameFieldElement = driver.findElement(By.id("fragment-46_profileFieldsForm_1835267210_first-name"));
        firstNameFieldElement.sendKeys(c5);
        WebElement lastNameFieldElement = driver.findElement(By.id("fragment-46_profileFieldsForm_614460147_last-name"));
        lastNameFieldElement.sendKeys(c6);
        WebElement promotionalEmailCheckBoxElement = driver.findElement(By.id("fragment-46_profileFieldsForm_237248213_oktocontact"));
        if (c7 == "prChecked")
            promotionalEmailCheckBoxElement.click();        
        WebElement termsOfServiceCheckBoxElement = driver.findElement(By.id("fragment-46_acceptAgreement"));
        if (c8 == "tossChecked")
            termsOfServiceCheckBoxElement.click();
        Thread.sleep(0500); 
        WebElement joinNowButtonElement = driver.findElement(By.cssSelector("a[class='internal-link create-account submit-button button']"));            
        joinNowButtonElement.click();          
    }
    
    
    public void newMethod(String s, String e, String p, String f, String l, String pr, String t) throws InterruptedException{
         driver.get("https://community.element14.com/user/createuser?ReturnUrl=%2F");
            Thread.sleep(1500); 
            WebElement signInNameFieldElement = driver.findElement(By.id("fragment-46_username"));
            if (s == "negativeTestCases")
                signInNameFieldElement.sendKeys(signInNameNegativeTestCasesList.get(i));            
                else if (s == "positiveTestCases")
                    signInNameFieldElement.sendKeys(signInNamePositiveTestCasesList.get(i));
                else
                    signInNameFieldElement.sendKeys(signInNameDefaultPositiveValue);            
                
            WebElement emailAdressFieldElement = driver.findElement(By.id("fragment-46_email"));            
            if (e == "negativeTestCases")
                emailAdressFieldElement.sendKeys(emailNegativeTestCasesList.get(i));                                   
                else
                    emailAdressFieldElement.sendKeys(emailDefaultPositiveValue);          
            
            WebElement passwordFieldElement = driver.findElement(By.id("fragment-46_password"));            
            WebElement confirmPasswordFieldElement = driver.findElement(By.id("fragment-46_password2"));                        
            if (p == "negativeTestCases") {
                passwordFieldElement.sendKeys(passwordNegativeTestCasesList.get(i));            
                confirmPasswordFieldElement.sendKeys(passwordNegativeTestCasesList.get(i));                
                }
                else if (p == "positiveTestCases") {
                    passwordFieldElement.sendKeys(passwordPositiveTestCasesList.get(i));            
                    confirmPasswordFieldElement.sendKeys(passwordPositiveTestCasesList.get(i));
                }
                else {
                    passwordFieldElement.sendKeys(passwordDefaultPositiveValue);   
                    confirmPasswordFieldElement.sendKeys(passwordDefaultPositiveValue);   
                }
            
            WebElement firstNameFieldElement = driver.findElement(By.id("fragment-46_profileFieldsForm_1835267210_first-name"));
            if (f == "negativeTestCases") 
                firstNameFieldElement.sendKeys(firstAndLastNameNegativeTestCasesList.get(i));
                else if (f == "positiveTestCases") 
                firstNameFieldElement.sendKeys(firstAndLastNamePositiveTestCasesList.get(i));
                    else
                    firstNameFieldElement.sendKeys(firstNameDefaultPositiveValue);
            
            WebElement lastNameFieldElement = driver.findElement(By.id("fragment-46_profileFieldsForm_614460147_last-name"));
            if (l == "negativeTestCases") 
                lastNameFieldElement.sendKeys(firstAndLastNameNegativeTestCasesList.get(i));
            else if (l == "positiveTestCases") 
                lastNameFieldElement.sendKeys(firstAndLastNamePositiveTestCasesList.get(i));
                else
                    lastNameFieldElement.sendKeys(lastNameDefaultPositiveValue);
            
            WebElement promotionalEmailCheckBoxElement = driver.findElement(By.id("fragment-46_profileFieldsForm_237248213_oktocontact"));
            if (pr == "prChecked")
                promotionalEmailCheckBoxElement.click();
            
            WebElement termsOfServiceCheckBoxElement = driver.findElement(By.id("fragment-46_acceptAgreement"));
            if (t == "tossChecked")
                termsOfServiceCheckBoxElement.click();
            
            
            WebElement joinNowButtonElement = driver.findElement(By.cssSelector("a[class='internal-link create-account submit-button button']"));            
            joinNowButtonElement.click();  
            Thread.sleep(0500);             
                 
    }
    
    
    
    
    
    
    
    
    @Test
    public void noInputOnPage(){        
        WebElement joinNowButtonElement = driver.findElement(By.cssSelector("a[class='internal-link create-account submit-button button']"));            
        joinNowButtonElement.click();
    }
    
    @Test
    public void invalidSignInName() throws InterruptedException{  
        SoftAssertions softAssertions = new SoftAssertions();   
        for (i=0; i<signInNameNegativeTestCasesList.size(); i++){
        newMethod("negativeTestCases", "e", "p", "f", "l", "pr", "tossChecked");
        List<WebElement> elementsList1 = driver.findElements(By.xpath("//li[@class='field-item required user-name']/span[contains(text(), 'This field is required.')]"));
        List<WebElement> elementsList2 = driver.findElements(By.xpath("//span[contains(text(), 'Please enter a value greater than or equal to 3.')]"));
        List<WebElement> elementsList3 = driver.findElements(By.xpath("//span[contains(text(), 'Your sign in name does not meet the requirements for this site.')]"));
        List<WebElement> elementsList4 = driver.findElements(By.xpath("//span[contains(text(), 'That sign in name is not available.')]"));
        int passed = elementsList1.size() + elementsList2.size() + elementsList3.size() + elementsList4.size();                
        if (passed == 0) 
            System.out.println("DataNotPassedTests: " + signInNameNegativeTestCasesList.get(i));
        softAssertions.assertThat(passed).isEqualTo(1);}
        softAssertions.assertAll();
    }
    
    @Test
    public void RegisterWithAlreadyUsedUsernameAndEmail() throws InterruptedException{
        inputAllFieldsFromCustomData("dragtomasevic", "dragan_tom@yahoo.com", "aA12345", "aA12345", "Tom", "Jones", s, "tossChecked");
    }
    
    @Test
    public void invalidEmailFormat() throws InterruptedException{
        inputAllFieldsFromLists("s", "negativeTestCases",  "p", "f", "l", "pr", "tossChecked");
    }
    
    @Test
    public void invalidPassword() throws InterruptedException{
        inputAllFieldsFromLists("s", "e", "negativeTestCases", "f", "l", "pr", "tossChecked");
    }
    
    @Test
    public void differentPasswords() throws InterruptedException{
        inputAllFieldsFromCustomData("c1", "c2", passwordDefaultPositiveValue, confirmPasswordDefaultPositiveValue, "c5", "c6", "c7", "tossChecked");
    }
        
    @Test
    public void invalidFirstname() throws InterruptedException{
        inputAllFieldsFromLists("s", "e", "p", "negativeTestCases", "l", "pr", "tossChecked");
    }
    
    @Test
    public void invalidLastname() throws InterruptedException{
        inputAllFieldsFromLists("s", "e", "p", "f", "negativeTestCases", "pr", "tossChecked");
    }
    
    @Test
    public void registerWithoutAcceptingToss() throws InterruptedException{
        inputAllFieldsFromCustomData(signInNameDefaultPositiveValue, emailDefaultPositiveValue, passwordDefaultPositiveValue, passwordDefaultPositiveValue, firstNameDefaultPositiveValue, lastNameDefaultPositiveValue, "prChecked", "tossNotChecked");
    }  
    
    
    @Test
    public void registerUsingAllPositiveTestData() throws InterruptedException{
        inputAllFieldsFromCustomData(signInNameDefaultPositiveValue, emailDefaultPositiveValue, passwordDefaultPositiveValue, passwordDefaultPositiveValue, firstNameDefaultPositiveValue, lastNameDefaultPositiveValue, "prChecked", "tossChecked");
        String expectedText = "Welcome to the community! Your account has been created and you are signed in.";
        String actualText = driver.findElement(By.xpath("//p[contains(text(), 'Welcome to the community!')]")).getText();
        assertEquals(actualText, expectedText);
    }

    @Test
    public void validPositiveSignInName() throws InterruptedException{
        inputAllFieldsFromLists("positiveTestCases", "e", "p", "f", "l", "pr", "tossChecked");
    }

    @Test
    public void validPositivePasswordInputData() throws InterruptedException{
        inputAllFieldsFromLists("s", "e", "positiveTestCases", "f", "l", "pr", "tossChecked");
    }

    @Test
    public void validFirstNameFormat() throws InterruptedException{
        inputAllFieldsFromLists("s", "e", "p", "positiveTestCases", "l", "pr", "tossChecked");
    }
    
    @Test
    public void validLastNameFormat() throws InterruptedException{
        inputAllFieldsFromLists("s", "e", "p", "f", "positiveTestCases", "pr", "tossChecked");
    }
    
    @Test
    public void registerWithPromotionalEmail() throws InterruptedException{
        inputAllFieldsFromCustomData(signInNameDefaultPositiveValue, emailDefaultPositiveValue, passwordDefaultPositiveValue, passwordDefaultPositiveValue, firstNameDefaultPositiveValue, lastNameDefaultPositiveValue, "prChecked", "tossChecked");
    }

    
    @Test
    public void countrySelectDropDown(){
        WebElement countrySelectDropDownElement = driver.findElement(By.cssSelector("#fragment-46_profileFieldsForm_717170147_country > option[value=\"AF\"]"));
        countrySelectDropDownElement.click();
    }
    
    
    
}
