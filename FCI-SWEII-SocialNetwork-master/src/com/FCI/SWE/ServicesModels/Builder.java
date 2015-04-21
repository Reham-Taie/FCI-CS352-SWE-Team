package com.FCI.SWE.ServicesModels;

public class Builder {
	String post;
	String TimelineName;
	String Feeling;
	String Privacy;
	
	public void createPost() {
	}

	public void setPost(String p) {
		post = p;
	}

	public void setTimelineName(String T) {
		TimelineName = T;
	}
	public void setFeeling(String T) {
		Feeling = T;
	}
	public void setPrivacy(String u) {
		Privacy = u;
	}
	public void checkType(String X) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException {
		PostType p = (PostType) Class
				.forName("com.FCI.SWE.ServicesModels." + X).newInstance();
		p.setPost(post);
		p.setTimelineName(TimelineName);
		p.setFeeling(Feeling);
		p.setPrivacy(Privacy);
		p.create();

	}
}
