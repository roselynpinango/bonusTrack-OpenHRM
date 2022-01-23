package Edit.BonusTrack_OpenHRM;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test_BonusTrack1b {
	@Test
	public void login() {
		// Establecer la ubicación del driver
		System.setProperty("webdriver.chrome.driver", "..\\BonusTrack-OpenHRM\\Drivers\\chromedriver.exe");
		
		// Crear la instancia de Driver: abrir la página y maximizarla
		WebDriver driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.manage().window().maximize();
		
		// Completar el formulario de login y hacer clic en el boton
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		driver.findElement(By.id("btnLogin")).click();
	}
}
