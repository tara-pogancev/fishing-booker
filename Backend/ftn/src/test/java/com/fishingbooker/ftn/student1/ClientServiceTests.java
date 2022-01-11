package com.fishingbooker.ftn.student1;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import com.fishingbooker.ftn.bom.cottages.CottageReservation;
import com.fishingbooker.ftn.bom.reservations.Reservation;
import com.fishingbooker.ftn.bom.users.RegisteredClient;
import com.fishingbooker.ftn.conversion.DataConverter;
import com.fishingbooker.ftn.dto.RegisteredClientDto;
import com.fishingbooker.ftn.repository.BoatReservationRepository;
import com.fishingbooker.ftn.repository.RegisteredClientRepository;
import com.fishingbooker.ftn.service.RegisteredClientServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = {
        "APP_URL=https://localhost:8080",
        "EPASS=***",
        "EMAIL=fishing.booker.service@gmail.com",
        "PASS=2331"})
@AutoConfigureMockMvc
public class ClientServiceTests {

    @Mock
    private RegisteredClientRepository clientRepository;

    @Mock
    private BoatReservationRepository boatReservationRepository;

    @Mock
    RegisteredClient clientMock;

    @Mock
    CottageReservation cottageReservationMock1;

    @Mock
    CottageReservation cottageReservationMock2;

    @InjectMocks
    private RegisteredClientServiceImpl clientService;

    @Test
    public void testDeleteClient() {

        when(clientRepository.get(10L)).thenReturn(clientMock);

        RegisteredClient deleteClient = clientService.delete(10L);

        assertNotNull(deleteClient);
    }

    @Test
    public void testFindClientByEmail() {

        RegisteredClient client = new RegisteredClient();
        client.setId(1L);
        client.setName("Tara");
        client.setLastName("Pogancev");
        client.setEmail("tp@gm.c");

        when(clientRepository.findAll()).thenReturn(Arrays.asList(client));

        RegisteredClientDto falseClient = clientService.findByEmail("123123");

        assertNull(falseClient);

    }

    @Test
    public void testGetClientsWithInstructorReservation() {

        clientMock.setId(100L);

        when(clientRepository.getUsersWithReservationInMoment(10L)).thenReturn(Arrays.asList(100L, 100L));
        when(clientRepository.get(100L)).thenReturn(clientMock);

        List<RegisteredClient> clients = clientService.getClientsWithReservation(10L);

        assertThat(clients).hasSize(2);

    }

}
