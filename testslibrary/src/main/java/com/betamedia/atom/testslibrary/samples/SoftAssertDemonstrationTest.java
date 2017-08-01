package com.betamedia.atom.testslibrary.samples;

import com.betamedia.atom.core.testingtype.web.WebClientTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

/**
 * @author mbelyaev
 * @since 7/26/17
 */
public class SoftAssertDemonstrationTest extends WebClientTest {
    /**
     * {@link SoftAssert#assertAll()} is invoked implicitly on test completion if you have used a soft assert anywhere during test
     */
    @Test
    public void softAssertImplicitTest() {
        softAssert().assertEquals(1, 42);
        System.out.println("Test advances after failed soft assertion");
        softAssert().assertEquals(15, 15);
    }

    /**
     * You can also invoke {@link SoftAssert#assertAll()} manually
     */
    @Test
    public void softAssertExplicitTest() {
        softAssert().assertEquals(2, 42);
        System.out.println("Test advances after failed soft assertion");
        softAssert().assertEquals(15, 15);
        softAssert().assertAll();
    }

    /**
     * Hard assert failure also invokes {@link SoftAssert#assertAll()}
     */
    @Test
    public void combinedAssertsTest() {
        softAssert().assertEquals(3, 42);
        System.out.println("Test advances after failed soft assertion");
        Assert.assertEquals(true, false);
    }

    /**
     * You can still use regular asserts only in your code
     */
    @Test
    public void noSoftAssert() {
        Assert.assertEquals(true, false);
    }
}