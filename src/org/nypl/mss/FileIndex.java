package org.nypl.mss;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileIndex {
    private final File csv;
    
    FileIndex(String csv) throws FileNotFoundException, IOException{
        this.csv = new File(csv);
        verifyExists();
        parseObjects();
    }
    
    private void verifyExists(){
        if(!csv.exists()){
            System.out.println("FTK CSV file does not exist");
            System.exit(0);
        }
    }
    
    private void parseObjects() throws FileNotFoundException, IOException{
        BufferedReader br = new BufferedReader(new FileReader(csv));
        String line;
        
        int count = 0;
        while((line = br.readLine()) != null){

            if(count > 0){
                FileModel fm = new FileModel();
                String[] tokensa = line.split(",");
                List<String> tokens = new ArrayList(Arrays.asList(tokensa));
                int i = 0;
                for(String token: tokens){
                    token = token.substring(1, token.length() - 1);                    
                    switch(i){
                        case 0:
                            fm.setFid(token);
                            break;
                        case 1:
                            fm.setFileName(token);
                            break;
                        case 2:
                            processPath(token, fm);
                            break;
                        case 4:
                            fm.setFileType(token);
                            break;
                        case 5:
                            processModDate(token, fm);
                            break;
                        case 7:
                            fm.setFileSize(Long.parseLong(token));
                            break;
                    }
                    i++;
                }
                fm.setUid();
                System.out.println(fm);
            }
            count++;
        }
    }
    
    private void processPath(String token, FileModel fm) {
        fm.setPath(token);
        Pattern P = Pattern.compile("^M.*\\.001");
        Matcher M = P.matcher(token);
        if(M.find()){
            fm.setCid(M.group().split("-")[0]);
            fm.setDid(M.group().split("-")[1].split("\\.")[0]);
        }
  
    }
    
    private void processModDate(String token, FileModel fm) {
        //System.out.println(token);
        Calendar cal = new GregorianCalendar();
        
        Pattern p = Pattern.compile("\\(.*\\)$");
        Matcher m = p.matcher(token);
        if(m.find()){
            String[] date = m.group().split(" ")[0].substring(1).split("-");
            String[] time = m.group().split(" ")[1].substring(1).split(":");
            //System.out.println(date);
            cal.set(
                Integer.parseInt(date[0]), Integer.parseInt(date[1]) - 1, Integer.parseInt(date[2]),
                Integer.parseInt(time[0]), Integer.parseInt(time[1]), Integer.parseInt(time[2])   
            );
            
            fm.setModDate(cal.getTime());
        }
    }
    
    public static void main(String[] args) throws FileNotFoundException, IOException {
        String s = "M18867-0001.csv";
        FileIndex f = new FileIndex(s);
    }




}
