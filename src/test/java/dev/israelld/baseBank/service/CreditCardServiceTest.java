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

import org.apache.catalina.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import dev.israelld.baseBank.model.CreditCard;
import dev.israelld.baseBank.repository.CreditCardRepository;

@SpringBootTest
class CreditCardServiceTest {
	@InjectMocks
	private CreditCardService cardService;

	@Mock
	private CreditCardRepository repository;
	@Mock
	private CreditCard card;

	private Optional<CreditCard> optional;

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
		startCreditCard();
	}

	@Test
	void testFindById() {
		when(repository.findById(Mockito.anyLong())).thenReturn(optional);

		CreditCard response = cardService.findById(155l);

		assertNotNull(response);
		assertEquals(CreditCard.class, response.getClass());
		assertEquals(155l, response.getId());
		assertEquals("4785", response.getCardNumber());
		assertEquals("4445", response.getCvv());
		assertEquals(200, response.getCredit_limit());

	}

	@Test
	void testFindAll() {
		when(repository.findAll()).thenReturn(List.of(card));

		List<CreditCard> response = cardService.findAll();

		assertNotNull(response);
		assertEquals(1, response.size());
		assertEquals(CreditCard.class, response.get(0).getClass());

		assertEquals(155l, response.get(0).getId());
		assertEquals("4785", response.get(0).getCardNumber());
		assertEquals("4445", response.get(0).getCvv());
		assertEquals(200, response.get(0).getCredit_limit());

	}

	
	  @Test 
	  void testUpdate() { 
	  }
	 

	@Test
	void testCreate() {
		when(repository.save(any())).thenReturn(card);

		CreditCard response = cardService.create(card);

		assertNotNull(response);
		assertEquals(CreditCard.class, response.getClass());
		assertEquals(155l, response.getId());
		assertEquals("4785", response.getCardNumber());
		assertEquals("4445", response.getCvv());
		assertEquals(200, response.getCredit_limit());
	}

	@Test
	void testDelete() {
		when(repository.findById(anyLong())).thenReturn(optional);
		doNothing().when(repository).deleteById(anyLong());

		cardService.delete(155l);
		verify(repository, times(1)).deleteById(anyLong());
	}

	private void startCreditCard() {
		card = new CreditCard(155l, "4785", "4445", 200);
		optional = Optional.of(new CreditCard(155l, "4785", "4445", 200));
	}

}
