package paginas;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home {
	@FindBy(id="menu_admin_viewAdminModule")
	WebElement lnkMenuAdmin;
	
	@FindBy(id="menu_admin_UserManagement")
	WebElement lnkMenuUsers;
	
	@FindBy(xpath="//a[@id='menu_admin_viewSystemUsers']")
	WebElement lnkMenuViewUsers;
	
	@FindBy(id="menu_pim_viewPimModule")
	WebElement lnkMenuPIM;
	
	@FindBy(id="menu_pim_addEmployee")
	WebElement lnkAddEmployee;
	
	@FindBy(css="#btnAdd")
	WebElement btnAddUser;
	
	@FindBy(id="menu_dashboard_index")
	WebElement lnkDashboard;
	
	WebDriver driver;
	
	public Home(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	public void goToAddUser() {
		lnkMenuAdmin.click();
		lnkMenuUsers.click();
		lnkMenuViewUsers.click();
		btnAddUser.click();
	}
	
	public void goToMenuAdmin() {
		lnkMenuAdmin.click();
	}
	
	public void clicOnDashboard() {
		lnkDashboard.click();
	}
	
	public void goToPIMMenu() {
		lnkMenuPIM.click();
	}
	
	public void goToAddEmployee() {
		lnkAddEmployee.click();
	}
}
