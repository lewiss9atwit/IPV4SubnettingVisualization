package app;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
public class calculator
{
	
	// This will return the subnet Mask of the department
	public static void subnetCalc(department dep,  List<subnetRange> available)
	{
		
	
		int bitsNeeded = 0;
		while (Math.pow(2, bitsNeeded)-2 < dep.ipsNeeded)
		{
			bitsNeeded++;
		}
		System.out.println("Here are the amount of bits needed: " + bitsNeeded);
		
		
		
		int prefix = 32 - bitsNeeded;
		long subnetMask = prefixToMask(prefix);
		
		// The amount of ips subnetted not taking into account network address and broadcast address needed to aligntheUp
		long blockSize = (long) Math.pow(2,  bitsNeeded);
		
		
		
		
		// Performs the and operation with the bits of the baseIpNum and the subnetMask to get the proper number for the network address
		long networkAddress = -1;
		long broadcastAddress = -1;
		
		for (int i = 0; i < available.size(); i++)
		{
			subnetRange sr = available.get(i);
			long startingIp = alignIpStart(sr.start, blockSize);
			long broadcastIp = startingIp + blockSize-1;
			if (broadcastIp <= sr.end)
			{
				networkAddress = startingIp;
				broadcastAddress = broadcastIp;
				List<subnetRange> leftRanges = new ArrayList<>();
				if (networkAddress > sr.start) 
				{
					leftRanges.add(new subnetRange(sr.start, networkAddress-1));
				}
				
				if(broadcastAddress < sr.end)
				{
					leftRanges.add(new subnetRange(broadcastAddress + 1, sr.end));
				}
				
				available.remove(i);
				available.addAll(leftRanges);
				break;
			}
			
		
			
			
			
			
		}
		
		if (networkAddress == -1)
		{
			
			dep.networkAddress = -1;
			System.out.println("There are not enough IPV4 Address available because " + blockSize + " IP Addresses are not available "
					+ "for the department's " + dep.ipsNeeded + " IP addresses it needs");
			return;
		}
		
		
		
		
		// To get broadcast Id we add the amount of Ips from the amoutn of bits we need to get the amount of IPs we want and subtract one from that value because the 
		// Ip  octet starts at 0 to a number so going from 0 to 64 is 65 Ip addresses but we are subnetting to get 64.
		
		
		
		dep.networkAddress = networkAddress;
		dep.broadcastAddress = broadcastAddress;
		dep.subnetMask = subnetMask;
		dep.prefix = prefix;
		
		
		System.out.println("Network Address: " + longToIP(networkAddress) + "/" + prefix);
		System.out.println("Broadcast Address: " + longToIP(broadcastAddress)+ "/" + prefix);
		System.out.println("Subnet Mask: " + longToIP(subnetMask));
		
		
		
		
	}
	
	
	/*
	public static boolean subnettingPossible(long baseIp, int bitsNeeded)
	{
		
		// Checks if there are enough ips for the department.
		long ipsAvailable = baseEnd - baseIp + 1;
		long blockSize = (long) Math.pow(2, bitsNeeded);
		if (ipsAvailable  <  blockSize)
		{
			System.out.println("There are not enough IPV4 Address available becasue there are " + ipsAvailable + " availalbe while regarding networkAddress and broadcast Address.\nAnd we need "+ blockSize + " addresses.");
			return false;
		}
		
		return true;
		
		
		
	}
	*/
	
	// Ensure that the Ip for example using 128 Ips starts at either x.x.x.128 or x.x.x.0 and not at x.x.x. 64 which is not possible because that is the second part of a split 128 Ips.
	public static long alignIpStart(long currentIp, long BlockSize)
	{
		
		
		return ((currentIp + BlockSize -1) / BlockSize) * BlockSize;
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