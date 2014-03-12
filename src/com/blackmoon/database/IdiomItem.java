package com.blackmoon.database;

public class IdiomItem {

	private int _id;
	private String _category;
	private String _english;
	private String _vietnamese;
	private String _author;
	private int _favorite;
	private int _award;

	public IdiomItem() {
		_id = -1;
		_category = " ";
		_english = " ";
		_vietnamese = " ";
		_author = " ";
		_favorite = 0;
		_award = 0;
	};
	
	public IdiomItem(IdiomItem item){
		set_id(item.get_id());
		set_category(item.get_category());
		set_english(item.get_english());
		set_vietnamese(item.get_vietnamese());
		set_author(item.get_author());
		set_favorite(item.get_favorite());
		set_award(item.get_award());
	}

	
	
	//getter and setter
	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public String get_category() {
		return _category;
	}

	public void set_category(String _category) {
		this._category = _category;
	}

	public String get_english() {
		return _english;
	}

	public void set_english(String _english) {
		this._english = _english;
	}

	public String get_vietnamese() {
		return _vietnamese;
	}

	public void set_vietnamese(String _vietnamese) {
		this._vietnamese = _vietnamese;
	}

	public String get_author() {
		return _author;
	}

	public void set_author(String _author) {
		this._author = _author;
	}
	
	
	

	public int get_favorite() {
		return _favorite;
	}

	public void set_favorite(int _favorite) {
		this._favorite = _favorite;
	}

	public int get_award() {
		return _award;
	}

	public void set_award(int _award) {
		this._award = _award;
	}
	
	@Override
	public String toString() {
		return "item" + _category + ", " + _english + ", " + _vietnamese + ", " + _author;
				
	}

}
