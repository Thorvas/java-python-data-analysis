package Controller;

import com.example.demo.Controller.Controller;
import com.example.demo.DummyObject.DummyEntity;
import com.example.demo.Services.DummyEntityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.AbstractMap;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;


@ExtendWith(MockitoExtension.class)
public class ControllerTest {

    @Mock
    private DummyEntityService service;

    @InjectMocks
    private Controller controller;

    private DummyEntity dummyEntity;
    private final String SETUP_VOIVODESHIP_VALUE = "mazowieckie";

    private final Map<String, Integer> SETUP_POPULATION = Map.ofEntries(
            new AbstractMap.SimpleEntry<>("2012", 67678),
            new AbstractMap.SimpleEntry<>("2013", 67888)
    );
    private final Integer SETUP_ESTIMATED_POPULATION = 1100;
    private final String SETUP_NAME = "powiat aleksandrowski";

    //Usage of deprecated method, marked for refactor
    private final Date SETUP_DATE = new Date(2023, Calendar.JANUARY, 1);

    @BeforeEach
    public void setUp() {
        dummyEntity = new DummyEntity();
        dummyEntity.setId(1L);
        dummyEntity.setVoivodeship(SETUP_VOIVODESHIP_VALUE);
        dummyEntity.setLastUpdated(SETUP_DATE);
        dummyEntity.setName(SETUP_NAME);
        dummyEntity.setPrediction(SETUP_ESTIMATED_POPULATION);
        dummyEntity.setPopulation(SETUP_POPULATION);
    }

    @Test
    public void postEntity_shouldReturnOKStatus() {

    }
}
