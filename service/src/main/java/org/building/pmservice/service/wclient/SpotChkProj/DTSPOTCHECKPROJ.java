
package org.building.pmservice.service.wclient.SpotChkProj;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>DT_SPOTCHECKPROJ complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="DT_SPOTCHECKPROJ"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ORDER_NO3D"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;minLength value="1"/&gt;
 *               &lt;maxLength value="12"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ORDER_TYP"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;length value="4"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="FUNC_LOC"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="30"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="EQUIP_NO"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;minLength value="1"/&gt;
 *               &lt;maxLength value="18"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="PLANT"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;length value="4"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="IWERK"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;length value="4"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="START_DATE"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;length value="8"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="FINISH_DATE"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;length value="8"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="ACT_TYPE"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="3"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="PLANNER"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="3"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="WORK_CTR"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="8"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="SHORT_TXT"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="40"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="GSBER"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;maxLength value="4"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="WBS" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://www.anshanmining.com/pm/&gt;DT_SPOTCHECKPROJ.WBS.Content"&gt;
 *                 &lt;attribute ref="{http://sap.com/xi/SAPGlobal/GDT}DeletionIndicator"/&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="lineItemsOfLtext" maxOccurs="unbounded" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="LONG_TXT" minOccurs="0"&gt;
 *                     &lt;complexType&gt;
 *                       &lt;simpleContent&gt;
 *                         &lt;extension base="&lt;http://www.anshanmining.com/pm/&gt;DT_SPOTCHECKPROJ.lineItemsOfLtext.LONG_TXT.Content"&gt;
 *                           &lt;attribute ref="{http://sap.com/xi/SAPGlobal/GDT}DeletionIndicator"/&gt;
 *                         &lt;/extension&gt;
 *                       &lt;/simpleContent&gt;
 *                     &lt;/complexType&gt;
 *                   &lt;/element&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="CTROL_KEY" minOccurs="0"&gt;
 *           &lt;complexType&gt;
 *             &lt;simpleContent&gt;
 *               &lt;extension base="&lt;http://www.anshanmining.com/pm/&gt;DT_SPOTCHECKPROJ.CTROL_KEY.Content"&gt;
 *                 &lt;attribute ref="{http://sap.com/xi/SAPGlobal/GDT}DeletionIndicator"/&gt;
 *               &lt;/extension&gt;
 *             &lt;/simpleContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DT_SPOTCHECKPROJ", propOrder = {
    "orderno3D",
    "ordertyp",
    "funcloc",
    "equipno",
    "plant",
    "iwerk",
    "startdate",
    "finishdate",
    "acttype",
    "planner",
    "workctr",
    "shorttxt",
    "gsber",
    "wbs",
    "lineItemsOfLtext",
    "ctrolkey"
})
public class DTSPOTCHECKPROJ {

    @XmlElement(name = "ORDER_NO3D", required = true)
    protected String orderno3D;
    @XmlElement(name = "ORDER_TYP", required = true)
    protected String ordertyp;
    @XmlElement(name = "FUNC_LOC", required = true)
    protected String funcloc;
    @XmlElement(name = "EQUIP_NO", required = true)
    protected String equipno;
    @XmlElement(name = "PLANT", required = true)
    protected String plant;
    @XmlElement(name = "IWERK", required = true)
    protected String iwerk;
    @XmlElement(name = "START_DATE", required = true)
    protected String startdate;
    @XmlElement(name = "FINISH_DATE", required = true)
    protected String finishdate;
    @XmlElement(name = "ACT_TYPE", required = true)
    protected String acttype;
    @XmlElement(name = "PLANNER", required = true)
    protected String planner;
    @XmlElement(name = "WORK_CTR", required = true)
    protected String workctr;
    @XmlElement(name = "SHORT_TXT", required = true)
    protected String shorttxt;
    @XmlElement(name = "GSBER", required = true)
    protected String gsber;
    @XmlElementRef(name = "WBS", type = JAXBElement.class, required = false)
    protected JAXBElement<WBS> wbs;
    protected List<LineItemsOfLtext> lineItemsOfLtext;
    @XmlElementRef(name = "CTROL_KEY", type = JAXBElement.class, required = false)
    protected JAXBElement<CTROLKEY> ctrolkey;

