package dev.israelld.baseBank.service;

import static org.junit.jupiter.api.Assertions.*;
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

import dev.israelld.baseBank.model.Account;
import dev.israelld.baseBank.model.AccountCurrent;
import dev.israelld.baseBank.repository.AccountCurrentRepository;
import dev.israelld.baseBank.repository.AccountRepository;

@SpringBootTest
class AccountServiceTest {
	@InjectMocks
	private AccountService accountService;
	@Mock
	private AccountRepository repository;
	@Mock
	private Account account;
	
	private Optional<Account> optional;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		startAccount();
	}


	@Test
	void testFindById() {
		when(repository.findById(Mockito.anyLong())).thenReturn(optional);

		Account response = accountService.findById(155l);

		assertNotNull(response);
		assertEquals(AccountCurrent.class, response.getClass());
		assertEquals(12l, response.getId());
		assertEquals("1548", response.getNumber());
		assertEquals(123.30, response.getBalance());
	}

	@Test
	void testFindAll() {
		when(repository.findAll()).thenReturn(List.of(account));

		List<Account> response = accountService.findAll();

		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(Account.class, response.get(0).getClass());

		assertEquals(12l, response.get(0).getId());
		assertEquals("1548", response.get(0).getNumber());
		assertEquals(123.30, response.get(0).getBalance());
	}
	
	private void startAccount() {
		account = new Account(12l,"1548",  123.30, true);
		optional = Optional.of(new AccountCurrent(12l,"1548",  123.30, true));
		
	}

}
