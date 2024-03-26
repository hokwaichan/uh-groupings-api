package edu.hawaii.its.api.groupings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.hawaii.its.api.util.JsonUtil;
import edu.hawaii.its.api.util.PropertyLocator;
import edu.hawaii.its.api.wrapper.GroupAttributeResults;

import edu.internet2.middleware.grouperClient.ws.beans.WsGetAttributeAssignmentsResults;

public class GroupingOptAttributesTest {
    private PropertyLocator propertyLocator;

    @BeforeEach
    public void beforeEach() {
        propertyLocator = new PropertyLocator("src/test/resources", "grouper.test.properties");
    }

    @Test
    public void constructor() throws JsonProcessingException {
        String json = propertyLocator.find("ws.get.attribute.assignment.results.optIn-on.optOut-on");
        WsGetAttributeAssignmentsResults wsGetAttributeAssignmentsResults =
                JsonUtil.asObject(json, WsGetAttributeAssignmentsResults.class);
        GroupAttributeResults groupAttributeResults = new GroupAttributeResults(wsGetAttributeAssignmentsResults);
        GroupingOptAttributes groupingOptAttributes = new GroupingOptAttributes(groupAttributeResults);
        assertNotNull(groupingOptAttributes);
        groupingOptAttributes = new GroupingOptAttributes();
        assertNotNull(groupingOptAttributes);
    }

    @Test
    public void optInOnOptOutOn() throws JsonProcessingException {
        String json = propertyLocator.find("ws.get.attribute.assignment.results.optIn-on.optOut-on");
        WsGetAttributeAssignmentsResults wsGetAttributeAssignmentsResults =
                JsonUtil.asObject(json, WsGetAttributeAssignmentsResults.class);
        GroupAttributeResults groupAttributeResults = new GroupAttributeResults(wsGetAttributeAssignmentsResults);
        GroupingOptAttributes groupingOptAttributes = new GroupingOptAttributes(groupAttributeResults);
        assertEquals("SUCCESS", groupingOptAttributes.getResultCode());
        assertNotNull(groupingOptAttributes.getGroupPath());
        assertTrue(groupingOptAttributes.isOptInOn());
        assertTrue(groupingOptAttributes.isOptOutOn());

    }

    @Test
    public void optInOnOptOutOff() throws JsonProcessingException {
        String json = propertyLocator.find("ws.get.attribute.assignment.results.optIn-on.optOut-off");
        WsGetAttributeAssignmentsResults wsGetAttributeAssignmentsResults =
                JsonUtil.asObject(json, WsGetAttributeAssignmentsResults.class);
        GroupAttributeResults groupAttributeResults = new GroupAttributeResults(wsGetAttributeAssignmentsResults);
        GroupingOptAttributes groupingOptAttributes = new GroupingOptAttributes(groupAttributeResults);
        assertEquals("SUCCESS", groupingOptAttributes.getResultCode());
        assertNotNull(groupingOptAttributes.getGroupPath());
        assertTrue(groupingOptAttributes.isOptInOn());
        assertFalse(groupingOptAttributes.isOptOutOn());

    }

    @Test
    public void optInOffOptOutOn() throws JsonProcessingException {
        String json = propertyLocator.find("ws.get.attribute.assignment.results.optIn-off.optOut-on");
        WsGetAttributeAssignmentsResults wsGetAttributeAssignmentsResults =
                JsonUtil.asObject(json, WsGetAttributeAssignmentsResults.class);
        GroupAttributeResults groupAttributeResults = new GroupAttributeResults(wsGetAttributeAssignmentsResults);
        GroupingOptAttributes groupingOptAttributes = new GroupingOptAttributes(groupAttributeResults);
        assertEquals("SUCCESS", groupingOptAttributes.getResultCode());
        assertNotNull(groupingOptAttributes.getGroupPath());
        assertFalse(groupingOptAttributes.isOptInOn());
        assertTrue(groupingOptAttributes.isOptOutOn());
    }

    @Test
    public void optInOffOptOutOff() throws JsonProcessingException {
        String json = propertyLocator.find("ws.get.attribute.assignment.results.optIn-off.optOut-off");
        WsGetAttributeAssignmentsResults wsGetAttributeAssignmentsResults =
                JsonUtil.asObject(json, WsGetAttributeAssignmentsResults.class);
        GroupAttributeResults groupAttributeResults = new GroupAttributeResults(wsGetAttributeAssignmentsResults);
        GroupingOptAttributes groupingOptAttributes = new GroupingOptAttributes(groupAttributeResults);
        assertEquals("SUCCESS", groupingOptAttributes.getResultCode());
        assertNotNull(groupingOptAttributes.getGroupPath());
        assertFalse(groupingOptAttributes.isOptInOn());
        assertFalse(groupingOptAttributes.isOptOutOn());
    }

    @Test
    public void failure() {
        GroupingOptAttributes groupingOptAttributes =
                new GroupingOptAttributes(new GroupAttributeResults(new WsGetAttributeAssignmentsResults()));
        assertEquals("FAILURE", groupingOptAttributes.getResultCode());
        assertEquals("", groupingOptAttributes.getGroupPath());
        assertFalse(groupingOptAttributes.isOptInOn());
        assertFalse(groupingOptAttributes.isOptOutOn());
    }
}
