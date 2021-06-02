package org.notima.test.bg;

import java.io.IOException;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.junit.Test;
import org.notima.bg.bgc.BgcAddresseeID;
import org.notima.bg.bgc.BgcBatch;
import org.notima.bg.bgc.BgcBatchDetails;
import org.notima.bg.bgc.BgcDocument;
import org.notima.bg.bgc.BgcDocumentDetails;
import org.notima.bg.bgc.BgcSection;
import org.notima.bg.bgc.BgcSectionDetails;

import junit.framework.TestCase;

public class TestBGCInvoice extends TestCase {
    
    @Test
	public void testMarshall() throws JAXBException {
        BgcBatch batch = new BgcBatch();
        batch.setId("test");
        batch.setStatus(BgcBatch.STATUS_TEST);
        batch.setVersion("1.2");

        BgcBatchDetails batchDetails = new BgcBatchDetails();
        batchDetails.setBgnr("0001212121");
        batchDetails.setTotalSections(1);
        batchDetails.setTotalDocuments(1);
        batch.setBatchDetails(batchDetails);

        BgcSection section = new BgcSection();
        section.setId("66548");
        section.setBgnr("0001212121");
        section.setBgnrType("originator");

        BgcSectionDetails sectionDetails = new BgcSectionDetails();
        sectionDetails.setTotalDocuments(1);

        section.setSectionDetails(sectionDetails);

        BgcDocument document = new BgcDocument();
        document.setId("186423");
        document.setType("INV01");
        document.setDistribution("B2B");
        document.setTemplateID(1);

        BgcDocumentDetails documentDetails = new BgcDocumentDetails();
        documentDetails.setOriginatorBgnr("0001212121");

        BgcAddresseeID addresseeID = new BgcAddresseeID();
        addresseeID.setType("ORGNR");
        addresseeID.setAdresseeID("5561234567");

        documentDetails.setAddresseeID(addresseeID);

        document.setDocumentDetails(documentDetails);

        List<BgcDocument> documents = new ArrayList<BgcDocument>();
        documents.add(document);

        section.setDocuments(documents);

        List<BgcSection> sections = new ArrayList<BgcSection>();
        sections.add(section);

        batch.setSections(sections);

        JAXBContext context = JAXBContext.newInstance(BgcBatch.class);
        Marshaller mar= context.createMarshaller();
        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        StringWriter sw = new StringWriter();
        mar.marshal(batch, sw);
        System.out.println(sw.toString());
    }

    @Test
    public void testUnmarshall() throws IOException, JAXBException {
        URL url = new URL("https://www.bankgirot.se/globalassets/dokument/exempelfiler/e-faktura/e-faktura_exempelfil_faktura_b2b.xml");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        
        JAXBContext context = JAXBContext.newInstance(BgcBatch.class);
        Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();  
        BgcBatch batch= (BgcBatch) jaxbUnmarshaller.unmarshal(con.getInputStream());

        System.out.println(batch.getBatchDetails().getBgnr());
        System.out.println(batch.getSections().get(0).getDocuments().get(0).getInvoice().getDelivery().getDate());
    }

    @Test
    public void testUnmarshallMarshall() throws Exception{
        URL url = new URL("https://www.bankgirot.se/globalassets/dokument/exempelfiler/e-faktura/e-faktura_exempelfil_faktura_b2b.xml");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        
        JAXBContext context = JAXBContext.newInstance(BgcBatch.class);
        Unmarshaller jaxbUnmarshaller = context.createUnmarshaller();  
        BgcBatch batch = (BgcBatch) jaxbUnmarshaller.unmarshal(con.getInputStream());

        System.out.println(batch.toString());
    }
}
