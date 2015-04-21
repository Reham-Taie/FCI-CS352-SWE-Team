package com.FCI.SWE.ServicesModels;

public abstract class PostType {
	String Post = "";
	String TimelineName = "";
	String Feeling = "";
	String Privacy = "";
	public void setPost(String P)
	{
		Post = P ; 
	}
	public void setTimelineName(String N)
	{
		TimelineName = N ; 
	}
	public void setPrivacy(String v)
	{
		Privacy = v; 
	}
	public void setFeeling(String f)
	{
		Feeling = f; 
	}
	public abstract void create();

}
