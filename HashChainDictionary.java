public class HashChainDictionary implements DictionaryADT {
	private LinearNode[] hashTable;
	private int size;


//hashFuction
	private int hashFunction(String s) {
		int val = (int)s.charAt(s.length()-1);
		for (int i = s.length()-2; i>-1; i--) {
			val = (val* 31 + (int) s.charAt(i)) % size;
		}
		return val;
	}

	/**
	 *
	 * @param size
	 */
	
// hashTable
    public HashChainDictionary(int size) {
    	this.size=size;
    	hashTable=new LinearNode[size];
    	for(int i=0;i<size;i++) {
    		hashTable[i]=null;
    	}
    }


	/**
	 *
	 * @param word - a word that has a string and score associated with it.
	 * @return
	 * @throws DictionaryException
	 */
	
//put method
	public int put(Word word) throws DictionaryException{
		int index=hashFunction(word.getKey());
		if (hashTable[index] == null) {
			LinearNode<Word> node= new LinearNode<Word> (word);
			hashTable[index]=node;
			return 0;
		}
		else if(hashTable[index]!=null) {
			LinearNode<Word> temp= hashTable[index];
			LinearNode<Word> checkedNode= null;
			while(temp !=null) {
				if(temp.getElement().getKey().equals(word.getKey())) {
					throw new DictionaryException(word.getKey());
				}
				else {
					checkedNode=temp;
					temp=temp.getNext();
				}
			}
		LinearNode<Word> node=new LinearNode<Word>(word);
		checkedNode.setNext(node);
		
		}return 1;
		}


	/**
	 *
 	 * @param inputWord
	 * @return
	 */
	public Word get(String inputWord) {
		int index=hashFunction(inputWord);
		if(hashTable[index]!=null) {
			LinearNode<Word> result= hashTable[index];
			return result.getElement();
		}
		else {
			return null;
		}
	}

	/**
	 *
	 * @param inputWord
	 * @return
	 * @throws NoKeyException
	 */
	public Word remove(String inputWord) throws NoKeyException {
		int index=hashFunction(inputWord);
		if(hashTable[index]==null) {
			throw new NoKeyException(inputWord);
		}
		else {
			LinearNode<Word> temp=hashTable[index];
			LinearNode<Word> checkedNode=null;
			while(temp!=null) {
				if(temp.getElement().getKey().equals(inputWord)) {
					checkedNode=temp;
					temp=temp.getNext();
					return checkedNode.getElement();					
				}		
				else {
					checkedNode=temp;
					temp=temp.getNext();
					continue;
				
				}
			}
//			temp.setElement(null);
//			temp=temp.getNext();
//			return removeWord.getElement();
		return null;
		}
	}
	public int size() {
		int i=0;
		while(hashTable[i]!=null) {
			i=i+1;
			
		}
		return i;
	}
	

}
