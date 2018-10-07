package com.yourzeromax.recyclerviewdemo.Bean;

public class GroupInfo {
    //组号
    private int mGroupID;
    // 每一个组的hader title
    private String mTitle;
    //每个Item在当前组的位置，用于判断是否为第一个；
    private int position;

    // 一个组的item数量
    private int mGroupCount;


    public GroupInfo(int groupId, String title) {
        this.mGroupID = groupId;
        this.mTitle = title;
    }

    public int getGroupID() {
        return mGroupID;
    }

    public void setGroupID(int groupID) {
        this.mGroupID = groupID;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public void setGroupCount(int count) {
        this.mGroupCount = count;
    }

    /**
     * 判断是否是组内第一个，如果是的话则绘制Header
     *
     * @return
     */
    public boolean isFirstInGroup() {
        return position == 0;
    }

    /**
     * 判断是否是组内最后一个
     *
     * @return
     */
    public boolean isLastInGroup() {
        return position == mGroupCount - 1 && position >= 0;
    }

}
