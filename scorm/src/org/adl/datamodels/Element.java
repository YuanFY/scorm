/*******************************************************************************
 **
 ** Filename:  Element.java
 **
 ** File Description:  The Element class manages the low level detail about
 **                    an CMI element.  It holds the value and type of the
 **                    element.  It provides methods to determine if the
 **                    element is mandatory,readable,writeable and implemented.
 **
 ** Author:  ADLI Project
 **
 ** Company Name: Concurrent Technologies Corporation
 **
 ** Module/Package Name: org.adl.datamodel.cmi
 ** Module/Package Description: Collection of CMI Data Model objects
 **
 ** Design Issues:
 ** Implementation Issues:
 ** Known Problems:
 ** Side Effects:
 **
 ** References: AICC CMI Data Model
 **             ADL SCORM
 **
 *******************************************************************************
 **
 ** Concurrent Technologies Corporation (CTC) grants you ("Licensee") a non-
 ** exclusive, royalty free, license to use, modify and redistribute this
 ** software in source and binary code form, provided that i) this copyright
 ** notice and license appear on all copies of the software; and ii) Licensee
 ** does not utilize the software in a manner which is disparaging to CTC.
 **
 ** This software is provided "AS IS," without a warranty of any kind.  ALL
 ** EXPRESS OR IMPLIED CONDITIONS, REPRESENTATIONS AND WARRANTIES, INCLUDING ANY
 ** IMPLIED WARRANTY OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE OR NON-
 ** INFRINGEMENT, ARE HEREBY EXCLUDED.  CTC AND ITS LICENSORS SHALL NOT BE LIABLE
 ** FOR ANY DAMAGES SUFFERED BY LICENSEE AS A RESULT OF USING, MODIFYING OR
 ** DISTRIBUTING THE SOFTWARE OR ITS DERIVATIVES.  IN NO EVENT WILL CTC  OR ITS
 ** LICENSORS BE LIABLE FOR ANY LOST REVENUE, PROFIT OR DATA, OR FOR DIRECT,
 ** INDIRECT, SPECIAL, CONSEQUENTIAL, INCIDENTAL OR PUNITIVE DAMAGES, HOWEVER
 ** CAUSED AND REGARDLESS OF THE THEORY OF LIABILITY, ARISING OUT OF THE USE OF
 ** OR INABILITY TO USE SOFTWARE, EVEN IF CTC HAS BEEN ADVISED OF THE POSSIBILITY
 ** OF SUCH DAMAGES.
 **
 *******************************************************************************
 **
 ** Date Changed   Author of Change  Reason for Changes
 ** ------------   ----------------  -------------------------------------------
 ** 11/15/2000      S. Thropp        PT 263: Removal of SCORM version reference
 **
 *******************************************************************************/
package org.adl.datamodels;

//native java imports
import java.io.*;

//adl imports
import org.adl.util.debug.*;

public class Element implements Serializable {
	// The value held by the elment
	private String value;

	// The type of the element
	private String type;

	// Whether or not the element can be set by an AU
	private boolean writeable;

	// Whether or not the LMS has implemented the element
	private boolean implemented;

	// Whether or not the LMS has initialized the element
	// The value attribute will have a value other than
	// an empty String ("")
	private boolean initialized;

	// Whether or not the element can be seen by an AU
	private boolean readable;

	// Whether or not the element is mandatory
	private boolean mandatory;

	// The vocabulary type (as needed). Used in verification of valid
	// request
	private String vocabularyType;

	/****************************************************************************
	 ** 
	 ** Method: Constructor Input: String inValue - string to set the value
	 * attribute String inType - string to set the type attribute boolean
	 * writeableFlag - flag indicating if the element is writeable boolean
	 * readableFlag - flag indicating if the element is readable boolean
	 * mandatoryFlag - flag indicating if the element is mandatory Output: none
	 ** 
	 ** Description: The Constructor sets up the class and initalizes all of its
	 * attributes
	 ** 
	 ***************************************************************************/
	public Element(String inValue, String inType, String inVocab,
			boolean writeableFlag, boolean readableFlag, boolean mandatoryFlag) {
		value = inValue;
		type = inType;
		vocabularyType = inVocab;
		writeable = writeableFlag;
		readable = readableFlag;
		if (inValue.equalsIgnoreCase("")) {
			initialized = false;
		} else {
			initialized = true;
		}

		mandatory = mandatoryFlag;
		implemented = true;

	} // end of constructor

	/****************************************************************************
	 ** 
	 ** Method: Default Constructor Input: none Output: none
	 ** 
	 ** Description: The Default Constructor sets up the class and initalizes all
	 * of its attributes to default values
	 ** 
	 ***************************************************************************/
	public Element() {
		value = new String("");
		type = new String("");
		vocabularyType = new String("");
		writeable = false;
		readable = false;
		initialized = false;
		implemented = false;
		mandatory = false;
	} // end of Default Constructor

