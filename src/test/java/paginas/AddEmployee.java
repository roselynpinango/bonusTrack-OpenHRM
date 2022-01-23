package paginas;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddEmployee {
	@FindBy(name="firstName")
	WebElement txtFirstName;
	
	@FindBy(id="middleName")
	WebElement txtMiddleName;
	
	@FindBy(xpath="//input[@id='lastName']")
	WebElement txtLastName;
	
	@FindBy(id="photofile")
	WebElement filePhotograph;
	
	@FindBy(css="#btnSave")
	WebElement btnSave;
	
	WebDriver driver;
	
	public AddEmployee(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}
	
	public void completeAddEmployeeForm(String firstName,
										String middleName,
										String lastName,
										String photograph) {
		txtFirstName.sendKeys(firstName);
		txtMiddleName.sendKeys(middleName);
		txtLastName.sendKeys(lastName);
		filePhotograph.sendKeys(photograph);
	}
	
	public void clicOnSave() {
		btnSave.click();
	}
}
