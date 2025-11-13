package app;

import java.net.InetAddress;
import java.net.UnknownHostException;
public class calculator
{
	
	// This will return the subnet Mask of the department
	public static boolean subnetCalc(department dep, long baseIp, long baseEnd)
	{
		
		if (!subnettingPossible(baseIp, baseEnd, dep.ipsNeeded)) {return false;}
		int bitsNeeded = 0;
		while (Math.pow(2, bitsNeeded) < dep.ipsNeeded)
		{
			bitsNeeded++;
		}
		System.out.println("Here are the amount of bits needed: " + bitsNeeded);
		
		
		
		int prefix = 32 - bitsNeeded;
		long subnetMask = prefixToMask(prefix);
		
		
		
		// Performs the and operation with the bits of the baseIpNum and the subnetMask to get the proper number for the network address
		long networkAddress = baseIp;
		
		// To get broadcast Id we add the amount of Ips from the amoutn of bits we need to get the amount of IPs we want and subtract one from that value because the 
		// Ip  octet starts at 0 to a number so going from 0 to 64 is 65 Ip addresses but we are subnetting to get 64.
		int hostIps = (int) Math.pow(2,  bitsNeeded);
		long broadcastAddress = networkAddress + hostIps -1;
		
		
		dep.networkAddress = networkAddress;
		dep.broadcastAddress = broadcastAddress;
		dep.subnetMask = subnetMask;
		
		
		System.out.println("Network Address: " + longToIP(networkAddress) + "/" + prefix);
		System.out.println("Broadcast Address: " + longToIP(broadcastAddress)+ "/" + prefix);
		System.out.println("Subnet Mask: " + longToIP(subnetMask));
		
		
		return true;
		
	}
	
	
	public static boolean subnettingPossible(long baseIp, long baseEnd, int ipsNeeded)
	{
		
		// Checks if there are enough ips for the department.
		long ipsAvailable = baseEnd - baseIp + 1 - 2;
		if (ipsAvailable  <  ipsNeeded)
		{
			System.out.println("There are not enough IPV4 Address available becasue there are " + ipsAvailable + " availalbe while regarding networkAddress and broadcast Address.\nAnd we need "+ ipsNeeded + " addresses.");
			return false;
		}
		
		return true;
		
		
		
	}
	
	public static long ipToLong(String ipAddress)
	{
		
		// Splits the Ip address by each period in it.
		String[] octets = ipAddress.split("\\.");
		
		
		long result = 0;
		for (String octet: octets)
		{
			result = result * 256 + Integer.parseInt(octet);
		}
		return result;
	}
	
	// Gets the subnet mask from the prefix
	public static long prefixToMask(int prefix)
	{
		return 0xFFFFFFFFL << (32-prefix);
	}
	
	// Converts the int value into an Ip address String
	public static String longToIP(long ipNum)
	{
		return ((ipNum >> 24) & 0xFF) + "." + ((ipNum >> 16) & 0xFF) + "." + ((ipNum >> 8) & 0xFF) + "." + (ipNum & 0xFF);
	}
	
}