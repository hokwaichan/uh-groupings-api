package edu.hawaii.its.api.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Properties;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import edu.hawaii.its.api.wrapper.Subject;

import edu.internet2.middleware.grouperClient.ws.beans.WsSubject;

import org.springframework.beans.factory.annotation.Value;

public class JsonUtilTest {
    @Value("${groupings.api.test.uh-uids}")
    private List<String> TEST_UIDS;

    @Value("${groupings.api.test.uh-numbers}")
    private List<String> TEST_NUMBERS;

    @Value("${groupings.api.test.uh-names}")
    private List<String> TEST_NAMES;

    @Value("${groupings.api.test.uh-first-names}")
    private List<String> TEST_FIRSTNAMES;

    @Value("${groupings.api.test.uh-last-names}")
    private List<String> TEST_LASTNAMES;

    final static private String SUCCESS = "SUCCESS";
    final static private String SUBJECT_NOT_FOUND = "SUBJECT_NOT_FOUND";
    private static PropertyLocator propertyLocator;
    private static Subject subject0;
    private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errStream = new ByteArrayOutputStream();

    @BeforeAll
    public static void beforeAll() throws JsonProcessingException {
        propertyLocator = new PropertyLocator("src/test/resources", "grouper.test.properties");
        String json = propertyLocator.find("ws.subject.success.uid");
        WsSubject wsSubject = JsonUtil.asObject(json, WsSubject.class);
        subject0 = new Subject(wsSubject);
        String subjectJson = JsonUtil.asJson(subject0);
        Subject subject1 = JsonUtil.asObject(subjectJson, Subject.class);

        System.out.println("json = " + json);
        System.out.println("wsSubject = " + wsSubject);
        System.out.println("subject0 = " + subject0);
        System.out.println("subjectJson = " + subjectJson);
        System.out.println("subject1 = " + subject1);
    }

    @BeforeEach
    public void beforeEach() {
        System.setOut(new PrintStream(outStream));
        System.setErr(new PrintStream(errStream));
    }

    @Test
    public void asJsonAsObject() throws JsonProcessingException {
        String subjectJson = JsonUtil.asJson(subject0);
        Subject subject1 = JsonUtil.asObject(subjectJson, Subject.class);
        assertEquals(subject0.getName(), subject1.getName());
        assertEquals(subject0.getUhUuid(), subject1.getUhUuid());
        assertEquals(subject0.getUid(), subject1.getUid());
        assertEquals(subject0.getFirstName(), subject1.getFirstName());
        assertEquals(subject0.getLastName(), subject1.getLastName());
        assertEquals(subject0.getClass(), subject1.getClass());
        assertDoesNotThrow(() -> JsonUtil.asJson(mock(Object.class)));
        assertDoesNotThrow(() -> JsonUtil.asObject("", Object.class));

    }

    @Test
    public void prettyPrint() {
        JsonUtil.prettyPrint(subject0);
        assertTrue(outStream.toString().contains("name"));
        assertDoesNotThrow(() -> JsonUtil.prettyPrint(mock(Object.class)));
    }

    @Test
    public void printJson() {
        JsonUtil.printJson(subject0);
        assertFalse(errStream.toString().trim().isEmpty());
        assertDoesNotThrow(() -> JsonUtil.printJson(mock(Object.class)));
    }

    @Test
    public void problems() {
        String json = JsonUtil.asJson(null);
        assertEquals(json, "null");

        json = JsonUtil.asJson("{}");
        assertEquals(json, "\"{}\"");

        json = JsonUtil.asJson("mistake");
        assertEquals(json, "\"mistake\"");
    }

    @Test
    public void constructorIsPrivate() throws Exception {
        Constructor<JsonUtil> constructor = JsonUtil.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        constructor.newInstance();
    }

}
