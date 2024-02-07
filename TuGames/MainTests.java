
import org.junit.Test;

public class MainTests extends MetodsClass {

    String url ="htte.ru/ ";
    String urlGame ="https://games";

    String emailLeading = "";
    String inputEmailLeading = "tset_12345";
    String passwordLeading = "Qw%";
    String leading =".button__red";
    String nameLeading = "Сергей";
    String phoneLeading = "+768434";

    String emailPlayer = "tse.ru";
    String inputEmailPlayer = "ts56";
    String passwordPlayer = "Q*";
    String player = ".button__green";
    String namePlayer = "Иван";
    String phonePlayer= "+765434";

    String emailAdmin = "ts.ru";
    String inputEmailAdmin = "tset7";
    String passwordAdmin = "Qw%*";
    String nameAdmin = "Иван";
    String surNameAdmin = "Рогов";
    String phoneAdmin= "+76854565434";


    @Test
    public void registredleading() {
        registration(emailLeading,inputEmailLeading,passwordLeading,leading,nameLeading,phoneLeading);
    }

    @Test
    public void registredplayer() {
        registration(emailPlayer,inputEmailPlayer,passwordPlayer,player,namePlayer,phonePlayer);
    }

    @Test
    public void deleteAccount() {
        autorization(emailLeading,inputEmailLeading,passwordLeading);
        deleteAccount();
    }

    @Test
    public void autorizatLeading() {

        autorization(emailLeading,inputEmailLeading,passwordLeading);
    }

    @Test
    public void autorizatPlayer() {

        autorization(emailPlayer,inputEmailPlayer,passwordPlayer);
    }

    @Test
    public void autorizatAdmin() {

        autorization(emailAdmin,inputEmailAdmin,passwordAdmin);
    }

    @Test
    public void buyAccess()  {
        autorization(emailPlayer,inputEmailPlayer,passwordPlayer);
        buyAccessGame();
    }

    @Test
    public void changeParamPhoneMail() {
        autorization(emailPlayer,inputEmailPlayer,passwordPlayer);
        changePhoneEmail("34534","Иванов","asd@qwe.com");
    }

    @Test
    public void changeParamNameSurname() {
        autorization(emailPlayer,inputEmailPlayer,passwordPlayer);
        changeNameSurname("Александр","Пушкин");
    }

    @Test
    public void createSessions() {
        autorization(emailPlayer,inputEmailPlayer,passwordPlayer);
        createSession();
    }

    @Test
    public void addUserInSession() {
        autorization(emailPlayer,inputEmailPlayer,passwordPlayer);
        addUserInSessions();
    }

    String deletingUser = "Федор Апраксин";

    @Test
    public void deletingUserFromSession(){
        autorization(emailPlayer,inputEmailPlayer,passwordPlayer);
        addUserInSessions();
        deleteUser(deletingUser);
    }

    @Test
    public void previousSessionPayment(){
        autorization(emailPlayer,inputEmailPlayer,passwordPlayer);
        primaSessionPayment();

    }

    @Test
    public void leadingHistoryayPmentIncome(){
        autorization(emailLeading,inputEmailLeading,passwordLeading);
        leadingHistoryPaymentIncomGames();

    }

    @Test
    public void adminCreateleading(){
        autorization(emailAdmin,inputEmailAdmin,passwordAdmin);
        adminCreateLead(surNameAdmin ,nameAdmin ,phoneAdmin);

    }
}
