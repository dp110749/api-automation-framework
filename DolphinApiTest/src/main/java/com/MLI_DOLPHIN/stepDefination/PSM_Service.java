package com.MLI_DOLPHIN.stepDefination;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import org.hamcrest.Matchers;
import com.MLI_DOLPHIN.baseclass.WebservicesMethod;
import com.MLI_DOLPHIN.utilities.ReusableFunction;
import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import junit.framework.Assert;

public class PSM_Service {

	private final static Logger logger = Logger.getLogger(PSM_Service.class.getName());
	
	private int getSecondRow;
	private String endPointUrl;
	private String header;
	private String requestFile;
	private String correlarionID;
	private String income;
	private String need;
	private String channel;
	private String planGroup;
	private String productfamily;
	private String insurdAge;
	private String isPremiumHalfOfInsured;
	private String isFrom2;
	private String isInsuredAge18;
	private String isInsuredTakenRider;
	private String stp;
	private String irpScore;
	private String dfa;
	private String lifeCycleBase;
	private String triggerBase;
	private String growthFund;
	private String highGrowthFund;
	private String superGrowthFund;
	private String balancedFund;
	private String requestBody;
	private Response responseBody;
	
	
	@Given("^Set the data in request$")
	public void set_the_data_in_request(DataTable preRequestData) throws Throwable {
		List<String> listOfPreData =preRequestData.asList(String.class);
		List<List<String>>numberOfRow =preRequestData.raw();
        getSecondRow=listOfPreData.size()/numberOfRow.size();
        for(int i=getSecondRow;i<listOfPreData.size();i++){
        	endPointUrl=listOfPreData.get(i);
        	i++;
        	header=listOfPreData.get(i);
        	i++;
        	requestFile=listOfPreData.get(i);
        	i++;
        	correlarionID=listOfPreData.get(i);
        	i++;
        	income=listOfPreData.get(i);
        	i++;
        	need=listOfPreData.get(i);
        	i++;
        	channel=listOfPreData.get(i);
        	i++;
        	planGroup=listOfPreData.get(i);
        	i++;
        	productfamily=listOfPreData.get(i);
        	i++;
        	insurdAge=listOfPreData.get(i);
        	i++;
        	isPremiumHalfOfInsured=listOfPreData.get(i);
        	i++;
        	isFrom2=listOfPreData.get(i);
        	i++;
        	isInsuredAge18=listOfPreData.get(i);
        	i++;
        	isInsuredTakenRider=listOfPreData.get(i);
        	i++;
        	stp=listOfPreData.get(i);
        	i++;
        	irpScore=listOfPreData.get(i);
        	i++;
        	dfa=listOfPreData.get(i);
        	i++;
        	lifeCycleBase=listOfPreData.get(i);
        	i++;
        	triggerBase=listOfPreData.get(i);
        	i++;
        	growthFund=listOfPreData.get(i);
        	i++;
        	highGrowthFund=listOfPreData.get(i);
        	i++;
        	superGrowthFund=listOfPreData.get(i);
        	i++;
        	balancedFund=listOfPreData.get(i);
        	
        }
       
	}
	
