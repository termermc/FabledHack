package net.termer.fabledhack.scripting;

public class MetaTag {
	private String n = null;
	private String d = null;
	
	public MetaTag(String name, String data) {
		n = name;
		d = data;
	}
	
	public String getTagName() {
		return n;
	}
	public String getData() {
		return d;
	}
}
