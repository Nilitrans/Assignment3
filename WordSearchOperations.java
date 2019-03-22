

import java.io.*;
import java.util.ArrayList;
public class WordSearchOperations{
	private HashChainDictionary dict;
	private HashChainDictionary foundWords;
	BufferedReader in ;  
	BufferedReader in2;
	private String [][] letters;
	private int maxScore;
	private int gridSize;

	/**
	 *
 	 * @param fileName
	 * @param wordTextFile
	 * @throws IOException
	 * @throws DictionaryException
	 */
public WordSearchOperations(String fileName, String wordTextFile) throws IOException, DictionaryException{
	dict=new HashChainDictionary(74051);
	File f1=new File(wordTextFile);
	FileReader f1Reading = new FileReader(f1);
	
	in=new BufferedReader(f1Reading);
	while (in.readLine()!=null) {
		String read=in.readLine();
		String[]readed=read.split(",");
		int score =Integer.parseInt(readed[1]);
		
		Word word=new Word(readed[0],score);
		dict.put(word);
		
		}
	
	in.close();
	f1Reading.close();
/////////////////////////////////////////////////
	/**read file and create a gridsize*gridsize table
	 *
	 */

	File f2=new File(fileName);
	FileReader f2Reading = new FileReader(f2);
	
	in2=new BufferedReader(f2Reading);
	
	String firstS=in2.readLine();  
	
	
	// convert the first string to int as gridSize
	gridSize=Integer.parseInt(firstS);
	letters=new String[gridSize][gridSize];

     
///////////////////////////////////////////////////////////////
// Third Try:
	/**
	 *
	 */

	String 	restLines;
	/**
	 * get the gridSize and add other
	 */


	char []restLinesC;
	for(int i=0;i<gridSize;i++) {
			restLines=in2.readLine();
			// study note: toChartArray:convert string to chartArray
			restLinesC=restLines.toCharArray();
			//Question: why this doesn't work? restLinesA=restLines.split("\\s+");
			for(int j=0;j<restLines.length();j++) {
				String add=Character.toString(restLinesC[j]);
				letters[i][j]=add;


			}
			}

/**
 * compute the maxScore
 */


///////////////////////////////////////////////////////////////////////	
	 String LWithoutGrid;
	 
		
		while((LWithoutGrid=in2.readLine())!=null) {
			if((dict.get(LWithoutGrid))!=null) {
				maxScore=maxScore + (dict.get(LWithoutGrid)).getValue();
			}
		
		}
	this.foundWords=new HashChainDictionary(100);
	in2.close();
	f2Reading.close();
	

	
}	

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

	/**
	 * checkWords
	 * @param string
	 * @return
	 */

ArrayList<Word> checkWords(String string){
		
		int box=4;
		int curr_Posi=0;
		ArrayList<Word> ListofWord=new ArrayList<Word>();
		for(int i=curr_Posi;i<=string.length()-box;i++) {
			for(int j=box;j+i<=string.length();j++) {
				String wordCheckin=string.substring(i,j+i);

				if((dict.get(wordCheckin))!=null) {
					
					ListofWord.add(dict.get(wordCheckin));
				
				}
				
			}
		}
		return ListofWord;
	}

	/**
	 * findWords in line
 	 * @param line
	 * @return
	 */
////////////////////////////////////////////////////////	
	ArrayList<Word> findWords(String line){
		String[] Sline=line.split("\\s+");
		ArrayList<Word> checking=new ArrayList<Word>();
		ArrayList<Word> resultList=new ArrayList<Word>();
		for(int i=0;i<Sline.length;i++) {
			checking=checkWords(Sline[i]);
			for(int j=0;j<checking.size();j++) {
				resultList.add(checking.get(j));
			}
			
			
			
		}
		
		
		return resultList;
	}

	/**
	 * update the new found
	 * @param words
	 * @return
	 * @throws DictionaryException
	 */

//这里错了 竖着找不出来
/////////////////////////////////////////////////////	
	ArrayList<Word> updateWordList(ArrayList<Word> words) throws DictionaryException{


		ArrayList<Word> updateResult=new ArrayList<Word>();

		for(int i=0;i<words.size();i++) {

			if(foundWords.get(   (words.get(i).getKey() )   ) ==null       ) {

				updateResult.add(words.get(i));
				foundWords.put(words.get(i));
			}
		}
	return updateResult;

	}

////////////////////////////////////////////////////////
	public int getNumWordsFound() {
		return foundWords.size();
	}
	public int getSize() {
		return gridSize;
	}
	public int getMaxScore() {
		return maxScore;
	}
	public String getLetter(int i, int j) {
		return this.letters[i][j];
	}



}



/////////////////////////////////////////////////////////////////////////////////////
