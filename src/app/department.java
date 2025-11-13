package app;

public class department
{
	
	public String name;
	public int ipsNeeded;
	public long networkAddress;
	public long broadcastAddress;
	public long subnetMask;
	public int prefix;

	
	
	// This will be the department which has a name and the amount of IPs they need
	public department (String depName, int IPs)
	{
		this.name = depName;
		this.ipsNeeded = IPs;
		
	}
}