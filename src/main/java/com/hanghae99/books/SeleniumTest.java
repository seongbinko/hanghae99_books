package com.hanghae99.books;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        WebDriver driver = new ChromeDriver();

        for(int page = 1; page < 21; page++) {
            //이동을 원하는 url

            String url = "https://select.ridibooks.com/categories/100?sort=popular&page=" + page;

            //webDriver를 해당 url로 이동한다.
            driver.get(url);

            //브라우저 이동시 생기는 로드시간을 기다린다.
            //HTTP 응답속도보다 자바의 컴파일 속도가 더 빠르기 때문에 임의적으로 1초를 대기한다.
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

//        class = "nav"인 모든 태그를 가진 WebElement리스트를 받아온다.
//        WebElement는 html의 태그를 가지는 클래스이다.

            int i = 0;
            while (i < 24) {
                List<WebElement> elList = driver.findElements(By.className("GridBookList_ItemLink"));
                elList.get(i).click();
                // 1초대기
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 책썸네일
                String imgUrl = driver.findElement(By.tagName("img")).getAttribute("src");
                System.out.println(imgUrl);
                // 책제목
                String title = driver.findElement(By.className("PageBookDetail_BookTitle")).getText();
                System.out.println(title);
                // 책 저자,번역,출판사
                String bookElements = driver.findElement(By.className("PageBookDetail_BookElements")).getText();
                System.out.println(bookElements);
                // 별점
                //String ratingSummaryAverage = driver.findElement(By.className("PageBookDetail_RatingSummaryAverage")).getText();
                //System.out.println(ratingSummaryAverage);
                // 별점누른 사람수
                //String ratingSummaryCount = driver.findElement(By.className("PageBookDetail_RatingSummaryCount")).getText();
                //System.out.println(ratingSummaryCount);

                // 책소개랑 출판등록일 class가 같아서 List로 받아옴
                List<WebElement> el1 = driver.findElements(By.className("PageBookDetail_PanelContent"));
                // 책 소개
                if(el1.get(0).getText().equals("")){
                    String bookDescription = el1.get(1).getText();
                    System.out.println(bookDescription);
                }else{
                    String bookDescription = el1.get(0).getText();
                    System.out.println(bookDescription);
                }

                String bookDescription = el1.get(0).getText();
                System.out.println(bookDescription);
                // 출판 등록일
                String bookDate = el1.get(el1.size()-1).getText();
                System.out.println(bookDate);

                String[] array;
                array=bookDate.trim().split(" ");
                if(array[0].contains(".")){
                    System.out.println(array[0]);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd.");
                    LocalDate localDate = LocalDate.parse(array[0],formatter);
                    System.out.println(localDate);
                }

                driver.navigate().back();
                i++;

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            //드라이버가 null이 아니라면
            if (driver != null) {
                // 드라이버 연결 종료
                driver.close(); // 드라이버 연결해제
                // 프로세스 종료
                driver.quit();
            }
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
