package com.mindex.challenge;

import com.mindex.challenge.data.*;
import com.mindex.challenge.dao.*;
import com.mindex.challenge.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.UUID;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ChallengeApplicationTests {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private CompensationRepository compensationRepository;

	private final String JOHN_LENNON_ID = "16a596ae-edd3-4847-99fe-c4518e82c86f";
	private final String PAUL_MCCARTNEY_ID = "b7839309-3348-463b-a7e3-5de1c168beb3";
	private final String RINGO_STARR_ID = "03aa1462-ffa9-4978-901b-7c001562cf6f";
	private final String PETE_BEST_ID = "62c1084e-6e34-4630-93fd-9153afb65309";
	private final String GEORGE_HARRISON_ID = "c0c2293d-16bd-4603-8e08-638a9d18b22c";

	@Test
	public void contextLoads() {
	}

	// TASK 1 TESTS *REPORTING STRUCTURE*

	// tests for employees whose direct reports also have direct reports
	@Test
	public void testJohnLennonReportingStructure() {
		ReportingStructure structure = employeeService.reportStruct(JOHN_LENNON_ID);

		// checks for the number of reports as well as its the correct employee
		assertEquals(4, structure.getNumberOfReports());
		assertEquals("John", structure.getEmployee().getFirstName());
	}

	// tests for employees with only direct reports
	@Test
	public void testRingoStarrReportingStructure() {
		ReportingStructure structure = employeeService.reportStruct(RINGO_STARR_ID);

		// checks for the number of reports as well as its the correct employee
		assertEquals(2, structure.getNumberOfReports());
		assertEquals("Ringo", structure.getEmployee().getFirstName());
	}

	// tests for employees with no direct or indirect reports
	@Test
	public void testPeteBestReportingStructure() {
		ReportingStructure structure = employeeService.reportStruct(PETE_BEST_ID);

		// checks for the number of reports as well as its the correct employee
		assertEquals(0, structure.getNumberOfReports());
		assertEquals("Pete", structure.getEmployee().getFirstName());
	}

	// TASK 2 TESTS *COMPENSATION*

	// checks the compensation exists and saves effectively
	@Test
	public void testCreateCompensationPaulMcMartney() {
		Compensation comp = employeeService.createComp(
				PAUL_MCCARTNEY_ID,
				200000,
				"01-01-2024"
		);

		assertNotNull(comp.getId());
		assertEquals(PAUL_MCCARTNEY_ID, comp.getEmployeeId());
		assertEquals(200000, comp.getSalary());
		assertEquals("01-01-2024", comp.getEffectiveDate());
	}

	// checks the persistence works and saves everything correctly
	@Test
	public void testReadCompensationPaulMcMartney() {
		Compensation comp = compensationRepository.findByEmployeeId(PAUL_MCCARTNEY_ID);
		assertEquals(PAUL_MCCARTNEY_ID, comp.getEmployeeId());
		assertEquals(200000, comp.getSalary());
		assertEquals("01-01-2024", comp.getEffectiveDate());
	}

	// ERROR TEST CASES

	@Test(expected = RuntimeException.class)
	public void testCompensationINvalidEmployeeFail() {
		employeeService.createComp("invalid-id-123", 100000, "01-01-2024");
	}

	@Test(expected = RuntimeException.class)
	public void testCompensationNegativeSalaryFail() {
		employeeService.createComp(GEORGE_HARRISON_ID, -100, "01-01-2024");
	}

	@Test(expected = RuntimeException.class)
	public void testReadNonExistentCompensationFail() {
		employeeService.readComp("fake-id");
	}

	@Test(expected = RuntimeException.class)
	public void testInvalidEmployeeReportingStructureFails() {
		employeeService.reportStruct("invalid-id");
	}

}
