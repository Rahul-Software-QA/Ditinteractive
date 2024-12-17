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

public class ServicePage {
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
    public void BigcommercePage() {
        navigateToServiceCategory("//span[contains(text(),'bigcommerce')]", "BigCommerce");
    }

    @Test(priority = 3)
    public void MagentoPage() {
        navigateToServiceCategory("//span[contains(text(),'magento')]", "Magento");
    }

    @Test(priority = 4)
    public void ShopifyPage() {
        navigateToServiceCategory("//a[contains(@href,'/shopify-development/')]//span[contains(text(),'Shopify')]", "Shopify");
    }

    @Test(priority = 5)
    public void WoocommercePage() {
        navigateToServiceCategory("//span[contains(text(),'Woocommerce')]", "WooCommerce");
    }

    @Test(priority = 6)
    public void FrontendPage() {
        navigateToServiceCategory("//span[contains(text(),'Front-End')]", "Front-End Development");
    }
    
    @Test(priority = 6)
    public void NodeJSPage() {
        navigateToServiceCategory("//span[contains(@class,'hover t_1000')][normalize-space()='Node JS']", "Node JS");
    }
    
    @Test(priority = 7)
    public void ReactJSPage() {
        navigateToServiceCategory("//a[contains(@href,'https://ditinteractive.com/reactjs-development/')]//span[contains(@class,'quadmenu-item-content')]//span[contains(@class,'hover t_1000')][normalize-space()='React JS']", "React JS");
    }
    
    @Test(priority = 8)
    public void wordpressPage() {
        navigateToServiceCategory("//span[contains(text(),'wordpress')]", "wordpress");
    }
    
    @Test(priority = 9)
    public void BrandingdesignPage() {
        navigateToServiceCategory("//span[contains(text(),'Branding Design')]", "Branding Design");
    }
    
    @Test(priority = 10)
    public void LogodesignPage() {
        navigateToServiceCategory("//span[contains(text(),'Logo Design')]", "Logo Design");
    }
    
    @Test(priority = 11)
    public void UIUXdesignPage() {
        navigateToServiceCategory("//span[contains(text(),'UI/UX Design')]", "UI/UX Design");
    }
    
    @Test(priority = 12)
    public void MobileAppdesignPage() {
        navigateToServiceCategory("//span[contains(text(),'Mobile App Design')]", "Mobile App Design");
    }
    
    @Test(priority = 13)
    public void EcommercedesignPage() {
        navigateToServiceCategory("//span[contains(text(),'Ecommerce Design')]", "Ecommerce Design");
    }
    
    @Test(priority = 14)
    public void ContentMarketingPage() {
        navigateToServiceCategory("//span[contains(text(),'Content Marketing')]", "Content Marketing");
    }
    
    @Test(priority = 15)
    public void LandingpageDesigning() {
        navigateToServiceCategory("//span[contains(text(),'Landing Page Designing')]", "Landing page Designing");
    }
    
    @Test(priority = 16)
    public void PPCPage() {
        navigateToServiceCategory("//a[contains(@href,'https://ditinteractive.com/ppc-services/')]//span[contains(@class,'quadmenu-item-content')]", "PPC");
    }
    
    @Test(priority = 17)
    public void SEOPage() {
        navigateToServiceCategory("//a[contains(@href,'https://ditinteractive.com/seo-services/')]//span[contains(@class,'quadmenu-item-content')]", "SEO");
    }
    
    @Test(priority = 18)
    public void SocialMediaMarketingPage() {
        navigateToServiceCategory("//span[contains(text(),'Social Media Marketing')]", "Social Media Marketing");
    }
    
    @Test(priority = 19)
    public void CROPage() {
        navigateToServiceCategory("//span[contains(text(),'CRO')]", "CRO");
    }

    // Helper method to navigate to a service category and scroll the page
    private void navigateToServiceCategory(String categoryXPath, String categoryName) {
        try {
            // Hover over the "Services" menu item
            WebElement servicesMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Services')]")));
            Actions actions = new Actions(driver);
            actions.moveToElement(servicesMenu).perform();
            System.out.println("Hovered over the Services menu.");

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
