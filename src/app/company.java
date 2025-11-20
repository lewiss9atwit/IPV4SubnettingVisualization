package app;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
public class company
{
	String name;
	String ipAddressBlock;
	String startIp;
	String baseEndIp;
	long totalIps;
	department[] deps;
	boolean enoughIPs;
	int prefix;
	
	
	
	
	// The boolean enough IPs will be based on the ipAddressBlock and the ips each department needs.
	public company (String nameC, String ipAddressBlockC, department[] departments)
	{
		this.name = nameC;
		this.ipAddressBlock = ipAddressBlockC;
		this.deps = departments;
		
	}
	
	// Tests the ipAddressBlock of the company to see if it can be used
	public static boolean ipAddressBlockValid(String ipAddress)
	{
		
		int indexOfSlash = ipAddress.indexOf("/");
		
		indexOfSlash= ipAddress.indexOf("/", indexOfSlash+1);
		
		if (indexOfSlash != -1) {return false;}
		
		
		
		String[] blockParts = ipAddress.split("/");
		if (blockParts.length == 2)
		{
			int prefix = Integer.parseInt(blockParts[1]);
			try {
				InetAddress ip = InetAddress.getByName(blockParts[0]);
				return ip.getHostAddress().equals(blockParts[0]) && ipAddress.contains(".") && (32 >= prefix &&  prefix >= 0);
			}
			catch (UnknownHostException ex)
			{
				return false;
			}
		}
		return false;
		
		
	}
	
	public static boolean noIps(String ipAddress)
	{
		
		String[] blockParts = ipAddress.split("/");
		if (blockParts.length == 2)
		{
			int prefix = Integer.parseInt(blockParts[1]);
			return (prefix == 32);
		}
		return false;
	}
	
	public void subnetCompany()
	{
		
		String[] ipParts = this.ipAddressBlock.split("/");
		long baseIp = calculator.ipToLong(ipParts[0]);
		this.prefix = Integer.parseInt(ipParts[1]);
		
		long mask = calculator.prefixToMask(prefix);
		long currentIp = baseIp & mask;
		
		long baseEnd = currentIp + (long) Math.pow(2, 32-prefix)-1;
		
		subnetRange ipRange = new subnetRange(currentIp, baseEnd);
		
		List<subnetRange> ipRanges = new ArrayList<>();
		ipRanges.add(ipRange);
		
		
		
		this.startIp = ("Starting point IP: " + calculator.longToIP(currentIp) + "/" + prefix);
		this.baseEndIp  = ("Base end IP: " + calculator.longToIP(baseEnd) + "/" + prefix);
		this.totalIps = (baseEnd-currentIp + 1);
		
		
		for (department d: deps)
		{
			
			calculator.subnetCalc(d, ipRanges);
			
		}
		
		
		
	}
		
}