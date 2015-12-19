
/*
I didnt know how the hashcode() method worked so I looked online and found the fundamental idea of how
to use it as well as how to make a general hashmap. This was on tekmarathon.com. They have a good skeleton
that I used to get an idea of how I need to approach this. I then started getting negative hash codes whilst 
creating the hashmap. To solve this, I took the absolute value. 
  
 Coded by Jordan Burton, 10/27/2015
 
 */


public class Hash_Map {
	final int maxSize;
	private entry[] items;
	private int numEntries = 0;


	public class entry{
		final String key;
		Object value;
		entry next;

		entry(String k, Object v){
			key = k;
			value = v;
		}

		public Object get(){
			return value;
		}

		public void set(Object v){
			this.value = v;
		}

		public String getKey(){
			return key;
		}

	}
	Hash_Map(int size){
		maxSize = size;
		items = new entry[maxSize];
	}

	public boolean set(String key, Object value){
		int hashNum = Math.abs(key.hashCode() % maxSize);
		//int hash = key.hashCode();
		entry toSet = items[hashNum];
		if(toSet != null){
			if(toSet.key.equals(key)){
				toSet.value = value;
				return true;
			}
			else{
				while(toSet.next != null){
					toSet = toSet.next;
					if(toSet.key.equals(key)){
						toSet.value = value;
						return true;
					}
				}
				
				if(numEntries >= maxSize){
					return false;
				}
				numEntries += 1;
				entry newEntry = new entry(key, value);
				toSet.next = newEntry;
				return true;


			}
		}
		else{
			if(numEntries >= maxSize){
				return false;
			}
			entry newEntry = new entry(key, value);
			items[hashNum] = newEntry;
			numEntries += 1;
			return true;
		}
	}

	public Object get(String key){
		int hashNum = Math.abs(key.hashCode() % maxSize);
		entry toGet = items[hashNum];
		if(toGet.value == null){
			return null;
		}
		while(toGet != null){
			if(toGet.value == null){
				return null;
			}
			else if(toGet.key.equals(key)){
				return toGet.value;
			}
			toGet = toGet.next;
		}
		return null;
	}

	public Object delete(String key){
		int hashNum = Math.abs(key.hashCode() % maxSize);
		entry toDelete = items[hashNum];
		if(toDelete.key.equals(key)){
			Object oldValue = toDelete.value;
			toDelete.value = null;
			return oldValue;
		}
		while(toDelete != null){
			if(toDelete.key.equals(key)){
				Object oldValue = toDelete.value;
				toDelete.value = null;
				return oldValue;
			}
			else if(toDelete.key.equals(key) && toDelete.value == null){
				return null;
			}
			toDelete = toDelete.next;
		}
		return null;
	}

	public float load(){
		float itemCount = 0;
		for(entry x : items){
			if(x != null){
				while(x != null){
					if (x.key != null && x.value != null){
						itemCount += 1;
					}
					x = x.next;
				}
				
			}
		}
		return (float)(itemCount/maxSize);
	}
	
	
	/*(Added here as well just in testing class fails)
	 * Testing all of the cases. 
	 *  for get and set, prints true if it was added, false if it cant add/if it exceeds the max # of entries.
	 * 
	 * 
	 */
	/*
	public static void main(String[] args){
		Hash_Map ng = new Hash_Map(4);
		String[] object1 = new String[15];
		int[] object2 = new int[12];
		System.out.println(ng.maxSize);
		ng.set("test int", 5);
		ng.set("test int array", object2);
		System.out.println("int array test: " + ng.get("test int array"));
		System.out.println("Testing value setting for char: " + ng.set("test char", 'w'));
		System.out.println("Character test: " + ng.get("test char"));
		System.out.println("Testing value setting for String[]: " + ng.set("test String array", object1));
		System.out.println("Testing load before deleting of an item: " + ng.load());
		System.out.println("deletion test: " + ng.delete("test int"));
		System.out.println("confirm value is deleted: " + ng.get("test int"));
		System.out.println("Testing lead after deleting an item: " + ng.load());
		System.out.println("Test for adding more items than allowed: " + ng.set("test over capacity", 20));
		System.out.println("Confirm value and key not added: " + ng.get("test over capacity"));
		/*
		 * overall, works with all types, and each key is capable of having
		 * different value types. In this map, I have types char, int, String[] and int[] with no issues.
		 * pre deletion, the map is full with map size of 4 and 4 items, so items / maxSize yields 1
		 * After deletion, the map size is still 4 but has 3 items. 3/4 is .75. This confirms that as well.
		 
			
	}
	*/
	
}

