package pruebas;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Utilidades.DatosExcel;
import paginas.AddEmployee;
import paginas.AddUser;
import paginas.Home;
import paginas.Login;

public class Test_BonusTrack5b {
	WebDriver driver;
	File screen;
	String urlEsperada = "https://opensource-demo.orangehrmlive.com/index.php/admin/saveSystemUser";
	String chromeDriverPath = "..\\BonusTrack-OpenHRM\\Drivers\\chromedriver.exe";
	String firefoxDriverPath = "..\\BonusTrack-OpenHRM\\Drivers\\geckodriver.exe";
	
	@BeforeSuite
	public void setUp() throws Exception {
		System.out.println("Inicio de Suite de Pruebas");
		
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		driver = new ChromeDriver();
		
		// Crear la instancia de Driver: abrir la página y maximizarla
		driver.get("https://opensource-demo.orangehrmlive.com/");
		driver.manage().window().maximize();
		
		screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File("..\\BonusTrack-OpenHRM\\Evidencias\\Test_BonusTrack5b\\01_pantallaprincipal.png"));
		
		// Completar el formulario de login y hacer clic en el boton
		Login login = new Login(driver);
		login.enterCredentials("Admin", "admin123");
				
		screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File("..\\BonusTrack-OpenHRM\\Evidencias\\Test_BonusTrack5b\\02_credencialesLogin.png"));
				
		login.clicOnLogin();
				
		screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File("..\\BonusTrack-OpenHRM\\Evidencias\\Test_BonusTrack5b\\03_pantallahrm.png"));
				
	}
	
	@Test(dataProvider="Datos Add User")
	public void login(	String userRole,
						String employeeName,
						String status,
						String userName,
						String password,
						String confirmPassword) throws Exception {
		// Acceder a la opción de Registro de Usuario
		Home home = new Home(driver);
		home.goToAddUser();
		
		screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File("..\\BonusTrack-OpenHRM\\Evidencias\\Test_BonusTrack5b\\04_formularioUsuario_" + userName + ".png"));
		
		// Completar el formulario de registro de usuario
		AddUser addUser = new AddUser(driver);
		addUser.completeAddUserForm(userRole, 
									employeeName, 
									userName, 
									status, 
									password, 
									confirmPassword);
		
		screen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screen, new File("..\\BonusTrack-OpenHRM\\Evidencias\\Test_BonusTrack5b\\05_formularioLleno_" + userName + ".png"));
		
		addUser.clicOnSave();
		
		Assert.assertEquals(urlEsperada, driver.getCurrentUrl());
		
		home.clicOnDashboard();
	}
	
	@Test(dataProvider="Datos Modify User")
	public void modifyUser(String userName, String newUserName) {
		Home home = new Home(driver);
		home.goToMenuAdmin();
		
		driver.findElement(By.xpath("//a[contains(text(),'" + userName + "')]")).click();
		
		AddUser addUser = new AddUser(driver);
		addUser.clicOnSave();
		
		addUser.modifyUsername(newUserName);
		//addUser.clicOnSave(); -- Se comenta para no hacer la modificación y poder probar varias veces
	}
	
	@Test(dataProvider="Datos Add Employee")
	public void addEmployee(String firstName,
							String middleName,
							String lastName,
							String photograph) {
		Home home = new Home(driver);
		home.goToPIMMenu();
		home.goToAddEmployee();
		
		AddEmployee addEmployee = new AddEmployee(driver);
		addEmployee.completeAddEmployeeForm(firstName, middleName, lastName, photograph);
		addEmployee.clicOnSave();
	}
	
	@AfterSuite
	public void tearDown() {
		System.out.println("Fin de Suite de Pruebas");
	}
	
	@DataProvider(name="Datos Add User")
	public Object[][] getAddUserData() throws Exception {
		return DatosExcel.leerExcel("..\\BonusTrack-OpenHRM\\Datos\\Datos_BonusTrack5b.xlsx", "Hoja1");
	}
	
	@DataProvider(name="Datos Modify User")
	public Object[][] getModifyUserData() throws Exception {
		return DatosExcel.leerExcel("..\\BonusTrack-OpenHRM\\Datos\\Datos_BonusTrack5b_Modify.xlsx", "Hoja1");
	}
	
	@DataProvider(name="Datos Add Employee")
	public Object[][] getAddEmployeeData() throws Exception {
		return DatosExcel.leerExcel("..\\BonusTrack-OpenHRM\\Datos\\Datos_BonusTrack5b_AddEmployee.xlsx", "Hoja1");
	}
}
