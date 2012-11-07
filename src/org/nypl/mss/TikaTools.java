/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nypl.mss;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.tokenize.SimpleTokenizer;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.util.Span;
import org.apache.tika.Tika;
import org.apache.tika.language.ProfilingWriter;

/**
 *
 * @author dm
 */
public class TikaTools {
    private final Tika tika;
    private FileModel fm;
    private NameFinderME finder;
    private File file;
    
    TikaTools() throws IOException{
        tika = new Tika();
        

    }
    
    public void init(File file, FileModel fm) throws IOException{
        this.file = file;
        this.fm = fm;
    }
    
    public String getLanguage() throws IOException{
        BufferedReader br = new BufferedReader(tika.parse(new FileInputStream(file)));
        ProfilingWriter pw = new ProfilingWriter();
        String line;
        while((line = br.readLine()) != null){
            pw.append(line);
        }
        if(pw.getProfile().getCount() > 0)
            return pw.getLanguage().getLanguage();
        else
            return "unkown";
    }
    
    public void getPersons() throws IOException{
        finder = new NameFinderME(new TokenNameFinderModel(new FileInputStream(new File("models/en-ner-person.bin"))));
        Tokenizer tokenizer = SimpleTokenizer.INSTANCE;
        String line;
        BufferedReader br = new BufferedReader(tika.parse(new FileInputStream(file)));
        while((line = br.readLine()) != null){
            Span[] tokensSpans = tokenizer.tokenizePos(line);
            String[] tokens = Span.spansToStrings(tokensSpans, line);
            Span[] names = finder.find(tokens);
            for(int i = 0; i < names.length; i++){
                Span startSpan = tokensSpans[names[i].getStart()];
                int nameStart = startSpan.getStart();
                
                Span endSpan = tokensSpans[names[i].getEnd() - 1];
                int nameEnd = endSpan.getEnd();
                
                String name = line.substring(nameStart, nameEnd);
                if(!fm.hasName(name)){
                    fm.addName(name);
                }
            }
        }
    }
    
    
    public void getLocations() throws IOException{
        finder = new NameFinderME(new TokenNameFinderModel(new FileInputStream(new File("models/en-ner-location.bin"))));
        Tokenizer tokenizer = SimpleTokenizer.INSTANCE;
        String line;
        BufferedReader br = new BufferedReader(tika.parse(new FileInputStream(file)));
        while((line = br.readLine()) != null){
            Span[] tokensSpans = tokenizer.tokenizePos(line);
            String[] tokens = Span.spansToStrings(tokensSpans, line);
            Span[] names = finder.find(tokens);
            for(int i = 0; i < names.length; i++){
                Span startSpan = tokensSpans[names[i].getStart()];
                int nameStart = startSpan.getStart();
                
                Span endSpan = tokensSpans[names[i].getEnd() - 1];
                int nameEnd = endSpan.getEnd();
                
                String name = line.substring(nameStart, nameEnd);
                if(!fm.hasLoc(name)){
                    fm.addLocation(name);
                }
            }
        }
    }
    
    public void getOrganizations() throws IOException{
        finder = new NameFinderME(new TokenNameFinderModel(new FileInputStream(new File("models/en-ner-organization.bin"))));
        Tokenizer tokenizer = SimpleTokenizer.INSTANCE;
        String line;
        BufferedReader br = new BufferedReader(tika.parse(new FileInputStream(file)));
        while((line = br.readLine()) != null){
            Span[] tokensSpans = tokenizer.tokenizePos(line);
            String[] tokens = Span.spansToStrings(tokensSpans, line);
            Span[] names = finder.find(tokens);
            for(int i = 0; i < names.length; i++){
                Span startSpan = tokensSpans[names[i].getStart()];
                int nameStart = startSpan.getStart();
                
                Span endSpan = tokensSpans[names[i].getEnd() - 1];
                int nameEnd = endSpan.getEnd();
                
                String name = line.substring(nameStart, nameEnd);
                if(!fm.hasOrg(name)){
                    fm.addOrg(name);
                }
            }
        }
    }
    
    public void getDates() throws IOException{
        finder = new NameFinderME(new TokenNameFinderModel(new FileInputStream(new File("models/en-ner-date.bin"))));
        Tokenizer tokenizer = SimpleTokenizer.INSTANCE;
        String line;
        BufferedReader br = new BufferedReader(tika.parse(new FileInputStream(file)));
        while((line = br.readLine()) != null){
            Span[] tokensSpans = tokenizer.tokenizePos(line);
            String[] tokens = Span.spansToStrings(tokensSpans, line);
            Span[] names = finder.find(tokens);
            for(int i = 0; i < names.length; i++){
                Span startSpan = tokensSpans[names[i].getStart()];
                int nameStart = startSpan.getStart();
                
                Span endSpan = tokensSpans[names[i].getEnd() - 1];
                int nameEnd = endSpan.getEnd();
                
                String name = line.substring(nameStart, nameEnd);
                if(!fm.hasDate(name)){
                    fm.addDate(name);
                }
            }
        }
    }
}
