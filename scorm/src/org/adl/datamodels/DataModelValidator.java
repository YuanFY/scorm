/*******************************************************************************
 **
 ** Filename:  DataModelValidator.java
 **
 ** File Description:  This class acts as a Data Model element verifier.  It
 **                    is used to verify that the set value of an LMSSetValue()
 **                    request maps to the correct type of the element.  It
 **                    is also used to verify that a return value from an LMS
 **                    for an LMSGetValue() request maps to the correct type
 **                    expected.
 **
 ** Author: ADLI Project
 **
 ** Company Name: Concurrent Technologies Corporation
 **
 ** Module/Package Name:  org.adl.datamodel.cmi
 ** Module/Package Description: Collection of CMI Data Model objects
 **
 ** Design Issues:
 **
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
 ** Date Changed   Author            Reason for Changes
 ** ------------   ----------------  -------------------------------------------
 ** 09/05/2000     ADLI Project      PT110 : The method checkString256() was
 **                                          changed to checkString255() to
 **                                          reflect the CMI 3.0.3 changes
 **
 ** 09/07/2000     ADLI Project      PT112 : The method checkLocale() was removed
 **                                          due to data model changes. CMILocale
 **                                          elements now fall under the
 **                                          CMIString255 category.
 **
 ** 11/07/2000     ADLI Project      PT 297: Vocabulary lists and data types were
 **                                   updated to match the SCORM 1.1
 **                                   specifications.
 **
 ** 11/15/2000     S. Thropp         PT 263: Removal of reference to SCORM
 **                                  version.
 **                                  PT 297: Cleaned up definition of Time and
 **                                  Timespan.  The seconds for these two data
 **                                  types must be in the following format:
 **                                  SS.SS - five chars
 **
 ** 12/15/2000     J. Poltrack       PT 297: The credit vocab member "no credit"
 **                                  was changed to "no-credit"
 **
 ** 12/27/2001     Jeff Falls        Added checkScorDecimal() to test the
 **                                  "cmi.core.score" elements for negative
 **                                  values
 **
 ** 01/21/2001     Jeff Falls        Added check to checkScoreDecimal() to test
 **                                  for normalized scores (0 <= n >= 100)
 **
 ** 05/09/2002     S. Thropp         PT1828: Removed the convertString() function
 **                                  This function is not used and it is private.
 **                                  Also removed fixWord function, this again
 **                                  is a private function that was only used
 **                                  by the convertString function.
 **
 ** 05/09/2002     S. Thropp         PT1832:  The checkIdentifier method returns
 **                                  true for blank identifiers.  This is not
 **                                  correct according to the SCORM.  Added
 **                                  test to see if the identifier is blank
 **
 ** 05/09/2002     S. Thropp         PT1833:  Changed checkTime to correctly
 **                                  check the seconds part.  Code added to make
 **                                  sure seconds has at least 2 decimals
 **                                  (03:00:03 vs. 03:00:3)
 **
 ** 05/14/2002     B. Capone         PT1833: Modified the hours/minutes if
 **                                  statement in the try block of checkTime().
 **                                  Only want to check seconds if hours and
 **                                  minutes are within allowable range.
 **
 ********************************************************************************/
package org.adl.datamodels;

//native java imports
import java.io.*;
import java.util.*;
//adl imports
import org.adl.util.debug.*;

public class DataModelValidator implements Serializable {
	// Hash table to hold the different vocabulary types found in the SCORM
	private static transient Hashtable vocabulary;

	/****************************************************************************
	 ** 
	 ** Method: DataModelvalidator Input : none Output: none
	 ** 
	 ** Description: Default constructor - Sets up the hash table with the
	 * following keys: Mode, Status, Exit, WhyLeft, Credit, Entry,
	 * TimeLimitAction, Interaction, and Result.
	 ** 
	 ** Each key maps to a set of valid values used for the keys vocabulary type
	 ** 
	 ** Mode - normal,review,browse Status - passed, completed, failed,
	 * incomplete, browsed not attempted.
	 ***************************************************************************/
	public DataModelValidator() {
		vocabulary = new Hashtable();

		vocabulary.put("Mode", buildModeList());
		vocabulary.put("Status", buildStatusList());
		vocabulary.put("Exit", buildExitList());
		vocabulary.put("Credit", buildCreditList());
		vocabulary.put("Entry", buildEntryList());
		vocabulary.put("TimeLimitAction", buildTimeLimitActionList());
		vocabulary.put("Interaction", buildInteractionList());
		vocabulary.put("Result", buildResultList());
	}

