package Ditinteractive;

import org.testng.annotations.AfterClass;
	import java.time.Duration;

	import org.openqa.selenium.By;
	import org.openqa.selenium.JavascriptExecutor;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
    import org.openqa.selenium.interactions.Actions;
    import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import org.testng.annotations.BeforeClass;
	import org.testng.annotations.Test;

	public class About{
	    private WebDriver driver;
	    private WebDriverWait wait;
	    private JavascriptExecutor js;

	    @BeforeClass
	    public void setUp() {
	        // Initialize the ChromeDriver
	        driver = new ChromeDriver();

	        // Maximize the browser window
	        driver.manage().window().maximize();

	        // Set implicit wait time
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	        // Initialize explicit wait
	        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	        // Initialize JavascriptExecutor
	        js = (JavascriptExecutor) driver;

	        System.out.println("Browser setup complete.");
	    }

	    @Test(priority = 1)
	    public void navigateToWebsite() {
	        // Navigate to the website
	        driver.get("https://ditinteractive.com/");
	        System.out.println("Navigated to DitInteractive website.");
	    }
	    
	    @Test(priority = 2)
	   
	    	public void navigateToAboutPage() throws InterruptedException {
	            // Hover over the "About" menu item
	            WebElement aboutMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//li[@id='menu-item-23395']//a")));
	            Actions actions = new Actions(driver);
	            actions.moveToElement(aboutMenu).perform();
	            System.out.println("Hovered over the About menu.");

	            // Click on "About" button
	            aboutMenu.click();
	            Thread.sleep(5000);
	            System.out.println("Navigated to About page.");
	            
	            // Perform continuous scroll
	            continuousScroll("About");
	            
	    }
	    

	    // Method to perform continuous scrolling
	    public void continuousScroll(String pageName) {
	        try {
	            System.out.println("Starting continuous scroll on " + pageName + " page...");

	            // Get the total page height
	            long pageHeight = (long) js.executeScript("return document.body.scrollHeight;");
	            long currentScrollPosition = 0; // Start from the top
	            int scrollStep = 200; // Step in pixels for each scroll
	            int delayBetweenScrolls = 500; // Delay in milliseconds for moderate speed

	            // Scroll down in steps until reaching the bottom
	            while (currentScrollPosition < pageHeight) {
	                js.executeScript("window.scrollBy(0, " + scrollStep + ");");
	                currentScrollPosition += scrollStep;

	                System.out.println("Scrolled to position: " + currentScrollPosition);
	                Thread.sleep(delayBetweenScrolls); // Wait between scrolls for moderate speed

	                // Update the page height dynamically in case the page loads additional content
	                pageHeight = (long) js.executeScript("return document.body.scrollHeight;");
	            }

	            System.out.println("Reached the bottom of " + pageName + " page.");
	        } catch (Exception e) {
	            System.err.println("Error during scrolling on " + pageName + ": " + e.getMessage());
	        }
	    }
	
	@AfterClass
    public void tearDown() {
        // Close the browser
        if (driver != null) {
             driver.quit();
            System.out.println("Browser closed.");
        }
    }
}



