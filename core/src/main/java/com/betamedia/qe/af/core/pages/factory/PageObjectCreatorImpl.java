package com.betamedia.qe.af.core.pages.factory;

import com.betamedia.qe.af.common.holder.ThreadLocalBeansHolder;
import com.betamedia.qe.af.common.repository.VersionedWebElementRepository;
import com.betamedia.qe.af.common.repository.WebElementRepository;
import com.betamedia.qe.af.core.pages.AbstractPageObject;
import com.betamedia.qe.af.core.pages.annotation.StoredId;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
@Service
@Scope(SCOPE_PROTOTYPE)
public class PageObjectCreatorImpl implements PageObjectCreator{

    private WebDriver driver;
    private VersionedWebElementRepository repository;

    @Autowired
    public PageObjectCreatorImpl( WebElementRepository repository) throws IOException {
        this.driver = ThreadLocalBeansHolder.getWebDriverFactoryThreadLocal().get();
        this.repository = ThreadLocalBeansHolder.getVersionedWebElementRepositoryThreadLocal();
    }

    public  <T extends AbstractPageObject> T getPage(Class<T> clazz) {
        T page;
        try {
            page = clazz.getConstructor(WebDriver.class).newInstance(driver);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        initWebFields(page);
        return page;
    }

    private <T extends AbstractPageObject> void initWebFields(T page) {
        Field[] declaredFields = page.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(StoredId.class) && field.getType().isAssignableFrom(By.class)) {
                StoredId storedId = field.getAnnotation(StoredId.class);
                setField(field, page, By.id(repository.getId(page.getClass().getSimpleName(), storedId.value())));
            }
        }
    }

    public void closeBrowser() {
        driver.quit();
    }

    private void setField(Field field, Object object, Object value) {
        try {
            if (Modifier.isPrivate(field.getModifiers())) {
                field.setAccessible(true);
            }
            field.set(object, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
