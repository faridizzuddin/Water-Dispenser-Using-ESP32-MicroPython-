package system;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

public class oasis {
	
	private WebDriver driver;
    private WebDriverWait wait;
    private final String baseUrl = "https://elearningtesting.umt.edu.my/oasis";
    private final String chromeDriverPath = "C://Users//user//Downloads//chromedriver-win64 (2)//chromedriver-win64//chromedriver.exe/";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(60));
        driver.get(baseUrl);
    }   
     

    @Test
    public void AtestUnsuccessfulRequest() {
    	
   
    	// Step 1: Enter username and password on the initial login page
    	String username = "k2_oasis_student";
	    String password = "k2_oasis_student";

	    // Go to url
	    driver.get("https://elearningtesting.umt.edu.my/oasis");

	    // Execute Test Procedure
	    // Click on the login/register button
	    driver.findElement(By.linkText("Log in")).click();

	    // Enter the username
	    driver.findElement(By.id("username")).sendKeys(username);

	    // Enter the password
	    driver.findElement(By.id("password")).sendKeys(password);

	    // Click on the "Login" button
	    driver.findElement(By.cssSelector(".text-center.mt-3 .btn.btn-success")).click();
        

//        // Wait for the button to be clickable
//        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.className("drawer-toggler")));
//
//        // Use Actions class to click the button
//        Actions actions = new Actions(driver);
//        actions.moveToElement(button).click().perform();
	    
	    //Click on request a course
	    WebElement requestCourseButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='https://epembelajaran.umt.edu.my/custom/button/reqbutton.gif']")));
        requestCourseButton.click();

        // Verify that the page navigates to the course request page
        String pageTitle = driver.getTitle();
        assertEquals("e-Pembelajaran : OASIS", pageTitle);

     // Enter the username
	    driver.findElement(By.id("username")).sendKeys(username);

	    // Enter the password
	    driver.findElement(By.id("password")).sendKeys(password);

	    // Click on the "Login" button
	    driver.findElement(By.cssSelector(".btn.btn-success.shadow-none.w-50.rounded-pill")).click();

        // Step 4: Verify the error message for unsuccessful login
        WebElement errorMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".loginerrors .error")));
        String actualErrorMessage = errorMessageElement.getText();
        String expectedErrorMessage = "Invalid username or password"; // Replace with your expected error message
        assertEquals("Error message does not match", expectedErrorMessage, actualErrorMessage);
    }
    
    @Test
    public void testAccessibilityFeatures() {
    	
    	
    	// Step 1: Enter username and password on the initial login page
    	String username = "k2_oasis_student";
	    String password = "k2_oasis_student";

	    // Go to url
	    driver.get("https://elearningtesting.umt.edu.my/oasis");

	    // Execute Test Procedure
	    // Click on the login/register button
	    driver.findElement(By.linkText("Log in")).click();

	    // Enter the username
	    driver.findElement(By.id("username")).sendKeys(username);

	    // Enter the password
	    driver.findElement(By.id("password")).sendKeys(password);

	    // Click on the "Login" button
	    driver.findElement(By.cssSelector(".text-center.mt-3 .btn.btn-success")).click();
	    
    	// Click on the "dropdown profile" button
	    driver.findElement(By.cssSelector(".userbutton")).click();
	    
	    // Click on the "accessibility" 
	    driver.findElement(By.id("accessibilitysettings-control")).click();
	    
	    // Wait until the dropdown is visible and then locate it
        WebElement fontDropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("fonttype")));
        
        // Create a Select object for the dropdown
        Select select = new Select(fontDropdown);
        
        // Select the desired option by value
        select.selectByValue("odafont");
        
     // Wait for and click the "Save" button within the modal footer
        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".modal-footer .btn.btn-primary")));
        saveButton.click();
	    
     // Step 4: Verify the success message after saving the settings
        WebElement successMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert.alert-success.alert-block.fade.in.alert-dismissible")));
        String actualSuccessMessage = successMessageElement.getText().trim(); // Trim any extra whitespace
        String expectedSuccessMessage = "Accessibility settings successfully saved\n"
        								+ "×\n"
        								+ "Dismiss this notification";
        assertEquals("Success message does not match", expectedSuccessMessage, actualSuccessMessage);
        
    }
    
    @Test
    public void testMessages() {
    	
    	// Step 1: Enter username and password on the initial login page
    	String username = "k2_oasis_student";
	    String password = "k2_oasis_student";
	    String text = "haii";
	    

	    // Go to url
	    driver.get("https://elearningtesting.umt.edu.my/oasis");

	    // Execute Test Procedure
	    // Click on the login/register button
	    driver.findElement(By.linkText("Log in")).click();

	    // Enter the username
	    driver.findElement(By.id("username")).sendKeys(username);

	    // Enter the password
	    driver.findElement(By.id("password")).sendKeys(password);

	    // Click on the "Login" button
	    driver.findElement(By.cssSelector(".text-center.mt-3 .btn.btn-success")).click();
	    
    	// Click on the "dropdown profile" button
	    driver.findElement(By.cssSelector(".userbutton")).click();
	    
	    // Click on the "Messages" 
	    driver.get("https://elearningtesting.umt.edu.my/oasis/message/index.php");
	    
	    // Click on the "student" message box
	    // Click on a specific message box (adjust your selector accordingly)
        driver.findElement(By.cssSelector(".m-0.text-truncate")).click();

        
     // Find the textarea element and wait for it to be clickable
        WebElement textAreaElement = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("textarea[data-region='send-message-txt']")));
        
        // Clear any existing text (optional)
        textAreaElement.clear();
        
        // Send keys to the textarea element
        textAreaElement.sendKeys(text);
	    
	    // Click on the "send messages" button
	    driver.findElement(By.cssSelector(".btn.btn-link.btn-icon.icon-size-3.ml-1.mt-auto")).click();
	    
	 // Wait for the new message to appear
	    WebElement messageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-message-id='1376'] [data-region='text-container'] p")));

	    // Get the actual text content
	    String actualMessage = messageElement.getText().trim(); // Trim any extra whitespace

	    // Define the expected text
	    String expectedMessage = "haii";

	    // Compare the actual and expected text
	    assertEquals("Message does not match", expectedMessage, actualMessage);
    	
    }
    
    @Test
    public void testAddContact() {
    	
    	// Step 1: Enter username and password on the initial login page
    	String username = "k2_oasis_student";
	    String password = "k2_oasis_student";
	    String person = "lecturer";
	    

	    // Go to url
	    driver.get("https://elearningtesting.umt.edu.my/oasis");

	    // Execute Test Procedure
	    // Click on the login/register button
	    driver.findElement(By.linkText("Log in")).click();

	    // Enter the username
	    driver.findElement(By.id("username")).sendKeys(username);

	    // Enter the password
	    driver.findElement(By.id("password")).sendKeys(password);

	    // Click on the "Login" button
	    driver.findElement(By.cssSelector(".text-center.mt-3 .btn.btn-success")).click();
	    
    	// Click on the "dropdown profile" button
	    driver.findElement(By.cssSelector(".userbutton")).click();
	    
	    // Click on the "Messages" 
	    driver.get("https://elearningtesting.umt.edu.my/oasis/message/index.php");
	    
	    //Click on search box and key in any friends name
	    driver.findElement(By.cssSelector(".form-control")).sendKeys(person);
	    
	    //Click search symbol
	    try {
	        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='button' and @data-action='search']")));
	        searchButton.click();
	        System.out.println("Search button clicked successfully using alternative locator.");
	    } catch (Exception e) {
	        System.out.println("Failed to click the search button using alternative locator.");
	        e.printStackTrace();
	    }
        
        
	    //Click name box message
	    try {
	        WebElement lecturerBox = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(@class, 'matchtext') and text()='lecturer']")));
	        lecturerBox.click();
	        System.out.println("Lecturer box clicked successfully.");
	    } catch (Exception e) {
	        System.out.println("Failed to click the lecturer box.");
	        e.printStackTrace();
	    }

	    //Click 3 dot
	    WebElement threeDotsButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("conversation-actions-menu-button")));
	    threeDotsButton.click();
	    
	    //Click add to contact
	    WebElement addToContactsOption = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-action='request-add-contact']")));
	    addToContactsOption.click();
        
        
        //click save button
	    WebElement confirmAddButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[data-action='confirm-add-contact']")));
	    confirmAddButton.click();

	 
	    // Increase wait time for the success message
	    WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(60));
	    WebElement successMessageElement = longWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[data-region='contact-request-sent-message-container'] p")));

	    // Retrieve the actual success message text
	    String actualSuccessMessage = successMessageElement.getText().trim(); // Trim any extra whitespace
	    String expectedSuccessMessage = "Contact request sent";

	    // Compare the actual and expected success messages
	    assertEquals("Success message does not match", expectedSuccessMessage, actualSuccessMessage);
	}
        
        
    @Test
    public void testAnnouncement() {
    	
    	// Step 1: Enter username and password on the initial login page
    	String username = "k2_oasis_student";
	    String password = "k2_oasis_student";
	    

	    // Go to url
	    driver.get("https://elearningtesting.umt.edu.my/oasis");

	    // Execute Test Procedure
	    // Click on the login/register button
	    driver.findElement(By.linkText("Log in")).click();

	    // Enter the username
	    driver.findElement(By.id("username")).sendKeys(username);

	    // Enter the password
	    driver.findElement(By.id("password")).sendKeys(password);

	    // Click on the "Login" button
	    driver.findElement(By.cssSelector(".text-center.mt-3 .btn.btn-success")).click();
	    
	    //Click my Courses
	    WebElement myCoursesLink = new WebDriverWait(driver, Duration.ofSeconds(10))
	    .until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='https://elearningtesting.umt.edu.my/oasis/my/courses.php']")));
	    myCoursesLink.click();
	    
	    //click on course name
	    driver.findElement(By.cssSelector(".aalink.coursename.mr-2.mb-1")).click();
	    
	    //click course announcement
	    driver.findElement(By.cssSelector(".aalink.stretched-link")).click();
	    
	 // Step 4: Verify the Course Announcement text
	    WebElement courseAnnouncementElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".page-context-header .page-header-headings h1.h2")));
	    String actualText = courseAnnouncementElement.getText().trim(); // Trim any extra whitespace
	    String expectedText = "Course Announcement";
	    assertEquals("Course Announcement text does not match", expectedText, actualText);
    	
    }
   
    
