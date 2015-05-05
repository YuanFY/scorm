/*******************************************************************************
**
** Filename:  CMIRequest.java
**
** File Description:  This class represents a request, either LMSGetValue() or
**          LMSSetValue().  The CMIRequest class is responsible for parsing
**          the string parameter passed into these API calls.  A CMIRequest is
**          made up of the following parts:
**
**          model: one and only one
**          base category: one and only one
**          subcategories: zero to many
**          array indices: zero to many
**          element: one and only one
**
**   Examples:
**      cmi.core.student_id
**        model - cmi
**        base category - core
**        element - student_id
**
**      cmi.student_data.tries.1.score.raw
**         model - cmi
**         base category - student_id
**         subcategory[0] - tries
**         subcategory[1] - score
**         index[0] - 1
**         element - raw
**
** Author: ADLI Project
**
** Company Name: Concurrent Technologies Corporation
**
** Module/Package Name: org.adl.datamodel.cmi
** Module/Package Description: Collection of CMI Data Model objects
**
** Design Issues:
**
** Implementation Issues:
** Known Problems:
** Side Effects:
**
** References: AICC CMI Data Model
**             ADL SCORM Version 1.0
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
** 08/07/2000     ADLI Team         Fixed a formatting problem in showRequest()
**
*******************************************************************************/
package org.adl.datamodels.cmi;

//native java imports
import java.io.*;
import java.util.*;

//adl imports
import org.adl.util.debug.*;

public class CMIRequest implements Serializable
{
   // The original LMSGetValue() or LMSSetValue() request un-tokenized
   private String request;

   // The model token of the request
   private String model;

   // The Base Category of the request
   private String baseCategory;

   // A collection of Sub-Categories of the request (if any)
   private int numSub = 10;
   private String[] subcategory;

   // The number of Sub-Categories collected from the request (if any)
   private int numOfSubCategories = 0;

   // A running total of the number of Sub-Categories returned (if any)
   private int numOfSubCatReturned = 0;

   // A collection of array indices collected from the request (if any)
   private int numInd = 10;
   private Integer[] index;

   // The number of array indices collected from the request (if any)
   private int numOfInd = 0;

   // A running total of the number of array indices returned (if any)
   private int numOfIndReturned = 0;

   // The Element of the request
   private String element;

   // The set value of the request (if LMSSetValue() request)
   private String setValue;

   // The total number of tokens found in the request.  If the request
   // is an LMSSetValue() the set value is not counted as a token
   private int totalNumberOfTokens = 0;

   // A running total of the number of tokens requested.  Initialized to
   // 1, because of the model.  Design does not ask for the model.
   private int tokensRequested = 1;

   // A running total of the number of tokes processed
   private int tokensProcessed = 0;

   // Boolean value indicating if this Request is for an LMSGetValue()
   private boolean getRequest;

   // Boolean value indicating if this Request is for an LMSSetValue()
   private boolean setRequest;

   // This attribute keeps track of the types of tokens processed
   // and keeps them in order for later use
   // 1 - model
   // 2 - base category
   // 3 - sub category
   // 4 - array index
   // 5 - element
   // 6 - value to use for set
   // cmi.core.student_id -- 1,2,5
   // cmi.student_data.tries.n.score.raw -- 1,2,3,4,3,5
   // Used to indicate what the token is
   private int numPat = 10;
   private Integer[] pattern;

   static private int MODEL = 1;
   static private int BASE_CATEGORY = 2;
   static private int SUB_CATEGORY = 3;
   static private int ARRAY_INDEX = 4;
   static private int ELEMENT = 5;
   static private int VALUE = 6;

   // initialize to 2 because the first two tokens would have
   // been processed before this value is used.
   static int pattern_counter = 2;

   // static method to increment the pattern_counter
   static void incrementPatternCount()
   {
      pattern_counter++;
   }

   // static method to reset the pattern_counter
   static void resetPatternCounter()
   {
      pattern_counter = 2;
   }


