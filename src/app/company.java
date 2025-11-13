package app;

import java.net.InetAddress;
import java.net.UnknownHostException;
public class company
{
	String name;
	String ipAddressBlock;
	department[] deps;
	boolean enoughIPs;
	
	
	
	// The boolean enough IPs will be based on the ipAddressBlock and the ips each department needs.
	public company (String nameC, String ipAddressBlockC, department[] departments)
	{
		this.name = nameC;
		this.ipAddressBlock = ipAddressBlockC;
		this.deps = departments;
		
	}
	
	
	public boolean ipAddressBlockValid()
	{
		String[] blockParts = ipAddressBlock.split("/");
		try {
			InetAddress ip = InetAddress.getByName(blockParts[0]);
			return ip.getHostAddress().equals(blockParts[0]) && ipAddressBlock.contains(".");
		}
		catch (UnknownHostException ex)
		{
			return false;
		}
		
	}
	
	public boolean subnetCompany()
	{
			
		if (!ipAddressBlockValid())
		{
			return false;
		}
		
		String[] ipParts = this.ipAddressBlock.split("/");
		long baseIp = calculator.ipToLong(ipParts[0]);
		int prefixLength = Integer.parseInt(ipParts[1]);
		
		long mask = calculator.prefixToMask(prefixLength);
		long currentIp = baseIp & mask;
		
		long baseEnd = currentIp + (long) Math.pow(2, 32-prefixLength)-1;
		
		System.out.println("Starting point IP: " + calculator.longToIP(currentIp));
		System.out.println("Base end IP: " + calculator.longToIP(baseEnd));
		
		System.out.println("Amount of Ips = " + (baseEnd-currentIp));
		
		
		for (department d: deps)
		{
			
			boolean poss = calculator.subnetCalc(d, currentIp, baseEnd);
			
			if (!poss) 
			{
				return false;
			}
			
			currentIp = d.broadcastAddress + 1;
			
			
			
		}
		
		return true;
		
	}
		
}