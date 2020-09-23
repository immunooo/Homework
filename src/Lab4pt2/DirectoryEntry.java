package Lab4pt2;
/**
 * DirectoryEntry.java: Base class for data type representing an entry in a phone book
 * 
 * @author Joseph Berlucchi <joeberlucchi2019@gmail.com>
 * @version 1.0
 * 
 */
public class DirectoryEntry {
 
    private String name;
    private String number;
     
    public DirectoryEntry(String name, String number){
        this.name = name;
        this.number = number;
    }
    
    /**
	 * Determines equality of this object and another based on the other object's name value.
	 * 
	 * @param obj The object that is being compared to this object.
	 * 
	 * @return A boolean value of whether the name value of the other object is equivalent. 
	 */
    @Override
    public boolean equals(Object obj){
    	if(obj == null) {
    		return false;
    	}
    	if(!(obj instanceof DirectoryEntry)) {
    		return false;
    	}
    	DirectoryEntry emp = (DirectoryEntry) obj;
    	
        return name.equalsIgnoreCase(emp.getName());
    }
    
    @Override
    public int hashCode(){
        return name.hashCode();
    }
     
    //Encapsulation Methods
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }

}
