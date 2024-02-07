package api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Main_Tests {

    private final static String url = "https://reqres.in";

    @Test
    public void getVacanciesSalaryStatistic() {// Получение всех вакансий с портала и статистики по зарплатам

        Specifications.installSpecification(Specifications.requestSpecification(url),Specifications.responseSpecification200());

        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .get("http://opendata.trudvsem.ru/api/v1/vacancies")//post delete put
                .then().log().all()
                .extract().response();

        JsonPath jsonPath = response.jsonPath();
        String companyName = jsonPath.getString("results.vacancies[4].vacancy.company.name");//получаем конкретную компанию из нулевого элемента
        String region = jsonPath.getString("results.vacancies[0].vacancy.region.name");//Получаем конкретное название региона
        List <String> job = jsonPath.get("results.vacancies.vacancy.job-name");//Получаем список вакансий из всего массива
        List <Integer> salary = jsonPath.get("results.vacancies.vacancy.salary_min");//Получаем список вакансий из всего массива

        System.out.println("Company Name: " + companyName);
        System.out.println("Region Name: " + region);

        for(String e : job){
            System.out.println(" Вакансия " + e);
        }

        int x =0;
        int y =0;

        for(Integer e : salary){// Задание по ТЗ
            if(e <40000){
                System.out.println(" Зарплата более 40т - " + e);
                 x++;
                        }
                System.out.println(" Зарплата менее 40т - " + e);
                 y++;
        }
        System.out.println("Всего хороших зарплат - "+ x);
        System.out.println("Всего плохих зарплат - "+ y);
    }


    @Test// Получаем конкретную компанию с помощью POJO класса
    public void getCompanyWithPojo() {
        RestAssured.baseURI = "http://opendata.trudvsem.ru";

        Response response = given()
                .when()
                .get("/api/v1/vacancies/vacancy/1145658034807/09d5a4e6-9e75-11ea-a7e7-736ab11edb0c")
                .then()
                .extract().response();

        Company companyInfo = response.jsonPath().getObject("results.vacancies[0].vacancy.company", Company.class);

        System.out.println(" Проверка вывода - " + companyInfo.getKpp());


        // Преобразование объекта Company в Map
        Map<String, Object> companyMap = companyToMap(companyInfo);

        // Распечатка Map
        for (Map.Entry<String, Object> entry : companyMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    // Преобразование объекта Company в Map
    private static Map<String, Object> companyToMap(Company company){
            Map<String, Object> map = new HashMap<>();
            map.put("companycode", company.getCompanycode());
            map.put("inn", company.getInn());
            map.put("kpp", company.getKpp());
            map.put("name", company.getName());
            map.put("ogrn", company.getOgrn());
            map.put("site", company.getSite());
            map.put("url", company.getUrl());

            return map;
        }


        @Test// Ответ упаковываем и выводим в виде JSON
        public void getCompanyWithPojoTransformJSON() throws JsonProcessingException {//Получение ответа и преобразование ответа в JSON
            RestAssured.baseURI = "http://opendata.trudvsem.ru";

            Response response = given()
                    .when()
                    .get("/api/v1/vacancies/vacancy/1145658034807/09d5a4e6-9e75-11ea-a7e7-736ab11edb0c")
                    .then()
                    .extract().response();

            JsonPath jsonPath = response.jsonPath();

            // Получаем объект компании
            Map<String, Object> company = jsonPath.getMap("results.vacancies[0].vacancy.company");

            System.out.println(" Проверка печати - " + company.get("ogrn"));

            // Проверяем, что "company" не равно null
            if (company != null) {
                // Преобразовываем Map в JSON строку
                String jsonCompany = convertMapToJsonString(company);

                // Распечатываем JSON строку
                System.out.println(jsonCompany);
            } else {
                System.out.println("Company is null");
            }
        }

    private static String convertMapToJsonString(Map<String, Object> map) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.valueToTree(map);
            return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(jsonNode);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Test
    public void checkAvatarIdTest(){

        Specifications.installSpecification(Specifications.requestSpecification(url),Specifications.responseSpecification200());

        List<UserData_GET> users = given()
                .when()
                .get("/api/users?page=2")
                .then().log().all()
                .extract().body().jsonPath().getList("data", UserData_GET.class);

        users.forEach(x -> Assert.assertTrue(x.getAvatar().contains(x.getId().toString())));//проверили что все аватары содержат id
        Assert.assertTrue(users.stream().allMatch(x -> x.getEmail().endsWith("@reqres.in")));// все совпадения..получить емеил и проверить что заканчивантся на..

        List<String> avatar = users.stream().map(UserData_GET::getAvatar).collect(Collectors.toList());
        List<String> idStr = users.stream().map(x ->x.getId().toString()).collect(Collectors.toList());
        List<Integer> id = users.stream().map(UserData_GET::getId).collect(Collectors.toList());

        for(int i =0; i< avatar.size(); i++){
            Assert.assertTrue(avatar.get(i).contains(idStr.get(i)));
        }

        System.out.println(" Печать аватаров " + avatar);
        System.out.println(" Печать айдишки " + id);
        System.out.println(" Печать айдишки строка  " + idStr);
    }

    //тестирование POST запроса . проверяем что ответ прриходит не пуской и что прриходит тот который нужен
    @Test
    public void succesRegTest(){

        Specifications.installSpecification(Specifications.requestSpecification(url),Specifications.responseSpecification200());

        Integer id = 4;
        String token = "QpwL5tke4Pnpja7X4";

        RequestReg_POST requestRegPost = new RequestReg_POST("eve.holt@reqres.in","pistol");//Создаем запрос
        ResponseReg_POST responseRegPost = given()//получаем ответ
                .body(requestRegPost)   //тело запроса . это данные которые нужно отпрпвить
                .when()
                .post("/api/register")  //отправить данные по этой ссылке
                .then().log().all()
                .extract().as(ResponseReg_POST.class);//достать данные из ответа

        Assert.assertNotNull(responseRegPost.getId());// проверяем что результат не пустой
        Assert.assertNotNull(responseRegPost.getToken());// проверяем что результат не пустой

        Assert.assertEquals(id,responseRegPost.getId());
        Assert.assertEquals(token,responseRegPost.getToken());

    }
    //проверка что приходит ошибка которая нужна. конкретный код который заложили
    @Test
    public void errorSuccesRegTest(){

        Specifications.installSpecification(Specifications.requestSpecification(url),Specifications.responseSpecificationError400());
        RequestReg_POST requestRegPost = new RequestReg_POST("sydney@fife","");
        ErrorSuccesRegTest_POST errorSuccesRegTestPost = given()
                .body(requestRegPost)
                .post("/api/register")  //отправить данные по этой ссылке
                .then().log().all()
                .extract().as(ErrorSuccesRegTest_POST.class);
        Assert.assertEquals("Missing password",errorSuccesRegTestPost.getError());

    }

    //проверка что года выводяться в порядке возрастания
    @Test
    public void sortYearTest(){
        Specifications.installSpecification(Specifications.requestSpecification(url),Specifications.responseSpecification200());
        List<ColorData> colors = given()
                .when()
                .get("/api/unknown")
                .then().log().all()
                .extract().body().jsonPath().getList("data",ColorData.class);
        List<Integer> years = colors.stream().map(ColorData::getYear).collect(Collectors.toList());// получаем года их каждого обьекта колорс которые приши в ответе
        List<Integer> sortegYears = years.stream().sorted().collect(Collectors.toList());//сартед сам сортирует по возрастанию
        Assert.assertEquals(sortegYears,years);//сравниваем ожидаемое с актуальным
        System.out.println(sortegYears);
        System.out.println(years);
    }

    //удаляем пользователя и проверяем код ответа который в задании
    @Test
    public void deleteUserTest(){
        Specifications.installSpecification(Specifications.requestSpecification(url),Specifications.responseSpecUnic(204));
        given()
                .delete("/api/users/2")
                .then().log().all();

    }
}

