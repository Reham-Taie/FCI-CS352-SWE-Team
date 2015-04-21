package com.FCI.SWE.ServicesModels;

public  class TimelinePostBuilder extends Builder {
private TimelinePost p;
public void setPost(String s)
{
p.setPost(s);	
}
@Override
public void createPost() {
	// TODO Auto-generated method stub
	p.create();
}


}
