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

import dev.israelld.baseBank.model.AccountCurrent;
import dev.israelld.baseBank.model.Agency;
import dev.israelld.baseBank.repository.AgencyRepository;

@SpringBootTest
class AgencyServiceTest {
	@InjectMocks
	private AgencyService agencyService;
	@Mock
	private AgencyRepository repository;
	@Mock
	private Agency agency;
	
	private Optional<Agency> optional;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		startAgency();
	}

	@Test
	void testFindById() {
		when(repository.findById(Mockito.anyLong())).thenReturn(optional);

		Agency response = agencyService.findById(155l);

		assertNotNull(response);
		assertEquals(Agency.class, response.getClass());
		assertEquals(265l, response.getId());
		assertEquals("Bank", response.getAgencyName());
		assertEquals("548576", response.getAgencyNumber());
		assertEquals("546358522", response.getCnpj());
	}

	@Test
	void testFindByAgencyName() {
		when(repository.findById(Mockito.anyLong())).thenReturn(optional);

		Agency response = agencyService.findByAgencyName("Bank");

		assertNotNull(response);
		assertEquals(Agency.class, response.getClass());
		assertEquals(265l, response.getId());
		assertEquals("Bank", response.getAgencyName());
	}

	@Test
	void testFindAll() {
		when(repository.findAll()).thenReturn(List.of(agency));

		List<Agency> response = agencyService.findAll();

		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(Agency.class, response.get(0).getClass());
		assertEquals(265l, response.get(0).getId());
		assertEquals("Bank", response.get(0).getAgencyName());
	}

	/*
	 * @Test void testUpdate() { fail("Not yet implemented"); }
	 */

	@Test
	void testCreate() {
		when(repository.save(any())).thenReturn(agency);

		Agency response = agencyService.create(agency);

		assertNotNull(response);
		assertEquals(Agency.class, response.getClass());
		assertEquals(265l, response.getId());
		assertEquals("Bank", response.getAgencyName());
	}

	@Test
	void testDelete() {
		when(repository.findById(anyLong())).thenReturn(optional);
		doNothing().when(repository).deleteById(anyLong());

		agencyService.delete(265l);
		verify(repository, times(1)).deleteById(anyLong());
	}

	private void startAgency() {
	agency = new Agency(265l, "Bank", "548576", "546358522");
	optional = Optional.of(new Agency(265l, "Bank", "548576", "546358522"));
	}

}