	/****************************************************************************
	 ** 
	 ** Method: isWriteable() Input: none Output: boolean result - indicates
	 * whether or not the element is writeable
	 ** 
	 ** Description: Indicates whether or not the element is writeable (can be
	 * set with an LMSSetValue() request.
	 ** 
	 ***************************************************************************/
	public boolean isWriteable() {
		return writeable;
	} // end of isWriteable()

	/****************************************************************************
	 ** 
	 ** Method: isImplemented Input: none Output: boolean result - indicates
	 * whether or not the element is implemented
	 ** 
	 ** Description: Indicates whether or not the element is implemented.
	 ** 
	 ***************************************************************************/
	public boolean isImplemented() {
		return implemented;
	} // end of isImplemented()

	/****************************************************************************
	 ** 
	 ** Method: isInitialized Input: none Output: boolean result - indicates
	 * whether or not the element is initialized.
	 ** 
	 ** Description: Indicates whether or not the element is initialized.
	 ** 
	 ***************************************************************************/
	public boolean isInitialized() {
		return initialized;
	} // end of isInitialized()

	/****************************************************************************
	 ** 
	 ** Method: isReadable Input: none Output: boolean result - indicates whether
	 * or not the element is readable
	 ** 
	 ** Description: Indicates whether or not the element is able to be accessed.
	 * (accessed via an LMSGetValue();
	 ** 
	 ***************************************************************************/
	public boolean isReadable() {
		return readable;
	} // end of isReadable()

	/****************************************************************************
	 ** 
	 ** Method: isMandatory Input: none Output: boolean result - indicates
	 * whether or not the element is mandatory
	 ** 
	 ** Description: Indicates whether or not the element was mandatory
	 ** 
	 ***************************************************************************/
	public boolean isMandatory() {
		return mandatory;
	} // end of isMandatory()

	/****************************************************************************
	 ** 
	 ** Method: getVocabularyType() Input: none Output: String - the specific
	 * vocabulary type
	 ** 
	 ** Description: Returns the vocabulary type of Element. Only those Elements
	 * that have a type of CMIVocabulary will have a valid Vocabulary Type. All
	 * others will be set to "NULL"
	 ** 
	 ***************************************************************************/
	public String getVocabularyType() {
		return vocabularyType;
	} // end of getVocabularyType()

	/****************************************************************************
	 ** 
	 ** Method: getValue Input: none Output: String value - the value of the
	 * element
	 ** 
	 ** Description: Returns the value held by the element.
	 ** 
	 ***************************************************************************/
	public String getValue() {
		return value;
	} // end of getValue()

	/****************************************************************************
	 ** 
	 ** Method: setValue Input: String inValue - value to use in setting the
	 * element Output: none
	 ** 
	 ** Description: Sets the elements value determined by the input argument.
	 ** 
	 ***************************************************************************/
	public void setValue(String inValue) {
		value = inValue;
		initialized = true;
	} // end of setValue()

	/****************************************************************************
	 ** 
	 ** Method: getType Input: none Output: String - the type of the element
	 ** 
	 ** Description: Returns the type of the element
	 ** 
	 ***************************************************************************/
	public String getType() {
		return type;
	} // end of getType()

	/****************************************************************************
	 ** 
	 ** Method: setElement Input: Element - the element to use to set up this
	 * element Output: none
	 ** 
	 ** Description: Sets this object's attributes in accordance with the input
	 * element. (Acts like a copy constructor)
	 ** 
	 ***************************************************************************/
	protected void setElement(Element e) {
		this.type = e.getType();
		this.value = e.getValue();
		this.vocabularyType = e.getVocabularyType();
		this.implemented = e.isImplemented();
		this.initialized = e.isInitialized();
		this.mandatory = e.isMandatory();
		this.readable = e.isReadable();
		this.writeable = e.isWriteable();
	} // end of setElement()

	/****************************************************************************
	 ** 
	 ** Method: showElement Input: none Output: none
	 ** 
	 ** Description: Sends all of the attributes to System.out in a readable
	 * manner.
	 ** 
	 ***************************************************************************/
	public void showElement() {
		if (DebugIndicator.ON) {
			System.out.println("     Value         " + getValue());
			System.out.println("     Type          " + getType());
			System.out.println("     Vocab Type    " + getVocabularyType());
			System.out.println("     Writeable     " + isWriteable());
			System.out.println("     Readable      " + isReadable());
			System.out.println("     Mandatory     " + isMandatory());
			System.out.println("     Implemented   " + isImplemented());
			System.out.println("     Intialized    " + isInitialized());
		}
	} // end of showElement()

} // end of Element

