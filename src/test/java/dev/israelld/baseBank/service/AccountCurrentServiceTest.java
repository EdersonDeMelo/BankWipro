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
import dev.israelld.baseBank.model.CreditCard;
import dev.israelld.baseBank.repository.AccountCurrentRepository;


@SpringBootTest
class AccountCurrentServiceTest {
	@InjectMocks
	private AccountCurrentService accountCurrentService;
	@Mock
	private AccountCurrentRepository repository;
	@Mock
	private AccountCurrent accountCurrent;
	
	private Optional<AccountCurrent> optional;
	

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		startAccountCurrent();
	}

	@Test
	void testFindById() {
		when(repository.findById(Mockito.anyLong())).thenReturn(optional);

		AccountCurrent response = accountCurrentService.findById(155l);

		assertNotNull(response);
		assertEquals(AccountCurrent.class, response.getClass());
		assertEquals(12l, response.getId());
		assertEquals("1548", response.getNumber());
		assertEquals(123.30, response.getBalance());
	}

	@Test
	void testFindAll() {
		when(repository.findAll()).thenReturn(List.of(accountCurrent));

		List<AccountCurrent> response = accountCurrentService.findAll();

		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(AccountCurrent.class, response.get(0).getClass());

		assertEquals(12l, response.get(0).getId());
		assertEquals("1548", response.get(0).getNumber());
		assertEquals(123.30, response.get(0).getBalance());
	}

	/*
	 * @Test void testUpdate() { fail("Not yet implemented"); }
	 */

	@Test
	void testCreate() {
		when(repository.save(any())).thenReturn(accountCurrent);

		AccountCurrent response = accountCurrentService.create(accountCurrent);

		assertNotNull(response);
		assertEquals(AccountCurrent.class, response.getClass());
		assertEquals(12l, response.getId());
		assertEquals("1548", response.getNumber());
		assertEquals(123.30, response.getBalance());
	}

	@Test
	void testDelete() {
		when(repository.findById(anyLong())).thenReturn(optional);
		doNothing().when(repository).deleteById(anyLong());

		accountCurrentService.delete(12l);
		verify(repository, times(1)).deleteById(anyLong());
	}
	
	
	private void startAccountCurrent() {
		accountCurrent = new AccountCurrent(12l,"1548",  123.30, true);
		optional = Optional.of(new AccountCurrent(12l,"1548",  123.30, true));
		
	}
}
