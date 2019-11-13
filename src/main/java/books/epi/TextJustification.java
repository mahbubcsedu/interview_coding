package books.epi;

import java.util.ArrayList;
import java.util.List;

public class TextJustification {

	public List<String> justfityText(String[] words, int maxWidth) {
		int numOfWordsCurLine = 0, curLineStart = 0, curLineLen = 0;
		List<String> result = new ArrayList<>();
		
		for(int i=0; i < words.length; i++) {
			numOfWordsCurLine ++;
			
			int lookAheadLineLen = curLineLen + words[i].length() + (numOfWordsCurLine -1);
			
			if(lookAheadLineLen == maxWidth) {
				result.add(joinALineWithSpace(words, curLineStart, i, i-curLineStart));
				curLineStart = i+1;
				numOfWordsCurLine = 0;
				curLineLen = 0;
			} else if (lookAheadLineLen > maxWidth) {
				result.add(joinALineWithSpace(words, curLineStart, i-1, maxWidth-curLineLen));
				curLineStart = i;
				numOfWordsCurLine = 1;
				curLineLen = words[i].length();
			}else {
				//numOfWordsCurLine +=1;
				curLineLen += words[i].length();
				
			}
			
		}

		if(numOfWordsCurLine > 0) {
			StringBuilder line = new StringBuilder(joinALineWithSpace(words, curLineStart, words.length-1, numOfWordsCurLine-1));
			for(int i=0; i < maxWidth - curLineLen - (numOfWordsCurLine-1); i++) {
				line.append(' ');
			}
			result.add(line.toString());
		}
		return result;
	}
	
	public String joinALineWithSpace(String[] words, int start, int end, int numOfSpaces) {
		
		int numOfWords= end-start +1;
		
		StringBuilder curLine = new StringBuilder();
		
		for(int i=start; i < end; ++i) {
		 curLine.append(words[i]);
		 --numOfWords;
		 int curSpace = (int)Math.ceil((double)numOfSpaces/numOfWords);
		 
		 
		 for(int j=0; j<curSpace; j++) {
			 curLine.append(' ');
		 }
		 numOfSpaces -= curSpace;
		}
		
		curLine.append(words[end]);
		
		// don't know why this part is needed
		for(int i=0; i<numOfSpaces; i++) {
			curLine.append(' ');
		}
		return curLine.toString();
	}
	
	public static void testCase1() {
		TextJustification tJ= new TextJustification();
		String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
		int maxWidth = 16;
		
		List<String> result = tJ.justfityText(words, maxWidth);
		result.forEach(t->System.out.println(t));
	}

	public static void main(String args[]) {
		testCase1();
	}
}
