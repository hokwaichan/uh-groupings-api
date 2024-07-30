package edu.hawaii.its.api.wrapper;

import java.util.List;

import edu.internet2.middleware.grouperClient.api.GcDeleteMember;
import edu.internet2.middleware.grouperClient.ws.beans.WsDeleteMemberResults;

public class RemoveMembersCommand extends GrouperCommand implements Command<RemoveMembersResults> {
    private final GcDeleteMember gcDeleteMember;

    public RemoveMembersCommand() {
        this.gcDeleteMember = new GcDeleteMember();
        this.gcDeleteMember.assignContentType("text/x-json"); // Remove after upgrading to Grouper 4
        includeUhMemberDetails(true);
    }

    public RemoveMembersResults execute() {
        WsDeleteMemberResults wsDeleteMemberResults = gcDeleteMember.execute();
        return new RemoveMembersResults(wsDeleteMemberResults);
    }

    public RemoveMembersCommand addUhIdentifiers(List<String> uhIdentifiers) {
        for (String uhIdentifier : uhIdentifiers) {
            addUhIdentifier(uhIdentifier);
        }
        return this;
    }

    public RemoveMembersCommand addUhIdentifier(String uhIdentifier) {
        if (isUhUuid(uhIdentifier)) {
            addUhUuid(uhIdentifier);
            includeUhMemberDetails(true);
        } else {
            addUid(uhIdentifier);
        }
        return this;
    }

    private RemoveMembersCommand addUhUuid(String uhUuid) {
        gcDeleteMember.addSubjectId(uhUuid);
        return this;
    }

    private RemoveMembersCommand addUid(String uid) {
        gcDeleteMember.addSubjectIdentifier(uid);
        return this;
    }

    public RemoveMembersCommand assignGroupPath(String groupPath) {
        gcDeleteMember.assignGroupName(groupPath);
        return this;
    }

    public RemoveMembersCommand includeUhMemberDetails(boolean includeDetails) {
        gcDeleteMember.assignIncludeSubjectDetail(includeDetails);
        return this;
    }

    public RemoveMembersCommand owner(String uhIdentifier) {
        gcDeleteMember.assignActAsSubject(subjectLookup(uhIdentifier));
        return this;
    }
}
