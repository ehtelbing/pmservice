
package org.building.pmservice.service.wclient.DJQXCLJG;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.anshanmining.eam_pm package. 
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

    private final static QName _MTDJQXCLJG_QNAME = new QName("http://www.anshanmining.com/EAM_PM/", "MT_DJQXCLJG");
    private final static QName _MTDJQXCLJGRet_QNAME = new QName("http://www.anshanmining.com/EAM_PM/", "MT_DJQXCLJGRet");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.anshanmining.eam_pm
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DTDJQXCLJGRet }
     * 
     */
    public DTDJQXCLJGRet createDTDJQXCLJGRet() {
        return new DTDJQXCLJGRet();
    }

    /**
     * Create an instance of {@link DTDJQXCLJG }
     * 
     */
    public DTDJQXCLJG createDTDJQXCLJG() {
        return new DTDJQXCLJG();
    }

    /**
     * Create an instance of {@link DTDJQXCLJGRet.ITEMS }
     * 
     */
    public DTDJQXCLJGRet.ITEMS createDTDJQXCLJGRetITEMS() {
        return new DTDJQXCLJGRet.ITEMS();
    }

    /**
     * Create an instance of {@link DTDJQXCLJG.ITEMS }
     * 
     */
    public DTDJQXCLJG.ITEMS createDTDJQXCLJGITEMS() {
        return new DTDJQXCLJG.ITEMS();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTDJQXCLJG }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.anshanmining.com/EAM_PM/", name = "MT_DJQXCLJG")
    public JAXBElement<DTDJQXCLJG> createMTDJQXCLJG(DTDJQXCLJG value) {
        return new JAXBElement<DTDJQXCLJG>(_MTDJQXCLJG_QNAME, DTDJQXCLJG.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DTDJQXCLJGRet }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.anshanmining.com/EAM_PM/", name = "MT_DJQXCLJGRet")
    public JAXBElement<DTDJQXCLJGRet> createMTDJQXCLJGRet(DTDJQXCLJGRet value) {
        return new JAXBElement<DTDJQXCLJGRet>(_MTDJQXCLJGRet_QNAME, DTDJQXCLJGRet.class, null, value);
    }

}