   /****************************************************************************
    **
    ** Method:  Constructor
    ** Input:   String theRequest - the request from the AU
    **          boolean getRequestFlag - flag indicating whether or not this
    **                                   request is for an LMSGetValue()
    ** Output:  none
    **
    ** Description: This constructor is responsible for creating the request
    **        object.  The constructor takes the input request
    **        (i.e. cmi.core.student_id) and parses the string into tokens.
    **
    ***************************************************************************/
   public CMIRequest(String theRequest,
                     boolean getRequestFlag)
   {
      if ( DebugIndicator.ON )
      {
         if ( getRequestFlag )
         {
             System.out.println("Building CMIRequest for a LMSGetValue(" +
                           theRequest + ") ");
         }
         else
         {
            System.out.println("Building CMIRequest for a LMSSetValue(" +
                            theRequest + ") ");
         }
      }

      // Set up initial values
      request = theRequest;
      model = new String("");
      baseCategory = new String("");
      element = new String("");
      setValue = new String("");

      getRequest = getRequestFlag;


      //***********************
      // Set up index array
      //**********************
      index = new Integer[numInd];

      // assign a value to each array element and print
      for ( int i = 0; i < index.length; i++ )
      {
         index[i] = new Integer(-1);
      }

      //***********************
      // Set up pattern array
      //**********************
      pattern = new Integer[numPat];

      // assign a value to each array element and print
      for ( int j = 0; j < pattern.length; j++ )
      {
         pattern[j] = new Integer(-1);
      }

      //**************************
      // Set up subcategory array
      //**************************
      subcategory = new String[numSub];

      // assign a value to each array element and print
      for ( int z = 0; z < subcategory.length; z++ )
      {
         subcategory[z] = new String("-1");
      }

      // used to hold the set value on an LMSSetValue()
      String theSetValue = new String("");

      // used to hold request
      String theNewRequest = new String("");

      // Check to see if this is a LMSGetValue() request
      if ( getRequest )
      {
         setRequest = false;
         parseGetRequest(theRequest);
      }
      else
      {
         setRequest = true;
         // Break up the request into into tokens
         StringTokenizer setTok = new StringTokenizer(theRequest, ",", false);
         int numTokensForSet =  setTok.countTokens();
         int numTokensProcessed = 0;

         theNewRequest = setTok.nextToken();
         numTokensProcessed++;

         if ( DebugIndicator.ON )
         {
            System.out.println("Request: [" + theNewRequest + "]");
         }

         if ( numTokensForSet == 1 )
         {
            theSetValue = new String("");
         }
         else
         {

            //Loop to build the value for a LMSSetValue()
            while (numTokensProcessed < numTokensForSet)
            {
               theSetValue += setTok.nextToken();
               numTokensProcessed++;

               if (numTokensProcessed != numTokensForSet)
               {
                   theSetValue += ",";
               }
            }

            if ( DebugIndicator.ON )
            {
               System.out.println("Set Value: [" + theSetValue + "]");
            }
         }

         totalNumberOfTokens++;
         parseSetRequest(theNewRequest,theSetValue);
      }

      if ( DebugIndicator.ON )
      {
         this.showRequest();
      }

   } // end of CMIRequest()

   /***************************************************************************
    **
    ** Method:  getModel()
    ** Input:   none
    ** Output:  String - data model used
    **
    ** Description:  Indicates the data model used
    **
    ***************************************************************************/
   public String getModel()
   {
      return model;
   }


   /***************************************************************************
    **
    ** Method:  isForASetRequest()
    ** Input:   none
    ** Output:  boolean setRequest
    **
    ** Description:  Indicates whether or not the current request is for
    **               an LMSSetValue() request
    **
    ***************************************************************************/
   public boolean isForASetRequest()
   {
      return setRequest;
   }

   /***************************************************************************
    **
    ** Method:  isAKeywordRequest()
    ** Input:   none
    ** Output:  boolean result
    **
    ** Description:  Indicates whether or not the current request is for
    **               keyword request--> _children, _count or _version
    **
    ***************************************************************************/
   public boolean isAKeywordRequest()
   {
      boolean result = false;
      if ( (isAChildrenRequest())  ||
           (isACountRequest()) ||
           (isAVersionRequest()) )
      {
         result = true;
      }
      return result;
   }

   /***************************************************************************
    **
    ** Method:  isAChildrenRequest()
    ** Input:   none
    ** Output:  boolean result
    **
    ** Description:  Indicates whether or not the current request is for
    **               an _children request
    **
    ***************************************************************************/
   public boolean isAChildrenRequest()
   {
      boolean result = false;
      if ( element.equals("_children") )
      {
         result = true;
      }
      return result;
   }

