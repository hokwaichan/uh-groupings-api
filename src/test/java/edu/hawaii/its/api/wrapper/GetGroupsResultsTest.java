package edu.hawaii.its.api.wrapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.hawaii.its.api.util.JsonUtil;
import edu.hawaii.its.api.util.PropertyLocator;

import edu.internet2.middleware.grouperClient.ws.beans.WsGetGroupsResults;

public class GetGroupsResultsTest {
    final static private String SUCCESS = "SUCCESS";
    private PropertyLocator propertyLocator;

    @BeforeEach
    public void beforeEach() throws Exception {
        propertyLocator = new PropertyLocator("src/test/resources", "grouper.test.properties");
    }

    @Test
    public void constructor() throws JsonProcessingException {
        assertNotNull(new GetGroupsResults(null));
        String json = propertyLocator.find("ws.get.groups.results.success");
        WsGetGroupsResults wsGetGroupsResults = JsonUtil.asObject(json, WsGetGroupsResults.class);
        GetGroupsResults getGroupsResults = new GetGroupsResults(wsGetGroupsResults);
        assertNotNull(getGroupsResults);
    }

    @Test
    public void successfulResults() throws JsonProcessingException {
        String json = propertyLocator.find("ws.get.groups.results.success");
        WsGetGroupsResults wsGetGroupsResults = JsonUtil.asObject(json, WsGetGroupsResults.class);
        GetGroupsResults getGroupsResults = new GetGroupsResults(wsGetGroupsResults);
        assertNotNull(getGroupsResults);
        assertNotNull(getGroupsResults.getGroups());
        assertEquals(2, getGroupsResults.getGroups().size());
        assertNotNull(getGroupsResults.getSubject());
        assertEquals(SUCCESS, getGroupsResults.getResultCode());
    }

    @Test
    public void emptyGroups() throws JsonProcessingException {
        String json = propertyLocator.find("ws.get.groups.results.empty.groups");
        WsGetGroupsResults wsGetGroupsResults = JsonUtil.asObject(json, WsGetGroupsResults.class);
        GetGroupsResults getGroupsResults = new GetGroupsResults(wsGetGroupsResults);
        assertNotNull(getGroupsResults);
        assertNotNull(getGroupsResults.getGroups());
        assertNotNull(getGroupsResults.getSubject());
        assertEquals("FAILURE", getGroupsResults.getResultCode());
    }

    @Test
    public void emptyResults() throws JsonProcessingException {
        String json = propertyLocator.find("ws.empty.results");
        WsGetGroupsResults wsGetGroupsResults = JsonUtil.asObject(json, WsGetGroupsResults.class);
        GetGroupsResults getGroupsResults = new GetGroupsResults(wsGetGroupsResults);
        assertNotNull(getGroupsResults);
        assertNotNull(getGroupsResults.getGroups());
        assertNotNull(getGroupsResults.getSubject());
        assertEquals("FAILURE", getGroupsResults.getResultCode());
    }
}
