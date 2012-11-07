package org.nypl.mss;

import java.io.IOException;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;

public class SolrFile {
    private final HttpSolrServer solr;
    private SolrDocumentList sdl;
    private FileModel fm;
    private SolrInputDocument doc;
    
    SolrFile() throws SolrServerException, IOException{
        solr = new HttpSolrServer("http://localhost:8080/solr");
        sdl = new SolrDocumentList();
    }
        
    public void indexFM(FileModel fm) throws SolrServerException, IOException{
        this.fm = fm;
        doc = new SolrInputDocument();
        populateDoc();

    }

    private void populateDoc() throws SolrServerException, IOException {
        doc.addField("id", fm.getUid());
        doc.addField("filename", fm.getFileName());
        doc.addField("cid", fm.getCid());
        doc.addField("did", fm.getDid());
        doc.addField("fid", fm.getFid());
        doc.addField("fType", fm.getFileType());
        doc.addField("fSize", fm.getFileSize());
        doc.addField("language", fm.getLanguage());
        for(String name: fm.getNames()){
            doc.addField("names", name);
        }
        
        for(String name: fm.getOrgs()){
            doc.addField("orgs", name);
        }
        
        for(String name: fm.getLocations()){
            doc.addField("locs", name);
        } 
        
        solr.add(doc);
        System.out.println(doc);
    }

    public void finalizeDoc() throws SolrServerException, IOException {
        solr.commit();
        solr.optimize();
    }

}
