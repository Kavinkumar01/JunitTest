package com.junit.demo;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

//Lifecycles
//@BeforeAll
//@BeforeEach
//@Test
//@AfterAll
//@AfterEach

//conditionalExecution
//    @EnabledOnOs
//    @DisabledOnOs

//Assumption:
//If the assumptions get false then the test will be aborted/ignored

//@RepeatedTest(value=5) or
//@RepeatedTest(value = 5, name = "test repeated {currentRepetition} of {totalRepetitions}")
//This replaces the @Test and it can be used for random values and code which needs to be executed multiple times


//@ParameterizedTest()
//@ValueSource(strings = {"9942484256","8667038594","8667491819"})
//@DisplayName("Should create contact using different phonenumbers")
//public void shouldCreateContactsWithDifferentPhoneNumbers(String phoneNumber) throws Exception {
// The parameterized constructor executes along with @ValueSource, @csvSource, @MethodSource, @csvFileSource,etc these are sources from which paramter
//gets passed to the method that we are executing, we can use this for testing same test with multiple cases



//@Disabled
//This will disable the test and it is not recommended to use as it is easy to forget about the annotation

//@Nested
//This is annotated over a class and
//This is used to group the releated test, It helps in structuring the tests


//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//If we use the above annotation then we can make the beforeAll and AfterAll methods Non-static
class contactManagerTest {

    ContactManager contactManager;

    @BeforeAll
    public static void someInitializationTask() {
        System.out.println("This method annotated with @BeforeAll will get executed before any of the tests run and " +
                "the method needs to be static so that it can be run ");
    }

    @BeforeEach
    public void someCommonCodeRequiredByAllTests() {
        System.out.println("This method annotated with @BeforeEach will get executed before every tests run " +
                "and we can have the common code inside this method like the class creation");
        contactManager = new ContactManager();
    }

    @Test
    @DisplayName("Should create contact successfully")
    public void shouldCreateContact() throws Exception {
//        ContactManager contactManager=new ContactManager();//moved to beforeEach block
        contactManager.addContact("kavin", "kumar", "9942484256");
        Assertions.assertFalse(contactManager.getAllContact().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContact().size());
        Assertions.assertTrue(contactManager.getAllContact().stream().filter(contact ->
                        contact.getFirstName().equalsIgnoreCase("kavin") &&
                                contact.getLastName().equalsIgnoreCase("kumar") && contact.getPhoneNumber().equalsIgnoreCase("9942484256"))
                .findAny().isPresent());
    }

    @Test
    @DisplayName("Should throw exception when there validation of firstName fails")
    public void shouldthrowExceptionWhenFirstNameisEmpty() {
//        ContactManager contactManager = new ContactManager();
        Assertions.assertThrows(Exception.class, () -> {
                    contactManager.addContact(null, "kumar", "9942484256");
                }
        );
    }

    @Test
    @DisplayName("Should throw exception when there validation of LastName fails")
    public void shouldthrowExceptionWhenLastNameisEmpty() {
//        ContactManager contactManager = new ContactManager();
        Assertions.assertThrows(Exception.class, () -> {
                    contactManager.addContact("kavin", null, "9942484256");
                }
        );
    }

    @AfterAll
    public static void CleanUpTask() {
        System.out.println("This method annotated with @AfterAll will get executed after all of the tests run" +
                " and this method needs to be static else all the tests will be ignored");
    }

    @AfterEach
    public void CleanUpTaskAfterEachTest() {
        System.out.println("This method annotated with @AfterEach will get executed after each of the tests run");
    }

    @Test
    @EnabledOnOs(value = OS.MAC, disabledReason = "This is enabled only on MAC OS")
    @DisplayName("Should create contact on MAC OS only")
    public void shouldCreateContactOnMACOS() throws Exception {
//        ContactManager contactManager=new ContactManager();//moved to beforeEach block
        contactManager.addContact("kavin", "kumar", "9942484256");
        Assertions.assertFalse(contactManager.getAllContact().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContact().size());
        Assertions.assertTrue(contactManager.getAllContact().stream().filter(contact ->
                        contact.getFirstName().equalsIgnoreCase("kavin") &&
                                contact.getLastName().equalsIgnoreCase("kumar") && contact.getPhoneNumber().equalsIgnoreCase("9942484256"))
                .findAny().isPresent());
    }

