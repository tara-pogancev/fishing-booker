package com.fishingbooker.ftn.student3.unitTests;

import com.fishingbooker.ftn.bom.adventures.Adventure;
import com.fishingbooker.ftn.bom.adventures.AdventureQuickReservation;
import com.fishingbooker.ftn.repository.AdventureRepository;
import com.fishingbooker.ftn.service.AdventureServiceImpl;
import com.fishingbooker.ftn.service.interfaces.AdventureService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource(properties = {
        "APP_URL=https://localhost:8080",
        "EPASS=***",
        "EMAIL=fishing.booker.service@gmail.com",
        "PASS=admin"})
public class AdventureServiceTests {

    @Mock
    private AdventureRepository adventureRepositoryMock;


    @InjectMocks
    private AdventureServiceImpl adventureService;



    @Test
    public void getInstructorAdventuresTest(){
        when(adventureRepositoryMock.getInstructorAdventures(1l)).thenReturn(TestData.getAdventures());

        List<Adventure> adventures=adventureService.getInstructorAdventures(1l);

        assertThat(adventures).hasSize(1);
        assertEquals(adventures.get(0).getName(),"Arctic Fishing");
    }

    @Test
    public void testDeleteAdventure(){
        Adventure adventure=TestData.getAdventures().get(0);
        when(adventureRepositoryMock.save(adventure)).thenReturn(adventure);
        when(adventureRepositoryMock.getById(adventure.getId())).thenReturn(adventure);

        boolean ret=adventureService.deleteAdventure(adventure.getId());
        assertEquals(ret,true);
    }

    @Test
    public void testGetAdventureQuickReservations(){
        Adventure adventure=TestData.getAdventures().get(0);
        when(adventureRepositoryMock.getById(adventure.getId())).thenReturn(adventure);

        List<AdventureQuickReservation> quickReservations=adventureService.getQuickReservations(adventure.getId());

        assertThat(quickReservations).hasSize(0);
    }
}
