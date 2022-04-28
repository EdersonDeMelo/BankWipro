package dev.israelld.baseBank.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.apache.tomcat.jni.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import dev.israelld.baseBank.model.Client;
import dev.israelld.baseBank.model.CreditCard;
import dev.israelld.baseBank.model.Person;
import dev.israelld.baseBank.repository.ClientRepository;


@SpringBootTest
class ClientServiceTest {
	@InjectMocks
	private ClientService clientService;
	@Mock
	private ClientRepository repository;
	
	private Client client;
	
	private Optional<Client> optional;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		startClient();		
	}

	@Test
	void testFindById() {
		when(repository.findById(Mockito.anyLong())).thenReturn(optional);
		
		Client response = clientService.findById(125l);
		assertNotNull(response);
		assertEquals(Client.class, response.getClass());
		assertEquals(125l, response.getId());
	}

	@Test
	void testFindAll() {
		when(repository.findAll()).thenReturn(List.of(client));
		
		List<Client> response = clientService.findAll();
		
		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(Client.class, response.get(0).getClass());
		assertEquals(125l, response.get(0).getId());
		assertEquals("154979", response.get(0).getCpf());
		assertEquals("Ederson", response.get(0).getName());
		assertEquals("progra", response.get(0).getAddress());
		
	}

	
	 @Test void testUpdate() { 
		 
	 }
	 
	
	@Test
	void testCreate() {
		when(repository.save(any())).thenReturn(client);
		
		Client response = clientService.create(client);
		
		assertNotNull(response);
		assertEquals(Client.class, response.getClass());
		assertEquals(125l, response.getId());
		assertEquals("154979", response.getCpf());
		assertEquals("Ederson", response.getName());
		assertEquals("progra", response.getAddress());
	}

	@Test
	void testDelete() {
		when(repository.findById(anyLong())).thenReturn(optional);
		doNothing().when(repository).deleteById(anyLong());
		
		clientService.delete(125l);
		verify(repository, times(1)).deleteById(anyLong());
		
	}

	private void startClient() {
		client = new Client(125l, "154979", "Ederson", "progra");
		optional = Optional.of(new Client(125l, "154979", "Ederson", "progra"));
	}
}
