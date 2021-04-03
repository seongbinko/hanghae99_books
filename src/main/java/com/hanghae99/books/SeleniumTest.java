package com.hanghae99.books;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;


public class SeleniumTest {

    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver"; // 드라이버 ID
    public static final String WEB_DRIVER_PATH = "chromedriver.exe"; // 드라이버 경로


    public static void main(String[] args){
        //드라이버 설정
        try {
            System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
        } catch (Exception e){
            e.printStackTrace();
        }

        // 크롬 설정을 담은 객체 생성
        ChromeOptions options = new ChromeOptions();
        // 브라우저가 눈에 보이지 않고 내부적으로 돈다.
        // 설정하지 않을 시 실제 크롬 창이 생성되고, 어떤 순서로 진행되는지 확인할 수 있다.

        options.addArguments("headless");

        //위에서 설정한 옵션들 담은 드라이버 객체 생성
        //옵션을 설정하지 않았을 때에는 생략 가능하다.
        //WebDriver 객체가 곧 하나의 브라우저 창이라 생각한다.
        WebDriver driver = new ChromeDriver(options);

        //이동을 원하는 url
        String url = "https://select.ridibooks.com/categories/100";

        //webDriver를 해당 url로 이동한다.
        driver.get(url);

        //브라우저 이동시 생기는 로드시간을 기다린다.
        //HTTP 응답속도보다 자바의 컴파일 속도가 더 빠르기 때문에 임의적으로 1초를 대기한다.
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){

        }

//        class = "nav"인 모든 태그를 가진 WebElement리스트를 받아온다.
//        WebElement는 html의 태그를 가지는 클래스이다.
        List<WebElement> el1 = driver.findElements(By.className("GridBookList_ItemLink")); // 살짝 이상한 위치 크롤링 되는것같다.
        System.out.println(el1);



        for (int i=0; i<el1.size(); i++){
            System.out.println(el1.get(i).getText());

            if(el1.get(i).getText().equals("인간 실격")){
                el1.get(i).click(); // 클릭이 안됨 스트링이랑 a태그랑 따로 있어서 그런듯
                WebElement el2 = driver.findElement(By.className("PageBookDetail_BookTitle"));
                System.out.println(el2.getText());
                driver.navigate().back();
            }
        }

        // 1초대기
        try{
            Thread.sleep(1000);
        } catch (InterruptedException e){

        }

        // 버튼을 클릭했기 때문에 브라우저는 엄마라는 제목을 가진 곳으로 이동돼있다.
        // 이동한 창의 제목을 가져온다.






        //div속에서 strong태그를 가진 모든 element를 받아온다.
//        List<WebElement> el3 = el2.findElements(By.className("css-orilj0-BookDetail-MetaContents--CategoryLink e1b1enb40"));
//
//        int count = 0;
//        for(int i=0; i<el3.size(); i++){
//            // 뉴스의 제목을 모두 출력한다.
//            System.out.println(++count + "번 뉴스" + el3.get(i).getText());
//        }


        try{
            //드라이버가 null이 아니라면
            if(driver != null){
                // 드라이버 연결 종료
                driver.close(); // 드라이버 연결해제
                // 프로세스 종료
                driver.quit();
            }
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }

    }


}