    @Test
    @DisabledOnOs(value = OS.WINDOWS, disabledReason = "This is disabled on Windows OS")
    @DisplayName("Should not create contact on Windows OS")
    public void shouldNotCreateContactOnWindowsOS() throws Exception {
//        ContactManager contactManager=new ContactManager();//moved to beforeEach block
        contactManager.addContact("kavin", "kumar", "9942484256");
        Assertions.assertFalse(contactManager.getAllContact().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContact().size());
        Assertions.assertTrue(contactManager.getAllContact().stream().filter(contact ->
                        contact.getFirstName().equalsIgnoreCase("kavin") &&
                                contact.getLastName().equalsIgnoreCase("kumar") && contact.getPhoneNumber().equalsIgnoreCase("9942484256"))
                .findAny().isPresent());
    }

    //for setting environment we can select edit configuration and provide the value as -DENV=DEV in vmoptions after -ea
    //If this doesn't work please check if the correct configuration is executed as there can be multiple configuration
    // created
    @Test
    @DisplayName("Should create contact on Developer machine")
    public void shouldCreateContactInDEV() throws Exception {
        Assumptions.assumeTrue("DEV".equals(System.getProperty("ENV")));
//        ContactManager contactManager=new ContactManager();//moved to beforeEach block
        contactManager.addContact("kavin", "kumar", "9942484256");
        Assertions.assertFalse(contactManager.getAllContact().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContact().size());
    }

    @RepeatedTest(value = 5, name = "test repeated {currentRepetition} of {totalRepetitions}")
    @DisplayName("Should create contact multipleTimes")
    public void shouldCreateContactsRepeatedly() throws Exception {
//        ContactManager contactManager=new ContactManager();//moved to beforeEach block
        contactManager.addContact("kavin", "kumar", "9942484256");
        Assertions.assertFalse(contactManager.getAllContact().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContact().size());
    }

    @ParameterizedTest
    @ValueSource(strings = {"9942484256","8667038594","8667491819"})// success case
//    @ValueSource(strings = {"7601887759"})// failure case
    @DisplayName("Should create contact using different phonenumbers")
    public void shouldCreateContactsWithDifferentPhoneNumbers(String phoneNumber) throws Exception {
//        ContactManager contactManager=new ContactManager();//moved to beforeEach block
        contactManager.addContact("kavin", "kumar", phoneNumber);
        Assertions.assertFalse(contactManager.getAllContact().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContact().size());
    }

    @ParameterizedTest
    @MethodSource("getTestContacts")// The below method is the method source for this test
    @DisplayName("Should create contact using methodSource")
    public void shouldCreateContactsWithMethodSource(String phoneNumber) throws Exception {
//        ContactManager contactManager=new ContactManager();//moved to beforeEach block
        contactManager.addContact("kavin", "kumar", phoneNumber);
        Assertions.assertFalse(contactManager.getAllContact().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContact().size());
    }

    public static String[] getTestContacts(){
        return new String[]{"9942484256","8667038594","8667491819"};
    }

    @ParameterizedTest
    @CsvSource({"9942484256","8667038594","8667491819"})
    @DisplayName("Should create contact using CSV source")
    public void shouldCreateContactsWithCSVSource(String phoneNumber) throws Exception {
//        ContactManager contactManager=new ContactManager();//moved to beforeEach block
        contactManager.addContact("kavin", "kumar", phoneNumber);
        Assertions.assertFalse(contactManager.getAllContact().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContact().size());
    }

    @ParameterizedTest
    @CsvFileSource( resources = {"/test.csv"})
    @DisplayName("Should create contact using CSV source")
    public void shouldCreateContactsWithCSVFileSource(String phoneNumber) throws Exception {
//        ContactManager contactManager=new ContactManager();//moved to beforeEach block
        contactManager.addContact("kavin", "kumar", phoneNumber);
        Assertions.assertFalse(contactManager.getAllContact().isEmpty());
        Assertions.assertEquals(1, contactManager.getAllContact().size());
    }

//The below disabled is not working when running seperately but when running bthe entire test it runs fine
    @Test
    @Disabled
    public void disabledTest(){

    }
}