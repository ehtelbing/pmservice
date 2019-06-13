
package org.building.pmservice.service.wclient.SpotChkProj;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.anshanmining.pm package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _MTSPOTCHKPROJ_QNAME = new QName("http://www.anshanmining.com/pm/", "MT_SPOTCHKPROJ");
    private final static QName _DTSPOTCHECKPROJWBS_QNAME = new QName("", "WBS");
    private final static QName _DTSPOTCHECKPROJCTROLKEY_QNAME = new QName("", "CTROL_KEY");
    private final static QName _DTSPOTCHECKPROJLineItemsOfLtextLONGTXT_QNAME = new QName("", "LONG_TXT");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.anshanmining.pm
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DTSPOTCHECKPROJ }
     * 
     */
    public DTSPOTCHECKPROJ createDTSPOTCHECKPROJ() {
        return new DTSPOTCHECKPROJ();
    }

    /**
     * Create an instance of {@link DTSPOTCHECKPROJ.LineItemsOfLtext }
     * 
     */
    public DTSPOTCHECKPROJ.LineItemsOfLtext createDTSPOTCHECKPROJLineItemsOfLtext() {
        return new DTSPOTCHECKPROJ.LineItemsOfLtext();
    }

    /**
     * Create an instance of {@link ZFAULTMSG }
     * 
     */
    public ZFAULTMSG createZFAULTMSG() {
        return new ZFAULTMSG();
    }

    /**
     * Create an instance of {@link ExchangeFaultData }
     * 
     */
    public ExchangeFaultData createExchangeFaultData() {
        return new ExchangeFaultData();
    }

    /**
     * Create an instance of {@link ExchangeLogData }
     * 
     */
    public ExchangeLogData createExchangeLogData() {
        return new ExchangeLogData();
    }

    /**
     * Create an instance of {@link DTSPOTCHECKPROJ.WBS }
     * 
     */
    public DTSPOTCHECKPROJ.WBS createDTSPOTCHECKPROJWBS() {
        return new DTSPOTCHECKPROJ.WBS();
    }

    /**
     * Create an instance of {@link DTSPOTCHECKPROJ.CTROLKEY }
     * 
     */
    public DTSPOTCHECKPROJ.CTROLKEY createDTSPOTCHECKPROJCTROLKEY() {
        return new DTSPOTCHECKPROJ.CTROLKEY();
    }

    /**
     * Create an instance of {@link DTSPOTCHECKPROJ.LineItemsOfLtext.LONGTXT }
     * 
     */
    public DTSPOTCHECKPROJ.LineItemsOfLtext.LONGTXT createDTSPOTCHECKPROJLineItemsOfLtextLONGTXT() {
        return new DTSPOTCHECKPROJ.LineItemsOfLtext.LONGTXT();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTSPOTCHECKPROJ }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.anshanmining.com/pm/", name = "MT_SPOTCHKPROJ")
    public JAXBElement<DTSPOTCHECKPROJ> createMTSPOTCHKPROJ(DTSPOTCHECKPROJ value) {
        return new JAXBElement<DTSPOTCHECKPROJ>(_MTSPOTCHKPROJ_QNAME, DTSPOTCHECKPROJ.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTSPOTCHECKPROJ.WBS }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "WBS", scope = DTSPOTCHECKPROJ.class)
    public JAXBElement<DTSPOTCHECKPROJ.WBS> createDTSPOTCHECKPROJWBS(DTSPOTCHECKPROJ.WBS value) {
        return new JAXBElement<DTSPOTCHECKPROJ.WBS>(_DTSPOTCHECKPROJWBS_QNAME, DTSPOTCHECKPROJ.WBS.class, DTSPOTCHECKPROJ.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTSPOTCHECKPROJ.CTROLKEY }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "CTROL_KEY", scope = DTSPOTCHECKPROJ.class)
    public JAXBElement<DTSPOTCHECKPROJ.CTROLKEY> createDTSPOTCHECKPROJCTROLKEY(DTSPOTCHECKPROJ.CTROLKEY value) {
        return new JAXBElement<DTSPOTCHECKPROJ.CTROLKEY>(_DTSPOTCHECKPROJCTROLKEY_QNAME, DTSPOTCHECKPROJ.CTROLKEY.class, DTSPOTCHECKPROJ.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTSPOTCHECKPROJ.LineItemsOfLtext.LONGTXT }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "LONG_TXT", scope = DTSPOTCHECKPROJ.LineItemsOfLtext.class)
    public JAXBElement<DTSPOTCHECKPROJ.LineItemsOfLtext.LONGTXT> createDTSPOTCHECKPROJLineItemsOfLtextLONGTXT(DTSPOTCHECKPROJ.LineItemsOfLtext.LONGTXT value) {
        return new JAXBElement<DTSPOTCHECKPROJ.LineItemsOfLtext.LONGTXT>(_DTSPOTCHECKPROJLineItemsOfLtextLONGTXT_QNAME, DTSPOTCHECKPROJ.LineItemsOfLtext.LONGTXT.class, DTSPOTCHECKPROJ.LineItemsOfLtext.class, value);
    }

}