   /***************************************************************************
    **
    ** Method:  isACountRequest()
    ** Input:   none
    ** Output:  boolean result
    **
    ** Description:  Indicates whether or not the current request is for
    **               an _count request
    **
    ***************************************************************************/
   public boolean isACountRequest()
   {
      boolean result = false;
      if ( element.equals("_count") )
      {
         result = true;
      }
      return result;
   }

   /***************************************************************************
    **
    ** Method:  isAVersionRequest()
    ** Input:   none
    ** Output:  boolean result
    **
    ** Description:  Indicates whether or not the current request is for
    **               an _version request
    **
    ***************************************************************************/
   public boolean isAVersionRequest()
   {
      boolean result = false;
      if ( element.equals("_version") )
      {
         result = true;
      }
      return result;
   }
   /***************************************************************************
    **
    ** Method:  getRequest
    ** Input:   none
    ** Output:  String request - Original unparsed request
    **
    ** Description:  This method returns the original unparsed request
    **
    ***************************************************************************/
   public String getRequest()
   {
      return request;
   }

   /***************************************************************************
    **
    ** Method:  getBaseCategory
    ** Input:   none
    ** Output:  String baseCategory - the base category found in request
    **
    ** Description:  This method returns the Base Category
    **
    ***************************************************************************/
   public String getBaseCategory()
   {
      tokensRequested++;
      return baseCategory;
   }

   /***************************************************************************
    **
    ** Method:  getValue
    ** Input:   none
    ** Output:  String value - the set value
    **
    ** Description:  This method returns the Value to be used for setting.
    **
    ***************************************************************************/
   public String getValue()
   {
      return setValue;
   }

   /***************************************************************************
    **
    ** Method:  getElement
    ** Input:   none
    ** Output:  String value - the element
    **
    ** Description:  This method returns the Element of the request
    **
    ***************************************************************************/
   public String getElement()
   {
      return element;
   }

   /***************************************************************************
    **
    ** Method:  getTotalNumTokens
    ** Input:   none
    ** Output:  String totalNumberOfTokens - The total number of tokens
    **          in the request
    **
    ** Description:  This method returns the Total Number of Tokens
    **
    ***************************************************************************/
   public int getTotalNumTokens()
   {
      return totalNumberOfTokens;
   }

   /***************************************************************************
    **
    ** Method:  getNumSubCat()
    ** Input:   none
    ** Output:  int - the number of subcategories
    **
    ** Description:  This method returns the number of sub categories
    **               in the request
    **
    ***************************************************************************/
   public int getNumSubCat()
   {
      return numOfSubCategories;
   }

  /***************************************************************************
    **
    ** Method:  getNumIndices()
    ** Input:   none
    ** Output:  int - the number of indicies
    **
    ** Description:  This method returns the number of indices found
    **               in the request
    **
    ***************************************************************************/
   public int getNumIndices()
   {
      return numOfInd;
   }

   /***************************************************************************
    **
    ** Method:  getIndex()
    ** Input:   none
    ** Output:  Integer - the position in the array to find the index
    **
    ** Description:  This method returns the index found at the input position
    **
    ***************************************************************************/
   public Integer getIndex( int position )
   {
      return index[position];
   }

   /***************************************************************************
    **
    ** Method:  getSubCategory()
    ** Input:   int - which subcategory
    ** Output:  String - the subcategory
    **
    ** Description:  This method returns the subcategory found at the
    **               input position
    **
    ***************************************************************************/
   public String getSubCategory(int position)
   {
       String subCat = new String ("-1");

       if ( (position >=0) && (position < 10) )
       {
          subCat = subcategory[position];
       }

       return subCat;
   }

