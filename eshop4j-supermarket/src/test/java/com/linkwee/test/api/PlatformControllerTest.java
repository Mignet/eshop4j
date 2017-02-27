package com.linkwee.test.api;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.web.util.HtmlUtils;

import com.linkwee.api.request.crm.BindOrgAcctRequest;
import com.linkwee.api.request.crm.PlatformManagerListRequest;
import com.linkwee.api.response.cim.ProductRecommendChooseResponse;
import com.linkwee.core.base.api.BaseResponse;
import com.linkwee.core.util.ApplicationUtils;
import com.linkwee.test.BaseTest;
import com.linkwee.test.TestHelper;
import com.linkwee.test.enums.AppEnum;
import com.linkwee.test.enums.PathEnum;
import com.linkwee.web.enums.PlatformEnum;
import com.linkwee.web.request.OrgInfoRequest;
import com.linkwee.web.request.orgInfo.OrgDetailRequest;
import com.linkwee.web.request.orgInfo.OrgJumpRequest;
import com.linkwee.web.request.orgInfo.OrgRecommendByChooseRequest;
import com.linkwee.web.response.orgInfo.InvestmentStrategyResponse;
import com.linkwee.web.service.CimOrginfoService;
import com.linkwee.xoss.util.AppUtils;

public class PlatformControllerTest extends BaseTest{

	@Resource
	private CimOrginfoService cimOrginfoService;
	
	/*@Test
	public void test() throws Exception {
		start();
		List<CimOrginfo> findRecommendOrg = cimOrginfoService.findRecommendOrg();
		System.out.println(findRecommendOrg);
		end();
		log();
	}*/
	
