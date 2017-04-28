package com.sesxh.hoschange.global;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author zhaohutai
 *
 */
public class BizConst {

	public static final String QUERY_TYPE = "sdsesxh_query_type_key";

	public static final String SESSIONUSER = "_SESSIONUSER_";

	public final static class SC {
		public static final int SC_200 = 200;// Success
		public static final int SC_300 = 300;// BusinessException
		public static final int SC_301 = 301;// time_out--->dologin
		public static final int SC_500 = 500;// Exception
		public static final int SC_501 = 502;// BaseException
		public static final int SC_502 = 502;// FrameException
		public static final int SC_503 = 503;// AppException
		public static final int SC_504 = 504;// AppException

		public static final int SC_303 = 303;// 重复提交
		public static final int SC_401 = 401;// Auth_Error
		public static final int SC_402 = 402;//
	}

	public final static class BOOLEAN {

		public static final String FALSE = "0";
		public static final String TRUE = "1";

	}

	/**
	 * 是否有效
	 * 
	 * @author cyf
	 * @date 2016年12月1日 下午3:38:23
	 * @title BizConst.java
	 *
	 */
	public final static class ENABLED {
		/** 1 有效 **/
		public static final String ENABLE = "1";
		/** 0 无效 **/
		public static final String DISABLE = "0";

	}

	/**
	 * 
	 * @author Lenovo
	 *
	 */
	public final static class DOORANDLOCK {
		/** 0 一致 **/
		public static final String YIYANG = "0";
		/** 1 不一致 **/
		public static final String BUYIYANG = "1";

	}

	/**
	 * 
	 * @author Lenovo
	 *
	 */
	public final static class DOORANDLOCKSTATE {
		/** 门锁的状态一样 */
		public static final String STATE = "0";

	}

	/** 系统配置 configName */
	public final static class SYSCONFIG {
		public static final String ISORNOROSTER = "isOrNoRoster";
		public static final String YESORNOHAVESHOWBOX = "yesOrNoHaveShoeBox";
		public static final String ISORNOTRECYCLED = "isOrNotRecycled";
		public static final String BRUSHCARDTIME = "brushCardTime";
		public static final String MAINFILEPATH = "filepath";
		public static final String POWERVALIDTIME = "PowerValidTime";
		public static final String SINGOUT = "SignOut";
		public static final String DISTRIBUTEWARDROBE = "DistributeWardrobe";
		public static final String STORAGEWARDROBE = "StorageWardrobe";
		public static final String RECYCLINGBIN = "RecyclingBin";
		public static final String WEBSOCKETSERVER = "WebSocketServer";

	}

	public final static class STATE {
		// 状态
		/** 1 有消毒鞋 **/
		public static final String USE = "1";
		/** 0 空柜 **/
		public static final String NOTUSE = "0";
		/** 2 存有自己鞋子，柜子使用中 **/
		public static final String USER_USE = "2";
		/**
		 * 3管理员正往鞋柜中添加鞋子中
		 */
		public static final String ADMIN_ALLOTING = "3";

	}

	public final static class PAGEQUERY {

		public static final String DESC = " desc ";
		public static final String ASC = " asc ";
		public static final String PAGE = "page";
		public static final String ROWS = "rows";
		public static final String SORT = "sort";
		public static final String ORDER = "order";
		public static final String START = "start";
		public static final String END = "end";
		public static final String SIZE = "size";

		public static final int MAX_ROWS = 500;
		public static final int DEFAULT_ROWS = 10;
		public static final int DEFAULT_PAGE = 1;
	}

	public final static class LOGINFO {
		public static final String LOG_ERROR = "log_error";
		public static final String LOG_OPERATION = "log_operation";
	}

	public final static class SFCJGLY {
		/** 是否是超级管理员 1是超级管理员 */
		public static final String TURE = "1";
	}

	public final static class PERMISSION_CODE {
		/** -权限code- 3 手术鞋管理 **/
		public static final String SHOES = "3";
		/** -权限code- 4消毒鞋柜管理 **/
		public static final String STERILIZER = "4";
		/** -权限code- 14灰名单管理 **/
		public static final String GREYLIST = "14";
		/** -权限code- 22黑名单管理 **/
		public static final String BLACKLIST = "22";
		/** -权限code- 19 衣物管理员 **/
		public static final String CLOTHINGADMIN = "19";
		/** -权限code- 20后台管理员 **/
		public static final String BACKADMIN = "20";
	}

	public final static class INFOPAGE {

		public static final String ERROR_PAGE_300 = "pages/300.html";

		public static final String ERROR_PAGE_401 = "pages/401.html";

		public static final String ERROR_PAGE_50X = "pages/500.jsp";
	}

	public final static class RECYCLED {
		/** 手术鞋 可回收 **/
		public static final String TRUE = "1";
		/** 手术鞋 不 可回收 **/
		public static final String FALSE = "0";
	}

	/**
	 * 设备类别
	 * 
	 * @author cyf
	 * @date 2016年12月1日 下午4:25:00
	 * @title BizConst.java
	 *
	 */
	public final static class DEVICE_TYPE {
		/** -设备类别- 1 手术衣发放设备 **/
		public static final String WARDRODE = "1";
		/** -设备类别- 2 消毒鞋柜 **/
		public static final String STERILIZER = "2";
		/** -设备类别- 3 回收桶 **/
		public static final String RECYCLE = "3";
		/** -设备类别- 4 手术衣柜 **/
		public static final String CLOTHESPRESS = "4";
		/** -设备类别- 5 发卡器 **/
		public static final String FKQ = "5";
	}

