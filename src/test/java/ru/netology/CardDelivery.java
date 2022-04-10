package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;


public class CardDelivery {

    String deliveryDate() {
        // deliveryDate == today+daysAdded
        LocalDate today = LocalDate.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        int daysAdded = 3;
        // daysAdded выглядит, наверное, как лишняя переменная (если уж переменная - то лучше в самом тесте, а тут можно было просто значение подставить)
        // но я реализовал так, чтобы потом сделать рандомайзер, т.е. чтобы deliveryDate возвращал
        // случайную дату, подходящую под ТЗ и в нужном формате
        // считаю, что ТЗ из ДЗ соответствует и в таком виде - "не ранее трёх дней с текущей даты"

        return today.plusDays(daysAdded).format(dtf);
    }

    @Test
    public void shouldOrderCardDelivery() {
        // do we need to hold it open?
        Configuration.headless = true;
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").doubleClick().sendKeys(deliveryDate());
        $("[data-test-id='name'] input").setValue("Василий Пупкин");
        $("[data-test-id='phone'] input").val("+78121234567");
        $("[data-test-id='agreement']>span").click();
        $$("button").find(exactText("Забронировать")).click();
        $("[class='notification__content']")
                .shouldHave(Condition.text("Встреча успешно забронирована на " + deliveryDate())
                        , Duration.ofSeconds(15));
    }
}
