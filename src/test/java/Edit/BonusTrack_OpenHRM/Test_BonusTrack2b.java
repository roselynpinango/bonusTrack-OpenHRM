package Edit.BonusTrack_OpenHRM;

import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Test_BonusTrack2b {
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
		
		// Acceder a la opción de Registro de Usuario
		driver.findElement(By.id("menu_admin_viewAdminModule")).click();
		driver.findElement(By.id("menu_admin_UserManagement")).click();
		
		driver.findElement(By.xpath("//a[@id='menu_admin_viewSystemUsers']")).click();
		driver.findElement(By.cssSelector("#btnAdd")).click();
		
		// Completar el formulario de registro de usuario
		Select userRole = new Select(driver.findElement(By.name("systemUser[userType]")));
		userRole.selectByValue("1");
		
		driver.findElement(By.id("systemUser_employeeName_empName")).sendKeys("Enrique Lopez");
		driver.findElement((By.xpath("//input[@id='systemUser_userName']"))).sendKeys("usuario0");
		
		Select status = new Select(driver.findElement(By.cssSelector("#systemUser_status")));
		status.selectByVisibleText("Enabled");
		
		driver.findElement(By.name("systemUser[password]")).sendKeys("ABCdef123");
		driver.findElement(By.id("systemUser_confirmPassword")).sendKeys("ABCdef123");
		
		driver.findElement(By.id("btnSave")).click();
	}
}
