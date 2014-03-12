package com.blackmoon.adapter;



public class NavigationDrawerListItem {
	public static final int CATEGORY_NAME = 0;
	public static final int SCREEN_IN_CATEGORY = 1;
	
	protected int mType;
	protected int mIconLeft;
	protected String mText;
	protected String mCounter;
	protected OnItemClickHandler mOnItemClickHandler;
	
	public int getType() {
		return mType;
	}
	
	public void setType(int mType) {
		this.mType = mType;
	}
	public int getIconLeft() {
		return mIconLeft;
	}
	public void setIconLeft(int mIconLeft) {
		this.mIconLeft = mIconLeft;
	}
	public String getText() {
		return mText;
	}
	public void setText(String mText) {
		this.mText = mText;
	}
	public String getCounter() {
		return mCounter;
	}
	public void setCounter(String mCounter) {
		this.mCounter = mCounter;
	}
	public OnItemClickHandler getOnItemClickHandler() {
		return mOnItemClickHandler;
	}
	public void setOnItemClickHandler(OnItemClickHandler mOnItemClickHandler) {
		this.mOnItemClickHandler = mOnItemClickHandler;
	} 
	
	public NavigationDrawerListItem(int mType, int mIconLeft, String mText, String mCounter, OnItemClickHandler mOnItemClickHandler) {
		this.mType = mType;
		this.mIconLeft = mIconLeft;
		this.mText = mText;
		this.mCounter = mCounter;
		this.mOnItemClickHandler = mOnItemClickHandler;
	}
	
}