    /**
     * 获取orderno3D属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getORDERNO3D() {
        return orderno3D;
    }

    /**
     * 设置orderno3D属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setORDERNO3D(String value) {
        this.orderno3D = value;
    }

    /**
     * 获取ordertyp属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getORDERTYP() {
        return ordertyp;
    }

    /**
     * 设置ordertyp属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setORDERTYP(String value) {
        this.ordertyp = value;
    }

    /**
     * 获取funcloc属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFUNCLOC() {
        return funcloc;
    }

    /**
     * 设置funcloc属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFUNCLOC(String value) {
        this.funcloc = value;
    }

    /**
     * 获取equipno属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEQUIPNO() {
        return equipno;
    }

    /**
     * 设置equipno属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEQUIPNO(String value) {
        this.equipno = value;
    }

    /**
     * 获取plant属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPLANT() {
        return plant;
    }

    /**
     * 设置plant属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPLANT(String value) {
        this.plant = value;
    }

    /**
     * 获取iwerk属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIWERK() {
        return iwerk;
    }

    /**
     * 设置iwerk属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIWERK(String value) {
        this.iwerk = value;
    }

    /**
     * 获取startdate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSTARTDATE() {
        return startdate;
    }

    /**
     * 设置startdate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSTARTDATE(String value) {
        this.startdate = value;
    }

    /**
     * 获取finishdate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFINISHDATE() {
        return finishdate;
    }

    /**
     * 设置finishdate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFINISHDATE(String value) {
        this.finishdate = value;
    }

    /**
     * 获取acttype属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getACTTYPE() {
        return acttype;
    }

    /**
     * 设置acttype属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setACTTYPE(String value) {
        this.acttype = value;
    }

    /**
     * 获取planner属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPLANNER() {
        return planner;
    }

    /**
     * 设置planner属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPLANNER(String value) {
        this.planner = value;
    }

    /**
     * 获取workctr属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWORKCTR() {
        return workctr;
    }

    /**
     * 设置workctr属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWORKCTR(String value) {
        this.workctr = value;
    }

    /**
     * 获取shorttxt属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSHORTTXT() {
        return shorttxt;
    }

    /**
     * 设置shorttxt属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSHORTTXT(String value) {
        this.shorttxt = value;
    }

    /**
     * 获取gsber属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGSBER() {
        return gsber;
    }

    /**
     * 设置gsber属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGSBER(String value) {
        this.gsber = value;
    }

    /**
     * 获取wbs属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link WBS }{@code >}
     *     
     */
    public JAXBElement<WBS> getWBS() {
        return wbs;
    }

    /**
     * 设置wbs属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link WBS }{@code >}
     *     
     */
    public void setWBS(JAXBElement<WBS> value) {
        this.wbs = value;
    }

    /**
     * Gets the value of the lineItemsOfLtext property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the lineItemsOfLtext property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLineItemsOfLtext().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LineItemsOfLtext }
     * 
     * 
     */
    public List<LineItemsOfLtext> getLineItemsOfLtext() {
        if (lineItemsOfLtext == null) {
            lineItemsOfLtext = new ArrayList<LineItemsOfLtext>();
        }
        return this.lineItemsOfLtext;
    }

    /**
     * 获取ctrolkey属性的值。
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link CTROLKEY }{@code >}
     *     
     */
    public JAXBElement<CTROLKEY> getCTROLKEY() {
        return ctrolkey;
    }

    /**
     * 设置ctrolkey属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link CTROLKEY }{@code >}
     *     
     */
    public void setCTROLKEY(JAXBElement<CTROLKEY> value) {
        this.ctrolkey = value;
    }