//    @Test
//    public void testEditButton() {
//    	//Step 1: Enter username and password on the initial login page
//    	String username = "k2_oasis_student";
//	    String password = "k2_oasis_student";
//	    
//
//	    // Go to url
//	    driver.get("https://elearningtesting.umt.edu.my/oasis");
//
//	    // Execute Test Procedure
//	    // Click on the login/register button
//	    driver.findElement(By.linkText("Log in")).click();
//
//	    // Enter the username
//	    driver.findElement(By.id("username")).sendKeys(username);
//
//	    // Enter the password
//	    driver.findElement(By.id("password")).sendKeys(password);
//
//	    // Click on the "Login" button
//	    driver.findElement(By.cssSelector(".text-center.mt-3 .btn.btn-success")).click();
//	    
//	    // Find the checkbox element and click on it
//	    driver.findElement(By.cssSelector(".d-flex.align-items-center.editmode-switch-form")).click();
//	    
//	    // Click on the "add block" 
//	    driver.get("https://elearningtesting.umt.edu.my/oasis/my/index.php?bui_addblock&sesskey=UJHlMFCzNB&bui_blockregion=content&__ncforminfo=1oiXynfjYIvRoelgF34_ao6Z7o1SlIKQLpz8B3tViOWJRccwpNAR9i0YPE-J3DNDAxHkzpQO2UgU1-sHjLAg4AINCkbTENgN8zNCypJavEmjremcSM8IPaHUM_b9JeiH");
//        
//	    //Click private files
//	    driver.get("https://elearningtesting.umt.edu.my/oasis/my/index.php?bui_addblock&sesskey=UJHlMFCzNB&bui_blockregion=content&bui_addblock=private_files");
//        
//	 
//	    
//		 // Step 5: Wait for the "Private files" header to be visible
//        try {
//            WebElement privateFilesHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h5#instance-18289-header.card-title.d-inline")));
//
//            // Step 6: Verify the text of the element
//            String actualText = privateFilesHeader.getText().trim();
//            String expectedText = "Private files";
//            assertEquals(expectedText, actualText, "Private files header text does not match");
//
//            // Output verification result
//            System.out.println("Expected output: Display Private files");
//            System.out.println("Actual output: " + actualText);
//        } catch (Exception e) {
//            fail("Timeout waiting for Private files header to be visible");
//            e.printStackTrace();
//        
//        }
//    	
//    }
//    

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void main(String[] args) {
        org.junit.runner.JUnitCore.main("oasis");
    }
}
   