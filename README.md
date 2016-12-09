# RAID-Comparison
RAID-1 &amp; RAID-4 Comparison

Driver class main method runs through program four times for each RAID:
random writes,
random reads,
sequential writes,
sequential reads.

drive method constructs a RAID-1 and RAID-4 each with 4 disks and a disk size of 10000

RAID(RAIDTYPE, NUMBEROFDISKS, DISKSIZE)
RAIDTYPE        - either 1 or 4
NUMBER OF DISKS - for RAID-1 must be an even number. for RAID-4 must be at least 3.
DISKSIZE        - integer


