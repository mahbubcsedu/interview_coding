package company.walmart;

public class AlienLanguageTranslator implements LanguageTranslator{

	@Override
    public String fromDefaultLanguage(String languageTokens)
    {
        //String responseStr="";
        char[] tokens=languageTokens.toCharArray();
        //char[] response=new char[tokens.length];
        StringBuilder sb=new StringBuilder();
        
        
        for(char ch:tokens){
        	if(ch>='A' && ch<='Z') {
            int c=(int)ch+3;
            
            sb.append((char)c);
        	}else if(ch>='a' && ch<='z') {
        		int c=(int)(ch+3);
                sb.append((char)c);
        	}
            
            
        }
        
        return sb.toString();
    }
    
    @Override
    public String toDefaultLanguage(String languageTokens)
    {
    	char[] tokens=languageTokens.toCharArray();
        //char[] response=new char[tokens.length];
        StringBuilder sb=new StringBuilder();
        
        
        for(char ch:tokens){
        	if(ch>='A' && ch<='Z') {
            int c=(int)ch-3;
            
            sb.append((char)c);
        	}else if(ch>='a' && ch<='z') {
        		int c=(int)(ch-3);
                sb.append((char)c);
        	}
            
            
        }
        
        return sb.toString();
    }
    
    public static void main(String args[]) {
    	AlienLanguageTranslator t=new AlienLanguageTranslator();
    	System.out.println(t.toDefaultLanguage("Mdyd"));
    	System.out.println(t.fromDefaultLanguage("Java"));
    }

}
