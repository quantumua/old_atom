package com.betamedia.qe.af.core.dsl.pages.factory;

import com.betamedia.qe.af.core.fwdataaccess.entities.FindBy;
import com.betamedia.qe.af.core.holders.ThreadLocalBeansHolder;
import com.betamedia.qe.af.core.fwdataaccess.repository.VersionedWebElementRepository;
import com.betamedia.qe.af.core.dsl.pages.AbstractPageObject;
import com.betamedia.qe.af.core.dsl.pages.annotation.StoredId;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.How;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
@Service
@Scope(SCOPE_PROTOTYPE)
public class PageObjectCreatorImpl implements PageObjectCreator {
    private static final Logger logger = LogManager.getLogger(PageObjectCreatorImpl.class);

    private final Map<Class, AbstractPageObject> pageObjectCache = new ConcurrentHashMap<>();

    private WebDriver driver;
    private VersionedWebElementRepository repository;

    @Autowired
    public PageObjectCreatorImpl() throws IOException {
        this.driver = ThreadLocalBeansHolder.getWebDriverFactoryThreadLocal().get();
        this.repository = ThreadLocalBeansHolder.getVersionedWebElementRepositoryThreadLocal();
    }

    @Override
    public <T extends AbstractPageObject> T getPage(Class<T> clazz) {
        return (T) pageObjectCache.computeIfAbsent(clazz, this::makePage);

    }

    private <T extends AbstractPageObject> T makePage(Class<T> clazz) {
        T page;
        try {
            page = clazz.getConstructor(WebDriver.class).newInstance(driver);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            logger.error("", e);
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
                String value = storedId.value().isEmpty() ? field.getName() : storedId.value();
                FindBy findBy = repository.get(page.getClass().getSimpleName(), value);
                setField(field, page, by(findBy));
            }
        }
    }

    private By by(FindBy findBy) {
        How how = How.valueOf(findBy.locatorType);
        return byProducer(how).apply(findBy.value);
    }

    private void setField(Field field, Object object, Object value) {
        try {
            if (Modifier.isPrivate(field.getModifiers())) {
                field.setAccessible(true);
            }
            field.set(object, value);
        } catch (IllegalAccessException e) {
            logger.error("", e);
            throw new RuntimeException(e);
        }
    }

    private Function<String, By> byProducer(How how) {
        switch (how) {
            case CLASS_NAME:
                return By::className;
            case CSS:
                return By::cssSelector;
            case ID:
                return By::id;
            case LINK_TEXT:
                return By::linkText;
            case NAME:
                return By::name;
            case PARTIAL_LINK_TEXT:
                return By::partialLinkText;
            case TAG_NAME:
                return By::tagName;
            case XPATH:
                return By::xpath;
            case UNSET:
            default:
                throw new InvalidArgumentException("Invalid selector strategy!");
        }
    }
}
