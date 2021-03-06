package com.maestrano.account;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import com.maestrano.Maestrano;
import com.maestrano.account.MnoRecurringBill.MnoRecurringBillClient;
import com.maestrano.helpers.MnoDateHelper;

public class MnoRecurringBillIntegrationTest {
	private MnoRecurringBillClient mnoRecurringBillClient;
	
	@Before
	public void beforeEach() {
		Properties props = new Properties();
		props.setProperty("environment", "test");
		props.setProperty("api.id", "app-1");
		props.setProperty("api.key", "gfcmbu8269wyi0hjazk4t7o1sndpvrqxl53e1");
		Maestrano.reloadConfiguration(props);
		mnoRecurringBillClient = MnoRecurringBill.client();
	}
	
	@Test
	public void all_itRetrievesAllBills() throws Exception {
		List<MnoRecurringBill> billList = mnoRecurringBillClient.all();
		MnoRecurringBill bill = billList.get(0);
		
		assertEquals("rbill-1",bill.getId());
		assertEquals("cld-3",bill.getGroupId());
		assertEquals("year",bill.getPeriod());
		assertEquals("1",bill.getFrequency().toString());
		assertEquals("1190",bill.getPriceCents().toString());
		assertEquals("2014-06-19T12:29:25Z",MnoDateHelper.toIso8601(bill.getCreatedAt()));
	}
	
	@Test
	public void all_withFilters_itRetrievesSelectBills() throws Exception {
		Map<String,String> filters = new HashMap<String,String>();
		filters.put("status", "cancelled");
		
		List<MnoRecurringBill> billList = mnoRecurringBillClient.all(filters);
		
		for (MnoRecurringBill bill : billList) {
			assertEquals("cancelled",bill.getStatus());
		}
	}
	
	@Test 
	public void retrieve_itRetrievesASingleBill() throws Exception {
		MnoRecurringBill bill = mnoRecurringBillClient.retrieve("rbill-1");
		
		assertEquals("rbill-1",bill.getId());
		assertEquals("cld-3",bill.getGroupId());
		assertEquals("year",bill.getPeriod());
		assertEquals("1",bill.getFrequency().toString());
		assertEquals("1190",bill.getPriceCents().toString());
		assertEquals("2014-06-19T12:29:25Z",MnoDateHelper.toIso8601(bill.getCreatedAt()));
	}
	
	@Test
	public void create_itCreatesANewBill() throws Exception {
		Map<String, Object> attrsMap = new HashMap<String, Object>();
		attrsMap.put("groupId", "cld-3");
		attrsMap.put("priceCents", 2000);
		attrsMap.put("description", "Product purchase");
		
		MnoRecurringBill bill = mnoRecurringBillClient.create(attrsMap);
		
		assertFalse(bill.getId() == null);
		assertEquals("cld-3",bill.getGroupId());
		assertEquals("2000",bill.getPriceCents().toString());
		assertFalse(bill.getCreatedAt() == null);
	}
	
	@Test
	public void cancel_itCancelsABill() throws Exception {
		Map<String, Object> attrsMap = new HashMap<String, Object>();
		attrsMap.put("groupId", "cld-3");
		attrsMap.put("priceCents", 2000);
		attrsMap.put("description", "Product purchase");
		MnoRecurringBill bill = mnoRecurringBillClient.create(attrsMap);
		
		assertTrue(mnoRecurringBillClient.cancel(bill));
		assertEquals("cancelled",bill.getStatus());
	}
}