	/****************************************************************************
	 ** 
	 ** Method: buildModeList() Input : none Output: String[] - list of members
	 ** 
	 ** Description: This method builds the list of valid Mode vocabulary
	 * members: normal,review,browse
	 ***************************************************************************/
	private String[] buildModeList() {
		int numItems = 3;
		String[] list = new String[numItems];

		list[0] = "normal";
		list[1] = "review";
		list[2] = "browse";
		return list;
	}

	/****************************************************************************
	 ** 
	 ** Method: buildStatusList() Input : none Output: String[] - list of members
	 ** 
	 ** Description: This method builds the list of valid Status vocabulary
	 * members: passed,completed,failed,incomplete,browsed,not attempted
	 ***************************************************************************/
	private String[] buildStatusList() {
		int numItems = 6;
		String[] list = new String[numItems];

		list[0] = "passed";
		list[1] = "completed";
		list[2] = "failed";
		list[3] = "incomplete";
		list[4] = "browsed";
		list[5] = "not attempted";
		return list;
	}

	/****************************************************************************
	 ** 
	 ** Method: buildExitList() Input : none Output: String[] - list of members
	 ** 
	 ** Description: This method builds the list of valid Exit vocabulary
	 * members: time-out, suspend, logout
	 ***************************************************************************/
	private String[] buildExitList() {
		int numItems = 4;
		String[] list = new String[numItems];

		list[0] = "";
		list[1] = "time-out";
		list[2] = "suspend";
		list[3] = "logout";
		return list;
	}

	/****************************************************************************
	 ** 
	 ** Method: buildCreditList() Input : none Output: String[] - list of members
	 ** 
	 ** Description: This method builds the list of valid Credit vocabulary
	 * members: credit, no credit
	 ***************************************************************************/
	private String[] buildCreditList() {
		int numItems = 2;
		String[] list = new String[numItems];

		list[0] = "credit";
		list[1] = "no-credit";
		return list;
	}

	/****************************************************************************
	 ** 
	 ** Method: buildEntryList() Input : none Output: String[] - list of members
	 ** 
	 ** Description: This method builds the list of valid Entry vocabulary
	 * members: ab-initio, resume
	 ***************************************************************************/
	private String[] buildEntryList() {
		int numItems = 3;
		String[] list = new String[numItems];

		list[0] = "";
		list[1] = "ab-initio";
		list[2] = "resume";
		return list;
	}

	/****************************************************************************
	 ** 
	 ** Method: buildTimeLimitActionList() Input : none Output: String[] - list
	 * of members
	 ** 
	 ** Description: This method builds the list of valid Time Limit Action
	 * vocabulary members: "exit,message", "continue,message",
	 * "exit,no message","continue,no message"
	 ***************************************************************************/
	private String[] buildTimeLimitActionList() {
		int numItems = 5;
		String[] list = new String[numItems];

		list[0] = "";
		list[1] = "exit,message";
		list[2] = "exit,no message";
		list[3] = "continue,message";
		list[4] = "continue,no message";
		return list;
	}

	/****************************************************************************
	 ** 
	 ** Method: buildInteractionList() Input : none Output: String[] - list of
	 * members
	 ** 
	 ** Description: This method builds the list of valid Interaction vocabulary
	 * members: true-false, multiple choice, fill in the blank, matching, simple
	 * performance, likert, sequencing, numeric
	 ***************************************************************************/
	private String[] buildInteractionList() {
		int numItems = 8;
		String[] list = new String[numItems];

		list[0] = "true-false";
		list[1] = "choice";
		list[2] = "fill-in";
		list[3] = "matching";
		list[4] = "performance";
		list[5] = "likert";
		list[6] = "sequencing";
		list[7] = "numeric";
		return list;
	}

	/****************************************************************************
	 ** 
	 ** Method: buildResultList() Input : none Output: String[] - list of members
	 ** 
	 ** Description: This method builds the list of valid Result vocabulary
	 * members: correcct, wrong, unanticipated, neutral, x.x
	 ***************************************************************************/
	private String[] buildResultList() {
		int numItems = 4;
		String[] list = new String[numItems];

		list[0] = "correct";
		list[1] = "wrong";
		list[2] = "unanticipated";
		list[3] = "neutral";

		return list;
	}