   /***************************************************************************
    **
    ** Method:  parseGetRequest
    ** Input:   StringTokenizer stk - String Tokenizer holding request
    ** Output:  none
    **
    ** Description:  This method performs the necessary steps to parse
    **               an LMSGetValue() request.
    **
    ***************************************************************************/
   private void parseGetRequest(String theRequest)
   {
      // Break up the request into tokens
      StringTokenizer stk = new StringTokenizer(theRequest, ".", false);

      // set the total number of tokens in the request
      totalNumberOfTokens = totalNumberOfTokens + stk.countTokens();

      // First token is the model
      model = stk.nextToken();
      tokensProcessed++;

      pattern[0] = ( new Integer(MODEL) );

      // Second token is the baseCategory
      baseCategory = stk.nextToken();
      tokensProcessed++;

      pattern[1] = (new Integer(BASE_CATEGORY));

      if ( stk.hasMoreTokens() )
      {
         // The only thing that can follow a category is
         // either another category (subcategory), array index,
         // or an element.

         // declare a boolean to be used to indicate when processing is done
         boolean done=false;

         // Loop until done
         while ( !done )
         {
            // Check to see if we are on the last token (ELEMENT)
            if ( onLastToken() )
            {
               // Last token to be processed, this is assigned
               // to the element attribute
               String temp = stk.nextToken();

               try
               {
                  // Try converting the token to an Integer. If this
                  // is successfull, then we are dealing with an array index
                  Integer tmpInt = new Integer(temp);
                  int tmpIndex = findNextIndexLoc();

                  // Store off the array index
                  index[tmpIndex] = tmpInt;

                  // Increment the number of indicies
                  numOfInd++;

                  int tmpIndex1 = findNextPatternLoc();

                  // Store off the array index pattern number
                  pattern[tmpIndex1] = ( new Integer(ARRAY_INDEX) );

                  // The last subcategory processed is the element.
                  // Use the numOfSubCategores as the base index.  Need
                  // to subtract 1 to get to the correct index.  Currently
                  // the only data model element that falls into this
                  // category is cmi.objectives.n.status.n
                  element = subcategory[numOfSubCategories - 1];

                  tmpIndex1 = findNextPatternLoc();
                  pattern[tmpIndex1] = ( new Integer(ELEMENT) );


               }
               catch ( NumberFormatException nfe )
               {
                  element = temp;
                  int tmpIndex = findNextPatternLoc();

                  pattern[tmpIndex] = ( new Integer(ELEMENT) );
               }

               // increment the tokens processed
               tokensProcessed++;

               done = true;
            }  // end of if onLastToken()
            else
            {
               // Check to see if next token is an array index
               // determineNextToken() will set index up if the element
               // being processed is an array index.
               //
               // cmi.objectives.n.id (index will be set to n on
               // return from method
               //
               // cmi.core.score.raw (subcategory1 or subcategory2
               // will be set to score on return from method
               determineNextToken(stk);
            }
         } // end of while
      }
      else
      {
          // Request only had two tokens: (i.e. "cmi.comments" or "cmi.suspend_data")
          // Need to set the element attribute to the base category
          element = baseCategory;
      }
   } // end parseGetRequest


   /***************************************************************************
   **
   ** Method:  parseSetRequest
   ** Input:   StringTokenizer stk - String Tokenizer holding request
   ** Output:  none
   **
   ** Description:  This method performs the necessary steps to parse
   **               an LMSSetValue() request.
   **
   ***************************************************************************/
   private void parseSetRequest(String theRequest,
                                String theSetValue)
   {
      if ( DebugIndicator.ON )
      {
         System.out.println("In CMIRequest::parseSetRequest");
      }

      // Break up the request into tokens
      StringTokenizer stk = new StringTokenizer(theRequest, ".", false);

      // set the total number of tokens in the request
      totalNumberOfTokens = totalNumberOfTokens + stk.countTokens();

      // First token is the model
      model = stk.nextToken();
      tokensProcessed++;

      pattern[0] = (new Integer(MODEL));

      // Second token is the baseCategory
      baseCategory = stk.nextToken();
      tokensProcessed++;

      pattern[1] = (new Integer(BASE_CATEGORY));

      if ( stk.hasMoreTokens() )
      {
         // The only thing that can follow a category is
         // either another category (subcategory), array index,
         // or an element.

         // declare a boolean to be used to indicate when processing is done
         boolean done=false;

         // Loop until done
         while ( !done )
         {
            // Check to see if we are on the last token (ELEMENT)
            if ( onLastToken() )
            {
               // Last token to be processed, this is assigned
               // to the element attribute
               setValue = theSetValue;
               tokensProcessed++;

               int tmpIndex = findNextPatternLoc();

               pattern[tmpIndex] = (new Integer(VALUE));
               done = true;
            }  // end of if onLastToken()
            else if ( (totalNumberOfTokens - tokensProcessed) == 2 )
            {
               element = stk.nextToken();
               tokensProcessed++;
               int tmpIndex = findNextPatternLoc();

               pattern[tmpIndex] = (new Integer(ELEMENT));
            }
            else
            {
               // Check to see if next token is an array index
               // determineNextToken() will set index up if the element
               // being processed is an array index.
               //
               // cmi.objectives.n.id (index will be set to n on
               // return from method
               //
               // cmi.core.score.raw (subcategory1 or subcategory2
               // will be set to score on return from method
               determineNextToken(stk);
            }
         } // end of while
      }
      else
      {
         // Last token to be processed, this is assigned
         // to the element attribute
         setValue = theSetValue;
         tokensProcessed++;
         int tmpIndex = findNextPatternLoc();

         pattern[tmpIndex] = (new Integer(VALUE));
      }

   } // end parseGetRequest

