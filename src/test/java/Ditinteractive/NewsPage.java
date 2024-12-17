package Ditinteractive;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NewsPage {
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
    public void ShopifyPage() {
        navigateToNewsCategory("//a[contains(@href,'https://ditinteractive.com/category/shopify/')]//span[contains(@class,'quadmenu-item-content')]//span[contains(@class,'hover t_1000')][normalize-space()='Shopify']", "Shopify");
    }

    @Test(priority = 3)
    public void BigcommercePage() {
        navigateToNewsCategory("//span[contains(text(),'Bigcommerce')]", "Bigcommerce");
    }

    @Test(priority = 4)
    public void EcommercePage() {
        navigateToNewsCategory("//span[contains(@class,'hover t_1000')][normalize-space()='Ecommerce']", "Ecommerce");
    }

    @Test(priority = 5)
    public void WordPressPage() {
        navigateToNewsCategory("//span[contains(text(),'WordPress')]", "WordPress");
    }

    @Test(priority = 6)
    public void MagentoPage() {
        navigateToNewsCategory("//span[contains(@class,'hover t_1000')][normalize-space()='Magento']", "Magento");
    }
    
    @Test(priority = 7)
    public void ReactJSPage() {
        navigateToNewsCategory("//a[contains(@href,'https://ditinteractive.com/category/react-js/')]//span[contains(@class,'quadmenu-item-content')]//span[contains(@class,'hover t_1000')][normalize-space()='React JS']", "React JS");
    }
    
    @Test(priority = 8)
    public void BlogPage() {
        navigateToNewsCategory("//span[normalize-space()='Headless CMS Integration with React for Streamlined Content Management']", "Blog page");
    }
    
    @Test(priority = 9)
    public void BlogPage1() {
        navigateToNewsCategory("//span[normalize-space()='Optimizing Your Magento Store for Mobile-First Shopping']", "Blog Page 1");
    }
    
    @Test(priority = 10)
    public void BlogPage2() {
        navigateToNewsCategory("//span[normalize-space()='The DIT Interactive Difference: Why Choose Us for Your WooCommerce Needs in the USA']", "Blog Page 2");
    }
    
    @Test(priority = 11)
    public void BlogPage3() {
        navigateToNewsCategory("//li[@id='menu-item-23436']//span[contains(@class,'quadmenu-item-content')]//span[1]", "Blog Page 3");
    }
    

    // Helper method to navigate to a service category and scroll the page
    private void navigateToNewsCategory(String categoryXPath, String categoryName) {
        try {
            // Hover over the "Services" menu item
            WebElement servicesMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(@class,'hover t_1000')][normalize-space()='News']")));
            Actions actions = new Actions(driver);
            actions.moveToElement(servicesMenu).perform();
            System.out.println("Hovered over the News menu.");

            // Click on the specified category
            WebElement categoryButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(categoryXPath)));
            categoryButton.click();
            System.out.println("Clicked on " + categoryName + " category.");

            // Perform continuous scrolling
            continuousScroll(categoryName);
        } catch (Exception e) {
            System.err.println("An error occurred while navigating to " + categoryName + ": " + e.getMessage());
            e.printStackTrace();
        }
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
