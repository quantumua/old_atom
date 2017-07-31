package com.betamedia.atom.core.dsl.operations.impl;

import org.springframework.beans.factory.annotation.Autowired;
import com.betamedia.atom.core.dsl.operations.WithdrawalValidation;
import com.betamedia.atom.core.environment.tp.EnvironmentDependent;
import com.betamedia.atom.core.persistence.repositories.AbstractConnectionBaseRepository;
import com.betamedia.atom.core.persistence.repositories.AbstractConnectionRoleBaseRepository;
import com.betamedia.atom.core.persistence.repositories.AbstractContactBaseRepository;
import com.betamedia.atom.core.persistence.repositories.AbstractContactExtensionRepository;

public abstract class AbstractWithdrawalValidationEntities  <T extends EnvironmentDependent> implements WithdrawalValidation{
	 
	
    @Autowired
    private AbstractContactExtensionRepository<T> contactExtensionRepository;

    @Autowired
    private AbstractContactBaseRepository<T> contactBaseRepository;

    @Autowired
    private AbstractConnectionBaseRepository<T> connectionBaseRepository;

    @Autowired
    private AbstractConnectionRoleBaseRepository<T> connectionRoleBaseRepository;


   
//    public void assertCustomerRejected(String customerId) {
//        assertCustomerExperience(customerId, REJECTED);
//    }
//
//   
//    public void assertCustomerUnknown(String customerId) {
//        assertCustomerExperience(customerId, UNKNOWN);
//    }
//
//    
//    public void assertCustomerNoExperience(String customerId) {
//        assertCustomerExperience(customerId, NO_EXPERIENCE);
//    }
//
//  
//    public void assertCustomerLowExperience(String customerId) {
//        assertCustomerExperience(customerId, LOW_EXPERIENCE);
//    }
//
//    @Override
//    public void assertCustomerHighExperience(String customerId) {
//        assertCustomerExperience(customerId, HIGH_EXPERIENCE);
//    }
//
//    @Override
//    public void assertCustomerExpert(String customerId) {
//        assertCustomerExperience(customerId, EXPERT);
//    }
//
//    @Override
//    public void assertUsernameRejected(String username) {
//        assertUsernameExperience(username, REJECTED);
//    }
//
//    @Override
//    public void assertUsernameUnknown(String username) {
//        assertUsernameExperience(username, UNKNOWN);
//    }
//
//    @Override
//    public void assertUsernameNoExperience(String username) {
//        assertUsernameExperience(username, NO_EXPERIENCE);
//    }
//
//    @Override
//    public void assertUsernameLowExperience(String username) {
//        assertUsernameExperience(username, LOW_EXPERIENCE);
//    }
//
//    @Override
//    public void assertUsernameHighExperience(String username) {
//        assertUsernameExperience(username, HIGH_EXPERIENCE);
//    }
//
//    @Override
//    public void assertUsernameExpert(String username) {
//        assertUsernameExperience(username, EXPERT);
//    }
//
//    private void assertCustomerExperience(String customerId, ExperienceLevel expectedExperience) {
//        assertEquals(contactExtensionRepository.findOne(customerId).getExperienceLevel(), expectedExperience.getLevel());
//    }
//    
//    private void assertUsernameExperience(String username, ExperienceLevel expectedExperience) {
//        assertEquals(contactExtensionRepository.findByUsername(username).getExperienceLevel(), expectedExperience.getLevel());
//    }
//
//    @Override
//    public void assertUsernameScore(String username, Double expectedScore) {
//        Assert.assertEquals(contactExtensionRepository.findByUsername(username).getExperienceScore(), expectedScore);
//    }
//
//    @Override
//    public void assertUsernameLoginType(String username, AccessType expectedAccessType) {
//        assertEquals(contactExtensionRepository.findByUsername(username).getAccess(), expectedAccessType.getType());
//    }
//
//    @Override
//    public void assertTrafficSource(String userLoginName, int expectedTrafficSourceId) {
//        assertEquals(expectedTrafficSourceId, contactExtensionRepository.findByUsername(userLoginName).getTrafficSource());
//    }
//
//    @Override
//    public void assertContactBaseBirthDate(String email, String expectedBirthDate) {
//        assertEquals(expectedBirthDate, contactBaseRepository.findByEmailAddress1(email).getBirthDate());
//    }
//
//    @Override
//    public void assertUsersHaveConnection(String firstUser, String secondUser, String expectedConnectionRoleName) {
//        String firstUserContactID = contactExtensionRepository.findByUsername(firstUser).getContactId();
//        String secondUserContactID = contactExtensionRepository.findByUsername(secondUser).getContactId();
//        List<ConnectionBase> firstUserConnections = connectionBaseRepository.findByRecord1Id(firstUserContactID);
//        String actualConnectionName = "";
//        for (ConnectionBase connection : firstUserConnections) {
//            if (connection.getRecord2Id().equalsIgnoreCase(secondUserContactID)) {
//                actualConnectionName = connectionRoleBaseRepository
//                        .findByConnectionRoleId(connection.getRecord2RoleId()).getName();
//                break;
//            }
//        }
//        assertEquals(expectedConnectionRoleName, actualConnectionName, actualConnectionName);
//    }
//
//    @Override
//    public void assertUsersHaveNotConnection(String firstUser, String secondUser) {
//        assertTrue("Users have connections in the database.",
//                connectionBaseRepository.findByRecord1Id(
//                        contactExtensionRepository.findByUsername(firstUser).getContactId()).isEmpty()
//        );
//        assertTrue("Users have connections in the database.",
//                connectionBaseRepository.findByRecord1Id(
//                        contactExtensionRepository.findByUsername(secondUser).getContactId()).isEmpty()
//        );
//
//    }
//
//    @Override
//    public void assertBulkEmailHasNotValue(String userLoginName, int notExpectedValue) {
//        assertTrue(contactExtensionRepository.findByUsername(userLoginName)
//                .getAcceptbulkemail() != notExpectedValue);
//    }
//
//    @Override
//    public void assertDoNotPhoneHasNotValue(String userLoginName, String notExpectedDoNotPhoneValue) {
//        assertFalse(contactBaseRepository.findByEmailAddress1(userLoginName)
//                .getDoNotPhone().equalsIgnoreCase(notExpectedDoNotPhoneValue));
//    }
//
//    @Override
//    public void assertUserCreatedInDatabase(String userEmail) {
//        assertTrue("User does not exist in database.",
//                Objects.nonNull(contactBaseRepository.findByEmailAddress1(userEmail)));
//    }
    
//    public void validateSring(String stringdata, String target, String typeComparation ) {
//    	if(typeComparation.equals(Validation.EXACT)){
//    		softAssert.assertEquals(stringdata, target,"String value " + stringdata + "is not equal to " + target);
//    	}
//    	if(typeComparation.equals(Validation.CONTAINS)){
//    		softAssert.assertTrue(stringdata.contains(target),"String value " + stringdata + "not contains" + target);
//    	}
//    	
//    }
    
    
    

    
}
