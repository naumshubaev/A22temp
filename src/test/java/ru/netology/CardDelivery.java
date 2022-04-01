package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;


public class CardDelivery {
    @Test
    public void shouldOrderCardDelivery() {
        // do we need to hold it open?
 //       Configuration.headless = true;
 //       Configuration.holdBrowserOpen = true;
        open("http://0.0.0.0:9999");
        $("[data-test-id=\"city\"]").setValue("Москва");
// ??        $(withText("Москва")).shouldBe(Condition.visible);

//        $("[data-test-id=\"city\"]");
        //autoconverted from $('[data-test-id="city"]')
    }
}
