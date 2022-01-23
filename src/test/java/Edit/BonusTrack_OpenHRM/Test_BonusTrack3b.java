package Edit.BonusTrack_OpenHRM;

import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class Test_BonusTrack3b {
	WebDriver driver;
	File screen;
	String urlEsperada = "https://opensource-demo.orangehrmlive.com/index.php/admin/saveSystemUser";
	
	@BeforeSuite
	public void setUp() throws Exception {
		System.out.println("Inicio de Suite de Pruebas");
		
		// Establecer la ubicación del driver
		System.setProperty("webdriver.chrome.driver", "..\\BonusTrack-OpenHRM\\Drivers\\chromedriver.exe");
				
		// Crear la instancia de Driver: abrir la página y maximizarla
		driver = new ChromeDriver();
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.manage().window().maximize();
		
		screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File("..\\BonusTrack-OpenHRM\\Evidencias\\Test_BonusTrack3b\\01_pantallaprincipal.png"));
	}
	
	@Test
	public void login() throws Exception {
		// Completar el formulario de login y hacer clic en el boton
		driver.findElement(By.id("txtUsername")).sendKeys("Admin");
		driver.findElement(By.id("txtPassword")).sendKeys("admin123");
		
		screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File("..\\BonusTrack-OpenHRM\\Evidencias\\Test_BonusTrack3b\\02_credencialesLogin.png"));
		
		driver.findElement(By.id("btnLogin")).click();
		
		screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File("..\\BonusTrack-OpenHRM\\Evidencias\\Test_BonusTrack3b\\03_pantallahrm.png"));
		
		// Acceder a la opción de Registro de Usuario
		driver.findElement(By.id("menu_admin_viewAdminModule")).click();
		driver.findElement(By.id("menu_admin_UserManagement")).click();
		
		driver.findElement(By.xpath("//a[@id='menu_admin_viewSystemUsers']")).click();
		driver.findElement(By.cssSelector("#btnAdd")).click();
		
		screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File("..\\BonusTrack-OpenHRM\\Evidencias\\Test_BonusTrack3b\\04_formularioUsuario.png"));
		
		// Completar el formulario de registro de usuario
		Select userRole = new Select(driver.findElement(By.name("systemUser[userType]")));
		userRole.selectByValue("1");
		
		driver.findElement(By.id("systemUser_employeeName_empName")).sendKeys("Enrique Lopez");
		driver.findElement((By.xpath("//input[@id='systemUser_userName']"))).sendKeys("usuario0");
		
		Select status = new Select(driver.findElement(By.cssSelector("#systemUser_status")));
		status.selectByVisibleText("Enabled");
		
		driver.findElement(By.name("systemUser[password]")).sendKeys("ABCdef123");
		driver.findElement(By.id("systemUser_confirmPassword")).sendKeys("ABCdef123");
		
		screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File("..\\BonusTrack-OpenHRM\\Evidencias\\Test_BonusTrack3b\\05_formularioLleno.png"));
		
		driver.findElement(By.id("btnSave")).click();
		
		Assert.assertEquals(urlEsperada, driver.getCurrentUrl());
	}
	
	@AfterSuite
	public void tearDown() {
		System.out.println("Fin de Suite de Pruebas");
	}
}
