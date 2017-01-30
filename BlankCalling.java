/**
 * 
 */
package calling;

/**
 * @author mattafire
 *
 *  This Class will be used to generate a form to input callings in
 *
 */
public class BlankCalling {
	int numEQ = 2;
	int numRS = 2;
	int numHE = 12;
	int numIDX = -1;
	int numWM = -1;
	int totalNumCallings = 200;
	private String[] masterList = new String[totalNumCallings+10];
	private int subTotal = 0;
	
	//calling lists
	private final static String[] HEADER = {"Calling,Name,Called,Sustained,SetApart,Entered Online"};
	private final static String[] BISHOPRIC = {"Executive Secretary,,,,,","Executive Secretary Asst,,,,,","Assistant Ward Clerk,,,,,","Assistant Ward Clerk,,,,,"};
	private final static String[] PRES = {"President,,,,,","First Counselor,,,,,","Second Counselor,,,,,","Secretary,,,,,"};
	private final static String[] EQ = {"Home Teaching District Supervisor,,,,,","Home Teaching District Supervisor,,,,,","Elders Quorum Instructor,,,,,","Elders Quorum Instructor,,,,,","Elders Quorum Instructor,,,,,","Sacrament Coordinator,,,,,"};
	private final static String[] SUNDAYSCHOOL = {"Gospel Doctrine Teacher,,,,,","Gospel Doctrine Teacher,,,,,","Gospel Doctrine Teacher,,,,,","Gospel Doctrine Teacher,,,,,","Gospel Doctrine Subsitute,,,,,","Gospel Doctrine Subsitute,,,,,"};
	private final static String[] RS = {"Pianist,,,,,","Chorister,,,,,","Compassionate Service Leader,,,,,","Compassionate Service Leader,,,,,","Compassionate Service Leader,,,,,","Weekly Activity Leader,,,,,","Weekly Activity Specialist,,,,,","Weekly Activity Specialist,,,,,","Visiting Teaching Leader,,,,,","Visiting Teaching Supervisor,,,,,","Visiting Teching Supervisor,,,,,","Teacher,,,,,","Teacher,,,,,","Teacher,,,,,"};
	private final static String[] WM = {"Ward Mission Leader,,,,,","Ward Mission Leader Asst.,,,,,"};
	private final static String[] FH = {"Family History Leader,,,,,","Family History Consultant,,,,,","Family History Consultant,,,,,"};
	private final static String[] MUSIC = {"Music Chairman,,,,,","Music Director,,,,,","Ward Pianist,,,,,,","Priesthood Pianist,,,,,","Priesthood Chorister,,,,,","Choir Director,,,,,","Choir Accompanist,,,,,"};
	private final static String[] FHE = {"HE Cordinator,,,,,","HE Asst Cordinator,,,,,"};
	private final static String[] OTHER = {"Ward History Facebook Specialist,,,,,","Ward History Specialist,,,,,","Ward Photographer,,,,,","Ward Bulletin Specialist,,,,,","Ward Bullletin Asst Specialist,,,,"};
	private final static String[] EXTRA = {"Greeter,,,,,","Ward Missionary,,,,,","Indexer,,,,,"};
	
	//constructors
	public BlankCalling(){
		filePrep();
	}
	public BlankCalling(int EQ, int RS, int HE, int calling){
		this.numEQ = EQ;
		this.numRS = RS;
		this.numHE = HE;
		this.totalNumCallings = calling + 25;
		masterListChanger();
		filePrep();
	}
	public BlankCalling(int EQ, int RS, int HE, int IDX, int WMs, int calling){
		this.numEQ = EQ;
		this.numRS = RS;
		this.numHE = HE;
		this.numIDX = IDX;
		this.numWM = WMs;
		this.totalNumCallings = calling + 20;
		masterListChanger();
		filePrep();
	}
	
	//change values of variables
	public void changeEQ(int in){
		this.numEQ = in;
	}
	public void changeRS(int in){
		this.numRS = in;
	}
	public void changeHE(int in){
		this.numHE = in;
	}
	public void changeIDX(int in){
		this.numIDX = in;
	}
	public void changeWM(int in){
		this.numWM = in;
	}
	public void changeTotalNumCall(int in){
		this.totalNumCallings = in + 20;
	}
	
