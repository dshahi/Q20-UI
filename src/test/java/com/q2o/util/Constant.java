package com.q2o.util;



public class Constant {

	public static String  PROPERTIES_FILE_PATH =System.getProperty("user.dir")+"\\src\\test\\resources\\project.properties";
	public static String XLS_FILE_LOCATION=System.getProperty("user.dir")+"\\src\\test\\java\\com\\q2o\\xls\\";
	public static String RESOURCE_FOLDER_LOCATION=System.getProperty("user.dir")+"\\src\\test\\resources\\";
	public static String CHROMEDRIVER_EXE_PATH=RESOURCE_FOLDER_LOCATION+"chromedriver.exe";
	public static String IEDRIVER_EXE_PATH=RESOURCE_FOLDER_LOCATION+"IEDriverServer.exe";
	public static String CapturedScreeshotPath=System.getProperty("user.dir")+"\\Screenshots\\";
	public static String AUTOITEXEFILE_LOCAION=System.getProperty("user.dir")+"\\src\\test\\resources\\partnerImage.exe";
	
	
	//Excel files
	public static  String Suite_XLS_File="Suites.xlsx";

  
	public static String First_Suite="suiteCCW";
	public static String Second_Suite="suiteSFDC";
	public static String First_Suite_Xls_File=First_Suite+".xlsx";
	public static String Second_Suite_Xls_File=Second_Suite+".xlsx";
			
	public static String Suite_Sheet="Test_Suites";
	public static String SuiteNameCoLumn="Test_Suites_Name";
	public static String SuiteRummodeColumnName="Runmode";
	public static String RUNMODE_YES="Y";
	public static String RUNMODE_NO="N";
	
	public static String TestcaseSheetName="TestCases";
	public static String TestCasesNameCoLumn="TestCases_Name";
	public static String Browser_ColName="Browser";
	public static String URL_COL="URL";
	public static String TestRummodeColumnName="Runmodes";
	public static String USER_ID_COL="userid";
	public static String PASSWORD_COL="password";
	public static String DEALNAME_COL="DealName";
	public static String CAM_COL="CAM";
	public static String ENDCUSTOMER_COL="EndCustomer";
	
	public static String ACCOUNTNAME_COL="Account_Name";
	public static String EXPECTED_PROD_AMT_COL="Exp_Prod_Amt";
	public static String STAGE_COL="Stage";
	public static String INSTALLBASE_COL="InstallBase";
	public static String PARTNER_COMPANY_COL="Partner_Company";
	public static String FORCASTING_POS_COL="Forcasting_Position";
			
			
			
	public static String COMP1_COL="Competitor_1";
    public static String COMP2_COL="Competitor_2";
    public static String INTENDEDUSE_COL="IntendedUse";
    public static String PRICELIST_COL="PriceList";
    public static String DEALCATEGORY_COL="DealCategory";
    public static String BUYMETHOD_COL="BuyMethod";
    public static String ASTQUOTE_COL="ASQuoteNumber";
    public static String ASPTQUOTETYPE_COL="ASPTQuoteType";
    
		
	
	public static String DATA_SHEET="Data";
	
	//Error
    public static final String BROWSER_ERROR="Fail To Lunch Browser";
    public static final String BROWSER_INCORRECT = "Browser name incorrect";
   
   public static final String LoginPage_tile="Cisco.com Login Page";
   public static final String WorkspaceHomePage="Cisco Commerce";
   public static final String NAVIGATION_ERROR = "ERROR - FAILED TO NAVIGATE TO BASE URL";
   public static final String INVALID_SELECTOR = "ERROR - Invalid selector given in properties file  - ";
   public static final String INVALID_SELECTOR_EXCEPTION = "ERROR - Selector was not given correctly - ";
   public static final String ELEMENT_NOT_FOUND="Failure - No such Element present";
   public static final String FAIL_TO_FOUND_DISPLAY = "Expected element No displayed";
	
   public static final String DEFFAULT_VALUE="TEST";
   public static final String TECHMIXDEFAULT="100";
   
   
   //SFDC
   
   public static final String LOGINPAGE_TITLE="salesforce.com - Customer Secure Login Page";
   public static final String SFDCHOMEPAGE_TITLE="salesforce.com - Unlimited Edition";
   public static final String ACCOUNTPOPUP_TITLE="Search ~ salesforce.com - Unlimited Edition";
}