	/**
	 * 回收桶类别
	 * 
	 *
	 */
	public final static class GOODS_TYPE {
		/** -物品类别- 0 鞋子 **/
		public static final String SHOES = "0";
		/** -物品类别- 1 衣服 **/
		public static final String CLOTHES = "1";
		/** -物品类别- 2 收纳盒 **/
		public static final String BOX = "2";
	}

	public final static class OPERATION_TYPE {
		/** 操作类别 分配 1 **/
		public static final String ALLOT = "1";
		/** 操作类别 领取 2 **/
		public static final String RECEIVE = "2";
		/** 操作类别 领取异常 3 **/
		public static final String ABNORMAL = "3";
		/** 操作类别 打开鞋柜 4 **/
		public static final String OPENSTERILIZER = "4";
		/** 操作类别 打开储物柜 5 **/
		public static final String OPENCLOTHESPRESS = "5";
	}

	public final static class SIZE {
		/** 鞋子尺码 对应字典表 **/
		public static final List<Integer> SHOESSIZE = Arrays.asList(new Integer[] { 1, 2, 3, 4, 5, 6, 7 });

		/** 衣服尺码 对应字典表 **/
		public static final List<Integer> OPERATIONSIZE = Arrays.asList(new Integer[] { 1, 2, 3, 4, 5 });
	}

	public final static class DICT {
		/** 手术鞋尺码 shoes **/
		public static final String SHOES = "shoes";
		/** 手术衣尺码 cloth **/
		public static final String CLOTH = "cloth";
	}

	/**
	 * 刷卡记录是否回收
	 * 
	 * @author cyf
	 * @date 2016年11月30日 下午6:40:48
	 * @title BizConst.java
	 *
	 */
	public final static class ISCALLBACK {
		/** 0未回收 **/
		public static final String NO = "0";
		/** 1 回收 **/
		public static final String YES = "1";
		/** 2 正回收 **/
		public static final String READYCALLBACK = "2";
	}

	/**
	 * 黑灰名单类别
	 * 
	 * @author cyf
	 * @date 2016年12月1日 上午9:07:18
	 * @title BizConst.java
	 *
	 */
	public final static class BLACKLIST_TYPE {
		/** 0 灰名单 **/
		public static final String GREY = "0";
		/** 1 黑名单 **/
		public static final String BLACK = "1";
	}

	/**
	 * 有无黑灰名单
	 * 
	 * @author cyf
	 * @date 2016年12月1日 下午3:23:39
	 * @title BizConst.java
	 *
	 */
	public final static class YESORNO_BLACK {
		/** 1 有名单 **/
		public static final String YES = "1";
		/** 2 无名单 **/
		public static final String NO = "2";
	}

	/**
	 * 黑灰名单规则模式 1 次数限制 模式2 时间段+次数
	 * 
	 * @author cyf
	 * @date 2016年12月1日 上午9:07:18
	 * @title BizConst.java
	 *
	 */
	public final static class BLACKLIST_MODEL {
		/** 模式1 次数限制 **/
		public static final String ONE = "1";
		/** 模式2 时间段+次数 **/
		public static final String TWO = "2";
	}

	/**
	 * 是否在黑名单
	 * 
	 * @author cyf
	 * @date 2016年12月1日 下午2:45:28
	 * @title BizConst.java
	 *
	 */
	public final static class ISORNO_LIST {
		/** 1 在黑名单 **/
		public static final String BLACK_TRUE = "1";
		/** 2 在灰名单 **/
		public static final String GREY_TRUE = "2";
		/** 0 不在名单 **/
		public static final String ALL_FALSE = "0";
	}

	/**
	 * 是否有鞋柜
	 *
	 */
	public final static class YESORNO {
		/** 有鞋柜 **/
		public static final String YES = "1";
		/** 没有鞋柜 **/
		public static final String NO = "0";
	}

	/**
	 * 是否有回收桶
	 *
	 */
	public final static class RecyclingBin {
		/** 有回收桶 **/
		public static final String YES = "0";
		/** 沒有回收桶 **/
		public static final String NO = "1";
	}

	/**
	 * 是否有发衣柜
	 *
	 */
	public final static class StorageWardrobe {
		/** 有发衣柜 **/
		public static final String YES = "0";
		/** 沒有发衣柜 **/
		public static final String NO = "1";
	}

	/**
	 * 鞋柜小柜使用状态
	 *
	 */
	public final static class USE_STATE {
		/** 1 使用中有鞋子 **/
		public static final String USE = "1";
		/** 0 未使用 没有鞋子 **/
		public static final String NO_USE = "0";
		/** 2 使用中 存放使用人的鞋子 **/
		public static final String OTHER_USE = "2";
	}

	/**
	 * 签到签退
	 */
	public final static class SIGN {
		/** 0 签到 **/
		public static final String IN = "0";
		/** 1 签退 **/
		public static final String OUT = "1";
	}

	/**
	 * 同步方式
	 */
	public final static class SYNWAY {
		/** 0 自动同步 **/
		public static final String AUTOMATIC = "0";
		/** 1 手动同步 **/
		public static final String HAND = "1";
	}

}