    /**
     * <p>anonymous complex type的 Java 类。
     * 
     * <p>以下模式片段指定包含在此类中的预期内容。
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;http://www.anshanmining.com/pm/&gt;DT_SPOTCHECKPROJ.CTROL_KEY.Content"&gt;
     *       &lt;attribute ref="{http://sap.com/xi/SAPGlobal/GDT}DeletionIndicator"/&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class CTROLKEY {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "DeletionIndicator", namespace = "http://sap.com/xi/SAPGlobal/GDT")
        protected Boolean deletionIndicator;

        /**
         * 获取value属性的值。
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * 设置value属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * 获取deletionIndicator属性的值。
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isDeletionIndicator() {
            return deletionIndicator;
        }

        /**
         * 设置deletionIndicator属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setDeletionIndicator(Boolean value) {
            this.deletionIndicator = value;
        }

    }


    /**
     * <p>anonymous complex type的 Java 类。
     * 
     * <p>以下模式片段指定包含在此类中的预期内容。
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;complexContent&gt;
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
     *       &lt;sequence&gt;
     *         &lt;element name="LONG_TXT" minOccurs="0"&gt;
     *           &lt;complexType&gt;
     *             &lt;simpleContent&gt;
     *               &lt;extension base="&lt;http://www.anshanmining.com/pm/&gt;DT_SPOTCHECKPROJ.lineItemsOfLtext.LONG_TXT.Content"&gt;
     *                 &lt;attribute ref="{http://sap.com/xi/SAPGlobal/GDT}DeletionIndicator"/&gt;
     *               &lt;/extension&gt;
     *             &lt;/simpleContent&gt;
     *           &lt;/complexType&gt;
     *         &lt;/element&gt;
     *       &lt;/sequence&gt;
     *     &lt;/restriction&gt;
     *   &lt;/complexContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "longtxt"
    })
    public static class LineItemsOfLtext {

        @XmlElementRef(name = "LONG_TXT", type = JAXBElement.class, required = false)
        protected JAXBElement<LONGTXT> longtxt;

        /**
         * 获取longtxt属性的值。
         * 
         * @return
         *     possible object is
         *     {@link JAXBElement }{@code <}{@link LONGTXT }{@code >}
         *     
         */
        public JAXBElement<LONGTXT> getLONGTXT() {
            return longtxt;
        }

        /**
         * 设置longtxt属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link JAXBElement }{@code <}{@link LONGTXT }{@code >}
         *     
         */
        public void setLONGTXT(JAXBElement<LONGTXT> value) {
            this.longtxt = value;
        }


        /**
         * <p>anonymous complex type的 Java 类。
         * 
         * <p>以下模式片段指定包含在此类中的预期内容。
         * 
         * <pre>
         * &lt;complexType&gt;
         *   &lt;simpleContent&gt;
         *     &lt;extension base="&lt;http://www.anshanmining.com/pm/&gt;DT_SPOTCHECKPROJ.lineItemsOfLtext.LONG_TXT.Content"&gt;
         *       &lt;attribute ref="{http://sap.com/xi/SAPGlobal/GDT}DeletionIndicator"/&gt;
         *     &lt;/extension&gt;
         *   &lt;/simpleContent&gt;
         * &lt;/complexType&gt;
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "value"
        })
        public static class LONGTXT {

            @XmlValue
            protected String value;
            @XmlAttribute(name = "DeletionIndicator", namespace = "http://sap.com/xi/SAPGlobal/GDT")
            protected Boolean deletionIndicator;

            /**
             * 获取value属性的值。
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getValue() {
                return value;
            }

            /**
             * 设置value属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setValue(String value) {
                this.value = value;
            }

            /**
             * 获取deletionIndicator属性的值。
             * 
             * @return
             *     possible object is
             *     {@link Boolean }
             *     
             */
            public Boolean isDeletionIndicator() {
                return deletionIndicator;
            }

            /**
             * 设置deletionIndicator属性的值。
             * 
             * @param value
             *     allowed object is
             *     {@link Boolean }
             *     
             */
            public void setDeletionIndicator(Boolean value) {
                this.deletionIndicator = value;
            }

        }

    }


    /**
     * <p>anonymous complex type的 Java 类。
     * 
     * <p>以下模式片段指定包含在此类中的预期内容。
     * 
     * <pre>
     * &lt;complexType&gt;
     *   &lt;simpleContent&gt;
     *     &lt;extension base="&lt;http://www.anshanmining.com/pm/&gt;DT_SPOTCHECKPROJ.WBS.Content"&gt;
     *       &lt;attribute ref="{http://sap.com/xi/SAPGlobal/GDT}DeletionIndicator"/&gt;
     *     &lt;/extension&gt;
     *   &lt;/simpleContent&gt;
     * &lt;/complexType&gt;
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "value"
    })
    public static class WBS {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "DeletionIndicator", namespace = "http://sap.com/xi/SAPGlobal/GDT")
        protected Boolean deletionIndicator;

        /**
         * 点检待修项目
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getValue() {
            return value;
        }

        /**
         * 设置value属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * 获取deletionIndicator属性的值。
         * 
         * @return
         *     possible object is
         *     {@link Boolean }
         *     
         */
        public Boolean isDeletionIndicator() {
            return deletionIndicator;
        }

        /**
         * 设置deletionIndicator属性的值。
         * 
         * @param value
         *     allowed object is
         *     {@link Boolean }
         *     
         */
        public void setDeletionIndicator(Boolean value) {
            this.deletionIndicator = value;
        }

    }

}
