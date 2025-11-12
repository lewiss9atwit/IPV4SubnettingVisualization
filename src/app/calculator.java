package app;

import java.net.InetAddress;
import java.net.UnknownHostException;
public class calculator
{
	
	// This will return the subnet Mask of the department
	public static boolean SubnetCalc(department dep, String ip)
	{
		
		
		String[] ipParts = ip.split("/");
		String baseIp = ipParts[0];
		long baseIpNum = ipToLong(baseIp);
		int prefixLength = Integer.parseInt(ipParts[1]);
		
		if (!subnettingPossible(prefixLength, dep.ipsNeeded)) 
		{
			return false;
		}
		
		int bitsNeeded = 0;
		while (Math.pow(2, bitsNeeded) < dep.ipsNeeded)
		{
			bitsNeeded++;
		}
		System.out.println("Here are the amount of bits needed: " + bitsNeeded);
		
		
		long subnetMask = prefixToMask(prefixLength);
		
		
		String subnetMaskIP = longToIP(subnetMask);
		
		// Performs the and operation with the bits of the baseIpNum and the subnetMask to get the proper number for the network address
		long networkAddress = baseIpNum & subnetMask;
		
		// To get braodcast Id we add the amount of Ips from the amoutn of bits we need to get the amount of IPs we want and subtract one from that value because the 
		// Ip  octet starts at 0 to a number so going from 0 to 64 is 65 Ip addresses but we are subnetting to get 64.
		int hostIps = (int) Math.pow(2,  bitsNeeded);
		long broadcastAddress = networkAddress + hostIps -1;
		
		
		System.out.println("Network Address: " + longToIP(networkAddress));
		System.out.println("Broadcast Address: " + longToIP(broadcastAddress));
		System.out.println("Subnet Mask: " + longToIP(subnetMask));
		
		return true;
		
	}
	
	
	public static boolean subnettingPossible(int prefixLength, int ipsNeeded)
	{
		
		int bitsAvailalbe = 32-prefixLength;
		
		int ipsAvailable = (int) Math.pow(2, bitsAvailalbe) - 2;
		
		if (ipsNeeded > ipsAvailable)
		{
			System.out.println("This department needs " + ipsNeeded + " IPv4 Addresses when there are only " + ipsAvailable + " IP addresses availalbe.");
	
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