	/****************************************************************************
	 ** 
	 ** Method: checkBlank() Input: Element elementToBeChecked - Element to be
	 * checked String value - value to be set Output: boolean result - indicates
	 * whether or not the value is correct type according to element
	 ** 
	 ** Description: This method check to see if the value is blank (length of
	 * string is 0
	 ***************************************************************************/
	public boolean checkBlank(Element elementToBeChecked, String value) {
		boolean result = false;
		if (value.length() == 0) {
			result = true;
		}
		return result;
	}

	/****************************************************************************
	 ** 
	 ** Method: checkBoolean() Input: Element elementToBeChecked - Element to be
	 * checked String value - value to be checked Output: boolean result -
	 * indicates whether or not the value is correct type according to element
	 ** 
	 ** Description: This method checks to see if the value passed in is equal to
	 * either true or false.
	 ***************************************************************************/
	public boolean checkBoolean(Element elementToBeChecked, String value) {
		boolean flag = false;
		if ((value.equals("true")) || (value.equals("false"))) {
			flag = true;
		}
		return flag;
	}

	/****************************************************************************
	 ** 
	 ** Method: checkFeedback() Input: Element elementToBeChecked - Element to be
	 * checked String value - value to be checked Output: boolean result -
	 * indicates whether or not the value is correct type according to element
	 ** 
	 ** Description:
	 ***************************************************************************/
	public boolean checkFeedback(Element elementToBeChecked, String value) {
		return true;
	}

	/****************************************************************************
	 ** 
	 ** Method: checkString255() Input: Element elementToBeChecked - Element to
	 * be checked String value - value to be checked Output: boolean result -
	 * indicates whether or not the value is correct type according to element
	 ** 
	 ** Description: This method makes sure the value passed in is less than or
	 * equal to 255
	 ***************************************************************************/
	public boolean checkString255(Element elementToBeChecked, String value) {
		boolean flag = false;
		if (value.length() <= 255) {
			flag = true;
		}

		return flag;
	}

	/****************************************************************************
	 ** 
	 ** Method: checkString4096() Input: Element elementToBeChecked - Element to
	 * be checked String setValue - value to be checked Output: boolean result -
	 * indicates whether or not the value is correct type according to element
	 ** 
	 ** Description: This method checks the value passed in to see if it is less
	 * than or equal to 4096
	 ***************************************************************************/
	public boolean checkString4096(Element elementToBeChecked, String value) {
		boolean flag = false;
		if (value.length() <= 4096) {
			flag = true;
		}

		return flag;
	}

	/****************************************************************************
	 ** 
	 ** Method: checkScoreDecimal() Input: Element elementToBeChecked - the
	 * element to be checked String value - value to be checked Output: boolean
	 * result - indicates whether or not the request is valid
	 ** 
	 ** Description: This method checks to make sure the value passed in is a
	 * valid decimal
	 ***************************************************************************/
	public boolean checkScoreDecimal(Element elementToBeChecked, String value) {
		boolean result = false;

		Double zero = new Double(0.0);
		Double oneHundred = new Double(100.0);

		if (!(checkBlank(elementToBeChecked, value))) {
			try {
				Double tmpDouble = new Double(value);

				// Check for Normalized Score (0 <= n >= 100)
				if ((tmpDouble.compareTo(zero) >= 0)
						&& (tmpDouble.compareTo(oneHundred) <= 0)) {
					result = true;
				} else {
					if (DebugIndicator.ON) {
						System.out
								.println(value + " is not a normalized score");
						System.out.println("0 <= " + value + " >= 100");
					}
				}
			} catch (NumberFormatException nfe) {
				if (DebugIndicator.ON) {
					System.out.println(value
							+ " is not a valid CMIDecimal Type");
				}
			}

		} else {
			result = true;
		}

		return result;
	}

	/****************************************************************************
	 ** 
	 ** Method: checkDecimal() Input: Element elementToBeChecked - the element to
	 * be checked String value - value to be checked Output: boolean result -
	 * indicates whether or not the request is valid
	 ** 
	 ** Description: This method checks to make sure the value passed in is a
	 * valid decimal
	 ***************************************************************************/
	public boolean checkDecimal(Element elementToBeChecked, String value) {
		boolean result = false;

		if (!(checkBlank(elementToBeChecked, value))) {

			try {
				Double tmpDouble = new Double(value);
				result = true;
			} catch (NumberFormatException nfe) {
				if (DebugIndicator.ON) {
					System.out.println(value
							+ " is not a valid CMIDecimal Type ");
				}
			}
		} else {
			result = true;
		}

		return result;
	}

