/*******************************************************************************
 ** 
 ** Filename:  LMSManifestHandler.java
 **
 ** File Description:  This file extends from CPDOMParser.  It uses the parser
 **                    in a way unique to the Sample RTE.  All relevant data
 **                    from the manifest file is written to a corresponding
 **                    database after it is accessed.
 **
 ** Author:  ADLI Project
 **
 ** Company Name: CTC
 **
 ** Module/Package Name: org.adl.lms.server
 ** Module/Package Description:
 **
 ** Design Issues:
 **
 ** Implementation Issues:
 ** Known Problems:
 ** Side Effects:
 **
 ** References:  SCORM
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
 **
 *******************************************************************************/
package org.adl.samplerte.server;

// ADL imports
import java.io.IOException;
import java.net.URLDecoder;
import java.sql.Timestamp;
import java.util.List;
import java.util.Vector;

import javax.annotation.Resource;

import org.adl.parsers.dom.ADLItem;
import org.adl.parsers.dom.ADLOrganizations;
import org.adl.parsers.dom.CPDOMParser;
import org.adl.util.debug.DebugIndicator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.w3c.dom.Attr;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.scorm.action.ActionSupport;
import com.scorm.service.CourseinfoService;
import com.scorm.service.ScoinfoService;
import com.scorm.utils.UserCommon;
import com.scorm.vo.Courseinfo;
import com.scorm.vo.Scoinfo;
import com.scorm.vo.Userinfo;

@Scope(value="prototype")
@Component(value="lmsManifestHandler") 
public class LMSManifestHandler extends CPDOMParser {
	
	@Resource(name="scoinfoService")
	private ScoinfoService scoinfoService;
	
	@Resource(name="courseinfoService")
	private CourseinfoService courseinfoService;
	

	protected Vector items;
	private Vector tempItemList;
	protected InputSource sourceToParse;
	protected String courseTitle;
	protected String courseDir;
	protected String courseID;
	protected String control;
	protected String isTest;

	/**************************************************************************
	 ** 
	 ** Method: LMSManifestHandler() Input: None
	 ** 
	 ** Output: None
	 ** 
	 ** Description: Default Constructor.
	 ** 
	 ***************************************************************************/
	public LMSManifestHandler() {
		super();
		tempItemList = new Vector();
		items = new Vector();
		courseTitle = "";
		courseDir = "";
		courseID = "";
		control = "";
	}

	/****************************************************************************
	 ** 
	 ** Method: processManifest() Input: None Output: boolean - Whether or not
	 * the method was successful Description: This method initiates the RTE
	 * import/parsing of a manifest file from a PIF content package.
	 ** 
	 ** Side Effects: none
	 ** 
	 ****************************************************************************/
	public boolean processManifest() {
		boolean valid = false;
		if (DebugIndicator.ON) {
			System.out.println("Entering LMSManifestHandler::processManifest()");
		}

		try {
			// Parse the file
			parse(sourceToParse);

			if (DebugIndicator.ON) {
				System.out.println("Document parsing complete.");
			}
			System.out.println("1----document="+document);
			document = getDocument();
			System.out.println("2----document="+document);
			valid = true;

			// Call process content
			processContent();
		} catch (SAXException se) {
			String message = "Parser threw a SAXException.";
			System.out.println(message);
		} catch (IOException ioe) {
			String message = "Parser threw a IOException.";
			System.out.println(message);
		}

		if (DebugIndicator.ON) {
			System.out.println("Exiting LMSManifestHandler::processManifest()");
		}

		// Write to the corresponding database
		updateDB();
		// Return boolean signifying whether or not the parsing was successful
		return valid;
	}

	/****************************************************************************
	 ** 
	 ** Method: processContent() Input: none Output: boolean - Whether the method
	 * was successful or not. Description: This method starts at the root node
	 * and then calls calls fillManifest which populates the parser structure.
	 ** 
	 ** Side Effects: none
	 ** 
	 ***************************************************************************/
	public boolean processContent() {
		if (DebugIndicator.ON) {
			System.out.println("*** LMSManifestHandler::processContent() ***");
		}

		// boolean that signifies whether or not the method call was successful
		boolean result = true;

		if (DebugIndicator.ON) {
			if (document == null) {
				System.out.println("the document is null");
			}
		}
System.out.println("-------document="+document+"|---");
		// Get the root node of the imsmanifest <manifest>
		Node contentNode = document.getDocumentElement();

		// Get the children of the root node
		NodeList contentChildren = contentNode.getChildNodes();

		if (DebugIndicator.ON) {
			System.out.println("*** NODE: " + contentNode.getNodeName());
			System.out.println("*** Children - " + contentChildren.getLength()
					+ " ***");
		}

		// Call fillManifest(). This will fill the parser structure
		this.manifest.fillManifest(contentNode);

		// Set the control attribute
		// *************************************************
		// This is proprietary in version 1.2 of the RTE
		// This will change!
		// *************************************************
		this.setSequence();

		// Uses the newly populated structure to populate the
		// items Vector.
		this.items = this.getItemList();

		// Get resources and compare w/ the item Vector
		for (int i = 0; i < contentChildren.getLength(); i++) {
			Node currentNode = contentChildren.item(i);

			// Make sure this is an "element node"
			if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
				// Move to the resources element to process the resources
				if (currentNode.getNodeName().equalsIgnoreCase("resources")) {
					if (DebugIndicator.ON) {
						System.out.println("*** Found Current Node: "
								+ currentNode.getNodeName());
					}

					// process the Resources section.
					result = processResources(currentNode);
					if (result == false) {
						break;
					}
				}

			}
		}

