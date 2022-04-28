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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import dev.israelld.baseBank.model.Client;
import dev.israelld.baseBank.model.Manager;
import dev.israelld.baseBank.repository.CreditCardRepository;
import dev.israelld.baseBank.repository.ManagerRepository;

@SpringBootTest
class ManagerServiceTest {
	@InjectMocks
	private ManagerService managerService;
	@Mock
	private ManagerRepository repository;
	@Mock
	private Manager manager;

	private Optional<Manager> optional;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		startManager();
	}

	@Test
	void testFindById() {
		when(repository.findById(Mockito.anyLong())).thenReturn(optional);

		Manager response = managerService.findById(125l);
		assertNotNull(response);
		assertEquals(Manager.class, response.getClass());
		assertEquals(125l, response.getId());
	}

	@Test
	void testFindAll() {
		when(repository.findAll()).thenReturn(List.of(manager));

		List<Manager> response = managerService.findAll();

		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(Manager.class, response.get(0).getClass());
		assertEquals(125l, response.get(0).getId());
		assertEquals("154979", response.get(0).getCpf());
		assertEquals("Ederson", response.get(0).getName());
		assertEquals("progra", response.get(0).getAddress());
	}

	/*
	 * @Test void testUpdate() { when(repository.save(any())).thenReturn(manager);
	 * 
	 * Manager managerUpdate = new Manager(125l, "1549", "Ederson", "progra");
	 * Manager response = managerService.update(125l, managerUpdate);
	 * 
	 * 
	 * assertNotNull(response); assertEquals(Manager.class, response.getClass());
	 * assertEquals(125l, response.getId()); assertEquals("154979",
	 * response.getCpf()); assertEquals("Ederson", response.getName());
	 * assertEquals("progra", response.getAddress()); }
	 */

	@Test
	void testCreate() {
		when(repository.save(any())).thenReturn(manager);

		Manager response = managerService.create(manager);

		assertNotNull(response);
		assertEquals(Manager.class, response.getClass());
		assertEquals(125l, response.getId());
		assertEquals("154979", response.getCpf());
		assertEquals("Ederson", response.getName());
		assertEquals("progra", response.getAddress());
	}

	@Test
	void testDelete() {
		when(repository.findById(anyLong())).thenReturn(optional);
		doNothing().when(repository).deleteById(anyLong());

		managerService.delete(125l);
		verify(repository, times(1)).deleteById(anyLong());
	}

	private void startManager() {
		manager = new Manager(125l, "154979", "Ederson", "progra");
		optional = Optional.of(new Manager(125l, "154979", "Ederson", "progra"));

	}

}