	/****************************************************************************
	 ** 
	 ** Method: checkVocabulary Input: Element elementToBeChecked - The element
	 * to be checked String value - value to be checked Output: boolean result -
	 * indicates whether or not the request is valid
	 ** 
	 ** Description: This method checks to make sure the set value passed in is
	 * the correct vocabulary member of the element.
	 ***************************************************************************/
	public boolean checkVocabulary(Element elementToBeChecked, String value) {

		boolean flag = false;
		String vocabType = elementToBeChecked.getVocabularyType();

		// find out the field being verified from the element
		String[] theList = (String[]) vocabulary.get(vocabType);

		for (int i = 0; i < theList.length; i++) {
			String tmpString = theList[i];

			if ((tmpString.equals(value) == true)) {
				flag = true;
				break;
			}
		}

		// Check to see if the "Results" category is passing in a decimal number
		if ((vocabType.equals("Result")) && (flag != true)) {
			flag = checkDecimal(elementToBeChecked, value);
		}

		// No match found
		if (flag == false) {
			if (DebugIndicator.ON) {
				System.out.println("[" + value
						+ "] is not a valid vocabulary member ");
				System.out.println("for the " + vocabType + " Vocabulary Type");
			}
		}

		return flag;

	}

	/****************************************************************************
	 ** 
	 ** Method: checkIdentifier Input: Element elementToBeChecked - element to be
	 * checked String value - value to be checked
	 ** 
	 ** Output: boolean result - indicates whether or not the request is valid
	 ** 
	 ** Description: This method check to make sure the value passed in is a
	 * valid identifier.
	 ***************************************************************************/
	public boolean checkIdentifier(Element elementToBeChecked, String value) {
		boolean flag = false;

		if ((value.length() <= 255) && (value.indexOf(' ') == -1)
				&& (!(checkBlank(elementToBeChecked, value)))) {
			flag = true;
		}

		return flag;
	}

	/****************************************************************************
	 ** 
	 ** Method: checkInteger Input: Element elementToBeChecked - element to be
	 * checked String value - value to be checked
	 ** 
	 ** Output: boolean result - indicates whether or not the request is valid
	 ** 
	 ** Description: This method checks to make sure the setValue passed in is a
	 * valid integer
	 ***************************************************************************/
	public boolean checkInteger(Element elementToBeChecked, String value) {
		boolean flag = false;

		try {
			Integer tmpInt = new Integer(value);
			int theValue = tmpInt.intValue();

			if ((theValue >= 0) && (theValue <= 65536)) {
				flag = true;
			}
		} catch (NumberFormatException nfe) {
			if (DebugIndicator.ON) {
				System.out.println(value
						+ "  has invalid format for an Integer");
			}
		}

		return flag;
	}

	/****************************************************************************
	 ** 
	 ** Method: checkSInteger() Input: Element elementToBeChecked - element to be
	 * checked String value - value to be checked
	 ** 
	 ** Output: boolean result - indicates whether or not the request is valid
	 ** 
	 ** Description: This method checks to make sure that the value passed in is
	 * a valid Signed Integer
	 ***************************************************************************/
	public boolean checkSInteger(Element elementToBeChecked, String value) {
		boolean flag = false;
		try {
			Integer tmpInt = new Integer(value);
			int theValue = tmpInt.intValue();

			if ((theValue >= -32768) && (theValue <= 32768)) {
				flag = true;
			}
		} catch (NumberFormatException nfe) {
			if (DebugIndicator.ON) {
				System.out.println(value
						+ " has invalid format for a Signed Integer");
			}
		}

		return flag;
	}

