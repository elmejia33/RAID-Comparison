package raidComparison;
/***DONE***///
public class WriteData {
	
	static int randData;
	static int randI;
	static int randJ;
	static int rowLength;
	static int numRows;
	static int rowSum;
/********************************************************************/
	public static void write(RAID raid, String order){
//		define size for raid 1
		if(raid.raidType == 1){
			rowLength = raid.numDisks/2;
			numRows   = raid.mainDisk.length;
		}
		
//		define size for raid 4
		if(raid.raidType == 4){
			rowLength = raid.numDisks-1;
			numRows   = raid.mainDisk.length;
		}
		
//		write data to disk based on String 'order'
		if( order.equals("seq") ){
			seqWrite(raid);
		}
		if( order.equals("rand") ){
			randWrite(raid);
		}
//		do appropriate backup
		bkupData(raid);
	}
/********************************************************************/
	public static void seqWrite(RAID raid){
//		double array to go through entire disk
		/***only write first column to simulate parallelism***/	
		for( int i=0; i<numRows; i++){
//			for( int j=0; j<rowLength; j++){
				generateRandomData(raid);
				raid.mainDisk[i][0] = randData ;
//			}	
		}
	}
/********************************************************************/
	public static void randWrite(RAID raid){
//		double array to go through entire disk
		for( int i=0; i<numRows; i++){
			for( int j=0; j<rowLength; j++){
				generateRandomData	  (raid);
				generateRandomLocation(raid);
				raid.mainDisk[randI][randJ] = randData ;
			}	
		}
	}
/********************************************************************/
	public static void generateRandomLocation(RAID raid){
//		/***STACK OVERFLOW***/
//		set random location
		randI	= (int) (Math.random()*numRows);
		randJ	= (int) (Math.random()*rowLength);
//		if not empty, set again		
//		if(raid.mainDisk[randI][randJ] != 0){
//			generateRandomLocation(raid);
//		}
	}
/********************************************************************/
	public static void generateRandomData(RAID raid){
		randData = (int) (Math.random()*100)+1;
	}
/********************************************************************/
	public static void bkupData(RAID raid){
//		if RAID-1, mirror data onto bkupDisk
		if(raid.raidType == 1){			
			for( int i=0; i<numRows; i++){
				for( int j=0; j<rowLength; j++){
					raid.bkupDisk[i][j] = raid.mainDisk[i][j];
				}
			}//end for
		}//end if
		
//		if RAID-4, use sum of data on all drives for parity drive
		if(raid.raidType == 4){
			for( int i=0; i<numRows; i++){
				rowSum = 0;
				for( int j=0; j<rowLength; j++){
					rowSum += raid.mainDisk[i][j];
				}
				raid.bkupDisk[i][0] = rowSum;
			}//end for			
		}//end if
	}
/********************************************************************/
	
}
