package edu.hawaii.its.api.groupings;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.junit.jupiter.api.Test;

import edu.hawaii.its.api.type.MembershipResult;

public class MembershipResultsTest {

    private static Properties properties;

    @Test
    public void testMembershipResultsConstructor() {
        List<MembershipResult> memberships = new ArrayList<>();
        MembershipResult membershipResult = new MembershipResult();
        memberships.add(membershipResult);

        /*
        List<Membership> memberships = new ArrayList<>();
        Group group = new Group("tmp:testiwta:testiwta-aux");
        properties = new Properties();
        String json = properties.getProperty("ws.subject.success.uid");
        WsSubject wsSubject = JsonUtil.asObject(json, WsSubject.class);
        Subject subject = new Subject(wsSubject);
        group.addMember(subject);
        Membership membership = new Membership(subject, group);
        memberships.add(membership);
         */

        MembershipResults membershipResults = new MembershipResults(memberships);
        assertNotNull(membershipResults);
        assertEquals("SUCCESS", membershipResults.getResultCode());
        assertEquals(1, membershipResults.getResults().size());

        MembershipResults emptyResults = new MembershipResults();
        assertNotNull(emptyResults);
        assertEquals("FAILURE", emptyResults.getResultCode());
        assertEquals(0, emptyResults.getResults().size());
    }
}
