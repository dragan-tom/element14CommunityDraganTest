package register;


import com.github.javafaker.Faker;
import org.junit.BeforeClass;
import framework.Configuration;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.AfterClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class RegisterTest {

    private static WebDriver driver;    
    protected Faker faker = new Faker();    
    int i = 0;
    
    ArrayList<String> signInNameNegativeTestCasesList = new ArrayList<>(Arrays.asList("", "   ", "a", "ab", " abc", "...", "---", "___"));
    ArrayList<String> signInNamePositiveTestCasesList = new ArrayList<>(Arrays.asList("Red", "4321", "12345678901234567890", "aA1.-_"));
    ArrayList<String> emailNegativeTestCasesList = new ArrayList<>(Arrays.asList("", "@2.3", "12.3", "1@.3", "1@23", "1@2.", "1@2.3@", " test@test.com"));
    ArrayList<String> passwordNegativeTestCasesList = new ArrayList<>(Arrays.asList("", "       ", "a","123456","abcdefg","ABCDEFG","1234567","abcABCD","abc1234","ABC1234"));
    ArrayList<String> passwordPositiveTestCasesList = new ArrayList<>(Arrays.asList("aA12345","aA123456","aA123456789012345678"," aA1`~!@#$%^&*()-_=+[{]};:'\"\\\\,<.>/?"));
    ArrayList<String> firstAndLastNameNegativeTestCasesList = new ArrayList<>(Arrays.asList("", "  ", "Z"," Al","Al3","aaaa"));
    ArrayList<String> firstAndLastNamePositiveTestCasesList = new ArrayList<>(Arrays.asList("Al","Joe","abba","McArthur", "O'Connnor"));
    
    @BeforeClass
    public static void setUpClass() throws IOException {
        Configuration.init();
        System.setProperty("webdriver.chrome.driver", Configuration.chromeDriverPath);
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://community.element14.com/user/createuser?ReturnUrl=%2F");
    }
    
//    @AfterClass
//    public static void tearDownClass() throws InterruptedException {
//        Thread.sleep(1500);
//        driver.quit();
//    }
    
    public void testMethod(String signInValue, String emailValue, String passwordValue, String firstnameValue, String lastnameValue, String promoEmailValue, String tosCheckBox) throws InterruptedException{
        driver.get("https://community.element14.com/user/createuser?ReturnUrl=%2F");
            Thread.sleep(3000); 
            
            WebElement signInNameFieldElement = driver.findElement(By.id("fragment-46_username"));
            if (signInValue == "signInNameNegativeTestCases")
                signInNameFieldElement.sendKeys(signInNameNegativeTestCasesList.get(i));            
                else if (signInValue == "signInNamePositiveTestCases")
                    signInNameFieldElement.sendKeys(signInNamePositiveTestCasesList.get(i));
                else if (signInValue == "signInDefaultPositive")
                    signInNameFieldElement.sendKeys("aA1" + faker.name().firstName());            
                else signInNameFieldElement.sendKeys(signInValue);            
                
            WebElement emailAddressFieldElement = driver.findElement(By.id("fragment-46_email"));            
            if (emailValue == "emailNegativeTestCases")
                emailAddressFieldElement.sendKeys(emailNegativeTestCasesList.get(i));                                   
                else if (emailValue == "emailDefaultPositive")
                    emailAddressFieldElement.sendKeys(faker.internet().emailAddress());          
                else emailAddressFieldElement.sendKeys(emailValue);
            
            WebElement passwordFieldElement = driver.findElement(By.id("fragment-46_password"));            
            WebElement confirmPasswordFieldElement = driver.findElement(By.id("fragment-46_password2"));                        
            if (passwordValue == "passwordNegativeTestCases") {
                passwordFieldElement.sendKeys(passwordNegativeTestCasesList.get(i));            
                confirmPasswordFieldElement.sendKeys(passwordNegativeTestCasesList.get(i));                
                }
                else if (passwordValue == "passwordPositiveTestCases") {
                    passwordFieldElement.sendKeys(passwordPositiveTestCasesList.get(i));            
                    confirmPasswordFieldElement.sendKeys(passwordPositiveTestCasesList.get(i));
                }
                else if (passwordValue == "passwordDefaultPositive"){
                    String generatedPassword = "aA1" + faker.internet().password();
                    passwordFieldElement.sendKeys(generatedPassword);   
                    confirmPasswordFieldElement.sendKeys(generatedPassword);   
                }
                else confirmPasswordFieldElement.sendKeys(passwordValue);
            
            WebElement firstnameFieldElement = driver.findElement(By.id("fragment-46_profileFieldsForm_1835267210_first-name"));
            if (firstnameValue == "firstnameNegativeTestCases") 
                firstnameFieldElement.sendKeys(firstAndLastNameNegativeTestCasesList.get(i));
                else if (firstnameValue == "firstnamePositiveTestCases") 
                    firstnameFieldElement.sendKeys(firstAndLastNamePositiveTestCasesList.get(i));
                else if (firstnameValue == "firstnameDefaultPositive")
                    firstnameFieldElement.sendKeys(faker.name().firstName());
                else firstnameFieldElement.sendKeys(firstnameValue);
            
            WebElement lastnameFieldElement = driver.findElement(By.id("fragment-46_profileFieldsForm_614460147_last-name"));
            if (lastnameValue == "lastnameNegativeTestCases") 
                lastnameFieldElement.sendKeys(firstAndLastNameNegativeTestCasesList.get(i));
                else if (lastnameValue == "lastnamePositiveTestCases") 
                    lastnameFieldElement.sendKeys(firstAndLastNamePositiveTestCasesList.get(i));
                else if (lastnameValue == "lastnameDefaultPositive")
                    lastnameFieldElement.sendKeys(faker.name().lastName());
                else lastnameFieldElement.sendKeys(lastnameValue);
            
            WebElement promotionalEmailCheckBoxElement = driver.findElement(By.id("fragment-46_profileFieldsForm_237248213_oktocontact"));
            if (promoEmailValue == "prChecked")
                promotionalEmailCheckBoxElement.click();
            
            WebElement termsOfServiceCheckBoxElement = driver.findElement(By.id("fragment-46_acceptAgreement"));
            if (tosCheckBox == "tossChecked")
                termsOfServiceCheckBoxElement.click();

            WebElement joinNowButtonElement = driver.findElement(By.cssSelector("a[class='internal-link create-account submit-button button']"));            
            joinNowButtonElement.click();  
    }
    
    
    @Test
    public void noInputOnPage() throws InterruptedException{                
        SoftAssertions softAssertions = new SoftAssertions();       
        Thread.sleep(3000);
        
        WebElement joinNowButtonElement = driver.findElement(By.cssSelector("a[class='internal-link create-account submit-button button']"));            
        joinNowButtonElement.click();
//      
        List<WebElement> signInEmptyFieldErrorMessage = driver.findElements(By.xpath("//li[@class='field-item required user-name']/span[contains(text(), 'This field is required.')]"));
        softAssertions.assertThat(signInEmptyFieldErrorMessage.get(0).getText()).isEqualTo("This field is required.");
        
        List<WebElement> emailEmptyFieldErrorMessage = driver.findElements(By.xpath("//li[@class='field-item required email']/span[contains(text(), 'This field is required.')]"));        
        softAssertions.assertThat(emailEmptyFieldErrorMessage.get(0).getText()).isEqualTo("This field is required.");
        
        List<WebElement> passwordEmptyFieldErrorMessage = driver.findElements(By.xpath("//li[@class='field-item required password']/span[contains(text(), 'This field is required.')]"));
        softAssertions.assertThat(passwordEmptyFieldErrorMessage.get(0).getText()).isEqualTo("This field is required.");
        
        List<WebElement> confirmPasswordEmptyFieldErrorMessage = driver.findElements(By.xpath("//li[@class='field-item required password2']/span[contains(text(), 'This field is required.')]"));
        softAssertions.assertThat(confirmPasswordEmptyFieldErrorMessage.get(0).getText()).isEqualTo("This field is required.");
        
        List<WebElement> firstNameEmptyFieldErrorMessage = driver.findElements(By.xpath("//div[@id='fragment-46_profileFieldsForm']//li[@class='field-item'][2]//span[contains(text(), 'This field is required.')]"));
        softAssertions.assertThat(firstNameEmptyFieldErrorMessage.get(0).getText()).isEqualTo("This field is required.");
        
        List<WebElement> lastNameEmptyFieldErrorMessage = driver.findElements(By.xpath("//div[@id='fragment-46_profileFieldsForm']//li[@class='field-item'][3]//span[contains(text(), 'This field is required.')]"));
        softAssertions.assertThat(lastNameEmptyFieldErrorMessage.get(0).getText()).isEqualTo("This field is required.");
        
        List<WebElement> termsOfServiseRequiredErrorMessage = driver.findElements(By.xpath("//li[@class='field-item required site-agreement']//span[contains(text(), 'This field is required.')]"));
        softAssertions.assertThat(termsOfServiseRequiredErrorMessage.get(0).getText()).isEqualTo("This field is required.");
        
        softAssertions.assertAll();
    }
    
    @Test
    public void invalidSignInName() throws InterruptedException{  
        SoftAssertions softAssertions = new SoftAssertions();   
        for (i=0; i<signInNameNegativeTestCasesList.size(); i++){
        testMethod("signInNameNegativeTestCases", "emailDefaultPositive", "passwordDefaultPositive", "firstnameDefaultPositive", "lastnameDefaultPositive", "prNotChecked", "tossChecked");
        
        List<WebElement> elementsList1 = driver.findElements(By.xpath("//li[@class='field-item required user-name']/span[contains(text(), 'This field is required.')]"));
        List<WebElement> elementsList2 = driver.findElements(By.xpath("//span[contains(text(), 'Please enter a value greater than or equal to 3.')]"));
        List<WebElement> elementsList3 = driver.findElements(By.xpath("//span[contains(text(), 'Your sign in name does not meet the requirements for this site.')]"));
        List<WebElement> elementsList4 = driver.findElements(By.xpath("//span[contains(text(), 'That sign in name is not available.')]"));
        int listSum = elementsList1.size() + elementsList2.size() + elementsList3.size() + elementsList4.size();                
        
        if (listSum == 0) 
            System.out.println("invalidSignInNameDataNotPassedTests: " + signInNameNegativeTestCasesList.get(i));
        
        softAssertions.assertThat(listSum).isEqualTo(1);}
        softAssertions.assertAll();
    }
    
    @Test
    public void RegisterWithAlreadyUsedUsernameAndEmail() throws InterruptedException{
        SoftAssertions softAssertions = new SoftAssertions();  
        testMethod("Oscar", "test@test.com", "passwordDefaultPositive", "firstnameDefaultPositive", "lastnameDefaultPositive", "prNotChecked", "tossChecked");
        
        List<WebElement> signInNameTakenErrorMessage = driver.findElements(By.xpath("//span[contains(text(), 'That sign in name is not available.')]"));
        softAssertions.assertThat(signInNameTakenErrorMessage.get(0).getText()).isEqualTo("That sign in name is not available.");
        
        List<WebElement> emailTakenErrorMessage = driver.findElements(By.xpath("//span[contains(text(), 'A user with this email address already exists.')]"));
        softAssertions.assertThat(emailTakenErrorMessage.get(0).getText()).isEqualTo("A user with this email address already exists.");
        
        softAssertions.assertAll();        
    }
    
    @Test
    public void invalidEmailFormat() throws InterruptedException{
        SoftAssertions softAssertions = new SoftAssertions(); 
        for (i=0; i<emailNegativeTestCasesList.size(); i++){
        testMethod("signInDefaultPositive", "emailNegativeTestCases", "passwordDefaultPositive", "firstnameDefaultPositive", "lastnameDefaultPositive", "prNotChecked", "tossChecked");
        
        List<WebElement> elementsList1 = driver.findElements(By.xpath("//li[@class='field-item required email']/span[contains(text(), 'This field is required.')]"));
        List<WebElement> elementsList2 = driver.findElements(By.xpath("//span[contains(text(), 'A user with this email address already exists.')]"));
        List<WebElement> elementsList3 = driver.findElements(By.xpath("//span[contains(text(), 'Your email address is invalid.')]"));
        int listSum = elementsList1.size() + elementsList2.size() + elementsList3.size();
        if (listSum == 0) 
            System.out.println("invalidEmailDataNotPassedTests: " + emailNegativeTestCasesList.get(i));
        
        softAssertions.assertThat(listSum).isEqualTo(1);}
        softAssertions.assertAll();
    }
    
    @Test
    public void invalidPassword() throws InterruptedException{
        SoftAssertions softAssertions = new SoftAssertions(); 
        for (i=0; i<passwordNegativeTestCasesList.size(); i++){
        testMethod("signInDefaultPositive", "emailDefaultPositive", "passwordNegativeTestCases", "firstnameDefaultPositive", "lastnameDefaultPositive", "prNotChecked", "tossChecked");
        
        List<WebElement> elementsList1 = driver.findElements(By.xpath("//li[@class='field-item required password']/span[contains(text(), 'This field is required.')]"));
        List<WebElement> elementsList2 = driver.findElements(By.xpath("//span[contains(text(), 'Password does not meet the requirements for this site.')]"));
        List<WebElement> elementsList3 = driver.findElements(By.xpath("//span[contains(text(), 'Password must be at least 7 characters')]"));
        int listSum = elementsList1.size() + elementsList2.size() + elementsList3.size();
        if (listSum == 0) 
            System.out.println("invalidPasswordDataNotPassedTests: " + passwordNegativeTestCasesList.get(i));
        
        softAssertions.assertThat(listSum).isEqualTo(1);}
        softAssertions.assertAll();
    }
    
    @Test
    public void differentPasswords() throws InterruptedException{
        SoftAssertions softAssertions = new SoftAssertions();           
        Thread.sleep(3000);
        
        WebElement passwordFieldElement = driver.findElement(By.id("fragment-46_password"));            
        passwordFieldElement.sendKeys("aA1" + faker.internet().password());   
        WebElement confirmPasswordFieldElement = driver.findElement(By.id("fragment-46_password2"));   
        confirmPasswordFieldElement.sendKeys("aA1" + faker.internet().password());   
        
        WebElement joinNowButtonElement = driver.findElement(By.cssSelector("a[class='internal-link create-account submit-button button']"));            
        joinNowButtonElement.click();
        
        List<WebElement> passwordsDoNotMatch = driver.findElements(By.xpath("//li[@class='field-item required password2']/span[contains(text(), 'Passwords do not match')]"));
        softAssertions.assertThat(passwordsDoNotMatch.get(0).getText()).isEqualTo("Passwords do not match");
        softAssertions.assertAll();
    }
        
    @Test
    public void invalidFirstname() throws InterruptedException{
        SoftAssertions softAssertions = new SoftAssertions(); 
        for (i=0; i<firstAndLastNameNegativeTestCasesList.size(); i++){
        testMethod("signInDefaultPositive", "emailDefaultPositive", "passwordDefaultPositive", "firstnameNegativeTestCases", "lastnameDefaultPositive", "prNotChecked", "tossChecked");
        
        List<WebElement> firstnameNoInput = driver.findElements(By.xpath("//div[@id='fragment-46_profileFieldsForm']//li[@class='field-item'][2]//span[contains(text(), 'This field is required.')]"));
        List<WebElement> firstnameInvalid = driver.findElements(By.xpath("//div[@id='fragment-46_profileFieldsForm']//li[@class='field-item'][2]//span[contains(text(), 'Here would be a path to error message: Invalid firstname. Must contain minimum of 2 letters.')]"));
        int listSum = firstnameNoInput.size() + firstnameInvalid.size();
        if (listSum == 0) 
            System.out.println("invalidFirstnameDataNotPassedTests: " + firstAndLastNameNegativeTestCasesList.get(i));
        
        softAssertions.assertThat(listSum).isEqualTo(1);}
        softAssertions.assertAll();
    }
    
    @Test
    public void invalidLastname() throws InterruptedException{
        SoftAssertions softAssertions = new SoftAssertions(); 
        for (i=0; i<firstAndLastNameNegativeTestCasesList.size(); i++){
        testMethod("signInDefaultPositive", "emailDefaultPositive", "passwordDefaultPositive", "firstnameDefaultPositive", "lastnameNegativeTestCases", "prNotChecked", "tossChecked");
        
        List<WebElement> lastnameNoInput = driver.findElements(By.xpath("//div[@id='fragment-46_profileFieldsForm']//li[@class='field-item'][3]//span[contains(text(), 'This field is required.')]"));
        List<WebElement> lastnameInvalid = driver.findElements(By.xpath("//div[@id='fragment-46_profileFieldsForm']//li[@class='field-item'][3]//span[contains(text(), 'Here would be a path to error message: Invalid lastname. Must contain minimum of 2 letters.')]"));
        int listSum = lastnameNoInput.size() + lastnameInvalid.size();
        if (listSum == 0) 
            System.out.println("invalidLastnameDataNotPassedTests: " + firstAndLastNameNegativeTestCasesList.get(i));
        
        softAssertions.assertThat(listSum).isEqualTo(1);}
        softAssertions.assertAll();
    }
    
    @Test
    public void registerWithoutAcceptingToss() throws InterruptedException{
        SoftAssertions softAssertions = new SoftAssertions(); 
        testMethod("signInDefaultPositive", "emailDefaultPositive", "passwordDefaultPositive", "firstnameDefaultPositive", "lastnameDefaultPositive", "prNotChecked", "tossNotChecked");
        
        List<WebElement> tossNotChecked = driver.findElements(By.xpath("//li[@class='field-item required site-agreement']//span[contains(text(), 'This field is required.')]"));
        softAssertions.assertThat(tossNotChecked.get(0).getText()).isEqualTo("This field is required.");
        softAssertions.assertAll();
    }  
    
    @Test
    public void registerUsingAllPositiveTestData() throws InterruptedException{
        SoftAssertions softAssertions = new SoftAssertions(); 
        
        testMethod("signInDefaultPositive", "emailDefaultPositive", "passwordDefaultPositive", "firstnameDefaultPositive", "lastnameDefaultPositive", "prNotChecked", "tossChecked");
        
        List<WebElement> successfullyRegistered = driver.findElements(By.xpath("//p[contains(text(), 'Welcome to the community!')]"));
        softAssertions.assertThat(successfullyRegistered.size()).isEqualTo(1);
        softAssertions.assertAll();
    }

    @Test
    public void validPositiveSignInName() throws InterruptedException{
        SoftAssertions softAssertions = new SoftAssertions();   
        for (i=0; i<signInNamePositiveTestCasesList.size(); i++){
        testMethod("signInNamePositiveTestCases", "emailDefaultPositive", "passwordDefaultPositive", "firstnameDefaultPositive", "lastnameDefaultPositive", "prNotChecked", "tossNotChecked");
        
        List<WebElement> elementsList1 = driver.findElements(By.xpath("//li[@class='field-item required user-name']/span[contains(text(), 'This field is required.')]"));
        List<WebElement> elementsList2 = driver.findElements(By.xpath("//span[contains(text(), 'Please enter a value greater than or equal to 3.')]"));
        List<WebElement> elementsList3 = driver.findElements(By.xpath("//span[contains(text(), 'Your sign in name does not meet the requirements for this site.')]"));
        List<WebElement> elementsList4 = driver.findElements(By.xpath("//span[contains(text(), 'That sign in name is not available.')]"));
        int listSum = elementsList1.size() + elementsList2.size() + elementsList3.size() + elementsList4.size();                
        if (listSum == 1) 
            System.out.println("validSignInNameDataNotPassedTests: " + signInNamePositiveTestCasesList.get(i));
        
        softAssertions.assertThat(listSum).isEqualTo(0);}
        softAssertions.assertAll();
    }

    @Test
    public void validPositivePasswordInputData() throws InterruptedException{
        SoftAssertions softAssertions = new SoftAssertions(); 
        for (i=0; i<passwordPositiveTestCasesList.size(); i++){
        testMethod("signInDefaultPositive", "emailDefaultPositive", "passwordPositiveTestCases", "firstnameDefaultPositive", "lastnameDefaultPositive", "prNotChecked", "tossNotChecked");
        
        List<WebElement> elementsList1 = driver.findElements(By.xpath("//li[@class='field-item required password']/span[contains(text(), 'This field is required.')]"));
        List<WebElement> elementsList2 = driver.findElements(By.xpath("//span[contains(text(), 'Password does not meet the requirements for this site.')]"));
        List<WebElement> elementsList3 = driver.findElements(By.xpath("//span[contains(text(), 'Password must be at least 7 characters')]"));
        int listSum = elementsList1.size() + elementsList2.size() + elementsList3.size();
        if (listSum == 1) 
            System.out.println("validPasswordDataNotPassedTests: " + passwordPositiveTestCasesList.get(i));
        
        softAssertions.assertThat(listSum).isEqualTo(0);}
        softAssertions.assertAll(); 
    }

    @Test
    public void validFirstNameFormat() throws InterruptedException{
        SoftAssertions softAssertions = new SoftAssertions(); 
        for (i=0; i<firstAndLastNamePositiveTestCasesList.size(); i++){
        testMethod("signInDefaultPositive", "emailDefaultPositive", "passwordDefaultPositive", "firstnamePositiveTestCases", "lastnameDefaultPositive", "prNotChecked", "tossNotChecked");
        
        List<WebElement> firstnameNoInput = driver.findElements(By.xpath("//div[@id='fragment-46_profileFieldsForm']//li[@class='field-item'][2]//span[contains(text(), 'This field is required.')]"));
        List<WebElement> firstnameInvalid = driver.findElements(By.xpath("//div[@id='fragment-46_profileFieldsForm']//li[@class='field-item'][2]//span[contains(text(), 'Here would be a path to error message: Invalid firstname. Must contain minimum of 2 letters.')]"));
        int listSum = firstnameNoInput.size() + firstnameInvalid.size();
        if (listSum == 1) 
            System.out.println("validFirstnameDataNotPassedTests: " + firstAndLastNamePositiveTestCasesList.get(i));
        
        softAssertions.assertThat(listSum).isEqualTo(0);}
        softAssertions.assertAll();
    }
    
    @Test
    public void validLastNameFormat() throws InterruptedException{
        SoftAssertions softAssertions = new SoftAssertions(); 
        for (i=0; i<firstAndLastNamePositiveTestCasesList.size(); i++){
        testMethod("signInDefaultPositive", "emailDefaultPositive", "passwordDefaultPositive", "firstnameDefaultPositive", "lastnamePositiveTestCases", "prNotChecked", "tossNotChecked");
        
        List<WebElement> firstnameNoInput = driver.findElements(By.xpath("//div[@id='fragment-46_profileFieldsForm']//li[@class='field-item'][2]//span[contains(text(), 'This field is required.')]"));
        List<WebElement> firstnameInvalid = driver.findElements(By.xpath("//div[@id='fragment-46_profileFieldsForm']//li[@class='field-item'][2]//span[contains(text(), 'Here would be a path to error message: Invalid firstname. Must contain minimum of 2 letters.')]"));
        int listSum = firstnameNoInput.size() + firstnameInvalid.size();
        if (listSum == 1) 
            System.out.println("validLastnameDataNotPassedTests: " + firstAndLastNamePositiveTestCasesList.get(i));
        
        softAssertions.assertThat(listSum).isEqualTo(0);}
        softAssertions.assertAll();
    }
    
    @Test
    public void registerWithPromotionalEmail() throws InterruptedException{
        SoftAssertions softAssertions = new SoftAssertions(); 
        
        testMethod("signInDefaultPositive", "emailDefaultPositive", "passwordDefaultPositive", "firstnameDefaultPositive", "lastnameDefaultPositive", "prChecked", "tossChecked");
        
        List<WebElement> successfullyRegistered = driver.findElements(By.xpath("//p[contains(text(), 'Welcome to the community!')]"));
        softAssertions.assertThat(successfullyRegistered.size()).isEqualTo(1); 
        softAssertions.assertAll();
    }

    @Test
    public void countrySelectDropDown(){
//selecting Japan from dropdown list
        WebElement countrySelectDropDownElement = driver.findElement(By.cssSelector("#fragment-46_profileFieldsForm_717170147_country > option[value=\"JP\"]"));
        countrySelectDropDownElement.click();
    }
    
}
