package edu.hawaii.its.api.groupings;

import edu.hawaii.its.api.wrapper.HasMembersResults;

public class GroupingExportableGroupsResults implements GroupingResult{

    private String resultCode;
    private String groupPath;
    private boolean all;
    private boolean basis;
    private boolean include;
    private boolean exclude;

    public GroupingExportableGroupsResults(HasMembersResults hasMembersResults) {
        System.out.println(hasMembersResults);
        setResultCode(hasMembersResults.getResultCode());
        this.all = checkAllStatus(hasMembersResults);
        this.basis = checkBasisStatus(hasMembersResults);
        this.include = checkIncludeStatus(hasMembersResults);
        this.exclude = checkExcludeStatus(hasMembersResults);
    }

    @Override public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    @Override public String getGroupPath() {
        return groupPath;
    }

    public void setGroupPath(String groupPath) {
        this.groupPath = groupPath;
    }

    private boolean checkAllStatus(HasMembersResults hasMembersResults) {
        return !hasMembersResults.getResults().isEmpty();
    }

    private boolean checkBasisStatus(HasMembersResults hasMembersResults) {
        return !hasMembersResults.getResults().isEmpty();
    }

    private boolean checkIncludeStatus(HasMembersResults hasMembersResults) {
        return !hasMembersResults.getResults().isEmpty();
    }

    private boolean checkExcludeStatus(HasMembersResults hasMembersResults) {
        return !hasMembersResults.getResults().isEmpty();
    }

    public boolean isAll() {
        return all;
    }

    public boolean isBasis() {
        return basis;
    }

    public boolean isInclude() {
        return include;
    }

    public boolean isExclude() {
        return exclude;
    }

}