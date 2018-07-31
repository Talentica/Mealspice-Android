package com.talenitca.mealspiceandroid.screens.details;

import com.talenitca.mealspiceandroid.data.DataManager;
import com.talenitca.mealspiceandroid.data.DataManagerProvider;
import com.talenitca.mealspiceandroid.data.models.Restaurant;
import com.talenitca.mealspiceandroid.screens.home.HomeContract;
import com.talenitca.mealspiceandroid.screens.home.HomePresenter;
import com.talenitca.mealspiceandroid.utils.TestUtils;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertNull;
import static org.mockito.ArgumentCaptor.forClass;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DetailsPresenterTest {

    @Mock
    DetailsContract.View view;

    DataManager dataManager;

    private DetailsPresenter presenter;

    @Before
    public void setUp() throws Exception {
        dataManager = DataManagerProvider.getMockImplementation();
        presenter = new DetailsPresenter(view, dataManager);

    }

    @Test
    public void checkIfViewIsReleasedOnDestroy() {
        presenter.destroy();
        assertNull(presenter.getView());
    }

    @Test
    public void checkIfRightViewIsShown() {
        presenter.start("anything");
        verify(view, times(1)).showLoading();
        verify(view, times(1)).hideLoading();
    }
    @Test
    public void checkfICorrectNamePassed() {
        ArgumentCaptor<String> captor = forClass(String.class);
        presenter.start("anything");
        verify(view, times(1)).loadRestaurantName(captor.capture());
        assertThat(captor.getValue(), is(TestUtils.getMockedRestaurant().getName()));
    }
}