	/****************************************************************************
	 ** 
	 ** Method: checkTime() Input: Element elementToBeChecked - element to be
	 * checked String value - value to be checked
	 ** 
	 ** Output: boolean result - indicates whether or not the request is valid
	 ** 
	 ** Description: This method checks to make sure the value passed in is a
	 * valid Time HH:MM:SS.S
	 ***************************************************************************/
	public boolean checkTime(Element elementToBeChecked, String value) {
		boolean flag = false;
		StringTokenizer stk = new StringTokenizer(value, ":", false);

		// Should have 3 tokens: HH, MM, and SS.SS
		if (stk.countTokens() == 3) {
			String hour = stk.nextToken();
			String minute = stk.nextToken();
			String sec = stk.nextToken();

			if ((hour.length() == 2) && (minute.length() == 2)
					&& (sec.length() <= 5)) {
				// Try to convert hours and minutes to integers and seconds to
				// float.
				try {
					Integer hourAsInt = new Integer(hour);
					Integer minAsInt = new Integer(minute);

					if (((hourAsInt.intValue() >= 0) && (hourAsInt.intValue() <= 24))
							&& ((minAsInt.intValue() >= 0) && (minAsInt
									.intValue() <= 60))) {
						flag = true;

						// Try to convert seconds. If not, throw
						// NumberFormatException.
						// Validate Seconds, SS.SS
						// Try to convert seconds. If not, throw
						// NumberFormatException.
						StringTokenizer secTokenizer = new StringTokenizer(sec,
								".", false);
						int numTok = secTokenizer.countTokens();

						String secondsPart = secTokenizer.nextToken();

						if (DebugIndicator.ON) {
							System.out.println("secondsPart: " + secondsPart);
						}

						if (secondsPart.length() == 2) {
							Double secAsDouble = new Double(secondsPart);
							flag = true;
							if (DebugIndicator.ON) {
								System.out
										.println("seconds part had 2 chars and a valid decimal");
							}
						} else {
							flag = false;
							if (DebugIndicator.ON) {
								System.out
										.println("Seconds Part did not have 2 chars");
							}
						}
					}
				} catch (NumberFormatException nfe) {
					if (DebugIndicator.ON) {
						System.out
								.println("Value could not be converted to a time");
					}

					flag = false;
				}
			} // end if correct size
		} // end if correct tokens

		if (!flag) {
			if (DebugIndicator.ON) {
				System.out.println("Value being used for setting: " + value
						+ " is not in Valid Time Format (HH:MM:SS.SS)");
			}
		}

		return flag;
	}

	/****************************************************************************
	 ** 
	 ** Method: checkTimespan() Input: Element elementToBeChecked - element to be
	 * checked String value - value to be checked
	 ** 
	 ** Output: boolean result - indicates whether or not the request is valid
	 ** 
	 ** Description: This method checks to make sure the value passed in is a
	 * valid Timespan HHHH:MM:SS.S
	 ***************************************************************************/
	public boolean checkTimespan(Element elementToBeChecked, String value) {
		boolean flag = false;

		if (!(checkBlank(elementToBeChecked, value))) {
			StringTokenizer stk = new StringTokenizer(value, ":", false);

			if (stk.countTokens() == 3) {
				String hour = stk.nextToken();
				String minute = stk.nextToken();
				String sec = stk.nextToken();

				if (((hour.length() >= 2) && (hour.length() <= 4))
						&& (minute.length() == 2) && (sec.length() <= 5)) {
					// Check to see if the inputted value can be changed to a
					// "Timespan"
					try {
						// Try to convert hours and minutes. If not, throw
						// NumberFormatException.
						Integer hourAsInt = new Integer(hour);
						Integer minAsInt = new Integer(minute);

						// Make sure hours and minutes are in range
						if (((hourAsInt.intValue() >= 0) && (hourAsInt
								.intValue() <= 9999))
								&& ((minAsInt.intValue() >= 0) && (minAsInt
										.intValue() <= 99))) {
							flag = true;
						}

						// Validate Seconds, SS.SS
						// Try to convert seconds. If not, throw
						// NumberFormatException.
						StringTokenizer secTokenizer = new StringTokenizer(sec,
								".", false);
						int numTok = secTokenizer.countTokens();

						String secondsPart = secTokenizer.nextToken();

						if (DebugIndicator.ON) {
							System.out.println("secondsPart: " + secondsPart);
						}

						if (secondsPart.length() == 2) {
							Double secAsDouble = new Double(secondsPart);
							flag = true;
							if (DebugIndicator.ON) {
								System.out
										.println("seconds part had 2 chars and a valid decimal");
							}
						} else {
							flag = false;
							if (DebugIndicator.ON) {
								System.out
										.println("Seconds Part did not have 2 chars");
							}
						}

						// If numTok == 2 then there is a decimal part
						if (numTok == 2 && flag) {
							String decimalPart = secTokenizer.nextToken();
							if (decimalPart.length() <= 2) {
								Double decAsDouble = new Double(decimalPart);
								flag = true;
							} else {
								flag = true;
							}
						}

					} catch (NumberFormatException nfe) {
						if (DebugIndicator.ON) {
							System.out
									.println("Value could not be converted to a time");
						}

						// Invalid Date Format
						flag = false;
					}
				} // ends the check for appropriate size
			} // Ends if value has 3 tokens
		} else {
			flag = true;
		}

		if (!flag) {
			if (DebugIndicator.ON) {
				System.out.println("Value being used for setting: " + value
						+ " is not in Valid Time Format (HHHH:MM:SS.SS)");
			}
		}

		return flag;
	}

} // end of DataModelValidator