	//return variables when called
	public int getNumEQ(){
		return this.numEQ;
	}
	public int getNumRS(){
		return this.numEQ;
	}
	public int getNumHE(){
		return this.numHE;
	}
	public int getNumIDX(){
		return this.numIDX;
	}
	public int getNumWM(){
		return this.numWM;
	}
	public int getTotalNumCall(){
		return this.totalNumCallings;
	}
	public String[] getMasterFile(){
		return this.masterList;
	}
	public int getEQNum(){
		return EQ.length + 4;
	}
	public int getRSNum(){
		return RS.length + 4;
	}
	public int getBishopricNum(){
		return BISHOPRIC.length;
	}
	public int getAllOtherNum(){
		int total = 0;
		total += BISHOPRIC.length + SUNDAYSCHOOL.length + PRES.length + FHE.length;
		total += WM.length + FH.length + MUSIC.length + OTHER.length + 3;
		return total;
	}
	public int getSubTotal(){
		return subTotal;
	}
	
	private void masterListChanger(){
		String[] tmp = new String[this.totalNumCallings];
		this.masterList = tmp;
	}
	
	//assemble the dynamic blank calling sheet
	void filePrep(){
		for(int i = 0; i < this.masterList.length; i ++){
			this.masterList[i] = null;
		}
		int count = 0;
		masterList[count] = HEADER[0];
		count++;
		masterList[count] = "Bishopric,,,,,";
		count++;
		//bishopric
		for(int i = 0; i < BISHOPRIC.length; i++,count++)
			masterList[count] = BISHOPRIC[i];
		//sunday school		
		masterList[count] = "Sunday School,,,,,";
		count++;
		for(int i = 0; i < PRES.length; i++,count++)
			masterList[count] = PRES[i];
		for(int i = 0; i < SUNDAYSCHOOL.length; i++,count++)
			masterList[count] = SUNDAYSCHOOL[i];
		//elders quorums
		for(int c = 0; c < numEQ; c++){
			masterList[count] = "Elders Quorum " +(c+1)+ ",,,,,";
			count++;
			for(int i = 0; i < PRES.length; i++,count++)
				masterList[count] = PRES[i];
			for(int i = 0; i < EQ.length; i++,count++)
				masterList[count] = EQ[i];
		}
		//Relief societies
		for(int c = 0; c < numRS; c++){
			masterList[count] = "Releif Society " +(c+1)+ ",,,,,";
			count++;
			for(int i = 0; i < PRES.length; i++,count++)
				masterList[count] = PRES[i];
			for(int i = 0; i < RS.length; i++,count++)
				masterList[count] = RS[i];
		}
		//Other Callings
		masterList[count] = "Other Callings,,,,,";
		count++;
		//Ward Mission
		for(int i = 0; i < WM.length; i++,count++)
			masterList[count] = WM[i];
		//music
		for(int i = 0; i < MUSIC.length; i++,count++)
			masterList[count] = MUSIC[i];
		//family history
		for(int i = 0; i < FH.length; i++,count++)
			masterList[count] = FH[i];
		//other stuff
		for(int i = 0; i < OTHER.length; i++,count++)
			masterList[count] = OTHER[i];
		//greeters
		for(int i = 0; i < 3; i++,count++)
			masterList[count] = EXTRA[0];
		//FHE groups
		masterList[count] = "Home Evening Groups,,,,,";
		count++;
		for(int i = 0; i < FHE.length; i++,count++)
			masterList[count] = FHE[i];
		for(int c = 0; c < numHE; c++){
			masterList[count] = "HE Leader Group " +(c+1)+ ",,,,,";
			count++;
			masterList[count] = "HE Asst Leader Group " +(c+1)+ ",,,,,";
			count++;
		}
		//Extra callings
		masterList[count] = "Extra Callings,,,,,";
		count++;
		for(int i = 0; i < 3; i++, count++){
			masterList[count] = EXTRA[0];
		}
		subTotal = count - 6;//-6 because of section headers
		//not to exceed total size of array
		if((totalNumCallings >= count + numWM + numIDX)){
		//as long as there is a value
		if(numWM >= 0 || numIDX >= 0){
		//Ward Missionaries
		if(numWM >= 0){
			for(int i = 0; i < numWM; i++, count++){
				masterList[count] = EXTRA[1];
			}
		}
		//Indexers
		if(numIDX >= 0){
			for(int i = 0; i < numIDX; i++, count++){
				masterList[count] = EXTRA[2];
			}
		}
		}
		else{
			int half = (totalNumCallings - count)/2;
			//ward missionaries
			for(int i = 0; i < half; i++, count++){
				masterList[count] = EXTRA[1];
			}
			//Indexers
			for(int i = 0; i < half; i++, count++){
				masterList[count] = EXTRA[2];
			}
		}
		}
	}	
}
