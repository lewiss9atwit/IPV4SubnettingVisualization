package app;

// This keeps track of ranges availalbe so we can go back to one lets say we skipped for a larger subnet size for a ealier department
public class subnetRange
{
	public long start;
	public long end;
	
	public subnetRange(long s, long e)
	{
		this.start = s;
		this.end = e;
	}
	
}