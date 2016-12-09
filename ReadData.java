package raidComparison;

public class ReadData {
	
	static int		rowLength;
	static int		numRows;
	static int[][]	data;
	static int		randJ;
	static int		randI;
	
/********************************************************************/
	public static int[][] read(RAID raid, String order){
		if(raid.raidType == 1){
			rowLength = raid.numDisks/2;
			numRows   = raid.mainDisk.length;
		}
		
		if(raid.raidType == 4){
			rowLength = raid.numDisks-1;
			numRows   = raid.mainDisk.length;
		}
		data = new int[numRows][rowLength];
//		read data from disk based on String 'order'
		if( order.equals("seq") ){
			return seqRead(raid);
		}
		
		if( order.equals("rand") ){
			return randRead(raid);
		}
		return new int[0][0];	
	}
/********************************************************************/
	public static int[][] seqRead(RAID raid){
//		double array to go through entire disk
		/***only read first column to simulate parallelism***/	
		for( int i=0; i<numRows; i++){
//			for( int j=0; j<rowLength; j++){
				data[i][0] = getDataAt(raid, i, 0);
//			}	
		}//end for
		return data;

	}
/********************************************************************/
	public static int[][] randRead(RAID raid){
//		double array to go through entire disk
		for( int i=0; i<numRows; i++){
			for( int j=0; j<rowLength; j++){
				generateRandomLocation(raid);
				data[i][j] = getDataAt(raid, randI,randJ);
			}	
		}//end for	
		return data;
	}
/********************************************************************/
	public static int getDataAt(RAID raid, int i, int j){
		return raid.mainDisk[i][j];
	}
/********************************************************************/
	public static void generateRandomLocation(RAID raid){
//		set random location
		randI	= (int) (Math.random()*numRows);
		randJ	= (int) (Math.random()*rowLength);
	}
/********************************************************************/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
