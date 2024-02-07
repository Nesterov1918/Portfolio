import org.checkerframework.checker.units.qual.K;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MetodsClass
{
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(10000,
                TimeUnit.MILLISECONDS);
    }

   /* @After
    public void tearDown() {
        driver.quit();
    }*/

    public  void registration(String a,String b,String c,String d,String e,String f) {

        driver.navigate().to("ht/");
        String game = driver.getWindowHandle();
        driver.findElement(By.xpath("//*[@class = '']")).sendKeys(a);
        driver.findElement(By.xpath("//*[@class='button__white app__login--btn']")).click();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.navigate().to("https://mail.ru/");
        driver.findElement(By.xpath("//button[.='Войти']")).click();
        var frame = driver.findElement(By.xpath("//iframe[contains(@src, 'https://account.mail.ru/login')]"));
        driver.switchTo().frame(frame);
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(b);
        driver.findElement(By.xpath("//button[.='Ввести пароль']")).click();
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(c);
        driver.findElement(By.xpath("(//button[.='Войти'])[2]")).click();
        var text= driver.findElement(By.xpath("(//span[@class='ll-sp__normal'])[1]")).getText().substring(22, 28);
        List<String> code = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            code.add(String.valueOf(text.charAt(i)));
        }
        driver.switchTo().window(game);
        List<WebElement> testCollection = driver.findElements(By.cssSelector(".app__login--verification"));
        for(int i =0; i<code.size(); i++){
            testCollection.get(i).sendKeys(code.get(i));
        }

        driver.findElement(By.xpath("//button")).click();
        driver.findElement(By.cssSelector(d)).click();
        driver.findElement(By.xpath("//*[@placeholder='Имя']")).sendKeys(e);
        driver.findElement(By.xpath("//*[@placeholder='Телефон']")).sendKeys(f);
        driver.findElement(By.xpath("//button")).click();
        System.out.println(" Тест registration- пройден");
    }

    public void autorization(String a,String b,String c){

        driver.navigate().to("htte.ru/");
        String game = driver.getWindowHandle();
        driver.findElement(By.xpath("//*[@class = '']")).sendKeys(a);
        driver.findElement(By.xpath("//*[@class='button__white app__login--btn']")).click();
        driver.switchTo().newWindow(WindowType.TAB);
        driver.navigate().to("https://mail.ru/");
        driver.findElement(By.xpath("//button[.='Войти']")).click();
        var frame = driver.findElement(By.xpath("//iframe[contains(@src, 'httplogin')]"));
        driver.switchTo().frame(frame);
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(b);
        driver.findElement(By.xpath("//button[.='Ввести пароль']")).click();
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(c);
        driver.findElement(By.xpath("(//button[.='Войти'])[2]")).click();
        var text= driver.findElement(By.xpath("(//span[@class='ll-sp__normal'])[1]")).getText().substring(22, 28);
        List<String> code = new ArrayList<>();
        for (int i = 0; i < text.length(); i++) {
            code.add(String.valueOf(text.charAt(i)));
        }
        driver.switchTo().window(game);

        List<WebElement> testCollection = driver.findElements(By.cssSelector(".app__login--verification"));
        for(int i =0; i<code.size(); i++){
            testCollection.get(i).sendKeys(code.get(i));
        }
        driver.findElement(By.cssSelector(".button__white.app__login--btn")).click();
        System.out.println(" Тест autorization- пройден");
    }

    public void deleteAccount(){

        driver.findElement(By.cssSelector(".action__button-arrow")).click();
        driver.findElement(By.xpath("//button[.='Мой профиль']")).click();
        driver.findElement(By.xpath("//button[@class='gamer-account__delete']")).click();
        driver.findElement(By.cssSelector(".button__red")).click();
        var result =driver.findElement(By.xpath("//p[.='Ваш аккаунт был удалён']")).getText();
        var actualResult = "Ваш аккаунт был удалён";
        Assert.assertEquals(result, actualResult);
    }

    public void swichToOtherPage(String a){
        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(a);
    }

    public void buyAccessGame()  {

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get("httpgames");
        driver.findElement(By.cssSelector(".button__blue")).click();
        driver.findElement(By.xpath("//input[@type='date']")).sendKeys(Keys.ESCAPE);
        driver.findElement(By.xpath("//input[@type='date']")).sendKeys(Keys.SPACE);
        driver.findElement(By.xpath("//input[@type='date']")).sendKeys(Keys.ENTER);
        
        WebElement dropdown = driver.findElement(By.xpath("(//img[@alt='Открыть'])[2]"));
        dropdown.click();
        WebElement option = driver.findElement(By.xpath("//*[.='Полгода']"));
        option.click();
        driver.findElement(By.xpath("//button[.=' Оплатить ']")).click();
        driver.findElement(By.xpath("//button[.=' Оплатить ']")).click();
        driver.findElement(By.xpath("//button[.=' Успешно ']")).click();
        var actualResult = driver.findElement(By.xpath("//*[.='Доступные игры']")).getText();
        Assert.assertEquals(actualResult, "Доступные игры");
        System.out.println(" Тест buyAccess - пройден. ");

    }

    public void changePhoneEmail(String a,String b,String c){

        driver.findElement(By.cssSelector(".action__button-arrow")).click();
        driver.findElement(By.xpath("//button[.='Мой профиль']")).click();
        driver.findElement(By.cssSelector("#phone")).sendKeys("2244578");
        driver.findElement(By.cssSelector("#surname")).sendKeys("Trumph");
        driver.findElement(By.cssSelector("#email")).sendKeys(c);
        driver.findElement(By.xpath("//button[.=' Сохранить ']")).click();
        driver.findElement(By.xpath("//img[@alt='закрыть окно']")).click();
        driver.findElement(By.cssSelector("#phone")).clear();
        driver.findElement(By.cssSelector("#phone")).sendKeys(a);
        driver.findElement(By.cssSelector("#surname")).clear();
        driver.findElement(By.cssSelector("#surname")).sendKeys(b);
        driver.findElement(By.cssSelector("#email")).clear();
        driver.findElement(By.cssSelector("#email")).sendKeys("tset_12345@mail.ru");
        driver.findElement(By.xpath("//button[.=' Сохранить ']")).click();
        driver.findElement(By.xpath("//img[@alt='закрыть окно']")).click();

        WebElement inputElementPhone = driver.findElement(By.id("phone"));
        String resultPhone = inputElementPhone.getAttribute("value");
        WebElement inputElementSurname = driver.findElement(By.id("surname"));
        String resultSurname = inputElementSurname.getAttribute("value");
        WebElement inputElemenEmail = driver.findElement(By.id("email"));
        String resultemail = inputElemenEmail.getAttribute("value");

        Assert.assertEquals(resultPhone, a);
        Assert.assertEquals(resultSurname, b);

        System.out.println(" Тест changePhoneEmail- пройден");
    }


    public void changeNameSurname(String a,String b){

        driver.findElement(By.cssSelector(".action__button-arrow")).click();
        driver.findElement(By.xpath("//button[.='Мой профиль']")).click();
        driver.findElement(By.cssSelector("#name")).clear();
        driver.findElement(By.cssSelector("#name")).sendKeys(a);
        driver.findElement(By.cssSelector("#surname")).clear();
        driver.findElement(By.cssSelector("#surname")).sendKeys(b);
        driver.findElement(By.xpath("//button[.=' Сохранить ']")).click();
        driver.findElement(By.xpath("//img[@alt='закрыть окно']")).click();

        WebElement inputElementName = driver.findElement(By.id("name"));
        String resultName = inputElementName.getAttribute("value");

        WebElement inputElementSurname = driver.findElement(By.id("surname"));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        String resultSurname = (String) jsExecutor.executeScript("return arguments[0].value;", inputElementSurname);

        Assert.assertEquals(resultName, a);
        Assert.assertEquals(resultSurname, b);
        System.out.println(" Тест changeNameSurname- пройден");
    }


    public void createSession(){

        driver.findElement(By.xpath("(//*[.=' Создать сессию '])[1]")).click();

        driver.findElement(By.xpath("//input[@type='date']")).sendKeys(Keys.ESCAPE);
        driver.findElement(By.xpath("//input[@type='date']")).sendKeys(Keys.SPACE);
        driver.findElement(By.xpath("//input[@type='date']")).sendKeys(Keys.ENTER);

        driver.findElement(By.xpath("//input[@placeholder='__:__:__']")).click();

        driver.findElement(By.xpath("//input[@placeholder='__:__:__']")).sendKeys(Keys.ESCAPE);
        driver.findElement(By.xpath("//input[@placeholder='__:__:__']")).sendKeys(Keys.SPACE);
        driver.findElement(By.xpath("//input[@placeholder='__:__:__']")).sendKeys(Keys.DOWN);
        driver.findElement(By.xpath("//input[@placeholder='__:__:__']")).sendKeys(Keys.ENTER);

        WebElement element = driver.findElement(By.xpath("//input[@placeholder='__:__:__']"));
        String date = "22;22;22";
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].value = arguments[1]", element, date);

        WebElement dropdown = driver.findElement(By.xpath("(//img[@alt='Открыть'])[3]"));
        dropdown.click();
        WebElement option = driver.findElement(By.xpath("//*[.='Корпоративный']"));
        option.click();
        driver.findElement(By.xpath("//input[@type='number']")).sendKeys("11000");
        driver.findElement(By.xpath("//button[.=' Создать ']")).click();

    }


    public void addUserInSessions(){

        driver.findElement(By.xpath("//span[.='Игровые сессии']")).click();
        driver.findElement(By.xpath("(//*[.=' Участники / Оплата '])[1]")).click();

        driver.findElement(By.xpath("//span[.='Добавить участника']")).click();
        driver.findElement(By.xpath("(//input)[1]")).sendKeys("Меньшиков");
        driver.findElement(By.xpath("(//input)[2]")).sendKeys("Александр");
        driver.findElement(By.xpath("(//input)[3]")).sendKeys("+1234567");
        driver.findElement(By.xpath("//img[@alt='select']")).click();
        driver.findElement(By.xpath("//li[.='Участник']")).click();
        driver.findElement(By.xpath("//button[@class='button__green game-session-info__save']")).click();

        driver.findElement(By.xpath("//span[.='Добавить участника']")).click();
        driver.findElement(By.xpath("(//input)[1]")).sendKeys("Лефорт");
        driver.findElement(By.xpath("(//input)[2]")).sendKeys("Франц");
        driver.findElement(By.xpath("(//input)[3]")).sendKeys("+15656567");
        driver.findElement(By.xpath("//img[@alt='select']")).click();
        driver.findElement(By.xpath("//li[.='Участник']")).click();
        driver.findElement(By.xpath("//button[@class='button__green game-session-info__save']")).click();

        driver.findElement(By.xpath("//span[.='Добавить участника']")).click();
        driver.findElement(By.xpath("(//input)[1]")).sendKeys("Апраксин");
        driver.findElement(By.xpath("(//input)[2]")).sendKeys("Федор");
        driver.findElement(By.xpath("(//input)[3]")).sendKeys("+1245567");
        driver.findElement(By.xpath("//img[@alt='select']")).click();
        driver.findElement(By.xpath("//li[.='Участник']")).click();
        driver.findElement(By.xpath("//button[@class='button__green game-session-info__save']")).click();

        driver.findElement(By.xpath("//span[.='Добавить участника']")).click();
        driver.findElement(By.xpath("(//input)[1]")).sendKeys("Александр");
        driver.findElement(By.xpath("(//input)[2]")).sendKeys("Пушкин");
        driver.findElement(By.xpath("(//input)[3]")).sendKeys("+76854565434");
        driver.findElement(By.xpath("//img[@alt='select']")).click();
        driver.findElement(By.xpath("//li[.='Участник']")).click();
        driver.findElement(By.xpath("//button[@class='button__green game-session-info__save']")).click();

    }


    public void deleteUser(String deletingUser){

        driver.findElement(By.xpath("//span[.='Добавить участника']"));
        driver.findElement(By.xpath("//div[.='"+deletingUser+"']/following-sibling::div//button[@class='button__red']")).click();
        driver.findElement(By.xpath("//div[@class='basic-modal']//button[@class='button__red']")).click();

    }


    public void primaSessionPayment(){

        driver.findElement(By.xpath("//button[@class='button__red']")).click();
        driver.findElement(By.xpath("//button[@class='button__green']")).click();

    }


    public  void leadingHistoryPaymentIncomGames(){

        driver.findElement(By.xpath("//*[@class='action__button-arrow']")).click();
        driver.findElement(By.xpath("//button[.='История оплат']")).click();

        var r = driver.findElement(By.xpath("//span[.='Сумма оплаты']")).getText();
        System.out.println(r);

        String url2 = driver.getCurrentUrl();
        Assert.assertEquals(url2, "htnts/");
        System.out.println("Перешли на страницу История оплат ");

        driver.findElement(By.xpath("//span[.='История доходов']")).click();

        String url1 = driver.getCurrentUrl();
        Assert.assertEquals(url1, "httpl/incomes");
        System.out.println("Перешли на страницу История доходов ");

        driver.findElement(By.xpath("//a[.='Игры']")).click();

        String url3 = driver.getCurrentUrl();
        Assert.assertEquals(url3, "htt/games");
        System.out.println("Перешли на страницу Игры");


    }


    public void adminCreateLead(String a,String b,String c){

        driver.findElement(By.xpath("//a[.='Создать ведущего']")).click();
        driver.findElement(By.xpath("//*[@placeholder='Фамилия']")).sendKeys(a);
        driver.findElement(By.xpath("//*[@placeholder='Имя']")).sendKeys(b);
        driver.findElement(By.xpath("//*[@placeholder='+_  _ _ _   _ _ _  _ _  _ _']")).sendKeys(c);
        driver.findElement(By.xpath("//button[.=' Добавить ']")).click();

    }
}