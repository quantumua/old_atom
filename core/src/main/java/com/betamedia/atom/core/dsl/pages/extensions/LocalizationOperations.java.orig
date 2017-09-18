package com.betamedia.atom.core.dsl.pages.extensions;

import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.extensions.base.Localizing;
import com.betamedia.atom.core.fwdataaccess.repository.util.Language;
import org.openqa.selenium.WebElement;

import java.util.function.Function;

import static com.betamedia.atom.core.testingtype.base.AbstractTest.softAssert;

/**
 * @author mbelyaev
 * @since 8/17/17
 */
public interface LocalizationOperations extends FieldOperations, CssOperations, Localizing {

    default void verifyDirection(Direction direction) {
        forPageElements(
                element ->
                        softAssert().assertEquals(
                                getCssValue("direction", element).toLowerCase(),
                                direction.value,
                                "Text direction verification for: " + element),
                field -> true,
                StoredId::localized,
                this);
    }

    default void verifyLocalization(Language language) {
        verifyLocalization(language, WebElement::getText);
    }

    default void verifyLocalization(Language language, Function<? super WebElement, String> mapper) {
        forPageElements(
                element ->
                        softAssert().assertEquals(
                                mapper.apply(waitUntilDisplayed(element)),
                                getLocalization(element, language),
                                "Text localization verification for: " + element),
                field -> true,
                StoredId::localized,
                this);
    }

    enum Direction {
        RTL("rtl"),
        LTR("ltr"),
        INITIAL("initial"),
        INHERIT("inherit");
        public final String value;

        Direction(String value) {
            this.value = value;
        }
    }

    static String getTextOrPlaceholder(WebElement element) {
        String text = element.getText();
        return text.isEmpty() ?
                element.getAttribute("placeholder") :
                text;
    }

}
