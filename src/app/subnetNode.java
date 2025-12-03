package app;

public class subnetNode
{
	public long size;
	public boolean depUses;
	public String companyName;
	public String networkAddress;
	public String broadcastAddress;
	public String subnetMask;
	public subnetNode left;
	public subnetNode right;
	public subnetNode parent;
	public long ipsNeeded;
	
	public subnetNode(department dep)
	{
		this.size = (long) Math.pow(2,32-dep.prefix);
		this.ipsNeeded = dep.ipsNeeded;
		this.depUses = true;
		this.companyName = "Deparment Name: " + dep.name;
		this.networkAddress = ("Subnet ID: " + calculator.longToIP(dep.networkAddress) + "/" + dep.prefix);
		this.broadcastAddress = ("Broadcast ID: " + calculator.longToIP(dep.broadcastAddress)+ "/" + dep.prefix);
		this.subnetMask  = ("Subnet Mask: " + calculator.longToIP(dep.subnetMask));
		this.left = null;
		this.right = null;
		
	}
	
	public subnetNode (long size)
	{
		this.size = size;
		this.depUses = false;
	}
	
}