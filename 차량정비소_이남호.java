import java.io.FileInputStream;
import java.util.Scanner;

public class Solution {
	static final boolean isForSubmission = true;
	
	
	public static void log(String s) {
		if(!isForSubmission)System.out.println(s);
	}
	
	public static void main(String[] args) throws Exception {
		
		if(!isForSubmission) System.setIn(new FileInputStream("input.txt"));
		
		Scanner sc = new Scanner(System.in);
		
		int numCases = sc.nextInt();
		
		
		
		for(int i = 0 ;i<numCases;i++) {
			
			int numRg = sc.nextInt();
			log("got " + numRg);
			int numRp = sc.nextInt();
			log("got " + numRp);
			int numClient = sc.nextInt();
			log("got " + numClient);
			int theRg = sc.nextInt();
			log("got " + theRg);
			int theRp = sc.nextInt();
			log("got " + theRp);
			
			
			Rg[] rgs = new Rg[numRg];
			for(int j = 0 ; j < numRg; j++) {
				rgs[j] = new Rg();
				rgs[j].time = sc.nextInt();
			}
			
			Rp[] rps = new Rp[numRp];
			for(int j = 0 ; j < numRp; j++) {
				rps[j] = new Rp();
				rps[j].time = sc.nextInt();
			}
		
			Client clientRoot = new Client();
			Client client = clientRoot;
			int clientCount = 0;
			for(int j = 0 ; j < numClient ; j++) {
				client.next = new Client();
				client = client.next;
				client.time = sc.nextInt();
				client.num = clientCount+1;
				log("got client with time " + client.time + " num " + client.num); 
				clientCount++;
			}
		
			
			int curtime = 0;
			int theSum = 0;
			
			int numInRg =0;
			int numInRp =0;
			
			Client curClient = clientRoot.next;
			Client curRgClient = null;
			Client lastRgClient =null;
			Client curRpClient = null;
			Client lastRpClient = null;
			
			while(true) {
				log("tic");
				
				while(curClient!=null && curtime >= curClient.time) {
						log("arrived");
						Client nextClient = curClient.next;
						if(curRgClient == null) {
							curRgClient = curClient;
							lastRgClient = curClient;
							curRgClient.next = null;
						}else {
							lastRgClient.next = curClient;
							lastRgClient = lastRgClient.next;
							lastRgClient.next = null;
						}
						curClient = nextClient;
				}
						

				int j = 0;

				Client[] curRgEnded = new Client[numRg];
				int curRgEndedCount = 0;
				
				for(j = 0;j<numRg;j++) {
					if(rgs[j].client!=null&&curtime == rgs[j].endtime) {
						numInRg--;
						curRgEnded[curRgEndedCount] = rgs[j].client; 
						curRgEndedCount++;		
						rgs[j].client = null;
					}
				}
			
				j=0;
				while(true) {
					if(curRgClient==null) break;
					
					if(rgs[j].client == null) {
						log("empty rg slot putting client "+curRgClient.num);
						numInRg++;
						rgs[j].client = curRgClient;
						rgs[j].endtime = curtime+rgs[j].time;
						curRgClient.regist = j;
						curRgClient = curRgClient.next;
					}	
					j++;
					if(j>=numRg)break;
					
				}
				

				for(j=0;j<numRg;j++) {
					for(int k = 0 ; k < curRgEndedCount ;k++) {
						if(curRgEnded[k].regist == j) {
							if(curRpClient==null) {
								curRpClient = curRgEnded[k];
								lastRpClient = curRgEnded[k];
								lastRpClient.next =null;
							}else {
								lastRpClient.next = curRgEnded[k];
								lastRpClient = lastRpClient.next;
								lastRpClient.next = null;
							}
						}
					}
				}
			
				for(j = 0;j<numRp;j++) {
					if(rps[j].client != null && curtime == rps[j].endtime) {
						numInRp--;
						rps[j].client = null;
					}
				}
			
			
				j=0;
				while(true) {
					if(curRpClient==null) break;
					
					if(rps[j].client == null) {
						log("empty rp slot putting client");
						numInRp++;
						rps[j].client = curRpClient;
						rps[j].endtime = curtime+rps[j].time;
						curRpClient.repair = j;
						
						Client finalized = curRpClient;
						if(finalized.regist == (theRg-1) && finalized.repair == (theRp-1)) {
							theSum+=finalized.num;
						}
						
						curRpClient = curRpClient.next;
					}else {
						j++;
						if(j>=numRp)break;
					}
					
				}
				
				if(numInRg ==0 && numInRp ==0 && curClient == null && curRpClient == null && curRgClient ==null) {
					break;
				}

				curtime++;
			}
			if(theSum == 0) theSum = -1;
			System.out.println("#"+(i+1)+" "+theSum);
		}
		
		
		
		

		
	}
	
	public static class Rg{
		int time;
		int endtime;
		Client client;
		public Rg() {
		
		
		}
	}
	public static class Rp{
		int time;
		int endtime;
		Client client;
		public Rp() {
		
		
		}
	}
	
	public static class Client{
		int time;
		int regist;//접수창구
		int repair;
		int num;
		
		Client next;
		
		public Client() {
			regist = -1;
			repair = -1;
			next = null;
		}
	}

}