	public String getRequestBody(){
		try {
			requestBody=ReusableFunction.readJsonFile(requestFile);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		requestBody=requestBody.replaceAll(Pattern.quote("{{"+"X-Correlation-ID"+"}}"), correlarionID);
		requestBody=requestBody.replaceAll(Pattern.quote("{{"+"income"+"}}"),income);
		requestBody=requestBody.replaceAll(Pattern.quote("{{"+"need"+"}}"), need);
		requestBody=requestBody.replaceAll(Pattern.quote("{{"+"channel"+"}}"), channel);
		requestBody=requestBody.replaceAll(Pattern.quote("{{"+"ageOfInsured"+"}}"), insurdAge);
		requestBody=requestBody.replaceAll(Pattern.quote("{{"+"planGroup"+"}}"), planGroup);
		requestBody=requestBody.replaceAll(Pattern.quote("{{"+"productFamily"+"}}"), productfamily);
		requestBody=requestBody.replaceAll(Pattern.quote("{{"+"isPremHalfOfIncome"+"}}"), isPremiumHalfOfInsured);
		requestBody=requestBody.replaceAll(Pattern.quote("{{"+"isForm2"+"}}"), isFrom2);
		requestBody=requestBody.replaceAll(Pattern.quote("{{"+"isInsuredAgeLessThan18"+"}}"), isInsuredAge18);
		requestBody=requestBody.replaceAll(Pattern.quote("{{"+"isPayorRiderTaken"+"}}"), isInsuredTakenRider);
		requestBody=requestBody.replaceAll(Pattern.quote("{{"+"stp"+"}}"), stp);
		requestBody=requestBody.replaceAll(Pattern.quote("{{"+"irpScore"+"}}"), irpScore);
		requestBody=requestBody.replaceAll(Pattern.quote("{{"+"dfa"+"}}"), dfa);
		requestBody=requestBody.replaceAll(Pattern.quote("{{"+"lifeCycleBased"+"}}"), lifeCycleBase);
		requestBody=requestBody.replaceAll(Pattern.quote("{{"+"triggerBased"+"}}"), triggerBase);
		requestBody=requestBody.replaceAll(Pattern.quote("{{"+"growthFund"+"}}"), growthFund);
		requestBody=requestBody.replaceAll(Pattern.quote("{{"+"highGrowthFund"+"}}"), highGrowthFund);
		requestBody=requestBody.replaceAll(Pattern.quote("{{"+"growthSuperFund"+"}}"), superGrowthFund);
		requestBody=requestBody.replaceAll(Pattern.quote("{{"+"balanceFund"+"}}"), balancedFund);
		
		logger.info("Set the prerequest Successfully..");
		return requestBody;
	}

	@When("^Lets Send the POST request for PSM$")
	public void lets_Send_the_POST_request_for_PSM() throws Throwable {
       responseBody=WebservicesMethod.POST_METHOD(endPointUrl, getRequestBody(), ReusableFunction.requestHeaders(header));
       logger.info("response Body is ::"+responseBody.prettyPrint());
	}

	@Then("^Lets valiate the response status code\"([^\"]*)\"$")
	public void lets_valiate_the_response_status_code(String inputStatusCode) throws Throwable {
         Assert.assertEquals(String.valueOf(responseBody.getStatusCode()),inputStatusCode);
		 logger.info("Validation of status code successfully..");
	}

	@Then("^Lets Validate the response message code\"([^\"]*)\"$")
	public void lets_Validate_the_response_message_code(String inputMsgCode) throws Throwable {
		responseBody.then().root("response.msgInfo").body("msgCode", Matchers.equalTo(inputMsgCode));
		logger.info("Validation of Msg code is successfully..");
		
	}

	@Then("^Lets Validate teh response Message\"([^\"]*)\"$")
	public void lets_Validate_teh_response_Message(String inputMsg) throws Throwable {
		responseBody.then().root("response.msgInfo").body("msg", Matchers.equalTo(inputMsg));
		logger.info("Validation of Message is successfully..");
	}
	
	@Then("^Lets validate the psmRecommendationCheck\"([^\"]*)\"$")
	public void lets_Validate_psmRecomendationUotPut(String psmRecomendationCheck) throws Throwable {
		responseBody.then().root("response.payload").body("psmRecommendationCheck", Matchers.equalTo(psmRecomendationCheck));
		logger.info("Validation of psmRecomendationCheck is successfully..");
	}
	@Given("^Lets Set the income of Insurd\"([^\"]*)\"$")
	public void lets_Set_the_income_of_Insurd(String inputInsurdIncome) throws Throwable {
         income=inputInsurdIncome;
         logger.info("User set the insurd Age is :"+insurdAge);
	}

	@Given("^Lets Set the channle\"([^\"]*)\"$")
	public void lets_Set_the_channle(String inputChannel) throws Throwable {
      channel=inputChannel;
      logger.info("User set the Channel is :"+channel);
	}

	@Given("^Lets Set the plangroup\"([^\"]*)\"$")
	public void lets_Set_the_plangroup(String inputPlanGroup) throws Throwable {
		planGroup = inputPlanGroup;
		logger.info("User set the insurd Age is :" + planGroup);
	}

	@Given("^Lets Set the product Family\"([^\"]*)\"$")
	public void lets_Set_the_product_Family(String inpuProdcutFamily) throws Throwable {
		productfamily=inpuProdcutFamily;
		logger.info("User set the insurd Age is :"+productfamily);
	}

	@Given("^Lets Set the insurdAge\"([^\"]*)\"$")
	public void lets_Set_the_insurdAge(String inputInsurdAge) throws Throwable {
		insurdAge=inputInsurdAge;
		logger.info("User set the insurd Age is :"+insurdAge);
	}

	@Given("^Lets Set the need\"([^\"]*)\"$")
	public void lets_Set_the_need(String inputNeed) throws Throwable {
		need = inputNeed;
		logger.info("User set the need is :" + need);

	}

	@Given("^Lets Set the Insured income Half\"([^\"]*)\"$")
	public void lets_Set_the_Insured_income_Half(String inputIsPremiumComeHalf) throws Throwable {
		isPremiumHalfOfInsured = inputIsPremiumComeHalf;
		logger.info("User set the isPremiumhalofInsuredIncome is :" + isPremiumHalfOfInsured);
	}

	@Given("^Lets Set the isForm\"([^\"]*)\"$")
	public void lets_Set_the_isForm(String inputIsForm2) throws Throwable {
		isFrom2 = inputIsForm2;
		logger.info("User set the ISform2 :" + isFrom2);
	}

	@Given("^Lets Set the Is Insured taken Rider\"([^\"]*)\"$")
	public void lets_Set_the_Is_Insured_taken_Rider(String inputInsuredTakenRider) throws Throwable {
		isInsuredTakenRider = inputInsuredTakenRider;
		logger.info("User set the isInsuredTakenRider is :" + isInsuredTakenRider);
	}

	@Given("^Lets Set the Is insured age\"([^\"]*)\"$")
	public void lets_Set_the_Is_insured_age(String inputIsInsuredAge18) throws Throwable {
		isInsuredAge18 = inputIsInsuredAge18;
		logger.info("User set the isInsuredAge18 is :" + isInsuredAge18);
	}

	@Then("^Lets validate the payorIncomeCheck\"([^\"]*)\"$")
	public void lets_validate_the_payorIncomeCheck(String inputPayorIncomeCheck) throws Throwable {
		responseBody.then().root("response.payload").body("psmIncomeCheck", Matchers.equalTo(inputPayorIncomeCheck));
		logger.info("Validation of psmIncomeCheck is successfully..");

	}

	@Then("^Lets validate the PSMPayorCheck\"([^\"]*)\"$")
	public void lets_validate_the_PSMPayorCheck(String inputRecomendationCheck) throws Throwable {
		responseBody.then().root("response.payload").body("psmPayorCheck", Matchers.equalTo(inputRecomendationCheck));
		logger.info("Validation of PSMPayorCheck is successfully..");
	}
	
	@Given("^Lets Set the Stp value\"([^\"]*)\"$")
	public void lets_Set_the_Stp_value(String inpuStp) throws Throwable {
		stp = inpuStp;
		logger.info("User set the stp value is :" + stp);

	}

	@Given("^Lets Set the irpScore\"([^\"]*)\"$")
	public void lets_Set_the_irpScore(String inputIrpScore) throws Throwable {
		irpScore=inputIrpScore;
		logger.info("User set the irpscore is  :" + irpScore);
	}

	@Then("^Lets Validate the PsmFundCheck\"([^\"]*)\"$")
	public void lets_Validate_the_PsmFundCheck(String expPsmFundCheck) throws Throwable {
		responseBody.then().root("response.payload").body("psmIRPFundCheck", Matchers.equalTo(expPsmFundCheck));
		logger.info("Validation of PSMFundheck is successfully..");

	}

	@Then("^Lets Validate the RiskProfile\"([^\"]*)\"$")
	public void lets_Validate_the_RiskProfile(String expRsikProfile) throws Throwable {
		responseBody.then().root("response.payload").body("riskProfile", Matchers.equalTo(expRsikProfile));
		logger.info("Validation of PSM Risk Profile is successfully..");

	}
	
	@Given("^Lets Set the DFA value\"([^\"]*)\"$")
	public void lets_Set_the_DFA_value(String inputDfa) throws Throwable {
		dfa=inputDfa;
		logger.info("User set the dfa is  :" + dfa);

	}

	@Given("^Lets Set the LifeCycleBase value\"([^\"]*)\"$")
	public void lets_Set_the_LifeCycleBase_value(String inputLifeCycle) throws Throwable {
		lifeCycleBase=inputLifeCycle;
		logger.info("User set the lifeCycleBase is  :" + lifeCycleBase);
	}

	@Given("^Lets Set the TriggerBase value\"([^\"]*)\"$")
	public void lets_Set_the_TriggerBase_value(String inputTriggerBase) throws Throwable {
		triggerBase=inputTriggerBase;
		logger.info("User set the triggerBase is  :" + triggerBase);
	}

	@Given("^Lest Set the FundGrowth value\"([^\"]*)\"$")
	public void lest_Set_the_FundGrowth_value(String inputFundGrowth) throws Throwable {
		growthFund=inputFundGrowth;
		logger.info("User set the fund Growth is  :" + growthFund);	
	}

	@Given("^Lest Set the HighFundGrowth value\"([^\"]*)\"$")
	public void lest_Set_the_HighFundGrowth_value(String inputHighFundGrowth) throws Throwable {
		highGrowthFund=inputHighFundGrowth;
		logger.info("User set the High fund Growth is  :" + highGrowthFund);	
	}

	@Given("^Lest Set the SuperFundGrowth value\"([^\"]*)\"$")
	public void lest_Set_the_SuperFundGrowth_value(String inputSuperFundGrowth) throws Throwable {
		superGrowthFund=inputSuperFundGrowth;
		logger.info("User set the Suoerfund Growth is  :" + superGrowthFund);	

	}
	@Given("^Lest Set the Balanced Fund Value\"([^\"]*)\"$")
	public void lest_Set_the_Balanced_Fund_Value(String inputBalancedFund) throws Throwable {
		balancedFund=inputBalancedFund;
		logger.info("User set the balancedFund is  :" + balancedFund);	

	}
	@Given("^Lets set the correlation id for PSM\"([^\"]*)\"$")
	public void lest_Set_the_CorrelationID(String inputCorrelationId) throws Throwable {
		correlarionID=inputCorrelationId;
		logger.info("User set the corrleationID is  :" + correlarionID);	

	}
	@Given("^Lets set the endPointUrlfor PSM\"([^\"]*)\"$")
	public void lest_Set_the_EndPointUrl(String inputEndPointUrl) throws Throwable {
		endPointUrl=inputEndPointUrl;
		logger.info("User set the endPoint url is  :" + endPointUrl);	

	}





}