	/**
	 * 查询理财师给投资客户推荐的平台
	 * @author yalin 
	 * @date 2016年7月18日 下午1:49:58  
	 * @throws Exception
	 */
	@Test
	public void queryPlannerRecommendPlatfrom() throws Exception {
		//App_investor_android
		OrgInfoRequest req = new OrgInfoRequest();
		//BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_IOS,this.getUrl(),"/api/platfrom/highQualityPlatform",this.getToken(),Map.class,req);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_WEB,this.getUrl(),"/api/platfrom/queryPlannerRecommendPlatfrom",this.getToken(),Map.class,req);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 优质平台
	 * @author yalin 
	 * @date 2016年7月18日 下午1:49:58  
	 * @throws Exception
	 */
	@Test
	public void testHighQualityPlatform() throws Exception {
		//App_investor_android
		OrgInfoRequest req = new OrgInfoRequest();
		//req.setPageSize(4);
		//BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_IOS,this.getUrl(),"/api/platfrom/highQualityPlatform",this.getToken(),Map.class,req);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_WEB,this.getUrl(),"/api/platfrom/highQualityPlatform",this.getToken(),Map.class,req);
		logger.debug(baseResponse.toString());
	}
	//eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE0Njg2NjI4NDM3NTYsInN1YiI6IjVlNTQzZDNhZGJjNDRjZTQ4OTFlNTAwOGMzY2I3NTNiIiwiaXNzIjoiaHR0cHM6XC9cL3d3dy5saW5rd2VlLmNvbSJ9.L-IrUa4xrXwBXP9iwtW_gQ52tR543LIks7HEB5rJVpg
	
	/**
	 * PC端 最新入驻机构
	 * @author yalin 
	 * @date 2016年7月18日 下午1:49:58  
	 * @throws Exception
	 */
	@Test
	public void testQueryLatestOrg() throws Exception {
		//App_investor_android
		OrgInfoRequest req = new OrgInfoRequest();
		//BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_IOS,this.getUrl(),"/api/platfrom/highQualityPlatform",this.getToken(),Map.class,req);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_WEB,this.getUrl(),"/api/platfrom/queryLatestOrg",this.getToken(),Map.class,req);
		logger.debug(baseResponse.toString());
	}
	/**
	 * 机构动态
	 * @author yalin 
	 * @date 2016年7月18日 下午1:49:58  
	 * @throws Exception
	 */
	@Test
	public void queryOrgDynamicInfo() throws Exception {
		//App_investor_android
		OrgInfoRequest req = new OrgInfoRequest();
		//BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_IOS,this.getUrl(),"/api/platfrom/highQualityPlatform",this.getToken(),Map.class,req);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_IOS,this.getUrl(),"/api/platfrom/queryOrgDynamicInfo",this.getToken(),Map.class,req);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 机构安全保障
	 * @author yalin 
	 * @date 2016年7月18日 下午1:49:58  
	 * @throws Exception
	 */
	@Test
	public void queryOrgSecurity() throws Exception {
		//App_investor_android
		OrgInfoRequest req = new OrgInfoRequest();
		//BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_IOS,this.getUrl(),"/api/platfrom/highQualityPlatform",this.getToken(),Map.class,req);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_IOS,this.getUrl(),"/api/platfrom/queryOrgSecurity",this.getToken(),Map.class,req);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 平台列表
	 * @author yalin 
	 * @date 2016年7月18日 下午1:50:08  
	 * @throws Exception
	 */
	@Test
	public void testPlatformPageList() throws Exception {
		OrgInfoRequest req = new OrgInfoRequest();
		//req.setPageIndex(2);//第2页
		//req.setSecurityLevel(">=4");
		//req.setBackground("上市公司");
		//req.setCity("other");
		//req.setYearProfit("12,16");
		//BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_IOS,this.getUrl(),"/api/platfrom/pageList",this.getToken(),Map.class,req);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_WEB,this.getUrl(),"/api/platfrom/pageList",this.getToken(),Map.class,req);
		//System.out.println(baseResponse.toString());
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * PC端T呗平台信息详情
	 * @author yalin 
	 * @date 2016年7月18日 下午1:50:14  
	 * @throws Exception
	 */
	@Test
	public void testPcOrgDetail() throws Exception {
		OrgDetailRequest req = new OrgDetailRequest();
		req.setOrgNo("OPEN_XIAONIUZAIXIAN_WEB");
		//req.setOrgNo("HUANG");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_WEB,this.getUrl(),"/api/platfrom/pcOrgDetail",this.getToken(),Map.class,req);
		//BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_IOS,this.getUrl(),"/api/platfrom/detail",this.getToken(),Map.class,req);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 平台信息详情
	 * @author yalin 
	 * @date 2016年7月18日 下午1:50:14  
	 * @throws Exception
	 */
	@Test
	public void testOrgDetail() throws Exception {
		OrgDetailRequest req = new OrgDetailRequest();
		req.setOrgNo("OPEN_XIAONIUZAIXIAN_WEB");
		//req.setOrgNo("HUANG");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_IOS,this.getUrl(),"/api/platfrom/detail",this.getToken(),Map.class,req);
		//BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_IOS,this.getUrl(),"/api/platfrom/detail",this.getToken(),Map.class,req);
		logger.debug(baseResponse.toString());
	}
	/**
	 * 平台信息筛选条件
	 * @author yalin 
	 * @date 2016年7月18日 下午1:50:14  
	 * @throws Exception
	 */
	@Test
	public void testPlatformHead() throws Exception {
		OrgDetailRequest req = new OrgDetailRequest();
		//req.setOrgNo("8888888");
		//BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_IOS,this.getUrl(),"/api/platfrom/platformHead",this.getToken(),Map.class,req);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_WEB,this.getUrl(),"/api/platfrom/platformHead",this.getToken(),Map.class,req);
		logger.debug(baseResponse.toString());
	}
	
	
	@Test
	public void testPlatformSaleProducts() throws Exception {
		OrgDetailRequest req = new OrgDetailRequest();
		req.setOrgNo("7777777");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_IOS,this.getUrl(),"/api/platfrom/platformSaleProducts",this.getToken(),Map.class,req);
		logger.debug(baseResponse.toString());
	}
	
	@Test
	public void testGetOrgProductUrl() throws Exception {
		OrgJumpRequest req = new OrgJumpRequest();
		req.setOrgNo("OPEN_TOUCHOU_WEB");
		req.setProductId("6E48BA3A848748E4B13C336B243F600C");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_IOS,this.getUrl(),"/api/platfrom/getOrgProductUrl",this.getToken(),Map.class,req);
		logger.debug(baseResponse.toString());
	}
	
	@Test
	public void testGetOrgUserCentertUrl() throws Exception {
		OrgDetailRequest req = new OrgDetailRequest();
		req.setOrgNo("OPEN_TOUCHOU_WEB");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_IOS,this.getUrl(),"/api/platfrom/getOrgUserCenterUrl",this.getToken(),Map.class,req);
		logger.debug(baseResponse.toString());
	}
	
	public static void main(String[] args) {
		 Map<String, Object> m = new HashMap<String, Object>();
		 m.put("haha", "dfdsf2222sdf");
		 String sss = (String) m.get("haha");
		 System.out.println(sss);
		
		/** HTML转义 **/  
		String s = HtmlUtils.htmlEscape("<div>hello world</div><p>&nbsp;</p>");  
		System.out.println(s);  
		//JavaScriptUtils.javaScriptEscape(input)
		/** HTML反转义(还原) **/  
		System.out.println("-------------------");
		String s2 = HtmlUtils.htmlUnescape(s);  
		System.out.println(s2);  
		/*
		String str = "银行系，资金托管，账户直通";
		if("银行系，资金托管账户直通".contains("，")){
			System.out.println("有逗号");
		}
		String[] strings = str.split("，");
		System.out.println(strings.length);
		System.out.println("*********");
		for(String s:strings){
			System.out.println(s);
		}
		String s = "OPEN_{0}_WEB";
		System.out.println(MessageFormat.format(s, str));
		
		String orgNumberTemplate = "OPEN_%s_WEB";
		System.out.println(String.format(orgNumberTemplate,str));
		
		System.out.println("product_search_condit_background".endsWith("background"));
		System.out.println(Integer.parseInt("30")/30);
		System.out.println(Integer.parseInt("10")/30);
		System.out.println(Integer.parseInt("180")/30);
		System.out.println(Integer.parseInt("181")/30);
		System.out.println(Integer.parseInt("365")/30);
		System.out.println(Integer.parseInt("360")/30);
		System.out.println(Integer.parseInt("359")/30);
		Integer n = 1;
		Integer nn = 1;
		System.out.println(n.equals("1"));
		System.out.println(n==nn);
		System.out.println(nn.toString());
		*/
	}
	
	@Test
	public void testDemo() throws Exception {
		System.out.println(AppUtils.isInvestorApp("App_investor_web"));
		System.out.println(PlatformEnum.WEB == AppUtils.getPlatform("App_invesor_web"));
		System.out.println(PlatformEnum.WEB.equals(AppUtils.getPlatform("App_investor_web")));
		System.out.println(AppUtils.getPlatform("App_investor_web")); //WEB
		System.out.println(ApplicationUtils.md5Hex("123456"));
		System.out.println(ApplicationUtils.sha1Hex("123456"));
		System.out.println(ApplicationUtils.sha256Hex("123456"));
		/*String str = "1,9";
		System.out.println(str.split(",").length);
		System.out.println(str.split(",")[0]);
		System.out.println(str.split(",")[1]);
		System.out.println(str.contains(","));
		System.out.println(str.contains("a"));
		System.out.println(str.contains(" "));
		System.out.println(str.contains(""));
		//List<String> list = new ArrayList<String>();
		List<String> list1 = null;
		System.out.println(list1.isEmpty());//true
		System.out.println(list1 != null);//true
		System.out.println(list1 == null); //false
		System.out.println(list1.size()>0);
		*/
	}
	
	/**
	 * 机构帐号管理列表
	 */
	@Test
	public void testplatformAcctManagerPageList() throws Exception {
		PlatformManagerListRequest req = new PlatformManagerListRequest();
		req.setType("2");
		req.setPageIndex(1);
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_IOS,this.getUrl(),"/api/platfrom/accountManager/pageList",this.getToken(),Map.class,req);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 机构帐号管理统计
	 */
	@Test
	public void testplatformAcctManagerStatistics() throws Exception {
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_IOS,this.getUrl(),"/api/platfrom/accountManager/statistics",this.getToken(),Map.class);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 绑定机构帐号
	 */
	@Test
	public void testBindOrgAcct() throws Exception {
		BindOrgAcctRequest req = new BindOrgAcctRequest();
		req.setPlatFromNumber("OPEN_TOUCHOU_WEB");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_IOS,this.getUrl(),"/api/platfrom/bindOrgAcct",this.getToken(),Map.class,req);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 是否绑定机构帐号
	 */
	@Test
	public void testIsBindOrgAcct() throws Exception {
		BindOrgAcctRequest req = new BindOrgAcctRequest();
		req.setPlatFromNumber("10000006");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_IOS,this.getUrl(),"/api/platfrom/isBindOrgAcct",this.getToken(),Map.class,req);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 帐号是否存在于第三方平台
	 */
	@Test
	public void testIsExistInPlatform() throws Exception {
		BindOrgAcctRequest req = new BindOrgAcctRequest();
		req.setPlatFromNumber("OPEN_EZHOUXING_WEB");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.INVESTOR_IOS,this.getUrl(),"/api/platfrom/isExistInPlatform",this.getToken(),Map.class,req);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 理财-机构推荐选择列表
	 * @throws Exception
	 */
	@Test
	public void testrecommendChooseList() throws Exception {
		Map<String, String> parameterMap =  new HashMap<String, String>();
		parameterMap.put("orgCode", "OPEN_XIAONIUZAIXIAN_WEB");
//		parameterMap.put("searchValue", "成国峰");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/platfrom/recommendChooseList",this.getToken(),ProductRecommendChooseResponse.class,parameterMap);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 理财-机构选择推荐
	 * @throws Exception
	 */
	@Test
	public void testrecommendByChoose() throws Exception {
		OrgRecommendByChooseRequest orgRecommendByChooseRequest =  new OrgRecommendByChooseRequest();
		orgRecommendByChooseRequest.setOrgCode("OPEN_XIAONIUZAIXIAN_WEB");
		orgRecommendByChooseRequest.setUserIdString("abc60c846f67467abbce17f660c32b4f");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/platfrom/recommendByChoose",this.getToken(),null,orgRecommendByChooseRequest);
		logger.debug(baseResponse.toString());
	}
	
	/**
	 * 投资攻略
	 * @throws Exception
	 */
	@Test
	public void testInvestmentStrategy() throws Exception {
		Map<String, String> parameterMap =  new HashMap<String, String>();
		parameterMap.put("orgCode", "OPEN_XIAONIUZAIXIAN_WEB");
		BaseResponse baseResponse = TestHelper.remote(AppEnum.CHANNEL_ANDROID,this.getUrl(PathEnum.LOCALHOST),"/api/platfrom/investmentStrategy",this.getToken(),InvestmentStrategyResponse.class,parameterMap);
		logger.debug(baseResponse.toString());
	}
}