   /*****************************************************************************
    **
    ** Method:  determineNextToken
    ** Input:   StringTokenizer stk - String Tokenizer holding the request
    ** Output:  none
    **
    ** Description:  This method is used to determine the next token.  The
    **               method first attempts to see if the next token is an
    **               array element.  If so, the method stores off the array
    **               element.  If the next element is a subcategory,
    **               the method stores off the subcategory
    **
    ***************************************************************************/
   private void determineNextToken(StringTokenizer stk)
   {
      // Get the next token from the String Tokenizer
      String temp = stk.nextToken();

      // increment the number of tokens processed
      tokensProcessed++;

      // Check to see if the token is an array index or a
      // subcategory
      try
      {
         // Try converting the token to an Integer. If this
         // is successfull, then we are dealing with an array index
         Integer tmpInt = new Integer(temp);

         int tmpIndex = findNextIndexLoc();

         // Store off the array index
         index[tmpIndex] = tmpInt;

         // Increment the number of indicies
         numOfInd++;

         int tmpIndex1 = findNextPatternLoc();

         // Store off the array index pattern number
         pattern[tmpIndex1] = ( new Integer(ARRAY_INDEX) );
      }
      catch ( NumberFormatException nfe )
      {
         // Token could not be converted to a number
         // Dealing with a subcategory

         int tmpIndex = findNextSubLoc();

         // store off the subcategory
         subcategory[tmpIndex] = temp;

         // Increment the number of subcategories
         numOfSubCategories++;

         int tmpIndex1 = findNextPatternLoc();

         // Store off the subcategory pattern number
         pattern[tmpIndex1] = (new Integer(SUB_CATEGORY));
      }
   }  // end of determineNextToken()


   /****************************************************************************
    **
    ** Method:  onLastToken
    ** Input:   none
    ** Output:  boolean result indicating whether or not the code is on the
    **          last token
    **
    ** Description:  This method determines if we are on the last token
    **
    ***************************************************************************/
   private boolean onLastToken()
   {
      boolean rtrnFlag = false;

      if ( (totalNumberOfTokens - tokensProcessed) == 1 )
      {
         rtrnFlag = true;
      }
      return rtrnFlag;
   } // end of onLastToken()


   /***************************************************************************
   **
   ** Method:  getNextToken
   ** Input:   none
   ** Output:  String - next available token
   **
   ** Description: This method uses the pattern collection to determine
   **              the next (in order from original request) availabe
   **              token.
   **
   ***************************************************************************/
   public String getNextToken()
   {
      // Value to return
      String rtrnValue = new String("");

      try
      {
         Integer tmpPat = pattern[pattern_counter];
         // Check pattern counter to see what the next token will be
         int tmpPattern = tmpPat.intValue();

         // Determine the appropriate value to return based on the pattern
         if ( tmpPattern == SUB_CATEGORY )
         {
            try
            {
               // Dealing with sub categories
               rtrnValue = subcategory[numOfSubCatReturned];
               numOfSubCatReturned++;
            }
            catch ( IndexOutOfBoundsException ioobe )
            {
               System.out.println(ioobe);
            }
         }
         else if ( tmpPattern == ARRAY_INDEX )
         {
            try
            {
               Integer tmpIndex = index[numOfIndReturned];
               numOfIndReturned++;
               // Dealing with array indexes
               rtrnValue = tmpIndex.toString();

            }
            catch ( IndexOutOfBoundsException ioobe )
            {
               System.out.println(ioobe);
            }
         }
         else if ( tmpPattern == ELEMENT )
         {
            // Dealing with elements
            rtrnValue = element;

            // on last token (element) reset static pattern
            // count
            resetPatternCounter();
         }
         else if ( tmpPattern == VALUE )
         {
            // Dealing with set values
            rtrnValue = setValue;
         }

         // If the pattern that is being processed is not an element
         // increment the static pattern count
         if ( tmpPattern != ELEMENT )
         {
            incrementPatternCount();
         }
         tokensRequested++;
      }
      catch ( IndexOutOfBoundsException ioobe )
      {
         System.out.println(ioobe);
      }

      return rtrnValue;
   }  // end of getNextToken()

