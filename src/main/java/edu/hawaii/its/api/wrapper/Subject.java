package edu.hawaii.its.api.wrapper;

import java.util.Arrays;

import edu.internet2.middleware.grouperClient.ws.beans.WsSubject;

/**
 * A wrapper for WsSubject.
 */
public class Subject extends Results {

    private final WsSubject wsSubject;

    public Subject(WsSubject wsSubject) {
        if (wsSubject == null) {
            this.wsSubject = new WsSubject();
        } else {
            this.wsSubject = wsSubject;
        }
    }

    public Subject() {
        this.wsSubject = new WsSubject();
    }

    public String getUhUuid() {
        String uhUuid = wsSubject.getId();
        return uhUuid != null ? uhUuid : "";
    }

    public void setUhUuid(String uhUuid) {
        wsSubject.setId(uhUuid);
    }

    public String getUid() {
        if (wsSubject.getIdentifierLookup() != null) {
            return wsSubject.getIdentifierLookup();
        }
        return getAttributeValue(0);
    }

    public void setUid(String uid) {
        wsSubject.setIdentifierLookup(uid);
    }

    public String getName() {
        String name = wsSubject.getName();
        return name != null ? name : "";
    }

    public void setName(String name) {
        wsSubject.setName(name);
    }

    public String getFirstName() {
        return getAttributeValue(3);
    }

    public void setFirstName(String firstName) {
        String[] attributeValues = wsSubject.getAttributeValues();
        attributeValues[3] = firstName;
        wsSubject.setAttributeValues(attributeValues);
    }

    public String getLastName() {
        return getAttributeValue(2);
    }

    public void setLastName(String lastName) {
        String[] attributeValues = wsSubject.getAttributeValues();
        attributeValues[2] = lastName;
        wsSubject.setAttributeValues(attributeValues);
    }

    private String getAttributeValue(int index) {
        String[] attributeValues = wsSubject.getAttributeValues();
        if (isEmpty(attributeValues)) {
            return "";
        }
        String attributeValue = wsSubject.getAttributeValue(index);
        return attributeValue != null ? attributeValue : "";
    }

    @Override
    public String getResultCode() {
        String resultCode = wsSubject.getResultCode();
        return resultCode != null ? resultCode : "";
    }

    @Override
    public boolean equals(Object obj) {
        String name = getName();
        String uid = getUid();
        String uhUuid = getUhUuid();

        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Subject other = (Subject) obj;
        if (name == null) {
            if (other.getName() != null)
                return false;
        } else if (!name.equals(other.getName()))
            return false;
        if (uid == null) {
            if (other.getUid() != null)
                return false;
        } else if (!uid.equals(other.getUid()))
            return false;
        if (uhUuid == null) {
            return other.getUhUuid() == null;
        } else
            return uhUuid.equals(other.getUhUuid());
    }

    public String getSourceId() {
        return wsSubject.getSourceId();
    }

    @Override
    public String toString() {
        return "Subject [name=" + getName() + ", uhUuid=" + getUhUuid() + ", uid=" + getUid() + "]";
    }

    /**
     * A WsSubject containing empty string for all attribute values returns false.
     */
    public boolean hasUHAttributes() {
        String[] attributeValues = this.wsSubject.getAttributeValues();
        if (attributeValues == null) {
            return false;
        }
        return !Arrays.stream(attributeValues).allMatch(value -> value.equals(""));
    }
}
