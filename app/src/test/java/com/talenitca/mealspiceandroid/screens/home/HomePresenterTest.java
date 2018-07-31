package com.talenitca.mealspiceandroid.screens.home;

import com.talenitca.mealspiceandroid.data.DataManager;
import com.talenitca.mealspiceandroid.data.DataManagerProvider;
import com.talenitca.mealspiceandroid.data.models.Restaurant;
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
public class HomePresenterTest {

    @Mock
    HomeContract.View view;

    DataManager dataManager;

    private HomePresenter presenter;

    @Before
    public void setUp() throws Exception {
        dataManager = DataManagerProvider.getMockImplementation();
        presenter = new HomePresenter(view, dataManager);

    }

    @Test
    public void checkIfViewIsReleasedOnDestroy() {
        presenter.destroy();
        assertNull(presenter.getView());
    }

    @Test
    public void checkIfRightViewIsShown() {
        presenter.fetchAllData();
        verify(view, times(1)).showLoading();
        verify(view, times(1)).hideLoading();
    }
    @Test
    public void checkfICorrectListIsPassed() {
        ArgumentCaptor<List<Restaurant>> captor = forClass(List.class);
        presenter.fetchAllData();
        verify(view, times(1)).loadRestaurants(captor.capture());
        assertThat(captor.getValue(), is(TestUtils.getMockedRestaurantList()));
    }
}
