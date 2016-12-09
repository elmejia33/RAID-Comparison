package raidComparison;


public class RAID {
	
	int			numDisks;
	int			raidType;
	int [][] 	mainDisk;
	int [][]	bkupDisk;
/***************************************************************************/
	public RAID(int raidType, int numDisks, int diskSize){
//		test cases
		if( (raidType != 1) && (raidType   != 4) ){
			System.out.println("Invalid RAID type");
		}
		if( (raidType == 1) && (numDisks%2 == 1) ){
			System.out.println("Invalid number of disks for raid type");
		}
		if( (raidType  == 4) && (numDisks < 3) ){
			System.out.println("Invalid number of disks for raid type");
		}
		else{
//			passed test cases, create arrays based on raidType
			this.raidType = raidType;
			this.numDisks = numDisks;
			if(raidType == 1){
				mainDisk = new int [diskSize][numDisks/2];
				bkupDisk = new int [diskSize][numDisks/2];
			}
			if(raidType == 4){
				mainDisk = new int [diskSize][numDisks-1];
				bkupDisk = new int [diskSize][1];
			}	
		}//end if
	}
/***************************************************************************/
}