   /***************************************************************************
   **
   ** Method:  hasMoreTokensToProcess()
   ** Input:   none
   ** Output:  boolean returnValue - indicates how many tokens are left
   **          to process
   **
   ** Description:  Determines if there are more tokens left to process
   **
   ***************************************************************************/
   public boolean hasMoreTokensToProcess()
   {
      boolean rtrnFlag = true;

      // If dealing with LMSSetValue() request don't count the setValue
      if ( setRequest )
      {
         if ( ((totalNumberOfTokens - 1) - tokensRequested) == 0 )
         {
            rtrnFlag = false;
         }
      }
      else
      {
         if ( (totalNumberOfTokens - tokensRequested) == 0 )
         {
            rtrnFlag = false;
         }
      }

      return rtrnFlag;
   } // end of hasMoreTokensToProcess()

   /***************************************************************************
    **
    ** Method:  done
    ** Input:   none
    ** Output:  none
    **
    ** Description:  This method is invoked when the processing of a request
    **               is finished.  It resets counters used in processing
    **
    ***************************************************************************/
   public void done()
   {
      resetPatternCounter();
      tokensRequested=1;
      numOfSubCatReturned = 0;
      numOfIndReturned = 0;
      return;
   }

   /***************************************************************************
    **
    ** Method:  findNextPatternLoc
    ** Input:   none
    ** Output:  int - The next available position in the pattern array
    **
    ** Description:  Determines the next available position in the pattern
    **               array and returns it to the caller
    **
    ***************************************************************************/
   private int findNextPatternLoc()
   {
      int result = -1;
      int value = -1;

      for ( int i = 0; i <= numPat; i++ )
      {
         if ( value == pattern[i].intValue() )
         {
            result = i;
            break;
         }
      }

      return result;
   }

   /***************************************************************************
    **
    ** Method:  findNexIndexLoc
    ** Input:   none
    ** Output:  int - The next available position in the index array
    **
    ** Description:  Determines the next available position in the index
    **               array and returns it to the caller
    **
    ***************************************************************************/
   private int findNextIndexLoc()
   {
      int result = -1;
      int value = -1;

      for ( int i = 0; i <= numInd; i++ )
      {
         if ( value == index[i].intValue() )
         {
            result = i;
            break;
         }
      }

      return result;
   }

   /***************************************************************************
    **
    ** Method:  findNexSubLoc
    ** Input:   none
    ** Output:  int - The next available position in the subcategory array
    **
    ** Description:  Determines the next available position in the subcategory
    **               array and returns it to the caller
    **
    ***************************************************************************/
   private int findNextSubLoc()
   {
      int result = -1;
      String tmpString = new String("-1");
      for ( int i = 0; i <= numSub; i++ )
      {
         if ( subcategory[i].equalsIgnoreCase(tmpString) )
         {
            result = i;
            break;
         }
      }

      return result;
   }

  /***************************************************************************
   **
   ** Method:  showRequest()
   ** Input:   none
   ** Output:  none
   **
   ** Description:  Sends important information about the request to System.out
   **
   **
   ***************************************************************************/
   public void showRequest()
   {
      if ( DebugIndicator.ON )
      {
          System.out.println("Base Category: " + baseCategory);
          System.out.println("Element: " + element);
          System.out.println("Model: " + model);
          System.out.println("Number of Indices: " + numOfInd);
          System.out.println("Number of SubCategories: " + numOfSubCategories);

          for ( int i=0; i < numOfSubCategories; i++)
          {
             System.out.println("Subcategory[" + i + "]: " + subcategory[i]);
          }

          for ( int j=0; j < numOfInd; j++)
          {
             System.out.println("Index[" + j + "]: " + index[j]);
          }

          for ( int x=0; x < numPat; x++)
          {
             if ( pattern[x].intValue() > -1 )
             {
                System.out.println("Pattern[" + x + "]: " + pattern[x]);
             }
          }

          if ( this.isForASetRequest() )
          {
             System.out.println("Set Value: " + setValue);
          }
      }

   }

} // end of CMIRequest