		if (DebugIndicator.ON) {
			System.out
					.println("*** Exiting LMSManifestHandler::processContent() ***");
			System.out.println("*** Returning: " + result);
		}
		return result;
	}

	/****************************************************************************
	 ** 
	 ** Method: processResources Input: Node resourcesNode - The <resources> node
	 * from the imsmanfest file Output: Description: This method populates the
	 * resources section of the parser structure.
	 ** 
	 ** Side Effects: none
	 ** 
	 ***************************************************************************/
	public boolean processResources(Node resourcesNode) {

		boolean result = true;
		int level = 1;

		if (DebugIndicator.ON) {
			System.out
					.println("******  LMSManifestHandler:processResources  *********");
		}

		if (document == null) {
			System.out.println("the document is null");
		}

		// Get the children of the resources node
		NodeList resourcesChildren = resourcesNode.getChildNodes();

		if (DebugIndicator.ON) {
			System.out.println("*** NODE: " + resourcesNode.getNodeName());
			System.out.println("*** Children - "
					+ resourcesChildren.getLength() + " ***");
		}

		// Find the resource node(s)
		for (int i = 0; i < resourcesChildren.getLength(); i++) {
			Node currentNode = resourcesChildren.item(i);

			// Make sure this is an "element node"
			if (currentNode.getNodeType() == Node.ELEMENT_NODE) {
				if (currentNode.getNodeName().equalsIgnoreCase("resource")) {
					if (DebugIndicator.ON) {
						System.out.println("*** Found Current Node: "
								+ currentNode.getNodeName());
					}

					result = processResource(currentNode);
					if (result == false) {
						break;
					}
				}
			}
		}

		if (DebugIndicator.ON) {
			System.out
					.println("*** Exiting LMSManifestHandler::processResources() ***");
			System.out.println("*** Returning: " + result);
		}

		return result;
	}

	/****************************************************************************
	 ** 
	 ** Method: processResource() Input: Node resourceNode - A <resource> node
	 * from the imsmanifest file Output: Description: This method populates the
	 * resource section of the parser object.
	 ** 
	 ** Side Effects: none
	 ** 
	 ***************************************************************************/
	public boolean processResource(Node resourceNode) {
		boolean result = true;

		if (DebugIndicator.ON) {
			System.out
					.println("******  LMSManifestHandler:processResource  *********");
		}

		String id = getAttribute(resourceNode, "identifier");
		String scormType = getAttribute(resourceNode, "adlcp:scormtype");
		String type = getAttribute(resourceNode, "type");
		String href = getAttribute(resourceNode, "href");

		updateItemList(id, scormType, type, href);

		if (DebugIndicator.ON) {
			System.out
					.println("*** Exiting LMSManifestHandler::processResource() ***");
			System.out.println("*** Returning: " + result);
		}

		return result;

	}

	/****************************************************************************
	 ** 
	 ** Method: updateItemList Input: String id - the identifier of the item
	 * String scormType - the scormtype of the item (or reference) String type -
	 * the type attribute String href - the href location Output: Description:
	 * This method adds the launch location to the item vector. The launch is
	 * not part of the <item> element, so this method compares the identifier
	 * and identifierref and when a match is found, the item section of the
	 * parser object is populated with the resource launch location. This is
	 * done only for convenience.
	 ** 
	 ** Side Effects: none
	 ** 
	 ***************************************************************************/
	public void updateItemList(String id, String scormType, String type,
			String href) {
		if (DebugIndicator.ON) {
			System.out
					.println("******  LMSManifestHandler:updateItemList  *********");
		}

		ADLItem tempItem = new ADLItem();

		// Loop throught the item vector checking the identfierref with the
		// identifier of the resource. If they match, update the item and exit
		for (int i = 0; i < items.size(); i++) {
			tempItem = (org.adl.parsers.dom.ADLItem) this.items.elementAt(i);
			String idref = tempItem.getIdentifierref();

			if (idref.equals(id)) {
				if (DebugIndicator.ON) {
					System.out
							.println("****** !!  Found Matching Resource  !!  *****");
					System.out.println("i is:" + i + " -- Items size is"
							+ items.size());
				}

				if (type != null && !type.equals("")) {
					tempItem.setType(type);
				}
				if (scormType != null && !type.equals("")) {
					tempItem.setScormType(scormType);
				}
				if (href != null && !href.equals("")) {
					tempItem.setLaunchLocation(href);
				}
				items.removeElementAt(i);
				items.insertElementAt(tempItem, i);
			}
		}

		if (DebugIndicator.ON) {
			System.out
					.println("****** Exiting - LMSManifestHandler:updateItemList  *********");
		}

	}

	/****************************************************************************
	 ** 
	 ** Method: getCourseID() Input: none Output: String - this course id.
	 * Description: This is a getter method on the courseID member.
	 ** 
	 ****************************************************************************/
	public String getCourseID() {
		return this.courseID;
	}

	/****************************************************************************
	 ** 
	 ** Method: getOrgsCopy() Input: none Output: ADLOrganizations Description:
	 * This method was developed anticipating sequencing for version 1.3. This
	 * method will return the entire course <organization> information. This
	 * will include all sequencing information.
	 ** 
	 ****************************************************************************/
	public ADLOrganizations getOrgsCopy() {
		return this.manifest.getOrganizations();
	}

	/****************************************************************************
	 ** 
	 ** Method: setSequence() Input: none Output: none Description: This method
	 * will be used to set the control type when sequencing is introduced in
	 * version 1.3.
	 ** 
	 ****************************************************************************/
	public void setSequence() {
		if (DebugIndicator.ON) {
			System.out.println("In LMSManifestHandler:setSequence() ");
		}
		// ****************************************************************
		// This is being commented out due to the lack of a sequencing spec
		// in version 1.2. It will be used in the next version
		// ****************************************************************
		// this.control =
		// this.manifest.getOrganizations().getFirstOrg().getSequence().getControl();

		if (DebugIndicator.ON) {
			System.out.println("Control is: " + this.control);
		}
	}

	// Class "Setter" methods
	public void setCourseName(String courseTitle) {
		this.courseTitle = courseTitle;
	}

	public void setControl(String controlType) {
		this.control = controlType;
	}

	public void setFileToParse(InputSource inputStream) {
		this.sourceToParse = inputStream;
	}

	/****************************************************************************
	 ** 
	 ** Method: updateDB() Input: none Output: none Description: This method
	 * takes the relevant information from the populated parser structure and
	 * writes it to a related database. This is done so that the JSP coding is
	 * more straight forward.
	 ** 
	 ** Side Effects: none
	 ** 
	 ***************************************************************************/
	protected void updateDB() {
		if (DebugIndicator.ON) {
			System.out
					.println("******  LMSManifestHandler:updateDB()  *********");
		}
		try {
			
			int t = 1;
			List<Scoinfo> list = scoinfoService.findAllScoinfo();
			try{
				System.out.println("----"+list.size());
				if(list!=null&&list.size()>0){
					t = list.get(list.size()-1).getScoId()+1; 
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			courseID = t+"";

			ADLItem tempItem = new ADLItem();

			// Loop through each item in the course adding it to the
			// database
			for (int i = 0; i < items.size(); i++) {
				tempItem = (org.adl.parsers.dom.ADLItem) items.elementAt(i);

				// Decode the URL before inserting into the database
				URLDecoder urlDecoder = new URLDecoder();
				String alteredLocation = new String();

				// If its external, don't concatenate to the local Web root.
				if ((tempItem.getLaunchLocation().startsWith("http://"))
						|| (tempItem.getLaunchLocation()
								.startsWith("https://"))) {
					alteredLocation = URLDecoder.decode(tempItem.getLaunchLocation());
				} else {
					// Create the altered location (with decoded url)
					alteredLocation = "/scorm/CourseImports/"
							+ courseID
							+ "/"
							+ URLDecoder.decode(tempItem
									.getLaunchLocation());
				}

				// Insert into the database
				Courseinfo cInfo = null;
				try{
					System.out.println("courseName="+courseTitle+"\n-----"+courseinfoService);
					cInfo = courseinfoService.findByCourseId(Integer.parseInt(courseTitle)).get(0);
					System.out.println("上传课件findCourseName="+cInfo.getCourseName());
				}catch(Exception e){
					e.printStackTrace();
					System.out.println("error="+e.getMessage());
					return ;
				}
				System.out.println("------------");
				Timestamp uploadTime = new Timestamp(System.currentTimeMillis());
				System.out.println("isTest="+isTest);
				
				Userinfo userinfo = ActionSupport.getSessionUser();
				Scoinfo scoinfo = new Scoinfo(cInfo,tempItem.getTitle(),uploadTime,userinfo.getUserName(),alteredLocation,0,Integer.parseInt(isTest));
				scoinfoService.saveScoinfo(scoinfo);
				System.out.println("asdfsadf");
				
			} // Ends looping throught the item list


		} catch (Exception se) {
			if (DebugIndicator.ON) {
				
				se.printStackTrace();
			}
		} 
	}

	/****************************************************************************
	 ** 
	 ** Method: getAttribute() Input: Node theNode - The current node that was
	 * traversed to String the Attribute - The name of the attribute that the
	 * value is desired for. Output: Description: This method
	 ** 
	 ** Side Effects: none
	 ** 
	 ***************************************************************************/
	protected String getAttribute(Node theNode, String theAttribute) {
		String returnValue = new String();

		// grab attributes of the node
		Attr attrs[] = sortAttributes(theNode.getAttributes());

		// now see if the asked for attribute exists and send
		// back the value
		Attr attribute;
		for (int i = 0; i < attrs.length; i++) {
			attribute = attrs[i];

			if (attribute.getName().equals(theAttribute)) {
				returnValue = attribute.getValue();
				break;
			}
		}

		return returnValue;
	}

	/****************************************************************************
	 ** 
	 ** Method: sortAttributes() Input: NamedNodeMap attrs - The list of
	 * attributes Output: Attr[] - Sorted array of attributes Description: This
	 * method returns an array of sorted attributes.
	 ** 
	 ** Side Effects: none
	 ** 
	 ***************************************************************************/
	protected Attr[] sortAttributes(NamedNodeMap attrs) {
		int len = (attrs != null) ? attrs.getLength() : 0;
		Attr array[] = new Attr[len];
		for (int i = 0; i < len; i++) {
			array[i] = (Attr) attrs.item(i);
		}
		for (int i = 0; i < len - 1; i++) {
			String name = array[i].getNodeName();
			int index = i;
			for (int j = i + 1; j < len; j++) {
				String curName = array[j].getNodeName();
				if (curName.compareTo(name) < 0) {
					name = curName;
					index = j;
				}
			}
			if (index != i) {
				Attr temp = array[i];
				array[i] = array[index];
				array[index] = temp;
			}
		}

		return (array);

	}

	/****************************************************************************
	 ** 
	 ** Method: getSubElement() Input: Node node - The current node that has been
	 * traversed to String element - The name of the sub-element that's value is
	 * desired. Output: String - The value of the sub-element Description: This
	 * method gets the value of a desired sub-element. Side Effects:
	 ** 
	 ****************************************************************************/
	public String getSubElement(Node node, String element) {
		String value = new String();
		NodeList kids = node.getChildNodes();
		// cycle through all children of node to get the text
		if (kids != null) {
			for (int i = 0; i < kids.getLength(); i++) {
				if (kids.item(i).getNodeType() == Node.ELEMENT_NODE) {
					// look for the asked for element
					if (kids.item(i).getNodeName().equalsIgnoreCase(element)) {
						value = getText(kids.item(i));
						return value;
					}
				}
			}
		} else {
			if (DebugIndicator.ON) {
				System.out.println("node has no kids");
			}
		}
		return value;
	}

	/****************************************************************************
	 ** 
	 ** Method: getText() Input: Node node - The current node Output: String -
	 * The text of the desired node. Description: This method gets the child
	 * text node of the node that is passed in for input. Side Effects:
	 ** 
	 ****************************************************************************/
	public String getText(Node node) {
		String value = new String();
		NodeList kids = node.getChildNodes();
		// cycle through all children of node to get the text
		if (kids != null) {
			for (int i = 0; i < kids.getLength(); i++) {
				// make sure we have a text element
				if ((kids.item(i).getNodeType() == Node.TEXT_NODE)
						|| (kids.item(i).getNodeType() == Node.CDATA_SECTION_NODE)) {
					value = value + kids.item(i).getNodeValue().trim();
				}
			}
		} else {
			if (DebugIndicator.ON) {
				System.out.println("node has no kids");
			}
		}

		return value;
	}

	public String getIsTest() {
		return isTest;
	}

	public void setIsTest(String isTest) {
		this.isTest = isTest;
	}

}
