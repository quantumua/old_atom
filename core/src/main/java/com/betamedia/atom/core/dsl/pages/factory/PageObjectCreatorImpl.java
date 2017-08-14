package com.betamedia.atom.core.dsl.pages.factory;

import com.betamedia.atom.core.dsl.pages.AbstractPageObject;
import com.betamedia.atom.core.dsl.pages.annotation.StoredId;
import com.betamedia.atom.core.dsl.pages.localization.LocalizationStorage;
import com.betamedia.atom.core.dsl.pages.localization.impl.LocalizationStorageImpl;
import com.betamedia.atom.core.fwdataaccess.entities.FindBy;
import com.betamedia.atom.core.fwdataaccess.repository.VersionedLocalizationRepository;
import com.betamedia.atom.core.fwdataaccess.repository.VersionedWebElementRepository;
import com.betamedia.atom.core.holders.ThreadLocalBeansHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.How;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.springframework.beans.factory.config.BeanDefinition.SCOPE_PROTOTYPE;
import static org.springframework.util.ReflectionUtils.*;

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
    private VersionedWebElementRepository elementRepository;
    private VersionedLocalizationRepository localizationRepository;

    private Supplier<LocalizationStorage> storageFactory = LocalizationStorageImpl::new;

    @Autowired
    public PageObjectCreatorImpl() {
        this.driver = ThreadLocalBeansHolder.getWebDriverFactory().get();
        this.elementRepository = ThreadLocalBeansHolder.getVersionedWebElementRepository();
        this.localizationRepository = ThreadLocalBeansHolder.getVersionedLocalizationRepository();
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
        LocalizationStorage localizationStorage = storageFactory.get();
        doWithFields(
                page.getClass(),
                field -> writeWebLocator(field, page, localizationStorage),
                PageObjectCreatorImpl::isStoredId);
        doWithFields(AbstractPageObject.class,
                field -> {
                    makeAccessible(field);
                    setField(field, page, localizationStorage);
                },
                field -> field.getType().isAssignableFrom(LocalizationStorage.class));
    }

    private <T extends AbstractPageObject> void writeWebLocator(Field field, T target, LocalizationStorage localizations) {
        StoredId storedId = field.getAnnotation(StoredId.class);
        String value = storedId.value().isEmpty() ? field.getName() : storedId.value();
        FindBy findBy = elementRepository.get(field.getDeclaringClass().getSimpleName(), value);
        makeAccessible(field);
        By locator = by(findBy);
        setField(field, target, locator);
        if (storedId.localized()) {
            localizations.put(locator, localizationRepository.get(field.getDeclaringClass().getSimpleName(), value));
        }
    }

    private static boolean isStoredId(Field field) {
        return field.isAnnotationPresent(StoredId.class) && field.getType().isAssignableFrom(By.class);
    }

    private static By by(FindBy findBy) {
        How how = How.valueOf(findBy.locatorType);
        return byProducer(how).apply(findBy.value);
    }

    private static Function<String, By> byProducer(How how) {
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
