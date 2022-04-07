package CucumberFiles;

public class Xpath {

	static String textBox = "//input[@class='new-todo ng-pristine ng-valid ng-touched']";
	static String textBox1 = "//input[@class='new-todo ng-valid ng-touched ng-dirty']";
	static String enter = "//input[@class='new-todo ng-valid ng-touched ng-dirty ng-valid-parse']";
	static String label = "//label[@class='ng-binding']";
	static String active = "//a[contains(text(),'Active')]";
	static String completed = "//a[contains(text(),'Completed')]";
	static String delete = "//button[@class='destroy']";
	static String textBox2 = "//input[@class='toggle ng-pristine ng-untouched ng-valid']";
	static String labeltest = "//li[@class='ng-scope completed']";
	static String bindingText = "//strong[@class='ng-binding']";
}
