package com.betamedia.atom.core.dsl.operations.impl;

import com.betamedia.atom.core.connectors.tp.FWTPConnector;
import com.betamedia.atom.core.dsl.operations.TagOperations;
import com.betamedia.atom.core.dsl.operations.impl.qa.QAEnvTagOperationsImpl;
import com.betamedia.common.search.Page;
import com.betamedia.common.search.Paging;
import com.betamedia.common.search.Sorting;
import com.betamedia.common.search.criteria.SearchCriteria;
import com.betamedia.tp.api.model.Tag;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

/**
 * Created by Oleksandr Losiev on 4/20/17.
 */
public class TagOperationsTest {

    @InjectMocks
    private QAEnvTagOperationsImpl tagOperations;

    @Mock
    private FWTPConnector tpConnector;

    private Tag expectedTag;
    private String tagName = "tagName";
    private String tagId = "tagId";

    @BeforeClass
    public void setupClass() {
        expectedTag = getExpectedTag();
    }

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.initMocks(this);

        when(tpConnector.readMultiple(any(SearchCriteria.class), isNull(Paging.class), isNull(Sorting.class)))
                .thenReturn(new Page<>(Collections.singletonList(expectedTag), null));
    }

    @Test
    public void testGetNoCategoryTag() {
        testTagWithCriteria(TagOperations.TagName.NO_CATEGORY, getNoCategoryCriteria());
    }

    @Test
    public void testGetShortTermTag() {
        testTagWithCriteria(TagOperations.TagName.SHORT_TERM_2_MIN_GAME_H3_TEXT, getShortTermCriteria());

    }

    private void testTagWithCriteria(TagOperations.TagName tagName, SearchCriteria<Tag> criteria) {
        ArgumentCaptor<SearchCriteria> argumentCaptor = ArgumentCaptor.forClass(SearchCriteria.class);
        List<Tag> tags = tagOperations.get(tagName);
        assertEquals(1, tags.size());

        Tag actualTag = tags.get(0);
        assertThat(expectedTag, new ReflectionEquals(actualTag));

        verify(tpConnector).readMultiple(argumentCaptor.capture(), isNull(Paging.class), isNull(Sorting.class));
        assertEquals(criteria, argumentCaptor.getValue());
    }

    private Tag getExpectedTag() {
        Tag tag = new Tag();
        tag.setName(tagName);
        tag.setId(tagId);
        return tag;
    }

    private SearchCriteria<Tag> getNoCategoryCriteria() {
        return new SearchCriteria<>(Tag.class)
                .and(Tag.EP_TAG_NAME.equalsTo(TagOperations.TagName.NO_CATEGORY.value));
    }

    private SearchCriteria<Tag> getShortTermCriteria() {
        return new SearchCriteria<>(Tag.class)
                .and(Tag.EP_TAG_NAME.equalsTo(TagOperations.TagName.SHORT_TERM_2_MIN_GAME_H3_TEXT.value));
    }
}