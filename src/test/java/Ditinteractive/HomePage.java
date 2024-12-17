package Ditinteractive;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

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

        System.out.println("Browser setup complete.");
    }

    @Test(priority = 1)
    public void navigateToWebsite() {
        // Navigate to the website
        driver.get("https://ditinteractive.com/");
        System.out.println("Navigated to DitInteractive website.");
    }

    @Test(priority = 2)
    public void testCart() {
        try {
            // Wait for the "Get Started" button to be clickable
            WebElement getStartedButton = wait.until(
                ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Get Started']"))
            );

            // Click on the "Get Started" button
            getStartedButton.click();
            System.out.println("Get Started button clicked, and new page may open.");

            // Wait briefly for the new window to open
            Thread.sleep(2000); // Sleep added to allow the new window to open

            // Get the current (main) window handle
            String mainWindow = driver.getWindowHandle();

            // Loop through all open windows and switch to the new one
            for (String windowHandle : driver.getWindowHandles()) {
                if (!windowHandle.equals(mainWindow)) {
                    // Switch to the new window
                    driver.switchTo().window(windowHandle);
                    System.out.println("Switched to the new window.");
                    
                    // Close the new window
                    driver.close();
                    System.out.println("New window closed.");
                }
            }

            // Switch back to the main window (ensures that the main window stays open)
            driver.switchTo().window(mainWindow);
            System.out.println("Switched back to the main window.");

            // Scroll the main window by 600 pixels
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0, 600);");
            System.out.println("Scrolled the main window by 600 pixels.");

        } catch (Exception e) {
            System.out.println("An error occurred during testCart execution: " + e.getMessage());
            e.printStackTrace();
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
