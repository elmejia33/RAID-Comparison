package raidComparison;

public class Driver {
	
	static int 		testCases		= 100;
	static double[] raid1Times	= new double[testCases];
	static double[] raid4Times	= new double[testCases];
	static double	raid1Sum 		= 0;
	static double	raid4Sum 		= 0;
	static double	raid1Average	= 0;
	static double	raid4Average	= 0;
	static double 	startTime;
	static double	endTime;
		   String	operation;
		   String	order;
/*********************************************************************/
	public static void drive(String operation, String order){
		RAID raid1 = new RAID(1,4,10000);
		RAID raid4 = new RAID(4,4,10000);
		
/***********RAID WRITE******************/
		if( operation.equals("write") ){
			for(int i=0; i<testCases; i++){
//				raid1
				startTime	  = System.currentTimeMillis()*1000000;
				WriteData.write(raid1, order);
				endTime		  = System.currentTimeMillis()*1000000;
				raid1Times[i] = endTime - startTime;
				
//				raid4
				startTime	   = System.currentTimeMillis()*1000000;
				WriteData.write(raid4, order);
				endTime 	   = System.currentTimeMillis()*1000000;
				raid4Times[i]  = endTime - startTime;	
			}//end for
		}//end if
/***********RAID READ******************/
		if( operation.equals("read") ){
			for(int i=0; i<testCases; i++){
//				raid1
				startTime	  = System.currentTimeMillis()*1000000;
				ReadData.read(raid1, order);
				endTime		  = System.currentTimeMillis()*1000000;
				raid1Times[i] = endTime - startTime;
				
//				raid4
				startTime	   = System.currentTimeMillis()*1000000;
				ReadData.read(raid4, order);
				endTime 	   = System.currentTimeMillis()*1000000;
				raid4Times[i]  = endTime - startTime;	
			}//end for
		}//end if
		
/***********RAID AVERAGE******************/
		for(int i=0; i<testCases; i++){		
			raid1Sum 	+= raid1Times[i];
			raid4Sum 	+= raid4Times[i];
		}
		raid1Average = raid1Sum/testCases;
		raid4Average = raid4Sum/testCases;
		System.out.println("Raid-1 " +order+ " " +operation+ " average "
							+raid1Average+ " nanoseconds");
		System.out.println("Raid-4 " +order+ " " +operation+ " average "
							+raid4Average+ " nanoseconds");
		
	}
/*********************************************************************/
	public static void main(String[] args){
		drive("write", "rand");
		clearSums();
		drive("read",  "rand");
		clearSums();			
		drive("write", "seq");
		clearSums();		
		drive("read",  "seq");
		clearSums();
	}
	public static void clearSums(){
		raid1Sum = 0;
		raid4Sum = 0;
	}
}
